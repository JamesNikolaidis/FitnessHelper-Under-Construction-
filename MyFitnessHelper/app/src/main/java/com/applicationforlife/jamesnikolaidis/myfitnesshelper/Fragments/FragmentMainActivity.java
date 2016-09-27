package com.applicationforlife.jamesnikolaidis.myfitnesshelper.Fragments;

import android.app.*;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.applicationforlife.jamesnikolaidis.myfitnesshelper.R;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Views_Classes.Manage_Views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by James Nikolaidis on 9/2/2016.
 */
public class FragmentMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static Manage_Views manager;
    private static  TextView  weigh_in_kilos_menu, height_in_cm_menu;
    private SharedPreferences User_Pref ;
    private SharedPreferences.Editor editor;
    private  static ViewPager viewPager;
    private  static Date date ;
    private  int TextViewSelectedId ;
    private static  double CaloriesSum = 0;
    private ArrayList<String> BFArray,BRArray,LYArray,PWArray,DIArray,BBArray; ;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout3);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        date = new Date();
        final DateFormat format = new SimpleDateFormat("E ,MMM d y ");
        final Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        final Date[] tomorrow = {calendar.getTime()};

        manager = new Manage_Views(getApplicationContext());
        User_Pref= getSharedPreferences("Pref",MODE_PRIVATE);
        editor = User_Pref.edit();
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout3);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                weigh_in_kilos_menu = (TextView) findViewById(R.id.Weight_in_Kilos);
                height_in_cm_menu = (TextView)findViewById(R.id.Height_In_Cm);
                weigh_in_kilos_menu.setText(User_Pref.getString("Height","n"));
                height_in_cm_menu.setText(User_Pref.getString("Weight","n"));


            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);



      viewPager = (ViewPager)findViewById(R.id.fragment_panel);
      viewPager.setAdapter(new FragmentPageManager(getSupportFragmentManager(),FragmentMainActivity.this));
        viewPager.setOffscreenPageLimit(3-1);



   viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
       @Override
       public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
       }

       @Override
       public void onPageSelected(int position) {
           if(position==0) {

               Screen1.RefreshData();
           }else if(position==2){

               if(Screen3.bodypart.matches("")){  Screen3.GetInstance().ActivateDialog();}

           }

               setTitle(String.valueOf(format.format(tomorrow[0])));
               calendar.add(Calendar.DAY_OF_YEAR, 1);
               tomorrow[0] = calendar.getTime();

       }

       @Override
       public void onPageScrollStateChanged(int state) {
       }
   });




        BFArray=new ArrayList<>();
        BRArray=new ArrayList<>();
        LYArray=new ArrayList<>();
        PWArray=new ArrayList<>();
        DIArray=new ArrayList<>();
        BBArray=new ArrayList<>();

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    public void MenuItemSelect(MenuItem item) {


        manager.MenuManager(item,this);

    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }




    public void LayoutClickMenu(View view) {
       TextViewSelectedId =view.getId();
       Intent intent =  manager.Screen2ClickListener(view,FragmentMainActivity.this);
       startActivityForResult(intent,1);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ListView v;
        ArrayAdapter adapter;
        int id = manager.GetMealId();
        switch (id){

            case R.id.Breakfast:
               v = (ListView)findViewById(R.id.Breakfast_list);
                BFArray.add(data.getStringExtra("food")+"     "+"Calories="+data.getStringExtra("Calories"));
                CaloriesSum = CaloriesSum + Float.parseFloat(data.getStringExtra("Calories"));
                editor = User_Pref.edit();
                editor.putString("Calories",String.valueOf(CaloriesSum));
                editor.commit();
                adapter = new ArrayAdapter(getApplicationContext(),R.layout.simpletext,R.id.ll,BFArray);

                v.setAdapter(adapter);
                break;

            case R.id.Brunch:
                 v = (ListView)findViewById(R.id.Brunch_list);
                BRArray.add(data.getStringExtra("food")+"     "+"Calories="+data.getStringExtra("Calories"));
                CaloriesSum = CaloriesSum + Float.parseFloat(data.getStringExtra("Calories"));
                editor = User_Pref.edit();
                editor.putString("Calories",String.valueOf(CaloriesSum));
                editor.commit();
                 adapter = new ArrayAdapter(getApplicationContext(),R.layout.simpletext,R.id.ll,BRArray);
                v.setAdapter(adapter);
                break;

            case R.id.Lunch:
                v = (ListView)findViewById(R.id.Lunch_list);
                LYArray.add(data.getStringExtra("food")+"     "+"Calories="+data.getStringExtra("Calories"));
                CaloriesSum = CaloriesSum + Float.parseFloat(data.getStringExtra("Calories"));
                editor = User_Pref.edit();
                editor.putString("Calories",String.valueOf(CaloriesSum));
                editor.commit();
                adapter = new ArrayAdapter(getApplicationContext(),R.layout.simpletext,R.id.ll,LYArray);
                v.setAdapter(adapter);
                break;

            case R.id.Post:
               v = (ListView)findViewById(R.id.Post_list);
                PWArray.add(data.getStringExtra("food")+"     "+"Calories="+data.getStringExtra("Calories"));
                CaloriesSum = CaloriesSum + Float.parseFloat(data.getStringExtra("Calories"));
                editor = User_Pref.edit();
                editor.putString("Calories",String.valueOf(CaloriesSum));
                editor.commit();
                adapter = new ArrayAdapter(getApplicationContext(),R.layout.simpletext,R.id.ll,PWArray);
                v.setAdapter(adapter);
                break;

            case R.id.Dinner:
                 v = (ListView)findViewById(R.id.Dinner_list);
                DIArray.add(data.getStringExtra("food")+"     "+"Calories="+data.getStringExtra("Calories"));
                CaloriesSum = CaloriesSum + Float.parseFloat(data.getStringExtra("Calories"));
                editor = User_Pref.edit();
                editor.putString("Calories",String.valueOf(CaloriesSum));
                editor.commit();
                adapter = new ArrayAdapter(getApplicationContext(),R.layout.simpletext,R.id.ll,DIArray);
                v.setAdapter(adapter);
                break;

            default:
                 v = (ListView)findViewById(R.id.Bed_list);
                BBArray.add(data.getStringExtra("food")+"     "+"Calories="+data.getStringExtra("Calories"));
                CaloriesSum = CaloriesSum + Float.parseFloat(data.getStringExtra("Calories"));
                editor = User_Pref.edit();
                editor.putString("Calories",String.valueOf(CaloriesSum));
                editor.commit();
                adapter = new ArrayAdapter(getApplicationContext(),R.layout.simpletext,R.id.ll,BBArray);
                v.setAdapter(adapter);
                break;


        }










    }


    public void CompleteClick(MenuItem item) {
    }
}
