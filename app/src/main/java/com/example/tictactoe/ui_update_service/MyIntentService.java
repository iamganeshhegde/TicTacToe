package com.example.tictactoe.ui_update_service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {




    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        Intent myBroadcastIntent = new Intent("com.myServiceIntent");

        myBroadcastIntent.putExtra("data","Even this is also data");

        sendBroadcast(myBroadcastIntent);





    }





}
