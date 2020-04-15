package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  {

    static FragmentManager fragmentManager;
    List<Jobs> jobsList;
    HttpLoggingInterceptor loggingInterceptor;
    OkHttpClient okHttpClient;
    Retrofit retrofit;
    APIService service;
    Jobs job;
    ListFragment listFragment;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jobs.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            service = retrofit.create(APIService.class);




            service.getJobs().enqueue(new Callback<List<Jobs>>() {

                @Override
                public void onResponse(Call<List<Jobs>> call, Response<List<Jobs>> response) {
                    Log.e("mylog", "RESPONSE BODY SIZE "+response.body().size());
                    jobsList= response.body();
                    listFragment = new ListFragment(jobsList);
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().add(R.id.mainPage, listFragment).commit();

                }

                @Override
                public void onFailure(Call<List<Jobs>> call, Throwable t) {
                    Log.e("mylog", "error "+t.getMessage());
                }
            });

        }catch (Exception e) {
            e.printStackTrace();
        }

        searchView = (SearchView)findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("mylog", "On Query Updated");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("mylog", "On Query Changed");

                retrofit = new Retrofit.Builder()
                        .baseUrl("https://jobs.github.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();

                service = retrofit.create(APIService.class);

                service.searchJobs(newText).enqueue(new Callback<List<Jobs>>() {
                    @Override
                    public void onResponse(Call<List<Jobs>> call, Response<List<Jobs>> response) {
                        Log.e("mylog", "RESPONSE SEARCH BODY SIZE "+response.body().size());
                        jobsList = response.body();

                    }

                    @Override
                    public void onFailure(Call<List<Jobs>> call, Throwable t) {

                    }
                });


                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.mainPage, new ListFragment(jobsList))
                        .commit();
                return false;
            }
        });

    }

//    @Override
//    public void goToDetail(String id) {
//        Log.e("mylog", "Main gotodetail");
//        try{
//
//            retrofit = new Retrofit.Builder()
//                    .baseUrl("https://jobs.github.com/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(okHttpClient)
//                    .build();
//
//            service = retrofit.create(APIService.class);
//            service.getJobsByID(id).enqueue(new Callback<Jobs>() {
//                @Override
//                public void onResponse(Call<Jobs> call, Response<Jobs> response) {
//                    job = response.body();
//                }
//
//                @Override
//                public void onFailure(Call<Jobs> call, Throwable t) {
//
//                }
//            });
//            Log.e("mylog", "Job Info: " + job.getTitle());
//            fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.mainPage, new DetailFragment(job))
//                    .addToBackStack("detail")
//                    .commit();
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

}
