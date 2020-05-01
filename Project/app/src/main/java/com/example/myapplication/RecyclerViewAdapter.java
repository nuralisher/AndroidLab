package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Job> jobs;
    Context context;

    public RecyclerViewAdapter(List<Job> jobs, Context context){
        this.jobs = jobs;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent , false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.title.setText(jobs.get(position).getTitle());
        viewHolder.type.setText(jobs.get(position).getType());
        viewHolder.created_at.setText(jobs.get(position).getCreatedAt());
        Picasso.get().load(jobs.get(position).getCompanyLogo()).into(viewHolder.logo);
        viewHolder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        int position;

        TextView title, type, created_at;
        LinearLayout item;
        ImageView logo;

        public ViewHolder(@NonNull View itemView ) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            created_at = itemView.findViewById(R.id.created_at);
            item = itemView.findViewById(R.id.item);
            logo = itemView.findViewById(R.id.logo);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, JobDetail.class);
                    intent.putExtra("job", jobs.get(position));
                    context.startActivity(intent);
                }
            });
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}

