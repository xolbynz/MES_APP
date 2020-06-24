package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.VO.StockInputVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class stock_statusAdapter extends BaseAdapter {

    Context context;
    StockInputVo stockInputVo;
    private ArrayList<StockInputVo> arrayList;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        context = parent.getContext();
        stockInputVo = arrayList.get(position);

        if(convertView == null){
            LayoutInflater infaInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infaInflater.inflate(R.layout.activity_raw_view, parent, false);
        }
        return null;
    }
}
