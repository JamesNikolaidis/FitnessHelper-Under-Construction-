package com.applicationforlife.jamesnikolaidis.myfitnesshelper.Views_Classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Fragments.Screen3;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions.General_Functions;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Panels.FoodPanel;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.R;

import java.util.ArrayList;

/**
 * Created by James Nikolaidis on 8/25/2016.
 */
public class Manage_Views extends View {
    private static Context application_context;
    private float ANIMATION_CASE_RIGHT = 380;
    private float ANIMATION_CASE_UP_RIGHT = 83;
    private float ANIMATION_CASE_LEFT_END = 60;
    private static float IMAGE_DIRECTION_X = 0, BAR1_DIRECTION_X, BAR2_DIRECTION_X, BAR3_DIRECTION_X;
    private static float IMAGE_DIRECTION_Y = 0;
    private static float TIC_IMAGE_DIRECTION_X = 150;
    private static float TIC_IMAGE_DIRECTION_Y = -250;
    private static double USER_FINAL_CALORIES;
    private final Handler handler = new Handler();
    private static String GENDER_SELECTION, ACTIVITY_LEVEL, GOAL;
    private static int i, tagcounter = 0, TIC_COUNTER = 0,MEAL_ID;
    private LinearLayout.LayoutParams parms;
    private General_Functions general_manager = new General_Functions();
    private Intent intent;



    public Manage_Views(Context context) {
        super(context);

    }


    public boolean SetVisibillityToObjects(View... views) {

        try {
            for (i = 0; i <= views.length; i++) {
                views[i].setVisibility(View.VISIBLE);

            }
        } catch (Exception notVisibleException) {
            return false;
        }

        return true;
    }

    public boolean SetInvisivillityToObjects(View... views) {
        try {
            for (i = 0; i <= views.length; i++) {
                views[i].setVisibility(View.INVISIBLE);
            }
        } catch (Exception notVisibleException) {
            return false;
        }
        return true;
    }


