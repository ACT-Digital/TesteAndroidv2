package com.drebtchinsky.testeandroidv2.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.drebtchinsky.testeandroidv2.DAO.UserAccountDAO;
import com.drebtchinsky.testeandroidv2.R;
import com.drebtchinsky.testeandroidv2.asynctask.BaseAsyncTask;
import com.drebtchinsky.testeandroidv2.database.BankDatabase;
import com.drebtchinsky.testeandroidv2.entity.UserAccount;
import com.drebtchinsky.testeandroidv2.retrofit.BankRetrofit;
import com.drebtchinsky.testeandroidv2.retrofit.service.UserAccountService;
import com.drebtchinsky.testeandroidv2.utils.NetworkUtils;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private UserAccountDAO userAccountDAO;
    private Context context;
    private ProgressBar progressBar;
    private EditText editUser;
    private EditText editPassword;
    private Button btnLogin;
    private ImageView imageLogo;
    private NetworkUtils networkUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.context = this;

        userAccountDAO = BankDatabase.getInstance(context)
                .getUserAccountDAO();

        if(userAccountDAO.getAll().size()>0){
            callIntent();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        setAllView();

        setLoading(false);
        networkUtils = new NetworkUtils(this);
    }

    private void setAllView() {
        progressBar = findViewById(R.id.login_progressBar);
        editUser = findViewById(R.id.login_edit_user);
        editPassword = findViewById(R.id.login_edit_password);
        btnLogin = findViewById(R.id.login_btn_login);
        imageLogo = findViewById(R.id.login_image_logo);
    }

    public void clickLogin(View view) {
        if (networkUtils.isNetworkConnected()) {
            setLoading(true);

            Call<Map<String, UserAccount>> loginCall = prepareRequestParams();

            new BaseAsyncTask<>(() -> {
                return getResponse(loginCall);
            }, userAccount -> {
                if (userAccount != null) {
                    saveUser(userAccount);
                    callIntent();
                } else {
                    setLoading(false);
                    Toast.makeText(this, "Não foi possivel realizar o login!", Toast.LENGTH_LONG).show();
                }
            }).execute();
        }else{
            Toast.makeText(this, "Não foi possivel realizar o login, pois você não está conectado a internet!", Toast.LENGTH_LONG).show();
        }
    }

    private void setLoading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.INVISIBLE);
        editUser.setVisibility(loading ? View.INVISIBLE : View.VISIBLE);
        editPassword.setVisibility(loading ? View.INVISIBLE : View.VISIBLE);
        btnLogin.setVisibility(loading ? View.INVISIBLE : View.VISIBLE);
        imageLogo.setVisibility(loading ? View.INVISIBLE : View.VISIBLE);
    }

    private void saveUser(UserAccount userAccount) {
        userAccountDAO.removeAll();
        userAccountDAO.create(userAccount);
    }

    @Nullable
    private UserAccount getResponse(Call<Map<String, UserAccount>> loginCall) {
        try {
            Response<Map<String, UserAccount>> response = loginCall.execute();
            UserAccount userAccount = response.body().get("userAccount");
            return userAccount;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Call<Map<String, UserAccount>> prepareRequestParams() {
        String user = editUser.getText().toString();
        String password = editPassword.getText().toString();
        UserAccountService userAccountService = new BankRetrofit().getUserAccountService();

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("user", user);
        jsonParams.put("password", password);

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        return userAccountService.login(body);
    }

    private void callIntent() {
        Intent intent = new Intent(this, CurrencyActivity.class);
        startActivity(intent);
    }
}