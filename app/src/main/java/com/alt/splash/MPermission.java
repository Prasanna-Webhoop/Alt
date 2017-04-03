package com.alt.splash;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alt.R;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.USE_FINGERPRINT;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public abstract class MPermission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpermission);
    }

    public boolean mayRequestLocationAccess() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED
                ) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
            requestPermissions(new String[]{ACCESS_FINE_LOCATION, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, CAMERA, READ_PHONE_STATE, USE_FINGERPRINT}, 1);
        } else {
            requestPermissions(new String[]{ACCESS_FINE_LOCATION, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, CAMERA, READ_PHONE_STATE, USE_FINGERPRINT}, 1);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        System.out.println("ONE PERMISSION GRANTED");

        int intCount = 0;
        if (requestCode == 1) {
            for (int intIndex = 0; intIndex < grantResults.length; intIndex++) {
                if (grantResults[intIndex] == PackageManager.PERMISSION_DENIED) {
                    intCount++;
                }
            }
            System.out.println("PERMISSION GRANTED COUNT : " + intCount);
        }
        System.out.println("USER PRINT PERMISSION GRANTED COUNT : " + intCount);

        if (intCount > 0) {
            // all permission granted
            onPermissionsGrantedListener(intCount);
        }
    }

    public abstract void onPermissionsGrantedListener(int requestCode);
}
