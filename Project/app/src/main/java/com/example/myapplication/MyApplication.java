package com.example.myapplication;

import android.app.Application;

import androidx.room.Room;

public class MyApplication extends Application {
    public static MyApplication instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "job_db")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }


    public static MyApplication getInstance(){
        return instance;
    }

    public AppDatabase getDatabase(){
        return database;
    }
}
