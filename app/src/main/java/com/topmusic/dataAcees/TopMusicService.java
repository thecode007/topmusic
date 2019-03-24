package com.topmusic.dataAcees;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.topmusic.dataAcees.NetworkConfig.API_KEY;

public interface TopMusicService {

    @GET("?method=geo.gettoptracks&api_key=" + API_KEY + "&format=json")
    Call<ResponseBody> countryList(@Query("country") String country);
}