package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class FourthActivity extends AppCompatActivity  {

    Application app;
    private List<Application> apps;
    TextView devName, appName, number, email, pubName, version, type, rate;
    static final int GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        Bundle arguments = getIntent().getExtras();
        app = (Application) arguments.get("thirdAct");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(bitmap);
                }
        }
    }

    public void prevAct(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void call(View view) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void mail(View view) {
        String mailstr = email.getText().toString();
        Intent intent2 = new Intent(Intent.ACTION_SEND);
        intent2.setData(Uri.parse("mailto:"));
        intent2.setType("plain/text");
        intent2.putExtra(Intent.EXTRA_EMAIL, new String[]{mailstr});
        startActivity(intent2);
    }

    public void soc(View view) {
        String socstr = pubName.getText().toString();
            Uri uriUrl = Uri.parse(socstr);
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
    }

    public void upload(View v) {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }
}