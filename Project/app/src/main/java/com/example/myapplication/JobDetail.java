package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class JobDetail extends AppCompatActivity {
    Job job;
    TextView title, created_at, type, location, description;
    ImageView logo;
    AppDatabase db;
    JobDao jobDao;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        db = MyApplication.getInstance().getDatabase();
        jobDao = db.jobDao();

        Intent i = getIntent();
        job = i.getParcelableExtra("job");

        title = findViewById(R.id.title);
        created_at = findViewById(R.id.created_at);
        type = findViewById(R.id.type);
        location = findViewById(R.id.location);
        description = findViewById(R.id.description);
        logo = findViewById(R.id.logo);
        button = findViewById(R.id.btn);

        title.setText(job.getTitle());
        created_at.setText(job.getCreatedAt());
        location.setText(job.getLocation());
        description.setText(job.getDescription().substring(3, 1000));

        Picasso.get()
                .load(job.getCompanyLogo()).into(this.logo);


        setTextToButton();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setTextToButton();
    }

    public void add(View view){


        if(!isExist()){
            jobDao.insert(job);
            Toast.makeText(this,"Job was added", Toast.LENGTH_LONG).show();
            button.setText("Remove");
        }else{
            jobDao.delete(job);
            Toast.makeText(this,"Job was deleted", Toast.LENGTH_LONG).show();
            button.setText("Add me");
        }
    }

    public Boolean isExist(){
        List<Job> jobs = jobDao.getJobList();

        boolean isExist = false;


        for(int i = 0; i<jobs.size(); i++){
            if(jobs.get(i).getTitle().equals(job.getTitle())){
                isExist = true;
                Log.e("mylog","isexist method true");
            }
        }
        return isExist;
    }

    public void setTextToButton(){
        if(isExist()){
            button.setText("Remove");
        }else{
            button.setText("Add me");
        }
    }
}
