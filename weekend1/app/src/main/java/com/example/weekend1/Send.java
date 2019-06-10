package com.example.weekend1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Send extends AppCompatActivity {
    Button button1;

    EditText edtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        edtext=findViewById(R.id.texttosend);
        button1=findViewById(R.id.send_btn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtext.getText().toString();

                Intent myIntent = new Intent(view.getContext(),Re.class);
                myIntent.putExtra("mytext",text);
                startActivity(myIntent);

            }
        });

    }
}
