package com.alt.preference;

/**
 * Created by jupitor on 30/11/16.
 */

public class CompanyInfoModel {

    private int id;
    private String company_name;
    private String company_code;
    private String company_registration_number;
    private String company_address;
    private String company_logo;
    private String company_registration_year;
    private String is_configuration_complated;
    private int step;
    private String created_at;
    private String updated_at;
    private String color;

    public CompanyInfoModel(int id, String company_name, String company_code, String company_registration_number, String company_address, String company_logo, String company_registration_year, String is_configuration_complated, int step, String created_at, String updated_at, String color) {
        this.id = id;
        this.company_name = company_name;
        this.company_code = company_code;
        this.company_registration_number = company_registration_number;
        this.company_address = company_address;
        this.company_logo = company_logo;
        this.company_registration_year = company_registration_year;
        this.is_configuration_complated = is_configuration_complated;
        this.step = step;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_registration_number() {
        return company_registration_number;
    }

    public void setCompany_registration_number(String company_registration_number) {
        this.company_registration_number = company_registration_number;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getCompany_registration_year() {
        return company_registration_year;
    }

    public void setCompany_registration_year(String company_registration_year) {
        this.company_registration_year = company_registration_year;
    }

    public String getIs_configuration_complated() {
        return is_configuration_complated;
    }

    public void setIs_configuration_complated(String is_configuration_complated) {
        this.is_configuration_complated = is_configuration_complated;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
