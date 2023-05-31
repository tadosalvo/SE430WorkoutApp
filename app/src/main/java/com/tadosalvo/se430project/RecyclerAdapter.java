package com.tadosalvo.se430project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Term> terms;

    public RecyclerAdapter(ArrayList<Term> terms){
        this.terms = terms;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView termTxt;

        public MyViewHolder(final View view){
            super(view);
            termTxt = view.findViewById(R.id.txt_row_item);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String term = terms.get(position).getTerm() + " - " + terms.get(position).getAbout();
        holder.termTxt.setText(term);
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }
}
