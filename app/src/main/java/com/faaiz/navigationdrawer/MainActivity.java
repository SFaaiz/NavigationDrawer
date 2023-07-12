package com.faaiz.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.faaiz.navigationdrawer.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Toolbar toolbar;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolBar);
        frameLayout = findViewById(R.id.container);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerLayout, toolbar,
                R.string.openDrawer, R.string.closeDrawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        loadFragment(new HomeFragment());

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.home){
                    loadFragment(new HomeFragment());
                }else if(id==R.id.about){
                    loadFragment(new AboutFragment());
                }else{
                    loadFragment(new SettingFragment());
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }else super.onBackPressed();
    }

    boolean add = true;
    public void loadFragment(Fragment f){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(add){
            ft.add(R.id.container, f);
            add=false;
        }else ft.replace(R.id.container, f);
        ft.commit();

    }
}

// project modified