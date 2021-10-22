package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView out;
    EditText appName, version;
    Application app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = new Application();
        appName = findViewById(R.id.appName);
        version = findViewById(R.id.version);
        /*appName.setText(app.getAppName());
        version.setText(app.getVersion());*/
        out = findViewById(R.id.out);
        out.setText(app.toString());
    }

    public void nextAct(View view) {
        getInfo();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("firstAct", app);
        startActivity(intent);
    }

    public void getInfo(){
        app.setAppName(appName.getText().toString());
        app.setVersion(version.getText().toString());

    }
}