    public void Sponsor_Function(View view, Context context) {

        switch (view.getId()) {
            case R.id.Under_Armour:
                Toast.makeText(context, "Under Armour Support Company , USA", Toast.LENGTH_SHORT).show();
                break;

            case R.id.bbcom:
                Toast.makeText(context, "BodyBuilding.com  Company , USA", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(context, "Elixir Gym Franchise , WorldWide", Toast.LENGTH_SHORT).show();
                break;

        }
    }


    public void MenuManager(MenuItem item, Activity s) {

        switch (item.getItemId()) {
            case R.id.Exit_Profile:

                s.finish();
                System.exit(0);
                break;

            case R.id.bodybuilding:

                break;

            case R.id.Profile:

                break;

            case R.id.action_favorite:
                Log.d("LogM","Hey");
                break;
            default:


                break;

        }

    }


    public void ChangePageAndStories(View view, Button Back_Button, String[] Story, TextView textView, LinearLayout layout, View... views) {
        CheckBox[] checkBoxes = new CheckBox[4];
        TextView[] texviews = new TextView[4];
        for (int counter = 1; counter < 4; counter++) {

            checkBoxes[counter] = (CheckBox) views[3 + (counter - 1)];
            texviews[counter] = (TextView) texviews[counter];
        }


        switch (view.getId()) {

            case R.id.Front_Button:


                textView.setText(Story[1]);
                SetInvisivillityToObjects(views[0], views[1], views[2]);
                SetVisibillityToObjects(views[3], views[4], views[5]);
                Back_Button.setEnabled(true);


                break;


            case R.id.Back_ButtonOnTheTop:


                if (views[3].getVisibility() == VISIBLE && views[4].getVisibility() == VISIBLE && views[5].getVisibility() == VISIBLE && views[6].getVisibility() == VISIBLE) {

                    SetInvisivillityToObjects(views[3], views[4], views[5], views[6]);
                    SetVisibillityToObjects(views[0], views[1], views[2]);
                    for (int counter = 2; counter < 2; counter++) {
                        texviews[counter].setText("");
                        checkBoxes[counter].setChecked(false);

                    }
                    view.setBackgroundResource(getResources().getIdentifier("com.applicationforlife.jamesnikolaidis.myfitnesshelper:drawable/next_arrow", null, null));
                    view.setTag("front");
                    layout.findViewWithTag("TicImage0").setVisibility(View.GONE);


                } else if (views[3].getVisibility() == VISIBLE && views[4].getVisibility() == VISIBLE && views[5].getVisibility() == INVISIBLE && views[6].getVisibility() == INVISIBLE) {


                    checkBoxes[1].setText("Lightly Active");
                    checkBoxes[2].setText("Moderate Active");
                    for (int counter = 1; counter < 3; counter++) {
                        checkBoxes[counter].setChecked(false);
                    }
                    layout.findViewWithTag("TicImage1").setVisibility(View.GONE);
                    SetVisibillityToObjects(views[3], views[4], views[5], views[6]);
                } else if (view.getTag() == "front") {
                    textView.setText(Story[1]);
                    SetInvisivillityToObjects(views[0], views[1], views[2]);
                    SetVisibillityToObjects(views[3], views[4], views[5]);
                    view.setBackgroundResource(getResources().getIdentifier("com.applicationforlife.jamesnikolaidis.myfitnesshelper:drawable/previous_arrow", null, null));

                }


                break;

            default:
                textView.setText(Story[0]);
                SetInvisivillityToObjects(views[3], views[4], views[5], views[6]);
                SetVisibillityToObjects(views[0], views[1], views[2]);
                break;


        }
    }


    public void Animation(ImageView image) {

        if (IMAGE_DIRECTION_X < ANIMATION_CASE_RIGHT) {

            do {

                image.setY(image.getY());
                image.setX(image.getX() + 5);
                image.invalidate();

                IMAGE_DIRECTION_X = image.getX();
                IMAGE_DIRECTION_Y = image.getY();
                Log.d("fdfdfs", "ON x " + String.valueOf(image.getX()) + "Y=" + String.valueOf(image.getY()));
            } while (IMAGE_DIRECTION_X < ANIMATION_CASE_RIGHT);


        } else if (IMAGE_DIRECTION_Y >= ANIMATION_CASE_UP_RIGHT && IMAGE_DIRECTION_X == ANIMATION_CASE_RIGHT) {
            do {

                image.setY(image.getY() - 5);
                image.setX(image.getX());
                image.invalidate();
                IMAGE_DIRECTION_X = image.getX();
                IMAGE_DIRECTION_Y = image.getY();
                Log.d("fdfdfs", "On y" + String.valueOf(image.getX()) + "Y=" + String.valueOf(image.getY()));
            }
            while (IMAGE_DIRECTION_Y >= ANIMATION_CASE_UP_RIGHT && IMAGE_DIRECTION_X == ANIMATION_CASE_RIGHT);


        } else if (IMAGE_DIRECTION_Y <= ANIMATION_CASE_UP_RIGHT) {

            do {
                Log.d("fdfdfs", "On retty" + String.valueOf(image.getX()) + "Y=" + String.valueOf(image.getY()));
                image.setY(image.getY());
                image.setX(image.getX() - 5);
                IMAGE_DIRECTION_X = image.getX();
                IMAGE_DIRECTION_Y = image.getY();
                image.invalidate();
            }
            while (IMAGE_DIRECTION_X >= ANIMATION_CASE_LEFT_END);

        }


    }


    public void CreateTicImage(Context app_context, LinearLayout layout) {


        final ImageView tic_image = new ImageView(app_context);
        tic_image.setY(TIC_IMAGE_DIRECTION_Y);
        tic_image.setX(TIC_IMAGE_DIRECTION_X);
        tic_image.setImageResource(getResources().getIdentifier("com.applicationforlife.jamesnikolaidis.myfitnesshelper:drawable/tick_good", null, null));
        final int[] widthd = {70};
        final int[] heightd = {70};
        final LinearLayout.LayoutParams[] parms = {new LinearLayout.LayoutParams(widthd[0], heightd[0])};
        tic_image.setLayoutParams(parms[0]);
        tic_image.setTag("TicImage" + String.valueOf(tagcounter));
        tic_image.setClickable(false);
        layout.addView(tic_image);


        handler.postDelayed(new Runnable() {
            public void run() {
                widthd[0] = 30;
                heightd[0] = 30;
                parms[0] = new LinearLayout.LayoutParams(widthd[0], heightd[0]);
                tic_image.setLayoutParams(parms[0]);
                tic_image.setAlpha(200);
                for (int counter = 0; counter < 3; counter++) {
                    Animation(tic_image);
                }
                ClearDirectionForAnimation();


            }
        }, 1000);

        tic_image.invalidate();
        tagcounter++;

    }


    public void ClearDirectionForAnimation() {
        IMAGE_DIRECTION_X = TIC_IMAGE_DIRECTION_X;
        IMAGE_DIRECTION_Y = TIC_IMAGE_DIRECTION_Y;

        if (TIC_COUNTER >= 2) {
            Log.d("dddd", "Here");

            ANIMATION_CASE_LEFT_END = ANIMATION_CASE_LEFT_END + 117;
            ANIMATION_CASE_RIGHT = 425;
        } else {
            ANIMATION_CASE_LEFT_END = ANIMATION_CASE_LEFT_END + 117;
            ANIMATION_CASE_RIGHT = 380;
            TIC_COUNTER++;
        }


    }


    public void CheckForCheckBoxClicked(final EditText e, final EditText e2, final View checkbox, final Context context, final LinearLayout layout, final int h, final int w, final int a, final TextView text, final String story, final CheckBox... remainCheckBox) {

        EditText height = (EditText) findViewById(R.id.Height_ED);
        if (remainCheckBox[2].getVisibility() == View.INVISIBLE) {

            switch (checkbox.getId()) {

                case R.id.Lightly_Active_CB:
                    GENDER_SELECTION = remainCheckBox[0].getText().toString();


                    CreateTicImage(context, layout);
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            e.setText("BMR : " + String.valueOf(general_manager.Calculate_BMR(h, w, a, GENDER_SELECTION)));
                            e2.setText("Daily Calories  : " + String.valueOf(general_manager.CalculateDailyCalorieIntake(general_manager.Calculate_BMR(h, w, a, GENDER_SELECTION), ACTIVITY_LEVEL)));
                            SetInvisivillityToObjects(remainCheckBox[0], remainCheckBox[1]);
                            e.setClickable(false);
                            e2.setClickable(false);
                            SetVisibillityToObjects(e, e2);
                            remainCheckBox[0].setText("Loose Weight");
                            remainCheckBox[1].setText("Stamina-Strengh");
                            remainCheckBox[2].setText("Muscle Growth");
                            remainCheckBox[0].setChecked(false);
                            remainCheckBox[1].setChecked(false);
                            remainCheckBox[2].setChecked(false);


                        }
                    }, 1000);


                    break;
                case R.id.Moderate_Active_CB:
                    GENDER_SELECTION = remainCheckBox[1].getText().toString();
                    CreateTicImage(context, layout);
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            e.setText("BMR : " + String.valueOf(general_manager.Calculate_BMR(h, w, a, GENDER_SELECTION)));
                            e2.setText("Daily Calories  : " + String.valueOf(general_manager.CalculateDailyCalorieIntake(general_manager.Calculate_BMR(h, w, a, GENDER_SELECTION), ACTIVITY_LEVEL)));
                            SetInvisivillityToObjects(remainCheckBox[0], remainCheckBox[1]);
                            e.setClickable(false);
                            e2.setClickable(false);
                            SetVisibillityToObjects(e, e2);
                            remainCheckBox[0].setText("Loose Weight");
                            remainCheckBox[1].setText("Stamina-Strengh");
                            remainCheckBox[2].setText("Muscle Growth");
                            remainCheckBox[0].setChecked(false);
                            remainCheckBox[1].setChecked(false);
                            remainCheckBox[2].setChecked(false);

                        }
                    }, 1000);

                    break;


            }


        } else

        {

            switch (checkbox.getId()) {


                case R.id.Lightly_Active_CB:

                    if (remainCheckBox[3].getVisibility() == View.INVISIBLE) {

                    } else {

                        CreateTicImage(context, layout);
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                for (int i = 2; i < remainCheckBox.length; i++) {
                                    remainCheckBox[i].setVisibility(View.INVISIBLE);
                                }
                                for (int i = 0; i < remainCheckBox.length; i++) {
                                    remainCheckBox[i].setChecked(false);
                                }
                                text.setText(story);
                                ACTIVITY_LEVEL = remainCheckBox[0].getText().toString();
                                remainCheckBox[0].setText("Male");
                                remainCheckBox[1].setText("Female");
                            }
                        }, 1000);

                        break;
                    }


                case R.id.Moderate_Active_CB:
                    if (remainCheckBox[3].getVisibility() == View.INVISIBLE) {

                    } else {
                        CreateTicImage(context, layout);
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                for (int i = 2; i < 4; i++) {
                                    remainCheckBox[i].setVisibility(View.INVISIBLE);
                                }

                                for (int i = 0; i < remainCheckBox.length; i++) {
                                    remainCheckBox[i].setChecked(false);
                                }

                                text.setText(story);
                                ACTIVITY_LEVEL = remainCheckBox[1].getText().toString();
                                remainCheckBox[0].setText("Male");
                                remainCheckBox[1].setText("Female");
                            }
                        }, 1000);
                    }
                    break;

                case R.id.Very_Active_CB:

                    if (remainCheckBox[3].getVisibility() == View.INVISIBLE) {

                    } else {
                        CreateTicImage(context, layout);
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                for (int i = 2; i < 4; i++) {
                                    remainCheckBox[i].setVisibility(View.INVISIBLE);
                                }

                                for (int i = 0; i < remainCheckBox.length; i++) {
                                    remainCheckBox[i].setChecked(false);
                                }

                                text.setText(story);
                                ACTIVITY_LEVEL = remainCheckBox[2].getText().toString();
                                remainCheckBox[0].setText("Male");
                                remainCheckBox[1].setText("Female");
                            }
                        }, 1000);
                    }
                    break;

                default:


                    CreateTicImage(context, layout);
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            for (int i = 2; i < 4; i++) {
                                remainCheckBox[i].setVisibility(View.INVISIBLE);
                            }

                            for (int i = 0; i < remainCheckBox.length; i++) {
                                remainCheckBox[i].setChecked(false);
                            }

                            text.setText(story);
                            ACTIVITY_LEVEL = remainCheckBox[3].getText().toString();
                            remainCheckBox[0].setText("Male");
                            remainCheckBox[1].setText("Female");
                        }
                    }, 1000);
                    break;


            }
        }

        if (remainCheckBox[0].getText().toString() == "Loose Weight") {

            switch (checkbox.getId()) {
                case R.id.Lightly_Active_CB:

                    CreateTicImage(context, layout);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            GOAL = "Loose";
                            USER_FINAL_CALORIES = general_manager.GoalCalculateDailyCalorieIntake(GOAL, general_manager.CalculateDailyCalorieIntake(general_manager.Calculate_BMR(h, w, a, GENDER_SELECTION), ACTIVITY_LEVEL));
                            e.setText("Daily Calories  : " + String.valueOf(USER_FINAL_CALORIES));
                            SetInvisivillityToObjects(remainCheckBox[0], remainCheckBox[1], remainCheckBox[2]);
                            e.setClickable(false);
                            SetVisibillityToObjects(e);
                        }
                    }, 1000);

                    break;

                case R.id.Moderate_Active_CB:

                    CreateTicImage(context, layout);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            GOAL = "Stamina-Strengh";
                            USER_FINAL_CALORIES =  general_manager.GoalCalculateDailyCalorieIntake(GOAL, general_manager.CalculateDailyCalorieIntake(general_manager.Calculate_BMR(h, w, a, GENDER_SELECTION), ACTIVITY_LEVEL));
                            e.setText("Daily Calories  : " + String.valueOf(USER_FINAL_CALORIES));
                            SetInvisivillityToObjects(remainCheckBox[0], remainCheckBox[1], remainCheckBox[2]);
                            e.setClickable(false);
                            SetVisibillityToObjects(e);


                        }
                    }, 1000);

                    break;

                case R.id.Very_Active_CB:
                    CreateTicImage(context, layout);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            GOAL = "Muscle Growth";
                            USER_FINAL_CALORIES = general_manager.GoalCalculateDailyCalorieIntake(GOAL, general_manager.CalculateDailyCalorieIntake(general_manager.Calculate_BMR(h, w, a, GENDER_SELECTION), ACTIVITY_LEVEL));
                            e.setText("Daily Calories  : " + String.valueOf(USER_FINAL_CALORIES));
                            SetInvisivillityToObjects(remainCheckBox[0], remainCheckBox[1], remainCheckBox[2]);
                            e.setClickable(false);
                            SetVisibillityToObjects(e);
                        }
                    }, 1000);
                    break;


            }


        }

    }


    public boolean SetTextToEmpty(TextView... text) {

        try {
            for (i = 0; i <= text.length; i++) {
                text[i].setText("");
            }
        } catch (Exception notVisibleException) {
            return false;
        }
        return true;
    }


    public int GetIntFromEditText(EditText editText) {
        int integers;
        return (integers = Integer.parseInt(editText.getText().toString()));


    }


    public boolean SetHintToTextes(String[] names, TextView... texts) {
        try {
            for (i = 0; i <= texts.length; i++) {
                texts[i].setHint(names[i]);
            }
        } catch (Exception notVisibleException) {
            return false;
        }
        return true;

    }


    public void GetBarsForDirections(EditText... texts) {

        BAR1_DIRECTION_X = texts[0].getX();
        BAR2_DIRECTION_X = texts[1].getX();
        BAR3_DIRECTION_X = texts[2].getX();

    }


    public void makeSpinnerDeclaration(Context context, Spinner... spinner) {


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.Weakness, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner[0].setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(context, R.array.Strong_Point, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner[1].setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(context, R.array.Levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner[2].setAdapter(adapter);


    }


    public String SetSpinnerSelectedListener(Spinner spinner) {

        final String[] value = new String[1];

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value[0] = String.valueOf(adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return value[0];
    }


    public Intent Screen2ClickListener(View view, Context context) {
        SharedPreferences pref = context.getSharedPreferences("Pref",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        switch (view.getId()) {
            case R.id.Breakfast:
                editor.putString("Meal","Breakfast");
                editor.commit();
                intent = new Intent(context, FoodPanel.class);
                MEAL_ID=R.id.Breakfast;
                break;
            case R.id.Brunch:
                editor.putString("Meal","Brunch");
                editor.commit();
                intent = new Intent(context, FoodPanel.class);
                MEAL_ID = R.id.Brunch;
                break;

            case R.id.Lunch:
                editor.putString("Meal","Lunch");
                editor.commit();
                intent = new Intent(context, FoodPanel.class);
                MEAL_ID = R.id.Lunch;
                break;
            case R.id.Post:
                editor.putString("Meal","Post");
                editor.commit();
                intent = new Intent(context, FoodPanel.class);
                MEAL_ID = R.id.Post;
                break;

            case R.id.Dinner :
                editor.putString("Meal","Dinner");
                editor.commit();
                intent = new Intent(context, FoodPanel.class);
                MEAL_ID = R.id.Dinner;
                break;
            default:
                editor.putString("Meal","BefBed");
                editor.commit();
                intent = new Intent(context, FoodPanel.class);
                MEAL_ID = R.id.Bed;
                break;


        }


        return intent;

    }


    public void SetTextToListView(ListView list, String[] values, Context context) {
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, values);
        list.setAdapter(adapter);


    }


    public ListView ReturnListViewByTextViewOption(TextView text) {
        ListView list;


        switch (text.getId()) {
            case R.id.Breakfast:
                list = (ListView) findViewById(R.id.Breakfast_list);
                break;

            case R.id.Brunch:
                list = (ListView) findViewById(R.id.Brunch_list);
                break;

            case R.id.Lunch:
                list = (ListView) findViewById(R.id.Lunch_list);
                break;

            case R.id.Post:
                list = (ListView) findViewById(R.id.Post_list);
                break;

            case R.id.Dinner:
                list = (ListView) findViewById(R.id.Dinner_list);
                break;

            default:
                list = (ListView) findViewById(R.id.Bed_list);
                break;


        }


        return list;
    }


    public double getUserFinalCalories(){
        return USER_FINAL_CALORIES;
    }

     public int GetMealId(){
         return MEAL_ID;
     }



    public String GetUserBodyPArt(String userbodypart) {
        String[] BODY_PART_ARRAY = new String[12];
        BODY_PART_ARRAY = getResources().getStringArray(R.array.BodyPartTrain);
        String Bodypart;
         Log.d("Loggg","UserBodyPArt "+userbodypart);
        Log.d("Loggg","BodyPArt2 "+BODY_PART_ARRAY[0]);
        if(userbodypart.matches(BODY_PART_ARRAY[0])){
            Bodypart="Chest";
        }else if (userbodypart.matches(BODY_PART_ARRAY[1])){Bodypart="Back";

        }else if (userbodypart.matches(BODY_PART_ARRAY[2])){  Bodypart="Bicep";

        }else if (userbodypart.matches(BODY_PART_ARRAY[3])){ Bodypart="triceps";

        }else if (userbodypart.matches(BODY_PART_ARRAY[4])){Bodypart="Hands";

        }else if (userbodypart .matches(BODY_PART_ARRAY[5])){  Bodypart="Chest-Back";

        }else if (userbodypart .matches(BODY_PART_ARRAY[6])){   Bodypart="Shoulders";

        }else if (userbodypart .matches(BODY_PART_ARRAY[7])){ Bodypart="Legs";

        }else if (userbodypart.matches(BODY_PART_ARRAY[8])){   Bodypart="Chest-Back";

        }else if (userbodypart.matches(BODY_PART_ARRAY[9])){  Bodypart="Chest-Triceps";

        }else if (userbodypart.matches(BODY_PART_ARRAY[10])){  Bodypart="Chest-Biceps";

        }else{
            Bodypart="Back-Triceps";
        }




   return  Bodypart;
    }



}


