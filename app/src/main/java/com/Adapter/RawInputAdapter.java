package com.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.VO.OrderVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class RawInputAdapter extends BaseAdapter {

    Context context;
    OrderVo orderVo = new OrderVo();
    private ArrayList<OrderVo> arrayList = new ArrayList<>();

    public void addItem(OrderVo orderVo) {
        arrayList.add(orderVo);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        context = parent.getContext(); // activity 정보를 읽어오기
        orderVo = arrayList.get(position);

        if (convertView == null) {
            LayoutInflater infaInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infaInflater.inflate(R.layout.adpter_raw_input, parent, false);

            TextView raw_mat_nm = convertView.findViewById(R.id.rawView_tv_rawNm);
            TextView cust_nm = convertView.findViewById(R.id.rawView_tv_custNm);
            TextView order_date = convertView.findViewById(R.id.rawView_tv_orderDate);
            TextView spec = convertView.findViewById(R.id.rawView_tv_spec);
            TextView order_amt = convertView.findViewById(R.id.rawView_tv_orderAmt);
            TextView orderNon_amt = convertView.findViewById(R.id.rawView_tv_nonInpAmt);
            EditText input_amt = convertView.findViewById(R.id.rawView_et_inpAmt);

            raw_mat_nm.setText(orderVo.getRawmat_Nm());
            cust_nm.setText(orderVo.getCust_Nm());
            order_date.setText(orderVo.getOrder_Date());
            order_amt.setText(orderVo.getOrder_Amt());
            spec.setText(orderVo.getSpec());
            orderNon_amt.setText(orderVo.getInput_NeedAmt());
            input_amt.setText("");
        }


        return convertView;
    }


}
