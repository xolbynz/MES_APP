package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.Adapter.RawAdapter;
import com.VO.OrderVo;
import com.VO.RawVo;
import com.common.DBInfo;
import com.example.mes_app.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class raw_inputActivity extends Fragment {


    Button btn_input;
    MainActivity activity;
    ViewGroup rootView;
    Context context;
    TextView main_label;
    DBInfo dbInfo;
    JSONArray JSONArray;
    JsonObject jsonObject;
    OrderVo orderVo;
    ArrayList<OrderVo> OrderVoArrayList;

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
        OrderVoArrayList = new ArrayList<>();

        Input_Order_Detail();


        return rootView;
    }

    public void Input_Order_Detail() {
        JSONArray = null;
        try {

            JSONArray = fn_Input_Order_Detail_List();
            if (JSONArray.length() != 0) {

                OrderVoArrayList.clear();
                for (int i = 0; i < JSONArray.length(); i++) {
                    JSONObject jo = JSONArray.getJSONObject(i);

                    String Order_Date = "";
                    String Order_Cd = "";
                    String Order_Seq = "";
                    String Input_Req_Date = "";
                    String Complete_Yn = "";
                    String Rawmat_Cd = "";
                    String Rawmat_Nm = "";
                    String Rawmat_Gubun = "";
                    String Rawmat_Gubun_Nm = "";
                    String Spec = "";
                    String Unit_cd = "";
                    String Unit_Nm = "";
                    String Cust_Cd = "";
                    String Cust_Nm = "";
                    String Price = "";
                    String TotalMoney = "";
                    String Check = "";
                    String Check_Yn = "";
                    String Order_Amt = "";
                    String Input_Amt = "";
                    String Input_NeedAmt = "";
                    String Storage = "";

                    if (jo.has("ORDER_DATE")) // Data값이 NULL인 경우 빈값으로 처리
                        Order_Date = jo.getString("ORDER_DATE");
                    if (jo.has("ORDER_CD"))
                        Order_Cd = jo.getString("ORDER_CD");
                    if (jo.has("ORDER_SEQ"))
                        Order_Seq = jo.getString("ORDER_SEQ");
                    if (jo.has("INPUT_REQ_DATE"))
                        Input_Req_Date = jo.getString("INPUT_REQ_DATE");
                    if (jo.has("COMPLETE_YN"))
                        Complete_Yn = jo.getString("COMPLETE_YN");
                    if (jo.has("RAW_MAT_CD"))
                        Rawmat_Cd = jo.getString("RAW_MAT_CD");
                    if (jo.has("RAW_MAT_NM"))
                        Rawmat_Nm = jo.getString("RAW_MAT_NM");
                    if (jo.has("RAW_MAT_GUBUN"))
                        Rawmat_Gubun = jo.getString("RAW_MAT_GUBUN");
                    if (jo.has("RAW_MAT_GUBUN_NM"))
                        Rawmat_Gubun_Nm = jo.getString("RAW_MAT_GUBUN_NM");
                    if (jo.has("SPEC"))
                        Spec = jo.getString("SPEC");
                    if (jo.has("UNIT_CD"))
                        Unit_cd = jo.getString("UNIT_CD");
                    if (jo.has("UNIT_NM"))
                        Unit_Nm = jo.getString("UNIT_NM");
                    if (jo.has("CUST_CD"))
                        Cust_Cd = jo.getString("CUST_CD");
                    if (jo.has("CUST_NM"))
                        Cust_Nm = jo.getString("CUST_NM");
                    if (jo.has("PRICE"))
                        Price = jo.getString("PRICE");
                    if (jo.has("TOTALMONEY"))
                        TotalMoney = jo.getString("TOTALMONEY");
                    if (jo.has("CHECK"))
                        Check = jo.getString("CHECK_YN");
                    if (jo.has("CHECK_YN"))
                        Check_Yn = jo.getString("CHECK_YN");
                    if (jo.has("ORDER_AMT"))
                        Order_Amt = jo.getString("ORDER_AMT");
                    if (jo.has("INPUT_AMT"))
                        Input_Amt = jo.getString("INPUT_AMT");
                    if (jo.has("INPUT_NEEDAMT"))
                        Input_NeedAmt = jo.getString("NO_INPUT_AMT");
                    if (jo.has("STORAGE"))
                        Storage = jo.getString("STORAGE");

                    orderVo = new OrderVo(Order_Date, Order_Cd, Order_Seq, Input_Req_Date, Complete_Yn,
                        Rawmat_Cd, Rawmat_Nm, Rawmat_Gubun, Rawmat_Gubun_Nm, Spec, Unit_cd,
                        Unit_Nm, Cust_Cd, Cust_Nm, Price, TotalMoney, Check, Check_Yn, Order_Amt,
                        Input_Amt, Input_NeedAmt, Storage);


                }
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

        query.append("select  A.ORDER_DATE   ");
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
        query.append("        , (select S_CODE_NM from[SM_FACTORY_COM].[dbo].[T_S_CODE]where L_CODE = '601'  and S_CODE = D.CHECK_GUBUN)AS CHECK_YN");
        query.append("        , convert(int,ISNULL(TOTAL_AMT, 0)) AS ORDER_AMT");
        query.append("        , convert(int,ISNULL(B.PRICE, 0))  AS PRICE");
        query.append("        , convert(int,ISNULL(B.TOTAL_MONEY, 0)) as TOTAL_MONEY");
        query.append("        , (select S_CODE_NM from[ SM_FACTORY_COM].[dbo].[T_S_CODE]where L_CODE = '300' and S_CODE = D.RAW_MAT_GUBUN)AS RAW_MAT_GUBUN_NM");
        query.append("        , convert(int,ISNULL(C.INPUT_AMT, 0)) AS INPUT_AMT");
        query.append("        , convert(int,ISNULL(C.NO_INPUT_AMT, 0)) AS NO_INPUT_AMT");
        query.append("        , D.RAW_STORAGE AS STORAGE");
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
