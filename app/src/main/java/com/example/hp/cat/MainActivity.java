package com.example.hp.cat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Handler;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btSubmit,btsubmit1;
    TextView tvDisplay,codeview,wait;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSubmit=findViewById(R.id.btSubmit);
        btsubmit1=findViewById(R.id.btsubmit1);
        codeview=findViewById(R.id.codeview);
        tvDisplay=findViewById(R.id.tvDisplay);
        wait=findViewById(R.id.wait);



        DataQueryBuilder queryBuilder=DataQueryBuilder.create();
        queryBuilder.setWhereClause("email ='" + CAT.user.getEmail()+"'");
        DataQueryBuilder queryBuilder1 = queryBuilder.setPageSize(1);

        Backendless.Persistence.of(category.class).find(queryBuilder, new AsyncCallback<List<category>>() {
            @Override
            public void handleResponse(List<category> response) {
                wait.setVisibility(View.GONE);
                if (response.get(0).getCategory1().equals("teacher"))
                {
                    tvDisplay.setVisibility(View.VISIBLE);
                    btSubmit.setVisibility(View.VISIBLE);
                    btSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String numbers = "0123456789";

                            Random rndm_method = new Random();
                            char[] otp = new char[7];
                            for (int i = 0; i < 7; i++) {
                                otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
                            }
                            tvDisplay.setText(otp, 0, 7);
                            btSubmit.setVisibility(View.GONE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    tvDisplay.setText("");
                                    btSubmit.setVisibility(View.VISIBLE);

                                }
                            }, 7000);
                            for (int i = 0; i < 7; i++) {
                                otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
                            }
                        }
                    });

                }
                else
                {
                    btsubmit1.setVisibility(View.VISIBLE);
                    codeview.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });


        }




}
