package com.example.colorguessgame;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ThirdScreen extends AppCompatActivity {

    Button redBtnView,blueBtnView,greenBtnView,yellowBtnView,purpleBtnView,orangeBtnView,grayBtnView , submitBtn , clearAnsBtn,playAgainBtn;
    TextView checkError;
    Dialog dialog;

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back is Disabled by Programmer!", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);

        final int[] colorCounter = {1};
        ArrayList<String> userSelection=new ArrayList<>(7);


        ArrayList<String> answerList = new ArrayList<>(7);

        Type type = new TypeToken<List<String>>() {
        }.getType();
        answerList = new Gson().fromJson(getIntent().getStringExtra("AnswerList"), type);

        Intent intent = getIntent();
        String playerName = intent.getStringExtra("playerName");

        redBtnView=findViewById(R.id.red);
        blueBtnView=findViewById(R.id.blue);
        greenBtnView=findViewById(R.id.green);
        yellowBtnView=findViewById(R.id.yellow);
        purpleBtnView=findViewById(R.id.purple);
        orangeBtnView=findViewById(R.id.orange);
        grayBtnView=findViewById(R.id.Gray);
        submitBtn=findViewById(R.id.submit);
        clearAnsBtn=findViewById(R.id.clear);
        checkError=findViewById(R.id.errorCheck);
        playAgainBtn=findViewById(R.id.playAgain);
        dialog=new Dialog(ThirdScreen.this);

        submitBtn.setEnabled(false);
        playAgainBtn.setVisibility(View.INVISIBLE);



        redBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int status=markButton(redBtnView, colorCounter[0]);
                if(status==1){
                    colorCounter[0]++;
                    userSelection.add("#FF4136");
                }
//
            }
        });

        blueBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 int status=markButton(blueBtnView, colorCounter[0]);
                if(status==1){
                    colorCounter[0]++;
                    userSelection.add("#0074D9");
                }
            }
        });

        greenBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 int status=markButton(greenBtnView, colorCounter[0]);
                if(status==1){
                    colorCounter[0]++;
                    userSelection.add("#2ECC40");
                }
            }
        });

        yellowBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int status=markButton(yellowBtnView, colorCounter[0]);
                if(status==1){
                    colorCounter[0]++;
                    userSelection.add("#FFDC00");

                }

            }
        });

        purpleBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 int status=markButton(purpleBtnView, colorCounter[0]);
                if(status==1){
                    colorCounter[0]++;
                    userSelection.add("#B10DC9");
                }
            }
        });

        orangeBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 int status=markButton(orangeBtnView, colorCounter[0]);
                if(status==1){
                    colorCounter[0]++;
                    userSelection.add("#FF851B");
                }
            }
        });

        grayBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int status=markButton(grayBtnView, colorCounter[0]);
                if(status==1){
                    colorCounter[0]++;
                    userSelection.add("#AAAAAA");
                }
            }
        });



        clearAnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCounter[0]=1;
                resetAnswer();
                userSelection.clear();
            }
        });


        ArrayList<String> finalAnswerList = answerList;
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(finalAnswerList.equals(userSelection)){
                    showDialog("win",playerName,finalAnswerList,userSelection);
                }
                else{
                    showDialog("loss",playerName,finalAnswerList,userSelection);
                }
                clearAnsBtn.setEnabled(false);
                playAgainBtn.setVisibility(View.VISIBLE);
            }
        });

        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 =new Intent(ThirdScreen.this,MainActivity.class);
                startActivity(intent2);
            }
        });


    }

    public int isSelected(String  buttonString){

        if(buttonString.equals("-")){
            checkError.setText("");
            checkError.setTextColor(Color.parseColor("#00FF00"));
            return 0;
        }
        else{
            checkError.setText("button alredy selected please select other button ! or click on reset");
            checkError.setTextColor(Color.parseColor("#FF0000"));
            return 1;
        }
    }

    public int markButton(Button button, int i){
        if(i==7){submitBtn.setEnabled(true);}
        String buttonString =button.getText().toString();
        if(isSelected(buttonString)==0){
            button.setText(String.valueOf(i));
            return 1;
        }
        return 0;
    }


    public void resetAnswer(){
        redBtnView.setText("-");
        blueBtnView.setText("-");
        greenBtnView.setText("-");
        yellowBtnView.setText("-");
        purpleBtnView.setText("-");
        orangeBtnView.setText("-");
        grayBtnView.setText("-");
        submitBtn.setEnabled(false);

    }

    public void showDialog(String status,String username ,ArrayList<String> answerlist ,ArrayList<String> userselceted  ){
        dialog.setContentView(R.layout.popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView winImage=dialog.findViewById(R.id.popUpImageView);
        CardView cardView=dialog.findViewById(R.id.cardbackgroud);
        TextView statusinfo=dialog.findViewById(R.id.staus);
        TextView usernameView=dialog.findViewById(R.id.usernameTextView);
        Button fullResult=dialog.findViewById(R.id.fullResultBtn);

        if(status.equals("win")){
            winImage.setImageResource(R.drawable.win);
            cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.yellow));

            statusinfo.setText("Congratulation!");
            statusinfo.setTextColor(Color.parseColor("#FF000000"));
            usernameView.setText(username+", You win..! 🏆");
            usernameView.setTextColor(Color.parseColor("#FF000000"));

        }
        else{
            winImage.setImageResource(R.drawable.loss);
            cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red));
            statusinfo.setText("Oops..Try Again!");
            usernameView.setText(username+", you lose 😢");
        }

        fullResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThirdScreen.this,Results.class);
                intent.putExtra("username",username);
                intent.putExtra("state",status);
                intent.putExtra("answerList", new Gson().toJson(answerlist));
                intent.putExtra("userSelectedList", new Gson().toJson(userselceted));
                startActivity(intent);
            }
        });

        dialog.show();

    }
}