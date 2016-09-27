package com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by James Nikolaidis on 9/25/2016.
 */
public class Food_Element_Manager {

    private ArrayList<Food> Foods_Array;
    private ArrayList<Integer> Foods_Id;
    private static Food_Element_Manager food_element_manager;
    private Food food;

    private Food_Element_Manager(Context context){




    }

    public static Food_Element_Manager getCrimeLab(Context context){
        if(food_element_manager==null){
            food_element_manager = new Food_Element_Manager(context.getApplicationContext());
        }
        return food_element_manager;

    }


    public Food getFoodByUUID(int code){

        for (Food food : Foods_Array) {
            if (food.getFoodId()==code){

                this.food = food;}

        }

       return this.food;
    }





    public ArrayList<Food> getmFoods() {
        return Foods_Array;
    }

    public void setmFood(ArrayList<Food> mfoods) {
        this.Foods_Array = mfoods;
    }


    public void AddmFood(Food food){
        Foods_Array.add(food);
    }



    public ArrayList<Integer> getmFoodsId() {
        return Foods_Id;
    }

    public void setmFoodId(ArrayList<Integer> mfoodsId) {
        this.Foods_Id = mfoodsId;
    }


    public void AddmFoodId(int code){
        Foods_Id.add(code);
    }



}
