package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    List<Job> jobs;
    HttpLoggingInterceptor httpLoggingInterceptor;
    OkHttpClient okHttpClient;
    Retrofit retrofit;
    APIService apiService;
    SearchView searchView;
    String email, password;
    Button profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = (SearchView)findViewById(R.id.search);
        profile = (Button)findViewById(R.id.profile);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");

        profile.setText(email);

        try{
            httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(60, TimeUnit.SECONDS).build();

            retrofit = new Retrofit.Builder().baseUrl("https://jobs.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient).build();

            apiService = retrofit.create(APIService.class);

            apiService.getJobs().enqueue(new Callback<List<Job>>() {
                @Override
                public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                    Log.e("mylog","resonse body: " + response.body().size());
                    jobs = response.body();
                    fragmentManager = getSupportFragmentManager();
                    if(jobs.size()>0){
                        fragmentManager.beginTransaction().add(R.id.mainPage, new ListFragment(jobs)).commit();
                    }else{
                        Log.e("mylog","no response");
                    }
                }

                @Override
                public void onFailure(Call<List<Job>> call, Throwable t) {
                    Log.e("mylog", "error "+t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                apiService.searchJobs(query).enqueue(new Callback<List<Job>>() {
                    @Override
                    public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                        Log.e("mylog","search return size: " + response.body().size());
                        jobs = response.body();
                        fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.mainPage, new ListFragment(jobs))
                                .addToBackStack("search")
                                .commit();
                    }

                    @Override
                    public void onFailure(Call<List<Job>> call, Throwable t) {

                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }



    public void toProfile(View view){
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
    }

}
