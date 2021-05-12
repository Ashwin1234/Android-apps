package com.example.project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int flag;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickFirst(View view) {

        Intent first=new Intent(MainActivity.this,Activity2.class);
        startActivityForResult(first,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.i("MainActivity","Result code "+resultCode);
        flag=resultCode;
        name=data.getExtras().getString("NAME");
        Log.i("MainActivity"," flag is "+flag);

    }

    public void onClickSecond(View view) {
        if(flag==0){
            Toast toast=Toast.makeText(getApplicationContext(),"Invalid Name "+name,Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
                Intent i=new Intent(ContactsContract.Intents.Insert.ACTION);
                i.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                i.putExtra("name",name);
                //i.putExtra("phone","98");
                startActivity(i);

        }

    }
}