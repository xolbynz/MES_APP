package com.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    EditText input_amt;

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
        this.position = position;

        if (convertView == null) {
            LayoutInflater infaInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infaInflater.inflate(R.layout.adapter_raw_input, parent, false);

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


            input_amt.addTextChangedListener(new TextWatcher() {


                @Override

                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    // 입력되는 텍스트에 변화가 있을 때
                }

                @Override public void afterTextChanged(Editable arg0) {

                    // 입력이 끝났을 때
//                    orderVo.setTemp_amt(input_amt.getText().toString());
//                    String yy = input_amt.getText().toString();
//                    arrayList.set(position , orderVo);
                }

                @Override

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    // 입력하기 전에

                }

            });
        }
        return convertView;
    }


}
