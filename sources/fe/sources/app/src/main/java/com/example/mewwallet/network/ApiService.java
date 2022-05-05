package com.example.mewwallet.network;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("most-popular")
    Call<String> getMostPopularTVShows(@Query("page") int page);
}

