package com.example.crud_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Update extends AppCompatActivity {
    EditText txt_update, txt_date, txt_developer, txt_position;
    CheckBox checkBox;
    Button btn_delete, btn_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        txt_update =findViewById(R.id.update_name_add);
        txt_date =findViewById(R.id.update_name_date);
        txt_developer =findViewById(R.id.update_name_developer);
        txt_position =findViewById(R.id.update_name_position);
        txt_update.setText(RecycleView_Adapter.name1);
        txt_developer.setText(RecycleView_Adapter.developer1);
        txt_position.setText(RecycleView_Adapter.pos);
        txt_date.setText(RecycleView_Adapter.date1);
        checkBox=findViewById(R.id.chk_status);

        if(RecycleView_Adapter.status1=="DONE"){
            checkBox.setChecked(true);
        }
        else{
            checkBox.setChecked(false);
        }


        btn_delete =findViewById(R.id.btn_deleteTask);
        final SQLHandler handler=new SQLHandler(Update.this);
        btn_update =findViewById(R.id.btn_updateTask1);

        //onclicklisteners
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idd="task_id";
                SQLiteDatabase database=handler.getWritableDatabase();
                ContentValues contentValues= new ContentValues();
                contentValues.put("task_name", txt_update.getText().toString());
                contentValues.put("task_date", txt_date.getText().toString());
                contentValues.put("developer_name", txt_developer.getText().toString());
                contentValues.put("position_name", txt_position.getText().toString());
                if(checkBox.isChecked()){
                    RecycleView_Adapter.status1="DONE";
                    contentValues.put("is_finish","1");
                }
                else{
                    RecycleView_Adapter.status1="PENDING";
                    contentValues.put("is_finish","0");
                }
             database.update("myTask",contentValues,idd+"=+?",new String[]{RecycleView_Adapter.id1});
                database.close();
                Intent intent=new Intent(Update.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               handler.deleteTask(RecycleView_Adapter.id1);
                Intent intent=new Intent(Update.this,MainActivity.class);
                startActivity(intent);
            }
        });



        //editText

        txt_update.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                RecycleView_Adapter.name1=editable.toString();

            }
        });

        txt_date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                RecycleView_Adapter.date1=editable.toString();

            }
        });

        txt_developer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                RecycleView_Adapter.developer1=editable.toString();

            }
        });


        txt_position.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                RecycleView_Adapter.pos=editable.toString();

            }
        });


    }
}