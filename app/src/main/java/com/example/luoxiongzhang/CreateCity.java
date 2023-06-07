package com.example.luoxiongzhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Range;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.luoxiongzhang.object_class.City_list;
import com.qweather.sdk.bean.air.AirNowBean;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.view.QWeather;

import java.util.ArrayList;
import java.util.List;

public class CreateCity extends AppCompatActivity {
    private static final String TAG = "CreateCity";
    ArrayList<City_list> city_lists = new ArrayList<>();
//    SimpleListItemBRVAH simpleListItemBRVAH;
    TextView mTextView;
    EditText mEditText;
    ImageView mImageView;
    ListView listView;
//    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_city);
        CreateCity context = this;
        initView();
    }
    private void initView() {
        //删除安卓自带的menu
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        mTextView = findViewById(R.id.textview);
        mEditText =  findViewById(R.id.edittext);
        mImageView =  findViewById(R.id.imageview);
        listView =  findViewById(R.id.listview);
        mEditText.getBackground().setAlpha(30);
        //设置删除图片的点击事件
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把EditText内容设置为空
                mEditText.setText("");
                //把ListView隐藏
                listView.setVisibility(View.GONE);
            }
        });

        //EditText添加监听
        mEditText.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}//文本改变之前执行

            @Override
            //文本改变的时候执行
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果长度为0
                if (s.length() == 0) {
                    //隐藏“删除”图片
                    mImageView.setVisibility(View.GONE);
                } else {//长度不为0
                    //显示“删除图片”
                    mImageView.setVisibility(View.VISIBLE);
                    //显示ListView
                    showListView();
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }//文本改变之后执行
        });

        mTextView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //如果输入框内容为空，提示请输入搜索内容
                if(TextUtils.isEmpty(mEditText.getText().toString().trim())){
                    Toast.makeText(CreateCity.this,"请输入您要搜索的内容",Toast.LENGTH_LONG).show();
                }else {
                    //判断cursor是否为空
                    city_lists.clear();
                    showListView();
                    }
                }
        });
    }

    private void showListView() {
        listView.setVisibility(View.VISIBLE);
        //获得输入的内容
        String str = mEditText.getText().toString().trim();
        QWeather.getGeoCityLookup(CreateCity.this, str, new QWeather.OnResultGeoListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "run:  "+throwable);
            }
            @Override
            public void onSuccess(GeoBean geoBean) {
                if (Code.OK == geoBean.getCode()) {
                    List<GeoBean.LocationBean>  locationBeans= geoBean.getLocationBean();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i=0;i<locationBeans.size();i++){
                                city_lists.add(new City_list(locationBeans.get(i).getAdm2(),locationBeans.get(i).getName(),locationBeans.get(i).getId()));
                            }
//                            simpleListItemBRVAH  = new SimpleListItemBRVAH(android.R.layout.simple_expandable_list_item_1,city_lists);
                            ArrayAdapter adapter = new ArrayAdapter<>(CreateCity.this,android.R.layout.simple_expandable_list_item_1,city_lists);
//                            listView.setLayoutManager(new LinearLayoutManager(CreateCity.this));
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    ShareData.addCity(CreateCity.this,city_lists.get(position));
                                    Intent intent = new Intent(CreateCity.this,MainActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("City",city_lists.get(position));
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    });
                } else {
                    //在此查看返回数据失败的原因
                    Code code = geoBean.getCode();
                    Log.i(TAG, "frun:  " + code);
                }
            }
        });
    }
}
