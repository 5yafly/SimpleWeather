package com.example.luoxiongzhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.luoxiongzhang.object_class.City_list;
import com.example.luoxiongzhang.object_class.DailyBean;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.weather.WeatherDailyBean;
import com.qweather.sdk.view.QWeather;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Weather15D extends AppCompatActivity {
    private static String[] weeks = {"周天","周一","周二","周三","周四","周五","周六"};
    City_list city_list;
    private static final String TAG = "Weather15D";
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ArrayList<WeatherFragment> weatherFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather15_d);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        //获取主界面传过来数据
        city_list= (City_list)getIntent().getSerializableExtra("15City");
        //对适配器添加数据
        initData();
        tabLayout = findViewById(R.id.tab);
        viewPager2 = findViewById(R.id.vp);
        FragmentStateAdapter fragmentStateAdapter = new FragmentStateAdapter(getSupportFragmentManager(),getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return weatherFragments.get(position);
            }
            @Override
            public int getItemCount() {
                return weatherFragments.size();
            }
        };
        viewPager2.setAdapter(fragmentStateAdapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(weatherFragments.get(position).getWeek());
            }
        });
        tabLayoutMediator.attach();
    }
    //对适配器添加数据
    private void initData() {
        weatherFragments = new ArrayList<>();
        for (int i=0;i<=14;i++){
            weatherFragments.add(WeatherFragment.newInstance(getWeek(i),city_list.getId(),i));
        }
    }
    //判断星期几
    public static String getWeek(int p) {
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = formatter.format(todayDate);
        Calendar c = Calendar.getInstance();
        try {
            //pTime
            c.setTime(formatter.parse(todayString));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weeks[(c.get(Calendar.DAY_OF_WEEK)-1+p)%7];
    }
}