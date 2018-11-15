package com.example.hp.cat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;

public class Main3Activity extends AppCompatActivity {

    EditText etMail,etPassword;
    Button btnLogin,btnRegister;
    TextView tvReset;
    private ProgressBar spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_t);



        spinner = (ProgressBar)findViewById(R.id.progressBar);
        etMail=findViewById(R.id.etMail);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnRegister=findViewById(R.id.btnRegister);
        tvReset=findViewById(R.id.tvReset);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etMail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty())
                {
                    Toast.makeText(Main3Activity.this,"Please enter all details.",Toast.LENGTH_SHORT).show();
                }
                else {
                    String name = etMail.getText().toString().trim();
                    String password=etPassword.getText().toString().trim();


                    Backendless.UserService.login(name, password, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            Toast.makeText(Main3Activity.this,"Logged in!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Main3Activity.this,MainActivity.class));
                            Main3Activity.this.finish();

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(Main3Activity.this,"Error: "+fault.getMessage(),Toast.LENGTH_SHORT).show();


                        }
                    }, true);
                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this,regT.class));


            }
        });
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMail.getText().toString().isEmpty())
                {
                    Toast.makeText(Main3Activity.this,"Please enter you email",Toast.LENGTH_SHORT ).show();
                }
                else
                {
                    String email=etMail.getText().toString().trim();

                    Backendless.UserService.restorePassword(email, new AsyncCallback<Void>() {
                        @Override
                        public void handleResponse(Void response) {
                            Toast.makeText(Main3Activity.this,"Reset instruction sent to email address",Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(Main3Activity.this,"Error:  "+fault.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });;


        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {

                if(response)
                {

                    String userObjectId= UserIdStorageFactory.instance().getStorage().get();
                    Backendless.Data.of(BackendlessUser.class).findById(userObjectId, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            startActivity(new Intent(Main3Activity.this,MainActivity.class));
                            Main3Activity.this.finish();

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(Main3Activity.this,"Error:  "+fault.getMessage(),Toast.LENGTH_SHORT).show();


                        }
                    });
                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(Main3Activity.this,"Error:  "+fault.getMessage(),Toast.LENGTH_SHORT).show();


            }
        });

    }

}
