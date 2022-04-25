package com.sagr.task.adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sagr.task.R;
import com.sagr.task.models.Task;
import com.sagr.task.utils.DataBaseAdapter;

import java.util.ArrayList;

public class TaskRecViewAdapter extends RecyclerView.Adapter<TaskRecViewAdapter.TaskViewHolder> {


    private DataBaseAdapter db;
    private ArrayList<Task> taskArrayList;

    public TaskRecViewAdapter(DataBaseAdapter db) {
        this.db = db;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.task_list, null);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        holder.taskId.setText(String.valueOf(taskArrayList.get(holder.getAdapterPosition()).getId()));
        holder.taskTitle.setText(taskArrayList.get(holder.getAdapterPosition()).getTaskTitle());
        holder.parent.setOnClickListener((View v) -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(holder.itemView.getContext());
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
            final Button btnSave = bottomSheetDialog.findViewById(R.id.btnSave);


            final EditText editTitle = bottomSheetDialog.findViewById(R.id.editTaskTitle);
            btnSave.setOnClickListener(v1 -> {

                holder.taskTitle.setText(editTitle.getText());
                db.updateTaskTitle(taskArrayList.get(holder.getAdapterPosition()).getId(), editTitle.getText().toString());
                Toast.makeText(holder.itemView.getContext(), "Success", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.hide();
            });

            bottomSheetDialog.show();


        });
        holder.checkBox.setChecked(taskArrayList.get(holder.getAdapterPosition()).isDone());


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked()){
                    db.updateTaskStatus(taskArrayList.get(holder.getAdapterPosition()).getId(), 1);
                }
                else{
                    db.updateTaskStatus(taskArrayList.get(holder.getAdapterPosition()).getId(), 0);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskId, taskTitle;
        private final RelativeLayout parent;
        private final CheckBox checkBox;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskId = (TextView) itemView.findViewById(R.id.txtTaskId);
            taskTitle = (TextView) itemView.findViewById(R.id.txtTaskTitle);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            parent = (RelativeLayout) itemView.findViewById(R.id.parent);
        }
    }

    public void setTaskArrayList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
        notifyDataSetChanged();
    }
}
