package com.example.deletethisshit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.deletethisshit.databinding.ActivityMainBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private String choice;
    private ListView lv_listView;
    private TextView tv_emptyTextView;
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private TextView txtPopulation;
    private TextView txtWeather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_listView = findViewById(R.id.lv_listView);
        tv_emptyTextView = findViewById(R.id.tv_emptyTextView);

        fragmentManager = getSupportFragmentManager();


        //String choice = "";

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cities_array));
        lv_listView.setAdapter(adapter);

        lv_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                choice = parent.getItemAtPosition(position).toString();

                Intent intent = new Intent(MainActivity.this, CityInfo.class);
                intent.putExtra("city", choice);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_SHORT).show();
            }
        });

        lv_listView.setEmptyView(tv_emptyTextView);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_nav_menu, menu);

        android.view.MenuItem search = menu.findItem(R.id.nav_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("Search something!");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }



}