package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.VO.LocVo;
import com.example.mes_app.R;

import java.util.ArrayList;

class LocAdapter extends BaseAdapter {

    Context context;
    LocVo locVo = new LocVo();

    public ArrayList<LocVo> arrayList = new ArrayList<>();

    public void addItem(LocVo locVo) {
        arrayList.add(locVo);
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
        locVo = arrayList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_loc, parent, false);
        }

        if (locVo != null) {

            String loc_nm = locVo.getLoc_nm();
            String loc_cd = locVo.getLoc_cd();

            ((TextView) convertView.findViewById(R.id.Loc_nm)).setText(loc_nm);
            ((TextView) convertView.findViewById(R.id.Loc_nm)).setTag(loc_cd);
        }

        return convertView;
    }
}
