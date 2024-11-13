package com.example.bottomnavigation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentManager fragmentManager = getSupportFragmentManager();

        // define your fragments here
        final Fragment fragment1 = new FirstFragment();
        final Fragment fragment2 = new SecondFragment();
        final Fragment fragment3 = new ThirdFragment();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener(
                new NavigationBarView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment;
                        if (item.getItemId() == R.id.action_favorites){
                            fragment = fragment1;
                        }else if (item.getItemId() == R.id.action_schedules) {
                            fragment = fragment2;
                        }else if (item.getItemId() == R.id.action_music) {
                            fragment = fragment3;
                        } else{
                            fragment = fragment1;
                        }
                        fragmentManager.beginTransaction().replace(R.id.rlContainer, fragment).commit();
                        return true;
                    }
                });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_favorites);



  /*      bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;

                if (item.getItemId() == R.id.action_favorites){
                    fragment = fragment1;
                }else if (item.getItemId() == R.id.action_schedules) {
                    fragment = fragment2;
                }else if (item.getItemId() == R.id.action_music) {
                    fragment = fragment3;
                } else{

                }
                fragmentManager.beginTransaction().replace(R.id.rlContainer, fragment).commit();
*//*                switch (item.getItemId()) {
                    case R.id.action_favorites:
                        // do something here
                        return true;
                    case R.id.action_schedules:
                        // do something here
                        return true;
                    case R.id.action_music:
                        // do something here
                        return true;
                    default: return true;
                }*//*
                return true;
            };

        });
        bottomNavigationView.setSelectedItemId(R.id.action_favorites);*/
    }
}