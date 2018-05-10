package com.example.gs.rentme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gs.rentme.data_model.Product_detail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyPoductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;

    ArrayList<Product_detail> Product_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_poducts);

        Product_list = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));

        get_product_list();


    }

    private void get_product_list() {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail().replace("." , "");

        FirebaseDatabase data = FirebaseDatabase.getInstance();
        System.out.println("rrrr");
        data.getReference().child("product").child(email).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Product_list.clear();


                for (DataSnapshot data : dataSnapshot.getChildren()) {


                        Product_detail details = data.getValue(Product_detail.class);
                        System.out.println("rrrrrr");
                        Product_list.add(details);

                        Adapter adapter = new Adapter();

                        recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void back(View view) {

        finish();
    }


    public class view_holder extends RecyclerView.ViewHolder {

        TextView product_price, product_name;
        LinearLayout product_lay;


        public view_holder(View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.product_name);
            product_lay = itemView.findViewById(R.id.product_lay);
            product_price = itemView.findViewById(R.id.product_price);

        }
    }

    public class Adapter extends RecyclerView.Adapter<view_holder> {

        @Override
        public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {

            view_holder v = new view_holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_product_cell, parent, false));

            return v;
        }

        @Override
        public void onBindViewHolder(view_holder holder, int position) {


            final Product_detail data = Product_list.get(position);
            holder.product_name.setText(data.name);
            holder.product_price.setText(data.price);
            holder.product_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String productprice = data.price;
                    String productquantity = data.quantity;
                    String producttype = data.rentType;
                    String productname = data.name;
                    String productloc = data.loc;
                    String productdescription = data.description;

                    Intent i = new Intent(MyPoductsActivity.this, MyProductDetailsActivity.class);
                    i.putExtra("productname", productname);
                    i.putExtra("productprice", productprice);
                    i.putExtra("productloc", productloc);
                    i.putExtra("productquantity", productquantity);
                    i.putExtra("producttype", producttype);
                    i.putExtra("productdescription", productdescription);
                    i.putExtra("images" , data.images);
                    startActivity(i);
                }
            });


        }

        @Override
        public int getItemCount() {
            return Product_list.size();
        }
    }
}
