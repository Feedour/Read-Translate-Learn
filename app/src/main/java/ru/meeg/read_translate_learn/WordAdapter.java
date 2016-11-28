package ru.meeg.read_translate_learn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by meeg on 28.11.16.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder>{
    private List<Word> wordList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView engTextView, ruTextView;

        public MyViewHolder(View view){
            super(view);
            engTextView = (TextView)view.findViewById(R.id.word_adapter_eng_verion);
            ruTextView = (TextView)view.findViewById(R.id.word_adapter_rus_verion);
        }
    }

    public WordAdapter(List<Word> wordList){
        this.wordList = wordList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_adapter,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Word word = wordList.get(position);
        holder.engTextView.setText(word.getEngVersion());
        holder.ruTextView.setText(word.getRuVersion());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
