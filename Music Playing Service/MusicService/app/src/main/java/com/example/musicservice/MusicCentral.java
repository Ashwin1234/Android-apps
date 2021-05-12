package com.example.musicservice;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.common.IMyAidlInterface;
import com.example.common.MySongs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// The service api

public class MusicCentral extends Service {
    List<String> song = Arrays.asList("You shook me all night", "Thunderstruck", "Paranoid", "Blinding Lights", "Toosie Slide","sick and tired","Lucky You","The Middle","Arms Around You","All My Love");
    List<String> artist = Arrays.asList("Ac Dc", "Ac Dc", "Black Sabbath", "The Weeknd", "Drake","Iann Dior","Eminem","Zedd","Maluma","Major Lazer");

    List<Integer> image = Arrays.asList(R.drawable.you_shook_me_all_night, R.drawable.thunderstruck, R.drawable.black_sabbath_paranoid, R.drawable.blinding_lights_the_weeknd, R.drawable.toosie_slide,R.drawable.luck_you,R.drawable.sick_and_tired,R.drawable.the_middle,R.drawable.arms_around_you,R.drawable.up_on_the_mountain_tops);
    List<String> url = Arrays.asList("https://ia803108.us.archive.org/1/items/cd_back-in-black_acdc/disc1/06.%20AC-DC%20-%20Back%20in%20Black_sample.mp3", "https://ia803004.us.archive.org/31/items/cd_who-made-who_acdc/disc1/05.%20AC-DC%20-%20Ride%20On_sample.mp3","https://ia803004.us.archive.org/31/items/cd_who-made-who_acdc/disc1/09.%20AC-DC%20-%20For%20Those%20About%20To%20Rock%20%28We%20Salute%20You%29_sample.mp3", "https://ia803004.us.archive.org/31/items/cd_who-made-who_acdc/disc1/09.%20AC-DC%20-%20For%20Those%20About%20To%20Rock%20%28We%20Salute%20You%29_sample.mp3", "https://ia803108.us.archive.org/1/items/cd_back-in-black_acdc/disc1/06.%20AC-DC%20-%20Back%20in%20Black_sample.mp3", "https://ia803108.us.archive.org/1/items/cd_back-in-black_acdc/disc1/06.%20AC-DC%20-%20Back%20in%20Black_sample.mp3","https://ia803108.us.archive.org/1/items/cd_back-in-black_acdc/disc1/06.%20AC-DC%20-%20Back%20in%20Black_sample.mp3","https://ia803108.us.archive.org/1/items/cd_back-in-black_acdc/disc1/06.%20AC-DC%20-%20Back%20in%20Black_sample.mp3","https://ia803108.us.archive.org/1/items/cd_back-in-black_acdc/disc1/06.%20AC-DC%20-%20Back%20in%20Black_sample.mp3","https://ia803108.us.archive.org/1/items/cd_back-in-black_acdc/disc1/06.%20AC-DC%20-%20Back%20in%20Black_sample.mp3","https://ia803108.us.archive.org/1/items/cd_back-in-black_acdc/disc1/06.%20AC-DC%20-%20Back%20in%20Black_sample.mp3");

    public MusicCentral() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;

    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private final IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {

        // The api to retrieve all songs in the service
        @Override


        public List<MySongs> getAllSongs() throws RemoteException {
            List<MySongs> all_songs = new ArrayList<>();
            for (int i = 0; i < song.size(); i++) {
                String name = song.get(i);
                String artist_name=artist.get(i);
                Bitmap bmp = BitmapFactory.decodeResource(getResources(),image.get(i));

                String url_link = url.get(i);

                MySongs mysongs = new MySongs();
                mysongs.name = name;
                mysongs.artist_name = artist_name;
                mysongs.image = bmp;
                mysongs.url = url_link;
                all_songs.add(mysongs);

            }
            return all_songs;
        }

        // The spi to retrieve specific song from the service
        @Override
        public MySongs getSpecificSong(int selected) throws RemoteException {
            String name = song.get(selected);
            String artist_name=artist.get(selected);
            Bitmap bmp = BitmapFactory.decodeResource(getResources(),image.get(selected));

            String url_link = url.get(selected);

            MySongs mysongs = new MySongs();
            mysongs.name = name;
            mysongs.artist_name = artist_name;
            mysongs.image = bmp;
            mysongs.url = url_link;
            return mysongs;
        }

        // The api to get the url of the song
        @Override
        public String getUrl(int selected) throws RemoteException {
            String song_url=url.get(selected);
            return song_url;
        }
    };

    /*private final IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public List<Bundle> getAllSongs() throws RemoteException {

            List<Bundle> all_songs = new ArrayList<>();
            for (int i = 0; i < song.size(); i++) {
                Bundle songs_bundle = new Bundle();
                songs_bundle.putString("name",song.get(i));
                songs_bundle.putString("artist_name",artist.get(i));
                Bitmap bmp = BitmapFactory.decodeResource(getResources(),image.get(i));
                //songs_bundle.putInt("image",image.get(i));
                songs_bundle.putParcelable("image",bmp);
                songs_bundle.putString("url",url.get(i));
                all_songs.add(songs_bundle);
            }
            return all_songs;
        }

        @Override
        public Bundle getSpecificSong(int selected) throws RemoteException {
            Bundle songs_bundle=new Bundle();
            songs_bundle.putString("name",song.get(selected));
            songs_bundle.putString("artist_name",artist.get(selected));
            Bitmap bmp = BitmapFactory.decodeResource(getResources(),image.get(selected));
            //songs_bundle.putInt("image",image.get(i));
            songs_bundle.putParcelable("image",bmp);
            songs_bundle.putString("url",url.get(selected));
            return songs_bundle;
        }

        @Override
        public String getUrl(int selected) throws RemoteException {

        }
    };*/

    /*private final IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public Map getMapValues() throws RemoteException{
            return null;
        }

        @Override
        public Map<String, String> getAllSongs() throws RemoteException {

            return null;
        }

        @Override
        public List<String> getSpecificSong(int selected) throws RemoteException {

            return null;
        }

        @Override
        public String getUrl(int selected) throws RemoteException {
            String songsurl=url.get(selected-1);
            return songsurl;
        }
    };*/
}