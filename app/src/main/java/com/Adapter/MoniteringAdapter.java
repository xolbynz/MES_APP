package com.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.VO.MoniteringVo;

import java.util.ArrayList;

public class MoniteringAdapter extends BaseAdapter {

    MoniteringVo moniteringVo;
    ArrayList<MoniteringVo> arrayList;


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

    public void addItem(MoniteringVo moniteringVo) {
        arrayList.add(moniteringVo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
