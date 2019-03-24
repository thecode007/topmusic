package com.topmusic.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.topmusic.R;
import com.topmusic.model.Song;

import java.util.ArrayList;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Song> songs;



   public SongListAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;

    }

    @NonNull
    @Override
    public SongListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_track, parent, false);
        return new SongListAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.textSongName.setText(songs.get(position).getName());
        holder.textArtist.setText(songs.get(position).getArtist());
        Picasso.get().load(songs.get(position).getImageUrl()).into(holder.image);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // setting the intent filter
                Intent intent= new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/results?search_query="
                        + songs.get(holder.getAdapterPosition()).getArtist() + " " +
                                songs.get(holder.getAdapterPosition()).getName()));
                intent.setPackage("com.google.android.youtube");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // sending the intent with the filter to start the app
                context.startActivity(intent);
            }
        });

    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textSongName;
        ImageView image;
        TextView textArtist;
        View view;

        MyViewHolder(View view) {
            super(view);
            this.view = view;
            textSongName = view.findViewById(R.id.text_track_name);
            image = view.findViewById(R.id.image_track);
            textArtist = view.findViewById(R.id.text_artist_name);
        }
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}

