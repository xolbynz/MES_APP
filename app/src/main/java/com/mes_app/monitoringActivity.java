package com.mes_app;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.Adapter.MoniteringAdapter;
import com.VO.MoniteringVo;
import com.common.DBInfo;
import com.example.mes_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class monitoringActivity extends Fragment {

    MainActivity activity;
    Context context;
    ViewGroup rootView;
    ImageButton btnSearch;
    InputMethodManager imm;
    int mYear, mMonth, mDay;
    EditText edit_startDate;
    EditText edit_endDate;
    DBInfo dbInfo;
    GridView gridView;
    JSONArray jsonArray;
    MoniteringVo moniteringVo;
    ArrayList<MoniteringVo> moniteringVoArrayList;


    public monitoringActivity() {
    }

    public monitoringActivity(Context context) {

        this.context = context;
        dbInfo = new DBInfo();
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.activity_monitoring, container, false);


        imm = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);

        gridView = rootView.findViewById(R.id.moniter_view_gv_monitering);
        btnSearch = rootView.findViewById(R.id.moniter_btn_search);

        edit_startDate = rootView.findViewById(R.id.edit_start_moniter);
        edit_endDate = rootView.findViewById(R.id.edit_end_moniter);

        edit_startDate.setOnClickListener(showDate);
        edit_endDate.setOnClickListener(showDate);

//        edit_startDate.setOnFocusChangeListener(hideKeyboard);
//        edit_endDate.setOnFocusChangeListener(hideKeyboard);

        imm.hideSoftInputFromWindow(edit_startDate.getWindowToken(), 0);
        btnSearch.setOnClickListener(Monitering_Search);

        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);

        mMonth = cal.get(Calendar.MONTH);

        mDay = cal.get(Calendar.DAY_OF_MONTH);


        return rootView;
    }

