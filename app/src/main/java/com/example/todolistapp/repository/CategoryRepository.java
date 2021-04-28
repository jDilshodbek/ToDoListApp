package com.example.todolistapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todolistapp.dao.CategoryDao;
import com.example.todolistapp.db.ToDoDatabase;
import com.example.todolistapp.model.Category;

import java.util.List;

public class CategoryRepository {
    private CategoryDao categoryDao;
    private LiveData<List<Category>> allCategories;


    public CategoryRepository(Application application){

        ToDoDatabase toDoDatabase=ToDoDatabase.getInstance(application);

        categoryDao=toDoDatabase.categoryDao();
        allCategories=categoryDao.getAllCategories();
    }


    public void insertCategory(Category category){
        new InsertCategoryAsyncTask(categoryDao).execute(category);
    }

    public void updateCategory(Category category){
        new UpdateCategoryAsyncTask(categoryDao).execute(category);
    }


    public void deleteCategory(Category category){
            new DeleteCategoryAsyncTask(categoryDao).execute(category);
    }


    public void deleteAllCategories(){
        new DeleteAllCategoriesAsyncTask(categoryDao).execute();
    }


    public LiveData<List<Category>> getAllCategories(){
        return allCategories;
    }




    private static class InsertCategoryAsyncTask extends AsyncTask<Category,Void,Void>{
        private CategoryDao categoryDao;

        private InsertCategoryAsyncTask(CategoryDao categoryDao){
            this.categoryDao=categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.insert(categories[0]);
            return null;
        }
    }

    private static class UpdateCategoryAsyncTask extends AsyncTask<Category,Void,Void>{
        private CategoryDao categoryDao;

        private UpdateCategoryAsyncTask(CategoryDao categoryDao){
            this.categoryDao=categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.update(categories[0]);
            return null;
        }
    }

    private static class DeleteCategoryAsyncTask extends AsyncTask<Category,Void,Void>{
        private CategoryDao categoryDao;

        private DeleteCategoryAsyncTask(CategoryDao categoryDao){
            this.categoryDao=categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.delete(categories[0]);
            return null;
        }
    }

    private static class DeleteAllCategoriesAsyncTask extends AsyncTask<Void,Void,Void>{
        private CategoryDao categoryDao;

        private DeleteAllCategoriesAsyncTask(CategoryDao categoryDao){
            this.categoryDao=categoryDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            categoryDao.deleteAllCategories();
            return null;
        }
    }

}
