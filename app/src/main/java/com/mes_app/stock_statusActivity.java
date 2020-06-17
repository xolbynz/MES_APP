package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

import com.example.mes_app.R;

public class stock_statusActivity  extends Fragment {

    InputMethodManager imm; //키보드 내리기
    public  stock_statusActivity() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        imm =(InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);






        return inflater.inflate(R.layout.activity_stock_status, container, false);
    }
}