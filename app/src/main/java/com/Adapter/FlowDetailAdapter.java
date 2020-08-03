package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.FlowDetailVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class FlowDetailAdapter extends BaseAdapter {

    Context context;
    FlowDetailVo flowDetailVo = new FlowDetailVo();
    public ArrayList<FlowDetailVo> flowDetailVoArrayList = new ArrayList<>();

    public void additem(FlowDetailVo flowDetailVo){
        flowDetailVoArrayList.add(flowDetailVo);
    }

    @Override
    public int getCount() {
        return flowDetailVoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return flowDetailVoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        context = parent.getContext(); // activity 정보를 읽어오기
        flowDetailVo = (FlowDetailVo) getItem(position);
        FlowDetailAdapter.ListViewHolder holder = null;

        final TextView Seq;
        final TextView Flow_date;
        final TextView F_sub_amt;
        final TextView Poor_amt;
        final TextView Non_amt;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_flow_detail,parent,false);

            Seq = convertView.findViewById(R.id.flowDetail_tv_Seq);
            Flow_date = convertView.findViewById(R.id.flowDetail_tv_Date);
            F_sub_amt = convertView.findViewById(R.id.flowDetail_tv__F_Sub_amt);
            Poor_amt = convertView.findViewById(R.id.flowDetail_tv_poor_amt);
            Non_amt = convertView.findViewById(R.id.flowDetail_tv_non_amt);

            holder = new ListViewHolder();

            holder.Seq = Seq;
            holder.Flow_date = Flow_date;
            holder.F_sub_amt = F_sub_amt;
            holder.Poor_amt = Poor_amt;
            holder.Non_amt = Non_amt;

            convertView.setTag(holder);
        }else{

            holder = (FlowDetailAdapter.ListViewHolder) convertView.getTag();

            Seq = holder.Seq;
            Flow_date = holder.Flow_date;
            F_sub_amt = holder.F_sub_amt;
            Poor_amt = holder.Poor_amt;
            Non_amt = holder.Non_amt;
        }

        if(flowDetailVo.getSEQ().equals("99999")){
            Seq.setText("계");
        }else
            Seq.setText(flowDetailVo.getSEQ());
        Flow_date.setText(flowDetailVo.getF_SUB_DATE());
        F_sub_amt.setText(flowDetailVo.getF_SUB_AMT());
        Poor_amt.setText(flowDetailVo.getPOOR_AMT());
        Non_amt.setText(flowDetailVo.getNON_INPUT_AMT());

        return convertView;
    }

    private class ListViewHolder{
        TextView Seq;
        TextView Flow_date;
        TextView F_sub_amt;
        TextView Poor_amt;
        TextView Non_amt;
    }
}
