package com.mes_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mes_app.R;

public class monitoringActivity  extends Fragment {


    public monitoringActivity(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return  inflater.inflate(R.layout.activity_monitoring, container, false);
    }
}
