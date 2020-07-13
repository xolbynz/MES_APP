package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.WorkListVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class WorkListAdapter extends BaseAdapter
{
Context context;
WorkListVo workListVo= new WorkListVo();
ArrayList<WorkListVo> arrayList = new ArrayList<>();

    @Override
    public int getCount() {
        return arrayList.size();
    }
    public void addItem(WorkListVo workListVo) {
        arrayList.add(workListVo);

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

        context= parent.getContext();
        workListVo = arrayList.get(position);

        WorkListAdapter.ListViewHolder  holder = null;

        final int adptPosition = position;

        final TextView custNm;
        final TextView lotNo;
        final TextView itemNm;
        final TextView instAmt;
        final TextView spec;
        final TextView completeYN;

        if(convertView ==null){
            LayoutInflater inflater =(LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
            convertView = inflater.inflate(R.layout.adapter_work_list, parent, false);

            custNm=convertView.findViewById(R.id.worklist_tv_custNm);
            lotNo=convertView.findViewById(R.id.worklist_tv_lotNo);
            itemNm=convertView.findViewById(R.id.worklist_tv_itemNm);
            instAmt=convertView.findViewById(R.id.worklist_tv_instAmt);
            spec=convertView.findViewById(R.id.worklist_tv_spec);
            completeYN=convertView.findViewById(R.id.worklist_tv_completeYN);

            holder = new WorkListAdapter.ListViewHolder();

            holder.completeYN=completeYN;
            holder.custNm=custNm;
            holder.instAmt=instAmt;
            holder.itemNm=itemNm;
            holder.lotNo= lotNo;
            holder.spec=spec;

            convertView.setTag(holder);

        }
        else {

            holder= (WorkListAdapter.ListViewHolder) convertView.getTag();
            completeYN=holder.completeYN;
           custNm=holder.custNm;
            instAmt=holder.instAmt;
            itemNm=holder.itemNm;
            lotNo= holder.lotNo;
           spec=holder.spec;

        }

        completeYN.setText(workListVo.getCompleteYN());
        custNm.setText(workListVo.getCustNm());
        instAmt.setText(workListVo.getInstAmt());
        itemNm.setText(workListVo.getItemNm());
        lotNo.setText(workListVo.getLotNo());
        spec.setText(workListVo.getSpec());

        return convertView;
    }


    private class ListViewHolder {

        TextView custNm;
        TextView lotNo;
        TextView itemNm;
        TextView instAmt;
        TextView spec;
        TextView completeYN;
    }
}
