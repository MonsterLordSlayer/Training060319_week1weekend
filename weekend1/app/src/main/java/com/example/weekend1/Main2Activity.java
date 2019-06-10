package com.example.weekend1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.time.Month;

public class Main2Activity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    SeekBar sb_for_loan;
    TextView loan;

    SeekBar sb_for_rate;
    TextView rate;

    SeekBar sb_for_month;
    TextView month;
    Button calculate;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        loan=findViewById(R.id.Loan_tv_forshow);
        sb_for_loan=findViewById(R.id.Loan_sb);


        rate=findViewById(R.id.Rate_tv_forshow);
        sb_for_rate=findViewById(R.id.Rate_sb);


        month =findViewById(R.id.Month_tv_forshow);
        sb_for_month=findViewById(R.id.Month_sb);
        sb_for_month.setOnSeekBarChangeListener(this);
        sb_for_loan.setOnSeekBarChangeListener(this);
        sb_for_rate.setOnSeekBarChangeListener(this);

        calculate=findViewById(R.id.Cal);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int P=Integer.parseInt(loan.getText().toString());
                double R=Integer.parseInt(rate.getText().toString())/100f;
                int N=Integer.parseInt(month.getText().toString());
                double re=P*R*Math.pow((1+R),N);
                re/=Math.pow((1+R),(N-1));
                result.setText(String.valueOf(re));
            }
        });
        result=findViewById(R.id.Result);



    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        // TODO Auto-generated method stub
        if (seekBar.getId()==R.id.Loan_sb)loan.setText(String.valueOf(seekBar.getProgress()));
        if (seekBar.getId()==R.id.Rate_sb)rate.setText(String.valueOf(seekBar.getProgress()));
        if (seekBar.getId()==R.id.Month_sb)month.setText(String.valueOf(seekBar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }
}
