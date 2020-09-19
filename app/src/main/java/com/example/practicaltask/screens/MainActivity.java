package com.example.practicaltask.screens;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.practicaltask.R;
import com.example.practicaltask.database.DbHelper;
import com.example.practicaltask.models.Questions;

import java.util.ArrayList;
import java.util.List;

import static com.example.practicaltask.database.DbContract.COL_CORRECT;
import static com.example.practicaltask.database.DbContract.COL_OPTION_A;
import static com.example.practicaltask.database.DbContract.COL_OPTION_B;
import static com.example.practicaltask.database.DbContract.COL_OPTION_C;
import static com.example.practicaltask.database.DbContract.COL_OPTION_D;
import static com.example.practicaltask.database.DbContract.COL_QUE;
import static com.example.practicaltask.database.DbContract.QUESTION_TABLE;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SQLiteDatabase sqLiteDatabase;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(MainActivity.this);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        if (shouldInsert()) {
            addQuestion();
        }



        CardView playBtnCV = findViewById(R.id.playBtnCV);

        playBtnCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toQuizScreen = new Intent(MainActivity.this, QuizScreen.class);
                startActivity(toQuizScreen);
            }
        });

    }


    private boolean shouldInsert() {
        String query = "SELECT * FROM " + QUESTION_TABLE + ";";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int rowCount = cursor.getCount();
        Log.d(TAG, "shouldInsert: rows count " + rowCount);
        cursor.close();
        return rowCount == 0;
    }

    private void addQuestion() {
        String[] questionStatement = new String[]{
                "Who is the Prime Minister of India?",
                "What is the capital of India?",
                "What is sum of 15 + 25 ?",
                "Which one is maximum? 25, 11, 17, 18, 40, 42",
                "What is the official language of Gujarat?",
                "What is multiplication of 12 * 12 ?",
                "Which state of India has the largest population?",
                "Who is the Home Minister of India?",
                "What is the capital of Gujarat?",
                "Which number will be next in series? 1, 4, 9, 16, 25",
                "Which one is minimum? 5, 0, -20, 11",
                "What is sum of 10, 12 and 15?",
                "What is the official language of the Government of India?",
                "Which country is located in Asia?",
                "Which language(s) is/are used for Android app development?"
        };

        String[] optionA = new String[]{
                "Narendra Modi",
                "Mumbai",
                "5",
                "11",
                "HINDI",
                "124",
                "UP",
                "AMIT SHAH",
                "Vadodara",
                "21",
                "0",
                "37",
                "HINDI",
                "INDIA",
                "JAVA"
        };

        String[] optionB = new String[]{
                "Rahul Gandhi",
                "Chennai",
                "25",
                "42",
                "Gujarati",
                "12",
                "BIHAR",
                "Rajnath Singh",
                "Ahmedabad,",
                "36",
                "11",
                "25",
                "English",
                "USA",
                "Java & Kotlin"
        };

        String[] optionC = new String[]{
                "Manmohan Singh",
                "Delhi",
                "40",
                "17",
                "Marathi",
                "24",
                "GUJARAT",
                "Narendra Modi,",
                "GANDINAGAR",
                "49",
                "-20",
                "10",
                "Gujarati",
                "UK",
                "Kotlin,"
        };

        String[] optionD = new String[]{
                "Amit Shah",
                "Ahmedabad",
                "NONE",
                "NONE",
                "NONE",
                "NONE",
                "Maharastra",
                "NONE",
                "Rajkot",
                "32",
                "None",
                "12",
                "NONE",
                "NONE",
                "SWIFT"



        };

        String[] correctOption = new String[]{
                "Narendra Modi",
                "Delhi",
                "40",
                "42",
                "GUJARATI",
                "NONE","" +
                "UP",
                "AMIT SHAH",
                "GANDHINAGAR",
                "36",
                "-20",
                "37",
                "HINDI",
                "INDIA",
                "Java & Kotlin"
        };

        for (int i = 0; i < questionStatement.length; i++) {
            ContentValues values = new ContentValues();
            values.put(COL_QUE, questionStatement[i]);
            values.put(COL_OPTION_A, optionA[i]);
            values.put(COL_OPTION_B, optionB[i]);
            values.put(COL_OPTION_C, optionC[i]);
            values.put(COL_OPTION_D, optionD[i]);
            values.put(COL_CORRECT, correctOption[i]);
             long insert = sqLiteDatabase.insert(QUESTION_TABLE, null, values);
             if (insert == -1){
                 Log.d(TAG, "addQuestion: operation failed");
             }else{
                 Log.d(TAG, "addQuestion: operation success");
             }
        }



    }

}