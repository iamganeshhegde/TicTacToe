package com.valtech.socnet.action;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import com.valtech.socnet.listener.CallBack;
import com.valtech.socnet.model.SocialUser;
import com.valtech.socnet.utils.SocnetConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ankit.thakur on 15-02-2016.
 */
public class SocialGP extends Socnet implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;

    private static final String TAG = "SocialGP";
    private CallBack.OnLogin onSuccess;
    private CallBack.OnFriendsList onfriends;
    private CallBack.OnShare onShare;
    private static SocialGP mSocialGP = null;


    private SocialGP() {


        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API,Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_PROFILE)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();


    }

    public static SocialGP getInstance() {

        if (mSocialGP == null)
            mSocialGP = new SocialGP();

        return mSocialGP;
    }

    public void login(CallBack.OnLogin success) {
        onSuccess = success;
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
        }else{
            mGoogleApiClient.connect();
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.i("Connection", "connected");


        if (onSuccess != null)
            onSuccess.getUserInfo(getProfileInformation());
        if (onfriends != null) {
            getFriendsList(onfriends);
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {

        Log.i("connection", "failed");
        int resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(mContext);
        if (resultCode == ConnectionResult.SUCCESS) {

            if (result.hasResolution()) {
                try {
                    result.startResolutionForResult((Activity) mContext, SocnetConstants.RC_SIGN_IN);
                } catch (IntentSender.SendIntentException e) {
                    mGoogleApiClient.connect();
                }
            }

        } else {
            if (resultCode == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Update Google Play Service");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                        i.setData(Uri.parse("market://details?id=com.google.android.gms"));
                        mContext.startActivity(i);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
            else{
                Toast.makeText(mContext,"Unable to connect",Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void getFriendsList(final CallBack.OnFriendsList friendslist) {

        if (mGoogleApiClient != null && isLoggedIn()) {
            Plus.PeopleApi.loadVisible(mGoogleApiClient, null)
                    .setResultCallback(new ResultCallback<People.LoadPeopleResult>() {

                        @Override
                        public void onResult(People.LoadPeopleResult peopleData) {
                            if (peopleData.getStatus().getStatusCode() == CommonStatusCodes.SUCCESS) {

                                PersonBuffer personBuffer = peopleData.getPersonBuffer();
                                try {
                                    int count = personBuffer.getCount();
                                    List<SocialUser> friendsList = new ArrayList<>();
                                    for (int i = 0; i < count; i++) {
                                        SocialUser friend = new SocialUser();
                                        friend.setUsername(personBuffer.get(i).getDisplayName() + "");
                                        friend.setUserId(personBuffer.get(i).getId() + "");
                                        friend.setProfileImage(personBuffer.get(i).getImage().getUrl());

                                        friendsList.add(friend);

                                    }
                                    friendslist.getList(friendsList, false);
                                } finally {
                                    personBuffer.release();

                                }
                            } else {
                                friendslist.getList(null, false);
                                Log.e(TAG, "Error requesting visible circles: " + peopleData.getStatus());
                            }

                        }
                    });
        } else {
            Log.i("google client", "null or not connected");
            onfriends = friendslist;
            login(null);
        }
    }


    public void share(HashMap<String, String> friendsId, String msg, String hashName, CallBack.OnShare onshare) {

        // friendsId.put("id","displayname");


        if (mGoogleApiClient != null && isLoggedIn()) {
            this.onShare=onshare;
            Person user = Plus.PeopleApi
                    .getCurrentPerson(mGoogleApiClient);

            List<Person> recpent = new ArrayList<>();
            if (friendsId != null) {
                for (Map.Entry<String, String> entry : friendsId.entrySet()) {
                    Person pers = PlusShare.createPerson(entry.getKey(), entry.getValue());
                    recpent.add(pers);
                }
            }

            Intent shareIntent = new PlusShare.Builder(mContext)
                    .setType("text/plain")

                    .setText(hashName).
                            setRecipients(user, recpent)

                    .setContentUrl(Uri.parse(msg))
                    .getIntent();

            ((Activity) mContext).startActivityForResult(shareIntent, SocnetConstants.GP_SHARE);
        }


    }


    private SocialUser getProfileInformation() {
        SocialUser user = null;

        try {

            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                user = new SocialUser();
                Person currentPerson = Plus.PeopleApi
                        .getCurrentPerson(mGoogleApiClient);

                user.setUsername(currentPerson.getDisplayName());
               user.setBirthDay(currentPerson.getBirthday());
                user.setGender(currentPerson.getGender()+"");

                user.setProfileImage(currentPerson.getImage().getUrl());
                user.setUserId(currentPerson.getId());
                Log.i("email id",""+Plus.AccountApi.getAccountName(mGoogleApiClient));

                user.setEmailId(Plus.AccountApi.getAccountName(mGoogleApiClient));

                if (currentPerson.hasCover())
                    user.setCoverImage(currentPerson.getCover().getCoverPhoto().getUrl());


            } else {
                return null;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return user;
    }

    public void logout() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.clearDefaultAccountAndReconnect();

            mGoogleApiClient.disconnect();

        }

    }

    public boolean isLoggedIn() {
        return mGoogleApiClient.isConnected();

    }


    public void onResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SocnetConstants.RC_SIGN_IN) mGoogleApiClient.connect();

            if (requestCode == SocnetConstants.GP_SHARE) {
                 onShare.onComplete("success");
            }
        }

    }


    public void loadMore(CallBack.OnFriendsList friendsList) {
        Toast.makeText(mContext, "No more friends", Toast.LENGTH_SHORT).show();
    }

}
