package com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions;

/**
 * Created by James Nikolaidis on 8/28/2016.
 */
public class General_Functions {


    public double Calculate_BMR(int height , int weight , int age , String Gender){

        if( Gender=="Male"){
      return  ( 10 * weight + 6.25 * height - 5 * age + 5);}
        else

            return  (10 * weight + 6.25 *height - 5 * age - 161);

    }

    public double CalculateDailyCalorieIntake(double BMR , String Activity_level){
        if(Activity_level=="Lightly Active"){
            return BMR*1.375;
        }else if(Activity_level=="Moderate Active"){
            return BMR* 1.55;
        }else if(Activity_level=="Very Active"){
            return BMR*1.725;
        }else
            return BMR*1.9;

    }


    public double GoalCalculateDailyCalorieIntake(String GOAL , double dailycalories){

        if( GOAL=="Loose"){
            return  ( dailycalories-250);}
        else if(GOAL=="Stamina-Strengh")

            return  (dailycalories-0);
        else
            return dailycalories+250;

    }


}
