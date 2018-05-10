package com.example.gs.rentme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {

        Intent i = new Intent(MainActivity.this , LoginActivity.class);
        startActivity(i);
    }

    public void signup(View view) {
        Intent i = new Intent(MainActivity.this , signup.class);

        startActivity(i);
    }
}

