package com.example.project3a3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Moviedesc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Moviedesc extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageView imageView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int img_res=-1;
    public int position;
    public Moviedesc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Moviedesc.
     */
    // TODO: Rename and change types and number of parameters
    public static Moviedesc newInstance(String param1, String param2) {
        Moviedesc fragment = new Moviedesc();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Moviedesc","onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("Moviedesc","onCreateView");
        setRetainInstance(true);
        return inflater.inflate(R.layout.fragment_moviedesc, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView=(ImageView) getActivity().findViewById(R.id.tvshowimage);
        if(img_res!=-1){
            imageView.setImageResource(img_res);
        }
        Log.i("Moviedesc","onActivityCreated");

    }
    public void populate(HashMap<String,Integer> tvshows,int index){
        /*int i=0;
        String key=null;
        Log.i("Moviesdesc",index+" ");
        for(Map.Entry<String,Integer> set: tvshows.entrySet()){
            key=set.getKey();
            if(i==index){
                break;
            }
            i++;

        }
        for(Map.Entry<String,Integer> set: tvshows.entrySet()){
            Log.i("Moviedesc",tvshows.get(set.getKey())+" ");

        }
        Log.i("key",key+" ");

        Log.i("image view",imageView+" ");
        Log.i("id",tvshows.get(key)+" ");
        imageView.setImageResource(tvshows.get(key));*/
        position = index;
        imageView.setImageResource(MainActivity.tvshows.get(position));

    }
}