package com.mes_app;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Adapter.MoniteringAdapter;
import com.VO.MoniteringVo;
import com.common.DBInfo;
import com.example.mes_app.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class monitoringActivity extends Fragment {

    MainActivity activity;
    Context context;
    ViewGroup rootView;
    Button btnSearch;
    InputMethodManager imm;
    int mYear, mMonth, mDay;
    EditText edit_startDate;
    EditText edit_endDate;
    DBInfo dbInfo;
    GridView gridView;
    JSONArray JArray;
    JsonObject jsonObject;
    MoniteringVo moniteringVo;
    ArrayList<MoniteringVo> moniteringVoArrayList;


    public monitoringActivity(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_monitoring, container, false);

        imm = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        gridView = rootView.findViewById(R.id.moniter_view_gv_monitering);

        edit_endDate = rootView.findViewById(R.id.edit_end_moniter);
        edit_startDate = rootView.findViewById(R.id.edit_start_moniter);

        edit_endDate.setOnClickListener(showDate);
        edit_startDate.setOnClickListener(showDate);
        return rootView;
    }


    View.OnClickListener showDate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            System.out.println("달력소환");
            imm.hideSoftInputFromWindow(edit_endDate.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(edit_startDate.getWindowToken(), 0);


            switch (v.getId()) {
                case R.id.edit_end_moniter:

                    new DatePickerDialog(context, listener, mYear,

                            mMonth, mDay).show();
                    break;
                case R.id.edit_start_moniter:

                    new DatePickerDialog(context, listener2, mYear,

                            mMonth, mDay).show();
                    break;

            }


        }
    };

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            mYear = year;

            mMonth = monthOfYear;

            mDay = dayOfMonth;

            edit_endDate.setText(String.format("%d/%d/%d", mYear,

                    mMonth + 1, mDay));

        }

    };

    private DatePickerDialog.OnDateSetListener listener2 = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            mYear = year;

            mMonth = monthOfYear;

            mDay = dayOfMonth;

            edit_startDate.setText(String.format("%d/%d/%d", mYear,

                    mMonth + 1, mDay));

        }

    };

    View.OnClickListener Monitering_Search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getLogic();
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    };


    public void getLogic() {
        try {
            JArray = null;
            JArray = fn_Monitering(JArray, edit_startDate.getText().toString(), edit_endDate.getText().toString());

            if (JArray.length() != 0) {

                MoniteringAdapter itemAdapter = new MoniteringAdapter();

                for (int i = 0; i < JArray.length(); i++) {

                    JSONObject jo = JArray.getJSONObject(i);

                    String Inst_date = "";
                    String Deli_date = "";
                    String Item_cd = "";
                    String Item_nm = "";
                    String Lot_no = "";
                    String Flow_count = "";
                    String Inst_amt = "";
                    String Input_amt = "";
                    String Poor_amt = "";
                    String Input_per = "";
                    String Poor_per = "";

                    if (jo.has("LOT_NO")) // Data값이 NULL인 경우 빈값으로 처리
                        Inst_date = jo.getString("LOT_NO");
                    if (jo.has("LOT_SUB"))
                        Deli_date = jo.getString("LOT_SUB");
                    if (jo.has("LOT_BAR"))
                        Item_cd = jo.getString("LOT_BAR");
                    if (jo.has("ITEM_CD"))
                        Item_nm = jo.getString("ITEM_CD");
                    if (jo.has("ITEM_NM"))
                        Lot_no = jo.getString("ITEM_NM");
                    if (jo.has("SPEC"))
                        Flow_count = jo.getString("SPEC");
                    if (jo.has("UNIT_CD"))
                        Inst_amt = jo.getString("UNIT_CD");
                    if (jo.has("UNIT_NM"))
                        Input_amt = jo.getString("UNIT_NM");
                    if (jo.has("F_SUB_AMT"))
                        Poor_amt = jo.getString("F_SUB_AMT");
                    if (jo.has("CUST_CD"))
                        Input_per = jo.getString("CUST_CD");
                    if (jo.has("CUST_NM"))
                        Poor_per = jo.getString("CUST_NM");

                    moniteringVo = new MoniteringVo(Inst_date, Deli_date, Item_cd, Item_nm, Lot_no,
                            Flow_count, Inst_amt, Input_amt, Poor_amt, Input_per, Poor_per);

                    itemAdapter.addItem(moniteringVo);
                }
                gridView.setAdapter(itemAdapter);

            } else {
                Toast.makeText(activity, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
                gridView.setAdapter(null);
            }
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONArray fn_Monitering(JSONArray jsonArray, String condition1, String condition2) throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();


        query.append(" SELECT   W.CUST_CD");
        query.append("         ,W.W_INST_DATE AS INST_DATE");
        query.append("         ,W.DELIVERY_DATE AS DELI_DATE");
        query.append("         ,W.ITEM_CD");
        query.append("         ,(SELECT ITEM_NM FROM [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] WHERE W.ITEM_CD = ITEM_CD) AS ITEM_NM");
        query.append("         ,W.LOT_NO");
        query.append("         ,(SELECT COUNT(*) FROM [" + dbInfo.Location + "].[dbo].[N_ITEM_FLOW] WHERE ITEM_CD = W.ITEM_CD) AS FLOW_COUNT");
        query.append("         ,CONVERT(INT,W.INST_AMT) AS INST_AMT");
        query.append("         ,CONVERT(INT,ISNULL(I.INPUT_AMT,0)) AS INPUT_AMT");
        query.append("         ,CONVERT(INT,ISNULL(INPUT_AMT,0)/W.INST_AMT * 100) AS INPUT_PER");
        query.append("         ,CONVERT(INT,F.POOR_AMT) AS POOR_AMT");
        query.append("         ,CONVERT(INT,ISNULL(F.POOR_AMT,0)/W.INST_AMT * 100) AS POOR_PER");
        query.append(" FROM [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW] A");
        query.append(" INNER JOIN [" + dbInfo.Location + "].[dbo].[F_WORK_INST] W");
        query.append(" ON A.LOT_NO = W.LOT_NO");
        query.append(" LEFT OUTER JOIN (  SELECT LOT_NO,");
        query.append("                           ISNULL(SUM(INPUT_AMT),0) AS INPUT_AMT");
        query.append("                     FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT]");
        query.append("                     GROUP BY LOT_NO ) I");
        query.append(" ON I.LOT_NO = A.LOT_NO");
        query.append(" LEFT OUTER JOIN (SELECT LOT_NO, ");
        query.append("                         SUM(ISNULL(POOR_AMT,0)) AS POOR_AMT");
        query.append("                  FROM [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW_DETAIL] ");
        query.append("                  GROUP BY LOT_NO ) F");
        query.append(" ON F.LOT_NO = A.LOT_NO");
        query.append(" WHERE W.INST_AMT IS NOT NULL AND W.INST_AMT > 0");
        if ((condition1 != "" || condition1 != null) && (condition2 != "" || condition2 != null))
            query.append(" AND A.FLOW_DATE > '" + condition1 + "' AND A.FLOW_DATE < '" + condition2 + "'");
        else if ((condition1 == "" || condition1 == null) && (condition2 != "" || condition2 != null))
            query.append(" AND A.FLOW_DATE < '" + condition2 + "'");
        else if ((condition1 != "" || condition1 != null) && (condition2 == "" || condition2 == null))
            query.append(" AND A.FLOW_DATE >  '" + condition2 + "'");

        jsonArray = dbInfo.SelectDB(query.toString());
        return jsonArray;
    }


}
