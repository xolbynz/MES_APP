package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.WorkInstRawVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class WorkInstRawAdapter extends BaseAdapter
{
    Context context;
    WorkInstRawVo workInstRawVo = new WorkInstRawVo();

    private ArrayList<WorkInstRawVo> arrayList = new ArrayList<>();
    public void addItem(WorkInstRawVo workInstRawVo) {
        arrayList.add(workInstRawVo);
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
        workInstRawVo = arrayList.get(position);

        if (convertView == null) {
            LayoutInflater infaInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infaInflater.inflate(R.layout.adapter_work_inst_raw, parent, false);


            TextView custNm = convertView.findViewById(R.id.workInstRaw_tv_custNm);
            TextView rawNm = convertView.findViewById(R.id.workInstRaw_tv_rawNm);
            TextView spec = convertView.findViewById(R.id.workInstRaw_tv_spec);
            TextView inpAmt = convertView.findViewById(R.id.workInstRaw_tv_inpAmt);
            TextView totAmt = convertView.findViewById(R.id.workInstRaw_tv_totAmt);
            TextView stock = convertView.findViewById(R.id.workInstRaw_tv_stock);
            TextView afterStock = convertView.findViewById(R.id.workInstRaw_tv_afterStock);
            TextView unit = convertView.findViewById(R.id.workInstRaw_tv_unit);
            TextView inpUnit = convertView.findViewById(R.id.workInstRaw_tv_inptUnit);

            custNm.setText(workInstRawVo.getCustNM());
            rawNm.setText(workInstRawVo.getRawNm());
            spec.setText(workInstRawVo.getSpec());
            inpAmt.setText(workInstRawVo.getInstAmt());
            totAmt.setText(workInstRawVo.getTotalAmt());
            stock.setText(workInstRawVo.getStock());
            afterStock.setText(workInstRawVo.getAfterStock());
            unit.setText(workInstRawVo.getUnit());
            inpUnit.setText(workInstRawVo.getInputUnit());



        }


        return convertView;
    }
}
