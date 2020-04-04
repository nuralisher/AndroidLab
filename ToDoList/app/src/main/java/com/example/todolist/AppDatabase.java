package com.example.todolist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class, Category.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

    public abstract CategoryDao categoryDao();
}
