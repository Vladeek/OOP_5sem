package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView out;
    EditText devName, pubName, number, mail;
    Application app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle arguments = getIntent().getExtras();
        app = (Application) arguments.get("firstAct");
        devName = findViewById(R.id.devName);
        pubName = findViewById(R.id.pubName);
        number = findViewById(R.id.number);
        mail = findViewById(R.id.email);
        out = findViewById(R.id.out);
        out.setText(app.toString());
    }

    public void nextAct(View view) {
        getInfo();
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("secAct", app);
        startActivity(intent);
    }

    public void prevAct(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void getInfo(){
        app.setDevName(devName.getText().toString());
        app.setPubName(pubName.getText().toString());
        app.setMail(mail.getText().toString());
        app.setNumber(number.getText().toString());
    }
}