//    View.OnFocusChangeListener hideKeyboard = new View.OnFocusChangeListener() {
//        @Override
//        public void onFocusChange(View v, boolean hasFocus) {
//
//            if (hasFocus){
//                imm.hideSoftInputFromWindow(edit_startDate.getWindowToken(), 0);
//                imm.hideSoftInputFromWindow(edit_endDate.getWindowToken(), 0);
//            }
//        }
//    };


    View.OnClickListener showDate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //   imm.hideSoftInputFromWindow(edit_startDate.getWindowToken(), 0);
            //   imm.hideSoftInputFromWindow(edit_endDate.getWindowToken(), 0);
            switch (v.getId()) {
                case R.id.edit_start_moniter:

                    new DatePickerDialog(context, listener, mYear,

                            mMonth, mDay).show();
                    break;
                case R.id.edit_end_moniter:

                    new DatePickerDialog(context, listener2, mYear,

                            mMonth, mDay).show();
                    break;

            }
        }
    };


    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;

            mMonth = month + 1;

            mDay = dayOfMonth;
            String m_Month = String.valueOf(mMonth);
            if (mMonth < 10) {
                m_Month = "0" + mMonth;
            }
            String m_Day = String.valueOf(mDay);

            if (mDay < 10) {
                m_Day = "0" + mDay;
            }

            String selectedDate = year + "-" + m_Month + "-" + m_Day;
            edit_startDate.setText(selectedDate);


        }


    };

    private DatePickerDialog.OnDateSetListener listener2 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            mYear = year;

            mMonth = month + 1;

            mDay = dayOfMonth;


            String m_Month = String.valueOf(mMonth);
            if (mMonth < 10) {
                m_Month = "0" + mMonth;
            }
            String m_Day = String.valueOf(mDay);

            if (mDay < 10) {
                m_Day = "0" + mDay;
            }

            String selectedDate = year + "-" + m_Month + "-" + m_Day;
            edit_endDate.setText(selectedDate);


        }


    };

    View.OnClickListener Monitering_Search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getLogic();
        }
    };


    public void getLogic() {
        try {

            jsonArray = null;
            jsonArray = fn_Monitering(jsonArray, edit_startDate.getText().toString(), edit_endDate.getText().toString());
            gridView.setAdapter(null);

            if (jsonArray.length() != 0) {

                final MoniteringAdapter moniteringAdapter = new MoniteringAdapter();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jo = jsonArray.getJSONObject(i);

                    String Cust_cd = "";
                    String Cust_nm = "";
                    String Inst_date = "";
                    String Deli_date = "";
                    String Item_cd = "";
                    String Item_nm = "";
                    String Lot_no = "";
                    String Flow_count = "";
                    String Processing = "";
                    String Inst_amt = "0";
                    String Input_amt = "0";
                    String Input_date = "";
                    String Poor_amt = "0";
                    String Input_per = "0";
                    String Poor_per = "0";

                    if (jo.has("CUST_CD")) // Data값이 NULL인 경우 빈값으로 처리
                        Cust_cd = jo.getString("CUST_CD");
                    if (jo.has("CUST_NM"))
                        Cust_nm = jo.getString("CUST_NM");
                    if (jo.has("INST_DATE"))
                        Inst_date = jo.getString("INST_DATE");
                    if (jo.has("DELI_DATE"))
                        Deli_date = jo.getString("DELI_DATE");
                    if (jo.has("ITEM_CD"))
                        Item_cd = jo.getString("ITEM_CD");
                    if (jo.has("ITEM_NM"))
                        Item_nm = jo.getString("ITEM_NM");
                    if (jo.has("LOT_NO"))
                        Lot_no = jo.getString("LOT_NO");
                    if (jo.has("FLOW_COUNT"))
                        Flow_count = jo.getString("FLOW_COUNT");
                    if (jo.has("INST_AMT"))
                        Inst_amt = jo.getString("INST_AMT");
                    if (jo.has("PROCESSING"))
                        Processing = jo.getString("PROCESSING");
                    if (jo.has("INPUT_AMT"))
                        Input_amt = jo.getString("INPUT_AMT");
                    if (jo.has("INPUT_DATE"))
                        Input_date = jo.getString("INPUT_DATE");
                    if (jo.has("POOR_AMT"))
                        Poor_amt = jo.getString("POOR_AMT");
                    if (jo.has("INPUT_PER"))
                        Input_per = jo.getString("INPUT_PER");
                    if (jo.has("POOR_PER"))
                        Poor_per = jo.getString("POOR_PER");

                    moniteringVo = new MoniteringVo(Cust_cd, Cust_nm, Inst_date, Deli_date,
                            Item_cd, Item_nm, Lot_no, Flow_count,
                            Inst_amt, Processing, Input_amt, Input_date,
                            Poor_amt, Input_per, Poor_per);

                    moniteringAdapter.addItem(moniteringVo);
                }
                gridView.setAdapter(moniteringAdapter);

            } else {
                Toast.makeText(context, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
                gridView.setAdapter(null);
            }
        } catch (SQLException | JSONException e) {
            System.out.println(e.toString());
            e.printStackTrace();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private JSONArray fn_Monitering(JSONArray Jarray, String condition1, String condition2) throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();


        query.append(" SELECT DISTINCT \n");
        query.append("         ISNULL(A.CUST_CD, '-') AS CUST_CD \n");
        query.append("         ,ISNULL((SELECT CUST_NM FROM [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] WHERE A.CUST_CD = CUST_CD), '-') AS CUST_NM \n");
        query.append("         ,A.W_INST_DATE AS INST_DATE \n");
        query.append("         ,A.DELIVERY_DATE AS DELI_DATE \n");
        query.append("         ,A.ITEM_CD \n");
        query.append("         ,(SELECT ITEM_NM FROM [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] WHERE A.ITEM_CD = ITEM_CD) AS ITEM_NM \n");
        query.append("         ,A.LOT_NO \n");
        query.append("         ,(SELECT COUNT(*) FROM [" + dbInfo.Location + "].[dbo].[N_ITEM_FLOW] WHERE ITEM_CD = A.ITEM_CD) AS FLOW_COUNT \n");
        query.append("         ,CONVERT(INT, A.INST_AMT) AS INST_AMT \n");
        query.append("         ,(CASE WHEN B.LOT_NO IS NULL THEN '공정 미진행' WHEN A.LOT_NO = A.LOT_NO AND A.COMPLETE_YN = 'N' \n");
        query.append("         THEN '공정 진행중' ELSE '공정 완료' END) AS PROCESSING \n");
        query.append("         ,ISNULL(I.INPUT_AMT, 0) AS INPUT_AMT \n");
        query.append("         ,ISNULL(G.INPUT_DATE, '-') AS INPUT_DATE \n");
        query.append("         ,CONVERT(INT, ISNULL(F.POOR_AMT, 0)) AS POOR_AMT \n");
        query.append("         ,CONVERT(INT,ISNULL(I.INPUT_AMT,0)/A.INST_AMT * 100) AS INPUT_PER \n");
        query.append("         ,CONVERT(INT,ISNULL(F.POOR_AMT,0)/A.INST_AMT * 100)  AS POOR_PER \n");
        query.append(" FROM [" + dbInfo.Location + "].[dbo].[F_WORK_INST] A \n");
        query.append(" LEFT OUTER JOIN [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW] B \n");
        query.append(" ON B.LOT_NO = A.LOT_NO \n");
        query.append(" LEFT OUTER JOIN (  SELECT LOT_NO, \n");
        query.append("                    ISNULL(SUM(INPUT_AMT),0) AS INPUT_AMT \n");
        query.append("                    FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] \n");
        query.append("                    GROUP BY LOT_NO ) I \n");
        query.append(" ON I.LOT_NO = A.LOT_NO \n");
        query.append(" LEFT OUTER JOIN (SELECT LOT_NO, SUM(ISNULL(POOR_AMT,0)) AS POOR_AMT FROM [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW_DETAIL] GROUP BY LOT_NO ) F \n");
        query.append(" ON F.LOT_NO = A.LOT_NO \n");
        query.append(" LEFT OUTER JOIN [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] G \n");
        query.append(" ON G.LOT_NO = A.LOT_NO \n");
        query.append(" WHERE A.INST_AMT IS NOT NULL and A.INST_AMT > 0 \n");

        if (!condition1.equals("") && !condition2.equals(""))
            query.append(" AND B.FLOW_DATE > '" + condition1 + "' AND B.FLOW_DATE < '" + condition2 + "' \n");
        else if ((condition1.equals("") && !condition2.equals("")))
            query.append(" AND B.FLOW_DATE < '" + condition2 + "' \n");
        else if (!condition1.equals("") && condition2.equals(""))
            query.append(" AND B.FLOW_DATE >  '" + condition2 + "' \n");
        else if (condition1.equals("") && condition2.equals(""))

        query.append(" ORDER BY A.LOT_NO ");
        Jarray = dbInfo.SelectDB(query.toString());
        return Jarray;

    }


}
