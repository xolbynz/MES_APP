package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Adapter.ItemTraceAdapter;
import com.common.DBInfo;
import com.example.mes_app.R;
import com.VO.TraceListVO;
import com.VO.TraceListDetailVo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class item_trackingActivity extends Fragment {

    MainActivity activity;
    ViewGroup rootView;
    Context context;
    DBInfo dbInfo;
    JSONArray jsonArray;
    EditText Barcode;
    Button btn_search;
    String Lot_no = "";
    TraceListVO traceListVO;
    TraceListDetailVo traceListDetailVo;
    InputMethodManager imm;
    GridView gridView;



    public item_trackingActivity() {

    }

    public item_trackingActivity(Context context) {
        this.context = context;
        dbInfo = new DBInfo();
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        rootView = (ViewGroup) inflater.inflate(R.layout.activity_item_tracking, container, false);
        imm = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);

        Barcode = rootView.findViewById(R.id.itemTracking_et_barcode);
        btn_search = rootView.findViewById(R.id.itemTracking_btn_search);
        gridView = rootView.findViewById(R.id.itemTracking_tv_fault);

        btn_search.setOnClickListener(onClickListener);

        return rootView;
    }

   Button.OnClickListener onClickListener = new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           imm.hideSoftInputFromWindow(Barcode.getWindowToken(), 0);

           if (Barcode.getText().length() != 13 || Barcode.getText().equals("")) {
               Toast.makeText(context, "잘못된 LOT 번호 입니다.", Toast.LENGTH_LONG).show();
           } else {
               Lot_no = Barcode.getText().toString();
               databind();
           }
       }
   };


    public void databind() {
        try {

            jsonArray = null;
            jsonArray = fn_flow_trace_list_info_By_Lot(jsonArray, Lot_no);

            if (jsonArray.length() != 0 && jsonArray != null) {

                final ItemTraceAdapter itemTraceAdapter = new ItemTraceAdapter();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jo = jsonArray.getJSONObject(i);

                    String input_date = "";
                    String input_cd = "";
                    String lot_no = "";
                    String work_inst_date = "";
                    String work_inst_cd = "";
                    String delivery_date = "";
                    String inst_notice = "";
                    String complete_yn = "";
                    String raw_mat_cd = "";
                    String inst_amt = "0";


                    if (jo.has("INPUT_DATE")) // Data값이 NULL인 경우 빈값으로 처리
                        input_date = jo.getString("INPUT_DATE");
                    if (jo.has("INPUT_CD"))
                        input_cd = jo.getString("INPUT_CD");
                    if (jo.has("W_INST_DATE"))
                        work_inst_date = jo.getString("W_INST_DATE");
                    if (jo.has("W_INST_CD"))
                        work_inst_cd = jo.getString("W_INST_CD");
                    if (jo.has("DELIVERY_DATE"))
                        delivery_date = jo.getString("DELIVERY_DATE");
                    if (jo.has("INST_NOTICE"))
                        inst_notice = jo.getString("INST_NOTICE");
                    if (jo.has("COMPLETE_YN"))
                        complete_yn = jo.getString("COMPLETE_YN");
                    if (jo.has("RAW_MAT_CD"))
                        raw_mat_cd = jo.getString("RAW_MAT_CD");
                    if (jo.has("INST_AMT"))
                        inst_amt = jo.getString("INST_AMT");

                    traceListVO = new TraceListVO(input_date, input_cd, lot_no, work_inst_date,
                            work_inst_cd, delivery_date, inst_notice, complete_yn, raw_mat_cd, inst_amt);

                    if (traceListVO.getComplete_yn().equals("Y")) {

                    } else {

                    }
                }

                jsonArray = null;
                jsonArray = fn_flow_trace_list_By_LotNo(jsonArray, Lot_no);

                if (jsonArray.length() != 0 && jsonArray != null) {


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jo = jsonArray.getJSONObject(i);

                        String gubun = "";
                        String order1 = "";
                        String order2 = "";
                        String input_date = "";
                        String input_cd = "";
                        String intime = "";
                        String cust_nm = "";
                        String raw_mat_nm = "";
                        String spec = "";
                        String lot_no = "";
                        String lot_sub = "";
                        String unit_nm = "";
                        String total_amt = "";
                        String loss_amt = "";
                        String poor = "";


                        if (jo.has("GUBUN")) // Data값이 NULL인 경우 빈값으로 처리
                            gubun = jo.getString("GUBUN");
                        if (jo.has("ORDER1"))
                            order1 = jo.getString("ORDER1");
                        if (jo.has("ORDER2"))
                            order2 = jo.getString("ORDER2");
                        if (jo.has("INPUT_DATE"))
                            input_date = jo.getString("INPUT_DATE");
                        if (jo.has("INPUT_CD"))
                            input_cd = jo.getString("INPUT_CD");
                        if (jo.has("INTIME"))
                            intime = jo.getString("INTIME");
                        if (jo.has("CUST_NM"))
                            cust_nm = jo.getString("CUST_NM");
                        if (jo.has("RAW_MAT_CD"))
                            raw_mat_nm = jo.getString("RAW_MAT_CD");
                        if (jo.has("SPEC"))
                            spec = jo.getString("SPEC");
                        if (jo.has("LOT_NO"))
                            lot_no = jo.getString("LOT_NO");
                        if (jo.has("LOT_SUB"))
                            lot_sub = jo.getString("LOT_SUB");
                        if (jo.has("UNIT_NM"))
                            unit_nm = jo.getString("UNIT_NM");
                        if (jo.has("TOTAL_AMT"))
                            total_amt = jo.getString("TOTAL_AMT");
                        if (jo.has("LOSS_AMT"))
                            loss_amt = jo.getString("LOSS_AMT");
                        if (jo.has("POOR"))
                            poor = jo.getString("POOR");

                        traceListDetailVo = new TraceListDetailVo(gubun, order1, order2, input_date,
                                input_cd, intime, cust_nm, raw_mat_nm, spec, lot_no, lot_sub, unit_nm,
                                total_amt, loss_amt, poor);

                        itemTraceAdapter.addItem(traceListDetailVo);
                    }
                    gridView.setAdapter(itemTraceAdapter);
                }

            } else if (jsonArray.length() > 1) {
                Toast.makeText(context, "하나 이상의 LOT번호가 존재합니다 < 오류 >", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException | JSONException e) {
            Toast.makeText(context, "검색 오류", Toast.LENGTH_SHORT).show();
            System.out.println(e.toString());
            e.printStackTrace();

        } catch (Exception ex) {
            Toast.makeText(context, "시스템 오류", Toast.LENGTH_SHORT).show();
            System.out.println(ex.toString());
        }
    }

    private JSONArray fn_flow_trace_list_info_By_Lot(JSONArray Jarray, String condition) throws SQLException, JSONException {


        StringBuilder sb = new StringBuilder();

        sb.append(" select ");
        sb.append("         I.INPUT_DATE ");
        sb.append("         ,I.INPUT_CD ");
        sb.append("         ,I.LOT_NO ");
        sb.append("         ,W.W_INST_DATE ");
        sb.append("         ,W.W_INST_CD ");
        sb.append("         ,W.DELIVERY_DATE ");
        sb.append("         ,W.INST_NOTICE ");
        sb.append("         ,F.COMPLETE_YN ");
        sb.append("         ,W.ITEM_CD AS RAW_MAT_CD ");
        sb.append("         ,W.INST_AMT ");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_INST] W ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] I ");
        sb.append(" on W.LOT_NO = I.LOT_NO ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW] F ");
        sb.append(" on F.LOT_NO = W.LOT_NO ");
        sb.append(" where W.LOT_NO = '" + condition + "' ");


        Jarray = dbInfo.SelectDB(sb.toString());
        return Jarray;
    }

    private JSONArray fn_flow_trace_list_By_LotNo(JSONArray Jarray, String condition) throws SQLException, JSONException {
        StringBuilder sb = new StringBuilder();

        sb.append(" select");
        sb.append(" '지시' AS GUBUN");
        sb.append(" ,1 as ORDER1");
        sb.append(" ,1 as ORDER2");
        sb.append(" ,W.W_INST_DATE AS INPUT_DATE ");
        sb.append(" ,CONVERT(nvarchar,W.W_INST_CD) AS INPUT_CD");
        sb.append(" ,'' AS INPUT_SEQ ");
        sb.append(" ,W.INTIME AS TIME ");
        sb.append(" ,(SELECT CUST_NM FROM [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] WHERE W.CUST_CD = CUST_CD) AS CUST_NM");
        sb.append(" ,N.ITEM_NM AS RAW_MAT_NM ");
        sb.append(" ,N.SPEC");
        sb.append(" ,W.LOT_NO AS LOT_NO");
        sb.append(" ,'' AS LOT_SUB");
        sb.append(" ,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.UNIT_CD = UNIT_CD) AS UNIT_NM");
        sb.append(" ,CONVERT(nvarchar,W.INST_AMT) AS TOTAL_AMT");
        sb.append(" ,'' AS LOSS_AMT");
        sb.append(" ,'' AS POOR");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_INST] W");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] N ");
        sb.append(" on W.ITEM_CD = N.ITEM_CD ");
        sb.append(" where W.LOT_NO = '" + condition + "' ");
        sb.append("  ");
        sb.append("  union all");
        sb.append(" select");
        sb.append(" '자재투입' AS GUBUN");
        sb.append(" ,2 as ORDER1");
        sb.append(" ,1 as ORDER2");
        sb.append(" ,ISNULL(D.INPUT_DATE,'음수 투입') AS INPUT_DATE");
        sb.append(" ,CONVERT(nvarchar,D.INPUT_CD )");
        sb.append(" ,CONVERT(nvarchar,D.SEQ ) ");
        sb.append(" ,W.INTIME AS TIME ");
        sb.append(" ,(SELECT CUST_NM FROM [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] WHERE RI.CUST_CD = CUST_CD ) AS CUST_NM");
        sb.append(" ,N.RAW_MAT_NM ");
        sb.append(" ,N.SPEC");
        sb.append(" ,'' AS LOT_NO ");
        sb.append(" ,'' AS LOT_SUB");
        sb.append(" ,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.OUTPUT_UNIT = UNIT_CD ) AS UNIT_NM ");
        sb.append(" ,CONVERT(nvarchar,O.TOTAL_AMT/I.SOYO_AMT)+'x'+CONVERT(nvarchar,I.SOYO_AMT)+'='+CONVERT(nvarchar,O.TOTAL_AMT) AS TOTAL_AMT ");
        sb.append(" ,'' AS LOSS_AMT ");
        sb.append(" ,'' AS POOR ");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_INST] W");
        sb.append(" inner join [" + dbInfo.Location + "].[dbo].[F_WORK_INST_DETAIL] I ");
        sb.append(" on W.W_INST_DATE = I.W_INST_DATE ");
        sb.append(" and W.W_INST_CD = I.W_INST_CD");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_RAW_OUTPUT] O");
        sb.append(" on I.LOT_NO = O.LOT_NO");
        sb.append(" and I.RAW_MAT_CD = O.RAW_MAT_CD ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_RAW_DETAIL] D");
        sb.append(" on D.INPUT_DATE = O.INPUT_DATE");
        sb.append(" and D.INPUT_CD = O.INPUT_CD ");
        sb.append(" and D.SEQ = O.INPUT_SEQ ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_RAW_INPUT] RI");
        sb.append(" on D.INPUT_DATE = RI.INPUT_DATE ");
        sb.append(" and D.INPUT_CD = RI.INPUT_CD ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_RAW_CODE] N ");
        sb.append(" on O.RAW_MAT_CD = N.RAW_MAT_CD");
        sb.append(" where O.LOT_NO = '" + condition + "'");
        sb.append("  union all");
        sb.append(" select");
        sb.append(" '반제품투입' AS GUBUN");
        sb.append(" ,3 as ORDER1");
        sb.append(" ,1 as ORDER2 ");
        sb.append(" ,ISNULL(D.INPUT_DATE,'음수 투입') AS INPUT_DATE ");
        sb.append(" ,CONVERT(nvarchar,D.INPUT_CD)");
        sb.append(" ,'' AS SEQ");
        sb.append(" ,W.INTIME AS TIME");
        sb.append(" ,'' AS CUST_NM");
        sb.append(" ,N.ITEM_NM AS RAW_MAT_NM ");
        sb.append(" ,N.SPEC ");
        sb.append(" ,O.LOT_NO AS LOT_NO ");
        sb.append(" ,CONVERT(nvarchar,O.LOT_SUB) AS LOT_SUB ");
        sb.append(" ,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.UNIT_CD = UNIT_CD ) AS UNIT_NM ");
        sb.append(" ,CONVERT(nvarchar,O.OUTPUT_AMT/I.SOYO_AMT)+'x'+CONVERT(nvarchar,I.SOYO_AMT)+'='+CONVERT(nvarchar,O.OUTPUT_AMT) AS TOTAL_AMT ");
        sb.append(" ,'' AS LOSS_AMT ");
        sb.append(" ,'' AS POOR ");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_INST] W");
        sb.append(" inner join [" + dbInfo.Location + "].[dbo].[F_WORK_INST_HALF_DETAIL] I ");
        sb.append(" on W.W_INST_DATE = I.W_INST_DATE ");
        sb.append(" and W.W_INST_CD = I.W_INST_CD");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_ITEM_OUT_DETAIL] O ");
        sb.append(" on I.LOT_NO = O.OUT_LOT ");
        sb.append(" and I.HALF_ITEM_CD = O.ITEM_CD");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] D");
        sb.append(" on D.INPUT_DATE = O.INPUT_DATE");
        sb.append(" and D.INPUT_CD = O.INPUT_CD ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] N");
        sb.append(" on O.ITEM_CD = N.ITEM_CD ");
        sb.append(" where O.OUT_LOT = '" + condition + "'");
        sb.append("  ");
        sb.append(" union all");
        sb.append("  select ");
        sb.append(" '공정' AS GUBUN ");
        sb.append(" ,4 as ORDER1");
        sb.append(" ,W.F_STEP as ORDER2");
        sb.append(" ,W.F_SUB_DATE AS INPUT_DATE ");
        sb.append(" ,CONVERT(nvarchar,W.SEQ) AS INPUT_CD ");
        sb.append(" ,'' AS SEQ");
        sb.append(" ,W.INTIME AS TIME");
        sb.append(" ,CONVERT(nvarchar,W.LOT_SUB)+' ('+CONVERT(nvarchar,F.FLOW_NM)+')' AS CUST_NM ");
        sb.append(" ,(SELECT FLOW_NM FROM [" + dbInfo.Location + "].[dbo].[N_FLOW_CODE] WHERE FLOW_CD = W.FLOW_CD ) AS RAW_MAT_NM ");
        sb.append(" ,'' ");
        sb.append(" ,W.LOT_NO");
        sb.append(" ,CONVERT(nvarchar,W.LOT_SUB) AS LOT_SUB ");
        sb.append(" ,'' AS UNIT_NM");
        sb.append(" ,CONVERT(nvarchar,W.F_SUB_AMT)+'/'+CONVERT(nvarchar,W.INPUT_AMT) AS TOTAL_AMT");
        sb.append(" ,CONVERT(nvarchar,W.LOSS) AS LOSS_AMT");
        sb.append(" ,CONVERT(nvarchar,W.POOR_AMT) AS POOR");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW_DETAIL] W");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_FLOW_CODE] F");
        sb.append(" on W.FLOW_CD = F.FLOW_CD ");
        sb.append(" where W.LOT_NO = '" + condition + "'");
        sb.append("  ");
        sb.append(" union all");
        sb.append("  ");
        sb.append("  select ");
        sb.append(" '공정' AS GUBUN ");
        sb.append(" ,4 as ORDER1");
        sb.append(" ,W.F_STEP+0.1 as ORDER2");
        sb.append(" ,'' AS INPUT_DATE");
        sb.append(" ,'' AS INPUT_CD ");
        sb.append(" ,'' AS SEQ");
        sb.append(" ,'' AS TIME ");
        sb.append(" ,''+CONVERT(nvarchar,F.FLOW_NM)+' 합계' AS CUST_NM");
        sb.append(" ,(SELECT FLOW_NM FROM [" + dbInfo.Location + "].[dbo].[N_FLOW_CODE] WHERE FLOW_CD = F.FLOW_CD ) AS RAW_MAT_NM ");
        sb.append(" ,'' ");
        sb.append(" ,W.LOT_NO");
        sb.append(" ,'' AS LOT_SUB");
        sb.append(" ,'' AS UNIT_NM");
        sb.append(" ,CONVERT(nvarchar,SUM(W.F_SUB_AMT))+'/'+CONVERT(nvarchar,SUM(W.INPUT_AMT)) AS TOTAL_AMT ");
        sb.append(" ,CONVERT(nvarchar,SUM(W.LOSS)) AS LOSS_AMT");
        sb.append(" ,CONVERT(nvarchar,SUM(W.POOR_AMT)) AS POOR");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW_DETAIL] W");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_FLOW_CODE] F");
        sb.append(" on W.FLOW_CD = F.FLOW_CD ");
        sb.append(" where W.LOT_NO = '" + condition + "'");
        sb.append(" group by F.FLOW_CD,W.F_STEP,F.FLOW_NM,LOT_NO ");
        sb.append("  ");
        sb.append(" union all");
        sb.append("  ");
        sb.append("  ");
        sb.append("  select ");
        sb.append(" '공정' AS GUBUN ");
        sb.append(" ,4 as ORDER1");
        sb.append(" ,99 as ORDER2 ");
        sb.append(" ,'' AS INPUT_DATE");
        sb.append(" ,'' AS INPUT_CD ");
        sb.append(" ,'' AS SEQ");
        sb.append(" ,'' AS TIME ");
        sb.append(" ,'총 공정 합계' AS CUST_NM");
        sb.append(" ,'' AS RAW_MAT_NM");
        sb.append(" ,'' AS TEST ");
        sb.append(" ,K.LOT_NO AS LOT_NO ");
        sb.append(" ,'' AS LOT_SUB");
        sb.append(" ,'' AS UNIT_NM");
        sb.append(" ,CONVERT(nvarchar,SUM(K.INPUT_AMT))+'/'+CONVERT(nvarchar,SUM(K.INST_AMT)) AS TOTAL_AMT");
        sb.append(" ,CONVERT(nvarchar,SUM(K.LOSS)) AS LOSS");
        sb.append(" ,CONVERT(nvarchar,SUM(K.POOR_AMT)) AS POOR_AMT");
        sb.append(" from (");
        sb.append("  select ");
        sb.append(" A.LOT_NO AS LOT_NO");
        sb.append(" ,AVG(B.INST_AMT) AS INST_AMT ");
        sb.append(" ,0 AS INPUT_AMT ");
        sb.append(" 		,SUM(A.LOSS) AS LOSS");
        sb.append(" 		,SUM(A.POOR_AMT) AS POOR_AMT");
        sb.append(" 	 FROM [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW_DETAIL] A ");
        sb.append(" 	 left outer join [" + dbInfo.Location + "].[dbo].[F_WORK_INST] B ");
        sb.append(" 	 on A.LOT_NO = B.LOT_NO ");
        sb.append(" 	 where A.LOT_NO = '" + condition + "' ");
        sb.append(" 	 group by A.LOT_NO ");
        sb.append("  ");
        sb.append("  union all");
        sb.append("  ");
        sb.append("  select ");
        sb.append(" A.LOT_NO AS LOT_NO");
        sb.append(" ,0 AS INST_AMT");
        sb.append(" ,SUM(A.INPUT_AMT) AS INPUT_AMT");
        sb.append(" ,0 AS LOSS");
        sb.append(" ,0 AS POOR_AMT");
        sb.append("  FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] A ");
        sb.append("  where A.LOT_NO = '" + condition + "'");
        sb.append("  group by A.LOT_NO");
        sb.append("  ");
        sb.append("  ) AS K ");
        sb.append("  where K.LOT_NO = '" + condition + "'");
        sb.append("  GROUP BY K.LOT_NO");
        sb.append("  ");
        sb.append("  union all");
        sb.append(" select");
        sb.append(" '생산' AS GUBUN ");
        sb.append(" ,5 as ORDER1");
        sb.append(" ,1 as ORDER2 ");
        sb.append(" ,I.INPUT_DATE");
        sb.append(" ,CONVERT(nvarchar,I.INPUT_CD)");
        sb.append(" ,'' AS INPUT_SEQ ");
        sb.append(" ,I.INTIME AS TIME");
        sb.append(" ,'' AS CUST_NM");
        sb.append(" ,N.ITEM_NM AS RAW_MAT_NM ");
        sb.append(" ,N.SPEC ");
        sb.append(" ,I.LOT_NO");
        sb.append(" ,convert(nvarchar,I.LOT_SUB) ");
        sb.append(" ,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.UNIT_CD = UNIT_CD ) AS UNIT_NM ");
        sb.append(" ,CONVERT(nvarchar,I.INPUT_AMT) AS TOTAL_AMT ");
        sb.append(" ,'' AS LOSS_AMT ");
        sb.append(" ,'' AS POOR ");
        sb.append(" FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT I ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] N");
        sb.append(" on N.ITEM_CD = I.ITEM_CD ");
        sb.append(" where I.LOT_NO = '" + condition + "'");
        sb.append("  ");
        sb.append(" union all");
        sb.append(" select");
        sb.append(" '생산' AS GUBUN ");
        sb.append(" ,5 as ORDER1");
        sb.append(" ,2 as ORDER2 ");
        sb.append(" ,'' AS INPUT_DATE");
        sb.append(" ,'' AS INPUT_CD ");
        sb.append(" ,'' AS INPUT_SEQ ");
        sb.append(" ,'' AS TIME ");
        sb.append(" ,'총 생산 합계' AS CUST_NM");
        sb.append(" ,N.ITEM_NM AS RAW_MAT_NM ");
        sb.append(" ,N.SPEC ");
        sb.append(" ,I.LOT_NO");
        sb.append(" ,'' AS LOT_SUB");
        sb.append(" ,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.UNIT_CD = UNIT_CD ) AS UNIT_NM ");
        sb.append(" ,CONVERT(nvarchar,SUM(I.INPUT_AMT)) AS TOTAL_AMT ");
        sb.append(" ,'' AS LOSS_AMT ");
        sb.append(" ,'' AS POOR ");
        sb.append(" FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] I ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] N");
        sb.append(" on N.ITEM_CD = I.ITEM_CD ");
        sb.append(" where I.LOT_NO = '" + condition + "'");
        sb.append(" group by I.LOT_NO , N.ITEM_NM , N.SPEC , N.UNIT_CD");
        sb.append("  ");
        sb.append(" union all");
        sb.append(" select");
        sb.append(" '납품' AS GUBUN ");
        sb.append(" ,7 as ORDER1");
        sb.append(" ,1 as ORDER2 ");
        sb.append("  ,S.SALES_DATE AS 매출일자");
        sb.append(" ,CONVERT(nvarchar,S.SALES_CD)");
        sb.append(" ,CONVERT(nvarchar,S.SEQ) AS INPUT_SEQ");
        sb.append(" ,SI.INTIME AS TIME");
        sb.append(" ,(SELECT CUST_NM FROM [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] where CUST_CD = SI.CUST_CD ) AS CUST_NM");
        sb.append(" ,N.ITEM_NM AS RAW_MAT_NM ");
        sb.append(" ,N.SPEC ");
        sb.append(" ,I.LOT_NO");
        sb.append(" ,convert(nvarchar,I.LOT_SUB) ");
        sb.append(" ,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.UNIT_CD = UNIT_CD ) AS UNIT_NM ");
        sb.append(" ,CONVERT(nvarchar,S.TOTAL_AMT) AS TOTAL_AMT ");
        sb.append(" ,'' AS LOSS_AMT ");
        sb.append(" ,'' AS POOR ");
        sb.append(" FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_OUT_DETAIL] I ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] N");
        sb.append(" on N.ITEM_CD = I.ITEM_CD ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_SALES_DETAIL] S ");
        sb.append(" on S.OUTPUT_DATE = I.OUTPUT_DATE ");
        sb.append(" and S.OUTPUT_CD = I.OUTPUT_CD");
        sb.append(" and S.OUTPUT_SEQ = I.SEQ ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_SAELS] SI");
        sb.append(" on SI.SALES_DATE = S.SALES_DATE ");
        sb.append(" and SI.SALES_CD = S.SALES_CD ");
        sb.append(" where I.LOT_NO = '" + condition + "' and I.OUT_LOT is null and S.OUTPUT_DATE is not null");
        sb.append("  ");
        sb.append(" union all ");
        sb.append("  ");
        sb.append(" select");
        sb.append(" '납품' AS GUBUN ");
        sb.append(" ,7 as ORDER1");
        sb.append(" ,2 as ORDER2 ");
        sb.append("  ,'' AS 매출일자 ");
        sb.append(" ,'' as 전표번호 ");
        sb.append(" ,'' AS INPUT_SEQ ");
        sb.append(" ,'' AS TIME ");
        sb.append(" ,'총 납품 합계' AS CUST_NM");
        sb.append(" ,N.ITEM_NM AS RAW_MAT_NM ");
        sb.append(" ,N.SPEC ");
        sb.append(" ,I.LOT_NO");
        sb.append(" ,'' AS LOT_SUB");
        sb.append("  			,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.UNIT_CD = UNIT_CD ) AS UNIT_NM");
        sb.append(" ,CONVERT(nvarchar,SUM(S.TOTAL_AMT)) AS TOTAL_AMT ");
        sb.append(" ,'' AS LOSS_AMT ");
        sb.append(" ,'' AS POOR ");
        sb.append(" FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_OUT_DETAIL] I ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] N");
        sb.append(" on N.ITEM_CD = I.ITEM_CD ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_SALES_DETAIL] S ");
        sb.append(" on S.OUTPUT_DATE = I.OUTPUT_DATE ");
        sb.append(" and S.OUTPUT_CD = I.OUTPUT_CD");
        sb.append(" and S.OUTPUT_SEQ = I.SEQ ");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_SALES] SI");
        sb.append(" on SI.SALES_DATE = S.SALES_DATE ");
        sb.append(" and SI.SALES_CD = S.SALES_CD ");
        sb.append(" where I.LOT_NO = '" + condition + "' and I.OUT_LOT is null and S.OUTPUT_DATE is not null");
        sb.append(" group by I.LOT_NO, N.ITEM_NM, N.SPEC, N.UNIT_CD ");
        sb.append(" order by ORDER1,ORDER2, INPUT_DATE, TIME");

        Jarray = dbInfo.SelectDB(sb.toString());
        return Jarray;
    }
}

