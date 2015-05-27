package com.aka.wecounsel;

import android.content.Intent;
import android.database.Cursor;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;




public class SubActivity extends ActionBarActivity {
//    MaterialTabHost tabHost;
//    ViewPager pager;
 //   ViewPagerAdapter adapter;
    String rank;
    DataBaseHelper myDbHelper;
    public   String KEY_RANK_OPEN="GNOP";
    public   String KEY_RANK_CLOSE="GNCR";

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
//        tv=(TextView)findViewById(R.id.tv);
        Intent i = getIntent();
        String cat =i.getExtras().getString("category");
       rank =i.getExtras().getString("ranks");


        String string = "Expected "+ cat + " Rank is " + rank ;


        getSupportActionBar().setTitle(string);


        if(cat.equals("GEN")){
            KEY_RANK_OPEN =DataBaseHelper.KEY_GENOP;
            KEY_RANK_CLOSE =DataBaseHelper.KEY_GENCL;

        }else if(cat.equals("OBC")){
            KEY_RANK_OPEN =DataBaseHelper.KEY_OBCOP;
            KEY_RANK_CLOSE =DataBaseHelper.KEY_OBCCL;

        }else if(cat.equals("SC")){
            KEY_RANK_OPEN =DataBaseHelper.KEY_SCOP;
            KEY_RANK_CLOSE =DataBaseHelper.KEY_SCCL;
        }else if(cat.equals("ST")){
            KEY_RANK_OPEN =DataBaseHelper.KEY_STOP;
            KEY_RANK_CLOSE =DataBaseHelper.KEY_SCCL;
        }





        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
            populateListView();
          //  registerListClickCallback();
        } catch (SQLException sqle) {
            throw sqle;
        }

     //   String data=myDbHelper.getCollege();
        myDbHelper.close();
      //  tv.setText(data);




































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

    @Override
    protected void onPause() {
        super.onPause();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    private void populateListView() {


        int pre_rank =Integer.parseInt(rank);
        Cursor cursor=myDbHelper.getAllRows(pre_rank ,KEY_RANK_OPEN,KEY_RANK_CLOSE);



        // Allow activity to manage lifetime of the cursor.
        // DEPRECATED! Runs on the UI thread, OK for small/short queries.
        startManagingCursor(cursor);

        // Setup mapping from cursor to view fields:

        String[] fromFieldNames = new String[]
        {DataBaseHelper.KEY_COLLEGE,DataBaseHelper.KEY_BRANCH_CODE ,DataBaseHelper.KEY_BRANCH,KEY_RANK_OPEN, KEY_RANK_CLOSE};
        int[] toViewIDs = new int[]
                {R.id.item_college, R.id.item_branch ,R.id.item_branch_name ,  R.id.item_open,           R.id.item_close};








        // Create adapter to may columns of the DB onto elemesnt in the UI.
        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        this,		// Context
                        R.layout.college_list_item,	// Row layout template
                        cursor,					// cursor (set of DB records to map)
                        fromFieldNames,			// DB Column names
                        toViewIDs				// View IDs to put information in
                );



        // Set the adapter for the list view
        ListView myList = (ListView) findViewById(R.id.college_list);
        myList.setAdapter(myCursorAdapter);
    }



//    private void registerListClickCallback() {
//        ListView myList = (ListView) findViewById(R.id.college_list);
//        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View viewClicked,
//                                    int position, long idInDB) {
//
//                Cursor cursor = myDbHelper.getRow(idInDB);
//                if (cursor.moveToFirst()) {
//                    long idDB = cursor.getLong(DataBaseHelper.COL_ROWID);
//                    String name = cursor.getString(DataBaseHelper.COL_COLLEGE);
//                    String branch = cursor.getString(DataBaseHelper.COL_BRANCH);
////                    int studentNum = cursor.getInt(DataBaseHelper.COL_GENOP);
////                    int favColour = cursor.getInt(DataBaseHelper.COL_GENCL);
//
//                    String message = "ID: " + idDB + "\n"
//                            + "College: " + name + "\n"
//                            + "Branch: " + branch + "\n";
//
//                    Toast.makeText(SubActivity.this, message, Toast.LENGTH_LONG).show();
//                }
//                cursor.close();
//            }
//        });

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