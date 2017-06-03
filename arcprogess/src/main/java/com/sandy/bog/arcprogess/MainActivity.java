package com.sandy.bog.arcprogess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private DetectorCirclePanel detectorCirclePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        detectorCirclePanel = (DetectorCirclePanel) findViewById(R.id.progress);


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    try {

                        Thread.sleep(500);
                        final int finalI = i ;
                        Log.d("Sandy", "progress:" + finalI);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                detectorCirclePanel.update(finalI);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();



    }
}
