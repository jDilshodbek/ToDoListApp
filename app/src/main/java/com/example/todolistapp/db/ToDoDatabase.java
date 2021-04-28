package com.example.todolistapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todolistapp.dao.CategoryDao;
import com.example.todolistapp.dao.TaskDao;
import com.example.todolistapp.model.Category;
import com.example.todolistapp.model.Task;

@Database(entities = {Category.class, Task.class}, version = 1)
public abstract class ToDoDatabase extends RoomDatabase {

    private static ToDoDatabase instance;

    public abstract CategoryDao categoryDao();
    public abstract TaskDao taskDao();

    public static synchronized ToDoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ToDoDatabase.class, "to_do_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

}
