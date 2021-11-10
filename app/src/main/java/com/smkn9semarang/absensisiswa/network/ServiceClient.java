package com.smkn9semarang.absensisiswa.network;

import com.smkn9semarang.absensisiswa.login.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceClient {

    //ini untuk melakukan login
    @POST("exec")
    Call<ResponseLogin> login(@Query(encoded = true, value = "action")String action,
                              @Query(encoded = true, value = "nis")String nis,
                              @Query(encoded = true, value = "password")String password);


}
