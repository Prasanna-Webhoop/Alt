package com.alt.preference.model;

/**
 * Created by jupitor on 29/11/16.
 */
public class CityModel {


    private int id;
    private String city_name;
    private String country_code;
    private int country_id;

    public CityModel(int id, String city_name, String country_code, int country_id) {
        this.id = id;
        this.city_name = city_name;
        this.country_code = country_code;
        this.country_id = country_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}
