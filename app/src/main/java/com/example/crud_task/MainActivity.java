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
        recyclerView.setAdapter(new MyAdapter(MainActivity.this,getTask()));
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
    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder>{
        Context context;
        ArrayList<Task> arrayList=new ArrayList<>();

        public MyAdapter(Context context,ArrayList<Task> arrayList) {
           this.context=context;
          this.arrayList=arrayList;
        }

        @NonNull
        @Override
        public MyAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.list,parent,false);
            return new myViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull final MyAdapter.myViewHolder holder, final int position1) {
            name=arrayList.get(position1).getName();
            date=arrayList.get(position1).getDate();
            developer=arrayList.get(position1).getDeveloper();
            position=arrayList.get(position1).getPosition();
            status=arrayList.get(position1).getStatus();
            id=arrayList.get(position1).getId();

            holder.textView.setText(name);
            holder.textView1.setText(date);
            holder.textView2.setText(developer);
            holder.textView3.setText(position);
            holder.textView4.setText(status);
            if(status=="PENDING"){
                holder.textView4.setTextColor(Color.parseColor("#C40E17"));
                holder.textView4.setTypeface(holder.textView4.getTypeface(), Typeface.BOLD);
            }
            else{
                holder.textView4.setTextColor(Color.parseColor("#FFFFFF"));
                holder.textView4.setTypeface(holder.textView4.getTypeface(), Typeface.BOLD);
            }

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    name1=arrayList.get(position1).getName();
                    date1=arrayList.get(position1).getDate();
                    developer1=arrayList.get(position1).getDeveloper();
                    pos=arrayList.get(position1).getPosition();
                    status1=arrayList.get(position1).getStatus();
                    id1=arrayList.get(position1).getId();
                    Intent intent=new Intent(MainActivity.this,Update.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class myViewHolder extends RecyclerView.ViewHolder  {
            TextView textView,textView1,textView2,textView3,textView4;
            Button button;
            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                textView=itemView.findViewById(R.id.task_name_add1);
                textView1=itemView.findViewById(R.id.task_name_date1);
                textView2=itemView.findViewById(R.id.task_name_developer1);
                textView3=itemView.findViewById(R.id.task_name_position1);
                textView4=itemView.findViewById(R.id.task_status);
                button=itemView.findViewById(R.id.btn_UpdateTask);

            }
        }
    }
}