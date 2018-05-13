package com.example.gs.rentme;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUriExposedException;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gs.rentme.data_model.createaccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView name, email, gender, location , rented , products;

    ImageView profile_pic ;
    private int PICK_IMAGE = 150;

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

        profile_pic = v.findViewById(R.id.profile_pic);


        products = v.findViewById(R.id.products);

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext() , MyPoductsActivity.class));
            }
        });

        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, PICK_IMAGE);
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

            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("." , "");

            StorageReference storageRef =
                    FirebaseStorage.getInstance().getReference();
            storageRef.child("Images/"+email).getDownloadUrl()
                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // Got the download URL for 'users/me/profile.png'

                            Picasso.get().load(uri).into(profile_pic);



                        }}).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                            }
                        });


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == PICK_IMAGE) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image

                FirebaseStorage mStorage = FirebaseStorage.getInstance();

                StorageReference storageRef = mStorage.getReference();

                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("." , "");

                Uri fileUri = data.getData();

                profile_pic.setImageURI(fileUri);

                StorageReference fileToUpload = storageRef.child("Images").child(email);

                fileToUpload.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        FirebaseDatabase database = FirebaseDatabase.getInstance();


                    }
                });
            }
        }
    }
}
