package com.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.VO.StorageVo;
import com.VO.WorkInstVo;
import com.example.mes_app.R;

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

        context = parent.getContext();
        storageVo = arrayList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_storage, parent, false);
        }

        if(storageVo != null){

            String storage_nm = storageVo.getStorage_nm();
            String storage_cd = storageVo.getStorage_cd();

            ((TextView)convertView.findViewById(R.id.Storage_nm)).setText(storage_nm);
            ((TextView)convertView.findViewById(R.id.Storage_nm)).setTag(storage_cd);
        }

        return convertView;
    }

}
