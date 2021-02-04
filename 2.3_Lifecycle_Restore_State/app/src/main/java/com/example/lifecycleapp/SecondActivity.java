package com.example.lifecycleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import static com.example.lifecycleapp.FirstActivity.LOG_TAG;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(LOG_TAG,SecondActivity.class.getName() + " is on onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Add you logic
        Log.d(LOG_TAG,SecondActivity.class.getName() + " is on onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG,SecondActivity.class.getName() + " is on onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG,SecondActivity.class.getName() + " is on onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG,SecondActivity.class.getName() + " is on onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG,SecondActivity.class.getName() + " is on onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,SecondActivity.class.getName() + " is on onDestroy");
    }
}