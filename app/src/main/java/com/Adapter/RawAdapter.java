package com.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.VO.RawVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class RawAdapter extends BaseAdapter {

    Context context;
    RawVo rawVo;
    private ArrayList<RawVo> arrayList = new ArrayList<>();

    public void addItem(RawVo rawVo) {
        arrayList.add(rawVo);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        context = parent.getContext(); // activity 정보를 읽어오기
        rawVo = new RawVo();
        rawVo = arrayList.get(position);

        RawAdapter.ListViewHolder holder = null;

        final TextView raw_mat_nm;
        final TextView spec;
        final TextView unit_nm;
        final TextView loc;
        final TextView input_amt;
        final TextView output_amt;
        final TextView curr_amt;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_raw_view, parent, false);


            raw_mat_nm = convertView.findViewById(R.id.rawView_tv_rawNm);
            spec = convertView.findViewById(R.id.rawView_tv_spec);
            unit_nm = convertView.findViewById(R.id.rawView_tv_unit);
            loc = convertView.findViewById(R.id.rawView_tv_position);
            input_amt = convertView.findViewById(R.id.rawView_tv_inpAmt);
            output_amt = convertView.findViewById(R.id.rawView_tv_outpAmt);
            curr_amt = convertView.findViewById(R.id.rawView_tv_amt);

            holder = new RawAdapter.ListViewHolder();

            holder.raw_mat_nm = raw_mat_nm;
            holder.spec = spec;
            holder.unit_nm = unit_nm;
            holder.loc = loc;
            holder.input_amt = input_amt;
            holder.output_amt = output_amt;
            holder.curr_amt = curr_amt;

            convertView.setTag(holder);

        } else {

            holder = (RawAdapter.ListViewHolder) convertView.getTag();

            raw_mat_nm = holder.raw_mat_nm;
            spec = holder.spec;
            unit_nm = holder.unit_nm;
            input_amt = holder.input_amt;
            output_amt = holder.output_amt;
            curr_amt = holder.curr_amt;
            loc = holder.loc;

        }

        raw_mat_nm.setText(rawVo.getRaw_mat_nm()); //
        spec.setText(rawVo.getSpec()); //
        unit_nm.setText(rawVo.getUnit_nm());
        input_amt.setText(rawVo.getInput_amt());
        output_amt.setText(rawVo.getOutput_amt());
        curr_amt.setText(rawVo.getCurr_amt());
        loc.setText(rawVo.getLoc());

        return convertView; // 뷰 객체 반환
    }

    private class ListViewHolder {
        TextView raw_mat_nm;
        TextView spec;
        TextView unit_nm;
        TextView loc;
        TextView input_amt;
        TextView output_amt;
        TextView curr_amt;
    }


}
