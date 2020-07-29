package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.WorkProcessVo;
import com.common.DBInfo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class WorkProcessAdapter extends BaseAdapter {

    DBInfo dbInfo;
    Context context;
    WorkProcessVo workProcessVo;
    private ArrayList<WorkProcessVo> workProcessVoArrayList = new ArrayList<>();

    public void addItem(WorkProcessVo workProcessVo) {
        workProcessVoArrayList.add(workProcessVo);
    }

    @Override
    public int getCount() {
        return workProcessVoArrayList.size();
    }

    @Override
    public Object getItem(int position) {

        return workProcessVoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        context = parent.getContext(); // activity 정보를 읽어오기
        workProcessVo = workProcessVoArrayList.get(position);

        WorkProcessAdapter.ListViewHolder holder = null;

        final TextView inst_date;
        final TextView deli_date;
        final TextView item_nm;
        final TextView lot_no;
        final TextView flow_count;
        final TextView inst_amt;
        final TextView input_amt;
        final TextView input_per;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_work_processing, parent, false);

            inst_date = convertView.findViewById(R.id.workprocess_tv_instdate);
            deli_date = convertView.findViewById(R.id.workprocess_tv_delidate);
            item_nm = convertView.findViewById(R.id.workprocess_tv_itemnm);
            lot_no = convertView.findViewById(R.id.workprocess_tv_lotno);
            flow_count = convertView.findViewById(R.id.workprocess_tv_flowcount);
            inst_amt = convertView.findViewById(R.id.workprocess_tv_instamt);
            input_amt = convertView.findViewById(R.id.workprocess_tv_inputamt);
            input_per = convertView.findViewById(R.id.workprocess_tv_inputper);

            holder = new ListViewHolder();

            holder.inst_date = inst_date;
            holder.deli_date = deli_date;
            holder.lot_no = lot_no;
            holder.item_nm = item_nm;
            holder.flow_count = flow_count;
            holder.inst_amt = inst_amt;
            holder.input_amt = input_amt;
            holder.input_per = input_per;

            convertView.setTag(holder);

        } else {
            holder = (WorkProcessAdapter.ListViewHolder) convertView.getTag();

            inst_date = holder.inst_date;
            deli_date = holder.deli_date;
            lot_no = holder.lot_no;
            item_nm = holder.item_nm;
            flow_count = holder.flow_count;
            inst_amt = holder.inst_amt;
            input_amt = holder.input_amt;
            input_per = holder.input_per;
        }

        inst_date.setText(workProcessVo.getInst_Date());
        deli_date.setText(workProcessVo.getDeli_date());
        lot_no.setText(workProcessVo.getLot_no());
        item_nm.setText(workProcessVo.getItem_nm());
        item_nm.setTag(workProcessVo.getItem_cd());
        flow_count.setText(workProcessVo.getFlow_Count());
        inst_amt.setText(workProcessVo.getInst_amt());
        input_amt.setText(workProcessVo.getInput_amt());
        input_per.setText(workProcessVo.getInput_per());

        return convertView;
    }

    private class ListViewHolder {
        TextView inst_date;
        TextView deli_date;
        TextView item_nm;
        TextView lot_no;
        TextView flow_count;
        TextView inst_amt;
        TextView input_amt;
        TextView input_per;
    }
}
