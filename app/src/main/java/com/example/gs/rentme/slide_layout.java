package com.example.gs.rentme;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class slide_layout extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myadapter = new SlideAdapter(this);
        viewPager.setAdapter(myadapter);

    }

    public void skip(View view) {

        if(FirebaseAuth.getInstance().getCurrentUser() !=null)
        {
            Intent i = new Intent(slide_layout.this, Home.class);
            startActivity(i);
            finish();
        }
        else {

            Intent i = new Intent(slide_layout.this, MainActivity.class);
            startActivity(i);
            finish();
        }

    }
}
