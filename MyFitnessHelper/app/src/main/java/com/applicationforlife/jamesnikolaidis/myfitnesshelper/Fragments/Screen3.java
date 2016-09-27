package com.applicationforlife.jamesnikolaidis.myfitnesshelper.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Database_Classes.Database_Fuction_Class;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions.List_Of_Exercises;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.R;

import java.util.ArrayList;

/**
 * Created by James Nikolaidis on 8/30/2016.
 */
public class Screen3  extends Fragment {
    private String BodyPart ;
    private static String[] BODY_PART_ARRAY;
    private static TextView BODYPART ;
    public static String bodypart="";
    private static View getView;
    private static Screen3  screen3;
    private static ArrayList<String> Array;
    private static Database_Fuction_Class database ;


    @Nullable
    @Override
    public  View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.screen3_layout,container,false);

        BODYPART = (TextView)view.findViewById(R.id.BodyPart);
        BODYPART.setText("");
        BODY_PART_ARRAY = new String[12];
        BODY_PART_ARRAY = getResources().getStringArray(R.array.BodyPartTrain);
        getView= view;
        database = Database_Fuction_Class.getInstance(getActivity());
        List_Of_Exercises list_of_exercises = List_Of_Exercises.getInstance(getActivity());
        Array = new ArrayList<>();
        return view;



    }

    public void ActivateDialog(){

        if(BODYPART.getText().toString().matches(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getView.getContext());
            builder.setTitle("Body Part ")
                    .setItems(R.array.BodyPartTrain, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            bodypart = BODY_PART_ARRAY[which];
                            BODYPART.setText(bodypart);

                        }
                    }).create().show();



        }else{

            BODYPART.setText(bodypart);
    }


    }



        public static void FillTheScreen(String BodyPart, final Context context){
            final ListView listView = (ListView)getView.findViewById(R.id.Screen3_LV);
            Cursor cursor ;
            cursor = database.getExerciseDataByTag(BodyPart);
            final String [] bodypartarray = new String[cursor.getCount()];
           int i=0;


                cursor.moveToFirst();
            do{
                bodypartarray[i] = cursor.getString(0);
                cursor.moveToNext();
                i++;
            }while(i!=cursor.getCount());




            AlertDialog.Builder builder = new AlertDialog.Builder(getView.getContext());
            builder.setTitle("Exercises ")
                    .setItems(bodypartarray, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Array.add(bodypartarray[which]);
                            ArrayAdapter adapter = new ArrayAdapter(context,R.layout.simpletext,R.id.ll,Array);
                            listView.setAdapter(adapter);

                        }
                    }).create().show();



        }




    public static Screen3 GetInstance(){
        if( screen3==null){

            Log.d("Loggg","Whats up doc1");
            screen3 = new Screen3();
        }
       return screen3;
    }







}
