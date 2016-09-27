package com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by James Nikolaidis on 9/3/2016.
 */
public class Food {
    private static ArrayList<Food> breakfast , Brunch , Lunch , Post_Workout , Dinner , Before_Ded,FoodList;
    private int FoodId ;
    private static Food food_element ;
    private String name,calories,serving_type,servingSize,Protein,Carbohydrates ,Fat ,TransFat,SaturatedFat ,VitaminA , VitaminB ,Cholysterol , Sodium ;



    public static Food getFood_element() {

        return food_element;
    }


    public static void SaveCompleteFoodElement(Food food){
        FoodList.add(food);
    }


    public static Food GetCompleteFoodElementById(int id){

        for (Food foods : FoodList) {
            if (foods.getFoodId()==id){

                food_element = foods;}

        }

        return  food_element;
    }


    public static void setFood_element(Food food_element) {
        Food.food_element = food_element;
    }

    public String getSodium() {
        return Sodium;
    }

    public void setSodium(String sodium) {
        Sodium = sodium;
    }

    public String getCholysterol() {
        return Cholysterol;
    }

    public void setCholysterol(String cholysterol) {
        Cholysterol = cholysterol;
    }

    public String getVitaminB() {
        return VitaminB;
    }

    public void setVitaminB(String vitaminB) {
        VitaminB = vitaminB;
    }

    public String getVitaminA() {
        return VitaminA;
    }

    public void setVitaminA(String vitaminA) {
        VitaminA = vitaminA;
    }

    public String getSaturatedFat() {
        return SaturatedFat;
    }

    public void setSaturatedFat(String saturatedFat) {
        SaturatedFat = saturatedFat;
    }

    public String getTransFat() {
        return TransFat;
    }

    public void setTransFat(String transFat) {
        TransFat = transFat;
    }

    public String getFat() {
        return Fat;
    }

    public void setFat(String fat) {
        Fat = fat;
    }

    public String getCarbohydrates() {
        return Carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        Carbohydrates = carbohydrates;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getServing_type() {
        return serving_type;
    }

    public void setServing_type(String serving_type) {
        this.serving_type = serving_type;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtein() {
        return Protein;
    }

    public void setProtein(String protein) {
        Protein = protein;
    }

    public Food( )
    {


        FoodList= breakfast = Brunch = Lunch = Post_Workout =Dinner = Before_Ded=new ArrayList<>();
    }


    public ArrayList<Food> getBreakfast() {
        return breakfast;
    }

    public static  void setBreakfast(Food Breakfast) { breakfast.add(Breakfast);
    }

    public ArrayList<Food> getBefore_Ded() {
        return Before_Ded;
    }

    public static void setBefore_Ded(Food before_Ded) {
        Before_Ded.add(before_Ded);
    }

    public ArrayList<Food> getDinner() {
        return Dinner;
    }

    public static void setDinner(Food dinner) {
        Dinner.add(dinner);
    }

    public ArrayList<Food> getPost_Workout() {
        return Post_Workout;
    }

    public static void setPost_Workout(Food post_Workout) {
        Post_Workout.add(post_Workout);
    }

    public ArrayList<Food> getBrunch() {
        return Brunch;
    }

    public static void setBrunch(Food brunch) {
        Brunch.add(brunch);
    }

    public ArrayList<Food> getLunch() {
        return Lunch;
    }

    public static void setLunch(Food lunch) {Lunch.add(lunch);
    }

    public int getFoodId() {
        return FoodId;
    }

    public void setFoodId(int foodId) {
        FoodId = foodId;
    }







}
