package com.example.workers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class PlumberActivity extends AppCompatActivity {
    private ListView showUsersData;
    private FirebaseFirestore fb;
    private StorageReference sr;
    private List<UserData> dataList = new ArrayList<UserData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber);
        showUsersData = findViewById(R.id.electricianList);
        fb = FirebaseFirestore.getInstance();
        Query query = fb.collection("workers").whereEqualTo("profession","Plumber");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot mydoc :
                        task.getResult()) {
                    dataList.add(mydoc.toObject(UserData.class));
                }
                MyAdapter myAdapter = new MyAdapter(getApplicationContext(),dataList);
                myAdapter.notifyDataSetChanged();
                showUsersData.setAdapter(myAdapter);
//                showUsersData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Toast.makeText(ElectricianActivity.this, "New Intent Working", Toast.LENGTH_SHORT).show();
//
//                        Intent intent = new Intent(ElectricianActivity.this,ListUserView.class);
//                        intent.putExtra("profession",dataList.get(i).getProfession());
//                        intent.putExtra("username",dataList.get(i).getUsername());
//                        intent.putExtra("mobilenumber",dataList.get(i).getMobileNumber());
////                        intent.putExtra("rentPerPerson",dataList.get(i).getRentPerPerson());
////                        intent.putExtra("numberOfRooms",dataList.get(i).getNumberOfRooms());
////                        intent.putExtra("hostelType",dataList.get(i).getHostelType());
////                        intent.putExtra("phoneNumber",dataList.get(i).getmo());
////                        intent.putExtra("hostelBuildingType",dataList.get(i).getHostelBuildingType());
//                        startActivity(intent);
//                    }
//                });
            }
        });
    }
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
    class MyAdapter extends ArrayAdapter<UserData> {
        Context context;
        List<UserData> myList;
        MyAdapter(Context c, List<UserData> myList){
            super(c,R.layout.list_row,myList);
            this.context = c;
            this.myList = myList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.list_row,parent,false);
            ImageView images = row.findViewById(R.id.phoneImage);
            TextView myTitle = row.findViewById(R.id.myTitle);
            TextView name = row.findViewById(R.id.nameField);
            TextView phoneNumber = row.findViewById(R.id.phoneNumber);

            final UserData myHostel = getItem(position);
            myTitle.setText(myHostel.getProfession());
            name.setText(myHostel.getUsername());
            phoneNumber.setText(myHostel.getMobileNumber());
            images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Phone Ringing", Toast.LENGTH_SHORT).show();
                    int checkPermission = ContextCompat.checkSelfPermission(PlumberActivity.this, Manifest.permission.CALL_PHONE);
                    if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(
                                PlumberActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                1);
                    }
                    Intent intent1 = new Intent(Intent.ACTION_CALL);
                    intent1.setData(Uri.parse("tel:"+myHostel.getMobileNumber()));
                    startActivity(intent1);
                }
            });
            return row;
        }
    }
}