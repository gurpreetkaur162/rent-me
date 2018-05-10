package com.example.gs.rentme;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class startingpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startingpage);
        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(startingpage.this, slide_layout.class);
                startActivity(i);
                finish();
            }
        }, 3000);

    }
}
