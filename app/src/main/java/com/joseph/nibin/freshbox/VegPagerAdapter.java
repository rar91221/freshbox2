package com.joseph.nibin.freshbox;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VegPagerAdapter extends FragmentPagerAdapter{

    ArrayList<Fragment> fragments = new ArrayList<>();

    public VegPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //ADD PAGE
    public void addFragment(Fragment f){
        fragments.add(f);
    }

    //SET TITLE
    @Override
    public CharSequence getPageTitle(int position) {
        String title = fragments.get(position).toString();
        return title.toString();
    }
}