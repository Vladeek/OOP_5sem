package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {
    String[] list = { "Игра", "Банковское приложение", "Социальные сети", "Контентные сервисы"};
    private List<Application> apps;
    TextView out;
    Application app;
    SeekBar seekBar;
    Spinner spinner;
    TextView outAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Bundle arguments = getIntent().getExtras();
        app = (Application) arguments.get("secAct");
        TextView textView = findViewById(R.id.seekText);
        textView.setText("0");
        out = findViewById(R.id.out);
        out.setText(app.toString());
        outAll = (TextView) findViewById(R.id.outAll);
        seekBar = findViewById(R.id.seekAppRate);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                textView.setText(String.valueOf((float)progress/(float)10.0));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                textView.setText(String.valueOf((float)seekBar.getProgress()/(float)10.0));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                textView.setText(String.valueOf((float)seekBar.getProgress()/(float)10.0));
            }
        });
        spinner = findViewById(R.id.appType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        try{
            apps = JSONHelper.importFromJSON(this);
            TextView outAll = (TextView) findViewById(R.id.outAll);
            outAll.setText(apps.toString());
        }catch(Exception e){
            Toast.makeText(this, "Файл отсутствует", Toast.LENGTH_LONG).show();
        }
    }
    public void prevAct(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void Save(View view) {
        app.setAppType(spinner.getSelectedItem().toString());
        app.setAppRate((float)seekBar.getProgress()/(float)10.0);

        if(apps == null)
            apps = new ArrayList<Application>();
        Application app1 = new Application(app.getAppName(), app.getVersion(), app.getDevName(), app.getAppType(), app.getPubName(), app.getAppRate());
        apps.add(app1);
        boolean result = JSONHelper.exportToJSON(this, apps);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }

        apps = JSONHelper.importFromJSON(this);
        outAll.setText(apps.toString());
    }

}