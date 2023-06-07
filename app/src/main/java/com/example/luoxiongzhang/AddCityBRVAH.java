package com.example.luoxiongzhang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.luoxiongzhang.object_class.City_list;

import java.util.List;

public class AddCityBRVAH extends BaseQuickAdapter<City_list, BaseViewHolder> {
    List<City_list> city_lists;
    public AddCityBRVAH(int layoutResId, @Nullable List<City_list> data) {
        super(layoutResId, data);
        this.city_lists=data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, City_list city_list) {
        baseViewHolder.setText(R.id.addcity_city,city_list.getCity());
        baseViewHolder.setText(R.id.addcity_area,city_list.getArea());
    }
}
