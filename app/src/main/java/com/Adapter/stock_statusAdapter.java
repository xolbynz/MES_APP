package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.VO.StockInputVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class stock_statusAdapter extends BaseAdapter {

    Context context;
    StockInputVo stockInputVo;
    private ArrayList<StockInputVo> arrayList = new ArrayList<>();

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

        stock_statusAdapter.ListViewHolder holder = null;

        final TextView pack_date;
        final TextView lot_no;
        final TextView plan;
        final TextView custNm;
        final TextView itemNm;
        final TextView spec;
        final TextView unit;
        final TextView amt;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_stock_status, parent, false);

            pack_date = convertView.findViewById(R.id.stockStatus_tv_pacDate);
            lot_no = convertView.findViewById(R.id.stockStatus_tv_lotNo);
            plan = convertView.findViewById(R.id.stockStatus_tv_plan);
            custNm = convertView.findViewById(R.id.stockStatus_tv_custNm);
            itemNm = convertView.findViewById(R.id.stockStatus_tv_itemNm);
            spec = convertView.findViewById(R.id.stockStatus_tv_spec);
            unit = convertView.findViewById(R.id.stockStatus_tv_unit);
            amt = convertView.findViewById(R.id.stockStatus_tv_amt);

            holder = new stock_statusAdapter.ListViewHolder();

            holder.pack_date = pack_date;
            holder.lot_no = lot_no;
            holder.plan = plan;
            holder.custNm = custNm;
            holder.itemNm = itemNm;
            holder.spec = spec;
            holder.unit = unit;
            holder.amt = amt;

            convertView.setTag(holder);
        } else {

            holder = (stock_statusAdapter.ListViewHolder) convertView.getTag();

            pack_date = holder.pack_date;
            lot_no = holder.lot_no;
            plan = holder.plan;
            custNm = holder.custNm;
            itemNm = holder.itemNm;
            spec = holder.spec;
            unit = holder.unit;
            amt = holder.amt;

        }

        pack_date.setText(stockInputVo.getPacking_date());
        lot_no.setText(stockInputVo.getLot_no());
        plan.setText(stockInputVo.getPlan_no());
        custNm.setText(stockInputVo.getCust_nm());
        itemNm.setText(stockInputVo.getItem_nm());
        spec.setText(stockInputVo.getItem_std());
        unit.setText(stockInputVo.getItem_unit());
        amt.setText(stockInputVo.getQuantity());


        return convertView;
    }

    private class ListViewHolder {
        TextView pack_date;
        TextView lot_no;
        TextView plan;
        TextView custNm;
        TextView itemNm;
        TextView spec;
        TextView unit;
        TextView amt;
    }
}
