package ru.meeg.read_translate_learn;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;

/**
 * Created by meeg on 20.11.16.
 */

public class FontSizeScrollView extends LinearLayout
implements  SeekBar.OnSeekBarChangeListener{

    private Button plsButton;
    private Button mnsButton;
    private EditText editText;
    private SeekBar seekBar;

    private int maxFontSize = 255;
    private int minFontSize = 0;

    public FontSizeScrollView(Context context){
        this(context, null);
    }

    public FontSizeScrollView(Context context, AttributeSet attrs){
        super(context, attrs);
        inflate(context, R.layout.font_size_scroll, this);
        init();
        Log.d("TAG_FSize", "Создано окно смены размера шрифта");
    }

    private void init(){
        this.plsButton = (Button)findViewById(R.id.font_size_scroll_plusBtn);
        plsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                incrementValue();
            }
        });

        this.mnsButton = (Button)findViewById(R.id.font_size_scroll_minusBtn);
        mnsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                decrementValue();
            }
        });

        this.editText = (EditText) findViewById(R.id.font_size_scroll_text);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                try {
                    int editTextValue = Integer.parseInt(editText.getText().toString());
                    editTextValue = putInRange(editTextValue);
                    /*
                        Необходимо для тех случаев, когда повторно воодится значение, больше
                        максимума, вследствие чего не срабатывает onProgressChanged
                    */
                    if (editTextValue == seekBar.getProgress()){
                        editText.setText(String.valueOf(editTextValue));
                    }
                    seekBar.setProgress(editTextValue);
                    return true;
                }
                catch (Throwable t){
                    seekBar.setProgress(0);
                    Log.d("TAG_FSize","ERROR: " + t.getMessage());
                    return false;
                }
            }
        });

        this.seekBar = (SeekBar)findViewById(R.id.font_size_scroll_seek_bar);
        this.seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        editText.setText(String.valueOf(seekBar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        editText.setText(String.valueOf(seekBar.getProgress()));
    }

    public void setFontSize(int value){

        seekBar.setProgress(putInRange(value));
    }

    public int getFontSize(){
        return this.seekBar.getProgress();
    }

    private void incrementValue(){
        seekBar.incrementProgressBy(1);
    }

    private  void decrementValue(){
        seekBar.incrementProgressBy(-1);
        }

    private int putInRange(int value){
        if (value < minFontSize)
            value = minFontSize;
        else if (value > maxFontSize)
            value = maxFontSize;
        return value;
    }
}
