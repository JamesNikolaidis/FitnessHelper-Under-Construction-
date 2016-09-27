package com.applicationforlife.jamesnikolaidis.myfitnesshelper.Panels;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Database_Classes.Database_Fuction_Class;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Fragments.FragmentMainActivity;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.R;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Views_Classes.Manage_Views;

/**
 * Created by James Nikolaidis on 8/30/2016.
 */
public class Final_Panel extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static Manage_Views manager;
    private static Spinner spinner1,spinner2,spinner3,spinner4;
    LinearLayout layout;
    private static  TextView  weigh_in_kilos_menu, height_in_cm_menu;
    private SharedPreferences User_Pref ;
    private Database_Fuction_Class Database_Object;
    private Cursor cursor;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout2);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        manager = new Manage_Views(getApplicationContext());
        User_Pref= getSharedPreferences("Pref",MODE_PRIVATE);
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout2);
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
        spinner1 = (Spinner)findViewById(R.id.Spinner);
        spinner2 = (Spinner)findViewById(R.id.Spinner2);
        spinner3 = (Spinner)findViewById(R.id.Spinner3);
        manager.makeSpinnerDeclaration(this,spinner1,spinner2,spinner3);
        Database_Object = Database_Fuction_Class.getInstance(getApplicationContext());
        Database_Object.InsertNewColumns("Strong_Point","Weak_Point");
        manager.SetSpinnerSelectedListener(spinner1);
        manager.SetSpinnerSelectedListener(spinner2);
        manager.SetSpinnerSelectedListener(spinner3);









    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
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


    public void TransportData(View view) {
        weigh_in_kilos_menu = (TextView) findViewById(R.id.Weight_in_Kilos);
        height_in_cm_menu = (TextView)findViewById(R.id.Height_In_Cm);
        weigh_in_kilos_menu.setText("ffff");
        height_in_cm_menu.setText("sssss");
    }


    public void GoToApplicationMainPage(View view) {

        Intent goToPanel = new Intent(Final_Panel.this, FragmentMainActivity.class);
        startActivity(goToPanel);

    }


}
