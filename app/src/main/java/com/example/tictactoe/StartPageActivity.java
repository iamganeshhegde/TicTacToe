package com.example.tictactoe;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.example.tictactoe.camera_without_preview.CameraPreviewActivity;
import com.example.tictactoe.contentProviderExample.ContentProviderActivity;
import com.example.tictactoe.example.A;
import com.example.tictactoe.example.B;
import com.example.tictactoe.java_coding_examples.JavaCodingExamples;
import com.example.tictactoe.launchmodes.LaunchModeA;
import com.example.tictactoe.multithreadingExample.MultiThreadingExampleActivity;
import com.example.tictactoe.pending_intent.PendingIntentActivity;
import com.example.tictactoe.roomExample.RoomExampleActivity;
import com.example.tictactoe.rxjavaexample.RXJavaActivity;
import com.example.tictactoe.sensorsExample.SensorsExampleActivity;
import com.example.tictactoe.servicesExample.ServicesExampleActivity;
import com.example.tictactoe.twitterExample.ui.activities.TwitterMainActivity;
import com.example.tictactoe.viewPager2.ViewPagerActivity;

import java.util.*;

public class StartPageActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = StartPageActivity.class.getSimpleName();
    Button btnTicTacToe,btnViewPager2,btnAnimation,btnRXJava,btnContentProvider,
            btnService,btnArrayList,btnMultiThreading,btnSensors,btnRoom,btnLaunchMode,
            btnPendingIntent,btnTwitter,btnJavaCoding,btnCameraPreview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_page);
A a=new B();
a.say();

        btnTicTacToe = findViewById(R.id.btnTicTacToe);
        btnViewPager2 = findViewById(R.id.btnViewPager2);
        btnAnimation = findViewById(R.id.btnAnimation);
        btnRXJava = findViewById(R.id.btnRXJava);
        btnContentProvider = findViewById(R.id.btnContentProvider);
        btnService = findViewById(R.id.btnService);
        btnArrayList = findViewById(R.id.btnArrayList);
        btnMultiThreading = findViewById(R.id.btnMultiThreading);
        btnSensors = findViewById(R.id.btnSensors);
        btnRoom = findViewById(R.id.btnRoom);
        btnLaunchMode = findViewById(R.id.btnLaunchMode);
        btnPendingIntent = findViewById(R.id.btnPendingIntent);
        btnTwitter = findViewById(R.id.btnTwitter);
        btnJavaCoding = findViewById(R.id.btnJavaCoding);
        btnCameraPreview = findViewById(R.id.btnCameraPreview);

        btnTicTacToe.setOnClickListener(this);
        btnViewPager2.setOnClickListener(this);
        btnAnimation.setOnClickListener(this);
        btnRXJava.setOnClickListener(this);
        btnContentProvider.setOnClickListener(this);
        btnService.setOnClickListener(this);
        btnArrayList.setOnClickListener(this);
        btnMultiThreading.setOnClickListener(this);
        btnSensors.setOnClickListener(this);
        btnRoom.setOnClickListener(this);
        btnLaunchMode.setOnClickListener(this);
        btnPendingIntent.setOnClickListener(this);
        btnTwitter.setOnClickListener(this);
        btnJavaCoding.setOnClickListener(this);
        btnCameraPreview.setOnClickListener(this);


        /*A classA = new B();
        classA.say();
        classA.print();*/




    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnTicTacToe:
                startActivity(new Intent(this,MainActivity.class));
                break;

            case R.id.btnViewPager2:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;

            case R.id.btnAnimation:

                startActivity(new Intent(this,AnimationExamplesActivity.class));
                break;

            case R.id.btnRXJava:

                startActivity(new Intent(this, RXJavaActivity.class));


                /*Intent intent = new Intent(this,RXJavaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                startActivity(intent);*/
                break;

            case R.id.btnContentProvider:

                startActivity(new Intent(this, ContentProviderActivity.class));
                break;

            case R.id.btnService:

                startActivity(new Intent(this, ServicesExampleActivity.class));
                break;

            case R.id.btnArrayList:

                checkArrayAvailability();


                break;

            case R.id.btnMultiThreading:

                startActivity(new Intent(this, MultiThreadingExampleActivity.class));
                break;

            case R.id.btnSensors:

                startActivity(new Intent(this, SensorsExampleActivity.class));
                break;

            case R.id.btnRoom:

                startActivity(new Intent(this, RoomExampleActivity.class));
                break;

            case R.id.btnLaunchMode:


                startActivity(new Intent(this, LaunchModeA.class));

                break;

            case R.id.btnPendingIntent:

                openNotification();
                break;

            case R.id.btnTwitter:
                startActivity(new Intent(this, TwitterMainActivity.class));
                break;

            case R.id.btnJavaCoding:

                startActivity(new Intent(this, JavaCodingExamples.class));
                break;

            case R.id.btnCameraPreview:
                startActivity(new Intent(this, CameraPreviewActivity.class));
                break;

        }
    }

    private void openNotification() {


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        Intent intent = new Intent(this, PendingIntentActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        notificationBuilder.setSmallIcon(android.R.drawable.btn_plus);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setContentTitle("Notification Pending Intent");
        notificationBuilder.setContentText("notify text");
        notificationBuilder.setSubText("subtext");

        NotificationManager notificationManager = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0,notificationBuilder.build());


    }



    private void checkArrayAvailability() {

        String[] array = new String[8];
        String[] tempArray = new String[5];
        Map<String,Integer> totalOccurenceMap = new HashMap<>();
        ArrayList<String> answerArray = new ArrayList<>();





        array[0] = "https://www.apple.com";
        array[1] = "https://www.apple.com";
        array[2] = "https://www.wiki.com";
        array[3] = "https://www.hacker.com";
        array[4] = "https://www.hacker.com";
        array[5] = "https://www.wiki.com";
        array[6] = "https://www.wiki.com";
        array[7] = "https://www.hacker.com";




        for(String current:array){
            int count = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                count = totalOccurenceMap.getOrDefault(current,0);
            }

            totalOccurenceMap.put(current,count+1);

            answerArray.add(current);


        }


        FrequencyComparator frequencyComparator = new FrequencyComparator(totalOccurenceMap);


        Collections.sort(answerArray,frequencyComparator);




        ArrayList<String> newDistinctAnsewerArrayList = new ArrayList<>();

        for(String element: answerArray)
        {
            if(!newDistinctAnsewerArrayList.contains(element))
            {
                newDistinctAnsewerArrayList.add(element);
            }
        }


        Log.i(TAG,totalOccurenceMap.toString());

        Log.i(TAG,answerArray.toString());

        Log.i(TAG,newDistinctAnsewerArrayList.toString());








