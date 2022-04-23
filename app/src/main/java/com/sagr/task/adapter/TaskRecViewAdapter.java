package com.sagr.task.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.sagr.task.R;
import com.sagr.task.models.Task;

import java.util.ArrayList;

public class TaskRecViewAdapter extends RecyclerView.Adapter<TaskRecViewAdapter.TaskViewHolder> {

    private ArrayList<Task> taskArrayList ;

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.task_list,null);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        holder.taskId.setText(String.valueOf(taskArrayList.get(holder.getAdapterPosition()).getId()));
        holder.taskTitle.setText(taskArrayList.get(holder.getAdapterPosition()).getTaskTitle());
        holder.checkBox.setActivated(taskArrayList.get(holder.getAdapterPosition()).isDone());


    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        private TextView taskId ,taskTitle;

        private CheckBox checkBox;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskId = itemView.findViewById(R.id.txtTaskId);
            taskTitle = itemView.findViewById(R.id.txtTaskTitle);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }

    public void setTaskArrayList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
        notifyDataSetChanged();
    }
}
