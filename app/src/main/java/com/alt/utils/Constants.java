package com.alt.utils;

import android.net.Uri;
import android.os.Environment;

/**
 * Created by student on 25/11/16.
 */

public class Constants {
    // LAST USED CODE TO BE INCREMENTED: 402+1 (

    public static final String SEPARATOR = "/";

    // User Profile.
    public static final String USER_PROFILE_URL = "user_profile_url";
    public static final String USER_NAME = "user_name";
    public static final int REQUEST_MADE_FOR_CAMERA_DOCUMENT = 401;
    public static final int REQUEST_MADE_FOR_GALLERY_DOCUMENT = 402;
    public static final String FILES = "Files";
    public static final String IMAGES = "Images";
    public static final String CHECK_LIST_DATA = "check_list_data";
    public static final String BASE_ERROR_STRING = "Are you sure, you want to delete this ";
    public static final String MESSAGE_CAPITAL = "Message";
    public static final String MAKE_EDITABLE = "make_editable";
    public static final String ALERT_MESSAGE_INTERNET_CONNECTION = "Please check internet connection";
    public static final String ALERT_MESSAGE_INVALID_EFFECTIVE_DATE = "Invalid date.";
    public static final String BANK_DETAILS_PREFERENCES = "BANK_DETAILS_PREFERENCES";
    public static final String CAR_DETAILS_PREFERENCES = "CARÒ_DETAILS_PREFERENCES";

    //    Default value to show the loader or not on fragment's Visit or fragment load.
    public static final boolean SHOW_LOADER = false;
    public static final String PREV_PAGE = "prevPage";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String NEXT_PAGE = "nextPage";
    public static final String CURRENT_PAGE_TITLE = "currentPageTitle";
    public static final String PAGE_HAS_VALIDATION = "pageHasValidation";
    public static final String USER_HAS_VALIDATE = "userHasValidate";
    public static final String VALIDATION_MESSAGE = "validationMessage";
    public static final String KEY_WORK_DAY = "work_day";
    public static final String T_SHIRT_SIZE = "t_shirt_size";
    public static final String PREFERRED_NAME = "preferred_name";

    //    public static final String ALERT_PASSPORT_LENGTH_MIN = "Passport number length should be greater than 5";


    // Static popup dialog string array.
    public static String[] POPUP_ACTION_LABLES = {"Delete", "Cancel"};
    public static String[] POPUP_ACTION_OK = {"OK"};

    // Static initial check_list Data to save empty string in shared preferences.
    public static String EMPTY_CHECK_LIST = "{\n" +
            "    \"preferred_email\": \"\",\n" +
            "    \"employee_photo_path\": \"\",\n" +
            "    \"mobile_number\": \"\",\n" +
            "    \"preferred_username\": \"\"\n" +
            "}";

    public static String FORMATTED_EMAIL_HTML_STRING = " " +
            "    <center style='text-align:justify; font-size:15px;font-family:HelveticaNeue;color:#362932;'>Do you have preference or would" +
            "        <br>" +
            "        <b>" +
            "            <u>@email</u>" +
            "        </b>" +
            "        be ok?" +
            "    </center>";

    public static final String PREF_CHECK_LIST = "pref_check_list";


    public static Uri uri;
    public static final int REQUEST_MADE_FOR_GALLERY = 200;
    public static final int REQUEST_MADE_FOR_CAMERA = 300;
    public static final int REQUEST_MADE_FOR_DOCUMENT = 400;
    public static int HEADER_VIEW_HIDE_CODE = 100;
    public static String PROFILE = "PROFILE";
    public static final String APPLICATION_PATH = Environment.getExternalStorageDirectory() + "/onBoarding/";


    // OTHER CONSTANTS.
    public static final String RESULT = "result";
    public static final String MESSAGE = "message";
    public static final String RESPONSE = "response";
    public static final String MEDIA = "media";
    public static final String MEDIA_UPLOAD_DOCUMENT = "media_upload_document";

    public static final String PDF_DOCUMENT = "pdf";
    public static final String WORDX_DOCUMENT = "docx";
    public static final String WORD_DOCUMENT = "doc";
    public static final String PPT_DOCUMENT = "ppt";
    public static final String EXCEL_DOCUMENT = "xls";
    public static final String EXCELX_DOCUMENT = "xlsx";
    public static final String IMAGE_PNG = "png";
    public static final String IMAGE_JPG = "jpg";


    // EXTERNAL INTEGRATION API KEY
//    public static final String CLOUD_VISION_API_KEY = "AIzaSyA9HPIwC_BFz3-hfGw2monJnsw7hFie0LI";
    public static final String CLOUD_VISION_API_KEY = "AIzaSyC7ZV75sfQqHwUf6jc5DbXR0xaK8FWF5-o";
    // Google docs load pdf url.
    //        "https://docs.google.com/viewer?url="
    public static final String GOOGLE_DOCS_URL = "https://docs.google.com/viewer?embedded=true&url=";
    // API


