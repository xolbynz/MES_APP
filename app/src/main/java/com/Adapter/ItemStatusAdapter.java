package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.ItemStatusVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class ItemStatusAdapter extends BaseAdapter {

    Context context;
    ItemStatusVo itemStatusVo = new ItemStatusVo();
    ArrayList<ItemStatusVo> arrayList = new ArrayList<>();

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

    public  void addItem(ItemStatusVo itemStatusVo){
        arrayList.add(itemStatusVo);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        context = parent.getContext(); // activity 정보를 읽어오기

        itemStatusVo = arrayList.get(position);

        if (convertView==null){
            LayoutInflater infaInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infaInflater.inflate(R.layout.adapter_item_status, parent, false);

            TextView itemNm= convertView.findViewById(R.id.itemStatus_tv_itemNm);
            TextView spec= convertView.findViewById(R.id.itemStatus_tv_spec);
            TextView positive= convertView.findViewById(R.id.itemStatus_tv_positive);
            TextView faulty= convertView.findViewById(R.id.itemStatus_tv_faulty);
            TextView amt= convertView.findViewById(R.id.itemStatus_tv_amt);
            TextView unit = convertView.findViewById(R.id.itemStatus_tv_unit);


            itemNm.setText(itemStatusVo.getItemNm());
            spec.setText(itemStatusVo.getSpec());
            positive.setText(itemStatusVo.getPstvAmt());
            faulty.setText(itemStatusVo.getFaultyAmt());
            amt.setText(itemStatusVo.getAmt());
            unit.setText(itemStatusVo.getUnitNm());
        }
        return convertView;
    }
}
