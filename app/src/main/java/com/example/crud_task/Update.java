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
import android.widget.Toast;

public class Update extends AppCompatActivity {
    EditText editText,editText1,editText2,editText3;
    CheckBox checkBox;
    Button button,button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editText=findViewById(R.id.update_name_add);
        editText1=findViewById(R.id.update_name_date);
        editText2=findViewById(R.id.update_name_developer);
        editText3=findViewById(R.id.update_name_position);
        editText.setText(MainActivity.name1);
        editText2.setText(MainActivity.developer1);
        editText3.setText(MainActivity.pos);
        editText1.setText(MainActivity.date1);
        checkBox=findViewById(R.id.chk_status);

        if(MainActivity.status1=="DONE"){
            checkBox.setChecked(true);
        }
        else{
            checkBox.setChecked(false);
        }


        button=findViewById(R.id.btn_deleteTask);
        final SQLHandler handler=new SQLHandler(Update.this);
        button1=findViewById(R.id.btn_updateTask1);

        //onclicklisteners
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idd="task_id";
                SQLiteDatabase database=handler.getWritableDatabase();
                ContentValues contentValues= new ContentValues();
                contentValues.put("task_name",editText.getText().toString());
                contentValues.put("task_date",editText1.getText().toString());
                contentValues.put("developer_name",editText2.getText().toString());
                contentValues.put("position_name",editText3.getText().toString());
                if(checkBox.isChecked()){
                    MainActivity.status1="DONE";
                    contentValues.put("is_finish","1");
                }
                else{
                    MainActivity.status1="PENDING";
                    contentValues.put("is_finish","0");
                }

             database.update("myTask",contentValues,idd+"=+?",new String[]{MainActivity.id1});
                database.close();
                Intent intent=new Intent(Update.this,MainActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idd="task_id";
                SQLiteDatabase database=handler.getWritableDatabase();
                database.delete("myTask",idd+"=?",new String[]{MainActivity.id1});
                database.close();
                Toast.makeText(Update.this,"Deleted successfully",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Update.this,MainActivity.class);
                startActivity(intent);
            }
        });



        //editText

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                MainActivity.name1=editable.toString();

            }
        });

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                MainActivity.date1=editable.toString();

            }
        });

        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                MainActivity.developer1=editable.toString();

            }
        });


        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                MainActivity.pos=editable.toString();

            }
        });


    }
    private void chk(){

    }
}