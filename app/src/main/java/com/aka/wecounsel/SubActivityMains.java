package com.aka.wecounsel;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.IOException;

public class SubActivityMains extends AppCompatActivity {


    String rank, Quota="HS";
    DataBaseHelper myDbHelper;
    public String KEY_RANK_OPEN = "GNOP";
    public String KEY_RANK_CLOSE = "GNCR";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_activity_mains);


        Intent i = getIntent();
        Bundle basket = i.getExtras();

        String cat = basket.getString("category");
        Quota = basket.getString("Quota");

        rank = basket.getString("ranks");


        String string = "Expected " + cat + " Rank is " + rank;


        getSupportActionBar().setTitle(string);


        if (cat.equals("GEN")) {
            KEY_RANK_OPEN = DataBaseHelper.KEY_GENOP;
            KEY_RANK_CLOSE = DataBaseHelper.KEY_GENCL;

        } else if (cat.equals("OBC")) {
            KEY_RANK_OPEN = DataBaseHelper.KEY_OBCOP;
            KEY_RANK_CLOSE = DataBaseHelper.KEY_OBCCL;

        } else if (cat.equals("SC")) {
            KEY_RANK_OPEN = DataBaseHelper.KEY_SCOP;
            KEY_RANK_CLOSE = DataBaseHelper.KEY_SCCL;
        } else if (cat.equals("ST")) {
            KEY_RANK_OPEN = DataBaseHelper.KEY_STOP;
            KEY_RANK_CLOSE = DataBaseHelper.KEY_SCCL;
        }


        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
            populateListViewMains();
            //  registerListClickCallback();
        } catch (SQLException sqle) {
            throw sqle;
        }

        //   String data=myDbHelper.getCollege();
        myDbHelper.close();
        //  tv.setText(data);
    }
    @Override
    protected void onPause() {
        super.onPause();
        Intent i = new Intent(this, Mains.class);
        startActivity(i);
    }



    private void populateListViewMains() {


        int pre_rank = Integer.parseInt(rank);
        Cursor cursor = myDbHelper.getAllRow(pre_rank, KEY_RANK_OPEN, KEY_RANK_CLOSE,Quota);


        // Allow activity to manage lifetime of the cursor.
        // DEPRECATED! Runs on the UI thread, OK for small/short queries.
        startManagingCursor(cursor);

        // Setup mapping from cursor to view fields:

        String[] fromFieldNames = new String[]
                {DataBaseHelper.KEY_COLLEGE, DataBaseHelper.KEY_QUOTA, DataBaseHelper.KEY_BRANCH, KEY_RANK_OPEN, KEY_RANK_CLOSE};
        int[] toViewIDs = new int[]
                {R.id.item_college, R.id.item_branch, R.id.item_branch_name, R.id.item_open, R.id.item_close};


        // Create adapter to may columns of the DB onto elemesnt in the UI.
        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        this,        // Context
                        R.layout.college_list_item,    // Row layout template
                        cursor,                    // cursor (set of DB records to map)
                        fromFieldNames,            // DB Column names
                        toViewIDs                // View IDs to put information in
                );


        // Set the adapter for the list view
        ListView myList = (ListView) findViewById(R.id.college_list_mains);
        myList.setAdapter(myCursorAdapter);
    }















//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_sub_activity_mains, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
