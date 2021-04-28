package com.example.todolistapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolistapp.model.Category;
import com.example.todolistapp.model.Task;

import java.util.List;
@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);


    @Query("DELETE FROM task")
    void deleteAllTasks();

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllTasks();
}
