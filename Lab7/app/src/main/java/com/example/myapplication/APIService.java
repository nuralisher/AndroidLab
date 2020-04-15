package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService  {

    @GET("positions.json/")
    Call<List<Jobs>> getJobs();

    @GET("positions/{id}.json/")
    Call<Jobs> getJobsByID(@Path("id") String id);

    @GET("positions.json/")
    Call<List<Jobs>> searchJobs(@Query("search")String searchNode);
}
