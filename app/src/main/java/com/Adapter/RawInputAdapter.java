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

    TextView raw_mat_nm;
    TextView cust_nm;
    TextView order_date;
    TextView spec;
    TextView order_amt;
    TextView orderNon_amt;
    public EditText input_amt;

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

            raw_mat_nm = convertView.findViewById(R.id.rawView_tv_rawNm);
            cust_nm = convertView.findViewById(R.id.rawView_tv_custNm);
            order_date = convertView.findViewById(R.id.rawView_tv_orderDate);
            spec = convertView.findViewById(R.id.rawView_tv_spec);
            order_amt = convertView.findViewById(R.id.rawView_tv_orderAmt);
            orderNon_amt = convertView.findViewById(R.id.rawView_tv_nonInpAmt);
            input_amt = convertView.findViewById(R.id.rawView_et_inpAmt);

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


    EditText.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER) {

            }
            return false;
        }
    };


}
