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

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;
    private TextView textView;
    private MyViewModel viewModel;
    Manager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Android application will start by default as a single-thread
        // Main Thread - UI Thread - Cannot be Removed
        // Main threads handles everything related to UI. Including drawing your screen
        // UI related stuff won't work on another thread other than Main-UI thread.
        // Worker Android

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

        //createFileAndStuff(12000L);       //Task6

        // Task 7

        // phone draws 60 frame per sec
        // if you block for 2 sec = 120 skipped
        // if you block for 0.5 sec = 30 skipped

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.loadUsers();

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
        //TaskA
        createFile(new ManagerListener() {
            @Override
            public void created() {
                //TaskB
                manager.runSomeErrands();
            }
        });
    }

    // Asyncronous programming example 2
    private void createFile(ManagerListener listener) {
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
                listener.created();
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // This is a callback
    }

    // We should we this to new Thread
    // On another thread we cannot access to views by default
    private void createFileAndStuff(Long milliSec) {
        //Created new thread
        //Worker Omer
        new Thread(new Runnable() {
            @Override
            public void run() {
                // I did some operations
                // it lasted for 3 sec.

                // Now I wanna update my text
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Now we are on UI thread.
                        // Worker android
                        textView.setText("Hey there");
                    }
                });

                // Worker android
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        // Now we are on UI thread.
                    }
                });

                // Worker android
                textView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Now we are on UI thread.
                    }
                },500L);


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