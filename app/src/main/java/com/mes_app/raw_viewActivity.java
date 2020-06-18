package com.mes_app;

import android.app.Activity;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.common.CompInfo;
import com.common.DBInfo;
import com.example.mes_app.R;
import com.example.mes_app.data.Result;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class raw_viewActivity extends Fragment  {

    MainActivity activity;
    Button btn_search;
    Spinner spinner_search;
    ViewGroup rootView;

    CompInfo compInfo;
    DBInfo dbInfo;
    ArrayList<HashMap<String ,Object>> list;
    ArrayAdapter<String> adapter;
    TableLayout tableLayout;
    EditText editSearch;
    InputMethodManager imm;
    ResultSet rs;
    public raw_viewActivity() {
        dbInfo = new DBInfo();
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
        editSearch = rootView.findViewById(R.id.edit_search);


        editSearch.setOnFocusChangeListener(OutFocus);
        btn_search.setOnClickListener(Raw_Search);

        dbInfo = new DBInfo();

        return rootView;
    }


    View.OnClickListener Raw_Search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            try {
                rs = Raw_Detail(rs,dbInfo.mainConn);

                if(rs != null || rs.getRow() != 0){
                    list = convertResultSetToArrayList(rs);

//                    for(int i=0; i < list.size(); i++)
//                    Toast.makeText(rootView,list.getClass(),Toast.LENGTH_LONG).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    EditText.OnFocusChangeListener OutFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus){
                InputMethodManager inputMethodManager =(InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    };

    public ResultSet Raw_Detail(ResultSet resultSet, Connection connection) throws SQLException {

        StringBuilder query = new StringBuilder();


        query.append("SELECT * ");
        query.append("FROM ["+ dbInfo.Location +"].[dbo].[F_RAW_DETAIL]  ");

        resultSet = dbInfo.SelectDB(query.toString());


        return resultSet;
    }

    public ArrayList<HashMap<String,Object>> convertResultSetToArrayList(ResultSet rs) throws SQLException { // ResultSet 해쉬맵 변환

        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        while(rs.next()) {
            HashMap<String,Object> row = new HashMap<String, Object>(columns);
            for(int i=1; i<=columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }
        return list;
    }


}
