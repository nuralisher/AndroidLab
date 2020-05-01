package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class Profile extends AppCompatActivity {

    TextView email, password, jobsText;
    String emailText, passwordText;
    LogOutListener logOutListener;
    RecyclerView recyclerView;
    List<Job> jobs;
    AppDatabase db;
    JobDao jobDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = MyApplication.getInstance().getDatabase();
        jobDao = db.jobDao();
        jobs = jobDao.getJobList();


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        jobsText = findViewById(R.id.jobsText);
        recyclerView = findViewById(R.id.recycler2);

        Intent intent = getIntent();
        emailText = intent.getStringExtra("email");
        passwordText = intent.getStringExtra("password");

        email.setText("Email: " + emailText);
        password.setText("Password: " + passwordText);


        if(jobs.size()>0){
            jobsText.setText("Saved Jobs List:");
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(jobs, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(recyclerViewAdapter);
        }else{
            jobsText.setText("No Saved Jobs");
        }
    }


    public void logout(View view){
        Log.e("mylog","logout1");
        logOutListener = (LogOutListener)Login.context;
        logOutListener.out();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();

    }

    public interface LogOutListener{
        void out();
    }
}
