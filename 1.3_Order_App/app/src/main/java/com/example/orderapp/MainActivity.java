package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  boolean whipedcream = false;
    private  boolean chocolatechecked = false;

    private TextView textView;
    private Button button1;
    private Button button2;
    private Button order;
    private CheckBox cream;
    private CheckBox chocolate;
    private int count = 0;
    private TextView reciept;
    private TextView toppings;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.plus);
        textView = findViewById(R.id.amount);
        button2 = findViewById(R.id.minus);
        cream=findViewById(R.id.whipped_cream);
        chocolate=findViewById(R.id.chocolate);
        order= findViewById(R.id.order);
        reciept=findViewById(R.id.reciept);
        editText = findViewById(R.id.name_text);
        toppings = findViewById(R.id.toppings);

        toppings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                textView.setText(String.valueOf(count));
            }

        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count>0){
                    count--;
                    textView.setText(String.valueOf(count));
                }}
        });

        cream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("Myapp","Status of checkbox"+isChecked);
                whipedcream=isChecked;
            }
        });

        chocolate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("Myapp","Status of checkbox"+isChecked);
                chocolatechecked=isChecked;
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = 0;
                if (whipedcream){
                    total += 10;
                }
                if (chocolatechecked){
                    total += 5;
                }

                total = total * count;

                reciept.setText("ORDER SUMMARY"+"\n"+
                        "Person: " + editText.getText() + "\n"+
                        "Add wipped cream? "+whipedcream+"\n"+"Add chocolate? "+chocolatechecked+"\n"+"Quantity: "+ textView.getText() +"\n"+"Total" + total + "$" +"\n"+"Thank you!!!"
                );
            }
        });

    }
}