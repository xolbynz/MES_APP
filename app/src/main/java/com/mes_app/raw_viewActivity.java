package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.common.CompInfo;
import com.example.mes_app.R;

import java.util.ArrayList;

public class raw_viewActivity extends Fragment {

    MainActivity activity;
    Button btn_search;
    Spinner spinner_search;
    ViewGroup rootView;

    CompInfo compInfo;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    TableLayout tableLayout;

    InputMethodManager imm;
    public raw_viewActivity() {

        list = new ArrayList<String>();



    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }


    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.activity_raw_view, container, false);
        btn_search = rootView.findViewById(R.id.btn_search);
        spinner_search = rootView.findViewById(R.id.spinner_raw_search);
        tableLayout = rootView.findViewById(R.id.tableLayout);
        return rootView;


    }
    class Querythread extends Thread {
        public void run() {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            Log.i("Android", " MSSQL 접속 준비");

            list.clear();

//            Query(); //쿼리함수 호출

        }
    }

}
