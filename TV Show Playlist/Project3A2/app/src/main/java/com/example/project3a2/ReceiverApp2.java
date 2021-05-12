package com.example.project3a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ReceiverApp2 extends BroadcastReceiver {
    @Override
    //The receiver function which receives the broadcasted intent and calls the activity to display the web page
    public void onReceive(Context context, Intent intent) {
        Log.i("Receiver","came to app2");
        Toast.makeText(context,"App2 receiving the broadcast from app3 ",Toast.LENGTH_SHORT).show();
    }
}
