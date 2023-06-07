package com.example.luoxiongzhang;

import com.example.luoxiongzhang.object_class.City_list;

import java.util.ArrayList;

public class CityManager {
    static ArrayList<City_list> city_lists;
    private static CityManager cityManager;
    public static void setCity_lists(ArrayList<City_list> city_lists) {
        cityManager.city_lists = city_lists;
    }
    public static CityManager getInstance(){
        if(cityManager==null){
            cityManager=new CityManager();
        }
        return cityManager;
    }
    private CityManager(){
        this.city_lists=new ArrayList<>();
    }
    public static ArrayList<City_list> getCity_lists() {
        return city_lists;
    }
    public static void remove(int position){
        city_lists.remove(position);
    }
}
