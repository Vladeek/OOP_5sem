package com.example.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void search(View view) {
        try {
            EditText et = findViewById(R.id.search);
            TextView out = findViewById(R.id.list);
            String text = et.getText().toString();
            out.setText("");
            String outtext = "";
            List<Application> lp = openFromExt();
            for (Application itVar : lp) {
                if (text.equals(itVar.getDevName()) || text.equals(itVar.getPubName()))
                    outtext += itVar.toString() + "\n";
            }
            out.setText(outtext);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public List<Application> openFromExt() {
        List<Application> ApplicationList = new ArrayList<Application>();
        try {
            ApplicationList = JSONHelper.importFromJSON(this);
            TextView out = (TextView) findViewById(R.id.list);
            String text = new String(ApplicationList != null ? ApplicationList.toString() : "");
            out.setText(text);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return ApplicationList;

    }

    public void prevAct(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}