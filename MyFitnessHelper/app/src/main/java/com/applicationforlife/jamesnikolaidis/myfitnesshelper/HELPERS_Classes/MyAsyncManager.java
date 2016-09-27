package com.applicationforlife.jamesnikolaidis.myfitnesshelper.HELPERS_Classes;

import android.app.Activity;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * Created by James Nikolaidis on 8/23/2016.
 */
public class MyAsyncManager extends AsyncTask<Void,Void,Boolean> {
   private Thread thread1,thread2;



    @Override
    protected Boolean doInBackground(Void... contexts) {
        Log.d("MESSAGE","Here I am");
        double i=0;
        for(i=0; i<=5000000;i++){}
        return false;
    }
}
