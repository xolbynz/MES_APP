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

    public void addItem(ItemStatusVo itemStatusVo) {
        arrayList.add(itemStatusVo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        context = parent.getContext(); // activity 정보를 읽어오기
        itemStatusVo = arrayList.get(position);
        ItemStatusAdapter.ListViewHolder holder = null;

        final TextView itemNm;
        final TextView spec;
        final TextView positive;
        final TextView faulty;
        final TextView amt;
        final TextView unit;

        if (convertView == null) {
            LayoutInflater Inflater = (LayoutInflater) context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = Inflater.inflate(R.layout.adapter_item_status, parent, false);

            itemNm = convertView.findViewById(R.id.itemStatus_tv_itemNm);
            spec = convertView.findViewById(R.id.itemStatus_tv_spec);
            positive = convertView.findViewById(R.id.itemStatus_tv_positive);
            faulty = convertView.findViewById(R.id.itemStatus_tv_faulty);
            amt = convertView.findViewById(R.id.itemStatus_tv_amt);
            unit = convertView.findViewById(R.id.itemStatus_tv_unit);

            holder = new ItemStatusAdapter.ListViewHolder();

            holder.itemNm = itemNm;
            holder.spec = spec;
            holder.positive = positive;
            holder.faulty = faulty;
            holder.amt = amt;
            holder.unit = unit;

            convertView.setTag(holder);
        } else {
            holder = (ItemStatusAdapter.ListViewHolder) convertView.getTag();

            itemNm = holder.itemNm;
            spec = holder.spec;
            positive = holder.positive;
            faulty = holder.faulty;
            amt = holder.amt;
            unit = holder.unit;
        }

        itemNm.setText(itemStatusVo.getItemNm());
        spec.setText(itemStatusVo.getSpec());
        positive.setText(itemStatusVo.getPstvAmt());
        faulty.setText(itemStatusVo.getFaultyAmt());
        amt.setText(itemStatusVo.getAmt());
        unit.setText(itemStatusVo.getUnitNm());

        return convertView;
    }

    private class ListViewHolder {

        TextView itemNm;
        TextView spec;
        TextView positive;
        TextView faulty;
        TextView amt;
        TextView unit;
    }
}
