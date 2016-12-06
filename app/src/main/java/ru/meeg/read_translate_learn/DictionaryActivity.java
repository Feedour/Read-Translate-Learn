package ru.meeg.read_translate_learn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;

import java.util.ArrayList;

import ru.meeg.read_translate_learn.helper.OnStartDragListener;
import ru.meeg.read_translate_learn.helper.SimpleItemTouchHelperCallback;

public class DictionaryActivity extends AppCompatActivity
    implements OnStartDragListener {

    private RecyclerView recyclerView;
    private WordAdapter adapter;
    private Toolbar toolbar;
    private ItemTouchHelper mitemTouchHelper;

    public static void start(Context context){
        Intent intent = new Intent(context, DictionaryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.dictionary_rv);

        String[] s_eng = getResources().getStringArray(R.array.temp_dictionaty_data_eng);
        String[] s_rus = getResources().getStringArray(R.array.temp_dictionaty_data_rus);
        
        ArrayList<Word> wordList= new ArrayList<Word>();
        int len = s_eng.length;
        for (int i = 0; i < len; i++){
           wordList.add(new Word(s_eng[i],s_rus[i]));
        }

        adapter = new WordAdapter(wordList, this, this );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mitemTouchHelper = new ItemTouchHelper(callback);
        mitemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mitemTouchHelper.startDrag(viewHolder);
    }
}
