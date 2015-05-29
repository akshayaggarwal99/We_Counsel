package com.aka.wecounsel;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class RankPredictor extends ActionBarActivity {

    EditText et_score;
    String score;
    int pre_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_predictor);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_rank_predictor, menu);
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

    public void onPredictRankClick(View view) {

        et_score = (EditText) findViewById(R.id.edit_score);
        String ed_text = et_score.getText().toString().trim();
        score = et_score.getText().toString();
        boolean b=false;



        if(ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null) {

            Toast.makeText(getApplicationContext(), "Score can't be empty ", Toast.LENGTH_SHORT).show();
            //EditText is empty
            b=true;
        }else    pre_score = Integer.parseInt(score);
        if (pre_score < 504 && pre_score >= 176) {
            Intent intent = new Intent(this, SubActivity_2.class);
            intent.putExtra("score", score);
            startActivity(intent);

        } else if (pre_score > 504) {
            Toast.makeText(getApplicationContext(), "INVALID ENTRY", Toast.LENGTH_SHORT).show();
        }else if (pre_score < 176&& !b ) {
            Toast.makeText(getApplicationContext(), "Rank above 14000  ", Toast.LENGTH_SHORT).show();
        }


    }
}
