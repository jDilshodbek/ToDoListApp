package com.example.todolistapp.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.R;
import com.example.todolistapp.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Task> taskList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

        Task task = taskList.get(position);
        holder.titleTxt.setText(task.getTitle());
        holder.descriptionTxt.setText(task.getDescription());
        holder.categoryTxt.setText(task.getCategoryName());


        Log.i("asdjsdv",""+task.toString());

        if (task.getCompleted()) {
            holder.isCompletedImg.setImageResource(R.drawable.ic_baseline_check_circle_24);
        } else {
            holder.isCompletedImg.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
        }


    }

    public void setTasks(List<Task> tasks) {
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    public Task getTaskAt(int position) {
        return taskList.get(position);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        private TextView titleTxt, descriptionTxt, categoryTxt;
        private ImageView isCompletedImg;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            descriptionTxt = itemView.findViewById(R.id.descriptionTxt);
            categoryTxt = itemView.findViewById(R.id.categoryTxt);
            isCompletedImg = itemView.findViewById(R.id.completedCheckBox);



            itemView.setOnClickListener(v -> {

                int position = getAdapterPosition();
                if(onItemClickListener!=null && position!=RecyclerView.NO_POSITION){
                    onItemClickListener.onItemClick(taskList.get(position));
                }


            });
        }


    }

    public interface OnItemClickListener {
        void onItemClick(Task task);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
