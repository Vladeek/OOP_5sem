package rudenia.fit.bstu.lab13;

import android.content.Intent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
//1. Кадр за кадром
//2. Анимация свойств объекта (aka Property Animator)
//3. Анимация View (aka View animation)
//4. Анимация векторных ресурсов (aka AnimatedVectorDrawable)


public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashscreenActivity.this, MainActivity.class));
                finish();
            }
        }, 3*10000);
    }
}