    //    public static final String BASE = "http://althr.webhoop.com";
    public static final String BASE = "http://dev.alt.hr";
    public static final String API_BASE = BASE + "/api/";

    //    Work day api
    public static final String API_WORK_DAY = API_BASE + "v1/workday";


    //    Terms and conditions.
//    http://dev.alt.hr/public/terms.html

    public static final String TERMS_BASE = BASE + "/public/";
    public static final String URL_TERMS_AND_CONDITIONS = TERMS_BASE + "terms.html";


    public static final String API_USER_LOGIN = API_BASE + "users/login";
    public static final String API_COMPANY_INFO = API_BASE + "company/info"; // Signup Company code
    public static final String API_USER_REGISTER = API_BASE + "users/save";
    public static final String API_USER_EDIT = API_BASE + "users/update";
    public static final String API_UPLOAD_USER_PHOTO = API_BASE + "users/upload_photo";
    public static final String API_UPLOAD_USER_CV = API_BASE + "users/upload_cv";
    public static final String API_REMOVE_USER_CV = API_BASE + "users/remove_cv";
    //    public static final String API_COUNTRY = API_BASE + "users/nationality"; // Get Country details
    public static final String API_COUNTRY = API_BASE + "users/countries"; // Get Country details
    public static final String API_CITIES = API_BASE + "users/cities"; // Get Country Cities
    public static final String API_USER_CONTACTS = API_BASE + "contacts/list";
    public static final String API_ADD_CONTACT = API_BASE + "contacts/add";
    public static final String API_DELETE_CONTACT = API_BASE + "contacts/delete";
    public static final String API_USER_JOBS = API_BASE + "jobs/list";
    public static final String API_ADD_JOB = API_BASE + "jobs/add";
    public static final String API_DELETE_JOB = API_BASE + "jobs/delete";
    public static final String API_USER_ADDRESSES = API_BASE + "addresses/list";
    public static final String API_ADD_ADDRESS = API_BASE + "addresses/add";
    public static final String API_DELETE_ADDRESS = API_BASE + "addresses/delete";
    public static final String API_USER_PAYSLIPS = API_BASE + "payslips/list";
    public static final String API_ADD_PAYSLIP = API_BASE + "payslips/add";
    public static final String API_REMOVE_PAYSLIP = API_BASE + "payslips/remove";
    public static final String API_USER_REFERENCES = API_BASE + "references/list";
    public static final String API_ADD_REFERENCE = API_BASE + "references/add";
    public static final String API_UPLOAD_REFERENCE_PHOTO = API_BASE + "references/upload_photo";
    //    NEW RECOMMENDATION HAS BEEN INCLUDED IN REFERENCES, SO HERE IS THE NEW API
    public static final String API_UPLOAD_REFERENCE_CV_PHOTO = API_BASE + "references/upload_cv_photo";
    public static final String API_UPLOAD_REFERENCE_CV_DOC = API_BASE + "references/upload_cv_doc";

    public static final String API_DELETE_REFERENCE = API_BASE + "references/delete";
    public static final String API_USER_RECOMMENDATIONS = API_BASE + "recommendation/list";
    public static final String API_USER_SIGN_OFFER_LETTER = API_BASE + "users/sign_offer_letter";

    //    Check List Bank and car api'// STARTSHIP: 31/01/17
    public static final String API_BANK_DETAILS = API_BASE + "users/bank_details";
    public static final String API_CAR_DETAILS = API_BASE + "users/car_details";
    public static final String API_ADD_STATUTORY = API_BASE + "users/add_statutory";
    public static final String API_STATUTORY_LIST = API_BASE + "users/list_statutory";

//    Get Details

    public static final String API_GET_CAR_DETAILS = API_BASE + "users/get_car_sticker";
    public static final String API_GET_BANK_DETAILS = API_BASE + "users/get_bank_details";


    public static final String API_ADD_BENEFICIARY = API_BASE + "users/add_beneficiary";
    public static final String API_LIST_BENEFICIARY = API_BASE + "users/list_beneficiary";
    public static final String API_DELETE_BENEFICIARY = API_BASE + "users/delete_beneficiary";


    // Popup listing api's
    public static final String API_STATES = API_BASE + "users/states"; // Get states details according to the country id.
    public static final String API_CITY = API_BASE + "users/city"; // Get city details according to the states.

    //RECOMMENDATION API'S
    public static final String API_ADD_RECOMMENDATION = API_BASE + "recommendation/add";
    public static final String API_UPLOAD_RECOMMENDATION_PHOTO = API_BASE + "recommendation/upload_photo";
    public static final String API_UPLOAD_RECOMMENDATION_CV_PHOTO = API_BASE + "recommendation/upload_cv_photo";
    public static final String API_UPLOAD_RECOMMENDATION_CV_DOCUMENT = API_BASE + "recommendation/upload_cv_doc";
    public static final String API_DELETE_RECOMMENDATION = API_BASE + "recommendation/delete";

