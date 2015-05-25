package com.aka.wecounsel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by a on 23-05-2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.aka.wecounsel/databases/";

    private static String DB_NAME = "jeeadvance.db";

    public static final String DATABASE_TABLE1 = "jee_2013";
    public static final String KEY_ROWID="_id";
    public static final String KEY_COLLEGE="college_name";
    public static final String KEY_BRANCH="branch";
    public static final String KEY_BRANCH_CODE="code_branch";
    public static final String KEY_GENOP="GENOP";
    public static final String KEY_GENCL="GENCL";
    public static final String KEY_OBCOP="OBCOP";
    public static final String KEY_OBCCL="OBCCL";
    public static final String KEY_SCOP="SCOP";
    public static final String KEY_SCCL="SCCL";
    public static final String KEY_STOP="STOP";
    public static final String KEY_STCL="STCL";

    public  final String KEY_RANK="";

    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_COLLEGE, KEY_BRANCH, KEY_BRANCH_CODE,KEY_GENOP,KEY_GENCL,KEY_OBCOP,KEY_OBCCL,KEY_SCOP,KEY_SCCL,KEY_STOP,KEY_STCL};

    public static final int COL_ROWID = 0;
    public static final int COL_COLLEGE= 1;
    public static final int COL_BRANCH = 2;
    public static final int COL_BRANCH_CODE = 3;
    public static final int COL_GENOP = 4;
    public static final int COL_GENCL = 5;
    public static final int COL_OBCOP= 6;
    public static final int COL_OBCCL = 7;
    public static final int COL_SCOP = 8;
    public static final int COL_SCCL = 9;
    private SQLiteDatabase myDataBase;

    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    // Return all data in the database.
    public Cursor getAllRows(int rank) {
       // String where =null;
        Cursor c =     myDataBase.query(true, DATABASE_TABLE1, ALL_KEYS,
                KEY_GENCL + " >?",new String[]{String.valueOf(rank)}  , null, null, "(" + KEY_GENCL + "-" +rank+")" + "ASC", null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        Cursor c = 	myDataBase.query(true, DATABASE_TABLE1, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
















    public ArrayList<String> getCategory() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM jee_2013",
                null);
        ArrayList<String> data = new ArrayList<String>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                data.add(cursor.getString(cursor.getColumnIndex("category")));
            }
        }
        cursor.close();
        return data;
    }
    public String getCollege() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{KEY_ROWID, KEY_COLLEGE, KEY_BRANCH, KEY_BRANCH_CODE, KEY_GENOP, KEY_GENCL};
        Cursor c =db.query(DATABASE_TABLE1,columns,null,null,null,null,null);
        String result ="";
        int iRow=c.getColumnIndex(KEY_ROWID);
        int iCollege=c.getColumnIndex(KEY_COLLEGE);
        int iBranch=c.getColumnIndex(KEY_BRANCH);
        int iBranch_Code=c.getColumnIndex(KEY_BRANCH_CODE);
        int iGENOP=c.getColumnIndex(KEY_GENOP);
        int iGENCL=c.getColumnIndex(KEY_GENCL);

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext() ){
            result =result + c.getString(iRow)+" " + c.getString(iCollege) +" "+c.getString(iBranch)+" " + c.getString(iBranch_Code) + "\n";

        }



        return result;

    }






    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

}