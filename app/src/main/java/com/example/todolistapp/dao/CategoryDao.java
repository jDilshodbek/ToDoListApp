package com.example.todolistapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolistapp.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {


    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);


    @Query("DELETE FROM category")
    void deleteAllCategories();

    @Query("SELECT * FROM category")
    LiveData<List<Category>> getAllCategories();




}
