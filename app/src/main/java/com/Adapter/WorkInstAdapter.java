package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.VO.WorkInstVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class WorkInstAdapter  extends BaseAdapter {

    Context context;
    WorkInstVo workInstVo= new WorkInstVo();
    int position;
    TextView lotNo;
    TextView custNM;
    TextView itemNm;
    TextView instAmt;
    Button btn_view;

    private ArrayList<WorkInstVo> arrayList = new ArrayList<>();

    public  void addItem(WorkInstVo workInstVo){
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

        context =parent.getContext();
        workInstVo =arrayList.get(position);
        this.position=position;

        if (convertView==null){
            LayoutInflater infaInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=infaInflater.inflate(R.layout.adapter_work_inst,parent,false);
           lotNo= convertView.findViewById(R.id.workInst_tv_lotNo);
             custNM= convertView.findViewById(R.id.workInst_tv_custNm);
             itemNm= convertView.findViewById(R.id.workInst_tv_itemNm);
             instAmt= convertView.findViewById(R.id.workInst_tv_instAmt);
             btn_view= convertView.findViewById(R.id.workInst_btn_view);


            lotNo.setText(workInstVo.getLotNO());
            custNM.setText(workInstVo.getCustNm());
            itemNm.setText(workInstVo.getItemNm());
            instAmt.setText(workInstVo.getInstAmt());
            btn_view.setText("보기");


            lotNo.setOnClickListener(lotNoclick);
            custNM.setOnClickListener(custNMclick);


        }
        return convertView;
    }

View.OnClickListener lotNoclick = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
 System.out.println(lotNo.getText());
    }
};
    View.OnClickListener custNMclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println(custNM.getText());
        }
    };

}
