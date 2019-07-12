package com.valtech.socnet.action;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.valtech.socnet.listener.CallBack;
import com.valtech.socnet.model.SocialUser;
import com.valtech.socnet.parser.UserParser;
import com.valtech.socnet.utils.SocnetConstants;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by ankit.thakur on 11-02-2016.
 */
public class SocialFB extends Socnet {

    private CallbackManager logincallbackManager;
    private GraphRequest request;
    private CallBack.OnShare share;
    private CallBack.OnFriendsList friendlist;
    private LoginManager loginmanager;
    private Bundle shareParms;
    private static SocialFB mSocialFB = null;
    CallBack.OnLogin login;
    ProgressDialog pd;


    private int INFO_TYPE;
    private static SharedPreferences mSharedPreferences;

    private SocialFB() {
        pd=new ProgressDialog(mContext);
        pd.setMessage("loading");
        FacebookSdk.sdkInitialize(mContext);
        mSharedPreferences = mContext.getSharedPreferences(SocnetConstants.FB_PREFS_NAME, Context.MODE_PRIVATE);
        result();
    }

    public static SocialFB getInstance() {

        if (mSocialFB == null)
            mSocialFB = new SocialFB();

        return mSocialFB;
    }

    private void result() {
        logincallbackManager = CallbackManager.Factory.create();
        loginmanager = LoginManager.getInstance();
        loginmanager.registerCallback(logincallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putBoolean(SocnetConstants.FB_LOGGEDIN, true);
                editor.commit();

                switch (INFO_TYPE) {

                    case 0:
                        getUserDetails(login);

                        break;
                    case 1:
                        getFriendsList(friendlist);
                        break;
                    case 2:
                        publish();
                        break;
                    default:
                        break;
                }

            }



            @Override
            public void onCancel() {
                  if(login!=null){
                      login.getUserInfo(null);
                  }
                if(friendlist!=null){
                    friendlist.getList(null,false);

                }
                if(share!=null){
                    share.onComplete(null);
                }
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("facebook exception",error.toString());
                Toast.makeText(mContext, "Unable to connect facebook" + error.getMessage(), Toast.LENGTH_SHORT).show();



            }
        });

    }

    public void login(final CallBack.OnLogin login) {
        logout();
        INFO_TYPE = 0;
        this.login=login;
        loginmanager.logInWithReadPermissions((Activity) mContext, Arrays.asList(SocnetConstants.FB_USERS, SocnetConstants.FB_EMAIL));


    }
    public void getFriendsList(final CallBack.OnFriendsList friendlist) {
        this.friendlist=friendlist;
        INFO_TYPE = 1;
        if(isLoggedIn()) {
            pd.show();
            Bundle parameter = new Bundle();
            parameter.putString(SocnetConstants.FB_LIMIT, "25");
            parameter.putString(SocnetConstants.FB_FIELDS,
                    SocnetConstants.FB_ID + "," + SocnetConstants.FB_NAME + "," + SocnetConstants.FB_PICTURE);
            GraphRequest freindlistRequest = new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    SocnetConstants.FB_TAGGABLE_FRIENDS);
            freindlistRequest.setParameters(parameter);
            freindlistRequest.setHttpMethod(HttpMethod.GET);
            freindlistRequest.setCallback(new GraphRequest.Callback() {
                @Override
                public void onCompleted(GraphResponse response) {
                    pd.dismiss();
                    Log.i("frends response",response.toString());
                    if (response.getJSONObject() != null) {
                        List<SocialUser> friendsList = UserParser.getFriendsList(response.getJSONObject());
                        request = response.getRequestForPagedResults(GraphResponse.PagingDirection.NEXT);

                        if (request == null) {
                            friendlist.getList(friendsList, false);

                        } else {
                            friendlist.getList(friendsList, true);
                        }
                    } else {
                        Toast.makeText(mContext, "Facebook exception:  " + response.getError().getErrorType(), Toast.LENGTH_SHORT).show();
                        friendlist.getList(null, false);

                    }
                }

            });
            freindlistRequest.executeAsync();
        }
        else{
            loginmanager.logInWithReadPermissions((Activity) mContext, Arrays.asList(SocnetConstants.FB_USERS, SocnetConstants.FB_EMAIL));
        }
    }


    public void share(final Bundle bundles, final CallBack.OnShare share) {
        INFO_TYPE = 2;
       /* final Bundle params = new Bundle();
        //  params.putString("title", "video");
        //  params.putString("tags", "['AaL5Oz_i1HSXMB55q29NYv9zEkSCINTcBTihAexdVkqFvhHAv5CxZXmdNJWG6uVf_YePDTpj0UShlJw1NyqbpSK80zu-rj73Z-DStHDVJkFjlQ']");
        params.putString("link", "http://www.valtech.co.in/");
        //params.putString("picture", "https://www.facebook.com/655221345/videos/10153826985526346/");
        // params.putString("source", "http://samples.mplayerhq.hu/SWF/test.swf");
        params.putString("name", "Valtech");
        params.putString("caption", "An engineering powerhouse of professionals with strong core values.");
        params.putString("description", "description");*/

        this.share = share;
        this.shareParms = bundles;
        if (AccessToken.getCurrentAccessToken() != null) {

            Set<String> permissions = AccessToken.getCurrentAccessToken().getPermissions();
            if (!(permissions.contains("publish_actions"))) {


                loginmanager.logInWithPublishPermissions((Activity) mContext, Arrays.asList(SocnetConstants.FB_PUBLISH));
            } else {
                publish();
            }

        }


    }

    private void getUserDetails(final CallBack.OnLogin login) {

        if (isLoggedIn()) {
            pd.show();
            Bundle parameters = new Bundle();
            parameters.putString(SocnetConstants.FB_FIELDS, "id,name,email,gender, birthday,cover,picture");
            GraphRequest userRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), SocnetConstants.FB_ME);
            userRequest.setParameters(parameters);
            userRequest.setHttpMethod(HttpMethod.GET);
            userRequest.setCallback(new GraphRequest.Callback() {
                @Override
                public void onCompleted(GraphResponse response) {
                    pd.dismiss();
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putBoolean(SocnetConstants.FB_LOGGEDIN, true);
                    editor.commit();

                    if (response.getJSONObject() != null) {
                        SocialUser user = UserParser.parseFBUserInfo(response.getJSONObject());
                        login.getUserInfo(user);
                    } else {
                        Toast.makeText(mContext, "unable to fetch user info:" + response.getError().getErrorType(), Toast.LENGTH_SHORT).show();
                        login.getUserInfo(null);
                    }

                }
            });

            userRequest.executeAsync();
        } else {
            Toast.makeText(mContext, "unable to fetch user info: user not logged in with facebook", Toast.LENGTH_SHORT).show();
            login.getUserInfo(null);

        }
    }

    private void publish() {
        pd.show();
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                SocnetConstants.FB_FEED,
                shareParms,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        pd.dismiss();
                        share.onComplete(response.toString());
                        Log.i("share rponse", response.toString());

                    }
                }
        ).executeAsync();

    }

    public void getLikes(String postid, final CallBack.OnShare like) {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" +
                        postid +
                        "/likes",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        like.onComplete(response.toString());

                    }
                }
        ).executeAsync();


    }


    public void loadMore(final CallBack.OnFriendsList friendsList) {
        if (request != null && isLoggedIn()) {
            request.setCallback(new GraphRequest.Callback() {
                @Override
                public void onCompleted(GraphResponse response) {
                    //parse json data

                    List<SocialUser> freinds = UserParser.getFriendsList(response.getJSONObject());
                    request = response.getRequestForPagedResults(GraphResponse.PagingDirection.NEXT);

                    if (request == null) {
                        friendsList.getList(freinds, false);

                    } else {
                        friendsList.getList(freinds, true);
                    }
                }
            });
            request.executeAsync();
        } else {
            Toast.makeText(mContext, "No more friends", Toast.LENGTH_SHORT).show();
        }
    }


    public void onResult(int requestCode, int resultCode, Intent data) {
        logincallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    public boolean isLoggedIn() {
        boolean isLoggedIn = mSharedPreferences.getBoolean(SocnetConstants.FB_LOGGEDIN, false);
        return isLoggedIn;
    }


    public void logout() {
        boolean isloggedin=isLoggedIn();
        if (isloggedin) {
            LoginManager.getInstance().logOut();
            AccessToken.setCurrentAccessToken(null);
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(SocnetConstants.FB_LOGGEDIN, false);
            editor.commit();

        }

    }

    private boolean isSubsetOf(List<String> publish_permissions, Set<String> permissions) {
        if (Arrays.asList(permissions).contains(publish_permissions))
            return true;

        return false;
    }
}

