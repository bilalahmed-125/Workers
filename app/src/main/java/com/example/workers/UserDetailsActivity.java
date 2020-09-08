package com.example.workers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsActivity extends AppCompatActivity {
    private FirebaseFirestore fb;
    private StorageReference sr;
    private List<UserData> dataList = new ArrayList<UserData>();
    private TextView name,email,cnic,number,profession;
    private UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        name = findViewById(R.id.nameW);
        email = findViewById(R.id.emailW);
        cnic = findViewById(R.id.cnicW);
        number = findViewById(R.id.mobilenumberW);
        profession = findViewById(R.id.professionW);
        user = new UserData();

        fb = FirebaseFirestore.getInstance();
        Query query = fb.collection("workers").whereEqualTo("email",""+user.getEmail());
        Toast.makeText(this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "E-mail Featched", Toast.LENGTH_SHORT).show();
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot mydoc :
                        task.getResult()) {

                    name.setText(mydoc.get("cnic").toString());
                    email.setText(mydoc.get("email").toString());
                    cnic.setText(mydoc.get("mobileNumber").toString());
                    number.setText(mydoc.get("profession").toString());
                    profession.setText(mydoc.get("username").toString());
                }
            }
        });
        Toast.makeText(this, "DataShow", Toast.LENGTH_SHORT).show();
////        int i=1;
//        name.setText("Bilal Ahmed");
//        email.setText("bilalahmed123@gmail.com");
//        cnic.setText("4130305003333");
//        number.setText("03225478954");
//        profession.setText("Electrician");
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}