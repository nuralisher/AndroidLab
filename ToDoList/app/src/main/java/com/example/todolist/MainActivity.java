package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("tag", "onCreateMain");

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.mainPage, new ListFragment(this)).commit();
    }

    public void add(View v){
        fragmentManager.beginTransaction().replace(R.id.mainPage, new AddFragment(this))
                .addToBackStack("add")
                .commit();
        Toast.makeText(this, "add clicked", Toast.LENGTH_LONG).show();
    }

    public void initialAdd(){
        //        for(int i=1; i<4; i++){
//            Category category = new Category();
//            category.title = "Category "+i;
//            categoryDao.insert(category);
//        }
//
//        List<Category> categories = categoryDao.getCategoryList();
//        int index = 1;
//        for(Category category: categories){
//            for(int i = 1; i<=4; i++){
//                Contact contact = new Contact();
//                contact.title = "Contact " + index;
//                contact.status = "Status"+index;
//                contact.category_id = category.id;
//                contactDao.insert(contact);
//                index++;
//            }
//        }
//        Toast.makeText(this, "add clicked", Toast.LENGTH_LONG).show();
    }
}
