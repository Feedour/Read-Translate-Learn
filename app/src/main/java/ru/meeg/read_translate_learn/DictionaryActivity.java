package ru.meeg.read_translate_learn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DictionaryActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;

    public static void start(Context context){
        Intent intent = new Intent(context, DictionaryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        listView = (ListView)findViewById(R.id.dictionary_list);
        String[] s = getResources().getStringArray(R.array.temp_dictionaty_data);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s);
        listView.setAdapter(adapter);
    }
}
