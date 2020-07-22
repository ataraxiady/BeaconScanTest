package com.example.beaconscantest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.beaconscantest.api.core.preferences.CookieSharedPreferences;
import com.example.beaconscantest.api.response.LoginData;
import com.example.beaconscantest.api.service.LoginService;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class IntroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {


            public void onLogin() {
                showProgressBar();
                LoginData loginData = new LoginData();
                String webId = CookieSharedPreferences.get(CookieSharedPreferences.LID,null);
                String webPw = CookieSharedPreferences.get(CookieSharedPreferences.LPW,null);
                Timber.d(webId + "/" + webPw);
                loginData.setWebId(webId);
                loginData.setWebPw(webPw);
                Timber.i("FCM TOKEN -> " + CookieSharedPreferences.get(CookieSharedPreferences.FCM_TOKEN,""));
                loginData.setPushToken(CookieSharedPreferences.get(CookieSharedPreferences.FCM_TOKEN,""));
                LoginService.getRetrofit(getApplicationContext()).login(loginData).enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        hideProgressBar();
                        LoginData data = response.body();

                        if (data.isResult()){
                            CookieSharedPreferences.put(CookieSharedPreferences.TOKEN, data.getToken());


                            /**
                             * 그린램프 앱에서는 intent에 넣는 내용이 많음
                             */

                            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        hideProgressBar();
                        //에러 다이얼로그 보여줘야함

                    }
                });
            }



            @Override
            public void run() {
                /**
                 * LoginActivity 테스트해보고싶으면 뒤에 부분 로그인액티비티로 바꾸기
                 */


                if (CookieSharedPreferences.get(CookieSharedPreferences.LID, null) != null
                    && CookieSharedPreferences.get(CookieSharedPreferences.LPW, null) != null){
                    onLogin();
                } else {
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2000);




    }
}