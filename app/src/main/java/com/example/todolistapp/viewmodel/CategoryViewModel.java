package com.example.todolistapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolistapp.model.Category;
import com.example.todolistapp.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel  {

    private CategoryRepository categoryRepository;
    private LiveData<List<Category>> allCategories;
    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepository =new CategoryRepository(application);
        allCategories= categoryRepository.getAllCategories();
    }


    public void insert(Category category){
        categoryRepository.insertCategory(category);
    }

    public void update(Category category){
        categoryRepository.updateCategory(category);
    }

    public void delete(Category category){
        categoryRepository.deleteCategory(category);
    }

    public void deleteAll(){
        categoryRepository.deleteAllCategories();
    }

    public LiveData<List<Category>> getAllCategories(){
        return allCategories;
    }
}
