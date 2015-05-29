package com.aka.wecounsel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText et_rank;
    String rank,jee;
    Bundle basket = new Bundle();
    RadioButton jee2013 ,jee2014;
    RadioGroup rg;
    Button find_colleges;


    Spinner spinner_category;
    private String[] state = {"GEN", "OBC", "SC", "ST"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        spinner_category = (Spinner) findViewById(R.id.spinnerstate);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                R.layout.spinner_item, state);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(adapter_state);
        rg = (RadioGroup) findViewById(R.id.radio_group);
        find_colleges = (Button) findViewById(R.id.find_colleges);

        jee2013 = (RadioButton) findViewById(R.id.radioButton_2013);
        jee2014 = (RadioButton) findViewById(R.id.radioButton_2014);

    }


    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

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

    public void onFindCollegesClick(View view) {


        et_rank = (EditText) findViewById(R.id.edit_rank);
        rank = et_rank.getText().toString();
        String category = spinner_category.getSelectedItem().toString();

        String ed_text = et_rank.getText().toString().trim();
        if (ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null) {

            Toast.makeText(getApplicationContext(), "Rank can't be empty ", Toast.LENGTH_SHORT).show();
            //EditText is empty
        }

        int selectedId = rg.getCheckedRadioButtonId();
        if(selectedId == jee2013.getId()) {
            jee="jee_2013";



        }
        if(selectedId == jee2014.getId()) {


            jee="jee_2014";


        }
        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
        basket.putString("category", category);
        basket.putString("ranks", rank);
        basket.putString("jee",jee);
        intent.putExtras(basket);
        startActivity(intent);



    }




}


//EditText is not empty






