package com.topmusic.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.topmusic.R;
import com.topmusic.dataAcees.TopMusicService;
import com.topmusic.dataAcees.RetrofitSingleton;
import com.topmusic.model.Song;
import com.topmusic.ui.adapters.SongListAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class SongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        Toolbar toolbar = findViewById(R.id.toolbar);
        final ProgressBar progress = findViewById(R.id.progress);
        final RecyclerView recyclerView = findViewById(R.id.recycler_tracks);
        setSupportActionBar(toolbar);


        TopMusicService topByCountryService = RetrofitSingleton.getInstance()
                .create(TopMusicService.class);

        LinearLayoutManager linearVertical = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearVertical);

        String countryName = getIntent().getExtras().getString("country");

        topByCountryService.countryList(countryName).enqueue(new Callback<ResponseBody>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {

                    assert response.body() != null;
                    // going down the data hierarchy
                    JSONObject tracksObject = new JSONObject(response.body().string());
                    JSONObject tracks = tracksObject.getJSONObject("tracks");
                    JSONArray tracksArray = tracks.getJSONArray("track");
                    ArrayList<Song> songs = new ArrayList<>();

                    // filling the data array list
                    for (int i = 0; i < tracksArray.length(); i++) {
                        String name = tracksArray.getJSONObject(i).getString("name");
                        String artist = tracksArray.getJSONObject(i).getJSONObject("artist").getString("name");
                        String imageUrl = tracksArray.getJSONObject(i).
                                getJSONArray("image").
                                getJSONObject(2).getString("#text");
                        songs.add(new Song(name, artist, imageUrl));
                    }
                    // attaching the data source to the adapter
                    SongListAdapter adapter = new SongListAdapter(getApplicationContext(), songs);
                    // attaching the adapter to the recycler
                    recyclerView.setAdapter(adapter);
                    progress.setVisibility(View.GONE);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Failed to pull the results", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed to pull the results", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
