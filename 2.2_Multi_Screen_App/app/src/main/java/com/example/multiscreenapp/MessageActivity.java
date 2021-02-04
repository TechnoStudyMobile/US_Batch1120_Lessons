package com.example.multiscreenapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE_SENT_NAME = "extraMessageSent";
    private final static String LOG_TAG = "MY_APP_TAG";

    public final static int MESSAGE_ACTIVITY_REQUEST_CODE_MESSAGE = 999;
    public final static int MESSAGE_ACTIVITY_REQUEST_CODE_CAMERA = 888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        String message = "Hey";
        Intent intent = getIntent();

        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_NAME);
        int pos = intent.getIntExtra(MainActivity.EXTRA_POSITION_NAME,0);

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        Button closeButton = findViewById(R.id.button_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Successull oldu
                Intent messageIntent = new Intent();
                messageIntent.putExtra(EXTRA_MESSAGE_SENT_NAME,"Message is Successfull");
                setResult(RESULT_OK, messageIntent);
                finish();
            }
        });

        Log.d(LOG_TAG, " onCreate called on MessageActivity");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}