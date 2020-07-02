package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.MoniteringVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class MoniteringAdapter extends BaseAdapter {

    Context context;
    MoniteringVo   moniteringVo = new MoniteringVo();
    ArrayList<MoniteringVo> arrayList = new ArrayList<>();

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

    public void addItem(MoniteringVo moniteringVo) {
        arrayList.add(moniteringVo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        context = parent.getContext(); // activity 정보를 읽어오기

        moniteringVo = arrayList.get(position);


        if (convertView == null) {
            LayoutInflater infaInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infaInflater.inflate(R.layout.adapter_moniter, parent, false);


            TextView cust_nm = convertView.findViewById(R.id.moniter_tv_custNm);
            TextView inst_date = convertView.findViewById(R.id.moniter_tv_ordDate);
            TextView deli_date = convertView.findViewById(R.id.moniter_tv_delDate);
            TextView item_nm = convertView.findViewById(R.id.moniter_tv_itemNm);
            TextView flow_count = convertView.findViewById(R.id.moniter_tv_workAmt);
            TextView inst_amt = convertView.findViewById(R.id.moniter_tv_ordAmt);
            TextView processing = convertView.findViewById(R.id.moniter_tv_workStep);
            TextView input_Date = convertView.findViewById(R.id.moniter_tv_prodDate);
            TextView input_amt = convertView.findViewById(R.id.moniter_tv_inpAmt);
            TextView poor_amt = convertView.findViewById(R.id.moniter_tv_faultyAmt);
            TextView poor_per = convertView.findViewById(R.id.moniter_tv_faultyRate);


            cust_nm.setText(moniteringVo.getCust_nm()); //
            item_nm.setText(moniteringVo.getItem_nm());
            deli_date.setText(moniteringVo.getDeli_date());
            inst_date.setText(moniteringVo.getInst_date()); //
            inst_amt.setText(moniteringVo.getInst_amt());
            processing.setText(moniteringVo.getInst_amt());
            flow_count.setText(moniteringVo.getFlow_count());
            input_Date.setText(moniteringVo.getInput_date());
            input_amt.setText(moniteringVo.getInput_amt());
            poor_amt.setText(moniteringVo.getPoor_amt());
            poor_per.setText(moniteringVo.getPoor_per() + "%");

        }
        return convertView; // 뷰 객체 반환
    }

}
