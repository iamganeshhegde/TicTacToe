package com.valtech.socnet.webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.valtech.socnet.listener.CallBack;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

/**
 * Created by ankit.thakur on 10-02-2016.
 */
public class TwitterURLTask extends AsyncTask<Void, Void, String> {
    RequestToken requesttoken;
    Twitter twitter;
    String callbackurl;
    CallBack.OnUrl delegates;
    CallBack.OnLogin success;
    ProgressDialog progress;
    Context context;
    String url = null;
   Integer error;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress.show();
    }

    public TwitterURLTask(Twitter twitter, String callbackurl, CallBack.OnUrl url, CallBack.OnLogin success, Context context) {
        this.context = context;
        this.twitter = twitter;
        this.delegates = url;
        this.callbackurl = callbackurl;
        this.success = success;
        progress = new ProgressDialog(context);
        progress.setMessage("Loading");
        progress.setCancelable(false);
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {
            requesttoken = twitter.getOAuthRequestToken(callbackurl);
            url = requesttoken.getAuthenticationURL();
        } catch (TwitterException e) {

            if (e.getErrorCode() == 135) {
                error=135;
                Log.i("Twitter exception", "System date inCorrect");

            }
        }

        return url;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progress.dismiss();
        if (delegates != null ) {
            delegates.getURL(s, requesttoken, success,error);
        }
    }
}
