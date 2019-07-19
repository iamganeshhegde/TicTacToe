package com.example.tictactoe.ui_update_service;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tictactoe.R;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServiceUIUpdateClass extends AppCompatActivity implements CustomHandler.AppReciver {

    private static final String TAG = ServiceUIUpdateClass.class.getSimpleName();
    TextView serviceResponce;
    BroadcastReceiver broadcastReceiver;
    String INTENT_FILTER = "com.myServiceIntent";


    CustomHandler customHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_service_ui_update);



        List<Integer> list = new CopyOnWriteArrayList<>();

        list.add(20);
        list.add(20);
        list.add(30);
        list.add(10);
        list.add(30);
        list.add(50);
        list.add(60);
        list.add(70);


//        Collections.reverse(list);

     /* Collections.sort(list, new Comparator<Integer>() {
          @Override
          public int compare(Integer o1, Integer o2) {

              return o1 -o2;

          }
      });
*/



     Iterator iterator = list.iterator();

     while (iterator.hasNext()){

         list.remove(iterator.next());

//         iterator.remove();
     }




    /* for(Integer i:list){
         list.remove(i);
     }*/

     /*for(int i=0;i<list.size();i++){
         list.remove(i);
     }*/


//     Collections.sort(list);

        Log.i(TAG,list.toString());


        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String myValue = intent.getStringExtra("data");
                serviceResponce.setText(myValue);


            }
        };


        init();







    }

    private void registerHandlerService() {
        Intent intent = new Intent(this,HandlerUIUpdateService.class);

        customHandler = new CustomHandler(this);

        intent.putExtra("handler",new Messenger(customHandler));
        startService(intent);

    }

    private void init() {

       /* try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        serviceResponce = findViewById(R.id.serviceResponce);
    }


    public void onClick(View view) {


        switch (view.getId()){
            case R.id.buttonHandler:{

                registerHandlerService();

//                startService(new Intent(this,HandlerUIUpdateService.class));
                break;
            }

            case R.id.button2:{
                startService(new Intent(this,MyIntentService.class));

                break;
            }
        }

    }


    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(broadcastReceiver,new IntentFilter(INTENT_FILTER));

    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onReceiveResult(@NotNull Message message) {


        Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show();

    }
}
