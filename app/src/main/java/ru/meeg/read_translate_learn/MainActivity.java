package ru.meeg.read_translate_learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView; //используется в смене размера шрифта и добавлении слов в словарь

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.main_textView);
        textView.setText(R.string.temp_main_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.main_menu_item_day_mode: {
                Log.d("TAG_CLICK", "Пункт меню: смена режима дня/ночи");

                return true;
            }
            case R.id.main_menu_item_font: {
                Log.d("TAG_CLICK", "Пункт меню: смена размера шрифта");

                //textView.setTextSize();

                return true;
            }
            case R.id.main_menu_item_dictionary: {
                Log.d("TAG_CLICK", "Пункт меню: открыть словарь");

                DictionaryActivity.start(this);

                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
