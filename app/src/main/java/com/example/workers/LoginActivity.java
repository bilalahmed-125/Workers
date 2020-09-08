package com.example.workers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button button,loginbutton;
    CheckBox show_hide_password;
    FirebaseAuth fAuth;
    UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();

        email=findViewById(R.id.login_email);
        password=findViewById(R.id.login_password);
        loginbutton=findViewById(R.id.loginBtn);
        show_hide_password =findViewById(R.id.show_hide_password);
        show_hide_password
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean allok = true;

                final String getEmail = email.getText().toString().trim();
                final String getPassword = password.getText().toString().trim();

                user = new UserData();
                user.setEmail(getEmail);

                if (email.length() == 0) {
                    email.setError("Email is Required");
                    allok = false;
                }

                if (password.length() == 0) {
                    password.setError("Password is Required");
                    allok = false;
                }
//                if (fAuth.getCurrentUser() != null) {
//                    startActivity(new Intent(getApplicationContext(), UserDetailsActivity.class));
//                    finish();
//                }
                if(allok){
                    fAuth.signInWithEmailAndPassword(getEmail, getPassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = fAuth.getCurrentUser();
                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this,UserDetailsActivity.class));
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(LoginActivity.this, "Incorrect Username and Password", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });



    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }


    public void signuphere(View view){
        startActivity(new Intent(getApplicationContext(),SignupActivity.class));
    }
    public void forgotpassword(View view){
        startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
    }

}
