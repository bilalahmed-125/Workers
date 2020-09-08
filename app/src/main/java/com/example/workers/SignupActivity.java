package com.example.workers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity {

    EditText username, email, cnic, mobilenumber, profession, password, confirmpassword;
    Button signup;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        username = (EditText) findViewById(R.id.userName);
        email = (EditText) findViewById(R.id.userEmailId);
        cnic = (EditText) findViewById(R.id.cnic);
        mobilenumber = (EditText) findViewById(R.id.mobilenumber);
        profession = (EditText) findViewById(R.id.profession);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmPassword);
        signup = (Button) findViewById(R.id.signUpBtn1);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void alreadyuser(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    public void signup(View view) {
        boolean allok=true;

        final String getUsername = username.getText().toString().trim();
        final String getEmail = email.getText().toString().trim();
        final String getCnic = cnic.getText().toString().trim();
        final String getMobilenumber = mobilenumber.getText().toString().trim();
        final String getProfession = profession.getText().toString().trim();
        String getPassword = password.getText().toString().trim();
        String getConfirmpassword = confirmpassword.getText().toString().trim();

        if (username.length() == 0) {
            username.setError("Username is Required");
            allok=false;
        }
        if (email.length() == 0) {
            email.setError("Email is Required");
            allok=false;
        }
        if (cnic.length() == 0) {
            cnic.setError("CNIC is Required");
            allok=false;
        }
        if(cnic.length() > 13 || cnic.length() < 13){
            cnic.setError("Number must be 13 (No dash'-')");
            allok=false;
        }
        if (mobilenumber.length() == 0) {
            mobilenumber.setError("MobileNumber is Required");
            allok=false;
        }
        if(mobilenumber.length() > 11 || mobilenumber.length() < 11){
            mobilenumber.setError("Number must be 11 (No dash'-')");
            allok=false;
        }
        if (profession.length() == 0) {
            profession.setError("Profession is Required");
            allok=false;
        }
        if (password.length() == 0) {
            password.setError("Password is Required");
            allok=false;
        }
        if (confirmpassword.length() == 0) {
            confirmpassword.setError("ConfirmPassword is Required");
            allok=false;
        }
        if (password.length() < 6) {
            password.setError("Password must be greater or equal to 6 characters");
            allok=false;
        }
        if (!getConfirmpassword.equals(getPassword)) {
            confirmpassword.setError("Both password must be same");
            allok=false;
        }
        if(allok){
            progressBar.setVisibility(View.VISIBLE);
        }


        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), UserDetailsActivity.class));
            finish();
           }

        if(allok) {
            // Now register the user in firebase database
            fAuth.createUserWithEmailAndPassword(getEmail, getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //Toast.makeText(SignupActivity.this, "User  Created.", Toast.LENGTH_SHORT).show();
                        UserData userData = new UserData();
                        userData.setEmail(getEmail);
                        userData.setUsername(getUsername);
                        userData.setCnic(getCnic);
                        userData.setMobileNumber(getMobilenumber);
                        userData.setProfession(getProfession);

                        db.collection("workers").add(userData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(SignupActivity.this,"User Registered Successfully",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), UserDetailsActivity.class));

                            }
                        });


                    } else {
                        Toast.makeText(SignupActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }


        }
    }

