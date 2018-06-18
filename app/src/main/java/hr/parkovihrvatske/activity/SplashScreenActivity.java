package hr.parkovihrvatske.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.Objects;

import butterknife.ButterKnife;
import hr.parkovihrvatske.R;
import hr.parkovihrvatske.persistence.Session;
import hr.parkovihrvatske.utility.Helper;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        final Session session = new Session(this);
        session.setSlideshow(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (session.isLoggedIn()) {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, Helper.SPLASH_DURATION);
    }


}
