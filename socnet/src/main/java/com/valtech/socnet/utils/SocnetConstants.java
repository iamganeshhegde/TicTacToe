package com.valtech.socnet.utils;

/**
 * Created by ankit.thakur on 24-02-2016.
 */
public class SocnetConstants {

    /*
    *    facebook constants
    * */
    public static final String FB_FIELDS="fields";
    public static final String FB_ID="id";
    public static final String FB_NAME="name";
    public static final String FB_PICTURE="picture";
    public static final String FB_EMAIL="email";
    public static final String FB_LIMIT="limit";
    public static final String FB_COVER="cover";
    public static final String FB_COVER_SOURCE="source";
    public static final String FB_USERS="user_friends";
    public static final String FB_PUBLISH="publish_actions";
    public static final String FB_ME="me";
    public static final String FB_TAGGABLE_FRIENDS="/me/taggable_friends";
    public static final String FB_USER_FRIENDS="/me/user_friends";
    public static final String FB_FEED="/me/feed";

    /*
    *  sharedpreference  Access token user looggedin or not
    */
    public static final String FB_LOGGEDIN="fb_logged_in";

    public static final String FB_PREFS_NAME ="fb_prefs";


/*
*   google plus constants
* */


    public static final int RC_FB_SIGN_IN = 64206;
    public static final int RC_SIGN_IN = 100;
    public static final int GP_SHARE=239;

    public static final String GOOGLE_PREF_NAME = "google_pref";
    public static final String GOOGLE_LOGGEDIN="google_logged_in";

/*
*   twitter constants
* */

    public static final String TWITTER_PREF_NAME = "twitter_pref";
    public static final String TWITTER_PREF_KEY_OAUTH_TOKEN = "oauth_token";
    public static final String TWITTER_PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
    public static final String TWITTER_PREF_KEY_TWITTER_LOGIN = "is_twitter_loggedin";
    public static final String TWITTER_PREF_KEY_OAUTH_VRIFI="oauth_verifier";
    public static final String TWITTER_PREF_KEY_CONSUMER_KEY="consumer_key";
    public static final String TWITTER_PREF_KEY_CONSUMER_SECRET="consumer_secret";
    public static final String TWITTER_PREF_KEY_CALLBCK_URL="callback_url";
}
