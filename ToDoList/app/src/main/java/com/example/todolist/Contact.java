package com.example.todolist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(defaultValue = "Title")
    public String title;
    @ColumnInfo(defaultValue = "This is description of contact")
    public String description;
    @ColumnInfo(defaultValue = "Status")
    public String status;
    @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id")
    public int category_id;
    @ColumnInfo(defaultValue = "Duration of Contacdt")
    public String duration;
}
