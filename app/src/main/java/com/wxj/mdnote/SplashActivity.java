package com.wxj.mdnote;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(new CheckTask()).start();
    }


    private class CheckTask implements Runnable{

        @Override
        public void run() {
            SystemClock.sleep(2000);
            startActivity(new Intent(getBaseContext(),NoteListActivity.class));
            finish();
        }
    }
}
