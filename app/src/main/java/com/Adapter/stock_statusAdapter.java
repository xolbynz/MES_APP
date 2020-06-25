package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.StockInputVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class stock_statusAdapter extends BaseAdapter {

    Context context;
    StockInputVo stockInputVo;
    private ArrayList<StockInputVo> arrayList =new ArrayList<>();

    public void addItem(StockInputVo stockInputVo) {
        arrayList.add(stockInputVo);
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
        stockInputVo = arrayList.get(position);

        if(convertView == null){
            LayoutInflater infaInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infaInflater.inflate(R.layout.activity_stock_status, parent, false);

            TextView pack_date= convertView.findViewById(R.id.stockStatus_tv_pacDate);
            TextView lot_no= convertView.findViewById(R.id.stockStatus_tv_lotNo);
            TextView plan= convertView.findViewById(R.id.stockStatus_tv_plan);
            TextView custNm= convertView.findViewById(R.id.stockStatus_tv_custNm);
            TextView itemNm= convertView.findViewById(R.id.stockStatus_tv_itemNm);
            TextView spec= convertView.findViewById(R.id.stockStatus_tv_spec);
            TextView unit= convertView.findViewById(R.id.stockStatus_tv_unit);
            TextView amt= convertView.findViewById(R.id.stockStatus_tv_amt);


            pack_date.setText(stockInputVo.getPacking_date());
            lot_no.setText(stockInputVo.getLot_no());
            plan.setText(stockInputVo.getPlan_no());
            custNm.setText(stockInputVo.getCust_nm());
            itemNm.setText(stockInputVo.getItem_nm());
            spec.setText(stockInputVo.getItem_std());
            unit.setText(stockInputVo.getItem_unit());
            amt.setText(stockInputVo.getQuantity());


        }
        return convertView;
    }
}
