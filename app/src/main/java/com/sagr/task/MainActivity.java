package com.sagr.task;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sagr.task.adapter.TaskRecViewAdapter;
import com.sagr.task.models.Task;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private TaskRecViewAdapter adapter;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
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
        fab.setOnClickListener((View v)->{
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
            final Button btnSave = bottomSheetDialog.findViewById(R.id.btnSave);
            btnSave.setText("Add");

            final EditText editTitle = bottomSheetDialog.findViewById(R.id.editTaskTitle);
            btnSave.setOnClickListener(v1 -> {
           if(!editTitle.getText().toString().equals("")){
               Random r = new Random();
               int randomId = r.nextInt(1000);

               tasks.add(new Task(randomId, editTitle.getText().toString()));
               adapter.setTaskArrayList(tasks);
               Toast.makeText(this, "Success",Toast.LENGTH_SHORT).show();
               bottomSheetDialog.hide();
           }
           });


            bottomSheetDialog.show();

        });


    }

}