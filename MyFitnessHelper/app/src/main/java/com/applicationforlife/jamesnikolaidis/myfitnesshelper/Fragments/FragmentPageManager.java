package com.applicationforlife.jamesnikolaidis.myfitnesshelper.Fragments;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by James Nikolaidis on 8/30/2016.
 */
public class FragmentPageManager extends FragmentPagerAdapter {
    private Activity ccc;
    private Date date ;
    DateFormat format;

    public FragmentPageManager(android.support.v4.app.FragmentManager fm, Activity con) {
        super(fm);
        ccc=con;
    }

    @Override
    public Fragment getItem(int position) {

        format = new SimpleDateFormat("E ,MMM d y ");
        date = new Date();
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        if(position==0){

            ccc.setTitle(String.valueOf(format.format(today)));

            return  new Screen1();


        }else if(position==1){

            return  new Screen2();

        }else if (position==2){

            return Screen3.GetInstance();

        }else{
            return null;
        }



    }

    @Override
    public int getCount() {
        return 3;
    }
}
