package com.example.todolist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContactDao {
    @Query("Select * from contact")
    List<Contact> getContactList();

    @Query("Select * from contact where id = :id")
    Contact getContact(int id);

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);
}
