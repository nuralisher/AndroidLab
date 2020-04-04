package com.example.todolist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListFragment extends Fragment {
    List<Contact> items;

    RecyclerView recyclerView;
    Context context;
    AppDatabase db;
    ContactDao contactDao;
    CategoryDao categoryDao;
    Button add;

    public ListFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.mainPage, new AddFragment(context))
                        .addToBackStack("add")
                        .commit();
                Toast.makeText(context, "add clicked", Toast.LENGTH_LONG).show();
            }
        });

        db = MyApplication.getInstance().getDatabase();
        contactDao = db.contactDao();
        categoryDao = db.categoryDao();
        items = contactDao.getContactList();

        recyclerView = view.findViewById(R.id.recycle);
        RecycleReviewAdapter recyclerViewAdapter = new RecycleReviewAdapter(items, context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
