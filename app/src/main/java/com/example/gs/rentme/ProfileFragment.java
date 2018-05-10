package com.example.gs.rentme;


import android.content.Intent;
import android.os.Bundle;
import android.os.FileUriExposedException;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gs.rentme.data_model.createaccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView name, email, gender, location , rented , products;

    public ProfileFragment() {
            }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        name = v.findViewById(R.id.Name);
        email = v.findViewById(R.id.email_prof);
        gender = v.findViewById(R.id.gender_prof);
        location = v.findViewById(R.id.Location_prof);

        rented = v.findViewById(R.id.rented_product);

        products = v.findViewById(R.id.products);

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext() , MyPoductsActivity.class));
            }
        });


        getdata();

        Button update = v.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), update_prof.class));
            }
        });
        return v;
    }
public  void  getdata(){
    FirebaseAuth auth = FirebaseAuth.getInstance();

    final String emails = auth.getCurrentUser().getEmail().replace(".","");


    FirebaseDatabase database = FirebaseDatabase.getInstance();

    database.getReference().child("users").child(emails).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            createaccount  data = dataSnapshot.getValue(createaccount.class);

            String name_s = data.name;
            String loc_s = data.location;
            String gen_s = data.gender;
          name.setText(name_s);
           location.setText(loc_s);
            gender.setText(gen_s);
          email.setText(emails);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}
}
