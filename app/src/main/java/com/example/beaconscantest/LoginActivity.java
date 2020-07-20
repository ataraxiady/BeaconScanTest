package com.example.beaconscantest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    }


    private void onLogin(){
        showProgressBar();
        LoginModel request = new LoginModel();


        request.setWebId(idEditText.getText().toString());
        try{
            String pw = AES.getInstance().encrypt(idEditText.getText().toString());
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


    }


}