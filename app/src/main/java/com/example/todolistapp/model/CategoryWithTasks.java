package com.example.todolistapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithTasks {

    @Embedded
    private Category category;
    @Relation(parentColumn = "title",
    entityColumn = "title")
    private List<Task> tasks;

    public Category getCategory() {
        return category;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
