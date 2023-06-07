package com.example.luoxiongzhang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.luoxiongzhang.object_class.City_list;
import com.google.gson.Gson;
import com.qweather.sdk.bean.air.AirNowBean;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.weather.WeatherDailyBean;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.QWeather;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    City_list city_list = new City_list();
    //CardView实现点击跳转页面未来天气页面
    CardView cv_AQL,cv_weather1,cv_weather2;
    ImageView tv_today_icon,tv_tomorrow_icon,add_city;
    TextView tv_city,tv_temp,tv_case,tv_explain1,tv_explain2,tv_airRegime,tv_AQI,tv_today_maxmintemp,tv_today_case,tv_today_grade,tv_tomorrow_manmintemp,tv_tomorrow_case,tv_tomorrow_grade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据和绑定控件
        ViewInitialize();
        isData();
        //获取intent传过来的城市
        getCity();
        //事件监听
        setOnClick();
    }

    private void isData() {
        ShareData.addCity(MainActivity.this,new City_list("北京市","海淀区","101010200"));
        ShareData.addCity(MainActivity.this,new City_list("武汉市","江夏区","101200105"));
        ShareData.addCity(MainActivity.this,new City_list("成都市","武侯区","101270119"));
//        CityManager.getInstance().getCity_lists().add(new City_list("北京市","海淀区","101010200"));
//        CityManager.getInstance().getCity_lists().add(new City_list("武汉市","江夏区","101200105"));
//        CityManager.getInstance().getCity_lists().add(new City_list("成都市","武侯区","101270119"));
    }

    private void getCity() {
        if (getIntent().getSerializableExtra("City")!=null){
            city_list = (City_list)getIntent().getSerializableExtra("City");
//            Log.d(TAG, "getCity: "+getIntent().getSerializableExtra("City"));
            tv_city.setText(city_list.getArea());
        }else {
//            Log.d(TAG, "getCity: "+city_list.toString());
            city_list = ShareData.get(MainActivity.this,0);
//            Log.d(TAG, "getCity: "+city_list.toString());
            tv_city.setText(city_list.getArea());
        }
    }

    private void setOnClick() {
        add_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddCity.class);
                startActivity(intent);
            }
        });
        cv_weather1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Weather15D.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("15City",city_list);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //view控件绑定和部分初始化
    private void ViewInitialize() {
        //删除安卓自带的menu
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        //textview绑定
        tv_city=findViewById(R.id.tv_city);
        tv_temp=findViewById(R.id.tv_temp);
        tv_case=findViewById(R.id.tv_case);
        tv_explain1=findViewById(R.id.tv_explain1);
        tv_explain2=findViewById(R.id.tv_explain2);
        tv_airRegime=findViewById(R.id.tv_airRegime);
        tv_AQI=findViewById(R.id.tv_AQI);
        tv_today_maxmintemp=findViewById(R.id.tv_today_maxmintemp);
        tv_today_case=findViewById(R.id.tv_today_case);
        tv_today_grade=findViewById(R.id.tv_today_grade);
        tv_tomorrow_manmintemp=findViewById(R.id.tv_tomorrow_manmintemp);
        tv_tomorrow_case=findViewById(R.id.tv_tomorrow_case);
        tv_tomorrow_grade=findViewById(R.id.tv_tomorrow_grade);
        cv_AQL=findViewById(R.id.cv_AQL);
        cv_weather1=findViewById(R.id.cv_weather1);
        cv_weather2=findViewById(R.id.cv_weather2);
        //imageview绑定
        tv_today_icon=findViewById(R.id.tv_today_icon);
        tv_tomorrow_icon=findViewById(R.id.tv_tomorrow_icon);
        add_city=findViewById(R.id.add_city);
        //增加CardView卡片透明度
        cv_AQL.getBackground().setAlpha(40);
        cv_weather1.getBackground().setAlpha(60);
        cv_weather2.getBackground().setAlpha(60);
    }

    @Override
    protected void onStart() {
        super.onStart();
        WeatherInit();
    }
    //在活动开始时初始化天气各个显示
    private void WeatherInit() {
        QWeather.getWeatherNow(MainActivity.this, city_list.getId(), new QWeather.OnResultWeatherNowListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "run:  "+throwable);
            }

            @Override
            public void onSuccess(WeatherNowBean weatherNowBean) {
                if (Code.OK == weatherNowBean.getCode()) {
                    WeatherNowBean.NowBaseBean now = weatherNowBean.getNow();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Log.d(TAG, "run: "+now.getText());
                            tv_temp.setText(now.getTemp()+"°");
                            tv_case.setText(now.getText());
                            tv_explain1.setText("体感"+now.getFeelsLike()+"|"+now.getWindDir()+now.getWindScale()+"|"+"湿度"+now.getHumidity());
                        }
                    });
                } else {
                    //在此查看返回数据失败的原因
                    Code code = weatherNowBean.getCode();
                    Log.i(TAG, "frun:  " + code);
                }
            }
        });
        QWeather.getAirNow(MainActivity.this,city_list.getId(), Lang.ZH_HANS,new QWeather.OnResultAirNowListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "run:  "+throwable);
            }

            @Override
            public void onSuccess(AirNowBean airNowBean) {
                if (Code.OK == airNowBean.getCode()) {
                    AirNowBean.NowBean nowair = airNowBean.getNow();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_airRegime.setText(nowair.getCategory());
                            tv_AQI.setText(nowair.getAqi());
                        }
                    });
                } else {
                    //在此查看返回数据失败的原因
                    Code code = airNowBean.getCode();
                    Log.i(TAG, "frun:  " + code);
                }
            }
        });
        QWeather.getWeather3D(MainActivity.this, city_list.getId(), new QWeather.OnResultWeatherDailyListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "run:  "+throwable);
            }

            @Override
            public void onSuccess(WeatherDailyBean weatherDailyBean) {
                if (Code.OK == weatherDailyBean.getCode()) {
                    List<WeatherDailyBean.DailyBean> weather3D = weatherDailyBean.getDaily();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_today_maxmintemp.setText(weather3D.get(0).getTempMin()+"/"+weather3D.get(0).getTempMax());
                            tv_today_case.setText(weather3D.get(0).getTextDay());
                            tv_today_grade.setText(weather3D.get(0).getWindDirDay());
                            int drawable = getResources().getIdentifier("a"+weather3D.get(0).getIconDay(),"drawable", MainActivity.this.getPackageName());
                            tv_today_icon.setImageResource(drawable);
                            tv_tomorrow_manmintemp.setText(weather3D.get(1).getTempMin()+"/"+weather3D.get(1).getTempMax());
                            tv_tomorrow_case.setText(weather3D.get(1).getTextDay());
                            tv_tomorrow_grade.setText(weather3D.get(1).getWindDirDay());
                            int drawable1 = getResources().getIdentifier("a"+weather3D.get(1).getIconDay(),"drawable", MainActivity.this.getPackageName());
                            tv_tomorrow_icon.setImageResource(drawable1);
                            tv_explain2.setText(compareTemp(weather3D));
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

    //判断每日与今日天气温度
    private String compareTemp(List<WeatherDailyBean.DailyBean> weather3D){
        int a = Integer.parseInt(weather3D.get(0).getTempMax());
        int b = Integer.parseInt(weather3D.get(1).getTempMax());
        String compareTemp;
        if (b-a>0){
            compareTemp = "明天温度比今日温度升高"+String.valueOf(b-a)+"度";
        }if (a-b>0){
            compareTemp = "明天温度比今日温度下降"+String.valueOf(a-b)+"度";
        }else {
            compareTemp = "明天温度与今日温度相同";
        }
        return compareTemp;
    }
}