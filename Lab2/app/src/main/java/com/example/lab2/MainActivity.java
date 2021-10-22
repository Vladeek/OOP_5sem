package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String[] list = { "Малоподвижный образ жизни", "Обычная физнагрузка", "Интенсивная нагрузка"};
    String sex = null;
    private static int DELTA_VALUE = 1;
    private SeekBar seekBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.seekBarValue);
        textView.setText("18");
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                textView.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                textView.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onRadioButtonClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.m:
                if (checked){
                        sex = "man";
                }
                break;
            case R.id.w:
                if (checked){
                    sex = "woman";
                }
                break;
        }
    }

    public void result()
    {
        EditText weight = findViewById(R.id.wei);
        EditText height = findViewById(R.id.hei);

        String heighthelp = height.getText().toString();
        String weighthelp = weight.getText().toString();

        double heightres = Double.parseDouble(heighthelp);
        double weightres = Double.parseDouble(weighthelp);

        double bmr;
        double amr;

        SeekBar seekBar = findViewById(R.id.seekBar);
        if (sex=="woman"){
            bmr = 655.0955 + (9.5634 * weightres) + (1.8496 * heightres) - (4.6756 * (double) seekBar.getProgress());
        }
        else {
            bmr = 66.4730 + (13.7516 * weightres) + (5.0033 * heightres) - (6.7550 * (double) seekBar.getProgress());
        }
        Spinner spinner = findViewById(R.id.spinner);
        String listitem = spinner.getSelectedItem().toString();

        switch(listitem) {
            case "Малоподвижный образ жизни":
                amr = 1.2;
                break;
            case "Обычная физнагрузка":
                amr = 1.55;
                break;
            case "Интенсивная нагрузка":
                amr = 1.725;
                break;

            default:
                amr = 1;
        }
        TextView result = findViewById(R.id.textView4);
        int res = (int) (bmr * amr);
        result.setText("Ваша суточная норма Ккал.: " + res);
    }

    public void resultClick(View view)
    {

        Toast dcr = Toast.makeText(getApplicationContext(),"Не все поля заполнены!", Toast.LENGTH_SHORT);
        try {
            result();
        }
        catch(Exception e) {
            dcr.show();
        }
    }

    public void decrease(View view)
    {
        SeekBar seekBar = findViewById(R.id.seekBar);
        int progress = seekBar.getProgress();
        if (progress - DELTA_VALUE < 0){
            seekBar.setProgress(18);
        }
        else {
            seekBar.setProgress(progress - DELTA_VALUE);
        }
    }

    public void increase(View view)
    {
        SeekBar seekBar = findViewById(R.id.seekBar);
        int progress = seekBar.getProgress();
        if (progress + DELTA_VALUE > 100){
            seekBar.setProgress(18);
        }
        else {
            seekBar.setProgress(progress + DELTA_VALUE);
        }
    }
}