package ru.meeg.read_translate_learn;

import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.res.Configuration;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, FontSizeDialogFragment.FontSizeDialogListener{


    private TextView textView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_layout);
        Log.d("TAG","вызван метод onCreate()");

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout)findViewById(R.id.navigation_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        textView = (TextView)findViewById(R.id.main_textView);
        textView.setText(R.string.temp_main_text);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.main_menu_item_day_mode: {
                Log.d("TAG_CLICK", "Пункт меню: смена режима дня/ночи");

                int currentNightMode = getResources().getConfiguration().uiMode
                        & Configuration.UI_MODE_NIGHT_MASK;
                setNightMode(currentNightMode);
                return true;
            }
            case R.id.main_menu_item_font: {
                Log.d("TAG_CLICK", "Пункт меню: смена размера шрифта");

                showFontSizeDialog();
                //textView.setTextSize();

                return true;
            }
            case R.id.main_menu_item_dictionary: {
                Log.d("TAG_CLICK", "Пункт меню: открыть словарь");

                DictionaryActivity.start(this);

                return true;
            }
            case R.id.home:{
                drawer.openDrawer(GravityCompat.START);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void setNightMode(int currentNightMode){
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:{
                getDelegate().setLocalNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);
                break;
            }
            case Configuration.UI_MODE_NIGHT_YES:{
                getDelegate().setLocalNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);
                break;
            }
        }
    }

    public void showFontSizeDialog(){
        DialogFragment dialog = new FontSizeDialogFragment();
        dialog.show(getSupportFragmentManager(),"FontSizeFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, EditText editText) {

        if (dialog != null && editText != null) {
            int value = Integer.valueOf(editText.getText().toString());

            if (value > 0) {
                if (value > 100){
                    value = 100;
                }
            } else {
                value = 24;
            }
            textView.setTextSize((float) value);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main_nav_menu_files) {
            Log.d("TAG","В NavigationDrawer выбран пункт files");

        } else if (id == R.id.main_nav_menu_words){
            Log.d("TAG","В NavigationDrawer выбран пункт words");
            DictionaryActivity.start(this);

        } else if (id == R.id.main_nav_menu_settings){
            Log.d("TAG","В NavigationDrawer выбран пункт settings");

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
