package com.applicationforlife.jamesnikolaidis.myfitnesshelper.Database_Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.sql.Date;

/**
 * Created by James Nikolaidis on 8/23/2016.
 */
public class Database_Fuction_Class extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyFitnessHelper.db";
    public static final String USERS_TABLE_NAME = "Users",FOOD_TABLE_NAME = "Food",EXERCISE_TABLE_NAME = "Exercise";
    public static final String[] USERS_TABLE_COLUMNS = new String[] {"id","Name","Email","Password","Date","Height","Weight","Age"};
    public static final String[] FOOD_TABLE_COLUMNS = new String[]{"foodid","name","calories","serving_type","servingSize","Protein","Carbohydrates","Fat","TransFat","SaturatedFat","VitaminA","VitaminB ","Cholysterol","Sodium"};
    public static final String[] EXERCISE_TABLE_COLUMNS= new String[]{"exid","name","injuryLevel","bodyparttag"};
    public static final String USERS_COLUMN_ID = "id", USERS_COLUMN_NAME = "Name",USERS_COLUMN_Password = "Password",USERS_COLUMN_EMAIL = "Email";
    private static Database_Fuction_Class database = null;



    private   Database_Fuction_Class(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table IF NOT EXISTS "+ USERS_TABLE_NAME +" (id integer primary key, Name varchar,Email varchar,Password varchar,Date varchar ,Height varchar ,Weight varchar ,Age varchar)"
        );
        db.execSQL(
                "create table IF NOT EXISTS "+ FOOD_TABLE_NAME +" (foodid integer primary key, name varchar,calories varchar, serving_type varchar,servingSize varchar,Protein varchar,Carbohydrates varchar ,Fat varchar ,TransFat varchar,SaturatedFat varchar ,VitaminA  varchar, VitaminB varchar ,Cholysterol varchar , Sodium  varchar )" );


        db.execSQL(
                "create table IF NOT EXISTS "+EXERCISE_TABLE_NAME +" (exid integer primary key , name varchar , injuryLevel varchar , bodyparttag varchar)" );

        Log.d("Insert","Database Created");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact(String ...columns_field)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int i=0;
        int j=1;
        for(i=0; i<columns_field.length;i++,j++) {

            contentValues.put(USERS_TABLE_COLUMNS[j],columns_field[i]);
        }

        try{
        db.insert(USERS_TABLE_NAME, null, contentValues);
            Log.d("Insert","Insert Complete");
        }
        catch (Exception ex){
            Log.d("Insert","Insert InComplete");
        }
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Users where id="+id+"", null );
        return res;
    }


    public Cursor getDataEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select id,Name,Email,Password,Height,Weight from Users where Email like'"+email+"'", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USERS_TABLE_NAME);
        return numRows;
    }



    public int numberOfFoodRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FOOD_TABLE_NAME);
        return numRows;
    }

    public int getFoodCo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(foodid) from Food",null);
        res.moveToFirst();
        return res.getInt(0);
    }

    public int getExerciseCounter(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(exid) from Exercise",null);
        res.moveToFirst();
        Log.d("Logggg","Counter ex "+String.valueOf( res.getInt(0)));
        return res.getInt(0);
    }



    public boolean updateContact (int id,String ...columns_field)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int i=0;
        for(i=1; i!=columns_field.length;i++) {
            contentValues.put(USERS_TABLE_COLUMNS[i], columns_field[i]);
        }
        db.update(USERS_TABLE_NAME, contentValues, "id = ? ", new String[] {Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USERS_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Users", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(USERS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }



    public boolean ChechUserInside(String Password,String Email){

        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select Password,Email from "+USERS_TABLE_NAME+" where Password like'"+Password+"' and Email like '"+Email+"'",null);
        cursor.moveToFirst();

        if(cursor.getCount()>0 )
        {
            if(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_Password)).matches(Password)
                    && cursor.getString(cursor.getColumnIndex(USERS_COLUMN_EMAIL)).matches(Email)){
            return true;
            }
               return false;
        }else{
            return false;
        }



    }


    public void InsertNewColumns(String ...ColumnName){
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            for (int i = 0; i != ColumnName.length; i++) {
                db.execSQL("ALTER TABLE " + USERS_TABLE_NAME + " ADD " + ColumnName[i] + " text ");

            }
        }catch (Exception ex){
             Log.d("Error","In the Exception");

        }



    }


   public  static  Database_Fuction_Class getInstance(Context context){
       if(database==null){
           database = new Database_Fuction_Class(context);
       }
       return database;

   }




    public boolean insertFood(String ...columns_field)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int i=0;
        int j=1;
        for(i=0; i<columns_field.length;i++,j++) {

            contentValues.put(FOOD_TABLE_COLUMNS[j],columns_field[i]);
        }

        try{
           db.insert(FOOD_TABLE_NAME, null, contentValues);
            Log.d("Insert","Insert Complete");
        }
        catch (Exception ex){
            Log.d("Insert","Insert InComplete");
        }
        return true;



    }




    public boolean insertExercise(String ...columns_field)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int i=0;
        int j=1;
        for(i=0; i<columns_field.length;i++,j++) {

            contentValues.put(EXERCISE_TABLE_COLUMNS[j],columns_field[i]);
        }

        try{
            db.insert(EXERCISE_TABLE_NAME, null, contentValues);
            Log.d("Insert","Insert Complete");
        }
        catch (Exception ex){
            Log.d("Insert","Insert InComplete");
        }
        return true;



    }





    public Cursor getFoodData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Food where foodid="+id+"", null );
        res.moveToFirst();
        return res;
    }


    public Cursor getExerciseData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Exercise where exid="+id+"", null );
        res.moveToFirst();
        return res;
    }






    public Cursor getFoodDataByName(String FoodName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Food where name like '"+FoodName+"'", null );
        return  res;
    }


    public Cursor getExerciseDataByName(String ExerciseName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Exercise where name like '"+ExerciseName+"'", null );
        return  res;
    }


    public Cursor getExerciseDataByTag(String tag){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("Loggg","The tag"+tag);
        Cursor res =  db.rawQuery( "select name from Exercise where bodyparttag like '"+tag+"'", null );
        return  res;

    }




}



