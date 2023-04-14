package com.example.colorguessgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SecondScreen extends AppCompatActivity {

    TextView counterView;
    CardView boxView;
    ArrayList<String> colorNames;

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back is Disabled by Programmer!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
                                                    // Red       Green       Blue      Yellow     Purple     Orange    Gray
        colorNames = new ArrayList<>(Arrays.asList("#FF4136", "#2ECC40", "#0074D9", "#FFDC00", "#B10DC9", "#FF851B", "#AAAAAA"));
        final int[] lenght = {7};
        final int[] gameStartCounter = {3};


        Intent intent = getIntent();
        String playerName = intent.getStringExtra("playerName");

        counterView = findViewById(R.id.counter);
        boxView = findViewById(R.id.box);


        new CountDownTimer(1000 * gameStartCounter[0], 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counterView.setText("look at the below box ,Game Starting in.. " + String.valueOf(gameStartCounter[0]--));
            }

            @Override
            public void onFinish() {
                counterView.setText("Remember the colors poping in the box");
                Collections.shuffle(colorNames);
                final int[] colorIndex={0};

                new CountDownTimer(1000 * lenght[0], 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        boxView.setCardBackgroundColor(Color.parseColor(colorNames.get(colorIndex[0]++)));

                    }

                    @Override
                    public void onFinish() {
                        Intent intent1=new Intent(SecondScreen.this,ThirdScreen.class);
                        intent1.putExtra("AnswerList", new Gson().toJson(colorNames));
                        intent1.putExtra("playerName", playerName);
                        startActivity(intent1);
                    }
                }.start();


            }
        }.start();

    }
}

