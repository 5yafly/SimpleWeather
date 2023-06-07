package com.example.luoxiongzhang;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.luoxiongzhang.object_class.DailyBean;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.weather.WeatherDailyBean;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.QWeather;

import java.util.List;


public class WeatherFragment extends Fragment {
    TextView week_maxmintemp,week_describe,tv_sunrise,tv_sunset,tv_humidity,tv_pressure,tv_precip,tv_vis,tv_windSpeedDay,tv_uvIndex,tv_cloud;
    private static final String TAG = "WeatherFragment";
    String week;
    String id;
    int position;
    public WeatherFragment() {
    }
    public WeatherFragment(String week,String id,int position) {
        this.week = week;
        this.id = id;
        this.position = position;
    }

    public static WeatherFragment newInstance(String week,String id,int position) {
        WeatherFragment fragment = new WeatherFragment(week,id,position);
        return fragment;
    }

    public String getWeek() {
        return week;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        isBind(view);
        return view;
    }
    private void isBind(View view) {
            LinearLayout linearLayout = view.findViewById(R.id.week_bk);
            CardView cardView = view.findViewById(R.id.week_cardview);
            week_maxmintemp = view.findViewById(R.id.week_maxmintemp);
            week_describe = view.findViewById(R.id.week_describe);
            tv_sunrise = view.findViewById(R.id.tv_sunrise);
            tv_sunset = view.findViewById(R.id.tv_sunset);
            tv_humidity = view.findViewById(R.id.tv_humidity);
            tv_pressure = view.findViewById(R.id.tv_pressure);
            tv_precip = view.findViewById(R.id.tv_precip);
            tv_vis = view.findViewById(R.id.tv_vis);
            tv_windSpeedDay = view.findViewById(R.id.tv_windSpeedDay);
            tv_uvIndex = view.findViewById(R.id.tv_uvIndex);
            tv_cloud = view.findViewById(R.id.tv_cloud);
            linearLayout.getBackground().setAlpha(0);
            cardView.getBackground().setAlpha(40);
    }

    @Override
    public void onStart() {
        super.onStart();
        QWeather.getWeather15D(getContext(), id, new QWeather.OnResultWeatherDailyListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "run:  "+throwable);
            }

            @Override
            public void onSuccess(WeatherDailyBean weatherDailyBean) {
                if (Code.OK == weatherDailyBean.getCode()) {
                    List<WeatherDailyBean.DailyBean> now = weatherDailyBean.getDaily();
                    getActivity().runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            week_maxmintemp.setText(now.get(position).getTempMax()+"/"+now.get(position).getTempMin()+"°");
                            week_describe.setText(now.get(position).getTextDay()+"·"+now.get(position).getWindDirDay()+"\t"+now.get(position).getWindScaleDay()+"级"+"·"+"夜晚风力"+now.get(position).getWindScaleNight()+"级");
                            tv_sunrise.setText(now.get(position).getSunrise());
                            tv_sunset.setText(now.get(position).getSunset());
                            tv_humidity.setText(now.get(position).getHumidity()+"%");
                            tv_pressure.setText(now.get(position).getPressure()+"hPa");
                            tv_precip.setText(now.get(position).getPrecip()+"mm");
                            tv_vis.setText(now.get(position).getVis()+"m");
                            tv_windSpeedDay.setText(now.get(position).getWindSpeedDay()+"m/s");
                            tv_uvIndex.setText(now.get(position).getUvIndex());
                            tv_cloud.setText(now.get(position).getCloud()+"%");
                        }
                    });
                } else {
                    //在此查看返回数据失败的原因
                    Code code = weatherDailyBean.getCode();
                    Log.i(TAG, "frun:  " + code);
                }
            }
        });
    }
}