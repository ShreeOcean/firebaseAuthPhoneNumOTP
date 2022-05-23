package com.ocean.firebaseauthphonenumapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ocean.firebaseauthphonenumapp.databinding.ActivityHomeBinding;
import com.ocean.firebaseauthphonenumapp.util.SessionManager;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TODO: shared preference session manager object
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.setLogin();

//        sharedPreferences =getSharedPreferences("LOGINACTIVITY", Context.MODE_PRIVATE);
//        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);

        String userPhoneNum = sessionManager.getContact();
        Log.d("Phone Num : --",userPhoneNum);



        if(savedInstanceState != null){
            sessionManager.setLogin();
            Toast.makeText(HomeActivity.this, "User logged in, is" + userPhoneNum, Toast.LENGTH_SHORT).show();
        }
//        else if(savedInstanceState == null){
//            //logout();
//
////            sessionManager.isLogin();
////            startActivity(new Intent(HomeActivity.this, MainActivity.class));
////            finish();
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id  = item.getItemId();
        if (id == R.id.id_menu_logout){
            //logout(); //TODO: Firebase inbuilt sign out function calling

            //sharedPreferences.edit().putBoolean("isLogin", false).apply();

            sessionManager.isLogin();
            sessionManager.logoutUser();
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();

        }else if (id == R.id.id_menu_about){
            //TODO: menu about code here
            Toast.makeText(this, "Coding to be done...", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        //TODO: Firebase inbuilt sign out function calling
        //FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(HomeActivity.this, MainActivity.class));
//        finish();
    }
}