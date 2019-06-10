package com.example.weekend1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class Re extends AppCompatActivity {
    TextView mTextview;
    String tmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re);
        mTextview = (TextView)findViewById(R.id.Show);
        tmp=mTextview.getText().toString();
        mTextview.setText(tmp+"\n"+getIntent().getStringExtra("mytext"));


    }


}
