package com.example.threadsimulation;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;


public class Manager {

    Manager manager;

    Handler handler = new Handler(Looper.getMainLooper());

    public void runSomeErrands() {
        Log.d("APP", "Running errands");
    }

    public void createFile(Context context){

    }

    public void drawFile() {
        // Main thread
        // do some stuff


        new Thread(new Runnable() {
            @Override
            public void run() {
                // Thread Smith
                // We are on thread smith
                // you did create the file and draw
                //createFile(null);
                // We are on Thread smith

                // now you wanna do access to main thread
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // We are on Main thread.
                        show();
                    }
                });

            }
        }).start();

        // Thread Main
        show();
    }

    // Asyncronous programming example 2
    public void createFile(ManagerListener callback) {

        //Thread A
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Lots of stuff
                try {
                    Thread.sleep(300L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // at the end Manager is created
                manager = new Manager();

                callback.created();
            }
        }).start();
    }

    public void show(){
        //
    }
}
