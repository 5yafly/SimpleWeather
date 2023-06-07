package com.example.luoxiongzhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener;
import com.example.luoxiongzhang.object_class.City_list;
import com.google.gson.Gson;

import java.util.ArrayList;


public class AddCity extends AppCompatActivity {
    RecyclerView RecyclerViewCity;
    ImageView iv_back;
    LinearLayout add_citys;
    CityManager cityManager;
    AddCityBRVAH addCityBRVAH;
    Context context;
    ArrayList<City_list> city_lists = new ArrayList<>();
    private static final String TAG = "AddCity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        cityManager.setCity_lists(ShareData.getAllCity(AddCity.this));
        //各种点击事件的实现
        setOnClick();
        //初始化数据
        //isData();
        //适配器的绑定
        isAdpter();
    }
    private void setOnClick() {
        iv_back=findViewById(R.id.iv_back);
        add_citys=findViewById(R.id.add_citys);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        add_citys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCity.this,CreateCity.class);
                startActivity(intent);
            }
        });
    }

    private void isAdpter() {
        RecyclerViewCity=findViewById(R.id.RecyclerViewCity);
//        Log.d(TAG, "isAdpter: "+CityManager.getCity_lists().size());
        addCityBRVAH = new AddCityBRVAH(R.layout.activity_caty_list,cityManager.getInstance().getCity_lists());
        addCityBRVAH.addChildClickViewIds(R.id.iv_CityDelete,R.id.confirm_city);
        RecyclerViewCity.setAdapter(addCityBRVAH);
        RecyclerViewCity.setLayoutManager(new LinearLayoutManager(AddCity.this,LinearLayoutManager.VERTICAL,false));
        RecyclerViewCity.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        addCityBRVAH.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                switch (view.getId()){
                    case R.id.iv_CityDelete:
                        cityManager.remove(position);
                        ShareData.remove(AddCity.this,position);
                        addCityBRVAH.notifyDataSetChanged();
                        break;
                    case R.id.confirm_city:
                        Intent intent = new Intent(AddCity.this,MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("City",ShareData.get(AddCity.this,position));
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
    }

//    private void isData() {
//        CityManager.getInstance().getCity_lists().add(new City_list("北京市","海淀区","12℃"));
//        CityManager.getInstance().getCity_lists().add(new City_list("武汉市","江夏区","25℃"));
//        CityManager.getInstance().getCity_lists().add(new City_list("成都市","武侯区","20℃"));
//    }

    protected void onResume() {
        super.onResume();
        addCityBRVAH.notifyDataSetChanged();
    }
}