package com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Database_Classes.Database_Fuction_Class;

/**
 * Created by James Nikolaidis on 9/3/2016.
 */
public class List_Of_Exercises {

    private String[] Back_exercises,Chest_Exercise,Bicep_Exercise,Tricep_Exercise,Deltoid_Exercise,Leg_Exercise ;
    private String[][] Exercise ;
    private Database_Fuction_Class database;
    private Cursor cursor;
    private static int ic=0,jc=0,counter;
    private SharedPreferences User_Pref ;
    private  static List_Of_Exercises list_of_exercise ;

    private List_Of_Exercises(Context context){

        database =  Database_Fuction_Class.getInstance(context);
        if(database!=null && counter==1){
            database.insertExercise("Lat Pulldown","Low","Back");
            database.insertExercise("Pull-Up","Minimum","Back");
            database.insertExercise(" Barbbell Bench Press","Medium","Chest");
            database.insertExercise("Dumbell Bench Press ","Low","Chest");
            database.insertExercise("Triceps Extension","Low","Triceps");
            database.insertExercise("Skull Crushes ","Low","Triceps");
            database.insertExercise("Bicep Barbell Curl","Low","Bicep");
            database.insertExercise("Hammers","Low","Bicep");
            database.insertExercise("Hamstring Press","Low","Legs");
            database.insertExercise("Squats","High","Legs");
            database.insertExercise("Calves Raises","Low","Calves");



        }
        Exercise = new String[database.getExerciseCounter()+1][1];
    }

    public String[][] returnExercise() {

        for(int j=1 ;  j <= database.getExerciseCounter(); j++ ) {
            cursor =  database.getExerciseData(j);
            cursor.moveToFirst();
            for (int i = 1; i != cursor.getColumnCount(); i++) {
                Exercise[j][i] =  cursor.getString(i);
                ic=i;
            }
            jc=j;
        }


        return Exercise;
    }



    public int getI(){
        return ic;
    }

    public int getj(){
        return jc;
    }


    public static List_Of_Exercises getInstance(Context context){
        if( list_of_exercise==null){

            counter=1;
            list_of_exercise = new List_Of_Exercises(context);
        }
        else{
            counter = 0;
        }
        return list_of_exercise;
    }





    public String[] getLeg_Exercise() {
        return Leg_Exercise;
    }
    public String[] getDeltoid_Exercise() {
        return Deltoid_Exercise;
    }
    void setDeltoid_Exercise(String[] deltoid_Exercise) {
        Deltoid_Exercise = deltoid_Exercise;
    }
    public String[] getTricep_Exercise() {
        return Tricep_Exercise;
    }
    public String[] getBicep_Exercise() {
        return Bicep_Exercise;
    }
    public String[] getChest_Exercise() {
        return Chest_Exercise;
    }
    public String[] getBack_exercises() {
        return Back_exercises;
    }


}
