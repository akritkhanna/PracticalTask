package com.example.practicaltask.screens;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practicaltask.R;
import com.example.practicaltask.database.DbHelper;
import com.example.practicaltask.models.Questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.practicaltask.database.DbContract.COL_CORRECT;
import static com.example.practicaltask.database.DbContract.COL_ID;
import static com.example.practicaltask.database.DbContract.COL_OPTION_A;
import static com.example.practicaltask.database.DbContract.COL_OPTION_B;
import static com.example.practicaltask.database.DbContract.COL_OPTION_C;
import static com.example.practicaltask.database.DbContract.COL_OPTION_D;
import static com.example.practicaltask.database.DbContract.COL_QUE;
import static com.example.practicaltask.database.DbContract.QUESTION_TABLE;

public class QuizScreen extends AppCompatActivity {
    private static final String TAG = QuizScreen.class.getSimpleName();
    DbHelper dbHelper;
    private int questionCount, correctCount;
    private List<Questions> questionsList;
    private String correctAns, chosenAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        final TextView questionStatementTV = findViewById(R.id.questionTV);
        final TextView optionTV = findViewById(R.id.optionTV);
        final Button btnA = findViewById(R.id.btnA);
        final Button btnB = findViewById(R.id.btnB);
        final Button btnC = findViewById(R.id.btnC);
        final Button btnD = findViewById(R.id.btnD);
        final Button btnNxt = findViewById(R.id.btnNxt);
        final TextView scoreTV = findViewById(R.id.scoreTV);

        dbHelper = new DbHelper(QuizScreen.this);

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        questionsList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int id = random.nextInt(14);
            Log.d(TAG, "onCreate: query " + "SELECT * FROM " + QUESTION_TABLE + " WHERE " + COL_ID + "=" + id);
            try {
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + QUESTION_TABLE + " WHERE " + COL_ID + "=" + id, null);
                Questions questions = getRandomQuestions(cursor);
                cursor.close();
                questionsList.add(questions);

            } catch (Exception e) {

            } finally {

            }


        }
        int tempCount = questionCount + 1;

        questionStatementTV.setText(tempCount+" "+questionsList.get(questionCount).getQuestionStatement());
        optionTV.setText(String.format("A: %s B: %s C: %s D: %s", questionsList.get(questionCount).getOptionA(), questionsList.get(questionCount).getOptionB(), questionsList.get(questionCount).getOptionC(), questionsList.get(questionCount).getOptionD()));
        correctAns = questionsList.get(questionCount).getCorrectOption();

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenAns = questionsList.get(questionCount).getOptionA();
                if (chosenAns.equalsIgnoreCase(correctAns)) {
                    correctCount++;
                    scoreTV.setText(String.format("Score :%s", correctCount));
                    btnA.setBackgroundColor(0xFF008000);
                } else {
                    btnA.setBackgroundColor(0xFFED2626);

                }
                btnB.setEnabled(false);
                btnC.setEnabled(false);
                btnD.setEnabled(false);
                //btnA.setBackgroundResource(android.R.drawable.btn_default);
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenAns = questionsList.get(questionCount).getOptionB();
                if (chosenAns.equalsIgnoreCase(correctAns)) {
                    btnB.setBackgroundColor(0xFF008000);
                    correctCount++;
                    scoreTV.setText(String.format("Score :%s", correctCount));
                } else {
                    btnB.setBackgroundColor(0xFFED2626);
                }
                btnA.setEnabled(false);
                btnC.setEnabled(false);
                btnD.setEnabled(false);
                //btnA.setBackgroundResource(android.R.drawable.btn_default);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenAns = questionsList.get(questionCount).getOptionC();
                if (chosenAns.equalsIgnoreCase(correctAns)) {
                    btnC.setBackgroundColor(0xFF008000);
                    correctCount++;
                    scoreTV.setText(String.format("Score :%s", correctCount));
                } else {
                    btnC.setBackgroundColor(0xFFED2626);
                }
                btnB.setEnabled(false);
                btnA.setEnabled(false);
                btnD.setEnabled(false);
                //btnA.setBackgroundResource(android.R.drawable.btn_default);
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenAns = questionsList.get(questionCount).getOptionD();
                if (chosenAns.equalsIgnoreCase(correctAns)) {
                    btnD.setBackgroundColor(0xFF008000);
                    correctCount++;
                    scoreTV.setText(String.format("Score :%s", correctCount));
                } else {
                    btnD.setBackgroundColor(0xFFED2626);
                }
                btnB.setEnabled(false);
                btnC.setEnabled(false);
                btnA.setEnabled(false);
                //btnA.setBackgroundResource(android.R.drawable.btn_default);
            }
        });

        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionCount < 9) {
                    questionCount++;
                    int tempCount = questionCount + 1;
                    questionStatementTV.setText(tempCount+" "+questionsList.get(questionCount).getQuestionStatement());
                    optionTV.setText(String.format("A: %s B: %s C: %s D: %s", questionsList.get(questionCount).getOptionA(), questionsList.get(questionCount).getOptionB(), questionsList.get(questionCount).getOptionC(), questionsList.get(questionCount).getOptionD()));
                    correctAns = questionsList.get(questionCount).getCorrectOption();
                    btnA.setBackgroundResource(android.R.drawable.btn_default);
                    btnB.setBackgroundResource(android.R.drawable.btn_default);
                    btnC.setBackgroundResource(android.R.drawable.btn_default);
                    btnD.setBackgroundResource(android.R.drawable.btn_default);
                    btnA.setEnabled(true);
                    btnB.setEnabled(true);
                    btnC.setEnabled(true);
                    btnD.setEnabled(true);
                    Log.d(TAG, "onClick: " + questionCount);
                } else {
                    Intent toScoreScreen = new Intent(QuizScreen.this, ScoreScreen.class);
                    toScoreScreen.putExtra("score", correctCount);
                    startActivity(toScoreScreen);
                }
            }
        });

    }

    private Questions getRandomQuestions(Cursor cursor) {
        cursor.moveToFirst();
        Questions questions = new Questions();
        try {
            questions.setQuestionStatement(cursor.getString(cursor.getColumnIndex(COL_QUE)));
            questions.setOptionA(cursor.getString(cursor.getColumnIndex(COL_OPTION_A)));
            questions.setOptionB(cursor.getString(cursor.getColumnIndex(COL_OPTION_B)));
            questions.setOptionC(cursor.getString(cursor.getColumnIndex(COL_OPTION_C)));
            questions.setOptionD(cursor.getString(cursor.getColumnIndex(COL_OPTION_D)));
            questions.setCorrectOption(cursor.getString(cursor.getColumnIndex(COL_CORRECT)));

        } catch (Exception e) {
            Log.d(TAG, "getRandomQuestions: " + e.getLocalizedMessage());
        } finally {
            cursor.close();
        }


        return questions;
    }
}