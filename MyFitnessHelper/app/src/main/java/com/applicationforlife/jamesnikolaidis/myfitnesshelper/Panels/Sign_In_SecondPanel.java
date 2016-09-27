package com.applicationforlife.jamesnikolaidis.myfitnesshelper.Panels;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Database_Classes.Database_Fuction_Class;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.R;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Views_Classes.Manage_Views;


import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Nikolaidis on 8/25/2016.
 */
public class Sign_In_SecondPanel extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static Manage_Views manager;
    private static Database_Fuction_Class DatabaseManager;
    private static  EditText height,weight,age,bar1,bar2,bar3;
    private static  TextView Presentation_text,weigh_in_kilos_menu, height_in_cm_menu;
    private String[] stories;
    private static CheckBox light,mod,very,exteme;
    private Button Back_Button ;
    private final Handler handler = new Handler();
    private static int height1,weight1,age1;
    private SharedPreferences User_Pref ;
    private SharedPreferences.Editor editor ;
    private Cursor cursor;
    private  static  LinearLayout layout;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drawer_layout);
        Presentation_text=(TextView)findViewById(R.id.first_steps_text);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        manager = new Manage_Views(getApplicationContext());
        stories= new String[]{"Welcome to MyFitnessHelper.Congratulation for your decision to change your Body and your past Habits." +
                "Starting your journey , you must calculate you BMR or Basic Metabolic Rate. BMR shows you how many calories do you burn throughout the day without exercise at all." +
                "For the first step just fill the fields Below and click next" +
                " Button on the bottom   ",
                "Now after you fill the fields and  your BMR has been calculated , it is time to select your Activity Level . Some tips : " +
                        "If you have a office job chooce moderate rate otherwise the Very Active one.","Below is your BMR by the data that you pass into the application . Also on the right side is the Daily amount of" +
                "calories you ust consume in order to maintain your current weight. Your goal is in the next page."
        };

        Presentation_text.setText(stories[0]);




        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        DatabaseManager = Database_Fuction_Class.getInstance(getApplicationContext());
        User_Pref  = getSharedPreferences("Pref",MODE_PRIVATE);

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    public void MenuItemSelect(MenuItem item) {

        manager.MenuManager(item,this);

    }

    public void ChangePage(final View view) {

          //                     Varaible declaration         //
        //*********************************************************

        layout = (LinearLayout)findViewById(R.id.layout);
        height=(EditText)findViewById(R.id.Height_ED);
        weight=(EditText)findViewById(R.id.Weight_ED);
        age=(EditText)findViewById(R.id.Age_ED);
        Presentation_text=(TextView)findViewById(R.id.first_steps_text);
        Back_Button = (Button)findViewById(R.id.Back_Button);
        final ImageView image = new ImageView(this);
        light = (CheckBox)findViewById(R.id.Lightly_Active_CB);
        mod = (CheckBox)findViewById(R.id.Moderate_Active_CB);
        very = (CheckBox)findViewById(R.id.Very_Active_CB);
        exteme = (CheckBox)findViewById(R.id.Extremely_Active_CB);
        weigh_in_kilos_menu =(TextView)findViewById(R.id.Weight_in_Kilos);
        height_in_cm_menu = (TextView)findViewById(R.id.Height_In_Cm);
        bar1=(EditText)findViewById(R.id.bar1);
        bar2=(EditText)findViewById(R.id.bar2);
        bar3=(EditText)findViewById(R.id.bar3);



        //*************************************************


        manager.GetBarsForDirections(bar1,bar2,bar3);
        if(height.getText().length()!=0 && weight.getText().length()!=0 && age.getText().length()!=0){

            manager.CreateTicImage(getApplicationContext(),layout);
            height1 = manager.GetIntFromEditText(height);
            weight1=  manager.GetIntFromEditText(weight);
            age1=  manager.GetIntFromEditText(age);
            try{
            cursor =  DatabaseManager.getDataEmail(User_Pref.getString("User_Email","nothing"));
            }catch (Exception Nullpointer)
            {
                   super.onDestroy();
            }

            cursor.moveToFirst();
            int userId = cursor.getInt(0);
            DatabaseManager.updateContact(userId,cursor.getString(1),cursor.getString(2),cursor.getString(3),"",height.getText().toString(),weight.getText().toString(),age.getText().toString());
            weigh_in_kilos_menu.setText(String.valueOf(weight1));
            height_in_cm_menu.setText(String.valueOf(height1));

            editor = User_Pref.edit();
            editor.putString("Height",height.getText().toString());
            editor.putString("Weight",weight.getText().toString());
            editor.commit();

            Log.d("Heyd",height.getText().toString()+weight.getText().toString());
            handler.postDelayed(new Runnable() {
                public void run() {

                        manager.ChangePageAndStories(view, Back_Button, stories, Presentation_text,layout, height, weight, age, light, mod, very, exteme);
                        manager.SetVisibillityToObjects((CheckBox) findViewById(R.id.Lightly_Active_CB), (CheckBox) findViewById(R.id.Moderate_Active_CB), (CheckBox) findViewById(R.id.Very_Active_CB), (CheckBox) findViewById(R.id.Extremely_Active_CB));
                        manager.SetInvisivillityToObjects( findViewById(R.id.Back_Button) , findViewById(R.id.Front_Button));
                        manager.SetVisibillityToObjects(findViewById(R.id.Back_ButtonOnTheTop));
                        manager.SetTextToEmpty(height,weight,age);

                }
            }, 1000);



        }
        else if (height.getText().length()!=0 && weight.getText().length()!=0 && age.getVisibility()==View.INVISIBLE){


            handler.postDelayed(new Runnable() {
                public void run() {

                    manager.ChangePageAndStories(view, Back_Button, stories, Presentation_text,layout, height, weight, age, light, mod, very, exteme);
                    manager.SetVisibillityToObjects((CheckBox) findViewById(R.id.Lightly_Active_CB), (CheckBox) findViewById(R.id.Moderate_Active_CB), (CheckBox) findViewById(R.id.Very_Active_CB));
                    manager.SetInvisivillityToObjects(findViewById(R.id.Front_Button));
                    manager.SetTextToEmpty(height,weight,age);


                }
            }, 1000);
        }else

            manager.ChangePageAndStories(view, Back_Button, stories, Presentation_text,layout, height, weight, age, light, mod, very, exteme);














    }


    public void CheckBoc_Check(View view) {

             CheckBox x = (CheckBox)findViewById(R.id.Lightly_Active_CB);

             Presentation_text = (TextView) findViewById(R.id.first_steps_text);
             manager.CheckForCheckBoxClicked(height, weight, view, this, layout, height1, weight1, age1, Presentation_text, stories[2], (CheckBox) findViewById(R.id.Lightly_Active_CB), (CheckBox) findViewById(R.id.Moderate_Active_CB), (CheckBox) findViewById(R.id.Very_Active_CB), (CheckBox) findViewById(R.id.Extremely_Active_CB));

        if( x.getText().toString()  =="Male")
        {
            manager.SetVisibillityToObjects(findViewById(R.id.Front_Button));

        }

        if(x.getText().toString() =="Loose Weight"){
            manager.SetInvisivillityToObjects(  findViewById(R.id.Back_ButtonOnTheTop) );

        }






    }

    public void GoToFinalPage(View view) {

        height=(EditText)findViewById(R.id.Height_ED);
        weight=(EditText)findViewById(R.id.Weight_ED);
        age=(EditText)findViewById(R.id.Age_ED);





        if(height.getText().length()!=0 && weight.getVisibility()==View.INVISIBLE && age.getVisibility()==View.INVISIBLE){

            editor.putString("FinalCalories",String.valueOf(manager.getUserFinalCalories()));
            editor.commit();
            Intent goToFinal = new Intent(this,Final_Panel.class);
            startActivity(goToFinal);
            finish();


        }

    }






}
