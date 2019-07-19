package com.example.tictactoe.ui_update_service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HandlerUIUpdateService extends IntentService {

    private CustomHandler handler;
    private Messenger messenger;
    private static final int SERVICE_FINISHED = 1;

    private ArrayList<String> list = new ArrayList<>();


    public HandlerUIUpdateService() {
        super("HandlerUIUpdateService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        messenger = intent.getParcelableExtra("handler");
//        handler = intent.getParcelableExtra("handler");


        Message message = new Message();

        message.what = SERVICE_FINISHED;
        message.obj = "Sending message to UI after completion of background task!";
//        handler.sendMessage(message);


        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
}
