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
    RawVo rawVo = new RawVo();
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
        rawVo = arrayList.get(position);

        if (convertView == null) {
            LayoutInflater infaInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infaInflater.inflate(R.layout.adapter_raw_view, parent, false);

            TextView raw_mat_cd = convertView.findViewById(R.id.rawView_tv_rawNm);
            TextView raw_mat_nm = convertView.findViewById(R.id.rawView_tv_rawNm);
            TextView spec = convertView.findViewById(R.id.rawView_tv_spec);
            TextView unit_nm = convertView.findViewById(R.id.rawView_tv_unit);
            TextView loc = convertView.findViewById(R.id.rawView_tv_position);
            TextView input_amt = convertView.findViewById(R.id.rawView_tv_inpAmt);
            TextView output_amt = convertView.findViewById(R.id.rawView_tv_outpAmt);
            TextView curr_amt = convertView.findViewById(R.id.rawView_tv_amt);


            String column1 = "원자재명";
            String column2 = "구매처명";
            String column3 = "위치";
            String column4 = "입고량";
            String column5 = "출고량";
            String column6 = "재고량";
            String column7 = "단위";


            raw_mat_cd.setText(rawVo.getRaw_mat_cd()); //
            raw_mat_nm.setText(rawVo.getRaw_mat_nm()); //
            spec.setText(rawVo.getSpec()); //
            unit_nm.setText(rawVo.getUnit_nm());
            input_amt.setText(rawVo.getInput_amt());
            output_amt.setText(rawVo.getOutput_amt());
            curr_amt.setText(rawVo.getCurr_amt());
            loc.setText(rawVo.getLoc());
        }
        return convertView; // 뷰 객체 반환
    }

}
