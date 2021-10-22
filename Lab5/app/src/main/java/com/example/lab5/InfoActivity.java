package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class InfoActivity extends AppCompatActivity {

    Application app;
    TextView devName, appName, number, email, pubName, version, type, rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Bundle arguments = getIntent().getExtras();
        app = (Application) arguments.get("infoItem");
        devName = findViewById(R.id.devName);
        appName = findViewById(R.id.appName);
        number = findViewById(R.id.number);
        email = findViewById(R.id.mail);
        pubName = findViewById(R.id.pubName);
        version = findViewById(R.id.version);
        type = findViewById(R.id.type);
        rate = findViewById(R.id.rate);
        devName.setText(app.getDevName());
        appName.setText(app.getAppName());
        number.setText(app.getNumber());
        email.setText(app.getMail());
        pubName.setText(app.getPubName());
        version.setText(app.getVersion());
        type.setText(app.getAppType());
        rate.setText(String.valueOf(app.getAppRate()));
    }
}