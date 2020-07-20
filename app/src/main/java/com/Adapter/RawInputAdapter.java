package com.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;

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

        order_date.setText(orderVo.getOrder_Date()); // 발주서 DATE, CD ,SEQ
        order_date.setTag(orderVo.getOrder_Cd());
        order_date.setHint(orderVo.getOrder_Seq());

        order_amt.setText(orderVo.getOrder_Amt());
        spec.setText(orderVo.getSpec());
        orderNon_amt.setText(orderVo.getInput_NeedAmt());

        input_amt.setOnKeyListener(onKeyListener);


        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Orer_Date = order_date.getText().toString();
                final String Order_Cd = order_date.getTag().toString();
                final String Order_Seq = order_date.getHint().toString();

                final double needamt;
                final double inputamt;

                needamt = Double.parseDouble(orderNon_amt.getText().toString());

                if (!input_amt.getText().toString().equals("")) {
                    inputamt = Double.parseDouble(input_amt.getText().toString());
                }else{
                    inputamt = 0;
                }
                if (needamt < inputamt) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("현재 입고하려는 수량이 미입고 수량보다 많습니다. 진행하시겠습니까?");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            input_amt.setText("");
                        }
                    });

//                    builder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                        }
//                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else{
                    input_Logic(Orer_Date, Order_Cd, Order_Seq);
                }
            }
        });

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

    private void input_Logic(String Order_date, String Order_cd, String Orcder_seq) {

    }


}
