package com.drebtchinsky.testeandroidv2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.drebtchinsky.testeandroidv2.DAO.StatementListDAO;
import com.drebtchinsky.testeandroidv2.DAO.UserAccountDAO;
import com.drebtchinsky.testeandroidv2.R;
import com.drebtchinsky.testeandroidv2.database.BankDatabase;
import com.drebtchinsky.testeandroidv2.entity.StatementList;
import com.drebtchinsky.testeandroidv2.entity.UserAccount;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private UserAccountDAO userAccountDAO;
    private StatementListDAO statementListDAO;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.context = this;
        userAccountDAO = BankDatabase.getInstance(context)
                .getUserAccountDAO();
        statementListDAO = BankDatabase.getInstance(context)
                .getStatementListDAO();
    }

    public void clickLogin(View view) {
        Intent intent = new Intent(this, CurrencyActivity.class);
        userAccountDAO.removeAll();
        statementListDAO.removeAll();

        userAccountDAO.create(new UserAccount(
                1,
                "Jose da Silva Teste",
                "2050",
                "012314564",
                33445
        ));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


        statementListDAO.create(new StatementList(
                "Pagamento",
                "Conta de luz",
                "2018-08-15",
                -50
        ));

        statementListDAO.create(new StatementList(
                "TED Recebida",
                "Joao Alfredo",
                "2018-07-25",
                745.03
        ));

        statementListDAO.create(new StatementList(
                "DOC Recebida",
                "Victor Silva",
                "2018-06-23",
                399.9
        ));

        statementListDAO.create(new StatementList(
                "Pagamento",
                "Conta de internet",
                "2018-05-12",
                -73.4
        ));

        startActivity(intent);
    }
}