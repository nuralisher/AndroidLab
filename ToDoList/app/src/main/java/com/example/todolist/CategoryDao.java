package com.example.todolist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CategoryDao {

    @Query("select * from category")
    List<Category> getCategoryList();

    @Query("select * from category where id=:id")
    Category getCategory(int id);

    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);
}
