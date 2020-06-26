package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.common.DBInfo;
import com.example.mes_app.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class raw_inputActivity extends Fragment {


    Button btn_input;
    MainActivity activity;
    ViewGroup rootView;
    Context context;
    TextView main_label;
    DBInfo dbInfo;
    JSONArray JSONArray;

    public raw_inputActivity() {
    }

    public raw_inputActivity(Context context) {

        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dbInfo = new DBInfo();

        rootView = (ViewGroup) inflater.inflate(R.layout.activity_raw_input, container, false);
        btn_input = rootView.findViewById(R.id.btn_input);

        btn_input.setOnClickListener(showDialog);

        Input_Order_Detail();


        return rootView;
    }

    public void Input_Order_Detail(){
        JSONArray = null;
        try {
            JSONArray = fn_Input_Order_Detail_List();

            if (JSONArray != null){

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public JSONArray fn_Input_Order_Detail_List() throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();

        SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.KOREAN);
        SimpleDateFormat month = new SimpleDateFormat("mm", Locale.KOREAN);
        SimpleDateFormat day = new SimpleDateFormat("dd", Locale.KOREAN);
        String today = year + "-" + month + "-" + day;

        query.append("select A.ORDER_DATE   ");
        query.append("        , A.ORDER_CD");
        query.append("        , B.SEQ");
        query.append("        , A.INPUT_REQ_DATE");
        query.append("        , A.COMPLETE_YN");
        query.append("        , B.RAW_MAT_CD");
        query.append("        , D.RAW_MAT_NM");
        query.append("        , D.SPEC");
        query.append("        , B.UNIT_CD ");
        query.append("        , (select UNIT_NM from [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] where UNIT_CD = B.UNIT_CD)AS UNIT_NM");
        query.append("        , D.RAW_MAT_GUBUN");
        query.append("        , X.CUST_CD");
        query.append("        , X.CUST_NM");
        query.append("        , D.CHECK_GUBUN");
        query.append("        , (select S_CODE_NM from[SM_FACTORY_COM].[dbo].[T_S_CODE]where L_CODE = '601'  and S_CODE = D.CHECK_GUBUN)AS 검사여부");
        query.append("        , ISNULL(TOTAL_AMT, 0) AS ORDER_AMT");
        query.append("        , B.PRICE");
        query.append("        , B.TOTAL_MONEY");
        query.append("        , (select S_CODE_NM from[ SM_FACTORY_COM].[dbo].[T_S_CODE]where L_CODE = '300' and S_CODE = D.RAW_MAT_GUBUN)AS RAW_MAT_GUBUN_NM");
        query.append("        , C.INPUT_AMT");
        query.append("        , C.NO_INPUT_AMT");
        query.append("        , D.RAW_STORAGE");
        query.append("FROM [" + dbInfo.Location + "].[dbo].[F_ORDER] A");
        query.append("LEFT OUTER JOIN [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] X ON A.CUST_CD = X.CUST_CD");
        query.append("LEFT OUTER JOIN [" + dbInfo.Location + "].[dbo].[F_ORDER_DETAIL] B");
        query.append("ON A.ORDER_DATE = B.ORDER_DATE");
        query.append("AND A.ORDER_CD = B.ORDER_CD");
        query.append("LEFT OUTER JOIN(");
        query.append("        SELECT AA.ORDER_DATE");
        query.append("        , AA.ORDER_CD");
        query.append("        , AA.SEQ");
        query.append("        , ISNULL(SUM(BB.TEMP_AMT), 0)AS INPUT_AMT");
        query.append("        , ISNULL(AA.TOTAL_AMT, 0) - ISNULL(SUM(BB.TEMP_AMT), 0)AS NO_INPUT_AMT");
        query.append("        FROM [" + dbInfo.Location + "].[dbo].[F_ORDER_DETAIL] AA");
        query.append("        LEFT OUTER JOIN [" + dbInfo.Location + "].[dbo].[F_RAW_DETAIL] BB");
        query.append("        ON AA.ORDER_DATE = BB.ORDER_DATE");
        query.append("        AND AA.ORDER_CD = BB.ORDER_CD");
        query.append("        AND AA.SEQ = BB.ORDER_SEQ");
        query.append("        GROUP BY AA.ORDER_DATE, AA.ORDER_CD, AA.SEQ, AA.TOTAL_AMT) C");
        query.append("ON A.ORDER_DATE = C.ORDER_DATE");
        query.append("AND A.ORDER_CD = C.ORDER_CD");
        query.append("AND B.SEQ = C.SEQ");
        query.append("LEFT OUTER JOIN [" + dbInfo.Location + "].[dbo].[N_RAW_CODE] D");
        query.append("ON B.RAW_MAT_CD = D.RAW_MAT_CD");
        query.append("where C.NO_INPUT_AMT > 0 and A.ORDER_DATE <= '" + today + "'");
        query.append("order by X.CUST_CD, A.ORDER_DATE desc, A.ORDER_CD desc, B.SEQ desc");

        JSONArray = dbInfo.SelectDB(query.toString());
        return JSONArray;
    }

    View.OnClickListener showDialog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            CustomDialog oDialog = new CustomDialog(context);
            oDialog.setCancelable(false);

            oDialog.show();

        }
    };


}
