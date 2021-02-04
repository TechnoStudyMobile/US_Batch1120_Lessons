package com.example.multiscreenapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE_NAME = "extraMessage";
    public final static String EXTRA_POSITION_NAME = "extraPosition";

    public final static int MAIN_ACTIVITY_REQUEST_CODE_MESSAGE = 999;
    public final static int MAIN_ACTIVITY_REQUEST_CODE_CAMERA = 888;

    private TextView textViewGoToMessage;
    public TextInputEditText editTextMessage;
    private TextView textViewRetrievedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewGoToMessage = findViewById(R.id.textview_goto_message_activity);
        editTextMessage = findViewById(R.id.edit_text_message);
        textViewRetrievedMessage = findViewById(R.id.textview_message);

        textViewGoToMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMessageActivity();
            }
        });
    }

    public void goToMessageActivity() {
        //Explicit intent
        Intent intent = new Intent(this, MessageActivity.class);
        String message = editTextMessage.getText().toString();

        /*Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MESSAGE_NAME, message);
        bundle.putInt(EXTRA_POSITION_NAME, 5);
        intent.putExtras(bundle);*/

        intent.putExtra(EXTRA_MESSAGE_NAME, message);
        intent.putExtra(EXTRA_POSITION_NAME, 5);

        //startActivity(intent);
        startActivityForResult(intent, MAIN_ACTIVITY_REQUEST_CODE_MESSAGE);
    }

    // it will start CameraActivity
    public void gotoCameraActivity() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivityForResult(intent, MAIN_ACTIVITY_REQUEST_CODE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // I also wanna do this
        if (requestCode == MAIN_ACTIVITY_REQUEST_CODE_MESSAGE && resultCode == RESULT_OK && data != null) {
            String retrievedMessage = data.getStringExtra(MessageActivity.EXTRA_MESSAGE_SENT_NAME);
            textViewRetrievedMessage.setText(retrievedMessage);
        } else if (requestCode == MAIN_ACTIVITY_REQUEST_CODE_MESSAGE && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        } else if (requestCode == MAIN_ACTIVITY_REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            // Coming from CameraActivity
        }
    }
}