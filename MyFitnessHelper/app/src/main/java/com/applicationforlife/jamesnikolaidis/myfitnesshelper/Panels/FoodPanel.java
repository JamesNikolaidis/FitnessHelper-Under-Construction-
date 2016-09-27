package com.applicationforlife.jamesnikolaidis.myfitnesshelper.Panels;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Database_Classes.Database_Fuction_Class;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions.Food;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions.List_Of_Foods;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.R;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Views_Classes.Manage_Views;

import java.util.ArrayList;

/**
 * Created by James Nikolaidis on 9/4/2016.
 */
public class FoodPanel extends AppCompatActivity {
    private Manage_Views manager;
    private Intent backDataPush;
    private  static  TextView name,protein,carbs,fats,satF,transF,chol,vita,vitb,sod,servingtype,calories;
    private EditText serving;
    private static  String [] ListViewFoodList;
    private List_Of_Foods list_of_foods ;
    private String[][] food_list;
    private  static int counter = 0,counter2=0,elementCounter=0;
    private Cursor cursor;
    private SharedPreferences User_Pref ;
    private SharedPreferences.Editor editor;
    private Database_Fuction_Class database;
    private ArrayList<TextView> ListOfNutrition;
    private static Food foodElement;




    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_panel);
        backDataPush = new Intent();
        foodElement = new Food();
        list_of_foods = List_Of_Foods.getInstance(getApplicationContext());
        ListOfNutrition = new ArrayList<TextView>(10);
        database = Database_Fuction_Class.getInstance(getApplicationContext());
        ListView listView = (ListView)findViewById(R.id.List_View);
        User_Pref =  getSharedPreferences("Pref", MODE_PRIVATE);



        food_list = list_of_foods.returnFoods();

        ListViewFoodList = new String[list_of_foods.getj()];
 try {

     for (int i = 1; i <= list_of_foods.getj(); i++) {
         for (int j = 1; j != 2; j++) {
             ListViewFoodList[counter] = food_list[i][j];
             counter++;
         }
     }
 }catch (ArrayIndexOutOfBoundsException ex){
     counter=0;
     ListViewFoodList = new String[list_of_foods.getj()+2];
     for (int i = 1; i <= list_of_foods.getj(); i++) {
         for (int j = 1; j != 2; j++) {
             ListViewFoodList[counter] = food_list[i][j];
             counter++;
         }
     }

 }

            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,ListViewFoodList);
            listView.setAdapter(adapter);





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                setContentView(R.layout.food_resources_panel);
                name = (TextView)findViewById(R.id.Food_NameTV);
                servingtype =(TextView)findViewById(R.id.ServingType_TV);
                protein =(TextView)findViewById(R.id.Protein_TV);
                carbs = (TextView)findViewById(R.id.Carbs_TV);
                fats =(TextView)findViewById(R.id.Fats_TV);
                satF = (TextView)findViewById(R.id.SaturatedF_TV);
                transF =(TextView)findViewById(R.id.TransF_TV);
                chol = (TextView)findViewById(R.id.Cholysterol_TV);
                vita =(TextView)findViewById(R.id.VitaminA_TV);
                vitb =(TextView)findViewById(R.id.VitaminB_TV);
                sod =(TextView)findViewById(R.id.Sodium_TV);
                serving = (EditText)findViewById(R.id.ServingSize_ED);
                calories= (TextView)findViewById(R.id.Calories_TV) ;

                ListOfNutrition.add(name);ListOfNutrition.add(calories);ListOfNutrition.add(servingtype);ListOfNutrition.add(protein);ListOfNutrition.add(carbs);
                ListOfNutrition.add(fats);ListOfNutrition.add(transF);ListOfNutrition.add(satF);ListOfNutrition.add(vita);
                ListOfNutrition.add(vitb);ListOfNutrition.add(chol);ListOfNutrition.add(sod);


                cursor = database.getFoodDataByName(String.valueOf(adapterView.getItemAtPosition(i)));
                for(int j=1; j<cursor.getColumnCount(); j++){
                    cursor.moveToFirst();
                    if(j==4){
                        serving.setText(cursor.getString(j));

                    }
                    else{
                    ListOfNutrition.get(counter2).setText(cursor.getString(j));
                        counter2++;

                    }


                }

                foodElement.setName(String.valueOf(adapterView.getItemAtPosition(i)));
                foodElement.setProtein(protein.getText().toString());
                foodElement.setCarbohydrates(carbs.getText().toString());
                foodElement.setFat(fats.getText().toString());
                foodElement.setTransFat(transF.getText().toString());
                foodElement.setSaturatedFat(satF.getText().toString());
                foodElement.setCholysterol(chol.getText().toString());
                foodElement.setSodium(sod.getText().toString());
                foodElement.setVitaminA(vita.getText().toString());
                foodElement.setVitaminB(vitb.getText().toString());
                foodElement.setCalories(calories.getText().toString());
                foodElement.setServingSize(serving.getText().toString());
                foodElement.setServing_type(servingtype.getText().toString());
                foodElement.setFoodId(elementCounter);


                switch(User_Pref.getString("Meal","Nothing")){

                    case "Breakfast":
                        Food.setBreakfast(foodElement);
                        Log.d("Mess","Here1");
                        break;
                    case  "Brunch":
                        Food.setBrunch(foodElement);
                        Log.d("Mess","Here2");
                        break;
                    case "Lunch":
                        Food.setLunch(foodElement);
                        Log.d("Mess","Here3");
                        break;
                    case "Post":
                        Food.setPost_Workout(foodElement);
                        Log.d("Mess","Here4");
                        break;
                    case "Dinner":
                        Food.setDinner(foodElement);
                        Log.d("Mess","Here5");
                        break;
                    default:
                        Log.d("Mess","Here6");
                        Food.setBefore_Ded(foodElement);
                        break;


                }

                Food.setBreakfast(foodElement);

                counter2=counter=0;
                elementCounter++;
               backDataPush.putExtra("food", String.valueOf(adapterView.getItemAtPosition(i)));
               // finish();






            }
        });



    }


    public void FoodLayoutClicked(View view) {
          serving = (EditText)findViewById(R.id.ServingSize_ED);
          calories= (TextView)findViewById(R.id.Calories_TV) ;
         float CaloriesResult;
         switch (Integer.parseInt(serving.getText().toString())){

            case 50:
                CaloriesResult = Integer.parseInt(calories.getText().toString())/2;

                break;
            case 25 :
                CaloriesResult = Integer.parseInt(calories.getText().toString())/4;

                break;
            case 30:
                CaloriesResult = Integer.parseInt(calories.getText().toString())*30/100;

            case 60 :
                CaloriesResult = (Integer.parseInt(calories.getText().toString())*60)/100;

                default:
                    CaloriesResult = Integer.parseInt(calories.getText().toString());

                    break;

        }
          backDataPush.putExtra("Calories",String.valueOf(CaloriesResult));
          setResult(RESULT_OK,backDataPush);
        finish();

    }


}
