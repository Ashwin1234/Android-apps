package com.example.musicclient;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    ArrayList<String> songlist,artistlist;
    ArrayList<Bitmap> imagelist;
    SongList.VideoListener listener;




    RecyclerAdapter(ArrayList<String> songlist, ArrayList<String> artistlist, ArrayList<Bitmap> imagelist, SongList.VideoListener listener){
        this.songlist=songlist;
        this.artistlist=artistlist;
        this.imagelist=imagelist;
        this.listener=listener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView song_name, song_artist;
        ImageView song_image;
        SongList.VideoListener listener;

        int no_of_menu_items = 3;
        int position;
        View selected_view;
        HashMap<Integer, String[]> wiki_map;
        //Creating the nested class ViewHolder to create and populate each element in recycler view

        public ViewHolder(@NonNull View itemView, SongList.VideoListener listener) {
            super(itemView);
            song_name = (TextView) itemView.findViewById(R.id.textView2);
            song_artist = (TextView) itemView.findViewById(R.id.textView3);
            song_image = (ImageView) itemView.findViewById(R.id.imageView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            //itemView.setOnCreateContextMenuListener(this);


        }
        //Overriding the onClick method to handle clicks on recycler view items
        @Override
        public void onClick(View v) {

            listener.onClick(v, getAdapterPosition());
        }


        //handlers to handle wiki page of songs and artists

    }
    @NonNull
    //Inflate the view holder instance with the views
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View v1=layoutInflater.inflate(R.layout.list_layout,parent,false);
        ViewHolder viewholder=new ViewHolder(v1,listener);
        return viewholder;
    }

    //Binding the view holder views with the appropriate song,artist and image
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.song_name.setText(songlist.get(position));
        holder.song_artist.setText(artistlist.get(position));
        holder.song_image.setImageBitmap(imagelist.get(position));



    }

    @Override
    public int getItemCount() {

        return songlist.size();
    }




}
