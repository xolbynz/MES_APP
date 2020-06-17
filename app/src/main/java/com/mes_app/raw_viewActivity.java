package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.common.CompInfo;
import com.common.DBInfo;
import com.example.mes_app.R;
import com.example.mes_app.data.Result;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class raw_viewActivity extends Fragment  {

    MainActivity activity;
    Button btn_search;
    Spinner spinner_search;
    ViewGroup rootView;

    CompInfo compInfo;
    DBInfo dbInfo;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    TableLayout tableLayout;
    EditText eidtSearch;
    InputMethodManager imm;
    ResultSet rs = null;
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
        eidtSearch = rootView.findViewById(R.id.edit_search);


        return rootView;
    }

    public ResultSet Raw_Detail(Connection connection, ResultSet rs){

        return rs;
    }


    View.OnClickListener Raw_Search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {
                rs = Raw_Detail(rs,dbInfo.mainConn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    public ResultSet Raw_Detail(ResultSet resultSet, Connection connection) throws SQLException {

        StringBuilder query = new StringBuilder();

        query.append("SELECT *");
        query.append("FROM [SM_FACTORY_COM].[dbo].[F_RAW_DETAIL]  ");

        resultSet = dbInfo.SelectDB(query.toString());

        return resultSet;
    }


}
