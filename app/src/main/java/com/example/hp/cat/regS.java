package com.example.hp.cat;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class regS extends AppCompatActivity {



    EditText etName,etMail,etPassword,etReEnter;
    Button btnRegister;
    RadioGroup rg;
    RadioButton teacher,student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_s);

        rg=findViewById(R.id.rg);
        teacher=findViewById(R.id.teacher);
        student=findViewById(R.id.student);
        etName=findViewById(R.id.etName);
        etMail=findViewById(R.id.etMail);
        etPassword=findViewById(R.id.etPassword);
        etReEnter=findViewById(R.id.etReEnter);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(etName.getText().toString().isEmpty() || etMail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()
                            || etReEnter.getText().toString().isEmpty()  )
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
                            user.setEmail(mail);
                            user.setPassword(password);
                            user.setProperty("name",name);

                            String ans;
                            if (teacher.isChecked())
                            ans="teacher";
                            else
                                ans="student";

                            category category= new category();

                            category.setEmail(mail);
                            category.setName(name);
                            category.setCategory1(ans);
                            category.setPin("0000000");
                            Backendless.Persistence.save(category, new AsyncCallback<category>() {
                                @Override
                                public void handleResponse(category response) {

                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast.makeText(regS.this,"Error: " + fault.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            });



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
                        startActivity(new Intent(regS.this,loginT.class));
                        regS.this.finish();
                    }

            }
        });

    }

}
