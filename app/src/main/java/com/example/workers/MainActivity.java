package com.example.workers;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginopen(View v) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    public void electricianopen(View v) {
        startActivity(new Intent(getApplicationContext(), ElectricianActivity.class));
    }

    public void masonopen(View v) {
        startActivity(new Intent(getApplicationContext(), MasonActivity.class));
    }

    public void plumberopen(View v) {
        startActivity(new Intent(getApplicationContext(), PlumberActivity.class));
    }

    public void painteropen(View v) {
        startActivity(new Intent(getApplicationContext(), PainterActivity.class));
    }

    public void carpenteropen(View v) {
        startActivity(new Intent(getApplicationContext(), CarpenterActivity.class));
    }

    public void onBackPressed() {
        finish();
    }
}

//        drawerlayout = (DrawerLayout) findViewById(R.id.drawer);
//        toggle = new ActionBarDrawerToggle(this, drawerlayout, R.string.Open, R.string.Close);
//        drawerlayout.addDrawerListener(toggle);
//        toggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if(toggle.onOptionsItemSelected(item)){
//            Toast.makeText(this, "Toggle Click", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//        int id=menuItem.getItemId();
//        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
//        switch (menuItem.getItemId()){
//            case R.id.db:
//                Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.el:
//                Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.cw:
//                Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                break;
//        }
//
//        if(id==R.id.db){
//            Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//        }
//
//        if(id==R.id.el){
//            Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//        }
//
//        if(id==R.id.pl){
//            Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//        }
//
//        if(id==R.id.cw){
//            Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//        }
//
//        if(id==R.id.li){
//            // If savedinstnacestate is null then replace login fragment
////            if (savedInstanceState == null) {
//                fragmentManager
//                        .beginTransaction()
//                        .replace(R.id.frameContainer, new LoginFragment(),
//                                Login_Fragment).commit();
//
//        }
//
//        if(id==R.id.su){
//
//            // If savedinstnacestate is null then replace login fragment
////            if (savedInstanceState == null) {
//            fragmentManager
//                    .beginTransaction()
//                    .replace(R.id.frameContainer, new SignUpFragment(),
//                            SignUp_Fragment).commit();
//        }
//        drawerlayout.closeDrawer(GravityCompat.START);
//
//        return true;
//    }

