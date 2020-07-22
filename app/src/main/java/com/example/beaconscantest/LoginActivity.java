package com.example.beaconscantest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.beaconscantest.api.core.preferences.CookieSharedPreferences;
import com.example.beaconscantest.api.response.LoginData;
import com.example.beaconscantest.api.service.LoginService;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.idEditText)
    EditText idEditText;
    @BindView(R.id.pwEditText)
    EditText pwEdiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.loginBtn)
    public void onLoginClick(){
        if (idEditText.getText().length() == 0){
            showToast("아이디를 입력하세요.");
            return;
        }
        if(pwEdiText.getText().length() == 0){
            showToast("비밀번호를 입력하세요.");
        }

        //로그인을 위한(아이디, 비밀번호 체크) 함수 실행
        //가능하다면 자동로그인 추가 구현(getFireBaseKey)
        onLogin();

    }


    private void onLogin(){
        showProgressBar();
        LoginData request = new LoginData();


        request.setWebId(idEditText.getText().toString());
        try{
            String pw = AES.getInstance().encrypt(pwEdiText.getText().toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }


        /**
         * 현재 이부분에 두줄 더 들어가야함
         * 중간중간 빼먹은 부분 있음  DFPref 부분 (cookiesharedpref부분과 비교해보기)
         * 현재 LoginData에 isResult 임의적으로 추가
         * getRetrofit부분이 그린램프앱과 다름. 난 getApplicationContext() 넣어주었음
         */

        Timber.i("FCM TOKEN ->" + CookieSharedPreferences.get(CookieSharedPreferences.FCM_TOKEN,""));
        request.setPushToken(CookieSharedPreferences.get(CookieSharedPreferences.FCM_TOKEN,""));

        LoginService.getRetrofit(getApplicationContext()).login(request).enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                hideProgressBar();
                LoginData data = response.body();

                if (data.isResult()) {
                    CookieSharedPreferences.put(CookieSharedPreferences.TOKEN, data.getToken());
                    CookieSharedPreferences.put(CookieSharedPreferences.LID, idEditText.getText().toString());
                    try {
                        CookieSharedPreferences.put(CookieSharedPreferences.LPW, AES.getInstance().encrypt(pwEdiText.getText().toString()));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (InvalidAlgorithmParameterException e) {
                        e.printStackTrace();
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    /**
                     * 그린램프앱에서는 여러가지 정보 putExtra
                     */
                    startActivity(intent);
                    finish();
                } else {
                    showToast(data.getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                hideProgressBar();
                showErrorDialog();

            }
        });





    }


}