package com.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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

        View gridView;

        context = parent.getContext(); // activity 정보를 읽어오기
        orderVo = arrayList.get(position);

        if (convertView == null) { 
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                gridView = inflater.inflate(R.layout.adpter_raw_input, null);

        }
        else
        {
            gridView = convertView;
        }

            raw_mat_nm = gridView.findViewById(R.id.rawView_tv_rawNm);
            cust_nm = gridView.findViewById(R.id.rawView_tv_custNm);
            order_date = gridView.findViewById(R.id.rawView_tv_orderDate);
            spec = gridView.findViewById(R.id.rawView_tv_spec);
            order_amt = gridView.findViewById(R.id.rawView_tv_orderAmt);
            orderNon_amt = gridView.findViewById(R.id.rawView_tv_nonInpAmt);
            input_amt = gridView.findViewById(R.id.rawView_et_inpAmt);

            raw_mat_nm.setText(orderVo.getRawmat_Nm());
            cust_nm.setText(orderVo.getCust_Nm());
            order_date.setText(orderVo.getOrder_Date());
            order_amt.setText(orderVo.getOrder_Amt());
            spec.setText(orderVo.getSpec());
            orderNon_amt.setText(orderVo.getInput_NeedAmt());
            input_amt.addTextChangedListener(textWatcher);
            input_amt.setOnKeyListener(onKeyListener);

            input_amt.setText("0");

            input_amt.addTextChangedListener(textWatcher);
        return gridView;
    }

    EditText.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0 );
                return true;
            }
            return false;
        }
    };

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (input_amt.getText().equals("0"))
            {
                input_amt.setText("");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
