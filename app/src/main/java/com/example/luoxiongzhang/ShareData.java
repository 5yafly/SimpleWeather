package com.example.luoxiongzhang;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.luoxiongzhang.object_class.City_list;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ShareData {
    public static City_list get(Context context, int position){
        SharedPreferences sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE);
        String str_cities = sharedPreferences.getString("cities","");
        Gson gson = new Gson();
        ArrayList<City_list> city_lists = gson.fromJson(str_cities,new TypeToken<List<City_list>>(){}.getType());
        if (city_lists==null){
            return new City_list();
        }
        return city_lists.get(position);
    }
    public static void remove(Context context, int position){
        SharedPreferences sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE);
        String str_cities = sharedPreferences.getString("cities","");
        Gson gson = new Gson();
        ArrayList<City_list> city_lists = gson.fromJson(str_cities,new TypeToken<List<City_list>>(){}.getType());
        if (city_lists==null){
            return;
        }
        city_lists.remove(position);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cities",gson.toJson(city_lists));
        editor.apply();
    }
    public static void deleteCity(Context context, City_list city_list){
        SharedPreferences sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE);
        String str_cities = sharedPreferences.getString("cities","");
        Gson gson = new Gson();
        ArrayList<City_list> city_lists = gson.fromJson(str_cities,new TypeToken<List<City_list>>(){}.getType());
        if (city_lists==null){
            return;
        }
        if (city_lists.contains(city_list)){
            city_lists.remove(city_list);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cities",gson.toJson(city_lists));
        editor.apply();
    }
    public static void addCity(Context context, City_list city_list){
        boolean a=true;
        SharedPreferences sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE);
        String str_cities = sharedPreferences.getString("cities","");
        Gson gson = new Gson();
        ArrayList<City_list> city_lists = gson.fromJson(str_cities,new TypeToken<List<City_list>>(){}.getType());
        if (city_lists==null){
            city_lists = new ArrayList<>();
        }
        for (City_list city_list1 : city_lists){
            if (city_list1.equals(city_list)){
                a=false;
            }
        }
        if (a){
            city_lists.add(city_list);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cities",gson.toJson(city_lists));
            editor.apply();
        }
    }
    public static ArrayList<City_list> getAllCity(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE);
        String str_cities = sharedPreferences.getString("cities","");
        Gson gson = new Gson();
        ArrayList<City_list> city_lists = gson.fromJson(str_cities,new TypeToken<List<City_list>>(){}.getType());
        if (city_lists==null){
            city_lists = new ArrayList<>();
        }
       return city_lists;
    }
}
