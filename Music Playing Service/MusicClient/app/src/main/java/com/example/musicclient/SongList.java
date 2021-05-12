package com.example.musicclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;

public class SongList extends AppCompatActivity {
    androidx.recyclerview.widget.RecyclerView recycle;
    ArrayList<String> songList;
    ArrayList<String> artistList;
    ArrayList<Bitmap> imageList;
    ArrayList<String> urlList;
    interface VideoListener {
        void onClick(View v, int position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recycle=(androidx.recyclerview.widget.RecyclerView)findViewById(R.id.recycle);
        Log.i("Recycler","came here");
            songList = MainActivity.song_name;
            artistList = MainActivity.artist_name;
            imageList = MainActivity.image;
            urlList = MainActivity.url;


        VideoListener listener = (View v, int position) -> {

            // Start playing the service when clicked on the Recycler View
            String url = urlList.get(position); // your URL here
            Log.i("Recycler View ", url);

            Intent service_intent = new Intent(getApplicationContext(),MusicPlayingService.class);
            service_intent.putExtra("url",url);
            startService(service_intent);


        };

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(songList, artistList, imageList,listener);
        recycle.setHasFixedSize(true);
        recycle.setAdapter(recyclerAdapter);

        recycle.setLayoutManager(new LinearLayoutManager(this));

        registerForContextMenu(recycle);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("Recyclersave","came here");
        outState.putStringArrayList("name",songList);
        outState.putStringArrayList("artist-name",artistList);
        outState.putParcelableArrayList("image",imageList);
        outState.putStringArrayList("url",songList);
    }

    @Override
    protected void onDestroy() {
        // Stop playing the service when activity is destroyed
        super.onDestroy();
        Intent i1 = new Intent(getApplicationContext(),MusicPlayingService.class);
        stopService(i1);
    }
}