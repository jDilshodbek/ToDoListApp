package com.example.todolistapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todolistapp.dao.CategoryDao;
import com.example.todolistapp.dao.TaskDao;
import com.example.todolistapp.db.ToDoDatabase;
import com.example.todolistapp.model.Category;
import com.example.todolistapp.model.Task;

import java.util.List;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Application application) {
        ToDoDatabase toDoDatabase = ToDoDatabase.getInstance(application);
        taskDao = toDoDatabase.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public void insertTask(Task task) {
        new InsertTaskAsyncTask(taskDao).execute(task);
    }

    public void updateTAsk(Task task) {
        new UpdateTaskAsyncTask(taskDao).execute(task);
    }


    public void deleteTask(Task task) {
        new DeleteTaskAsyncTask(taskDao).execute(task);
    }


    public void deleteAllTasks() {
        new DeleteAllTasksAsyncTask(taskDao).execute();
    }


    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }


    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        private InsertTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        private UpdateTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        private DeleteTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }

    private static class DeleteAllTasksAsyncTask extends AsyncTask<Void, Void, Void> {
        private TaskDao taskDao;

        private DeleteAllTasksAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.deleteAllTasks();
            return null;
        }
    }
}