/*
        for(String webSite:array){

            if(totalOccurenceMap.containsKey(webSite))
            {
                Integer count = totalOccurenceMap.get(webSite);

                totalOccurenceMap.put(webSite,++count);

            }else {
                totalOccurenceMap.put(webSite,1);
            }

        }*/



        /*FrequencyComparator frequencyComparator = new FrequencyComparator(totalOccurenceMap);

        Map<String, Integer> sortedMap = new TreeMap<String, Integer>(frequencyComparator);

        sortedMap.putAll(totalOccurenceMap);


        ArrayList<String> output = new ArrayList<String>();
        for(String web:sortedMap.keySet())
        {
            for(int c=0;c<sortedMap.get(web);c++){
                output.add(web);
            }
        }*/


/*
            for(int i=0;i<array.length;i++)
        {

            for(int j=0; j< tempArray.length; j++)
            {
                if(tempArray[j] != null && array[i].equals(tempArray[j]))
                {

                }else {
                    tempArray[j] = array[i];

                }

            }
        }*/

//        Log.i(TAG,output.toString());


    }



    public class FrequencyComparator implements Comparator<String>{

        Map<String,Integer> refMap;

        FrequencyComparator(Map<String,Integer> refMap){
            this.refMap = refMap;
        }

        @Override
        public int compare(String o1, String o2) {


            //frequency
            int freqCounrt = refMap.get(o2).compareTo(refMap.get(o1));


            //value

            int valCompare = o1.compareTo(o2);

            if(freqCounrt == 0)
            {
                return valCompare;
            }else {
                return freqCounrt;
            }


        }
    }


    protected void onDestroy() {
        super.onDestroy();
    }
}
