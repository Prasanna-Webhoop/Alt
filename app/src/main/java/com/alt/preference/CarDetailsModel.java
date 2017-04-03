package com.alt.preference;

/**
 * Created by student on 31/01/17.
 */

public class CarDetailsModel {
    private int id;
    private int user_id;
    private String car_plate_number;
    private String car_brand;
    private String car_type;
    private String created_at;
    private String updated_at;

    public CarDetailsModel(int id, int user_id, String car_plate_number, String car_brand, String car_type, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.car_plate_number = car_plate_number;
        this.car_brand = car_brand;
        this.car_type = car_type;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCar_plate_number() {
        return car_plate_number;
    }

    public void setCar_plate_number(String car_plate_number) {
        this.car_plate_number = car_plate_number;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
