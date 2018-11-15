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

public class regT extends AppCompatActivity {



    EditText etName,etMail,etPassword,etReEnter;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_s);


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
                    Toast.makeText(regT.this,"Please enter all details!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (etPassword.getText().toString().equals(etReEnter.getText().toString()))
                    {
                        String name=etName.getText().toString().trim();
                        String mail=etMail.getText().toString().trim();
                        String password=etPassword.getText().toString().trim();
                        BackendlessUser teacher= new BackendlessUser();
                        ((BackendlessUser) teacher).setEmail(mail);
                        teacher.setPassword(password);
                        teacher.setProperty("name",name);


                        Backendless.UserService.register(teacher, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {

                                Toast.makeText(regT.this,"User registered!",Toast.LENGTH_SHORT).show();
                                regT.this.finish();

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {

                                Toast.makeText(regT.this,"Error" + fault.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                    else
                        Toast.makeText(regT.this,"Passwords do not match!,",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
