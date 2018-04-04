package com.example.shakt.baked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import android.support.v7.widget.Toolbar;

public class SearchBar_activity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar_activity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        final ListView listView = findViewById(R.id.search_results);
        ArrayList<String> product_list = new ArrayList<>();
        product_list.addAll(Arrays.asList(getResources().getStringArray(R.array.product_array)));
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,product_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("value","temp");
                Intent intent = new Intent(getApplicationContext(),RecommendationActivity.class);
                intent.putExtra("search",listView.getItemAtPosition(position).toString());
                startActivity(intent);

            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search_bar,menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar_menu);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Products");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    return super.onCreateOptionsMenu(menu);
    }
}
