package com.example.project3a1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ReceiverApp1 extends BroadcastReceiver {
    Bundle extras;
    //The receiver function which receives the broadcasted intent and calls the activity to display the web page
    @Override
    public void onReceive(Context context, Intent intent) {
        //Log.i("Receiver","came here"+extras.getString("tv_url"));
        Toast.makeText(context,"App1 receiving intent from App3",Toast.LENGTH_SHORT).show();
        extras=intent.getExtras();
        //TvShow tvShow=new TvShow();
        //tvShow.displayTv(extras);
        Intent display_tv=new Intent(context,TvShow.class);
        display_tv.putExtra("tv_url",extras.getString("tv_url"));
        context.startActivity(display_tv);

    }

}
