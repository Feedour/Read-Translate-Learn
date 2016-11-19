package ru.meeg.read_translate_learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

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

                return true;
            }
            case R.id.main_menu_item_dictionary: {
                Log.d("TAG_CLICK", "Пункт меню: открыть словарь");

                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
