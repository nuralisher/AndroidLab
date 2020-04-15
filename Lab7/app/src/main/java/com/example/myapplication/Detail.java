package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {

    Jobs job;
    TextView title,created_at,type,location,description;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_fragment);

        Intent i = getIntent();
        job = i.getParcelableExtra("job");

        title = findViewById(R.id.title);
        created_at = findViewById(R.id.created_at);
        location = findViewById(R.id.location);
        type = findViewById(R.id.type);
        description = findViewById(R.id.description);
        logo = findViewById(R.id.img);

        title.setText(job.getTitle());
        created_at.setText(job.getCreatedAt());
        location.setText(job.getLocation());
        type.setText(job.getType());
        description.setText(job.getDescription());

        Picasso.get()
                .load(job.getCompanyLogo()).into(logo);
    }
}
