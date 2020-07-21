package com.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.VO.StorageVo;
import com.VO.WorkInstVo;

import java.util.ArrayList;

public class StorageAdapter extends BaseAdapter {

    Context context;
    StorageVo storageVo = new StorageVo();

    public ArrayList<StorageVo> arrayList = new ArrayList<>();


    public void addItem(StorageVo storageVo) {
        arrayList.add(storageVo);
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
        return null;
    }
}
