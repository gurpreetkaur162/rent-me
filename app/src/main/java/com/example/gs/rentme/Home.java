package com.example.gs.rentme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.gs.rentme.ui.activities.ChatActivity;
import com.example.gs.rentme.ui.activities.UserListingActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    private TextView mTextMessage;
    FragmentManager fm = getSupportFragmentManager();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_btn:
                    FragmentTransaction ft1 = fm.beginTransaction();

                    HomeFragment home_fragment = new HomeFragment();

                    ft1.replace(R.id.main_frame, home_fragment);

                    ft1.commit();

                    return true;
                case R.id.rent_btn:

                    FragmentTransaction ft2 = fm.beginTransaction();

                    RentFragment rent_fragment = new RentFragment();

                    ft2.replace(R.id.main_frame, rent_fragment);

                    ft2.commit();
                    return true;
                case R.id.profile_btn:

                    FragmentTransaction ft = fm.beginTransaction();
                    ProfileFragment profileFragment = new ProfileFragment();
                    ft.replace(R.id.main_frame, profileFragment);
                    ft.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDrawerLayout = findViewById(R.id.drawer);
        FragmentTransaction ft = fm.beginTransaction();
        HomeFragment home_fragment = new HomeFragment();
        TextView emaill = findViewById(R.id.email_idd);
        FirebaseAuth f = FirebaseAuth.getInstance();
        emaill.setText(f.getCurrentUser().getEmail());
        ft.replace(R.id.main_frame, home_fragment);

        ft.commit();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void drawer(View view) {

        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    public void user_profile(View view) {
        FragmentTransaction ft = fm.beginTransaction();
        ProfileFragment profileFragment = new ProfileFragment();
        ft.replace(R.id.main_frame, profileFragment);
        ft.commit();
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    public void home(View view) {
        FragmentTransaction ft1 = fm.beginTransaction();

        HomeFragment home_fragment = new HomeFragment();

        ft1.replace(R.id.main_frame, home_fragment);

        ft1.commit();
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    public void chat(View view) {
        startActivity(new Intent(this, UserListingActivity.class));
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    public void logout(View view) {
        FirebaseAuth f_auth = FirebaseAuth.getInstance();
        f_auth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }


    public void Categories(View view) {

        startActivity(new Intent(this, categories.class));
        mDrawerLayout.closeDrawer(Gravity.LEFT);

    }

    public void search(View view) {

        startActivity(new Intent(this, SearchActivity.class));
        mDrawerLayout.closeDrawer(Gravity.LEFT);

    }
}
