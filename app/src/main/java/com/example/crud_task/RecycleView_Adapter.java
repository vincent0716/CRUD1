package com.example.crud_task;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleView_Adapter extends RecyclerView.Adapter<RecycleView_Adapter.myViewHolder>{
    Context context;
    ArrayList<Task> arrayList=new ArrayList<>();
    String name,date,developer,position,status,stat,id;
    public static String name1,date1,developer1,pos,status1,stat1,id1;
    public RecycleView_Adapter(Context context, ArrayList<Task> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public RecycleView_Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.list,parent,false);
        return new myViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final RecycleView_Adapter.myViewHolder holder, final int position1) {
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
                Intent intent=new Intent(context,Update.class);
                context.startActivity(intent);
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