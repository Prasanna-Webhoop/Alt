package com.alt.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alt.R;
import com.alt.base.AppController;
import com.alt.others.OnBoardingButton;
import com.alt.others.OnBoardingEditText;
import com.alt.preference.UserPreference;
import com.alt.utils.CallWebService;
import com.alt.utils.Constants;
import com.alt.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements CallWebService.WebserviceResponse, View.OnClickListener {

    private Activity objActivity;
    private AppController objAppController;

    private OnBoardingEditText objEditTextSignInUserName;
    private OnBoardingEditText objEditTextSignInPassword;
    private OnBoardingButton objButtonLogin, objButtonSignUp;
    UserPreference objUserPreference;
//    private CustomAlert objCustomAlert;

    private Intent intent;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = LoginActivity.this;

        objActivity = LoginActivity.this;
        objEditTextSignInUserName = (OnBoardingEditText) findViewById(R.id.edittext_signin_username);
        objEditTextSignInPassword = (OnBoardingEditText) findViewById(R.id.edittext_signin_password);
        objButtonLogin = (OnBoardingButton) findViewById(R.id.button_login);
        objButtonSignUp = (OnBoardingButton) findViewById(R.id.button_signUp);
        setListener();
        objAppController = (AppController) getApplication();
        objUserPreference = new UserPreference(objActivity);
        new CallWebService(Constants.API_COUNTRY, new JSONObject(), this, this, false).execute();
        new CallWebService(Constants.API_CITIES, new JSONObject(), this, this, false).execute();

       /* objCustomAlert = new CustomAlert(objActivity);
        objCustomAlert.build(objActivity);*/
    }

    public void setListener() {
        objButtonLogin.setOnClickListener(this);
        objButtonSignUp.setOnClickListener(this);
    }

    public void onDontHaveAccountClick(View view) {
        objEditTextSignInUserName.setText("");
        objEditTextSignInPassword.setText("");
//        startActivity(new Intent(LoginActivity.this, SignUpCompanyActivity.class));
       /* intent = new Intent(LoginActivity.this, SignUpCompanyActivity.class);
        Utils.pushToNext(objActivity, intent);*/
        /*startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);*/
//        Utils.pushToNext(objActivity, intent);
    }

    @Override
    public void onWebserviceResponse(String strUrl, String strResult) {
        switch (strUrl) {
            case Constants.API_COUNTRY:
                try {
                    JSONObject objJsonObject = new JSONObject(strResult);

                    if (objJsonObject.getBoolean("result")) {
                        objUserPreference.setCountryData(objJsonObject.getString(Constants.RESPONSE));
                    } else {
                        //  TO AVOID NULL_POINTER_EXCEPTION.
                        objUserPreference.setCountryData("[]");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case Constants.API_CITIES:
                try {
                    JSONObject objJsonObject = new JSONObject(strResult);

                    if (objJsonObject.getBoolean("result")) {
                        objUserPreference.setCityData(objJsonObject.getString(Constants.RESPONSE));
                    } else {
                        //  TO AVOID NULL_POINTER_EXCEPTION.
                        objUserPreference.setCityData("[]");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case Constants.API_USER_LOGIN:
                try {
                    JSONObject objJsonObject = new JSONObject(strResult);
                    if (objJsonObject.getBoolean("result")) {
                        objUserPreference.setUserInfoData(objJsonObject.getJSONObject(Constants.RESPONSE).getString("user"));
                        objUserPreference.setCompanyInfoData(objJsonObject.getJSONObject(Constants.RESPONSE).getString("company"));
                        objUserPreference.setFlow(false);
                        objUserPreference.setIsRegister("Y");
                        objEditTextSignInUserName.setText("");
                        objEditTextSignInPassword.setText("");
                        Intent intent = new Intent(LoginActivity.this, AltBaseActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Utils.pushToNext(objActivity, intent);
                    } else {
//                        objCustomAlert.showAlertWithMessage(objJsonObject.getString(Constants.MESSAGE));
                        Utils.showToast(LoginActivity.this, objJsonObject.getString(Constants.MESSAGE));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                System.out.println("CLICKED");
                loginAPI();
                break;

            case R.id.button_signUp:
             /*   Intent intent = new Intent(activity, TermsAndConditionsActivity.class);
                Utils.pushToNext(activity, intent);*/

                break;

        }
    }

    public void loginAPI() {
        System.out.println("IN API METHOD");
        if (objEditTextSignInUserName.getText().length() > 0 && objEditTextSignInPassword.getText().length() > 0) {
            System.out.println("NOT EMPTY");
            if (Utils.isEmailValid(objEditTextSignInUserName.getText().toString())) {
                try {
                    JSONObject objJsonObject = new JSONObject();
                    objJsonObject.put("email", objEditTextSignInUserName.getText().toString().trim());
                    objJsonObject.put("password", objEditTextSignInPassword.getText().toString());
                    new CallWebService(Constants.API_USER_LOGIN, objJsonObject, this, this, true).execute();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("NOT VALID EMAIL");
//                objCustomAlert.showAlertWithMessage("Please enter valid email");
                Utils.showToast(LoginActivity.this, "Please enter valid email");
            }
        } else {
            System.out.println("EMPTY FIELDS");
//            objCustomAlert.showAlertWithMessage("Please enter username and password");
            Utils.showToast(LoginActivity.this, "Please enter username and password");
        }
    }
}
