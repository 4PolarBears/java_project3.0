package com.example.deletethisshit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CityInfo extends AppCompatActivity {
    private String city;
    private TextView txtPopulation;
    private TextView txtWeather;
    ArrayList<String> list = new ArrayList<>();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_info);
        city = getIntent().getStringExtra("city");

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewArea);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back2);
        actionBar.setDisplayHomeAsUpEnabled(true);


//        ImageButton viewUsers = (ImageButton) findViewById(R.id.button2);

        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this, city);
        viewPager.setAdapter(tabPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
//                getSupportFragmentManager().beginTransaction().replace(R.id.frameBottom, cityInfoFragment).commit();
//                bottomFragment.updateImportantText(); // Update the bottom fragment information
            }

            //            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                bottomFragment.updateImportantText();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//                bottomFragment.updateImportantText();
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                TabLayout.Tab tab = tabLayout.getTabAt(position);
                if (tab != null) {
                    tab.select();
                }
            }
        });


//        ImageButton viewUsers = (ImageButton) findViewById(R.id.button2);
//        viewUsers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CityInfo.this, MainActivity.class);
//                finish();
//                startActivity(intent);
//            }
//        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
