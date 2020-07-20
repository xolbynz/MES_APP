package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
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
    TextView CompleteYn;


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
        gridView = rootView.findViewById(R.id.itemTracking_gv_grid);
        CompleteYn = rootView.findViewById(R.id.itemTracking_CompleteYn);


        Barcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                int length = Barcode.getText().toString().length();
                String lot = Barcode.getText().toString();

                if (length == 10 && !lot.equals("")) {
                    imm.hideSoftInputFromWindow(Barcode.getWindowToken(),0);
                    Lot_no = Barcode.getText().toString();
                    databind();
                } else if (length > 10 && !lot.equals(""))   {
                    Toast.makeText(context, "잘못된 LOT 번호 입니다.", Toast.LENGTH_LONG).show();
                }else{

                }
            }
        });

        btn_search.setOnClickListener(onClickListener);
        gridView.setOnItemClickListener(hideKeyboard1);
        rootView.setOnClickListener(hideKeyboard2);

        Barcode.findFocus();

        return rootView;
    }

    Button.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            imm.hideSoftInputFromWindow(Barcode.getWindowToken(), 0);
            if (Barcode.getText().length() != 10 || Barcode.getText().equals("")) {
                Toast.makeText(context, "잘못된 LOT 번호 입니다.", Toast.LENGTH_LONG).show();
                gridView.setAdapter(null);
            } else {
                Lot_no = Barcode.getText().toString();
                databind();
            }
        }
    };

    GridView.OnItemClickListener hideKeyboard1 = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            imm.hideSoftInputFromWindow(gridView.getWindowToken(), 0);
        }
    };
    View.OnClickListener hideKeyboard2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            imm.hideSoftInputFromWindow(gridView.getWindowToken(), 0);
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
                        CompleteYn.setText("완료");
                    } else {
                        CompleteYn.setText("미   완료");
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
                        if (jo.has("TIME"))
                            intime = jo.getString("TIME");
                        if (jo.has("CUST_NM"))
                            cust_nm = jo.getString("CUST_NM");
                        if (jo.has("RAW_MAT_NM"))
                            raw_mat_nm = jo.getString("RAW_MAT_NM");
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
                                input_cd, intime, cust_nm, raw_mat_nm,
                                spec, lot_no, lot_sub, unit_nm,
                                total_amt, loss_amt, poor);

                        itemTraceAdapter.addItem(traceListDetailVo);
                    }
                    gridView.setAdapter(itemTraceAdapter);
                }

            } else if (jsonArray.length() > 1) {
                gridView.setAdapter(null);
                Toast.makeText(context, "하나 이상의 LOT번호가 존재합니다 < 오류 >", Toast.LENGTH_SHORT).show();
            } else {
                gridView.setAdapter(null);
                Toast.makeText(context, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException | JSONException e) {
            gridView.setAdapter(null);
            Toast.makeText(context, "검색 오류", Toast.LENGTH_SHORT).show();
            System.out.println(e.toString());
            e.printStackTrace();

        } catch (Exception ex) {
            gridView.setAdapter(null);
            Toast.makeText(context, "시스템 오류", Toast.LENGTH_SHORT).show();
            System.out.println(ex.toString());
        }
    }

    private JSONArray fn_flow_trace_list_info_By_Lot(JSONArray Jarray, String condition) throws SQLException, JSONException {


        StringBuilder sb = new StringBuilder();

        sb.append("select  \n");
        sb.append("         I.INPUT_DATE  \n");
        sb.append("         ,I.INPUT_CD  \n");
        sb.append("         ,I.LOT_NO  \n");
        sb.append("         ,W.W_INST_DATE  \n");
        sb.append("         ,W.W_INST_CD  \n");
        sb.append("         ,W.DELIVERY_DATE  \n");
        sb.append("         ,W.INST_NOTICE  \n");
        sb.append("         ,F.COMPLETE_YN  \n");
        sb.append("         ,W.ITEM_CD AS RAW_MAT_CD  \n");
        sb.append("         ,W.INST_AMT  \n");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_INST] W  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] I  \n");
        sb.append(" on W.LOT_NO = I.LOT_NO  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW] F  \n");
        sb.append(" on F.LOT_NO = W.LOT_NO  \n");
        sb.append(" where W.LOT_NO = '" + condition + "'  \n");


        Jarray = dbInfo.SelectDB(sb.toString());
        return Jarray;
    }

    private JSONArray fn_flow_trace_list_By_LotNo(JSONArray Jarray, String condition) throws SQLException, JSONException {
        StringBuilder sb = new StringBuilder();

        sb.append(" select \n");
        sb.append(" '지시' AS GUBUN \n");
        sb.append(" ,1 as ORDER1 \n");
        sb.append(" ,1 as ORDER2 \n");
        sb.append(" ,W.W_INST_DATE AS INPUT_DATE  \n");
        sb.append(" ,CONVERT(nvarchar,W.W_INST_CD) AS INPUT_CD \n");
        sb.append(" ,'' AS INPUT_SEQ  \n");
        sb.append(" ,W.INTIME AS TIME  \n");
        sb.append(" ,(SELECT CUST_NM FROM [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] WHERE W.CUST_CD = CUST_CD) AS CUST_NM \n");
        sb.append(" ,N.ITEM_NM AS RAW_MAT_NM  \n");
        sb.append(" ,N.SPEC \n");
        sb.append(" ,W.LOT_NO AS LOT_NO \n");
        sb.append(" ,'' AS LOT_SUB \n");
        sb.append(" ,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.UNIT_CD = UNIT_CD) AS UNIT_NM \n");
        sb.append(" ,CONVERT(nvarchar,W.INST_AMT) AS TOTAL_AMT \n");
        sb.append(" ,'' AS LOSS_AMT \n");
        sb.append(" ,'' AS POOR \n");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_INST] W \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] N  \n");
        sb.append(" on W.ITEM_CD = N.ITEM_CD  \n");
        sb.append(" where W.LOT_NO = '" + condition + "'  \n");
        sb.append("   \n");
        sb.append("  union all \n");
        sb.append(" select \n");
        sb.append(" '자재투입' AS GUBUN \n");
        sb.append(" ,2 as ORDER1 \n");
        sb.append(" ,1 as ORDER2 \n");
        sb.append(" ,ISNULL(D.INPUT_DATE,'음수 투입') AS INPUT_DATE \n");
        sb.append(" ,CONVERT(nvarchar,D.INPUT_CD ) \n");
        sb.append(" ,CONVERT(nvarchar,D.SEQ )  \n");
        sb.append(" ,W.INTIME AS TIME  \n");
        sb.append(" ,(SELECT CUST_NM FROM [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] WHERE RI.CUST_CD = CUST_CD ) AS CUST_NM \n");
        sb.append(" ,N.RAW_MAT_NM  \n");
        sb.append(" ,N.SPEC \n");
        sb.append(" ,'' AS LOT_NO  \n");
        sb.append(" ,'' AS LOT_SUB \n");
        sb.append(" ,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.OUTPUT_UNIT = UNIT_CD ) AS UNIT_NM  \n");
        sb.append(" ,CONVERT(nvarchar,O.TOTAL_AMT/I.SOYO_AMT)+'x'+CONVERT(nvarchar,I.SOYO_AMT)+'='+CONVERT(nvarchar,O.TOTAL_AMT) AS TOTAL_AMT  \n");
        sb.append(" ,'' AS LOSS_AMT  \n");
        sb.append(" ,'' AS POOR  \n");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_INST] W \n");
        sb.append(" inner join [" + dbInfo.Location + "].[dbo].[F_WORK_INST_DETAIL] I  \n");
        sb.append(" on W.W_INST_DATE = I.W_INST_DATE  \n");
        sb.append(" and W.W_INST_CD = I.W_INST_CD \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_RAW_OUTPUT] O \n");
        sb.append(" on I.LOT_NO = O.LOT_NO \n");
        sb.append(" and I.RAW_MAT_CD = O.RAW_MAT_CD  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_RAW_DETAIL] D \n");
        sb.append(" on D.INPUT_DATE = O.INPUT_DATE \n");
        sb.append(" and D.INPUT_CD = O.INPUT_CD  \n");
        sb.append(" and D.SEQ = O.INPUT_SEQ  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_RAW_INPUT] RI \n");
        sb.append(" on D.INPUT_DATE = RI.INPUT_DATE  \n");
        sb.append(" and D.INPUT_CD = RI.INPUT_CD  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_RAW_CODE] N  \n");
        sb.append(" on O.RAW_MAT_CD = N.RAW_MAT_CD \n");
        sb.append(" where O.LOT_NO = '" + condition + "' \n");
        sb.append("  union all \n");
        sb.append(" select \n");
        sb.append(" '반제품투입' AS GUBUN \n");
        sb.append(" ,3 as ORDER1 \n");
        sb.append(" ,1 as ORDER2  \n");
        sb.append(" ,ISNULL(D.INPUT_DATE,'음수 투입') AS INPUT_DATE  \n");
        sb.append(" ,CONVERT(nvarchar,D.INPUT_CD) \n");
        sb.append(" ,'' AS SEQ \n");
        sb.append(" ,W.INTIME AS TIME \n");
        sb.append(" ,'' AS CUST_NM \n");
        sb.append(" ,N.ITEM_NM AS RAW_MAT_NM  \n");
        sb.append(" ,N.SPEC  \n");
        sb.append(" ,O.LOT_NO AS LOT_NO  \n");
        sb.append(" ,CONVERT(nvarchar,O.LOT_SUB) AS LOT_SUB  \n");
        sb.append(" ,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.UNIT_CD = UNIT_CD ) AS UNIT_NM  \n");
        sb.append(" ,CONVERT(nvarchar,O.OUTPUT_AMT/I.SOYO_AMT)+'x'+CONVERT(nvarchar,I.SOYO_AMT)+'='+CONVERT(nvarchar,O.OUTPUT_AMT) AS TOTAL_AMT  \n");
        sb.append(" ,'' AS LOSS_AMT  \n");
        sb.append(" ,'' AS POOR  \n");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_INST] W \n");
        sb.append(" inner join [" + dbInfo.Location + "].[dbo].[F_WORK_INST_HALF_DETAIL] I  \n");
        sb.append(" on W.W_INST_DATE = I.W_INST_DATE  \n");
        sb.append(" and W.W_INST_CD = I.W_INST_CD \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_ITEM_OUT_DETAIL] O  \n");
        sb.append(" on I.LOT_NO = O.OUT_LOT  \n");
        sb.append(" and I.HALF_ITEM_CD = O.ITEM_CD \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] D \n");
        sb.append(" on D.INPUT_DATE = O.INPUT_DATE \n");
        sb.append(" and D.INPUT_CD = O.INPUT_CD  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] N \n");
        sb.append(" on O.ITEM_CD = N.ITEM_CD  \n");
        sb.append(" where O.OUT_LOT = '" + condition + "' \n");
        sb.append("   \n");
        sb.append(" union all \n");
        sb.append("  select  \n");
        sb.append(" '공정' AS GUBUN  \n");
        sb.append(" ,4 as ORDER1 \n");
        sb.append(" ,W.F_STEP as ORDER2 \n");
        sb.append(" ,W.F_SUB_DATE AS INPUT_DATE  \n");
        sb.append(" ,CONVERT(nvarchar,W.SEQ) AS INPUT_CD  \n");
        sb.append(" ,'' AS SEQ \n");
        sb.append(" ,W.INTIME AS TIME \n");
        sb.append(" ,CONVERT(nvarchar,W.LOT_SUB)+' ('+CONVERT(nvarchar,F.FLOW_NM)+')' AS CUST_NM  \n");
        sb.append(" ,(SELECT FLOW_NM FROM [" + dbInfo.Location + "].[dbo].[N_FLOW_CODE] WHERE FLOW_CD = W.FLOW_CD ) AS RAW_MAT_NM  \n");
        sb.append(" ,''  \n");
        sb.append(" ,W.LOT_NO \n");
        sb.append(" ,CONVERT(nvarchar,W.LOT_SUB) AS LOT_SUB  \n");
        sb.append(" ,'' AS UNIT_NM \n");
        sb.append(" ,CONVERT(nvarchar,W.F_SUB_AMT)+'/'+CONVERT(nvarchar,W.INPUT_AMT) AS TOTAL_AMT \n");
        sb.append(" ,CONVERT(nvarchar,W.LOSS) AS LOSS_AMT \n");
        sb.append(" ,CONVERT(nvarchar,W.POOR_AMT) AS POOR \n");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW_DETAIL] W \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_FLOW_CODE] F \n");
        sb.append(" on W.FLOW_CD = F.FLOW_CD  \n");
        sb.append(" where W.LOT_NO = '" + condition + "' \n");
        sb.append("   \n");
        sb.append(" union all \n");
        sb.append("   \n");
        sb.append("  select  \n");
        sb.append(" '공정' AS GUBUN  \n");
        sb.append(" ,4 as ORDER1 \n");
        sb.append(" ,W.F_STEP+0.1 as ORDER2 \n");
        sb.append(" ,'' AS INPUT_DATE \n");
        sb.append(" ,'' AS INPUT_CD  \n");
        sb.append(" ,'' AS SEQ \n");
        sb.append(" ,'' AS TIME  \n");
        sb.append(" ,''+CONVERT(nvarchar,F.FLOW_NM)+' 합계' AS CUST_NM \n");
        sb.append(" ,(SELECT FLOW_NM FROM [" + dbInfo.Location + "].[dbo].[N_FLOW_CODE] WHERE FLOW_CD = F.FLOW_CD ) AS RAW_MAT_NM  \n");
        sb.append(" ,''  \n");
        sb.append(" ,W.LOT_NO \n");
        sb.append(" ,'' AS LOT_SUB \n");
        sb.append(" ,'' AS UNIT_NM \n");
        sb.append(" ,CONVERT(nvarchar,SUM(W.F_SUB_AMT))+'/'+CONVERT(nvarchar,SUM(W.INPUT_AMT)) AS TOTAL_AMT  \n");
        sb.append(" ,CONVERT(nvarchar,SUM(W.LOSS)) AS LOSS_AMT \n");
        sb.append(" ,CONVERT(nvarchar,SUM(W.POOR_AMT)) AS POOR \n");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW_DETAIL] W \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_FLOW_CODE] F \n");
        sb.append(" on W.FLOW_CD = F.FLOW_CD  \n");
        sb.append(" where W.LOT_NO = '" + condition + "' \n");
        sb.append(" group by F.FLOW_CD,W.F_STEP,F.FLOW_NM,LOT_NO  \n");
        sb.append("   \n");
        sb.append(" union all \n");
        sb.append("   \n");
        sb.append("   \n");
        sb.append("  select  \n");
        sb.append(" '공정' AS GUBUN  \n");
        sb.append(" ,4 as ORDER1 \n");
        sb.append(" ,99 as ORDER2  \n");
        sb.append(" ,'' AS INPUT_DATE \n");
        sb.append(" ,'' AS INPUT_CD  \n");
        sb.append(" ,'' AS SEQ \n");
        sb.append(" ,'' AS TIME  \n");
        sb.append(" ,'총 공정 합계' AS CUST_NM \n");
        sb.append(" ,'' AS RAW_MAT_NM \n");
        sb.append(" ,'' AS TEST  \n");
        sb.append(" ,K.LOT_NO AS LOT_NO  \n");
        sb.append(" ,'' AS LOT_SUB \n");
        sb.append(" ,'' AS UNIT_NM \n");
        sb.append(" ,CONVERT(nvarchar,SUM(K.INPUT_AMT))+'/'+CONVERT(nvarchar,SUM(K.INST_AMT)) AS TOTAL_AMT \n");
        sb.append(" ,CONVERT(nvarchar,SUM(K.LOSS)) AS LOSS \n");
        sb.append(" ,CONVERT(nvarchar,SUM(K.POOR_AMT)) AS POOR_AMT \n");
        sb.append(" from ( \n");
        sb.append("  select  \n");
        sb.append(" A.LOT_NO AS LOT_NO \n");
        sb.append(" ,AVG(B.INST_AMT) AS INST_AMT  \n");
        sb.append(" ,0 AS INPUT_AMT  \n");
        sb.append(" 		,SUM(A.LOSS) AS LOSS \n");
        sb.append(" 		,SUM(A.POOR_AMT) AS POOR_AMT \n");
        sb.append(" 	 FROM [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW_DETAIL] A  \n");
        sb.append(" 	 left outer join [" + dbInfo.Location + "].[dbo].[F_WORK_INST] B  \n");
        sb.append(" 	 on A.LOT_NO = B.LOT_NO  \n");
        sb.append(" 	 where A.LOT_NO = '" + condition + "'  \n");
        sb.append(" 	 group by A.LOT_NO  \n");
        sb.append("   \n");
        sb.append("  union all \n");
        sb.append("   \n");
        sb.append("  select  \n");
        sb.append(" A.LOT_NO AS LOT_NO \n");
        sb.append(" ,0 AS INST_AMT \n");
        sb.append(" ,SUM(A.INPUT_AMT) AS INPUT_AMT \n");
        sb.append(" ,0 AS LOSS \n");
        sb.append(" ,0 AS POOR_AMT \n");
        sb.append("  FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] A  \n");
        sb.append("  where A.LOT_NO = '" + condition + "' \n");
        sb.append("  group by A.LOT_NO \n");
        sb.append("   \n");
        sb.append("  ) AS K  \n");
        sb.append("  where K.LOT_NO = '" + condition + "' \n");
        sb.append("  GROUP BY K.LOT_NO \n");
        sb.append("   \n");
        sb.append("  union all \n");
        sb.append(" select \n");
        sb.append(" '생산' AS GUBUN  \n");
        sb.append(" ,5 as ORDER1 \n");
        sb.append(" ,1 as ORDER2  \n");
        sb.append(" ,I.INPUT_DATE \n");
        sb.append(" ,CONVERT(nvarchar,I.INPUT_CD) \n");
        sb.append(" ,'' AS INPUT_SEQ  \n");
        sb.append(" ,I.INTIME AS TIME \n");
        sb.append(" ,'' AS CUST_NM \n");
        sb.append(" ,N.ITEM_NM AS RAW_MAT_NM  \n");
        sb.append(" ,N.SPEC  \n");
        sb.append(" ,I.LOT_NO \n");
        sb.append(" ,convert(nvarchar,I.LOT_SUB)  \n");
        sb.append(" ,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.UNIT_CD = UNIT_CD ) AS UNIT_NM  \n");
        sb.append(" ,CONVERT(nvarchar,I.INPUT_AMT) AS TOTAL_AMT  \n");
        sb.append(" ,'' AS LOSS_AMT  \n");
        sb.append(" ,'' AS POOR  \n");
        sb.append(" FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] I  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] N \n");
        sb.append(" on N.ITEM_CD = I.ITEM_CD  \n");
        sb.append(" where I.LOT_NO = '" + condition + "' \n");
        sb.append("   \n");
        sb.append(" union all \n");
        sb.append(" select \n");
        sb.append(" '생산' AS GUBUN  \n");
        sb.append(" ,5 as ORDER1 \n");
        sb.append(" ,2 as ORDER2  \n");
        sb.append(" ,'' AS INPUT_DATE \n");
        sb.append(" ,'' AS INPUT_CD  \n");
        sb.append(" ,'' AS INPUT_SEQ  \n");
        sb.append(" ,'' AS TIME  \n");
        sb.append(" ,'총 생산 합계' AS CUST_NM \n");
        sb.append(" ,N.ITEM_NM AS RAW_MAT_NM  \n");
        sb.append(" ,N.SPEC  \n");
        sb.append(" ,I.LOT_NO \n");
        sb.append(" ,'' AS LOT_SUB \n");
        sb.append(" ,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.UNIT_CD = UNIT_CD ) AS UNIT_NM  \n");
        sb.append(" ,CONVERT(nvarchar,SUM(I.INPUT_AMT)) AS TOTAL_AMT  \n");
        sb.append(" ,'' AS LOSS_AMT  \n");
        sb.append(" ,'' AS POOR  \n");
        sb.append(" FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] I  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] N \n");
        sb.append(" on N.ITEM_CD = I.ITEM_CD  \n");
        sb.append(" where I.LOT_NO = '" + condition + "' \n");
        sb.append(" group by I.LOT_NO , N.ITEM_NM , N.SPEC , N.UNIT_CD \n");
        sb.append("   \n");
        sb.append(" union all \n");
        sb.append(" select \n");
        sb.append(" '납품' AS GUBUN  \n");
        sb.append(" ,7 as ORDER1 \n");
        sb.append(" ,1 as ORDER2  \n");
        sb.append("  ,S.SALES_DATE AS 매출일자 \n");
        sb.append(" ,CONVERT(nvarchar,S.SALES_CD) \n");
        sb.append(" ,CONVERT(nvarchar,S.SEQ) AS INPUT_SEQ \n");
        sb.append(" ,SI.INTIME AS TIME \n");
        sb.append(" ,(SELECT CUST_NM FROM [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] where CUST_CD = SI.CUST_CD ) AS CUST_NM \n");
        sb.append(" ,N.ITEM_NM AS RAW_MAT_NM  \n");
        sb.append(" ,N.SPEC  \n");
        sb.append(" ,I.LOT_NO \n");
        sb.append(" ,convert(nvarchar,I.LOT_SUB)  \n");
        sb.append(" ,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.UNIT_CD = UNIT_CD ) AS UNIT_NM  \n");
        sb.append(" ,CONVERT(nvarchar,S.TOTAL_AMT) AS TOTAL_AMT  \n");
        sb.append(" ,'' AS LOSS_AMT  \n");
        sb.append(" ,'' AS POOR  \n");
        sb.append(" FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_OUT_DETAIL] I  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] N \n");
        sb.append(" on N.ITEM_CD = I.ITEM_CD  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_SALES_DETAIL] S  \n");
        sb.append(" on S.OUTPUT_DATE = I.OUTPUT_DATE  \n");
        sb.append(" and S.OUTPUT_CD = I.OUTPUT_CD \n");
        sb.append(" and S.OUTPUT_SEQ = I.SEQ  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_SALES] SI \n");
        sb.append(" on SI.SALES_DATE = S.SALES_DATE  \n");
        sb.append(" and SI.SALES_CD = S.SALES_CD  \n");
        sb.append(" where I.LOT_NO = '" + condition + "' and I.OUT_LOT is null and S.OUTPUT_DATE is not null \n");
        sb.append("   \n");
        sb.append(" union all  \n");
        sb.append("   \n");
        sb.append(" select \n");
        sb.append(" '납품' AS GUBUN  \n");
        sb.append(" ,7 as ORDER1 \n");
        sb.append(" ,2 as ORDER2  \n");
        sb.append("  ,'' AS 매출일자  \n");
        sb.append(" ,'' as 전표번호  \n");
        sb.append(" ,'' AS INPUT_SEQ  \n");
        sb.append(" ,'' AS TIME  \n");
        sb.append(" ,'총 납품 합계' AS CUST_NM \n");
        sb.append(" ,N.ITEM_NM AS RAW_MAT_NM  \n");
        sb.append(" ,N.SPEC  \n");
        sb.append(" ,I.LOT_NO \n");
        sb.append(" ,'' AS LOT_SUB \n");
        sb.append("  			,(select UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE N.UNIT_CD = UNIT_CD ) AS UNIT_NM \n");
        sb.append(" ,CONVERT(nvarchar,SUM(S.TOTAL_AMT)) AS TOTAL_AMT  \n");
        sb.append(" ,'' AS LOSS_AMT  \n");
        sb.append(" ,'' AS POOR  \n");
        sb.append(" FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_OUT_DETAIL] I  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] N \n");
        sb.append(" on N.ITEM_CD = I.ITEM_CD  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_SALES_DETAIL] S  \n");
        sb.append(" on S.OUTPUT_DATE = I.OUTPUT_DATE  \n");
        sb.append(" and S.OUTPUT_CD = I.OUTPUT_CD \n");
        sb.append(" and S.OUTPUT_SEQ = I.SEQ  \n");
        sb.append(" left outer join [" + dbInfo.Location + "].[dbo].[F_SALES] SI \n");
        sb.append(" on SI.SALES_DATE = S.SALES_DATE  \n");
        sb.append(" and SI.SALES_CD = S.SALES_CD  \n");
        sb.append(" where I.LOT_NO = '" + condition + "' and I.OUT_LOT is null and S.OUTPUT_DATE is not null \n");
        sb.append(" group by I.LOT_NO, N.ITEM_NM, N.SPEC, N.UNIT_CD  \n");
        sb.append(" order by ORDER1,ORDER2, INPUT_DATE, TIME \n");

        Jarray = dbInfo.SelectDB(sb.toString());
        return Jarray;
    }
}

