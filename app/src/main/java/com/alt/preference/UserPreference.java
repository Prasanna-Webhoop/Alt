package com.alt.preference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.alt.base.AppController;
import com.alt.preference.model.CityModel;
import com.alt.preference.model.CountryModel;
import com.alt.preference.model.CustomListModel;
import com.alt.preference.model.UserInfoModel;
import com.alt.utils.Constants;
import com.alt.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by jupitor on 29/11/16.
 */

public class UserPreference {

    SharedPreferences objSharedPreferences;
    AppController objAppController;
    private ArrayList<CountryModel> arrCountryModel = new ArrayList<CountryModel>();
    private ArrayList<CityModel> arrCityModel = new ArrayList<CityModel>();
    private ArrayList<CompanyInfoModel> arrCompanyInfoModel = new ArrayList<CompanyInfoModel>();
    private ArrayList<UserInfoModel> arrUserInfoModel = new ArrayList<UserInfoModel>();
    //User information from the preferences.
    private String profileImageUrl, userName;

    public UserPreference(Activity objActvity) {
        objAppController = (AppController) objActvity.getApplication();
        objSharedPreferences = (SharedPreferences) objAppController.get(Constants.OBJECT_USER_PREFERENCE);
    }

    public void clearPreference() {
        objSharedPreferences.edit().clear().commit();

        // yes user can edit all fields in all edit forms.
        setFlow(true);
    }

    /*
    * Set Country data within sharedpreference.
    * */
    public void setCountryData(String data) {
        objSharedPreferences.edit().putString(Constants.PREF_COUNTRIES, data).commit();
    }

    /*
    * Set Country data within sharedpreference.
    * */
    public void setCompanyInfoData(String data) {
        objSharedPreferences.edit().putString(Constants.PREF_COMPANY_INFO, data).commit();
    }

    /*
    * Set City data within sharedpreference.
    * */
    public void setCityData(String data) {
        objSharedPreferences.edit().putString(Constants.PREF_CIITES, data).commit();
    }

    /*
    * Set User Info data within sharedpreference.
    * */
    public void setUserInfoData(String data) {
        objSharedPreferences.edit().putString(Constants.PREF_USER_INFO, data).commit();
    }

