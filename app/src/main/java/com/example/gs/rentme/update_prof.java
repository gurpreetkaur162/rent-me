package com.example.gs.rentme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gs.rentme.data_model.createaccount;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class update_prof extends AppCompatActivity {

    String  names, locations, genders, dd;
    EditText name ,location;
    RadioGroup gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prof);
        name=findViewById(R.id.name);
        gender=findViewById(R.id.gender);
        location =findViewById(R.id.location);
        /*PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                update_prof.this.getFragmentManager().findFragmentById(R.id.update_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("", "Place: " + place.getName());
                locations = place.getAddress().toString();
                dd= (String) place.getAddress().subSequence(place.getName().length(),place.getAddress().length());
                System.out.println(dd);

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("", "An error occurred: " + status);
            }
        });*/

    }

    public void update(View view) {
        names = name.getText().toString();
        locations = location.getText().toString();
        genders =((RadioButton)findViewById(gender.getCheckedRadioButtonId())).getText().toString();
        if (genders.length() >= 2) {

        } else {
            Toast.makeText(update_prof.this, "Select Gender", Toast.LENGTH_SHORT).show();
            return;
        }

        if (names.length()>=2){

        } else {
            name.setError("Confirm Password");
            return;
        }
        if (locations.length() >= 3) {

        } else {
            Toast.makeText(update_prof.this, "Enter Location", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog progress_bar = new ProgressDialog(update_prof.this);
        progress_bar.setTitle("please wait");
        progress_bar.setMessage("updating account");
        progress_bar.show();

        final FirebaseAuth f_auth = FirebaseAuth.getInstance();




                createaccount data = new createaccount(names, locations, genders);
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                    String email = f_auth.getCurrentUser().getEmail().replace(".", "");
                    database.getReference().child("users").child(email).setValue(data);
        progress_bar.hide();
                    Toast.makeText(update_prof.this, "done", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(update_prof.this, Home.class);
                    startActivity(i);
                    finish();

    }
}
