package com.alt.splash;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;

import com.alt.R;
import com.alt.base.AppController;
import com.alt.login.LoginActivity;
import com.alt.preference.UserPreference;
import com.alt.utils.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LaunchActivity extends MPermission {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;

    Activity activity;
    AppController objAppController;
    UserPreference objUserPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        activity = LaunchActivity.this;
        objAppController = (AppController) getApplication();
        objUserPreference = new UserPreference(activity);
        overridePendingTransition(0, 0);


        generateHashkey();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!LaunchActivity.super.mayRequestLocationAccess()) {
                return;
            }
        } else {
            start();
        }


    }

    public void generateHashkey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.althr.onboarding",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Utils.out("KEY HASH : " + Base64.encodeToString(md.digest(), Base64.NO_WRAP));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Utils.d("KEY HASH ERROR : ", e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            Utils.d("KEY HASH ERROR : ", e.getMessage());
        }
    }

    private void start() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (objUserPreference.getIsRegister().equals("Y")) {
//                    User if already register so, get him inside to the Main Board.
                    /*Intent i = new Intent(LaunchActivity.this, MainBoardProfileActivity.class);
                    startActivity(i);
                    finish();*/
                } else {
//                    Incomplete profile so let him complete his profile.
                    if (objUserPreference.getUserInfoModel() != null && objUserPreference.getUserInfoModel().getId().length() > 0) {
                        /*Intent i = new Intent(LaunchActivity.this, ImportProfileActivity.class);
                        startActivity(i);*/
                    }/* else {
                        Intent i = new Intent(LaunchActivity.this, LaunchActivity.class);
                        startActivity(i);
                    }*/
//                    finish();
                }
/*                Intent i = new Intent(SplashActivity.this, CheckListActivity.class);
                startActivity(i);
                finish();*/
            }
        }, SPLASH_TIME_OUT);
    }

    public void onGetStartedClick(View view) {

        Utils.pushToNext(activity, new Intent(LaunchActivity.this, LoginActivity.class));


      /*  CustomAlert objCustomAlert = new CustomAlert(LaunchActivity.this);
        objCustomAlert.build(LaunchActivity.this);
        objCustomAlert.showAlertWithMessage("ThiS is a test Error message.");*/
    }

    @Override
    public void onPermissionsGrantedListener(int requestCode) {
        if (requestCode == 0) {
            final Intent i = new Intent();
            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.setData(Uri.parse("package:" + activity.getPackageName()));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            activity.startActivity(i);
        } else {
            start();
        }
    }
}
