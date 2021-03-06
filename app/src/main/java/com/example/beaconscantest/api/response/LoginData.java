package com.example.beaconscantest.api.response;

import androidx.annotation.NonNull;

public class LoginData {

    private String webId;
    private String webPw;
    private String pushToken;


    /**
     * 원래 BaseModel에 들어가는 것이지만 일단 여기다가 변수 선언
     * BaseModel을 만들어만 놓았음
     */
    boolean result;
    String message;
    String token;


    public LoginData(){ }

    public String getWebId() {return webId;}
    public void setWebId(String webId) {this.webId = webId;}

    public String getWebPw() {return webPw;}
    public  void setWebPw(String webPw) {this.webPw = webPw;}

    public String getPushToken() {return pushToken;}
    public void setPushToken(String pushToken) {this.pushToken = pushToken; }


    /**
     * 그린램프앱에서는  basemodel에 있던 애들
     * @return
     */
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }





    @NonNull
    @Override

    /**
     * result, message, token은 Base Model에서 가져오는 것
     */
    public String toString() {
        return "LoginModel{" +
                "webId='" + webId + '\'' +
                ", webPw='" + webPw + '\'' +
                ", pushToken='" + pushToken + '\'' +
                ", result=" + result +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
