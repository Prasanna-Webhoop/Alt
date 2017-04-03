package com.alt.base;

import android.app.Application;
import android.content.SharedPreferences;

import com.alt.utils.Constants;

import java.util.HashMap;

/**
 * Created by Capternal on 07/10/16.
 */

public class AppController extends Application {

    SharedPreferences objUserPreferences;

    HashMap<String, Object> hashMap = new HashMap<String, Object>();


    @Override
    public void onCreate() {
        super.onCreate();
        objUserPreferences = getSharedPreferences(Constants.USER_PREFERENCE, MODE_PRIVATE);
        put(Constants.OBJECT_USER_PREFERENCE, objUserPreferences);
    }


    /**
     * This will add the Object
     */
    public void put(String key, Object object) {
        hashMap.put(key, object);
    }

    /**
     * This Will return the object stored in the hash map with the specified key.
     */
    public Object get(String key) {
        return hashMap.get(key);
    }
}
