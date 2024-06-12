package com.mobileapp.mukhtabir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mobileapp.mukhtabir.fragments.FavoriteFragment;
import com.mobileapp.mukhtabir.fragments.HomeFragment;
import com.mobileapp.mukhtabir.fragments.NotificationFragment;
import com.mobileapp.mukhtabir.fragments.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fragment> fragmentList=new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_view);
        fragmentList.add(new HomeFragment());
        fragmentList.add(new FavoriteFragment());
        fragmentList.add(new NotificationFragment());
        fragmentList.add(new SettingsFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment selectedFragment = null;
                switch (item.getItemId())
                {
                    case R.id.navigation_dashboard:
                        selectedFragment = fragmentList.get(0);
                        break;
                    case R.id.navigation_favorite:
                        selectedFragment = fragmentList.get(1);
                        break;
                    case R.id.navigation_notification:
                        selectedFragment = fragmentList.get(2);
                        break;

                    case R.id.navigation_settings:
                        selectedFragment = fragmentList.get(3);
                        break;

                }

                loadFragment(selectedFragment);
                return true;
            }
        });


        loadFragment(fragmentList.get(0));
    }

    public void loadFragment(Fragment fragment)
    {
        if (fragment != null)
        {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.container_view, fragment).commit();
        }
    }

    @SuppressLint("MissingSuperCall")
    public void onBackPressed()
    {
        if(bottomNavigationView.getSelectedItemId()==R.id.navigation_dashboard)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Exit App")
                    .setMessage("Are you sure you want to exit the app?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(R.drawable.ic_logo)
                    .show();
        }
        else
        {
            bottomNavigationView.setSelectedItemId(R.id.navigation_dashboard);
        }
    }

}