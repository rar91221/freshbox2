package com.joseph.nibin.freshbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class VegFragment2 extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_veg_fragment2,container,false);
        ListView lv = (ListView) rootView.findViewById(R.id.lv2);
        TopVeggiesAdapter adapter = new TopVeggiesAdapter(this.getActivity(),getBeansAndGrouds());
        lv.setAdapter(adapter);
        return rootView;
    }

    private ArrayList<Custom_Class_VF1> getBeansAndGrouds(){
        ArrayList<Custom_Class_VF1> topVegFragmentArrayList = new ArrayList<>();
        Custom_Class_VF1 ccvf1Obj;
        for (int i = 0; i<10; i++){
            ccvf1Obj = new Custom_Class_VF1("image","name","unit","price");
            topVegFragmentArrayList.add(ccvf1Obj);
        }

        return topVegFragmentArrayList;
    }

    @Override
    public String toString() {
        String title = "Beans and Grouds";
        return title;
    }
}