package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.MoniteringVo;
import com.VO.RawVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class MoniteringAdapter extends BaseAdapter {

    Context context;
    MoniteringVo moniteringVo;
    ArrayList<MoniteringVo> arrayList;


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addItem(MoniteringVo moniteringVo) {
        arrayList.add(moniteringVo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        context = parent.getContext(); // activity 정보를 읽어오기
        moniteringVo= new MoniteringVo();
        moniteringVo = arrayList.get(position);

        if (convertView == null) {
            LayoutInflater infaInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infaInflater.inflate(R.layout.adapter_raw_view, parent, false);


            TextView cust_cd = convertView.findViewById(R.id.rawView_tv_rawNm);
            TextView cust_nm = convertView.findViewById(R.id.rawView_tv_rawNm);
            TextView inst_date = convertView.findViewById(R.id.rawView_tv_spec);
            TextView deli_date = convertView.findViewById(R.id.rawView_tv_unit);
            TextView item_cd = convertView.findViewById(R.id.rawView_tv_position);
            TextView item_nm = convertView.findViewById(R.id.rawView_tv_inpAmt);
            TextView lot_no = convertView.findViewById(R.id.rawView_tv_outpAmt);
            TextView flow_count = convertView.findViewById(R.id.rawView_tv_amt);
            TextView inst_amt = convertView.findViewById(R.id.rawView_tv_amt);
            TextView processing = convertView.findViewById(R.id.rawView_tv_amt);
            TextView poor_amt = convertView.findViewById(R.id.rawView_tv_amt);
            TextView input_per = convertView.findViewById(R.id.rawView_tv_amt);
            TextView poor_per = convertView.findViewById(R.id.rawView_tv_amt);


            cust_cd.setText(moniteringVo.getCust_cd()); //
            cust_nm.setText(moniteringVo.getCust_nm()); //
            inst_date.setText(moniteringVo.getInst_date()); //
            deli_date.setText(moniteringVo.getDeli_date());
            item_cd.setText(moniteringVo.getItem_cd());
            item_nm.setText(moniteringVo.getItem_nm());
            lot_no.setText(moniteringVo.getLot_no());
            flow_count.setText(moniteringVo.getFlow_count());
            inst_amt.setText(moniteringVo.getInst_amt());
            processing.setText(moniteringVo.getInst_amt());
            poor_amt.setText(moniteringVo.getPoor_amt());
            input_per.setText(moniteringVo.getInput_per() + "%");
            poor_per.setText(moniteringVo.getPoor_per() + "%");


        }
        return convertView; // 뷰 객체 반환
    }
    }
}
