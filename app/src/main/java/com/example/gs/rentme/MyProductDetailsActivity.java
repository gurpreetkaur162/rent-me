package com.example.gs.rentme;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gs.rentme.data_model.Product_detail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyProductDetailsActivity extends AppCompatActivity {
    TextView name,des, location,price,quantity;
    String  sname,sdes, slocation,sprice,squantity;

    private ArrayList<String> url_list ;

    RadioButton available_btn , not_available_btn ;

    Gallery gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_product_details);

        available_btn = findViewById(R.id.available_btn);

        not_available_btn = findViewById(R.id.not_available_btn);

        url_list = new ArrayList<>();

        gallery = (Gallery) findViewById(R.id.gallery);

        gallery.setSpacing(10);


        name = findViewById(R.id.Name_prod);
        price = findViewById(R.id.price_prod);
        des =findViewById(R.id.prod_description);
        location = findViewById(R.id.prod_Location);
        quantity = findViewById(R.id.prod_quantity);
        name.setText(getIntent().getStringExtra("productname"));
        price.setText(getIntent().getStringExtra("productprice")+"  "+getIntent().getStringExtra("producttype"));
        des.setText(getIntent().getStringExtra("productdescription"));
        quantity.setText(getIntent().getStringExtra("productquantity"));
        location.setText(getIntent().getStringExtra("productloc"));

        if(getIntent().getStringExtra("availability").equals("yes"))
        {
            available_btn.setChecked(true);
        }

        if(getIntent().getStringExtra("availability").equals("no"))
        {
            available_btn.setChecked(true);
        }


        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view)
            {

                FirebaseAuth auth = FirebaseAuth.getInstance();

                String email = auth.getCurrentUser().getEmail().replace("." , "");

                FirebaseDatabase data = FirebaseDatabase.getInstance();

                data.getReference().child("product").child(email).child(getIntent().getStringExtra("productname")+getIntent().getStringExtra("productloc")).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        finish();
                    }
                });


            }
        });


        String[] images_url = getIntent().getStringExtra("images").split(",");

        if(images_url.length > 0)
        {
            for(int i = 0 ; i < images_url.length ; i ++)
            {
                if(!images_url[i].equals("")) {
                    url_list.add(images_url[i]);
                }
            }
        }

        else {

            url_list.add(getIntent().getStringExtra("images"));
        }

        GalleryImageAdapter galleryImageAdapter= new GalleryImageAdapter(this);
        gallery.setAdapter(galleryImageAdapter);


    }

    public void delete(View view) {


    }

    public void update_availability(View view) {

        String available = "no";

        if(available_btn.isChecked())
        {
            available = "yes";
        }

        if(not_available_btn.isChecked())
        {
            available = "no";
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail().replace(".", "");

        FirebaseDatabase data = FirebaseDatabase.getInstance();



        Product_detail dataModel = new Product_detail(getIntent().getStringExtra("productname") , getIntent().getStringExtra("productloc") ,
                getIntent().getStringExtra("productprice") , getIntent().getStringExtra("productquantity") , getIntent().getStringExtra("producttype") ,
                getIntent().getStringExtra("productdescription") , getIntent().getStringExtra("category") , available );


        dataModel.images = getIntent().getStringExtra("images");

        data.getReference().child("product").child(email).child(getIntent().getStringExtra("productname") + getIntent().getStringExtra("productloc")).setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(MyProductDetailsActivity.this , "availability updated" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public class GalleryImageAdapter extends BaseAdapter {
        private Context mContext;


        public GalleryImageAdapter(Context context) {
            mContext = context;
        }

        public int getCount() {
            return url_list.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }


        // Override this method according to your need
        public View getView(int index, View view, ViewGroup viewGroup) {
            // TODO Auto-generated method stub
            ImageView i = new ImageView(mContext);

            Picasso.get().load(url_list.get(index)).into(i);
            i.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

            i.setScaleType(ImageView.ScaleType.FIT_XY);


            return i;
        }


    }

}
