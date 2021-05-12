package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Activity2 extends AppCompatActivity {
    EditText edit_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
         edit_text=(EditText)findViewById(R.id.editTextTextPersonName);

         edit_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
             @Override
             public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                 Intent back=new Intent();
                 int flag=0;
                 String  name=edit_text.getText().toString().trim();
                 back.putExtra("NAME",name);
                 String first_last[]=name.split(" ");

                 if(first_last.length>1 && first_last.length<4){
                     for(int i=0;i<first_last.length;i++){

                         if(first_last[i].matches("[a-zA-Z]+"))
                         {
                             flag=flag+1;
                         }
                     }

                     if(flag==first_last.length){
                         setResult(RESULT_OK,back);
                     }
                     else{
                         setResult(RESULT_CANCELED,back);

                     }


                 }
                 else{

                     setResult(RESULT_CANCELED,back);
                 }


                 finish();
                 return true;
             }
         });

        //Toast toast=Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT);
        //toast.show();

    }

}
