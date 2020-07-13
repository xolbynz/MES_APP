package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.VO.WorkInstVo;
import com.example.mes_app.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WorkInstAdapter extends BaseAdapter {

    Context context;
    WorkInstVo workInstVo = new WorkInstVo();
    int position;

    public ArrayList<WorkInstVo> arrayList = new ArrayList<>();

    public void addItem(WorkInstVo workInstVo) {
        arrayList.add(workInstVo);
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

        context = parent.getContext();
        workInstVo = arrayList.get(position);
        WorkInstAdapter.ListViewHolder holder = null;
        this.position = position;

        final TextView lotNo;
        final TextView custNM;
        final TextView itemNm;
        final TextView instAmt;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_work_inst, parent, false);

            lotNo = convertView.findViewById(R.id.workInst_tv_lotNo);
            custNM = convertView.findViewById(R.id.workInst_tv_custNm);
            itemNm = convertView.findViewById(R.id.workInst_tv_itemNm);
            instAmt = convertView.findViewById(R.id.workInst_tv_instAmt);

            holder = new WorkInstAdapter.ListViewHolder();

            holder.lotNo = lotNo;
            holder.custNM = custNM;
            holder.itemNm = itemNm;
            holder.instAmt = instAmt;

            convertView.setTag(holder);
        } else {

            holder = (WorkInstAdapter.ListViewHolder) convertView.getTag();

            lotNo = holder.lotNo;
            custNM = holder.custNM;
            itemNm = holder.itemNm;
            instAmt = holder.instAmt;
        }

        lotNo.setText(workInstVo.getLotNO());
        custNM.setText(workInstVo.getCustNm());
        itemNm.setText(workInstVo.getItemNm());
        instAmt.setText(workInstVo.getInstAmt());
        return convertView;
    }

    private class ListViewHolder {
        TextView lotNo;
        TextView custNM;
        TextView itemNm;
        TextView instAmt;
    }


}
