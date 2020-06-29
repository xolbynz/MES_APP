package com.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.VO.WorkInstVo;

import java.util.ArrayList;

public class WorkInstAdapter  extends BaseAdapter {

    Context context;
    WorkInstVo workInstVo= new WorkInstVo();

    private ArrayList<WorkInstVo> arrayList = new ArrayList<>();

    public  void addItem(WorkInstVo workInstVo){
        arrayList.add(workInstVo);
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

        context =parent.getContext();
        workInstVo =arrayList.get(position);
        return null;
    }
}
