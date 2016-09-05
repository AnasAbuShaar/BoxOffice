package com.volley.boxoffice.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.volley.boxoffice.R;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle boxOffice) {
        // TODO Auto-generated method stub
        super.onCreate(boxOffice);
        setContentView(R.layout.splash);


        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent box = new Intent("android.intent.action.MainActivity");
                    startActivity(box);
                }


            }

        };
        timer.start();


    }

    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();

    }


}