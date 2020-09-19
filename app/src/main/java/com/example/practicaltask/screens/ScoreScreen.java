package com.example.practicaltask.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practicaltask.R;

public class ScoreScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        TextView scoreTV = findViewById(R.id.scoreTV);
        TextView messageTV = findViewById(R.id.messageTV);

        Intent intent = getIntent();

        int score = intent.getIntExtra("score", 0);

        scoreTV.setText(String.valueOf(score));

        if (score == 10) {
            messageTV.setText("Awesome. You are Genius. Congratulations you won the Game");
            messageTV.setTextColor(getResources().getColor(R.color.green));
        } else if (score == 9) {
            messageTV.setText("You Won! Congratulations and Well Done");
            messageTV.setTextColor(getResources().getColor(R.color.green));
        } else if (score == 7) {
            messageTV.setText("You Won! Congratulations.");
            messageTV.setTextColor(getResources().getColor(R.color.green));
        } else if (score == 5) {
            messageTV.setText("You Won!");
            messageTV.setTextColor(getResources().getColor(R.color.green));
        } else if (score <= 4) {
            messageTV.setText("Well played but you failed. All The Best for Next Game.");
            messageTV.setTextColor(getResources().getColor(R.color.red));
        }

    }
}