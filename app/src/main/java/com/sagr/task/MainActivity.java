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
import com.sagr.task.utils.DataBaseAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private TaskRecViewAdapter adapter;
    private FloatingActionButton fab;
    private DataBaseAdapter dataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseAdapter = new DataBaseAdapter(this);

        fab = findViewById(R.id.fab);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new TaskRecViewAdapter(dataBaseAdapter);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks = dataBaseAdapter.getEntry();

        adapter.setTaskArrayList(tasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab.setOnClickListener((View v)->{
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
            final Button btnSave = bottomSheetDialog.findViewById(R.id.btnSave);
            btnSave.setText("Add");

            final EditText editTitle = bottomSheetDialog.findViewById(R.id.editTaskTitle);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!editTitle.getText().toString().equals("")){
                         ArrayList<Task> taskArrayList;

                        dataBaseAdapter.insertEntry(new Task(1, editTitle.getText().toString(),false));
                        taskArrayList = dataBaseAdapter.getEntry();

                        adapter.setTaskArrayList(taskArrayList);
                        Toast.makeText(MainActivity.this, "Success",Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.hide();
                    }
                }
            });


            bottomSheetDialog.show();

        });


    }

}