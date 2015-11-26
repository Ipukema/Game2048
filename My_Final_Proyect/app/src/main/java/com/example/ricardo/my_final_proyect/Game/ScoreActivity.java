package com.example.ricardo.my_final_proyect.Game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricardo.my_final_proyect.MainActivity;
import com.example.ricardo.my_final_proyect.R;

public class ScoreActivity extends AppCompatActivity {
    int random_score = (int) (Math.random() * 5000) + 2;
    TextView generatedScore;
    EditText NewPlayerName;
    private SharedPreferences gameHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        generatedScore = (TextView) findViewById(R.id.score_random);
        NewPlayerName = (EditText) findViewById(R.id.person_name);
        gameHistory = this.getSharedPreferences("gameHistory", Context.MODE_PRIVATE);
        if(gameHistory.getBoolean("isSaved", false)) {
            random_score = gameHistory.getInt("highScore", 0);
        }
        generatedScore.setText(String.valueOf(random_score));
    }
    public void saveScore(View view) {
        Intent intentSave =new Intent(this,MainActivity.class);
        String newScore = generatedScore.getText().toString();
        String playerName = NewPlayerName.getText().toString();
        intentSave.putExtra("MESSAGE", playerName);
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name", playerName);
        editor.putString("Score", newScore);
        editor.commit();
        setResult(2, intentSave);
        finish();
        Toast.makeText(this, "Score was saved succesfully ", Toast.LENGTH_LONG).show();
        }
    }

