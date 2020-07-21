package com.example.beaconscantest.api.core.preferences;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;

/**
 * CookieSharedPreferences 클래스
 */

public class CookieSharedPreferences {

    public static final String COOKIE_SHARED_PREFERENCES_KEY = "com.example.cookie";
    public static final String TOKEN = "TOKEN";
    public static final String FCM_TOKEN = "FCM_TOKEN";
    public static final String LID = "LID";
    public static final String LPW = "LPW";

    // SharedPreferences 객체
    private static SharedPreferences sharedPreferences;



    /**
     * 싱글톤 모델로 객체 초기화
     */

    private static CookieSharedPreferences cookieSharedPreferences = null;

    public static CookieSharedPreferences getInstanceof(Context c){
        if (cookieSharedPreferences == null){
            cookieSharedPreferences = new CookieSharedPreferences(c);
        }
        return cookieSharedPreferences;
    }


    /**
     * 생성자
     *
     * @param context
     */
    public CookieSharedPreferences(Context context) {
        final String COOKIE_SHARED_PREFERENCE_NAME = context.getPackageName();
        sharedPreferences = context.getSharedPreferences(COOKIE_SHARED_PREFERENCE_NAME, Activity.MODE_PRIVATE);
    }

    /**
     * SharedPreferences에 값 추가 메소드
     *
     * @param key
     * @param value
     */
    public void putHashSet(String key, HashSet<String> value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, value);
        editor.commit();
    }

    /**
     * SharedPreferences에서 값 가져오는 메소드
     *
     * @param key
     * @param cookie
     * @return
     */
    public HashSet<String> getHashSet(String key, HashSet<String> cookie){
        try{
            return (HashSet<String>) sharedPreferences.getStringSet(key, cookie);
        } catch(Exception e){
            return cookie;
        }
    }

    /**
     * SharedPreferences에 값 추가 메소드
     *
     * @param key
     * @param value
     */
    public void putString(String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * SharedPreferences에서 값 가져오는 메소드
     *
     * @param key
     * @param cookie
     * @return
     */
    public String getString(String key, String cookie){
        try{
            return sharedPreferences.getString(key, cookie);
        } catch(Exception e){
            return cookie;
        }
    }

    public static void put(String key, String value){
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }


    public static String get(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public static boolean get(String key, boolean defValue){
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void put(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static Integer get(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public static void put(String key, int value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(key, value).commit();
    }

    public static Long get(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    public static void put(String key, long value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putLong(key, value).commit();
    }

}

