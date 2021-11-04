package com.example.lab6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Application> apps;
    String fname = "data.json";
    ListView listView;
    EditText devName;
    Application app1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExistBase(fname);
        devName = findViewById(R.id.devName);
        /*listView = findViewById(R.id.listView);
        try {
            apps = JSONHelper.importFromJSON(this);

            ArrayAdapter<Application> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, apps);
            listView.setAdapter(adapter);
        } catch (Exception e) {
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString(); // получаем текст нажатого элемента

                app1 = apps.get(position);
                info();
            }


        });

        registerForContextMenu(listView);*/
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }


    public void EditItem(int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Вы уверены что хотите изменить элемент?");
        alertDialog.setMessage("Восстановить элемент будет невозможно");

        alertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                editItem(position);
            }
        });

        alertDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    public void DeleteItem(int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Вы уверены что хотите удалить элемент?");
        alertDialog.setMessage("Восстановить элемент будет невозможно");

        alertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                deleteItem(position);
            }
        });

        alertDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit:
                EditItem(info.position);
                // метод, выполняющий действие при редактировании пункта меню
                return true;
            case R.id.delete:
                DeleteItem(info.position); //метод, выполняющий действие при удалении пункта меню
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteItem(int position) {
        try {
            List<Application> Applications = JSONHelper.importFromJSON(this);
            Applications.remove(position);
            JSONHelper.exportToJSON(this, Applications);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        try {
            apps = JSONHelper.importFromJSON(this);

            ArrayAdapter<Application> adapter = new ArrayAdapter<Application>(this,
                    android.R.layout.simple_list_item_1, apps);
            listView.setAdapter(adapter);
        } catch (Exception e) {
        }
    }

    private void editItem(int position) {
        Intent intent = new Intent(this, ChangeActivity.class);
        intent.putExtra("changeItem", position);
        startActivity(intent);
        try {
            apps = JSONHelper.importFromJSON(this);

            ArrayAdapter<Application> adapter = new ArrayAdapter<Application>(this,
                    android.R.layout.simple_list_item_1, apps);
            listView.setAdapter(adapter);
        } catch (Exception e) {
        }
    }

    private void info() {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("infoItem", app1);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private boolean ExistBase(String fname) {
        boolean rc = false;
        File f = new File(super.getFilesDir(), fname);
        Toast cr = Toast.makeText(getApplicationContext(), "Файл создан!", Toast.LENGTH_SHORT);

        if (rc = f.exists()) {

        } else {
            File f1 = new File(super.getFilesDir(), fname);
            try {
                f1.createNewFile();
                cr.show();
            } catch (IOException e) {

            }
        }
        return rc;
    }

    public void create(MenuItem item) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void search(MenuItem item) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void sort(MenuItem item) {
        try {
            List<Application> Applications = JSONHelper.importFromJSON(this);
            Collections.sort(Applications, new Comparator<Application>() {
                public int compare(Application o1, Application o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            });
            JSONHelper.exportToJSON(this, Applications);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        try {
            apps = JSONHelper.importFromJSON(this);

            ArrayAdapter<Application> adapter = new ArrayAdapter<Application>(this,
                    android.R.layout.simple_list_item_1, apps);
            listView.setAdapter(adapter);
        } catch (Exception e) {
        }
    }
}