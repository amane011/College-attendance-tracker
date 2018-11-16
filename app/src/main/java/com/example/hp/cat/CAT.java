package com.example.hp.cat;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class CAT extends Application {
    public static final String APPLICATION_ID = "3F64B6D3-D5A6-7B6F-FF15-9AC86ECC2900";
    public static final String API_KEY = "F529273E-3595-A715-FF71-34C3FFDAD500";
    public static final String SERVER_URL = "https://api.backendless.com";
    public static BackendlessUser user;
    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
               APPLICATION_ID,
                API_KEY );

    }
}
