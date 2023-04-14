package com.example.colorguessgame;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultRecycleViewAdapter extends RecyclerView.Adapter<ResultRecycleViewAdapter.MyViewHolder> {

    public ArrayList<String> answer;
    public ArrayList<String> userSelection;


    public ResultRecycleViewAdapter(ArrayList<String> answer, ArrayList<String> userSelection ) {
        this.answer = answer;
        this.userSelection = userSelection;
    }

    @NonNull
    @Override
    public ResultRecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.result_block,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultRecycleViewAdapter.MyViewHolder holder, int position) {

        String correct=answer.get(position).toString();
        String useroption=userSelection.get(position).toString();
        if(correct.equals(useroption)){
            holder.lossOrwin.setImageResource(R.drawable.correctans);
        }
        else{
            holder.lossOrwin.setImageResource(R.drawable.wrongans);
        }

        holder.correctCard.setCardBackgroundColor(Color.parseColor(correct));
        holder.userSelectedCard.setCardBackgroundColor(Color.parseColor(useroption));
    }

    @Override
    public int getItemCount() {
        return answer.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public CardView correctCard;
        public CardView userSelectedCard;
        public ImageView lossOrwin;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            correctCard=itemView.findViewById(R.id.correctAnsCard);
            userSelectedCard=itemView.findViewById(R.id.userSelectedCard);
            lossOrwin=itemView.findViewById(R.id.lossOrWinImage);

        }
    }
}
