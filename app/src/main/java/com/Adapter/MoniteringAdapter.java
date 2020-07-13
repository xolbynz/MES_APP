package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.VO.MoniteringVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class MoniteringAdapter extends BaseAdapter {

    Context context;
    MoniteringVo moniteringVo = new MoniteringVo();
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
        MoniteringAdapter.ListViewHolder holder = null;
        moniteringVo = arrayList.get(position);

        final TextView cust_nm;
        final TextView inst_date;
        final TextView item_nm;
        final TextView deli_date;
        final TextView flow_count;
        final TextView inst_amt;
        final TextView processing;
        final TextView input_Date;
        final TextView input_amt;
        final TextView poor_amt;
        final TextView poor_per;


        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_moniter, parent, false);

            cust_nm = convertView.findViewById(R.id.moniter_tv_custNm);
            inst_date = convertView.findViewById(R.id.moniter_tv_ordDate);
            deli_date = convertView.findViewById(R.id.moniter_tv_delDate);
            item_nm = convertView.findViewById(R.id.moniter_tv_itemNm);
            flow_count = convertView.findViewById(R.id.moniter_tv_workAmt);
            inst_amt = convertView.findViewById(R.id.moniter_tv_ordAmt);
            processing = convertView.findViewById(R.id.moniter_tv_workStep);
            input_Date = convertView.findViewById(R.id.moniter_tv_prodDate);
            input_amt = convertView.findViewById(R.id.moniter_tv_inpAmt);
            poor_amt = convertView.findViewById(R.id.moniter_tv_faultyAmt);
            poor_per = convertView.findViewById(R.id.moniter_tv_faultyRate);

            holder = new MoniteringAdapter.ListViewHolder();

            holder.cust_nm = cust_nm;
            holder.inst_date = inst_date;
            holder.item_nm = item_nm;
            holder.deli_date = deli_date;
            holder.flow_count = flow_count;
            holder.inst_amt = inst_amt;
            holder.processing = processing;
            holder.input_Date = input_Date;
            holder.input_amt = input_amt;
            holder.poor_amt = poor_amt;
            holder.poor_per = poor_per;

            convertView.setTag(holder);

        } else {

            holder = (MoniteringAdapter.ListViewHolder) convertView.getTag();

            cust_nm = holder.cust_nm;
            inst_date = holder.inst_date;
            item_nm = holder.item_nm;
            deli_date = holder.deli_date;
            flow_count = holder.flow_count;
            inst_amt = holder.inst_amt;
            processing = holder.processing;
            input_Date = holder.input_Date;
            input_amt = holder.input_amt;
            poor_amt = holder.poor_amt;
            poor_per = holder.poor_per;

        }

        cust_nm.setText(moniteringVo.getCust_nm());
        inst_date.setText(moniteringVo.getInst_date());
        item_nm.setText(moniteringVo.getItem_nm());
        deli_date.setText(moniteringVo.getDeli_date());
        flow_count.setText(moniteringVo.getFlow_count());
        inst_amt.setText(moniteringVo.getInst_amt());
        processing.setText(moniteringVo.getProcessing());
        input_Date.setText(moniteringVo.getInput_date());
        input_amt.setText(moniteringVo.getInput_amt());
        poor_amt.setText(moniteringVo.getPoor_amt());
        poor_per.setText(moniteringVo.getPoor_per());

        return convertView; // 뷰 객체 반환
    }

    ;

    private class ListViewHolder {
        TextView cust_nm;
        TextView inst_date;
        TextView item_nm;
        TextView deli_date;
        TextView flow_count;
        TextView inst_amt;
        TextView processing;
        TextView input_Date;
        TextView input_amt;
        TextView poor_amt;
        TextView poor_per;
    }

}
