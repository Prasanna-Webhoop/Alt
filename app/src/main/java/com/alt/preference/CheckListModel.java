package com.alt.preference;

/**
 * Created by student on 13/12/16.
 */

public class CheckListModel {
    private String preferred_email, employee_photo_path, mobile_number, preferred_username,imagePath;

    public CheckListModel(String preferred_email, String employee_photo_path, String mobile_number, String preferred_username, String imagePath) {
        this.preferred_email = preferred_email;
        this.employee_photo_path = employee_photo_path;
        this.mobile_number = mobile_number;
        this.preferred_username = preferred_username;
        this.imagePath = imagePath;
    }

    public String getPreferred_email() {
        return preferred_email;
    }

    public void setPreferred_email(String preferred_email) {
        this.preferred_email = preferred_email;
    }

    public String getEmployee_photo_path() {
        return employee_photo_path;
    }

    public void setEmployee_photo_path(String employee_photo_path) {
        this.employee_photo_path = employee_photo_path;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getPreferred_username() {
        return preferred_username;
    }

    public void setPreferred_username(String preferred_username) {
        this.preferred_username = preferred_username;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
