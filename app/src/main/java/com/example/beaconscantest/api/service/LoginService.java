package com.example.beaconscantest.api.service;

import android.content.Context;

import com.example.beaconscantest.api.core.APIAdapter;
import com.example.beaconscantest.api.response.LoginData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * LoginService 클래스?
 */

public class LoginService extends APIAdapter {

    /**
     * Retrofit 객체를 가져오는 메소드
     * @param context
     * @return
     */

    public static LoginAPI getRetrofit(Context context){
        return (LoginAPI)retrofit(context,LoginAPI.class);
    }

    /**
     * LoginAPI 인터페이스
     */

    public interface LoginAPI{
        /**
         * 로그인
         */
        @Headers("Content-Type: application/json")
        @POST("login")
        Call<LoginData> login(@Body LoginData body);
    }



}
