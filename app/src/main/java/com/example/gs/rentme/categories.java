package com.example.gs.rentme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }


    public void home_decor(View view) {

        Intent i = new Intent(categories.this , ViewCategoryProductActivity.class);
        i.putExtra("category" , "Home Decor");

        startActivity(i);

    }

    public void art(View view) {

        Intent i = new Intent(categories.this , ViewCategoryProductActivity.class);
        i.putExtra("category" , "Art");

        startActivity(i);

    }

    public void toys(View view) {

        Intent i = new Intent(categories.this , ViewCategoryProductActivity.class);
        i.putExtra("category" , "Toys");

        startActivity(i);

    }


    public void bags(View view) {

        Intent i = new Intent(categories.this , ViewCategoryProductActivity.class);
        i.putExtra("category" , "Bags");

        startActivity(i);


    }

    public void study(View view) {

        Intent i = new Intent(categories.this , ViewCategoryProductActivity.class);
        i.putExtra("category" , "Study material");

        startActivity(i);


    }

    public void household(View view) {

        Intent i = new Intent(categories.this , ViewCategoryProductActivity.class);
        i.putExtra("category" , "Household equipments");

        startActivity(i);

    }

    public void electronic(View view) {

        Intent i = new Intent(categories.this , ViewCategoryProductActivity.class);
        i.putExtra("category" , "Electronic things");

        startActivity(i);

    }

    public void sports(View view) {

        Intent i = new Intent(categories.this , ViewCategoryProductActivity.class);
        i.putExtra("category" , "Sports");

        startActivity(i);
    }

    public void accessories(View view) {

        Intent i = new Intent(categories.this , ViewCategoryProductActivity.class);
        i.putExtra("category" , "Accessories");

        startActivity(i);


    }

    public void handicraft(View view) {

        Intent i = new Intent(categories.this , ViewCategoryProductActivity.class);
        i.putExtra("category" , "Handicraft");

        startActivity(i);


    }
}
