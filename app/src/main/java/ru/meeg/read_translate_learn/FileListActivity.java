package ru.meeg.read_translate_learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FileListActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);

        listView = (ListView)findViewById(R.id.file_list_rv);
        String s[] = getResources().getStringArray(R.array.temp_file_list_data);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,s);
        listView.setAdapter(arrayAdapter);
    }
}
