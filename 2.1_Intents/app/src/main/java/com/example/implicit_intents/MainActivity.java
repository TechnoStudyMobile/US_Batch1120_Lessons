package com.example.implicit_intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSendMessage;
    private Button buttonSend;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSendMessage = findViewById(R.id.edit_text_send_message);
        buttonSend = findViewById(R.id.button_send);
        buttonCancel = findViewById(R.id.button_cancel);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the text message with a string
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editTextSendMessage.getText().toString());
                sendIntent.setType("text/plain");

                // Verify that the intent will resolve to an activity
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendIntent);
                }

                buttonCancel.setVisibility(View.GONE);
            }
        });
    }


    public void toastMessage(View myView) {
        Toast.makeText(this, "My meesage", Toast.LENGTH_SHORT).show();
    }
}