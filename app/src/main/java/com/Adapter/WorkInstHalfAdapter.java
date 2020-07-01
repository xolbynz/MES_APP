package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.WorkInstHalfVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class WorkInstHalfAdapter extends BaseAdapter {

    Context context;
    WorkInstHalfVo workInstHalfVo = new WorkInstHalfVo();



    private ArrayList<WorkInstHalfVo> arrayList = new ArrayList<>();
    public void addItem(WorkInstHalfVo workInstHalfVo) {
        arrayList.add(workInstHalfVo);
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext(); // activity 정보를 읽어오기
        workInstHalfVo = arrayList.get(position);

        if (convertView == null) {
            LayoutInflater infaInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infaInflater.inflate(R.layout.adapter_work_inst_half, parent, false);



            TextView custNm = convertView.findViewById(R.id.workInstRaw_tv_custNm);
            TextView halfNm = convertView.findViewById(R.id.workInstRaw_tv_rawNm);
            TextView spec = convertView.findViewById(R.id.workInstRaw_tv_spec);
            TextView inpAmt = convertView.findViewById(R.id.workInstRaw_tv_inpAmt);
            TextView totAmt = convertView.findViewById(R.id.workInstRaw_tv_totAmt);
            TextView stock = convertView.findViewById(R.id.workInstRaw_tv_stock);
            TextView afterStock = convertView.findViewById(R.id.workInstRaw_tv_afterStock);
            TextView unit = convertView.findViewById(R.id.workInstRaw_tv_unit);
            TextView inpUnit = convertView.findViewById(R.id.workInstRaw_tv_inptUnit);

            custNm.setText(workInstHalfVo.getCustNM());
            halfNm.setText(workInstHalfVo.getHalfNm());
            spec.setText(workInstHalfVo.getSpec());
            inpAmt.setText(workInstHalfVo.getInstAmt());
            totAmt.setText(workInstHalfVo.getTotalAmt());
            stock.setText(workInstHalfVo.getStock());
            afterStock.setText(workInstHalfVo.getAfterStock());
            unit.setText(workInstHalfVo.getUnit());
            inpUnit.setText(workInstHalfVo.getInputUnit());


        }

        return convertView;
    }
}
