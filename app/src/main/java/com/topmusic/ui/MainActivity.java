package com.topmusic.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.topmusic.R;
import com.topmusic.model.Country;
import com.topmusic.ui.adapters.CountryListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_flags);
        LinearLayoutManager linearVertical = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearVertical);

        // adding data
        ArrayList<Country> countries
                 = new ArrayList<>();
        countries.add(new Country("Lebanon", R.drawable.lebanon));
        countries.add(new Country("United Arab Emirates", R.drawable.uae));

        // setting the adapter
        recyclerView.setAdapter(new CountryListAdapter(this, countries));
    }


}
