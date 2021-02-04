package com.example.lifecycleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    public static final String LOG_TAG = "myLogtag";
    private Button buttonNavigate;
    private TextView textTitle;
    private ConstraintLayout layoutMain;
    private boolean isButtonClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        buttonNavigate = findViewById(R.id.button_navigate);
        textTitle = findViewById(R.id.textView_title);
        layoutMain = findViewById(R.id.layout_main);

        if (savedInstanceState == null) {
            Log.d(LOG_TAG,FirstActivity.class.getName() + "Bundle is null on onCreate");
        }

        if (savedInstanceState != null && savedInstanceState.getBoolean("KEYSTATEUI")) {
            isButtonClicked = true;
            changeUI();
        }

        //buttonNavigate.setVisibility(View.INVISIBLE);
        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigate to SecondActivity
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
                //showAlertMessage();
                /*isButtonClicked = true;
                changeUI();*/
            }
        });

        Log.d(LOG_TAG,FirstActivity.class.getName() + " is on onCreate");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG,FirstActivity.class.getName() + " is on onSaveInstanceState");
        outState.putBoolean("KEYSTATEUI", isButtonClicked);
    }

    public void changeUI() {
        textTitle.setText("First Activity");
        layoutMain.setBackgroundColor(getResources().getColor(R.color.teal_700));
        Log.d(LOG_TAG,FirstActivity.class.getName() + " UI is changing");
    }

    public void showAlertMessage() {
        //Builder pattern
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // dismiss
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Add you logic
        Log.d(LOG_TAG,FirstActivity.class.getName() + " is on onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG,FirstActivity.class.getName() + " is on onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG,FirstActivity.class.getName() + " is on onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG,FirstActivity.class.getName() + " is on onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG,FirstActivity.class.getName() + " is on onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,FirstActivity.class.getName() + " is on onDestroy");
    }
}