package com.example.project3a3;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Tvshowtitle.ListSelectionListener {

    public static HashMap<String,Integer> tvshows;
    public HashMap<Integer,String> tvshowurls;
    FrameLayout name_frame;
    FrameLayout image_frame;
    Tvshowtitle tvshowtitle;
    Moviedesc moviedesc;

    int old_index;
    String old_item="old_item";
    FragmentManager fragmentManager;
    boolean orientation;
    Intent i1;
    String TOAST_INTENT="com.example.project3a3";
    String A1_A2_PERMISSION="edu.uic.cs478.s19.kaboom";
    @Override

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        name_frame=(FrameLayout)findViewById(R.id.name);
//        image_frame=(FrameLayout)findViewById(R.id.action_image);
//        tvshows=new HashMap<>();
//        tvshowurls=new HashMap<>();
//
//        tvshows.put("Friends",R.drawable.friends_tv);
//        tvshows.put("Big Bang Theory",R.drawable.big_bang_theory_tv);
//        updateTvshows();
//        tvshowurls.put(0,"https://en.wikipedia.org/wiki/Friends");
//        tvshowurls.put(1,"https://en.wikipedia.org/wiki/The_Big_Bang_Theory");
//
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.tvshow);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_USE_LOGO);
        getSupportActionBar().setIcon(R.drawable.tvshow); 11
//
//
        parseJson(tvshows,tvshowurls);
//        fragmentManager=getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        tvshowtitle=new Tvshowtitle();
//        moviedesc=new Moviedesc();
//        fragmentTransaction.replace(R.id.name,tvshowtitle);
//        Log.i("MainActivity","title fragment replaced");
//        orientation=false;
//        fragmentTransaction.commit();
//        i1=new Intent(TOAST_INTENT);
//        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            public void onBackStackChanged() {
//                setLayout();
//            }
//        });
//        /*if(savedInstanceState!=null){
            old_index=savedInstanceState.getInt(old_item);
//            int new_index=savedInstanceState.getInt("state");
//            orientation=savedInstanceState.getBoolean("orientation");
//            Log.i("index received",+savedInstanceState.getInt("state")+ " "+orientation);
//
//                /*fragmentTransaction.replace(R.id.action_image, moviedesc);
//                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
//                fragmentManager.executePendingTransactions();
//            if(getSupportFragmentManager().getFragment(savedInstanceState,"image fragment")!=null){
//                moviedesc=(Moviedesc)getSupportFragmentManager().getFragment(savedInstanceState,"image fragment");
//            }
//
//            onListIndex(new_index);
//
//        }*/
//        if (savedInstanceState != null) {
//            old_index = savedInstanceState.getInt("savedIndex");
//            if (old_index != -1) {
//                if (getSupportFragmentManager().getFragment(savedInstanceState,"iFragment") != null) {
//                    moviedesc = (Moviedesc) getSupportFragmentManager().getFragment(savedInstanceState,"iFragment");
//                    moviedesc.populate(tvshows,old_index);
//                    setLayout();
//                }
//            }
//        }
//
//    }
//
//    @Override
//    public void onListIndex(int pos) {
//        Log.i("Main Activity","came here");
//        old_index = pos;
//        if(!orientation) {
//
//            if(!moviedesc.isAdded()) {
//                Log.i("MainActivity","Image fragment added");
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.action_image, moviedesc);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//                fragmentManager.executePendingTransactions();
//            }
//        }
//
//        Log.i("index state ",old_index+" ");
//
//        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
//            Log.i("landscape","came here");
//            name_frame.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT, 1f));
//            image_frame.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,2f));
//        }
//        else {
//            Log.i("potrait","came here");
//            name_frame.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0f));
//            image_frame.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 3f));
//        }
//            orientation=false;
//            Log.i("index",old_index+" ");
//            moviedesc.populate(tvshows, old_index);
//
//    }
//
//    public void setLayout() {
//        Log.i("Main Activity", " came here too!!");
//        if (!moviedesc.isAdded()) {
//            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                name_frame.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
//                image_frame.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2f));
//            } else {
//                name_frame.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 3f));
//                image_frame.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0f));
//            }
//        }
//
//        else{
//                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                    name_frame.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
//                    image_frame.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2f));
//                } else {
//
//                    name_frame.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0f));
//                    image_frame.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 3f));
//                }
//            }
//        }
//
//
//    /*@Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Log.i("onsaveinstance",old_index+" ");
//        outState.putInt("state ",old_index);
//        outState.putBoolean("orientation",true);
//        if(moviedesc.isAdded()){
//            fragmentManager.putFragment(outState,"image fragment",moviedesc);
//        }
//    }*/
//    public void onSaveInstanceState(Bundle bundle) {
//        super.onSaveInstanceState(bundle) ;
         save the current index in the bundle
//        if (old_index != -1) {
//            bundle.putInt("savedIndex", old_index) ;
//        }
//        else {
//            bundle.putInt("savedIndex", -1 ) ;
//        }
         if there is an image selection, save the image fragment
//        if (moviedesc.isAdded()) {
//            fragmentManager.putFragment(bundle, "iFragment", moviedesc);
//        }
         save the title fragment
//        fragmentManager.putFragment(bundle, "lFragment", moviedesc);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.option_menu,menu);
//        return true;
//
//    }
//
//    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        i1.putExtra("tv_url",tvshowurls.get(old_index));
        Log.i("MainActivity","broadcasting intents");
        switch (item.getItemId()){
            case R.id.launch:
                Log.i("MainActivity","broadcasting intents");
                sendOrderedBroadcast(i1,A1_A2_PERMISSION);
                return true;
            case R.id.exit:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                return true;
        }
        return true;
    }
    public void parseJson(HashMap<String,Integer> tvshows,HashMap<Integer,String> tvshowurls){
        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray values=jsonObject.getJSONArray("values");
            for(int i=0;i<values.length();i++){
                tvshows.put(values.getJSONObject(i).getString("name"),Integer.parseInt(values.getJSONObject(i).getString("image")));
                tvshowurls.put(i,values.getJSONObject(i).getString("url"));
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("tvshows.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public void updateTvshows(){

    }
}