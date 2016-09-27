package com.applicationforlife.jamesnikolaidis.myfitnesshelper.Day_Function;

import com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions.Exercise;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions.Food;

import java.util.Date;

/**
 * Created by James Nikolaidis on 9/3/2016.
 */
public class Day_Class {

    private Date date;
    private Food food_element;
    private int id;
    private Exercise exercise_element;



    public Day_Class(){
        date = new Date();
        food_element=new Food();
        exercise_element = new Exercise();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


/*

    public void SetFoodBreakfast(String ...breakfast_food){

        food_element.setBreakfast(breakfast_food);
    }
    public void SetFoodBrunch(String ...Brunch_food){
       food_element.setBrunch(Brunch_food);

   }
    public void SetFoodLynch(String ...lynch_food){

        food_element.setLunch(lynch_food);
    }
    public void SetFoodPre(String ...pre_food){
        food_element.setPost_Workout(pre_food);
    }
    public void SetDinnerFood(String ...dinner_food){
        food_element.setDinner(dinner_food);

    }
    public void SetBedFood(String ... bed_food){
        food_element.setBefore_Ded(bed_food);
    }


    public void SetWorkoutName(String name){
        exercise_element.setWorkout_Name(name);
    }
    public void SetExerciseList(String ... exercies){
        exercise_element.setExercises(exercies);
    }



    public void getUserId(int ids){
        id=ids;

    }


     public Food getFoodElement(){return food_element;}
    public Exercise getExercise_element(){return exercise_element;}
*/

}
