package com.example.beaconscantest.api.core.interceptor;

import android.content.Context;

import com.example.beaconscantest.api.core.preferences.CookieSharedPreferences;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Cookie;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookieInterceptor implements Interceptor {


    // CookieSharedReferences 객체
    private CookieSharedPreferences cookieSharedPreferences;


    public  ReceivedCookieInterceptor(Context context){
        cookieSharedPreferences = CookieSharedPreferences.getInstanceof(context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        // 가져온 chain으로 부터 리스폰스 객체를 생성
        Response response = chain.proceed(chain.request());

        //리스폰스의 헤더 영역에 set-Cookie가 설정되어 있는 경우
        if(!response.headers("Set-Cookie").isEmpty()){
            HashSet<String> cookies = new HashSet<>();

            // 쿠키 값을 읽어옴
            for(String header : response.headers("Set-Cookie")){
                cookies.add(header);
            }

            // 쿠키 값을 CookieSharedPreferences에 저장
            cookieSharedPreferences.putHashSet(CookieSharedPreferences.COOKIE_SHARED_PREFERENCES_KEY, cookies);
        }

        // 리스폰스 객체 반환
        return response;
    }
}
