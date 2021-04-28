package com.example.todolistapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.R;
import com.example.todolistapp.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private List<Category> categoryList=new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new CategoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        Category category=categoryList.get(position);
        holder.titleTxt.setText(category.getTitle());
        holder.descriptionTxt.setText(category.getDescription());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public void setCategories(List<Category> categories){
        this.categoryList=categories;
        notifyDataSetChanged();
    }

    public Category getCategoryAt(int position){
        return categoryList.get(position);
    }

    class CategoryHolder extends RecyclerView.ViewHolder{

        private TextView titleTxt,descriptionTxt;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            descriptionTxt=itemView.findViewById(R.id.descriptionTxt);

            itemView.setOnClickListener(v -> {

                int position=getAdapterPosition();
                if(onItemClickListener!=null && position!=RecyclerView.NO_POSITION){
                    onItemClickListener.onItemClick(categoryList.get(position));
                }




            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Category category);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
