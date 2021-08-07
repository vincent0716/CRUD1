package com.example.crud_task;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 5;
    private static final String DB_NAME = "taskdb";
    private static final String task_name = "task_name";
    private static final String table_name = "myTask";
    private static final String task_id = "task_id";
    private static final String  task_date= "task_date";
    private static final String  developer_name= "developer_name";
    private static final String  position_name= "position_name";
    private static final String status1="is_finish";
    private Context context;

    public SQLHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + table_name + "("
                + task_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + task_date + " DATE," + task_name + " VARCHAR(50),"+ developer_name + " VARCHAR(50),"
                + position_name + " VARCHAR(50)," + status1 + " BOOLEAN"+ ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_name);
    onCreate(sqLiteDatabase);
    }

    public void insertTask(String task,String developer,String position,boolean status,String task_date1){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(task_name,task);
        contentValues.put(developer_name,developer);
        contentValues.put(position_name,position);
        contentValues.put(task_date,task_date1);
        contentValues.put(status1,status);
        long row=sqLiteDatabase.insert(table_name,null,contentValues);
        sqLiteDatabase.close();

        if(row!=-1){
            Toast.makeText(context,"Saved",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"LUL",Toast.LENGTH_SHORT).show();
        }

    }
}
