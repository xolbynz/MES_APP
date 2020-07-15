package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.RawVo;
import com.VO.TraceListDetailVo;
import com.example.mes_app.R;

import org.w3c.dom.Text;

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

        final TextView gubun;
        final TextView order1;
        final TextView order2;
        final TextView input_date;
        final TextView input_cd;
        final TextView intime;
        final TextView cust_nm;
        final TextView raw_mat_nm;
        final TextView sepc;
        final TextView lot_no;
        final TextView lot_sub;
        final TextView unit_nm;
        final TextView loss_amt;
        final TextView poor;



        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        return convertView;
    }
}
