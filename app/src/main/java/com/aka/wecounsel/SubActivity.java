package com.aka.wecounsel;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class SubActivity extends ActionBarActivity {
    MaterialTabHost tabHost;
    ViewPager pager;
 //   ViewPagerAdapter adapter;
    String rank;
    DataBaseHelper myDbHelper;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        tv=(TextView)findViewById(R.id.tv);
        Intent i = getIntent();
       rank =i.getExtras().getString("ranks");
        int pre_rank =Integer.parseInt(rank);



        getSupportActionBar().setTitle("Expected Rank - " + rank);




        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        String data=myDbHelper.getCollege();
        myDbHelper.close();
        tv.setText(data);




































//        Toolbar toolbar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolbar);
//        this.setSupportActionBar(toolbar);

//        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
//        pager = (ViewPager) this.findViewById(R.id.pager );
//
//        // init view pager
//        adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        pager.setAdapter(adapter);
//        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                // when user do a swipe the selected tab change
//                tabHost.setSelectedNavigationItem(position);
//
//            }
//        });

//        // insert all tabs from pagerAdapter data
//        for (int i = 0; i < adapter.getCount(); i++) {
//            tabHost.addTab(
//                    tabHost.newTab()
//                            .setText(adapter.getPageTitle(i))
//                            .setTabListener(this)
//            );
//
//        }



    }
}

//    @Override
//    public void onTabSelected(MaterialTab tab) {
//        pager.setCurrentItem(tab.getPosition());
//    }
//
//    @Override
//    public void onTabReselected(MaterialTab tab) {
//
//    }
//
//    @Override
//    public void onTabUnselected(MaterialTab tab) {
//
//    }
//
//    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
//
//        public ViewPagerAdapter(FragmentManager fm) {
//            super(fm);
//
//        }
//
//        public Fragment getItem(int num) {
//            return new FragmentText();
//        }
//
//        @Override
//        public int getCount() {
//            return 6;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return "Section " + position;
//        }
//
//    }
//}