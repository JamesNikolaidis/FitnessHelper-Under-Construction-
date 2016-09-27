package com.applicationforlife.jamesnikolaidis.myfitnesshelper.General_Functions;

import com.applicationforlife.jamesnikolaidis.myfitnesshelper.Day_Function.Day_Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Nikolaidis on 9/3/2016.
 */
public class CollectDays {
    private List<Day_Class> List_of_Days;

    public CollectDays(){
        List_of_Days = new ArrayList<>();
    }

    public void AddDay(Day_Class day){

        List_of_Days.add(day);
    }

    public Day_Class getDayByPosition(int position){
        return  List_of_Days.get(position);
    }

    public List<Day_Class> getArrayDay(){

        return  List_of_Days;
    }


}
