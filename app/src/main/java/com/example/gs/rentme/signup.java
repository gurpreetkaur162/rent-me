package com.example.gs.rentme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gs.rentme.data_model.createaccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity
{
    String emails, passs, names, locations, genders, cpasss;
    EditText email, pass, name, location, cpass;
    RadioGroup gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        name=findViewById(R.id.name);
        location=findViewById(R.id.loc);
        gender=findViewById(R.id.gender);
        cpass=findViewById(R.id.cpass);
    }

    public void Signup(View view)
    {
        names = name.getText().toString();
        locations = location.getText().toString();
        genders =((RadioButton)findViewById(gender.getCheckedRadioButtonId())).getText().toString();
        emails = email.getText().toString();
        passs = pass.getText().toString();
        cpasss = cpass.getText().toString();

        if (Patterns.EMAIL_ADDRESS.matcher(emails).matches()) {

        } else {
            email.setError("Invalid Email");
            return;
        }


        if (passs.length() >= 8 && passs.length() < 33) {

        } else {
            pass.setError("password must be between 8-33 character");
            return;
        }

        if (genders.length() >= 2) {

        } else {
            Toast.makeText(signup.this, "Select Gender", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cpasss.equals(passs)) {

        } else {
            cpass.setError("Confirm Password");
            return;
        }
        if (locations.length() >= 3) {

        } else {
            Toast.makeText(signup.this, "Enter Location", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog progress_bar = new ProgressDialog(signup.this);
        progress_bar.setTitle("please wait");
        progress_bar.setMessage("Create account");
        progress_bar.show();

        final FirebaseAuth f_auth = FirebaseAuth.getInstance();

        OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progress_bar.hide();

                createaccount data = new createaccount(names, locations, genders);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                if (task.isSuccessful()) {
                    String email = f_auth.getCurrentUser().getEmail().replace(".", "");
                    database.getReference().child("users").child(email).setValue(data);
                    Toast.makeText(signup.this, "done", Toast.LENGTH_SHORT).show();

                    sendVerificationEmail();
                } else {
                    Toast.makeText(signup.this, "error try again", Toast.LENGTH_SHORT).show();
                }
            }
        };

        f_auth.createUserWithEmailAndPassword(emails, passs).addOnCompleteListener(listener);


    }

    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            // email sent

                            // after email is sent just logout the user and finish this activity
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(signup.this, slide_layout.class));
                            finish();
                        }
                        else
                        {
                            // email not sent, so display message and restart the activity or do whatever you wish to do

                            //restart this activity
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());

                        }
                    }
                });
    }
}
