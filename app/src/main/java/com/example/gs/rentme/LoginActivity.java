package com.example.gs.rentme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email_et, password_et;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_et = findViewById(R.id.email_login);
        password_et = findViewById(R.id.password_login);

    }

    public void Login_(View view) {
        email = email_et.getText().toString();
        password = password_et.getText().toString();
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + password);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + email);
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

        } else {
            email_et.setError("Invalid Email");
            return;
        }


        if (password.length() >= 8 && password.length() < 33) {

        } else {
            password_et.setError("password must be between 8-33 character");
            return;
        }
        final ProgressDialog progress_bar = new ProgressDialog(LoginActivity.this);
        progress_bar.setTitle("please wait");
        progress_bar.setMessage("Logging in");
        progress_bar.show();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    progress_bar.hide();
                    Toast.makeText(LoginActivity.this, "Welcome User", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, Home.class));
                    finish();

                } else {

                    Toast.makeText(LoginActivity.this, "invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
