package com.example.beaconscantest.api.resource;

public class APIUrl {


    /**
     * api 서버  url
     * url 주소 오웬 코드에서 가져온 것! 바꿔야 할 수 도 있음
     */
    public static final String BASE_URL = "http://112.220.86.130:8090/";


    /**
     * 실제 api 경로
     */

    public static final String COMMUTE_API = "api/commute/";
    public static final String PUT_BEACON_RECORD = COMMUTE_API + "put/beaconRecord";
    public static final String GET_BEACON_RECORD = COMMUTE_API + "get/beaconRecord";


    public static final String USER_API = "api/user/";
    public static final String LOGIN = USER_API + "/doLogin";


}
