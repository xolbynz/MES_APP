package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.VO.RawVo;
import com.common.DBInfo;
import com.example.mes_app.R;

import org.json.JSONArray;

import java.util.ArrayList;

public class work_progressActivity extends Fragment {

    ViewGroup rootView;
    MainActivity activity;
    DBInfo dbInfo;
    GridView RecordWork;
    GridView Recordraw1;
    GridView Recordraw2;
    Context context;
    Button btn_search;
    Spinner spinner_search;

    GridView gridView;
    EditText editSearch;
    JSONArray JArray;
    RawVo rawVo;
    ArrayList<RawVo> rawVoArrayList;




    public work_progressActivity(Context context) {
        dbInfo = new DBInfo();
        this.context=context;
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.activity_work_progress, container, false);


        btn_search= rootView.findViewById(R.id.workPrg_btn_view);

        btn_search.setOnClickListener(inst_Search);
        return rootView;
    }

    View.OnClickListener inst_Search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                pop_workList oDialog = new pop_workList(context);
                oDialog.setCancelable(false);
                oDialog.show();
            }
            catch (Exception ex){
                System.out.println(ex.toString());
            }


        }
    };





}