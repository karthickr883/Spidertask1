package com.example.karthik.experiment;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    private long millisec=15000;
    TextView t;
    
    long stopmilli,millisecs; ;


    boolean stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = (TextView) findViewById(R.id.countdown);

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            millisec = millisec-1000;

                            if (millisec >= 0) {
                                updatetext();

                                handler.postDelayed(this,1000);

                            } else if (millisec< 0)
                                countdown();
                        }
                    };
                    handler.postDelayed(r, 0);

            }

        });
    }

    private void countdown() {
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop = true;

            }
        });
        stopmilli= SystemClock.uptimeMillis();
        final Handler h = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                millisecs = SystemClock.uptimeMillis() - stopmilli;
                if (stop != true) {
                    updatestoptext();
                    h.postDelayed(this, 0);
                }
            }
        };
        h.postDelayed(r, 0);

    }

    private void updatetext() {

        t.setText(String.valueOf((int) millisec/1000));
    }

    private void updatestoptext() {
        int seconds = (int) millisecs / 1000;
        int minutes = (seconds) / 60;
        int secs = seconds % 60;
        int milli = (int) millisecs % 1000;
        String s = String.format("%02d:%02d:%03d", minutes, secs,milli);
        t.setText(s);

    }
}
