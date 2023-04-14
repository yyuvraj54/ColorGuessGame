package com.example.colorguessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button startbtn;
    EditText nameView;
    TextView errorInName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbtn=findViewById(R.id.startbtn);
        nameView=findViewById(R.id.Namefield);
        errorInName=findViewById(R.id.nameError);


        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name =nameView.getText().toString();

                if(name.equals("")){
                    errorInName.setText("Please enter a name to Start!");
                    errorInName.setTextColor(Color.parseColor("#FF0000"));
                }
                else{
                    errorInName.setText("Lets Go");
                    errorInName.setTextColor(Color.parseColor("#00FF00"));

                    Intent intent=new Intent(MainActivity.this,SecondScreen.class);
                    intent.putExtra("playerName", name);
                    startActivity(intent);

                }

            }
        });



    }






}