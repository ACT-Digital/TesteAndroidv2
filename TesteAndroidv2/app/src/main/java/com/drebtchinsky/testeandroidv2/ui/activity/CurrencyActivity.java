package com.drebtchinsky.testeandroidv2.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.drebtchinsky.testeandroidv2.DAO.StatementListDAO;
import com.drebtchinsky.testeandroidv2.DAO.UserAccountDAO;
import com.drebtchinsky.testeandroidv2.R;
import com.drebtchinsky.testeandroidv2.asynctask.BaseAsyncTask;
import com.drebtchinsky.testeandroidv2.database.BankDatabase;
import com.drebtchinsky.testeandroidv2.entity.StatementList;
import com.drebtchinsky.testeandroidv2.entity.UserAccount;
import com.drebtchinsky.testeandroidv2.retrofit.BankRetrofit;
import com.drebtchinsky.testeandroidv2.retrofit.service.StatementListService;
import com.drebtchinsky.testeandroidv2.ui.adapter.ListStatementAdapter;
import com.drebtchinsky.testeandroidv2.utils.NetworkUtils;
import com.drebtchinsky.testeandroidv2.utils.StringUtils;
import com.google.gson.internal.LinkedTreeMap;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class CurrencyActivity extends AppCompatActivity {


    private Context context;
    private UserAccountDAO userAccountDAO;
    private StatementListDAO statementListDAO;
    private UserAccount userAccount;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        context = this;
    }

    @Override
    protected void onResume() {
        super.onResume();

        progressBar = findViewById(R.id.currency_progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        instantiateDAO();

        loadUser();

        NetworkUtils networkUtils = new NetworkUtils(context);

        if(networkUtils.isNetworkConnected()) {
            loadStatement();
        }else{
            getLocalStatements();
        }
    }

    private void loadStatement() {
        progressBar.setVisibility(View.VISIBLE);
        Call<Map<String, Object>> statementsCall = setRequestParameters();

        new BaseAsyncTask<>(() -> {
            return getResponse(statementsCall);
        }, statementLists -> {
            if (statementLists != null) {
                setLocalStatements(statementLists);
                getLocalStatements();
            } else {
                Toast.makeText(context, "Não foi possivel carregar suas transações!", Toast.LENGTH_LONG).show();
            }
        }).execute();


    }

    private void getLocalStatements() {
        ListView listStatement = findViewById(R.id.currency_listview_statement);
        listStatement.setAdapter(new ListStatementAdapter(statementListDAO.getAll(), context));
        progressBar.setVisibility(View.GONE);
    }

    private void setLocalStatements(List<Object> statementLists) {
        statementListDAO.removeAll();
        for (Object item : statementLists) {
            LinkedTreeMap<Object, Object> t = (LinkedTreeMap) item;
            statementListDAO.create(
                    new StatementList(
                            t.get("title").toString(),
                            t.get("desc").toString(),
                            t.get("date").toString(),
                            Double.valueOf(t.get("value").toString())
                    ));
        }
    }

    @Nullable
    private List<Object> getResponse(Call<Map<String, Object>> statementsCall) {
        try {
            Response<Map<String, Object>> response = statementsCall.execute();
            Object statementLists = response.body().get("statementList");
            List<Object> list = new ArrayList<>();
            if (statementLists.getClass().isArray()) {
                list = Arrays.asList((Object[]) statementLists);
            } else if (statementLists instanceof Collection) {
                list = new ArrayList<>((Collection<Object>) statementLists);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Call<Map<String, Object>> setRequestParameters() {
        StatementListService statementListService = new BankRetrofit().getStatementListService();
        return statementListService.statements(userAccount.getUserId());
    }

    private void loadUser() {
        userAccount = userAccountDAO.getAll().get(0);

        loadInfo(R.id.currency_textview_name, userAccount.getName());

        loadInfo(R.id.currency_textview_account, userAccount.getBankAccount() + " / " + StringUtils.agencyFormat(userAccount.getAgency()));

        loadInfo(R.id.currency_textview_balance, StringUtils.currencyFormat(userAccount.getBalance()));
    }

    private void loadInfo(int id, String value) {
        TextView textView = findViewById(id);
        textView.setText(value);
    }

    private void instantiateDAO() {

        userAccountDAO = BankDatabase.getInstance(context)
                .getUserAccountDAO();

        statementListDAO = BankDatabase.getInstance(context)
                .getStatementListDAO();
    }

    public void clickLogout(View view) {
        userAccountDAO.removeAll();
        statementListDAO.removeAll();
        finish();
    }
}