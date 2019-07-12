package com.valtech.socnet.action;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.valtech.socnet.listener.CallBack;
import com.valtech.socnet.model.SocialUser;
import com.valtech.socnet.utils.SocnetConstants;
import com.valtech.socnet.webservice.TwitterFriendsTask;
import com.valtech.socnet.webservice.TwitterURLTask;

import java.util.ArrayList;
import java.util.List;

import socnet.valtech.com.socnet.R;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by ankit.thakur on 09-02-2016.
 */
public class SocialTwitter extends Socnet implements CallBack.OnUrl {

    static {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    private RequestToken requestToken;
    private Twitter twitter;
    private String callbackurl;


    private static SharedPreferences mSharedPreferences;
    private static SocialTwitter mSocialTwitter = null;

    private SocialTwitter() {

    }

    public static SocialTwitter getInstance() {
        if (mSocialTwitter == null) {
            mSocialTwitter = new SocialTwitter();
            mSharedPreferences = mContext.getSharedPreferences(SocnetConstants.TWITTER_PREF_NAME, Context.MODE_PRIVATE);
        }
        return mSocialTwitter;
    }

    public void login(String consumerKey, String consumerSecret, String callbackurl, CallBack.OnLogin success, onDialogCancelListner aDialogCancelListener) {

        this.callbackurl = callbackurl;

        SharedPreferences.Editor e = mSharedPreferences.edit();
        e.putString(SocnetConstants.TWITTER_PREF_KEY_CONSUMER_KEY, consumerKey);
        e.putString(SocnetConstants.TWITTER_PREF_KEY_CONSUMER_SECRET, consumerSecret);
        e.putString(SocnetConstants.TWITTER_PREF_KEY_CALLBCK_URL, callbackurl);
        e.commit();

        final ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(consumerKey);
        builder.setOAuthConsumerSecret(consumerSecret);

        final Configuration configuration = builder.build();
        final TwitterFactory factory = new TwitterFactory(configuration);
        twitter = factory.getInstance();

        setDialogCancelListener(aDialogCancelListener);
        login(success);

    }
    private void login(CallBack.OnLogin success) {
        logout();
        boolean isLoggedIn = isLoggedin();
        if (!isLoggedIn) {
            new TwitterURLTask(twitter, callbackurl, this, success,mContext).execute();
        }
    }
    public void getFriendsList(final CallBack.OnFriendsList delegates) {
        if (isLoggedin()) {
            new TwitterFriendsTask(getConfig(), delegates,mContext).execute();
        }

    }

    public void shareToTimeline(ArrayList<SocialUser> ScreenName, String msg, CallBack.OnShare onShare) {

        if (isLoggedin()) {


             twitter = getConfig();
            if(ScreenName!=null) {
                for (int i = 0; i < ScreenName.size(); i++) {
                    try {
                        StatusUpdate update = new StatusUpdate("@" + ScreenName.get(i).getScreeName() + " " + msg);

               /* if (media != null) {
                    update.setMedia(media);
                }*/
                        twitter.updateStatus(update);
                        onShare.onComplete("Successful posted to your Twitter.");
                        //Toast.makeText(mContext, "Successful posted to your Twitter.", Toast.LENGTH_SHORT).show();
                    } catch (TwitterException e) {
                        e.printStackTrace();
                        if(e.getErrorCode()==135){

                            onShare.onComplete("please update system date");
                        }else {
                            onShare.onComplete("Already you shared this post.");
                        }
                    }
                }
            }
            else{
                try {
                    StatusUpdate update = new StatusUpdate( msg);
            /*if (media != null) {
                update.setMedia(media);
            }*/
                    twitter.updateStatus(update);
                    onShare.onComplete("Successful posted to your Twitter.");
                } catch (TwitterException e) {
                    if(e.getErrorCode()==135){
                        onShare.onComplete("please update system date");
                    }else {
                        onShare.onComplete("Already you shared this post.");
                    }
                    e.printStackTrace();
                }
            }
        }

    }

    public void shareDirectMessage(List<String> friends, String msg, CallBack.OnShare onShare)  {
        if (isLoggedin()) {
             twitter = getConfig();
            for (int i = 0; i < friends.size(); i++) {
                try {
                    twitter.sendDirectMessage(Long.parseLong(friends.get(i)), msg);
                    onShare.onComplete("Successful posted to your Twitter.");
                } catch (TwitterException e) {
                    if(e.getErrorCode()==135){
                        onShare.onComplete("please update system date");
                    }else {
                        onShare.onComplete("Already you shared this post.");
                    }
                    e.printStackTrace();
                }
            }
        }
    }



    private SocialUser saveTwitterInfo(AccessToken accessToken) {


        User user;
        SocialUser AuthUser = null;
        try {
            user = twitter.showUser(accessToken.getUserId());
            AuthUser = new SocialUser();
            AuthUser.setScreeName(user.getScreenName());
            AuthUser.setUserId(user.getId() + "");
            AuthUser.setUsername(user.getName());
            AuthUser.setProfileImage(user.getProfileImageURL());
            AuthUser.setCoverImage(user.getProfileBannerMobileURL());
            /* Storing oAuth tokens to shared preferences */
            SharedPreferences.Editor e = mSharedPreferences.edit();
            e.putString(SocnetConstants.TWITTER_PREF_KEY_OAUTH_TOKEN, accessToken.getToken());
            e.putString(SocnetConstants.TWITTER_PREF_KEY_OAUTH_SECRET, accessToken.getTokenSecret());
            e.putBoolean(SocnetConstants.TWITTER_PREF_KEY_TWITTER_LOGIN, true);

            e.commit();

        } catch (TwitterException e1) {
            e1.printStackTrace();
        }
        return AuthUser;
    }


    @Override
    public void getURL(String url, RequestToken requesttoken, final CallBack.OnLogin success, Integer error) {

        if (url != null && requesttoken != null) {
            this.requestToken = requesttoken;
            final Dialog dialog = new Dialog(mContext);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.activity_webview);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            final ProgressDialog pd = new ProgressDialog(mContext);
            pd.setMessage("Loading...");
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Log.e("CANCEL LISTENER", "CANCEL LISTENER");
                    getDialogCancelLisnter().onDialogCancel();
                }
            });


            dialog.show();
            WebView webview = (WebView) dialog.findViewById(R.id.webView);
            webview.loadUrl(url);
            webview.getSettings().setJavaScriptEnabled(true);

            webview.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    pd.show();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if (pd.isShowing()) pd.dismiss();
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {


                    if (url.contains(callbackurl)) {
                        try {
                            Uri uri = Uri.parse(url);

				/* Sending results back */
                            String verifier = uri.getQueryParameter(SocnetConstants.TWITTER_PREF_KEY_OAUTH_VRIFI);

                            if (verifier != null) {
                                AccessToken accesstoken = twitter.getOAuthAccessToken(requestToken, verifier);

                                SocialUser userInfo = saveTwitterInfo(accesstoken);
                                success.getUserInfo(userInfo);
                                dialog.dismiss();
                            } else {
                                dialog.dismiss();
                                success.getUserInfo(null);
                            }
                        } catch (TwitterException e) {

                        } finally {
                            if (dialog.isShowing()) dialog.dismiss();
                        }
                /* closing webview */

                        return true;
                    }
                    return false;
                }
            });
        }
        else{

            success.getUserInfo(null);
            if(error!=null){
                Toast.makeText(mContext,"Please update System date",Toast.LENGTH_LONG).show();
            }
        }
    }



    public void logout() {
        if (isLoggedin()) {

          //  twitter.setOAuthAccessToken(null);
                android.webkit.CookieManager cookieManager = CookieManager.getInstance();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    cookieManager.removeAllCookies(new ValueCallback<Boolean>() {
                        // a callback which is executed when the cookies have been removed
                        @Override
                        public void onReceiveValue(Boolean aBoolean) {
                            Log.d("Cookie", "Cookie removed: " + aBoolean);
                        }
                    });
                } else cookieManager.removeAllCookie();

            mSharedPreferences.edit().clear().commit();

        }
    }

    public boolean isLoggedin() {
        boolean isLoggedIn=mSharedPreferences.getBoolean(SocnetConstants.TWITTER_PREF_KEY_TWITTER_LOGIN, false);;
        return isLoggedIn;
    }

    private Twitter getConfig() {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(mSharedPreferences.getString(SocnetConstants.TWITTER_PREF_KEY_CONSUMER_KEY, ""));
        builder.setOAuthConsumerSecret(mSharedPreferences.getString(SocnetConstants.TWITTER_PREF_KEY_CONSUMER_SECRET, ""));
        // Access Token
        String access_token = mSharedPreferences.getString(SocnetConstants.TWITTER_PREF_KEY_OAUTH_TOKEN, "");
        // Access Token Secret
        String access_token_secret = mSharedPreferences.getString(SocnetConstants.TWITTER_PREF_KEY_OAUTH_SECRET, "");

        AccessToken accessToken = new AccessToken(access_token, access_token_secret);
         twitter = new TwitterFactory(builder.build()).getInstance(accessToken);
        return twitter;
    }


    public void setDialogCancelListener(onDialogCancelListner aDialogCancelListen) {
        this.myDialogCancelListener = aDialogCancelListen;
    }

    public onDialogCancelListner getDialogCancelLisnter() {
        return myDialogCancelListener;
    }

    onDialogCancelListner myDialogCancelListener;

    /**
     * Dialog cancel listener
     */
    public interface onDialogCancelListner {
        public void onDialogCancel();
    }

}
