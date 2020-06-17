package com.drebtchinsky.testeandroidv2.retrofit.service;

import com.drebtchinsky.testeandroidv2.entity.UserAccount;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAccountService {
    @POST("login")
    Call<Map<String, UserAccount>> login(@Body RequestBody body);
}
