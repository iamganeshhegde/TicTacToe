package com.example.tictactoe.pure_java_lang;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class PureJavaLangCodes extends AppCompatActivity implements checkMethodInterface{

    private String TAG = PureJavaLangCodes.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        checkInterfaceMethods();

        checkMethod();

        hashsetMethods();

        treesetMethods();

        treemapExample();

        hashmapMethod();


        try {
            producerConsumerProblem();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void producerConsumerProblem() throws InterruptedException {


        final PC pc = new PC();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                pc.produce();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                pc.consume();
            }
        });

        t1.start();
        t2.start();


        //t1 finnishes before t2

        t1.join();
        t2.join();





    }

    private void hashmapMethod() {



        //it does not maintain insertion order and allows null key value pairs
        HashMap hashMap = new HashMap();

        hashMap.put(1,1);
        hashMap.put(1,2);
        hashMap.put(10,6);
        hashMap.put(6,6);
        hashMap.put(7,20);
        hashMap.put(5,null);
        hashMap.put(null,10);
        hashMap.put(null,12);

        Log.i(TAG,"hashmap"+ String.valueOf(hashMap));


    }

    private void treemapExample() {


        //sorted order of keys  no null keys
        TreeMap treeMap = new TreeMap();

        treeMap.put(1,10);
        treeMap.put(2,100);
        treeMap.put(3,120);
        treeMap.put(6,90);
        treeMap.put(5,80);
        treeMap.put(4,null);
        treeMap.put(7,null);
//        treeMap.put(null,null);

        Log.i(TAG,"treeMap"+ String.valueOf(treeMap));

       /*  treeMap.comparator();

         Comparator comparator = new Comparator() {

             @Override
             public int compare(Object o1, Object o2) {
                 return (Integer) o1 - (Integer)o2 ;
             }

         };
*/
        Log.i(TAG,"treeMap"+ String.valueOf(treeMap));


    }

    private void treesetMethods() {


        //does not allow null values no repetition does not maintain insertion order  stores in natural sort ordering
        TreeSet treeSet = new TreeSet();

        treeSet.add("Hi");
        treeSet.add("Hi");
        treeSet.add("Hello");
        treeSet.add("Heeeee");
        treeSet.add("Hoooooo");
        treeSet.add("Hoooooo");
        treeSet.add("Hioo");
//        treeSet.add(null);


        Log.i(TAG,"treeset"+ String.valueOf(treeSet));

    }


    private void hashsetMethods() {



        //hashset can include null elements but no repeatation   does not maintain insertion order

        HashSet hashSet = new HashSet();

        hashSet.add(null);
        hashSet.add(null);
        hashSet.add("Hi");
        hashSet.add("Hi");
        hashSet.add("hello");
        hashSet.add("helloooo");
        hashSet.add(null);


        Log.i(TAG, String.valueOf(hashSet));

    }

//    private void checkMethod(){
//
//    }

    private void checkInterfaceMethods() {

    }

    @Override
    public void checkMethod() {



    }

    private class PC {


        LinkedList<Integer> linkedList = new LinkedList<>();

        int capacity = 2;

        public void produce(){

            

        }




    }
}