package com.topmusic.ui.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.topmusic.R;
import com.topmusic.model.Country;
import com.topmusic.ui.SongActivity;

import java.util.ArrayList;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.MyViewHolder> {
    private ArrayList<Country> countries;
    private Context context;


    // Provide a reference to the views for each data item
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textCountryName;
        ImageView imageFlag;
        View view;
        MyViewHolder(View view) {
            super(view);
            this.view = view;
            textCountryName = view.findViewById(R.id.text_country);
            imageFlag = view.findViewById(R.id.image_flag);
        }
    }

    public CountryListAdapter(Context context, ArrayList<Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public CountryListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                              int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_country, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        // - get element from your data set at this position
        // - replace the contents of the view with that element
        holder.textCountryName.setText(countries.get(position).getName());
        holder.imageFlag.setBackgroundResource(countries.get(position).getFlagResource());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SongActivity.class);
                intent.putExtra("country", countries.get(holder.getAdapterPosition()).getName());
                context.startActivity(intent);
            }
        });

    }

    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return countries.size();
    }
}