package com.example.luoxiongzhang.object_class;

import java.io.Serializable;

public class City_list implements Serializable {
    public String city;
    public String area;
    public String id;

    public boolean equals(City_list city_list) {
        if (!this.city.equals(city_list.getCity())){
            return false;
        }
        if (!this.id.equals(city_list.getId())){
            return false;
        }
        if (!this.area.equals(city_list.getArea())){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    public City_list() {
    }
//    public City_list(String city, String area) {
//        this.city = city;
//        this.area = area;
//    }
    public City_list(String city, String area, String id) {
        this.city = city;
        this.area = area;
        this.id = id;
    }
    public String getId(String city){
        return this.id;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return city+"\t"+area;
    }
}
