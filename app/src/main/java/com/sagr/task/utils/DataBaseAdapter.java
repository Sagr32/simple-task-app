package com.sagr.task.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.sagr.task.models.Task;

import java.util.ArrayList;

public class DataBaseAdapter {
    static DataBaseHelper databaseHelper;

    public DataBaseAdapter(Context context) {
        databaseHelper = new DataBaseHelper(context);
    }



    public long insertEntry(Task task){
        int taskStatus = task.isDone()? 1:0;
        SQLiteDatabase sqLiteDatabase =databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.COL_Title,task.getTaskTitle());
        values.put(DataBaseHelper.COL_IsDone,taskStatus);
        long id= sqLiteDatabase.insert(DataBaseHelper.TABLE_NAME,null,values);
        return  id ;

    }
    public ArrayList<Task> getEntry(){
        ArrayList<Task> taskArrayList =new ArrayList<>();
        Cursor c;
        SQLiteDatabase sqLiteDatabase =databaseHelper.getReadableDatabase();
        String[] columns = {DataBaseHelper.COL_Id,DataBaseHelper.COL_Title,DataBaseHelper.COL_IsDone,};
        c = sqLiteDatabase.query(DataBaseHelper.TABLE_NAME,columns,null,null,null,null,null);
        while (c.moveToNext()){
            Log.i("SQL",c.getString(1));
            taskArrayList.add(new Task((c.getInt(0)),c.getString(1),toBoolean(c.getInt(2))));
            Log.i("SQL",String.valueOf(c.getInt(2)));
        }
        return taskArrayList ;
    }

    public void updateTaskTitle(int id , String title){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COL_Title,title);
        SQLiteDatabase sqLiteDatabase =databaseHelper.getWritableDatabase();
       int asds =  sqLiteDatabase.update(DataBaseHelper.TABLE_NAME,cv, DataBaseHelper.COL_Id+" = ?", new String[]{String.valueOf(id)});
        Log.i("SQL",String.valueOf(asds));

    }



    public void updateTaskStatus(int id , int status){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COL_IsDone,status);
        SQLiteDatabase sqLiteDatabase =databaseHelper.getWritableDatabase();
     sqLiteDatabase.update(DataBaseHelper.TABLE_NAME,cv, DataBaseHelper.COL_Id+" = ?", new String[]{String.valueOf(id)});
    }

    private boolean toBoolean(int i){
        return i==0 ?  false:true;
    }

    public static class DataBaseHelper extends SQLiteOpenHelper{

        public DataBaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }



        private static final int DATABASE_VERSION=1;
        private static final String DATABASE_NAME="TASKS_Database.db";
        private static final String TABLE_NAME="task_table";
        private static final String COL_Id ="_id";
        private static final String COL_Title="title";
        private static final String COL_IsDone="isDone";
        private static final String CREATE_MY_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME +" ("+COL_Id +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                COL_Title +" TEXT NOT NULL, " + COL_IsDone + " INTEGER NOT NULL  );";


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("SQL","Created");
            db.execSQL(CREATE_MY_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("SQL","Updated");
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
    }


}
