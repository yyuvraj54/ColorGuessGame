package com.example.colorguessgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Results extends AppCompatActivity {

    TextView resultStrText;
    RecyclerView recyclerView;
    Button playAgainbtn;
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back is Disabled by Programmer!", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        Intent intent = getIntent();
        String playerName = intent.getStringExtra("username");
        String state = intent.getStringExtra("state");

        ArrayList<String> answerList = new ArrayList<>(7);
        ArrayList<String> userSelectedList = new ArrayList<>(7);
        Type type = new TypeToken<List<String>>() {
        }.getType();
        answerList = new Gson().fromJson(getIntent().getStringExtra("answerList"), type);
        userSelectedList = new Gson().fromJson(getIntent().getStringExtra("userSelectedList"), type);


        resultStrText=findViewById(R.id.resultStr);
        recyclerView=findViewById(R.id.resultRecycleView);
        playAgainbtn=findViewById(R.id.homeButton);

        if(state.equals("win")){
            resultStrText.setText("You Won: Keep It Up! "+ playerName +" You are great 🏆");
            resultStrText.setTextColor(Color.parseColor("#60B876"));
        }
        else{
            resultStrText.setText("You Loss: Don't feel bad! "+ playerName +" Try Again and prove it 😤");
            resultStrText.setTextColor(Color.parseColor("#E03A48"));
        }

        ResultRecycleViewAdapter adapter=new ResultRecycleViewAdapter(answerList,userSelectedList);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        playAgainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Results.this,MainActivity.class);
                startActivity(intent1);
            }
        });



    }
}