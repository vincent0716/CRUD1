package com.example.crud_task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button add_button;
    ArrayList<Task> arrayList;
    RecyclerView recyclerView;
    String name,date,developer,position,status,stat,id;
    public static String name1,date1,developer1,pos,status1,stat1,id1;

    SQLHandler sqlHandler=new SQLHandler(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar1);
        TextView textView=findViewById(R.id.txt_title);
        setSupportActionBar(toolbar);
        setTitle(textView.getText());
        arrayList=new ArrayList<>();
        recyclerView=findViewById(R.id.re_tasklist);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecycleView_Adapter(MainActivity.this,getTask()));
        add_button=findViewById(R.id.btn_add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });




    }

    //get all database values
    public ArrayList<Task> getTask(){
        SQLiteDatabase database=sqlHandler.getWritableDatabase();
       // database.execSQL("Select name FROM task_management");

        Cursor rs=database.rawQuery("Select * FROM myTask",null);
        while (rs.moveToNext()){
            name=rs.getString(rs.getColumnIndex("task_name"));
            date=rs.getString(rs.getColumnIndex("task_date"));
            developer=rs.getString(rs.getColumnIndex("developer_name"));
            status=rs.getString(rs.getColumnIndex("is_finish"));
            int w=Integer.parseInt(status);
            id=rs.getString(rs.getColumnIndex("task_id"));
            position=rs.getString(rs.getColumnIndex("position_name"));
            if(w==0){
                stat="PENDING";
            }
            else{
                stat="DONE";
            }
            Task task=new Task(name,date,developer,position,stat,id);
            arrayList.add(task);
        }

        return arrayList;
    }

    //recycleview

}