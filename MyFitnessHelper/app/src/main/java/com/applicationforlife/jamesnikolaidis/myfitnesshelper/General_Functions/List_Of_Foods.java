package com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;

import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Database_Classes.Database_Fuction_Class;

import java.util.ArrayList;

/**
 * Created by James Nikolaidis on 9/3/2016.
 */
public class List_Of_Foods {
    private String[][] foods ;
    private Database_Fuction_Class database;
    private Cursor cursor;
    private static int ic=0,jc=0,counter;
    private SharedPreferences User_Pref ;

    private  static List_Of_Foods list_of_foods ;

    private List_Of_Foods(Context context){

        database =  Database_Fuction_Class.getInstance(context);
        if(database!=null && counter==1){
            database.insertFood("Mousakas","300","grams","100","10","40","20","10","5","0","0","50mg","30mg");
            database.insertFood("Macaroni Boiled","100", "grams","100","1","30","1","0","0","0","0","1mg");

        }
        foods = new String[database.getFoodCo()+1][14];



    }


    public String[][] returnFoods() {

for(int j=1 ;  j <= database.getFoodCo(); j++ ) {
    cursor =  database.getFoodData(j);
    cursor.moveToFirst();
    for (int i = 1; i != cursor.getColumnCount(); i++) {
       foods[j][i] =  cursor.getString(i);
        ic=i;
    }
     jc=j;
}


        return foods;
    }



    public int getI(){
        return ic;
    }

    public int getj(){
        return jc;
    }

    public static List_Of_Foods getInstance(Context context){
        if( list_of_foods==null){

            counter=1;
            list_of_foods = new List_Of_Foods(context);
        }
        else{
            counter = 0;
        }
return list_of_foods;
    }



}
