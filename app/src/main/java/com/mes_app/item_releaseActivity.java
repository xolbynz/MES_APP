package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mes_app.R;

public class item_releaseActivity extends Fragment {

    ViewGroup rootView;
    public item_releaseActivity(){

    }
    public  item_releaseActivity(Context context)
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_item_release, container, false);
        return   rootView;
    }

}
