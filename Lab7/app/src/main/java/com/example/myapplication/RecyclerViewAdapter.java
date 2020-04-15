package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Jobs> jobsList;
    Context context;

    public RecyclerViewAdapter(Context context, List<Jobs> jobsList){
        this.context = context;
        this.jobsList = jobsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent , false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vholder = (ViewHolder)holder;
        vholder.title.setText(jobsList.get(position).getTitle() );
        vholder.type.setText(jobsList.get(position).getType());
        vholder.created_at.setText(jobsList.get(position).getCreatedAt());
        vholder.id = jobsList.get(position).getId();
        Picasso.get()
                .load(jobsList.get(position).getCompanyLogo()).into(vholder.logo);
        vholder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        int position;
        String id;

        TextView title,type , created_at;
        LinearLayout item;
        ImageView logo;

        public ViewHolder(@NonNull View itemView ) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            created_at = itemView.findViewById(R.id.created_at);
            item = itemView.findViewById(R.id.item);
            logo = itemView.findViewById(R.id.img);



            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("mylog", "Clicked");
                    Intent i = new Intent(context, Detail.class);
                    i.putExtra("job", jobsList.get(position));
                    context.startActivity(i);
                }
            });
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

}
