package com.example.project3a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TOAST_INTENT="com.example.project3a3";
    String permission="edu.uic.cs478.s19.kaboom";
    int ASK_PERMISSION=0;
    BroadcastReceiver broadcastReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadcastReceiver=new ReceiverApp2();
        intentFilter=new IntentFilter(TOAST_INTENT);
        intentFilter.setPriority(2);
    }
    //This method checks whether a permission is given by the user if not asks the permission to the user.
    public void checkPermissions(View view) {
        if(ContextCompat.checkSelfPermission(MainActivity.this,permission)== PackageManager.PERMISSION_GRANTED){
            registerReceiver(broadcastReceiver,intentFilter);
            Intent launch_A3=getPackageManager().getLaunchIntentForPackage("com.example.project3a33");
            //launch_A3.setComponent(new ComponentName("com.example.project3a","MainActivity.java"));
            startActivity(launch_A3);
            //Toast toast=Toast.makeText(getApplicationContext(),"Broadcast from App3",Toast.LENGTH_LONG);
            //toast.show();
        }
        else{
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {permission},
                    ASK_PERMISSION
            );
        }
    }
    //The result function of check permission which calls App2 if permission is given else displays a toast
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

            registerReceiver(broadcastReceiver,intentFilter);
            Intent launch_A3=getPackageManager().getLaunchIntentForPackage("com.example.project3a33");
            //launch_A3.setComponent(new ComponentName("com.example.project3a","MainActivity.java"));
            startActivity(launch_A3);
        }
        else{
            Toast.makeText(getApplicationContext(),"Permission not granted cannot continue the computations of App2",Toast.LENGTH_LONG).show();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }


}