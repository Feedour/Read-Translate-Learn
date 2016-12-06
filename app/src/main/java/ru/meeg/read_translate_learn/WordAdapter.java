package ru.meeg.read_translate_learn;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ru.meeg.read_translate_learn.helper.ItemTouchHelperAdapter;
import ru.meeg.read_translate_learn.helper.ItemTouchHelperViewHolder;
import ru.meeg.read_translate_learn.helper.OnStartDragListener;

/**
 * Created by meeg on 28.11.16.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder>
    implements ItemTouchHelperAdapter {

    private List<Word> wordList;

    private OnStartDragListener mDragStartListener;

    public class MyViewHolder extends RecyclerView.ViewHolder
    implements ItemTouchHelperViewHolder {
        public TextView engTextView, ruTextView;

        public MyViewHolder(View view){
            super(view);
            engTextView = (TextView)view.findViewById(R.id.word_adapter_eng_verion);
            ruTextView = (TextView)view.findViewById(R.id.word_adapter_rus_verion);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    public WordAdapter(List<Word> wordList, Context context,  OnStartDragListener dragStartListener){
        mDragStartListener = dragStartListener;
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
    public void onItemDismiss(int position) {
        wordList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(wordList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
