package com.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.VO.RawVo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class RawAdapter extends BaseAdapter {

    Context context;
    RawVo rawVo;
    private ArrayList<RawVo> arrayList;

    public void addItem(RawVo rawVo){
        arrayList.add(rawVo);
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        context = parent.getContext();
        rawVo = arrayList.get(position);

        if(convertView == null){
            LayoutInflater infaInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infaInflater.inflate(R.layout.activity_raw_view, parent, false);
        }
        return null;
    }

}
