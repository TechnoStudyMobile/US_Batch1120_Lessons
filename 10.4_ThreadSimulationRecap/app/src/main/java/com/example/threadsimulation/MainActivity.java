package com.example.threadsimulation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;
    private TextView textView;
    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //info: Main-UI thread is our application's default thread that has been created and managed
        //      by OS. It's used for UI interaction and drawing the screen.

        //Info: Syncronous programming: tasks run one after another.
        //Info: Asyncronous programming: task run in parallel to each other, at the same time.

        //Rule1: If you are going to do a long lasting operation, create a new thread and do it there.
        //Rule2: Android UI is drawn on Main-UI thread, we should not block it. If you block it, it will skip frames. (app is gonna lag)
        //Rule3: If you are on a thread other than Main thread, you cannot interact with UI toolkit.
        //       You cannot do any UI operation. UI toolkit is not thread-safe.

        // We wanna interact with UI
        // 3.A : We are on View types - Activity-Fragment:

        //Activity.runOnUiThread(Runnable)
        //View.post(Runnable)
        //View.postDelayed(Runnable, long)

        // 3.B : We are in ViewModel
        //  You can use LiveData

        // 3.C : Other classes
        // Handler handler = new Handler(Looper.getMainLooper());
        // handler.post(Runnable)

        // Or Callbacks using Interfaces

        // set: Should be used on another thread. (not Main)
        //usersLiveData.setValue(new ArrayList<>());

        // post: Should be used on Main thread
        //usersLiveData.postValue(new ArrayList<>());

        super.onCreate(savedInstanceState);         //task1
        setContentView(R.layout.activity_main); //task2

        button = findViewById(R.id.button); //task3
        button2 = findViewById(R.id.button2); //task3
        textView = findViewById(R.id.textView); //task4

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFileAndStuff(500L);
            }
        });     //task5

        Manager manager = new Manager();
        manager.drawFile();

        //createFileAndStuff(12000L);       //Task6

        // Task 7

        // phone draws 60 frame per sec
        // if you block for 2 sec = 120 skipped
        // if you block for 0.5 sec = 30 skipped

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.loadUsers();      //Long lasting task

        viewModel.getUsersLiveData().observe(this, users -> {
            // update UI
        });

        doCreateFileOperation();

    }
/*
      thread A -> |<---A---->|
            /
    thread B --------------->->|<----B---------->|
    */

    //ThreadB is UI Thread
    private void doCreateFileOperation() {
        Manager manager = new Manager();
        //TaskA
        manager.createFile(new ManagerListener() {
            @Override
            public void created() {
                //TaskB
                manager.runSomeErrands();
                // show something
            }
        });

        // Continue

        // Do other stuff

        createFileAndStuff(500L);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // This is a callback
    }

    // We should we this to new Thread
    // On another thread we cannot access to views by default
    private void createFileAndStuff(Long milliSec) {

        // We are in Main thread

        //Created new thread
        //Worker Omer
        new Thread(new Runnable() {
            @Override
            public void run() {
                // We are in Omer thread

                // I did some operations
                // it lasted for 3 sec.

                // Now I wanna update my text
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // We are in Main thread
                        // Now we are on UI thread.
                        // Worker android
                        textView.setText("Hey there");
                    }
                });

                // We are in Omer thread

                // Worker android
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        // Now we are on Main thread.
                    }
                });

                // Worker android
                textView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Now we are on Main-UI thread.
                    }
                },100L);


                // Worker Omer
                Log.d("APP","Sleeping" + milliSec);
                try {
                    Thread.sleep(milliSec);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}