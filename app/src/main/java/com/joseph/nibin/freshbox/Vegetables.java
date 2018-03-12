package com.joseph.nibin.freshbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class Vegetables extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    ViewPager vp;
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbaIid);
        setSupportActionBar(toolbar);

        vp = (ViewPager) findViewById(R.id.viewpagerId);
        vp.setOffscreenPageLimit(3);
        this.addPages();

        //TABLAYOUT
        tabLayout = findViewById(R.id.tabId);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(vp);
        tabLayout.setOnTabSelectedListener(this);
    }

    public void addPages(){
        VegPagerAdapter vegPagerAdapter = new VegPagerAdapter(this.getSupportFragmentManager());
        vegPagerAdapter.addFragment(new VegFragment1());
        vegPagerAdapter.addFragment(new VegFragment2());
        vegPagerAdapter.addFragment(new VegFragment3());
        vegPagerAdapter.addFragment(new VegFragment4());

        //SET ADAPTER TO VIEWPAGER
        vp.setAdapter(vegPagerAdapter);
        vp.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vp.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