    //DEGREE API'S
    public static final String API_USER_DEGREES = API_BASE + "degree/list";
    public static final String API_DEGREES = API_BASE + "degree/degrees";
    public static final String API_ADD_DEGREE = API_BASE + "degree/add";
    public static final String API_UPLOAD_DEGREE_CV_PHOTO = API_BASE + "degree/upload_cv_photo";
    public static final String API_UPLOAD_DEGREE_CV_DOCUMENT = API_BASE + "degree/upload_cv_doc";
    public static final String API_DELETE_DEGREE = API_BASE + "degree/delete";
    public static final String API_GET_FIELDS_OF_STUDY_DATA = API_BASE + "degree/fields";

    public static final String API_ADD_BUSINESS_CARD = API_BASE + "business_card/add";
    public static final String API_GET_BUSINESS_CARD = API_BASE + "business_card/list";
    public static final String API_UPLOAD_BUSINESS_CARD_PHOTO = API_BASE + "business_card/upload_photo";
    public static final String API_IC_STRING = API_BASE + "users/ic";
    public static final String API_UPLOAD_ID_PHOTO = API_BASE + "users/upload_scan";
    public static final String API_CONTENT_LIST = API_BASE + "content/list";


    //CMS CONSTANTS

    public static final String BASE_CMS = BASE + "/api/v1/onboarding/cms/";
    public static final String BASE_CMS_PAGE = BASE_CMS + "page/";
    public static final String BASE_CMS_VIDEO = BASE_CMS + "video/";


    public static final String URL_PROMOTION = BASE_CMS + "welcome/";
    public static final String URL_PROMOTION_WELCOME = BASE_CMS + "welcome-user/";
    public static final String URL_CMS = BASE_CMS_PAGE;
    public static final String URL_CEO_MESSAGE = BASE_CMS_PAGE;
    public static final String URL_OFFER_LETTER = BASE_CMS + "offer_letter/";
    public static final String URL_CMS_DETAIL = BASE_CMS + "detail/";
    public static final String URL_CMS_INFORMATION = BASE_CMS + "information/";
    public static final String URL_FEEDBACK = BASE_CMS + "feedback/";
    //    public static final String API_CONTENT_NEXT = API_BASE + "content/next";
    public static final String API_CONTENT_NEXT = BASE_CMS + "next-page/";
    public static final String API_CONTENT_PREVIOUS = API_BASE + "content/previous";
    public static final String URL_TERMS = BASE_CMS + "terms/";
    public static final String URL_CMS_VALIDATION = API_BASE + "content/valifdation";


    // APP PREFERENCES
    public static final String USER_PREFERENCE = "user_preference";
    // KEY
    public static final String PREF_COMPANY_INFO = "company_info";
    public static final String PREF_USER_INFO = "user_info";
    public static final String PREF_USER_PAYSLIPS = "user_payslips";
    public static final String PREF_USER_BUSINESS_CARD = "user_business_card";
    public static final String PREF_USER_IS_REGISTER = "is_register";
    public static final String PREF_LAST_LOGIN_USER_INFO = "last_login_user_info";
    public static final String PREF_USER_PASSWORD = "password";
    public static final String PREF_TOUCH_SWITCH = "touch_id_switch";
    public static final String PREF_UNIQUE_KEY = "unique_key";
    public static final String PREF_COUNTRIES = "countries";
    public static final String PREF_CIITES = "cities";
    // GLOBAL OBJECT KEY
    public static final String OBJECT_USER_PREFERENCE = "user_preference_object";

    // POPUP MENU TAGS
    public static String MARITAL_STATUS = "marital_status";
    public static String GENDER = "gender";


    // ALERT MESSAGES
    public static final String ALERT_MESSAGE_EMAIL_INVALID = "Invalid Email";
    public static final String ALERT_MESSAGE_FIELD_REQUIRED = "Please fill required fields";
    public static final String ALERT_MESSAGE_COUNTRY = "Please select country first";
    public static final String ALERT_MESSAGE_STATE = "Please select state first";
    public static final String ALERT_MESSAGE_ERROR_UPLOADING_RESOURCE = "Error while uploading please try again later";
    public static final String ALERT_MESSAGE_COMPANY = "Company code required";
    public static final String ALERT_MESSAGE_INVALID_PHONE_NUMBER = "Please enter valid phone number";
    public static final String ALERT_MESSAGE_PHOTO_REQUIRED = "Employee photo required";
    public static final String ALERT_MESSAGE_SIGN_OFFER_LETTER = "Please sign the offer letter";
    public static final String ALERT_MESSAGE_INCORRECT_IC_PASSPORT_NUMBER = "The length of the passport number should be greater than 5 characters while the IC should be 14 characters";
    public static final String DISCARD_SAVE_TITLE = "Warning";
    public static final String DISCARD_SAVE_MESSAGE = "Do you want to continue without saving information?";


}
