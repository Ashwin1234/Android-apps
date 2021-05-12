package com.example.project3a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class TvShow extends AppCompatActivity {
    Bundle extras;
    WebView wb;
    String url;
    ReceiverApp1 receiverApp1;
    WebSettings websettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show);
        receiverApp1=new ReceiverApp1();
        wb=(WebView)findViewById(R.id.webview);
        wb.setWebViewClient(new MyBrowser());
        websettings=wb.getSettings();
        websettings.setJavaScriptEnabled(true);
        //Get the extras from the intent
        extras=getIntent().getExtras();
        Log.i("Tvshow","Receiving url "+extras);
        if(extras==null){
            Toast.makeText(getApplicationContext(),"No movie is selected",Toast.LENGTH_SHORT).show();
        }
        else{
            //Display the web page in web view inside the activity
            url=extras.getString("tv_url");
            wb.loadUrl(url);
        }

    }

    public void displayTv(Bundle extras){

    }
}