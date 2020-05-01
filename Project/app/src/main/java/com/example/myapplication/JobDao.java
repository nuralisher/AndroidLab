package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface JobDao {

    @Query("Select * from job")
    List<Job> getJobList();

    @Query("select * from job where id = :id")
    Job getJob(int id);

    @Insert
    void insert(Job job);

    @Update
    void update(Job job);

    @Delete
    void delete(Job job);

}
