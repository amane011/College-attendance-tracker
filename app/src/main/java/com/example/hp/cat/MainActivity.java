package com.example.hp.cat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btSubmit;
    Button bt1;
    Button bt2;
    TextView q1;
    TextView tvDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSubmit=findViewById(R.id.btSubmit);
        bt1=findViewById(R.id.bt1);
        bt2=findViewById(R.id.bt2);
        q1=findViewById(R.id.q1);
        tvDisplay=findViewById(R.id.tvDisplay);
        bt1.setVisibility(View.GONE);
        bt2.setVisibility(View.GONE);
        q1.setVisibility(View.GONE);

        btSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String numbers = "0123456789";

                Random rndm_method = new Random();
                char[] otp = new char[7];
                for (int i = 0; i < 7; i++)
                {
                    otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
                }
                tvDisplay.setText(otp,0,7);
                btSubmit.setVisibility(View.GONE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run()
                    {
                        tvDisplay.setVisibility(View.GONE);
                        bt1.setVisibility(View.VISIBLE);
                        bt2.setVisibility(View.VISIBLE);
                        q1.setVisibility(View.VISIBLE);
                    }
                }, 7000);
                for (int i = 0; i < 7; i++)
                {
                    otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
                }
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numbers = "0123456789";

                Random rndm_method = new Random();
                char[] otp = new char[7];
                for (int i = 0; i < 7; i++)
                {
                    otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
                }
                tvDisplay.setText(otp,0,7);
                tvDisplay.setVisibility(View.VISIBLE);
                bt1.setVisibility(View.GONE);
                bt2.setVisibility(View.GONE);
                q1.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tvDisplay.setVisibility(View.GONE);
                        bt1.setVisibility(View.VISIBLE);
                        bt2.setVisibility(View.VISIBLE);
                        q1.setVisibility(View.VISIBLE);
                    }
                }, 7 * 1000);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setVisibility(View.VISIBLE);
                String a="Thank you for using our app! :)";
                tvDisplay.setText(a);
                bt1.setVisibility(View.GONE);
                bt2.setVisibility(View.GONE);
                q1.setVisibility(View.GONE);
            }
        });
    }
}
