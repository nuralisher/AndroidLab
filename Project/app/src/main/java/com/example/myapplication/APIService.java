package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("positions.json/")
    Call<List<Job>> getJobs();

    @GET("positions/{id}.json/")
    Call<Job> getJobsByID(@Path("id") String id);

    @GET("positions.json/")
    Call<List<Job>> searchJobs(@Query("search")String searchNode);
}
