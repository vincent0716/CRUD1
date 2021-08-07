package com.example.crud_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button insert_task;
    EditText task_name,task_date,developer,position;
    String name,date,dev,pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar=findViewById(R.id.toolbar1);
        TextView textView=findViewById(R.id.txt_title);
        setSupportActionBar(toolbar);
        setTitle(textView.getText());
        insert_task=findViewById(R.id.btn_insertTask);
        task_name=findViewById(R.id.task_name_add);
        task_date=findViewById(R.id.task_name_date);
        developer=findViewById(R.id.task_name_developer);
        position=findViewById(R.id.task_name_position);
        final SQLHandler sqlHandler=new SQLHandler(MainActivity2.this);

        //Insert button

        insert_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlHandler.insertTask(name,dev,pos,false,date);
                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });


        //EditText

        task_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            name=editable.toString();
            }
        });

        task_date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                date=editable.toString();
            }
        });

        developer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dev=editable.toString();
            }
        });

        position.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pos=editable.toString();
            }
        });
    }
}