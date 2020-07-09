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

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class work_progressActivity extends Fragment {

    ViewGroup rootView;
    MainActivity activity;
    DBInfo dbInfo;
    GridView RecordWork;
    GridView Recordraw1;
    GridView Recordraw2;

    Button btn_search;
    Spinner spinner_search;

    GridView gridView;
    EditText editSearch;
    JSONArray JArray;
    RawVo rawVo;
    ArrayList<RawVo> rawVoArrayList;




    public work_progressActivity() {
        dbInfo = new DBInfo();
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

        return rootView;
    }

    View.OnClickListener Raw_Search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {

                JArray = work_inst(JArray, editSearch.getText().toString());

                if (JArray != null) {

                    rawVoArrayList.clear();
                    RawAdapter rawAdapter = new RawAdapter();
                    for (int i = 0; i < JArray.length(); i++) {

                        JSONObject jo = JArray.getJSONObject(i);

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


    public JSONArray work_inst(JSONArray JSONArray, String condition) throws SQLException, JSONException {

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

        JSONArray = dbInfo.SelectDB(query.toString());
        return JSONArray;
    }


}