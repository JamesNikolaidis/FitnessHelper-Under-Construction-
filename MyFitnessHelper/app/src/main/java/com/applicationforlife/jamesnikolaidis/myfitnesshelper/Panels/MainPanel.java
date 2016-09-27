package com.applicationforlife.jamesnikolaidis.myfitnesshelper.Panels;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Database_Classes.Database_Fuction_Class;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Fragments.FragmentMainActivity;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.R;
import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Views_Classes.Manage_Views;

import java.util.Random;

/**
 * Created by James Nikolaidis on 8/23/2016.
 */
public class MainPanel extends AppCompatActivity{
    private EditText name,password,email,birth;
    private LinearLayout layout_for_names , layout_for_other;
    private static Manage_Views manager;
    private  String Preferenc1_Name1 = "Pref";
    private SharedPreferences pref ;
    private SharedPreferences.Editor editor ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_panel);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        manager= new Manage_Views(getApplicationContext());

        pref = getSharedPreferences(Preferenc1_Name1,MODE_PRIVATE);
        editor =pref.edit();


         }


    public void Sign_In(View view) {

        name = (EditText)findViewById(R.id.Name_ED);
        password=(EditText)findViewById(R.id.Password_ED);
        email=(EditText)findViewById(R.id.Email_ED);
        birth=(EditText)findViewById(R.id.Birth_ED);
        layout_for_names = (LinearLayout) findViewById(R.id.layout1);
        layout_for_other =(LinearLayout)findViewById(R.id.layout2);




        if(name.getText().toString().length()>0) {

            Database_Fuction_Class database = Database_Fuction_Class.getInstance(getApplicationContext());
            if (database.ChechUserInside(password.getText().toString(),email.getText().toString()) ) {

                Toast.makeText(getApplicationContext(), "User Already in Database. Please Log in", Toast.LENGTH_LONG).show();
                manager.SetVisibillityToObjects(name,password,layout_for_names);
                manager.SetTextToEmpty(name,password,password);
                name.setHint("Give Email");



                manager.SetInvisivillityToObjects(email,birth,layout_for_other);
            } else {

                Toast.makeText(getApplicationContext(),"User Register. Please wait.", Toast.LENGTH_LONG).show();;
                editor.putString("User_Email",email.getText().toString());
                editor.commit();
                database.insertContact(name.getText().toString(),email.getText().toString(),password.getText().toString(),birth.getText().toString(),"","","");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                          /*
                          Intent Email_Sender = new Intent(Intent.ACTION_SEND);
                          Email_Sender.setType("message/rfc822");
                          Email_Sender.setData(Uri.parse("mailto:dimitriosnikolaidis@hotmail.com"));
                          Email_Sender.putExtra(Intent.EXTRA_SUBJECT,"Profil Confirm");
                          Email_Sender.putExtra(Intent.EXTRA_TEXT,"Paste the code below to activate the registration" + String.valueOf(random.nextInt()));
                         try {
                             startActivity(Email_Sender);
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(MainPanel.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                        */

                        Intent passenger = new Intent(MainPanel.this, Sign_In_SecondPanel.class);
                        startActivity(passenger);
                    }
                }, 500);
            }
        }else
        {
            manager.SetVisibillityToObjects(name,password,email,birth,layout_for_names,layout_for_other);
            manager.SetHintToTextes(new String[]{"Name","Password","Email","Date of Birth"},name,password,email,birth);

        }





    }




    public void Log_In(View view) {
        name = (EditText)findViewById(R.id.Name_ED);
        password=(EditText)findViewById(R.id.Password_ED);
        email=(EditText)findViewById(R.id.Email_ED);
        birth=(EditText)findViewById(R.id.Birth_ED);
        layout_for_names = (LinearLayout) findViewById(R.id.layout1);




        if(name.getText().toString().length()>0) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("User_Email",name.getText().toString());
            editor.commit();
            Database_Fuction_Class database = Database_Fuction_Class.getInstance(getApplicationContext());
            if (database.ChechUserInside(password.getText().toString(),name.getText().toString())) {


                Toast.makeText(getApplicationContext(), "Welcome "+name.getText().toString(), Toast.LENGTH_LONG).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        manager.SetTextToEmpty(name,password,password);
                        Intent passenger = new Intent(MainPanel.this, FragmentMainActivity.class);
                        startActivity(passenger);
                    }
                }, 500);
            } else {


            }
        }else
        {
            manager.SetVisibillityToObjects(name,password,layout_for_names);
            manager.SetInvisivillityToObjects(email,birth,layout_for_other);
            name.setHint("Give Email");

        }





    }

    public void SponsorClass(View view) {

            manager.Sponsor_Function(view,getApplicationContext());
    }


}
