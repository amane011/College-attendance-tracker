package com.example.hp.cat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class startPage extends AppCompatActivity {
    Button bt4;
    Button bt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        bt4=findViewById(R.id.bt4);
        bt5=findViewById(R.id.bt5);

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(startPage.this,loginT.class));
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(startPage.this,com.example.hp.cat.Main3Activity.class);
                startActivity(intent1);
            }
        });

    }
}
