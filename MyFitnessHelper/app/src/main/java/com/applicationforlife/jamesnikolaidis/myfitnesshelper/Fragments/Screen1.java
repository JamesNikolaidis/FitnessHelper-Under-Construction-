package com.applicationforlife.jamesnikolaidis.myfitnesshelper.Fragments;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.applicationforlife.jamesnikolaidis.myfitnesshelper.R;

/**
 * Created by James Nikolaidis on 9/2/2016.
 */
public class Screen1 extends Fragment {
    private static SharedPreferences User_Pref ;
    private static  View mainview;
    private static float USER_EATEN_CALORIES=0,FINAL_USER_EATEN_CAL=0;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen1_layout,container,false);
        User_Pref = getActivity().getSharedPreferences("Pref",Activity.MODE_PRIVATE);
        TextView user_cal = (TextView)view.findViewById(R.id.USER_CALORIES);
        user_cal.setText(User_Pref.getString("FinalCalories","0"));
        mainview=view;
               return view;



    }


    public static void RefreshData(){


        TextView user_cal = (TextView)mainview.findViewById(R.id.USER_CALORIES);
        TextView eatenCal = (TextView)mainview.findViewById(R.id.EATEN_CALORIES);
        TextView finalC = (TextView)mainview.findViewById(R.id.FINAL_CALORIES);
        String cal = User_Pref.getString("Calories","0");
        USER_EATEN_CALORIES =  Float.parseFloat(cal);
        user_cal.setText(User_Pref.getString("FinalCalories","0"));
        eatenCal.setText(String.valueOf(USER_EATEN_CALORIES));
        finalC.setText(String.valueOf((Float.parseFloat(user_cal.getText().toString())- Float.parseFloat(eatenCal.getText().toString()) ) )   );

    }




}