    public void setUserInfoData(UserInfoModel userInfoModel) {

        JSONObject objJsonObject = new JSONObject();
        try {
            objJsonObject.put("id", userInfoModel.getId().toString());

            objJsonObject.put("name", userInfoModel.getName());
            objJsonObject.put("first_name", userInfoModel.getFirst_name());
            objJsonObject.put("middle_name", userInfoModel.getMiddle_name());
            objJsonObject.put("last_name", userInfoModel.getLast_name());
            objJsonObject.put("email", userInfoModel.getEmail());
//                        Constants.BASE + objJsonObject.put("photo"),
            objJsonObject.put("photo", userInfoModel.getPhoto());
            objJsonObject.put("company_id", userInfoModel.getCompany_id());
            objJsonObject.put("ic_passport", userInfoModel.getIc_passport());
            objJsonObject.put("gender", userInfoModel.getGender());
            objJsonObject.put("dob", userInfoModel.getDob());
            objJsonObject.put("birth_country", userInfoModel.getBirth_country());
            objJsonObject.put("birth_region", userInfoModel.getBirth_region());
            objJsonObject.put("birth_city", userInfoModel.getBirth_city());
            objJsonObject.put("marital_status", userInfoModel.getMarital_status());
            objJsonObject.put("ethnicity", userInfoModel.getEthnicity());
            objJsonObject.put("religion", userInfoModel.getReligion());
            objJsonObject.put("address", userInfoModel.getAddress());
            objJsonObject.put("nationality", userInfoModel.getNationality());
            objJsonObject.put("cv", userInfoModel.getCv());
            objJsonObject.put("offer_letter_sign", userInfoModel.getOffer_letter_sign());
            objJsonObject.put("is_offer_letter_signed", userInfoModel.getIs_offer_letter_signed());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        objSharedPreferences.edit().putString(Constants.PREF_USER_INFO, objJsonObject.toString()).commit();
    }


    public ArrayList<CountryModel> getCountryModel() {
        try {
            arrCountryModel.clear();
            /*{
                "id": 1,
                    "sortname": "AF",
                    "name": "Afghanistan",
                    "nationality": "Afghani",
                    "phone_code": "93",
                    "num_code": "4",
                    "iso3": "AFG"
            }
            */
            JSONArray objJsonArray = new JSONArray(objSharedPreferences.getString(Constants.PREF_COUNTRIES, ""));
            for (int index = 0; index < objJsonArray.length(); index++) {
                arrCountryModel.add(new CountryModel(
                        Integer.parseInt(objJsonArray.getJSONObject(index).getString("id")),
                        objJsonArray.getJSONObject(index).getString("sortname"),
                        objJsonArray.getJSONObject(index).getString("name"),
                        objJsonArray.getJSONObject(index).getString("nationality"),
                        objJsonArray.getJSONObject(index).getString("phone_code"),
                        objJsonArray.getJSONObject(index).getString("num_code"),
                        objJsonArray.getJSONObject(index).getString("iso3")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrCountryModel;
    }

    public ArrayList<CustomListModel> getCustomNationalityModel(int tag) {
        try {
            ArrayList<CustomListModel> arrCustomListModels = new ArrayList<>();
            /*{
                "id": 1,
                    "sortname": "AF",
                    "name": "Afghanistan",
                    "nationality": "Afghani",
                    "phone_code": "93",
                    "num_code": "4",
                    "iso3": "AFG"
            }
            */

            JSONArray objJsonArray = new JSONArray(objSharedPreferences.getString(Constants.PREF_COUNTRIES, ""));
            for (int index = 0; index < objJsonArray.length(); index++) {
                arrCustomListModels.add(new CustomListModel(
                        Integer.parseInt(objJsonArray.getJSONObject(index).getString("id")),
                        objJsonArray.getJSONObject(index).getString("nationality"),
                        tag));
            }
            if (arrCustomListModels.size() > 0) {
                return arrCustomListModels;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<CustomListModel> getCustomCountryModel(int tag) {
        try {
            ArrayList<CustomListModel> arrCustomListModels = new ArrayList<>();
            /*{
                "id": 1,
                    "sortname": "AF",
                    "name": "Afghanistan",
                    "nationality": "Afghani",
                    "phone_code": "93",
                    "num_code": "4",
                    "iso3": "AFG"
            }
            */
            JSONArray objJsonArray = new JSONArray(objSharedPreferences.getString(Constants.PREF_COUNTRIES, ""));
            for (int index = 0; index < objJsonArray.length(); index++) {
                arrCustomListModels.add(new CustomListModel(
                        Integer.parseInt(objJsonArray.getJSONObject(index).getString("id")),
                        objJsonArray.getJSONObject(index).getString("name"),
                        tag));
            }
            if (arrCustomListModels.size() > 0) {
                return arrCustomListModels;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<CustomListModel> getCustomCountryCodeModel(int tag) {
        try {
            ArrayList<CustomListModel> arrCustomListModels = new ArrayList<>();
            /*{
                "id": 1,
                    "sortname": "AF",
                    "name": "Afghanistan",
                    "nationality": "Afghani",
                    "phone_code": "93",
                    "num_code": "4",
                    "iso3": "AFG"
            }
            */
            JSONArray objJsonArray = new JSONArray(objSharedPreferences.getString(Constants.PREF_COUNTRIES, ""));
            for (int index = 0; index < objJsonArray.length(); index++) {
                arrCustomListModels.add(new CustomListModel(
                        Integer.parseInt(objJsonArray.getJSONObject(index).getString("id")),
                        objJsonArray.getJSONObject(index).getString("name") + " (" + objJsonArray.getJSONObject(index).getString("phone_code") + ")",
                        tag));
            }
            if (arrCustomListModels.size() > 0) {
                return arrCustomListModels;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<CustomListModel> getCustomCountryCodeModelForAddress(int tag) {
        try {
            ArrayList<CustomListModel> arrCustomListModels = new ArrayList<>();
            /*{
                "id": 1,
                    "sortname": "AF",
                    "name": "Afghanistan",
                    "nationality": "Afghani",
                    "phone_code": "93",
                    "num_code": "4",
                    "iso3": "AFG"
            }
            */
            JSONArray objJsonArray = new JSONArray(objSharedPreferences.getString(Constants.PREF_COUNTRIES, ""));
            for (int index = 0; index < objJsonArray.length(); index++) {
                arrCustomListModels.add(new CustomListModel(
                        Integer.parseInt(objJsonArray.getJSONObject(index).getString("id")),
                        objJsonArray.getJSONObject(index).getString("name"),
                        tag));
            }
            if (arrCustomListModels.size() > 0) {
                return arrCustomListModels;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<CityModel> getCityModel() {
        try {
            arrCityModel.clear();
            JSONArray objJsonArray = new JSONArray(objSharedPreferences.getString(Constants.PREF_CIITES, ""));
            for (int index = 0; index < objJsonArray.length(); index++) {
                arrCityModel.add(new CityModel(
                        Integer.parseInt(objJsonArray.getJSONObject(index).getString("id")),
                        objJsonArray.getJSONObject(index).getString("city_name"),
                        objJsonArray.getJSONObject(index).getString("country_code"),
                        Integer.parseInt(objJsonArray.getJSONObject(index).getString("country_id"))));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrCityModel;
    }

    public CompanyInfoModel getCompanyInfoModel() {
        CompanyInfoModel objCompanyInfoModel = null;
        try {
            JSONObject objJsonObject = new JSONObject(objSharedPreferences.getString(Constants.PREF_COMPANY_INFO, ""));
            objCompanyInfoModel = new CompanyInfoModel(
                    Integer.parseInt(objJsonObject.getString("id")),
                    objJsonObject.getString("company_name"),
                    objJsonObject.getString("company_code"),
                    objJsonObject.getString("company_registration_number"),
                    objJsonObject.getString("company_address"),
                    objJsonObject.getString("company_logo"),
                    objJsonObject.getString("company_registration_year"),
                    objJsonObject.getString("is_configuration_complated"),
                    Integer.parseInt(objJsonObject.getString("step")),
                    objJsonObject.getString("created_at"),
                    objJsonObject.getString("updated_at"),
                    objJsonObject.getString("color")
            );
            return objCompanyInfoModel;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserInfoModel getUserInfoModel() {
        UserInfoModel objUserInfoModel = null;
        try {
            JSONObject objJsonObject = new JSONObject(objSharedPreferences.getString(Constants.PREF_USER_INFO, ""));
            if (objJsonObject.length() > 0) {
                objUserInfoModel = new UserInfoModel(
                        objJsonObject.getString("id"),
                        objJsonObject.getString("name"),
                        objJsonObject.getString("first_name"),
                        objJsonObject.getString("middle_name"),
                        objJsonObject.getString("last_name"),
                        objJsonObject.getString("email"),
//                        Constants.BASE + objJsonObject.getString("photo"),
                        objJsonObject.getString("photo"),
                        objJsonObject.getString("company_id"),
                        objJsonObject.getString("ic_passport"),
                        objJsonObject.getString("gender"),
                        objJsonObject.getString("dob"),
                        objJsonObject.getString("birth_country"),
                        objJsonObject.getString("birth_region"),
                        objJsonObject.getString("birth_city"),
                        objJsonObject.getString("marital_status"),
                        objJsonObject.getString("ethnicity"),
                        objJsonObject.getString("religion"),
                        objJsonObject.getString("address"),
                        objJsonObject.getString("nationality"),
                        objJsonObject.getString("cv"),
                        objJsonObject.getString("offer_letter_sign"),
                        objJsonObject.getString("is_offer_letter_signed")
                );
                return objUserInfoModel;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @return return the profile image associated with the current logged in user.
     */
    public String getProfileImageUrl() {
        return objSharedPreferences.getString(Constants.USER_PROFILE_URL, "");

    }

    /**
     * SET the user profile image path in shared preferences.
     */
    public void setProfileImageUrl(String profileImageUrl) {
        objSharedPreferences.edit().putString(Constants.USER_PROFILE_URL, profileImageUrl).commit();
    }

    /**
     * @return return the profile image associated with the current logged in user.
     */
    public String getUserName() {
        return objSharedPreferences.getString(Constants.USER_NAME, "");
    }

    /**
     * SET the user name in shared preferences.
     */
    public void setUserName(String userName) {
        objSharedPreferences.edit().putString(Constants.USER_NAME, userName).commit();

    }

    public String getIsRegister() {
        return objSharedPreferences.getString(Constants.PREF_USER_IS_REGISTER, "");
    }

    /**
     * SET the user register in shared preferences.
     */
    public void setIsRegister(String isRegister) {
        objSharedPreferences.edit().putString(Constants.PREF_USER_IS_REGISTER, isRegister).commit();
    }

    public void saveCheckListData(String preferred_email, String employee_photo_path, String mobile_number, String preferred_username, String imagePath) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("preferred_email", preferred_email);
            jsonObject.put("employee_photo_path", employee_photo_path);
            jsonObject.put("mobile_number", mobile_number);
            jsonObject.put("preferred_username", preferred_username);
            jsonObject.put("image_path", imagePath);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        putString(Constants.CHECK_LIST_DATA, jsonObject.toString());
    }

    /**
     * Save User checklist preferences as an JsonObject Format below.
     * This will contains the following format.
     * {
     * preferred_email:"",
     * employee_photo_path:"",
     * mobile_number:"",
     * preferred_username:""
     * <p>
     * }
     */

    public CheckListModel getCheckList() {
        CheckListModel checkListModel = null;
        try {
            Utils.out("CHECK LIST DATA :" + getString(Constants.CHECK_LIST_DATA));
            if (!getString(Constants.CHECK_LIST_DATA).isEmpty()) {

                JSONObject jsonObject = new JSONObject(getString(Constants.CHECK_LIST_DATA));

                checkListModel = new CheckListModel(jsonObject.getString("preferred_email"),
                        jsonObject.getString("employee_photo_path"),
                        jsonObject.getString("mobile_number"),
                        jsonObject.getString("preferred_username"),
                        jsonObject.getString("image_path"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return checkListModel;

    }

    /**
     * Put and commit changes to the shared preferences.
     */
    public void putString(@NonNull String key, @NonNull String data) {
        objSharedPreferences.edit().putString(key, data).commit();
    }

    public void putInt(@NonNull String key, @NonNull int data) {
        objSharedPreferences.edit().putInt(key, data).commit();
    }

    public String getString(String key) {
        return objSharedPreferences.getString(key, "");
    }

    public int getInt(String key) {
        return objSharedPreferences.getInt(key, 0);
    }


    public boolean getBoolean(String key) {
        return objSharedPreferences.getBoolean(key, true);
    }

    /**
     * get the flow can be editable or not.
     */
    public boolean getFlow() {
        return getBoolean(Constants.MAKE_EDITABLE);

    }

    /**
     * Set the flow can be editable or not.
     */
    public void setFlow(boolean makeEditable) {
        objSharedPreferences.edit().putBoolean(Constants.MAKE_EDITABLE, makeEditable).commit();

    }

    /**
     * Save the user Bank details.
     */
    public void saveBankDetails(String data) {
        putString(Constants.BANK_DETAILS_PREFERENCES, data);
    }

    /**
     * Save the user Car details
     */

    public void saveCarDetails(String data) {
        putString(Constants.CAR_DETAILS_PREFERENCES, data);
    }

    /**
     * Get the user car details model
     */
    public CarDetailsModel getCarDetailsModel() {
        CarDetailsModel carDetailsModel = null;
        try {
            if (!getString(Constants.CAR_DETAILS_PREFERENCES).isEmpty()) {

                JSONObject jsonObject = new JSONObject(getString(Constants.CAR_DETAILS_PREFERENCES));
                carDetailsModel = new CarDetailsModel(jsonObject.getInt("id"),
                        jsonObject.getInt("user_id"),
                        jsonObject.getString("car_plate_number"),
                        jsonObject.getString("car_brand"),
                        jsonObject.getString("car_type"),
                        jsonObject.getString("created_at"),
                        jsonObject.getString("updated_at"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return carDetailsModel;

    }

    /**
     * Get the user Bank details model
     */
    public BankDetailsModel getBankDetailsModel() {
        BankDetailsModel bankDetailsModel = null;
        try {
            if (!getString(Constants.BANK_DETAILS_PREFERENCES).isEmpty()) {

                JSONObject jsonObject = new JSONObject(getString(Constants.BANK_DETAILS_PREFERENCES));

                bankDetailsModel = new BankDetailsModel(jsonObject.getInt("id"),
                        jsonObject.getInt("user_id"),
                        jsonObject.getString("account_nickname"),
                        jsonObject.getString("name_on_account"),
                        jsonObject.getString("account_type"),
                        jsonObject.getString("bank_name"),
                        jsonObject.getString("bank_code"),
                        jsonObject.getString("account_number"),
                        jsonObject.getString("branch_code"),
                        jsonObject.getString("created_at"),
                        jsonObject.getString("updated_at"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bankDetailsModel;

    }

    public void getNextContentModel() {

    }

    public void setPrevPage(String data) {
        putString(Constants.PREV_PAGE, data);
    }

    public String getPrevPage() {
        return getString(Constants.PREV_PAGE);
    }

    public void setCurrentPage(String data) {
        putString(Constants.CURRENT_PAGE, data);
    }

    public String getCurrentPage() {
        return getString(Constants.CURRENT_PAGE);
    }


    public void setNextPage(String data) {
        putString(Constants.NEXT_PAGE, data);
    }

    public String getNextPage() {
        return getString(Constants.NEXT_PAGE);
    }

    public void setCurrentPageTitle(String data) {
        putString(Constants.CURRENT_PAGE_TITLE, data);
    }

    public String getCurrentPageTitle() {
        return getString(Constants.CURRENT_PAGE_TITLE);
    }

    /**
     * Page has validation.
     */

    public void setPageHasValidation(int data) {
        putInt(Constants.PAGE_HAS_VALIDATION, data);
    }

    public int getPageHasValidation() {
        return getInt(Constants.PAGE_HAS_VALIDATION);
    }


    /**
     * User has validate.
     */

    public void setUserHasValidate(int data) {
        putInt(Constants.USER_HAS_VALIDATE, data);
    }

    public int getUserHasValidate() {
        return getInt(Constants.USER_HAS_VALIDATE);
    }


    /**
     * Validation Message.
     */

    public void setValidationMessage(String data) {
        putString(Constants.VALIDATION_MESSAGE, data);
    }

    public String getValidationMessage() {
        return getString(Constants.VALIDATION_MESSAGE);
    }


    /**
     * Save work day response in shared preferences.
     */
    public void setWorkDayData(String workDayReponseString) {
        putString(Constants.KEY_WORK_DAY, workDayReponseString);

    }

    /**
     * Get the work day model.
     */

    public WorkDayModel getWorkDayModel() {
        WorkDayModel workDayModel = null;
        try {
            if (!getString(Constants.KEY_WORK_DAY).isEmpty()) {

                workDayModel = new WorkDayModel();

                JSONObject jsonObject = new JSONObject(getString(Constants.KEY_WORK_DAY));

                workDayModel.setFirst_name(jsonObject.getString("first_name"));
                workDayModel.setLast_name(jsonObject.getString("last_name"));
                workDayModel.setFull_name(jsonObject.getString("full_name"));
                workDayModel.setEmail(jsonObject.getString("email"));
                workDayModel.setIc(jsonObject.getString("ic"));

//            Set short gender name.
                workDayModel.setGender(jsonObject.getString("gender"));
//            Set Full gender name
                workDayModel.setGender_full(Utils.getFullGenderFromShort(jsonObject.getString("gender")));

                workDayModel.setDob(jsonObject.getString("dob"));

//            Short marital status name
                workDayModel.setMarital_status(jsonObject.getString("marital_status"));
//            Full marital status name
                workDayModel.setMarital_status_full(Utils.getMaritalStatusFromShortName(jsonObject.getString("marital_status")));
                Utils.out("FULL MARITAL STATUS :" + workDayModel.getMarital_status_full());

                workDayModel.setRace(jsonObject.getString("race"));
                workDayModel.setReligion(jsonObject.getString("religion"));
                workDayModel.setBirth_country(jsonObject.getString("birth_country"));
                workDayModel.setBirth_region(jsonObject.getString("birth_region"));
                workDayModel.setBirth_city(jsonObject.getString("birth_city"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return workDayModel;
    }


}
