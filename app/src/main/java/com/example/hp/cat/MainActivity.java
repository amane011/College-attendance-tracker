package com.example.hp.cat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

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
       final String name=getIntent().getStringExtra("name");



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
                    btSubmit.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v) {
                            String numbers = "0123456789";
                            category c1=new category();

                            Random rndm_method = new Random();
                            char[] otp = new char[7];
                            for (int i = 0; i < 7; i++) {
                                otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
                            }
                                c1.setOtp(otp.toString());
                                c1.setEmail(CAT.user.getEmail());
                                c1.setName(name);
                               Backendless.Persistence.save(c1, new AsyncCallback<category>() {
                                   @Override
                                   public void handleResponse(category response) {
                                       Toast.makeText(MainActivity.this,"OTP generated",Toast.LENGTH_SHORT).show();
                                   }

                                   @Override
                                   public void handleFault(BackendlessFault fault) {

                                   }
                               });


                            tvDisplay.setText(otp, 0, 7);
                            btSubmit.setVisibility(View.GONE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    tvDisplay.setText("");
                                    btSubmit.setVisibility(View.VISIBLE);

                                }
                            }, 10000);
                            for (int i = 0; i < 7; i++)
                            {
                                otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
                            }
                                Toast.makeText(MainActivity.this,"OTP expired",Toast.LENGTH_SHORT).show();
                                c1.setOtp(otp.toString());
                                c1.setEmail(CAT.user.getEmail());
                                c1.setName(name);
                                Backendless.Persistence.save(c1, new AsyncCallback<category>() {
                                    @Override
                                    public void handleResponse(category response) {

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {

                                    }
                                });

                        }
                    });

                }
                else
                {
                    btsubmit1.setVisibility(View.VISIBLE);
                    codeview.setVisibility(View.VISIBLE);
                    final String otp=codeview.getText().toString();
                    DataQueryBuilder queryBuilder=DataQueryBuilder.create();
                    queryBuilder.setWhereClause("category1 = teacher" );
                     queryBuilder = queryBuilder.setPageSize(10);
                    Backendless.Persistence.of(category.class).find(queryBuilder, new AsyncCallback<List<category>>() {
                        @Override
                        public void handleResponse(List<category> response) {
                            boolean attrndance_marked=false;
                            for (int i=0;i<10;i++) {
                                if (response.get(0).getOtp() == otp) {
                                    Toast.makeText(MainActivity.this, "Attendace marked", Toast.LENGTH_SHORT).show();
                                    attrndance_marked=true;
                                }
                            }
                            if(attrndance_marked==false)
                                Toast.makeText(MainActivity.this,"Wrong otp",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                        }
                    });


                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });


        }




}
