package com.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.RawVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class RawAdapter extends BaseAdapter {

    Context context;
    RawVo rawVo;
    private ArrayList<RawVo> arrayList;

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
        rawVo = arrayList.get(position);

        if (convertView == null) {
            LayoutInflater infaInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infaInflater.inflate(R.layout.adapter_raw_view, parent, false);

            TextView raw_mat_cd = convertView.findViewById(R.id.txt_raw_mat_cd);
            TextView raw_mat_nm = convertView.findViewById(R.id.txt_raw_mat_nm);
            TextView spec = convertView.findViewById(R.id.txt_spec);
            TextView raw_mat_gubun = convertView.findViewById(R.id.txt_raw_mat_gubun);
            TextView type_cd = convertView.findViewById(R.id.txt_type_cd);
            TextView input_unit = convertView.findViewById(R.id.txt_input_unit);
            TextView output_unit = convertView.findViewById(R.id.txt_output_unit);
            TextView input_price = convertView.findViewById(R.id.txt_input_price);
            TextView output_price = convertView.findViewById(R.id.txt_output_price);
            TextView st_status_yn = convertView.findViewById(R.id.txt_st_status_yn);
            TextView raw_storage = convertView.findViewById(R.id.txt_raw_strage);
            TextView used_cd = convertView.findViewById(R.id.txt_used_cd);
            TextView basic_stock = convertView.findViewById(R.id.txt_basic_stock);
            TextView bal_stock = convertView.findViewById(R.id.txt_bal_stock);
            TextView check_gubun = convertView.findViewById(R.id.txt_check_gubun);
            TextView prop_stock = convertView.findViewById(R.id.txt_prop_stock);


            raw_mat_cd.setText(rawVo.getRaw_mat_cd());
            raw_mat_nm.setText(rawVo.getRaw_mat_nm());
            spec.setText(rawVo.getSpec());
            raw_mat_gubun.setText(rawVo.getRaw_mat_gubun());
            type_cd.setText(rawVo.getType_cd());
            input_unit.setText(rawVo.getInput_unit());
            output_unit.setText(rawVo.getOutput_unit());
            input_price.setText(rawVo.getInput_price());
            output_price.setText(rawVo.getOutput_price());
            st_status_yn.setText(rawVo.getSt_status_yn());
            raw_storage.setText(rawVo.getRaw_storage());
            used_cd.setText(rawVo.getUsed_cd());
            basic_stock.setText(rawVo.getBasic_stock());
            bal_stock.setText(rawVo.getBal_stock());
            check_gubun.setText(rawVo.getCheck_gubun());
            prop_stock.setText(rawVo.getProp_stock());
        }
        return convertView; // 뷰 객체 반환
    }

}
