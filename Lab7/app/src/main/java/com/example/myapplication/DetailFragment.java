package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    Jobs job;
    TextView title,created_at,type,location,description;

    public DetailFragment(Jobs job){
        this.job = job;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = view.findViewById(R.id.title);
        created_at = view.findViewById(R.id.created_at);
        location = view.findViewById(R.id.location);
        type = view.findViewById(R.id.type);
        description = view.findViewById(R.id.description);

        title.setText(job.getTitle());
        created_at.setText(job.getCreatedAt());
        location.setText(job.getLocation());
        type.setText(job.getType());
        description.setText(job.getDescription());
    }
}
