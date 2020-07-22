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
     *
     * 싱글톤 모델이란?
     * -특정 클래스에 대한 인스턴스를 단 한번만  static 메모리 영역에 할당하고
     * 해당 클래스에 대한 생성자를 여러번 호출하더라도 최초에 생성된 객체를 반환하는 디자인 형태
     * -장점 : 무분별한 new 연산으로 인한 메모리 낭비 방지
     * -문제점 : 싱글톤 인스턴스가 너무 많은 일을 하거나 많은 데이터를 공유 시킬 경우
     * 다른 클래스의 인스턴스들 간에 결합도가 높아져 "개방-폐쇄 원칙"을 위배하게 된다
     * (=객체 지향 설계 원칙에 어긋나는 것)
     */

    private static CookieSharedPreferences cookieSharedPreferences = null;

    /**
     * 이건 Thread safe lazy initialization + Double-checked locking 으로
     * 게으른 초기화 성능저하를 완화시킴킴     */
    public static CookieSharedPreferences getInstanceof(Context c){
        if (cookieSharedPreferences == null){
            synchronized (CookieSharedPreferences.class) {
                if (cookieSharedPreferences == null)
                    cookieSharedPreferences = new CookieSharedPreferences(c);
            }
        }
        return cookieSharedPreferences;
    }

    /**
     * 아래 방법이 싱글톤 모델을 사용하기 위한 제일 좋은 방법이지만 코드 내 고쳐야할 부분이 좀 있는 관계로 일단 패-쓰
     */
//    private static class LazyHolder{
//        public static final CookieSharedPreferences cookieSharedPreferences = new CookieSharedPreferences();
//    }
//
//    public static CookieSharedPreferences getInstance(){
//        return LazyHolder.cookieSharedPreferences;
//    }
//
//


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

