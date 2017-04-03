package com.alt.preference.model;

/**
 * Created by jupitor on 29/11/16.
 */

public class CountryModel {

    /*
    {
            "id": 1,
            "sortname": "AF",
            "name": "Afghanistan",
            "nationality": "Afghani",
            "phone_code": "93",
            "num_code": "4",
            "iso3": "AFG"
        }*/

    private String sortname, name, nationality, phone_code, num_code, iso3;
    private int id;

    public CountryModel(int id, String sortname, String name, String nationality, String phone_code, String num_code, String iso3) {
        this.id = id;
        this.sortname = sortname;
        this.name = name;
        this.nationality = nationality;
        this.phone_code = phone_code;
        this.num_code = num_code;
        this.iso3 = iso3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone_code() {
        return phone_code;
    }

    public void setPhone_code(String phone_code) {
        this.phone_code = phone_code;
    }

    public String getNum_code() {
        return num_code;
    }

    public void setNum_code(String num_code) {
        this.num_code = num_code;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }
}
