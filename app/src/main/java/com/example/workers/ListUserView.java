package com.example.workers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ListUserView extends AppCompatActivity {
//    private ImageView hostelImage;
//    private TextView hostelNameField;
//    private TextView hostelTypeField;
//    private TextView hostelAddressField;
    private TextView profession;
    private TextView username;
    private TextView mobilenumber;
    private Button dialphonenumber;
//    private Button hostelPhoneNumberBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_view);
        profession = findViewById(R.id.profession);
        username = findViewById(R.id.userName);
        mobilenumber = findViewById(R.id.mobilenumber);
        dialphonenumber= findViewById(R.id.dialPhoneNumber);
//        hostelAddressField = findViewById(R.id.hostelAddress);
//        rentPerPersonField = findViewById(R.id.hostelRentPerPerson);
//        numberOfRoomsField = findViewById(R.id.hostelNumberOfRooms);
//        hostelBuildingTypeField = findViewById(R.id.buildingType);
//        hostelPhoneNumberBtn = findViewById(R.id.hostelPhoneNumber);
        final Intent intent = getIntent();
        profession.setText(intent.getStringExtra("profession"));
        username.setText(intent.getStringExtra("username"));
        mobilenumber.setText(intent.getStringExtra("mobilenumber"));
//        rentPerPersonField.setText(intent.getStringExtra("rentPerPerson"));
//        numberOfRoomsField.setText(intent.getStringExtra("numberOfRooms"));
//        hostelBuildingTypeField.setText(intent.getStringExtra("hostelBuildingType"));
//        hostelPhoneNumberBtn.setText(intent.getStringExtra("phoneNumber"));
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference(intent.getStringExtra("hostelImage"));
//        Glide.with(this).load(storageReference).into(hostelImage);
        dialphonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkPermission = ContextCompat.checkSelfPermission(ListUserView.this, Manifest.permission.CALL_PHONE);
                if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            ListUserView.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                }
                Intent intent1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+intent.getStringExtra("phoneNumber")));
                startActivity(intent1);
            }
        });
    }
}