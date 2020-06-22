package com.mes_app;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class raw_viewActivity extends Fragment {

    MainActivity activity;
    Button btn_search;
    Spinner spinner_search;
    ViewGroup rootView;

    CompInfo compInfo;
    DBInfo dbInfo;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    TableLayout tableLayout;
    EditText editSearch;
    InputMethodManager imm;
    JSONArray JArray;
    JSONObject jsonObject;

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
                JArray = Raw_Detail(JArray, editSearch.toString());

                if (JArray != null) {

                } else {
                    Toast.makeText(activity, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException | JSONException e) {
                e.printStackTrace();
            }

        }
    };

    EditText.OnFocusChangeListener OutFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    };


    public JSONArray Raw_Detail( JSONArray JSONArray, String condition) throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();

        query.append("SELECT * ");
        query.append("FROM [" + dbInfo.Location + "].[dbo].[N_RAW_CODE]  ");
        query.append("WHERE 1=1");
        if (!condition.equals("")) {
            if (spinner_search.toString().equals("원자재"))
                if (condition.toString().equals(""))
                    query.append("WHERE RAW_MAT_NM LIKE %'" + condition + "'%");
                else if (spinner_search.toString().equals("거래처"))
                    query.append("WHERE CUST_NM  = '" + getCustcd(condition) + "'");
        }
        JSONArray = dbInfo.SelectDB(query.toString());
        return JSONArray;
    }

    public ArrayList<String> convertResultSetToArrayList(ResultSet rs) throws SQLException { // ResultSet 해쉬맵 변환

        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList<String> list = new ArrayList<>(columns);

        while (rs.next()) {
            int i = 1;
            while(i <= columns) {
                list.add(rs.getString(i++));
            }
        }
        return list;

    }

    public String getCustcd(String Condition) throws SQLException, JSONException {

        JSONArray jsonArray;
        StringBuilder query = new StringBuilder();

        query.append("SELECT CUST_CD ");
        query.append("FROM [" + dbInfo.Location + "].[dbo].[N_CUST_CODE]  ");
        query.append("WHERE 1=1");
        query.append("WHERE CUST_NM LIKE %" + Condition + "% ");

        jsonArray = dbInfo.SelectDB(query.toString());

        return jsonArray.getString(1);
    }


}
