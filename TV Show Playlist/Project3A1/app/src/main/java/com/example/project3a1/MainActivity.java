package com.example.project3a1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver broadcastReceiver;
    IntentFilter intentFilter;
    String TOAST_INTENT="com.example.project3a3";
    String permission="edu.uic.cs478.s19.kaboom";
    int ASK_PERMISSION_CODE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadcastReceiver=new ReceiverApp1();
        intentFilter=new IntentFilter(TOAST_INTENT);
        intentFilter.setPriority(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    //The result function of check permission which calls App2 if permission is given else displays a toast
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //Register receiver
            registerReceiver(broadcastReceiver,intentFilter);
            Log.i("MainActivity","Launching App2");
            //Launch A2
            Intent launch_A2=getPackageManager().getLaunchIntentForPackage("com.example.project3a2");
            //launch_A2.setComponent(new ComponentName("com.example.project3a2","MainActivity.java"));
            startActivity(launch_A2);
        }
        else{
            Toast.makeText(getApplicationContext(),"Permission was not granted by the user cannot proceed with the computations of A1",Toast.LENGTH_LONG).show();
        }
    }

    //This method checks whether a permission is given by the user if not asks the permission to the user.
    public void checkPermissions(View view) {
        Log.i("MainActivity","came here");
        if(ContextCompat.checkSelfPermission(MainActivity.this,permission)==PackageManager.PERMISSION_GRANTED){
            registerReceiver(broadcastReceiver,intentFilter);
            Toast toast=Toast.makeText(getApplicationContext(),"reached here",Toast.LENGTH_SHORT);
            toast.show();
            Intent launch_A2=getPackageManager().getLaunchIntentForPackage("com.example.project3a2");
            //launch_A2.setComponent(new ComponentName("com.example.project3a2","MainActivity.java"));
            startActivity(launch_A2);
        }
        else{
            Log.i("MainActivity","came here");
            ActivityCompat.requestPermissions(this,new String[] {permission},
                    ASK_PERMISSION_CODE
            );
        }
    }

}