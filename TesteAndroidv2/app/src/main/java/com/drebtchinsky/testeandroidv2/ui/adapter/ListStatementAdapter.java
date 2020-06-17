package com.drebtchinsky.testeandroidv2.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.drebtchinsky.testeandroidv2.R;
import com.drebtchinsky.testeandroidv2.entity.StatementList;
import com.drebtchinsky.testeandroidv2.utils.StringUtils;

import java.util.List;

public class ListStatementAdapter extends BaseAdapter {
    private final List<StatementList> list;
    private final Context context;

    public ListStatementAdapter(List<StatementList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getStatementId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_statement, parent, false);

        StatementList statementList = list.get(position);

        loadInfo(view, R.id.item_transaction_title, statementList.getTitle());

        loadInfo(view, R.id.item_transaction_description, statementList.getDesc());

        loadInfo(view, R.id.item_transaction_date, statementList.getDate().toString());

        loadInfo(view, R.id.item_transaction_value, StringUtils.currencyFormat(statementList.getValue()));

        return view;
    }

    private void loadInfo(View view, int id, String value) {
        TextView title = view.findViewById(id);
        title.setText(value);
    }
}
