package com.sagr.task;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sagr.task.adapter.TaskRecViewAdapter;
import com.sagr.task.models.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private TaskRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new TaskRecViewAdapter();
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "First task"));
        tasks.add(new Task(2, "Another task"));
        tasks.add(new Task(3, "Another task"));
        tasks.add(new Task(4, "Another task"));
        adapter.setTaskArrayList(tasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

}