package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.Adapter.WorkInstAdapter;
import com.Adapter.WorkInstHalfAdapter;
import com.Adapter.WorkInstRawAdapter;
import com.VO.WorkInstHalfVo;
import com.VO.WorkInstRawVo;
import com.VO.WorkInstVo;
import com.common.DBInfo;
import com.example.mes_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class work_viewActivity extends Fragment {

    InputMethodManager imm; //키보드 내리기
    DBInfo dbInfo;
    GridView gv_inst;
    JSONArray JArray;
    WorkInstVo workInstVo;
    MainActivity activity;
    ViewGroup rootView;
    TextView et_instDate;
    TextView et_instCd;
    TextView et_lotNo;
    TextView et_spec;
    TextView et_memo;
    TextView et_custNm;
    TextView et_delDate;
    TextView et_instAmt;
    TextView et_itemNm;
    TextView et_workNm;
    TextView et_lineNm;
    GridView gv_instDetail;

    WorkInstRawVo workInstRawVo;
    WorkInstHalfVo workInstHalfVo;


    public work_viewActivity() {

    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_work_view, container, false);
        gv_inst = rootView.findViewById(R.id.work_view_gv_workInst);

        et_custNm = rootView.findViewById(R.id.workView_et_custNm);
        et_delDate = rootView.findViewById(R.id.workView_et_delDate);
        et_instAmt = rootView.findViewById(R.id.workView_et_instAmt);
        et_instCd = rootView.findViewById(R.id.workView_et_instCd);
        et_itemNm = rootView.findViewById(R.id.workView_et_itemNm);
        et_memo = rootView.findViewById(R.id.workView_et_memo);
        et_spec = rootView.findViewById(R.id.workView_et_spec);
        et_lineNm = rootView.findViewById(R.id.workView_et_lineNm);
        et_lotNo = rootView.findViewById(R.id.workView_et_lotNo);
        et_workNm = rootView.findViewById(R.id.workView_et_workNm);
        et_instDate = rootView.findViewById(R.id.workView_et_instDate);


        gv_instDetail = rootView.findViewById(R.id.workInstRaw_gv_detail);
        dbInfo = new DBInfo();
        datebinding();
        return rootView;
    }

    public void datebinding() {
        try {
            JArray = null;
            JArray = work_inst(JArray, "");

            if (JArray.length() != 0) {
                final WorkInstAdapter workInstAdapter = new WorkInstAdapter();

                for (int i = 0; i < JArray.length(); i++) {
                    JSONObject jo = JArray.getJSONObject(i);

                    String lotNo = jo.getString("LOT_NO");
                    String itemNm = jo.getString("ITEM_NM");
                    String custNm = jo.getString("CUST_NM");
                    String instAmt = jo.getString("INST_AMT");

                    workInstVo = new WorkInstVo(lotNo, custNm, itemNm, instAmt);

                    workInstAdapter.addItem(workInstVo);

                }

                if (workInstAdapter.getCount() == 0) {
                    Toast.makeText(activity, "오류 발생", Toast.LENGTH_SHORT).show();
                }


                gv_inst.setAdapter(workInstAdapter);


                gv_inst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        try {
                            Toast.makeText(getContext(), "LOT_No:" + workInstAdapter.arrayList.get(position).getLotNO().toString(), Toast.LENGTH_SHORT).show();
                            datebind2(workInstAdapter.arrayList.get(position).getLotNO().toString());
                            datebind3(workInstAdapter.arrayList.get(position).getLotNO().toString());

                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }


                    }
                });
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public JSONArray work_inst(JSONArray JSONArray, String condition) throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();
        query.append("select A.W_INST_DATE \n");
        query.append(",A.W_INST_CD \n");
        query.append(" ,isnull(A.LOT_NO,'') as LOT_NO  \n");
        query.append(",isnull(B.ITEM_NM,'') as ITEM_NM \n");
        query.append(",B.ITEM_GUBUN \n");
        query.append(" ,B.SPEC  \n");
        query.append(" ,convert(int,isnull(A.INST_AMT,0)) as INST_AMT\n");
        query.append(",A.CHARGE_AMT  \n");
        query.append(",A.PACK_AMT  \n");
        query.append(" ,A.PLAN_NUM  \n");
        query.append(" ,A.PLAN_ITEM  \n");
        query.append(" ,A.INSTAFF  \n");
        query.append("  ,A.INST_NOTICE   \n");
        query.append(" ,A.CUST_CD  \n");

        query.append(" ,isnull('0',B.BAL_STOCK) as  BAL_STOCK ,isnull(D.CUST_NM,'') as CUST_NM   ,A.DELIVERY_DATE    ,C.COMPLETE_YN  as COMPLETE  ,CASE WHEN ISNULL(C.COMPLETE_YN,'N')='Y' THEN '완료' ELSE ( CASE WHEN ISNULL(C.COMPLETE_YN,'N')='S' THEN '진행중' ELSE '미완료' END ) END COMPLETE_YN     \n");


        query.append("from [" + dbInfo.Location + "].[dbo].[F_WORK_INST] as A ");
        query.append("left outer join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] as B ");
        query.append(" ON A.ITEM_CD = B.ITEM_CD  ");

        query.append("left outer join [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW] as C  ");
        query.append(" ON A.LOT_NO = C.LOT_NO ");
        query.append("left outer join [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] as D on D.CUST_CD=A.CUST_CD   ");


        query.append("where 1=1  \n");
        query.append("  order by A.W_INST_DATE desc, A.W_INST_CD desc  \n");
        query.append("\n");
        JSONArray = dbInfo.SelectDB(query.toString());
        System.out.println(query.toString());
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


    public JSONArray work_inst2(JSONArray JSONArray, String condition) throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();

        query.append("select \n");
        query.append("A.W_INST_DATE \n");
        query.append(",A.W_INST_CD \n");
        query.append(",A.LOT_NO \n");
        query.append(",A.ITEM_CD \n");
        query.append(",isnull(B.ITEM_NM,'') as ITEM_NM \n");
        query.append(",A.CUST_CD \n");
        query.append(",isnull(C.CUST_NM,'') as CUST_NM \n");
        query.append(",isnull(A.INST_AMT,0) as INST_AMT \n");
        query.append(",A.WORKER_CD \n");
        query.append(",isnull(D.STAFF_NM,'') as WORKDER_NM \n");
        query.append(",isnull(F.LINE_NM,'') as LINE_NM \n");
        query.append(",A.DELIVERY_DATE \n");
        query.append("from [" + dbInfo.Location + "].[dbo].[F_WORK_INST] as A  \n");
        query.append("left join [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] as c on C.CUST_CD=A.CUST_CD \n");
        query.append("left join [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] as B on B.ITEM_CD=A.ITEM_CD \n");
        query.append("left join [" + dbInfo.Location + "].[dbo].[N_STAFF_CODE] as D on D.STAFF_CD=A.WORKER_CD \n");
        query.append("left join [" + dbInfo.Location + "].[dbo].[N_LINE_CODE] as F on F.LINE_CD= A.LINE_CD  \n");
        query.append("where 1=1 \n");
        query.append("and A.LOT_NO='" + condition + "'\n");

        query.append("\n");
        JSONArray = dbInfo.SelectDB(query.toString());
        System.out.println(query.toString());
        return JSONArray;
    }

    public void datebind2(String LotNo) {
        try {
            JArray = null;
            JArray = work_inst2(JArray, LotNo);

            if (JArray.length() != 0) {


                for (int i = 0; i < JArray.length(); i++) {
                    JSONObject jo = JArray.getJSONObject(i);

                    String W_INST_DATE = jo.getString("W_INST_DATE");
                    String W_INST_CD = jo.getString("W_INST_CD");
                    String LOT_NO = jo.getString("LOT_NO");
                    String ITEM_CD = jo.getString("ITEM_CD");
                    String ITEM_NM = jo.getString("ITEM_NM");
                    String CUST_CD = jo.getString("CUST_CD");
                    String CUST_NM = jo.getString("CUST_NM");
                    String INST_AMT = jo.getString("INST_AMT");
                    String WORKER_CD = jo.getString("WORKER_CD");
                    String WORKDER_NM = jo.getString("WORKDER_NM");
                    String LINE_NM = jo.getString("LINE_NM");
                    String DELIVERY_DATE = jo.getString("DELIVERY_DATE");

                    et_instDate.setText(W_INST_DATE);
                    et_instCd.setText(W_INST_CD);
                    et_lotNo.setText(LOT_NO);
                    et_itemNm.setText(ITEM_NM);
                    et_custNm.setText(CUST_CD);
                    et_instAmt.setText(INST_AMT);
                    et_workNm.setText(WORKDER_NM);
                    et_lineNm.setText(LINE_NM);
                    et_delDate.setText(DELIVERY_DATE);


                }

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
//원부재료 바인딩
    public void datebind3(String lotNo) {
        try {

            JArray = null;
            JArray = work_inst_detail(JArray, "and A.LOT_NO ='" + lotNo + "'");

            if (JArray.length() != 0) {

                WorkInstRawAdapter workInstRawAdapter = new WorkInstRawAdapter();
                for (int i = 0; i < JArray.length(); i++) {
                    JSONObject jo = JArray.getJSONObject(i);

                    String RAW_MAT_NM = "";
                    String CUST_NM = "";
                    String spec = "";
                    double SOYO_AMT = 0;
                    String OUTPUT_UNIT_NM = "";
                    double BAL_STOCK = 0;
                    String INPUT_UNIT_NM = "";

                    double bal_stock = 0;


                    double TOTAL_SOYO_AMT=0;
                    String CVR_RATIO="0";

                    if (jo.has("RAW_MAT_NM")) // Data값이 NULL인 경우 빈값으로 처리
                        RAW_MAT_NM = jo.getString("RAW_MAT_NM");
                    if (jo.has("CUST_NM"))
                        CUST_NM = jo.getString("CUST_NM");
                    if (jo.has("SPEC"))
                        spec = jo.getString("SPEC");
                    if (jo.has("SOYO_AMT"))
                        SOYO_AMT = Double.valueOf(jo.getString("SOYO_AMT"));
                    if (jo.has("OUTPUT_UNIT_NM"))
                        OUTPUT_UNIT_NM = jo.getString("OUTPUT_UNIT_NM");
                    if (jo.has("BAL_STOCK"))
                        BAL_STOCK = Double.valueOf(jo.getString("BAL_STOCK"));
                    if (jo.has("INPUT_UNIT_NM"))
                        INPUT_UNIT_NM = jo.getString("INPUT_UNIT_NM");
                    if (jo.has("TOTAL_SOYO_AMT"))
                        TOTAL_SOYO_AMT = Double.valueOf(jo.getString("TOTAL_SOYO_AMT"));

                    if (jo.has("CVR_RATIO"))
                        CVR_RATIO = jo.getString("CVR_RATIO");

                    Double rs_ex_stock=Double.valueOf(bal_stock)-(Double.valueOf(TOTAL_SOYO_AMT)*Double.valueOf(CVR_RATIO));


                    workInstRawVo = new WorkInstRawVo(CUST_NM,RAW_MAT_NM,spec,Double.toString(SOYO_AMT),Double.toString(TOTAL_SOYO_AMT),OUTPUT_UNIT_NM,Double.toString(BAL_STOCK),rs_ex_stock.toString(),INPUT_UNIT_NM);
                    workInstRawAdapter.addItem(workInstRawVo);
                }
                gv_instDetail.setAdapter(workInstRawAdapter);
            }

        } catch (Exception ex) {
            System.out.println("어댑터"+ex.toString());
        }
    }


    public JSONArray work_inst_detail(JSONArray JSONArray, String condition ) throws SQLException, JSONException {
        StringBuilder sb = new StringBuilder();
        sb.append(" select A.W_INST_DATE\n");
        sb.append(",A.W_INST_CD \n" +
                "                 ,A.SEQ \n" +
                "                 ,A.LOT_NO \n" +
                "                 ,A.RAW_MAT_CD  \n" +
                "                 ,B.RAW_MAT_NM   \n" +
                "                 ,B.SPEC   \n" +
                "                 ,B.CUST_CD \n" +
                "                 ,C.CUST_NM \n" +
                "                 ,B.INPUT_UNIT \n" +
                "                 ,(select UNIT_NM from [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] where UNIT_CD = B.INPUT_UNIT) as INPUT_UNIT_NM  \n" +
                "                 ,B.OUTPUT_UNIT\n" +
                "                 ,(select UNIT_NM from [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] where UNIT_CD = B.OUTPUT_UNIT) as OUTPUT_UNIT_NM   \n" +
                "                 ,A.SOYO_AMT  \n" +
                "                 ,A.TOTAL_AMT as TOTAL_SOYO_AMT \n" +
                "                 ,ISNULL(B.BAL_STOCK,0) AS BAL_STOCK  \n" +
                "                 ,B.CVR_RATIO  \n" +
                "                  \n");
        sb.append("from [" + dbInfo.Location + "].[dbo].[F_WORK_INST_DETAIL] A \n" +
                "             left outer join  [" + dbInfo.Location + "].[dbo].[N_RAW_CODE] B\n" +
                "             on A.RAW_MAT_CD = B.RAW_MAT_CD\n" +
                "             left outer join  [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] C  \n" +
                "             on B.CUST_CD = C.CUST_CD\t \n" +
                "             inner join  [" + dbInfo.Location + "].[dbo].[F_WORK_INST] F \n" +
                "             on A.W_INST_DATE = F.W_INST_DATE \n" +
                "                 and A.W_INST_CD = F.W_INST_CD \n" +
                "      where 1=1     \n" + condition +
                "             order by A.SEQ \n");
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");





        JSONArray = dbInfo.SelectDB(sb.toString());
        System.out.println(sb.toString());
        return JSONArray;
    }


    //반재품 바인딩
    public void datebind4(String lotNo) {
        try {

            JArray = null;
            JArray = work_inst_half(JArray, "and A.LOT_NO ='" + lotNo + "'");

            if (JArray.length() != 0) {

                WorkInstHalfAdapter workInstHalfAdapte = new WorkInstHalfAdapter();
                for (int i = 0; i < JArray.length(); i++) {
                    JSONObject jo = JArray.getJSONObject(i);

                    String ITEM_NM = "";
                    String CUST_NM = "";
                    String spec = "";
                    String SOYO_AMT = "";
                    String OUTPUT_UNIT_NM = "";
                    String BAL_STOCK = "0" +
                            "";
                    String INPUT_UNIT_NM = "";

                    String bal_stock = "0";


                    String TOTAL_SOYO_AMT="0";
                    String CVR_RATIO="0";

                    if (jo.has("ITEM_NM")) // Data값이 NULL인 경우 빈값으로 처리
                        ITEM_NM = jo.getString("ITEM_NM");
                    if (jo.has("CUST_NM"))
                        CUST_NM = jo.getString("CUST_NM");
                    if (jo.has("SPEC"))
                        spec = jo.getString("SPEC");
                    if (jo.has("SOYO_AMT"))
                        SOYO_AMT = jo.getString("SOYO_AMT");
                    if (jo.has("OUTPUT_UNIT_NM"))
                        OUTPUT_UNIT_NM = jo.getString("UNIT_NM");
                    if (jo.has("BAL_STOCK"))
                        BAL_STOCK = jo.getString("BAL_STOCK");
                    if (jo.has("INPUT_UNIT_NM"))
                        INPUT_UNIT_NM = jo.getString("UNIT_NM");
                    if (jo.has("TOTAL_SOYO_AMT"))
                        TOTAL_SOYO_AMT = jo.getString("TOTAL_SOYO_AMT");





                    Double rs_ex_stock=Double.valueOf(bal_stock)-(Double.valueOf(TOTAL_SOYO_AMT));
                    workInstHalfVo = new WorkInstHalfVo(CUST_NM,ITEM_NM,spec,SOYO_AMT,TOTAL_SOYO_AMT,OUTPUT_UNIT_NM,BAL_STOCK,rs_ex_stock.toString(),INPUT_UNIT_NM);
                    workInstHalfAdapte.addItem(workInstHalfVo);
                }
                gv_instDetail.setAdapter(workInstHalfAdapte);
            }

        } catch (Exception ex) {
            System.out.println("어댑터"+ex.toString());
        }
    }


    public JSONArray work_inst_half(JSONArray JSONArray, String condition ) throws SQLException, JSONException {
        StringBuilder sb = new StringBuilder();

        sb.append(" select A.W_INST_DATE\n" +
                "                 ,A.W_INST_CD\n" +
                "                 ,A.SEQ\n" +
                "                 ,A.LOT_NO\n" +
                "                 ,A.HALF_ITEM_CD \n" +
                "                 ,B.ITEM_NM  \n" +
                "                 ,B.SPEC  \n" +
                "                 ,B.CUST_CD\n" +
                "                 ,C.CUST_NM\n" +
                "                 ,B.UNIT_CD\n" +
                "                 ,(select UNIT_NM from N_UNIT_CODE where UNIT_CD = B.UNIT_CD) as UNIT_NM  \n" +
                "                 ,A.SOYO_AMT \n" +
                "                 ,A.TOTAL_AMT as TOTAL_SOYO_AMT\n" +
                "                 ,ISNULL(B.BAL_STOCK,0) AS BAL_STOCK  \n" +
                "             from F_WORK_INST_HALF_DETAIL A \n" +
                "             left outer join N_ITEM_CODE B\n" +
                "             on A.HALF_ITEM_CD = B.ITEM_CD\n" +
                "                 and B.ITEM_GUBUN = '2'\n" +
                "             left outer join N_CUST_CODE C \n" +
                "             on B.CUST_CD = C.CUST_CD\t\n");

        sb.append("where 1=1");
        sb.append(condition);






        JSONArray = dbInfo.SelectDB(sb.toString());
        System.out.println(sb.toString());
        return JSONArray;
    }
}
