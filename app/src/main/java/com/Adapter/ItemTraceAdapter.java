package com.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.TraceListDetailVo;
import com.example.mes_app.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemTraceAdapter extends BaseAdapter {

    Context context;
    TraceListDetailVo traceListDetailVo;


    public ArrayList<TraceListDetailVo> arrayList = new ArrayList<>();

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

//        traceListDetailVo = new TraceListDetailVo();
        traceListDetailVo = arrayList.get(position);

        ItemTraceAdapter.ListViewHolder holder = null;

        final TextView gubun;
        final TextView intime;
        final TextView cust_nm;
        final TextView raw_mat_nm;
        final TextView spec;
        final TextView unit_nm;
        final TextView total_amt;
        final TextView poor;


        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.adapter_item_tracking, parent, false);

            gubun = convertView.findViewById(R.id.itemTracking_tv_list);
            intime = convertView.findViewById(R.id.itemTracking_tv_date);
            raw_mat_nm = convertView.findViewById(R.id.itemTracking_tv_detail);
            spec = convertView.findViewById(R.id.itemTracking_tv_spec);
            total_amt = convertView.findViewById(R.id.itemTracking_tv_amt);
            poor = convertView.findViewById(R.id.itemTracking_tv_fault);

            holder = new ListViewHolder();

            holder.gubun = gubun;
            holder.intime = intime;
//            holder.cust_nm = cust_nm;
            holder.raw_mat_nm = raw_mat_nm;
            holder.spec = spec;
            holder.total_amt = total_amt;
            holder.poor = poor;

            convertView.setTag(holder);

        } else {

            holder = (ItemTraceAdapter.ListViewHolder) convertView.getTag();

            gubun = holder.gubun;
            intime = holder.intime;
//            cust_nm = holder.cust_nm;
            raw_mat_nm = holder.raw_mat_nm;
            spec = holder.spec;
            total_amt = holder.total_amt;
            poor = holder.poor;
        }



        gubun.setText(traceListDetailVo.getGubun()); //
        if(traceListDetailVo.getIntime().equals("")){
            intime.setText("==================");
        }else {
            intime.setText(traceListDetailVo.getIntime());
        }
        spec.setText(traceListDetailVo.getSpec());
        if(traceListDetailVo.getCust_nm().contains("합계")) {
            raw_mat_nm.setText(traceListDetailVo.getCust_nm());
        }else {
            raw_mat_nm.setText(traceListDetailVo.getRaw_mat_nm());
        }
//        unit_nm.setText(traceListDetailVo.getUnit_nm());



        DecimalFormat df = new DecimalFormat("#.#");

        if (traceListDetailVo.getTotal_amt().contains("/"))
        {
            String[] sTemp = traceListDetailVo.getTotal_amt().split("/");
            String sTemp2 = df.format(Double.parseDouble(sTemp[0]));
            sTemp2 += " / " + df.format(Double.parseDouble(sTemp[1]));
            total_amt.setText(sTemp2);
        }
        else  if (traceListDetailVo.getTotal_amt().contains("x"))
        {
            String[] sTemp = traceListDetailVo.getTotal_amt().split("x");
            String sTemp2 = df.format(Double.parseDouble(sTemp[0]));
            String[] sTemp3 = sTemp[1].split("=");
            sTemp2 += " x " + df.format(Double.parseDouble(sTemp3[0]));
            sTemp2 += " = " + df.format(Double.parseDouble(sTemp3[1]));
            total_amt.setText(sTemp2);
        }
        else
        {
            total_amt.setText(df.format(Double.parseDouble(traceListDetailVo.getTotal_amt())));
        }
        if (traceListDetailVo.getPoor() != null && !traceListDetailVo.getPoor().equals(""))
        {
            poor.setText(df.format(Double.parseDouble(traceListDetailVo.getPoor())));
        }
        else
        {
            poor.setText("0");
        }

        if(traceListDetailVo.getIntime().equals("") && traceListDetailVo.getInput_date().equals("") && traceListDetailVo.getInput_cd().equals("")){
            gubun.setBackgroundColor(Color.LTGRAY);
            intime.setBackgroundColor(Color.LTGRAY);
            raw_mat_nm.setBackgroundColor(Color.LTGRAY);
            spec.setBackgroundColor(Color.LTGRAY);
            total_amt.setBackgroundColor(Color.LTGRAY);
            poor.setBackgroundColor(Color.LTGRAY);
        }else{
            gubun.setBackgroundColor(Color.DKGRAY);
            intime.setBackgroundColor(Color.DKGRAY);
            raw_mat_nm.setBackgroundColor(Color.DKGRAY);
            spec.setBackgroundColor(Color.DKGRAY);
            total_amt.setBackgroundColor(Color.DKGRAY);
            poor.setBackgroundColor(Color.DKGRAY);
        }

        return convertView;
    }

    private class ListViewHolder {
        TextView gubun;
        TextView intime;
        TextView raw_mat_nm;
        TextView spec;
        TextView total_amt;
        TextView poor;
    }

//    public static String fmt(double d) {
//        if (d == (long) d)
//            return String.format("%d", (long) d);
//        else
//            return String.format("%s", d);
//    }
}
