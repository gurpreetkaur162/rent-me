package com.example.gs.rentme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gs.rentme.data_model.Product_detail;
import com.example.gs.rentme.data_model.createaccount;
import com.example.gs.rentme.ui.activities.ChatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Product_details extends AppCompatActivity {
TextView name,des, location,price,quantity;
  String  sname,sdes, slocation,sprice,squantity;

    private ArrayList<String> url_list ;

    Gallery gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

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
        Button book = findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChatActivity.startActivity(Product_details.this,
                        getIntent().getStringExtra("email"),
                        getIntent().getStringExtra("email"),
                        getIntent().getStringExtra("email"));
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

        GalleryImageAdapter galleryImageAdapter= new GalleryImageAdapter(Product_details.this);
        gallery.setAdapter(galleryImageAdapter);


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
