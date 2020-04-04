package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    Context context;
    List<Contact> items;

    public RecycleReviewAdapter(List<Contact> items , Context context){
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent , false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vholder = (ViewHolder)holder;
        vholder.textView.setText(items.get(position).title);
        vholder.status.setText(items.get(position).status);
        vholder.category.setText("category id:"+items.get(position).category_id);
        vholder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        int position;

        LinearLayout linearLayout;
        TextView textView;
        TextView status;
        TextView category;

        public ViewHolder(@NonNull View itemView ) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.item);
            textView = itemView.findViewById(R.id.text);
            status = itemView.findViewById(R.id.status);
            category = itemView.findViewById(R.id.category);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.mainPage, new DetailFragment(items.get(position)))
                            .addToBackStack("detail").commit();
                }
            });
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

}
