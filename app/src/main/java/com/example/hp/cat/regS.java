package com.example.hp.cat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class regS extends AppCompatActivity {

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    EditText etName,etMail,etPassword,etReEnter;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_s);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        etName=findViewById(R.id.etName);
        etMail=findViewById(R.id.etMail);
        etPassword=findViewById(R.id.etPassword);
        etReEnter=findViewById(R.id.etReEnter);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(etName.getText().toString().isEmpty() || etMail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()
                            || etReEnter.getText().toString().isEmpty() )
                    {
                        Toast.makeText(regS.this,"Please enter all details!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if (etPassword.getText().toString().equals(etReEnter.getText().toString()))
                        {
                            String name=etName.getText().toString().trim();
                            String mail=etMail.getText().toString().trim();
                            String password=etPassword.getText().toString().trim();
                            BackendlessUser user= new BackendlessUser();
                            ((BackendlessUser) user).setEmail(mail);
                            user.setPassword(password);
                            user.setProperty("name",name);


                            Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                                @Override
                                public void handleResponse(BackendlessUser response) {

                                    Toast.makeText(regS.this,"User registered!",Toast.LENGTH_SHORT).show();
                                    regS.this.finish();

                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {

                                    Toast.makeText(regS.this,"Error" + fault.getMessage(),Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                        else
                            Toast.makeText(regS.this,"Passwords do not match!,",Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }

}
