package com.drebtchinsky.testeandroidv2.retrofit.service;

import com.drebtchinsky.testeandroidv2.entity.StatementList;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatementListService {
    @GET("statements/{id}")
    Call<Map<String, Object>> statements(@Path("id") int id);
}
