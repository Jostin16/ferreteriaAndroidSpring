package idat.com.appferreteria.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import idat.com.minimarketapispring.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        final Intent intentMain = new Intent(this, LoginActivity.class);
        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                }
                catch (InterruptedException ex){

                }
                finally {
                    startActivity(intentMain);
                    finish();
                }
            }
        };
        timer.start();
    }
}