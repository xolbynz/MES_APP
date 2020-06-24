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
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Adapter.RawAdapter;
import com.VO.RawVo;
import com.common.CompInfo;
import com.common.DBInfo;
import com.example.mes_app.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class raw_viewActivity extends Fragment {

    MainActivity activity;
    Button btn_search;
    Spinner spinner_search;
    ViewGroup rootView;

    DBInfo dbInfo;
    GridView gridView;
    EditText editSearch;
    JSONArray JArray;
    RawVo rawVo;
    RawAdapter rawAdapter;
    ArrayList<RawVo> rawVoArrayList;

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
        editSearch = rootView.findViewById(R.id.edit_search);
        gridView = rootView.findViewById(R.id.raw_GridView);

        editSearch.setOnFocusChangeListener(OutFocus);
        btn_search.setOnClickListener(Raw_Search);


        dbInfo = new DBInfo();
        rawVoArrayList = new ArrayList<>();

        return rootView;


    }

    View.OnClickListener Raw_Search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {

                JArray = Raw_Detail(JArray, editSearch.getText().toString());

                if (JArray != null) {

                    rawVoArrayList.clear();

                    for (int i = 0; i < JArray.length(); i++) {

                        JSONObject jo = JArray.getJSONObject(i);

                        String raw_mat_cd = "";
                        String raw_mat_nm = "";
                        String spec = "";
                        String raw_mat_gubun = "";
                        String type_cd = "";
                        String input_unit = "";
                        String output_unit = "";
                        String input_price = "";
                        String output_price = "";
                        String st_status_yn = "";
                        String raw_strage = "";
                        String used_cd = "";
                        String basic_stock = "";
                        String bal_stock = "";
                        String check_gubun = "";
                        String prop_stock = "";

                        if (jo.has("RAW_MAT_CD"))
                            raw_mat_cd = jo.getString("RAW_MAT_CD");
                        if (jo.has("RAW_MAT_NM"))
                            raw_mat_nm = jo.getString("RAW_MAT_NM");
                        if (jo.has("SPEC"))
                            spec = jo.getString("SPEC");
                        if (jo.has("RAW_MAT_GUBUN"))
                            raw_mat_gubun = jo.getString("RAW_MAT_GUBUN");
                        if (jo.has("TYPE_CD"))
                            type_cd = jo.getString("TYPE_CD");
                        if (jo.has("INPUT_UNIT"))
                            input_unit = jo.getString("INPUT_UNIT");
                        if (jo.has("OUTPUT_UNIT"))
                            output_unit = jo.getString("OUTPUT_UNIT");
                        if (jo.has("INPUT_PRICE"))
                            input_price = jo.getString("INPUT_PRICE");
                        if (jo.has("OUTPUT_PRICE"))
                            output_price = jo.getString("OUTPUT_PRICE");
                        if (jo.has("ST_STATUS_YN"))
                            st_status_yn = jo.getString("ST_STATUS_YN");
                        if (jo.has("RAW_STORAGE"))
                            raw_strage = jo.getString("RAW_STORAGE");
                        if (jo.has("USED_CD"))
                            used_cd = jo.getString("USED_CD");
                        if (jo.has("BASIC_STOCK"))
                            basic_stock = jo.getString("BASIC_STOCK");
                        if (jo.has("BAL_STOCK"))
                            bal_stock = jo.getString("BAL_STOCK");
                        if (jo.has("CHECK_GUBUN"))
                            check_gubun = jo.getString("CHECK_GUBUN");
                        if (jo.has("PROP_STOCK"))
                            prop_stock = jo.getString("PROP_STOCK");

                        rawVo = new RawVo(raw_mat_cd, raw_mat_nm, spec, raw_mat_gubun, type_cd,
                                input_unit, output_unit, input_price, output_price, st_status_yn,
                                raw_strage, used_cd, basic_stock, bal_stock, check_gubun, prop_stock);

                        rawAdapter = new RawAdapter();
                        rawAdapter.addItem(rawVo);
                        rawVoArrayList.add(rawVo);

                    }
                    gridView.setAdapter(rawAdapter);

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


    public JSONArray Raw_Detail(JSONArray JSONArray, String condition) throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();

        query.append("SELECT * ");
        query.append("FROM [" + dbInfo.Location + "].[dbo].[N_RAW_CODE]  ");
        query.append("WHERE 1=1");
        if (!condition.equals("")) {
            if (spinner_search.getSelectedItem().toString().equals("원자재"))
                if (!condition.equals(""))
                    query.append(" AND RAW_MAT_NM LIKE '%" + condition + "%'");
                else
                    query.append("");
            else if (spinner_search.getSelectedItem().toString().equals("거래처"))
                query.append("AND CUST_NM  = '" + getCustcd(condition) + "'");
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
            while (i <= columns) {
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
