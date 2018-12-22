package com.example.hp.cat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private DrawerLayout ndrawerLayout;
    private ActionBarDrawerToggle nToggle;


    Button btsubmit1;
    Menu menu;
    TextView codeview;
    NavigationView nv;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btsubmit1 = findViewById(R.id.btsubmit1);
        codeview = findViewById(R.id.codeview);

        btsubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final int num = Integer.valueOf(codeview.getText().toString());
                final String value=Integer.toString(num);
                String teacher="teacher";

                DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                queryBuilder.setWhereClause("category1 = '" +teacher+ "'" );
                queryBuilder = queryBuilder.setPageSize(10);

                Backendless.Persistence.of(category.class).find(queryBuilder, new AsyncCallback<List<category>>() {
                    @Override
                    public void handleResponse(List<category> response) {

                           if (response.get(0).getPin().equals(value)) {
                                Toast.makeText(Main2Activity.this, "Attendance marked", Toast.LENGTH_SHORT).show();
                               CAT.c1.setAttendance(CAT.c1.getAttendance()+1);
                                Backendless.Persistence.save(CAT.c1, new AsyncCallback<category>() {
                                    @Override
                                    public void handleResponse(category response) {
                                        Toast.makeText(Main2Activity.this, "Attendance added", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {

                                    }
                                });
                            }

                        else
                            Toast.makeText(Main2Activity.this, "Wrong otp", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                        Toast.makeText(Main2Activity.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
   return nToggle.onOptionsItemSelected(item) ||super.onOptionsItemSelected(item);
    }
}
