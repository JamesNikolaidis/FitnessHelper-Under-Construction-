package com.applicationforlife.jamesnikolaidis.myfitnesshelper;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.applicationforlife.jamesnikolaidis.myfitnesshelper.HELPERS_Classes.MyAsyncManager;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Panels.MainPanel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_application_picture);



           new MyAsyncManager().execute();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                Intent passenger = new Intent(MainActivity.this,MainPanel.class);
                startActivity(passenger);
            }
        }, 500);


    }



}
