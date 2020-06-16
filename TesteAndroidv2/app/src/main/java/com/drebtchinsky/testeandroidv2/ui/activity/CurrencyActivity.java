package com.drebtchinsky.testeandroidv2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.drebtchinsky.testeandroidv2.DAO.StatementListDAO;
import com.drebtchinsky.testeandroidv2.DAO.UserAccountDAO;
import com.drebtchinsky.testeandroidv2.R;
import com.drebtchinsky.testeandroidv2.database.BankDatabase;
import com.drebtchinsky.testeandroidv2.entity.UserAccount;
import com.drebtchinsky.testeandroidv2.ui.adapter.ListStatementAdapter;
import com.drebtchinsky.testeandroidv2.utils.StringUtils;

public class CurrencyActivity extends AppCompatActivity {


    private Context context;
    private UserAccountDAO userAccountDAO;
    private StatementListDAO statementListDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        instantiateDAO();

        loadUser();

        loadStatement();
    }

    private void loadStatement() {
        ListView listStatement = findViewById(R.id.currency_listview_statement);
        listStatement.setAdapter(new ListStatementAdapter(statementListDAO.getAll(),this));
    }

    private void loadUser() {
        UserAccount userAccount = userAccountDAO.getAll().get(0);

        loadInfo(R.id.currency_textview_name, userAccount.getName());

        loadInfo(R.id.currency_textview_account, userAccount.getBankAccount() + " / " + StringUtils.agencyFormat(userAccount.getAgency()));

        loadInfo(R.id.currency_textview_balance, StringUtils.currencyFormat(userAccount.getBalance()));
    }

    private void loadInfo(int id, String value) {
        TextView textView = findViewById(id);
        textView.setText(value);
    }

    private void instantiateDAO() {
        this.context = this;

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