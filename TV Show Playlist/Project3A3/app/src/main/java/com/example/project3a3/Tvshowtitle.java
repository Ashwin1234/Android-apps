package com.example.project3a3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tvshowtitle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tvshowtitle extends ListFragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView tvshowtitle;
    ListSelectionListener listSelectionListener;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String OLD_ITEM="oldpos";
    Integer old_item;
    protected interface ListSelectionListener{
        public void onListIndex(int pos);
    }
    public Tvshowtitle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tvshowtitle.
     */
    // TODO: Rename and change types and number of parameters
    public static Tvshowtitle newInstance(String param1, String param2) {
        Tvshowtitle fragment = new Tvshowtitle();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        getListView().setItemChecked(position,true);
        Log.i("tvshowwithtitle","came here");
        listSelectionListener.onListIndex(position);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listSelectionListener = (ListSelectionListener) context;
        }
        catch(ClassCastException e){
            throw new ClassCastException(context.toString() + " Must extend the ListSelectionListener interface");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View retView =  super.onCreateView(inflater, container, savedInstanceState) ;
        if (savedInstanceState != null) {
            int oldPosition = savedInstanceState.getInt(OLD_ITEM) ;

            old_item = oldPosition ;
        }
        else {
            old_item = null ;
        }
        return retView ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvshowtitle=(TextView) getActivity().findViewById(R.id.tvshowtitle);
        getListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        ArrayList<String> names=new ArrayList<>();
        for(Map.Entry<String,Integer> set: MainActivity.tvshows.entrySet()){
            names.add(set.getKey());
            Log.i("Tvshowtitle",set.getKey()+" "+set.getValue());
        }
        setListAdapter(new ArrayAdapter<String>(getActivity(),R.layout.fragment_tvshowtitle, names));

    }
}