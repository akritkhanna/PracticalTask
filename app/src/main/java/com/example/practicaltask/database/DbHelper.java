package com.example.practicaltask.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.example.practicaltask.database.DbContract.COL_CORRECT;
import static com.example.practicaltask.database.DbContract.COL_OPTION_A;
import static com.example.practicaltask.database.DbContract.COL_OPTION_B;
import static com.example.practicaltask.database.DbContract.COL_OPTION_C;
import static com.example.practicaltask.database.DbContract.COL_OPTION_D;
import static com.example.practicaltask.database.DbContract.COL_QUE;
import static com.example.practicaltask.database.DbContract.QUESTION_TABLE;
import static com.example.practicaltask.database.DbContract.COL_ID;


/**
 * Created by the Sir Anku on 19-09-2020 at 10:59 AM .
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "quiz.db";

    private static final int DATABASE_VERSION = 1;
    private static final String TAG = DbHelper.class.getSimpleName();


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String questionTable=  "CREATE TABLE " + QUESTION_TABLE + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_QUE + " TEXT NOT NULL," +
                COL_OPTION_A + " TEXT NOT NULL,"+
                COL_OPTION_B + " TEXT NOT NULL," +
                COL_OPTION_C + " TEXT NOT NULL," +
                COL_OPTION_D + " TEXT NOT NULL,"+
                COL_CORRECT + " TEXT NOT NULL);";

        Log.d(TAG, "onCreate: query "+ questionTable);
        sqLiteDatabase.execSQL(questionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QUESTION_TABLE);
        onCreate(sqLiteDatabase);
    }
}
