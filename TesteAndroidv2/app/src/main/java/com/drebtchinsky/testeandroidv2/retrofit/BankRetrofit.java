package com.drebtchinsky.testeandroidv2.retrofit;

import com.drebtchinsky.testeandroidv2.retrofit.service.StatementListService;
import com.drebtchinsky.testeandroidv2.retrofit.service.UserAccountService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BankRetrofit {

    private final UserAccountService userAccountService;
    private final StatementListService statementListService;

    public BankRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userAccountService = retrofit.create(UserAccountService.class);
        statementListService = retrofit.create(StatementListService.class);
    }

    public UserAccountService getUserAccountService() {
        return userAccountService;
    }

    public StatementListService getStatementListService() {
        return statementListService;
    }
}
