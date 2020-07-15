package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.TraceListDetailVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class ItemTraceAdapter extends BaseAdapter {

    Context context;
    TraceListDetailVo traceListDetailVo;


    private ArrayList<TraceListDetailVo> arrayList = new ArrayList<>();

    public void addItem(TraceListDetailVo traceListDetailVo) {
        arrayList.add(traceListDetailVo);
    }


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
        traceListDetailVo = new TraceListDetailVo();
        traceListDetailVo = arrayList.get(position);

        ItemTraceAdapter.ListViewHolder holder = null;

        final TextView gubun;
        final TextView intime;
        final TextView cust_nm;
        final TextView raw_mat_nm;
        final TextView spec;
        final TextView lot_no;
        final TextView total_amt;
        final TextView poor;


        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_item_tracking, parent, false);


            gubun = convertView.findViewById(R.id.itemTracking_tv_list);
            intime = convertView.findViewById(R.id.itemTracking_tv_date);
            cust_nm = convertView.findViewById(R.id.itemStatus_tv_faulty);
            raw_mat_nm = convertView.findViewById(R.id.itemTracking_tv_detail);
            spec = convertView.findViewById(R.id.itemTracking_tv_spec);
            lot_no = convertView.findViewById(R.id.itemTracking_tv_lotNo);
            total_amt = convertView.findViewById(R.id.itemTracking_tv_amt);
            poor = convertView.findViewById(R.id.itemTracking_tv_fault);

            holder = new ListViewHolder();

            holder.gubun = gubun;
            holder.intime = intime;
            holder.cust_nm = cust_nm;
            holder.raw_mat_nm = raw_mat_nm;
            holder.spec = spec;
            holder.lot_no = lot_no;
            holder.total_amt = total_amt;
            holder.poor = poor;

            convertView.setTag(holder);

        } else {

            holder = (ItemTraceAdapter.ListViewHolder) convertView.getTag();

            gubun = holder.gubun;
            intime = holder.intime;
            cust_nm = holder.cust_nm;
            raw_mat_nm = holder.raw_mat_nm;
            spec = holder.spec;
            lot_no = holder.lot_no;
            total_amt = holder.total_amt;
            poor = holder.poor;
        }

        gubun.setText(traceListDetailVo.getGubun()); //
        intime.setText(traceListDetailVo.getIntime());
        cust_nm.setText(traceListDetailVo.getCust_nm());
        raw_mat_nm.setText(traceListDetailVo.getRaw_mat_nm());
        spec.setText(traceListDetailVo.getSpec());
        lot_no.setText(traceListDetailVo.getLot_no());
        total_amt.setText(traceListDetailVo.getLot_sub());
        poor.setText(traceListDetailVo.getPoor());

        return convertView;
    }

    private class ListViewHolder {
        TextView gubun;
        TextView intime;
        TextView cust_nm;
        TextView raw_mat_nm;
        TextView spec;
        TextView lot_no;
        TextView total_amt;
        TextView poor;
    }
}
