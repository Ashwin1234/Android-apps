package com.example.musicclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.common.IMyAidlInterface;
import com.example.common.MySongs;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    IMyAidlInterface myAidlInterface;
    Intent conncetionintent;
    TextView status,song_display,artist_display;
    Spinner spinner;
    Button bind,unbind,getAll;
    ImageView song_image;
    RelativeLayout relative;

    boolean isBinded,isUnBinded;
    static ArrayList<String> song_name;
    static ArrayList<String> artist_name;
    static ArrayList<Bitmap> image;
    static ArrayList<String> url;
    String specific_url;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Declare and initialize all the variables
        conncetionintent = new Intent(IMyAidlInterface.class.getName());
        ResolveInfo info = getPackageManager().resolveService(conncetionintent,0);
        conncetionintent.setComponent(new ComponentName(info.serviceInfo.packageName,info.serviceInfo.name));
        //conncetionintent = new Intent("service");
        //conncetionintent.setPackage("com.example.musicservice");
        bind = (Button) findViewById(R.id.button);
        unbind = (Button) findViewById(R.id.button2);
        status = (TextView) findViewById(R.id.textView);
        spinner = (Spinner) findViewById(R.id.spinner);
        getAll = (Button) findViewById(R.id.button3);
        song_display = (TextView) findViewById(R.id.textView2);
        artist_display = (TextView) findViewById(R.id.textView3);
        song_image = (ImageView) findViewById(R.id.imageView);
        relative = (RelativeLayout) findViewById(R.id.relative);

        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        isBinded = false;
        isUnBinded = false;

        song_name = new ArrayList<>();
        artist_name = new ArrayList<>();
        image = new ArrayList<>();
        url = new ArrayList<>();

    }



    public void onBindOperations() throws RemoteException {
        // if binded retrieve the all the songs and display it in a spinner
        List<String> categories = new ArrayList<>();
        List<MySongs> all_songs = myAidlInterface.getAllSongs();
        for(int i=0;i<all_songs.size();i++){
            categories.add(all_songs.get(i).name);
        }
        ArrayAdapter<String> data = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,categories);
        data.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(data);

    }




    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            Log.i("MainActivity","Service Connected");
            status.setText(R.string.service_connected);
            try {
                onBindOperations();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MainActivity","Service Disconnected");

            myAidlInterface = null;
        }
    };


    public void bindToService(View view) {
        // onClick listener to bind to the service
        // If binded enable all the Ui elements and disable the bind button
        if(!isBinded) {
            bindService(conncetionintent, serviceConnection, Context.BIND_AUTO_CREATE);
            isBinded = true;

            bind.setEnabled(false);
            unbind.setEnabled(true);
            getAll.setEnabled(true);
            spinner.setEnabled(true);
            relative.setVisibility(View.VISIBLE);
        }
    }

    public void unbindToService(View view) {
        // onClick listener to unbind to the service
        // If unbinded disable all the Ui elements and enable the bind button
        if(isBinded) {
            Log.i("Main Activity", "came here");
            unbindService(this.serviceConnection);
            status.setText(R.string.service_disconnected);
            isBinded = false;

            bind.setEnabled(true);
            unbind.setEnabled(false);
            getAll.setEnabled(false);
            spinner.setEnabled(false);
            relative.setVisibility(View.INVISIBLE);
            Intent i1 = new Intent(getApplicationContext(),MusicPlayingService.class);
            stopService(i1);

        }
    }

    public void getAllSongs(View view) throws RemoteException {
        // get all the songs and send an intent to the activity containing recycler view
        List<MySongs> all_songs = myAidlInterface.getAllSongs();
        for(int i=0;i<all_songs.size();i++){
            song_name.add(all_songs.get(i).name);
            artist_name.add(all_songs.get(i).artist_name);
            image.add(all_songs.get(i).image);


            url.add(all_songs.get(i).url);
        }
        Intent i1 = new Intent(getApplicationContext(), SongList.class);
        /*i1.putStringArrayListExtra("name",song_name);
        i1.putStringArrayListExtra("artist_name",artist_name);
        //i1.putParcelableArrayListExtra("image",image);
        i1.putStringArrayListExtra("url",url);*/
        startActivity(i1);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Handler to display the Ui elements
        try {
             MySongs mySong = myAidlInterface.getSpecificSong(position);
             song_image.setImageBitmap(mySong.image);
             song_display.setText(mySong.name);
             artist_display.setText(mySong.artist_name);
             specific_url = mySong.url;
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onDestroy() {
        // Stop the media player service
        super.onDestroy();
        Intent i1 = new Intent(getApplicationContext(),MusicPlayingService.class);
        stopService(i1);
    }

    public void playSong(View view) {
        // Handler to play the songs when clicked on the spinner
        Intent service_intent = new Intent(getApplicationContext(),MusicPlayingService.class);
        service_intent.putExtra("url",specific_url);
        startService(service_intent);

    }
}