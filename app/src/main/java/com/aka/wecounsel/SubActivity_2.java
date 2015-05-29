package com.aka.wecounsel;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.IOException;


public class SubActivity_2 extends ActionBarActivity {
    String score;
    DataBaseHelper myDbHelper;
    TextView rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_activity_2);


        rank = (TextView) findViewById(R.id.tv_rank);
        Intent i = getIntent();
        score = i.getExtras().getString("score");


        String string = "Expected score - " + score;


        getSupportActionBar().setTitle(string);


        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
            populateTextView();
            //  registerListClickCallback();
        } catch (SQLException sqle) {
            throw sqle;
        }

        //   String data=myDbHelper.getCollege();
        myDbHelper.close();
        //  tv.setText(data);


    }

    private void populateTextView() {

        int open, close;
        int pre_score = Integer.parseInt(score);
//      Cursor c=myDbHelper.getRow(pre_score);

        String result = myDbHelper.getRow(pre_score);


        rank.setText(" YOUR EXPECTED RANK IS IN BETWEEN - " + result);

//        if( c != null && c.moveToFirst() ){
//            result  = c.getString(c.getColumnIndex("OPR"));
//
//            c.close();
//        }


        //   Log.v(title,"title");
    }


}





//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_sub_activity_2, menu);
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
//}
