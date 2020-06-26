package com.mes_app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Adapter.RawAdapter;
import com.VO.RawVo;
import com.common.DBInfo;
import com.example.mes_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.jar.JarEntry;

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

                JArray = null;
                JArray = Raw_Detail(JArray, editSearch.getText().toString());


                if (JArray.length() != 0) {

                    rawVoArrayList.clear();
                    RawAdapter rawAdapter = new RawAdapter();
                    for (int i = 0; i < JArray.length(); i++) {

                        JSONObject jo = JArray.getJSONObject(i);

                        String raw_mat_cd = "";
                        String raw_mat_nm = "";
                        String spec = "";
                        String unit_nm = "";
                        String cust_nm = "";
                        String input_amt = "";
                        String output_amt = "";
                        String curr_amt = "";
                        String loc = "";
                        String basic_stock = "";
                        String bal_stock = "";

                        if (jo.has("RAW_MAT_CD")) // Data값이 NULL인 경우 빈값으로 처리
                            raw_mat_cd = jo.getString("RAW_MAT_CD");
                        if (jo.has("RAW_MAT_NM"))
                            raw_mat_nm = jo.getString("RAW_MAT_NM");
                        if (jo.has("SPEC"))
                            spec = jo.getString("SPEC");
                        if (jo.has("UNIT_NM"))
                            unit_nm = jo.getString("UNIT_NM");
                        if (jo.has("CUST_NM"))
                            cust_nm = jo.getString("CUST_NM");
                        if (jo.has("INPUT_AMT"))
                            input_amt = jo.getString("INPUT_AMT");
                        if (jo.has("OUTPUT_AMT"))
                            output_amt = jo.getString("OUTPUT_AMT");
                        if (jo.has("CURR_AMT"))
                            curr_amt = jo.getString("CURR_AMT");
                        if (jo.has("LOC"))
                            loc = jo.getString("LOC");
                        if (jo.has("BASIC_STOCK"))
                            basic_stock = jo.getString("BASIC_STOCK");
                        if (jo.has("BAL_STOCK"))
                            bal_stock = jo.getString("BAL_STOCK");

                        rawVo = new RawVo(raw_mat_cd, raw_mat_nm, spec, unit_nm, cust_nm,
                                input_amt, output_amt, curr_amt, loc, basic_stock, bal_stock);

                        rawAdapter.addItem(rawVo);
                    }

                    if(rawAdapter.getCount() == 0) {
                        Toast.makeText(activity, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
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

        SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.KOREAN);
        SimpleDateFormat month = new SimpleDateFormat("mm", Locale.KOREAN);
        SimpleDateFormat day = new SimpleDateFormat("dd", Locale.KOREAN);
        String today = year + "-" + month + "-" + day;

        query.append("  SELECT \n");
        query.append("  A.RAW_MAT_CD \n");
        query.append(", A.RAW_MAT_NM \n");
        query.append(", A.SPEC \n");
        query.append(", E.UNIT_NM \n");
        query.append(", ISNULL(D.CUST_NM, '') AS CUST_NM \n");
        query.append(", ISNULL(B.LOC, '-') AS LOC \n");
        query.append(", ISNULL(B.INPUT_AMT,0) AS INPUT_AMT \n");
        query.append(", ISNULL(C.OUTPUT_AMT,0) AS OUTPUT_AMT \n");
        query.append(", ISNULL(B.INPUT_AMT,0) - ISNULL(C.OUTPUT_AMT,0) AS CURR_AMT \n");
        query.append(", ISNULL(A.BAL_STOCK,0) AS BAL_STOCK \n");
        query.append(", ISNULL(A.BASIC_STOCK,0) AS BASIC_STOCK \n");
        query.append("  from [" + dbInfo.Location + "].[dbo].[N_RAW_CODE] A \n");
        query.append("  LEFT OUTER JOIN( \n ");
        query.append("          select RAW_MAT_CD \n");
        query.append("          , SUM(ISNULL(TOTAL_AMT,0)) as INPUT_AMT \n");
        query.append("          ,B.STORAGE_NM + ' / ' + A.LOC_NM AS LOC \n");
        query.append("          from [" + dbInfo.Location + "].[dbo].[F_RAW_DETAIL] A \n");
        query.append("          LEFT OUTER JOIN [" + dbInfo.Location + "].[dbo].[N_STORAGE_CODE] B \n");
        query.append("          ON A.STORAGE_CD = B.STORAGE_CD \n");
        query.append("         where INPUT_DATE = '" + today + "' \n");
        query.append("          group by RAW_MAT_CD,B.STORAGE_NM,A.LOC_NM) B \n");
        query.append("  ON A.RAW_MAT_CD = B.RAW_MAT_CD \n");
        query.append("  LEFT OUTER JOIN( \n");
        query.append("          select RAW_MAT_CD \n");
        query.append("          , SUM(ISNULL(TOTAL_AMT,0)) as OUTPUT_AMT \n");
        query.append("          from [" + dbInfo.Location + "].[dbo].[F_RAW_OUTPUT] \n");
        query.append("         where OUTPUT_DATE = '" + today + "' \n");
        query.append("          group by RAW_MAT_CD) C \n");
        query.append("  ON A.RAW_MAT_CD = C.RAW_MAT_CD \n");
        query.append("  left join [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] as D on D.CUST_CD = A.CUST_CD \n");
        query.append("  left join [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] as E on A.INPUT_UNIT = E.UNIT_CD \n");
        query.append("  where 1=1 \n");
        if (!condition.equals("")) {
            if (spinner_search.getSelectedItem().toString().equals("원자재"))
                if (!condition.equals(""))
                    query.append(" AND A.RAW_MAT_NM LIKE '%" + condition + "%'");
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
