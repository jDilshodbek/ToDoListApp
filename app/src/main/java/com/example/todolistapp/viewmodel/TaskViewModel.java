package com.example.todolistapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolistapp.model.Category;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.repository.CategoryRepository;
import com.example.todolistapp.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        allTasks = taskRepository.getAllTasks();
    }

    public void insert(Task task) {
        taskRepository.insertTask(task);
    }

    public void update(Task task) {
        taskRepository.updateTAsk(task);
    }

    public void delete(Task task) {
        taskRepository.deleteTask(task);
    }

    public void deleteAll() {
        taskRepository.deleteAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
}
