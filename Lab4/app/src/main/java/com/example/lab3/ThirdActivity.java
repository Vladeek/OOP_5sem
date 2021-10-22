package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
    Application app1;



    // получаем экземпляр элемента ListView
    ListView listView;
    //TextView outAll;
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
        //outAll = (TextView) findViewById(R.id.outAll);

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
        listView  = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString(); // получаем текст нажатого элемента

                app1 = apps.get(position);
                openNewAct();
            }


        });

        try{
            apps = JSONHelper.importFromJSON(this);

            // используем адаптер данных
            ArrayAdapter<Application> adapter1 = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, apps);
            listView.setAdapter(adapter1);
        }catch(Exception e){
            Toast.makeText(this, "Файл отсутствует", Toast.LENGTH_LONG).show();
        }
    }





    public void openNewAct() {

        Intent intent1 = new Intent(this, FourthActivity.class);
        intent1.putExtra("thirdAct", app1);
        startActivity(intent1);
    }
   /* public void getInfo(){
        app.setDevName(devName.getText().toString());
        app.setPubName(pubName.getText().toString());
        app.setMail(mail.getText().toString());
        app.setNumber(number.getText().toString());
    }*/
   /* public void nextAct(View view) {
        //getInfo();
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("thirdAct", app);
        startActivity(intent);
    }*/
    public void prevAct(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void Save(View view) {
        app.setAppType(spinner.getSelectedItem().toString());
        app.setAppRate((float)seekBar.getProgress()/(float)10.0);

        if(apps == null)
            apps = new ArrayList<Application>();
        Application app1 = new Application(app.getAppName(), app.getVersion(), app.getDevName(), app.getAppType(), app.getPubName(), app.getAppRate(), app.getMail(), app.getNumber());
        apps.add(app1);
        boolean result = JSONHelper.exportToJSON(this, apps);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }

        apps = JSONHelper.importFromJSON(this);

        // используем адаптер данных
        ArrayAdapter<Application> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, apps);

        listView.setAdapter(adapter);

        /*Intent intent = new Intent(this, FourthActivity.class);
        startActivity(intent);*/
    }

}