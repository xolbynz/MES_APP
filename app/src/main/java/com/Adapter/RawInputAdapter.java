package com.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.VO.OrderVo;
import com.example.mes_app.R;
import com.mes_app.CustomDialog;

import java.util.ArrayList;

public class RawInputAdapter extends BaseAdapter {

    Context context;
    OrderVo orderVo = new OrderVo();
    private ArrayList<OrderVo> arrayList = new ArrayList<>();
    private Activity activity;
    private int layout;

    private Button btn_input;
    InputMethodManager imm;


    int position;

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
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        context = parent.getContext(); // activity 정보를 읽어오기
        orderVo = arrayList.get(position);

        RawInputAdapter.ListViewHolder holder = null;

        final int adptPosition = position;

        final TextView raw_mat_nm;
        final TextView cust_nm;
        final TextView order_date;
        final TextView spec;
        final TextView order_amt;
        final TextView orderNon_amt;
        final EditText input_amt;
        final Button btn_input;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_raw_input, parent, false);

            raw_mat_nm = convertView.findViewById(R.id.rawInp_tv_rawNm);
            cust_nm = convertView.findViewById(R.id.rawInp_tv_custNm);
            order_date = convertView.findViewById(R.id.rawInp_tv_orderDate);
            spec = convertView.findViewById(R.id.rawInp_tv_spec);
            order_amt = convertView.findViewById(R.id.rawInp_tv_orderAmt);
            orderNon_amt = convertView.findViewById(R.id.rawInp_tv_nonInpAmt);
            input_amt = convertView.findViewById(R.id.rawInp_et_inpAmt);
            btn_input = convertView.findViewById(R.id.rawInp_btn_input);

            holder = new RawInputAdapter.ListViewHolder();

            holder.raw_mat_nm = raw_mat_nm;
            holder.cust_nm = cust_nm;
            holder.order_date = order_date;
            holder.spec = spec;
            holder.order_amt = order_amt;
            holder.orderNon_amt = orderNon_amt;
            holder.input_amt = input_amt;
            holder.btn_input = btn_input;

            convertView.setTag(holder);

        } else {

            holder = (RawInputAdapter.ListViewHolder) convertView.getTag();

            raw_mat_nm = holder.raw_mat_nm;
            cust_nm = holder.cust_nm;
            order_date = holder.order_date;
            spec = holder.spec;
            order_amt = holder.order_amt;
            orderNon_amt = holder.orderNon_amt;
            input_amt = holder.input_amt;
            btn_input = holder.btn_input;

        }

        raw_mat_nm.setText(orderVo.getRawmat_Nm());
        cust_nm.setText(orderVo.getCust_Nm());
        order_date.setText(orderVo.getOrder_Date());
        order_amt.setText(orderVo.getOrder_Amt());
        spec.setText(orderVo.getSpec());
        orderNon_amt.setText(orderVo.getInput_NeedAmt());
        input_amt.setOnKeyListener(onKeyListener);


        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        input_amt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (input_amt.getText().equals("0")) {
                    input_amt.setText(null);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (input_amt.getText().equals("0")) {
                    input_amt.setText(null);
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_amt.setText("0");
        btn_input.setTag((int) position);

        return convertView;
    }

    EditText.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        }
    };

    private class ListViewHolder {
        TextView raw_mat_nm;
        TextView cust_nm;
        TextView order_date;
        TextView spec;
        TextView order_amt;
        TextView orderNon_amt;
        EditText input_amt;
        Button btn_input;
    }


}
