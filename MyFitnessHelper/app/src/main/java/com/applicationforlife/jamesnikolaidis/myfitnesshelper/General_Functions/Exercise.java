package com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions;

/**
 * Created by James Nikolaidis on 9/3/2016.
 */
public class Exercise {



    private String[] Exercises;
    private String Workout_Name ;



    public Exercise (){



    }


    public String[] getExercises() {
        return Exercises;
    }

    public void setExercises(String[] exercises) {
        Exercises = exercises;
    }



    public void setWorkout_Name(String workout_Name) {
        Workout_Name = workout_Name;
    }
}
