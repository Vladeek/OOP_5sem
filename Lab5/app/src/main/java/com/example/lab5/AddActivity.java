package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    String[] list = {"Игра", "Банковское приложение", "Социальные сети", "Контентные сервисы"};
    private List<Application> apps;
    EditText devName, appName, number, email, pubName, version;
    Application app;
    Spinner spinner;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        try {
            apps = JSONHelper.importFromJSON(this);
        } catch (Exception e) {
        }
        app = new Application();
        devName = findViewById(R.id.devName);
        appName = findViewById(R.id.appName);
        number = findViewById(R.id.number);
        email = findViewById(R.id.mail);
        pubName = findViewById(R.id.pubName);
        version = findViewById(R.id.version);
        TextView textView = findViewById(R.id.seekText);
        textView.setText("0");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = findViewById(R.id.appType);
        spinner.setAdapter(adapter);
        seekBar = findViewById(R.id.seekAppRate);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf((float) progress / (float) 10.0));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView.setText(String.valueOf((float) seekBar.getProgress() / (float) 10.0));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText(String.valueOf((float) seekBar.getProgress() / (float) 10.0));
            }
        });
    }

    public void add(View view) {
        getInfo();
        if (apps == null)
            apps = new ArrayList<Application>();
        Application app1 = new Application(app.getAppName(), app.getVersion(), app.getDevName(), app.getAppType(), app.getPubName(), app.getAppRate(), app.getMail(), app.getNumber());
        apps.add(app1);
        boolean result = JSONHelper.exportToJSON(this, apps);
        if (result) {
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }

    public void getInfo() {
        app.setAppName(appName.getText().toString());
        app.setVersion(version.getText().toString());
        app.setDevName(devName.getText().toString());
        app.setAppType(spinner.getSelectedItem().toString());
        app.setPubName(pubName.getText().toString());
        app.setAppRate((float) seekBar.getProgress() / (float) 10.0);
        app.setMail(email.getText().toString());
        app.setNumber(number.getText().toString());

    }
}