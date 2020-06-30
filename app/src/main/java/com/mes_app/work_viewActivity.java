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
        query.append(",F.LINE_NM \n");
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

    public void datebind3(int instAmt, String item_cd) {
        try {

            JArray = null;
            JArray = work_inst_detail(JArray, "and A.ITEM_CD ='" + item_cd + "'", instAmt);

            if (JArray.length() != 0) {


                for (int i = 0; i < JArray.length(); i++) {
                    JSONObject jo = JArray.getJSONObject(i);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }


    public JSONArray work_inst_detail(JSONArray JSONArray, String condition, double inst_amt) throws SQLException, JSONException {
        StringBuilder sb = new StringBuilder();
        sb.append("select A.ITEM_CD \n");
        sb.append("     ,A.ITEM_NM  \n");
        sb.append("     ,C.RAW_MAT_CD  \n");
        sb.append("     ,C.RAW_MAT_NM  \n");
        sb.append("     ,C.SPEC   \n");
        sb.append("     ,C.CUST_CD     \n");
        sb.append("     ,D.CUST_NM  \n");
        sb.append("     ,C.INPUT_UNIT  \n");
        sb.append("     ,(select UNIT_NM from N_UNIT_CODE where UNIT_CD = C.INPUT_UNIT) as INPUT_UNIT_NM  \n ");
        sb.append("     ,C.OUTPUT_UNIT  \n");
        sb.append("     ,(select UNIT_NM from N_UNIT_CODE where UNIT_CD = C.OUTPUT_UNIT) as OUTPUT_UNIT_NM   \n ");
        sb.append("     ,ISNULL(B.TOTAL_AMT,0) AS SOYO_AMT  \n");
        sb.append("     ,ISNULL(" + inst_amt + "*B.TOTAL_AMT,0) AS TOTAL_SOYO_AMT \n ");
        sb.append("     ,ISNULL(C.BAL_STOCK,0) AS BAL_STOCK  \n ");
        sb.append("     ,ISNULL(C.CVR_RATIO,0) AS CVR_RATIO   \n");
        sb.append("     ,Z.S_CODE_NM  \n "); //원자재 부자재 명
        sb.append("     ,Z.S_CODE \n "); //원자재, 부자재  코드
        sb.append(" FROM N_ITEM_CODE A  \n");
        sb.append(" inner join N_ITEM_COMP B  \n");
        sb.append(" on A.ITEM_CD = B.ITEM_CD  \n");
        sb.append(" left outer join N_RAW_CODE C   \n ");
        sb.append(" on B.RAW_MAT_CD = C.RAW_MAT_CD	  \n");
        sb.append(" left outer join N_CUST_CODE D	  \n");
        sb.append(" on C.CUST_CD = D.CUST_CD	  \n");
        sb.append("inner join T_S_CODE AS Z on c.RAW_MAT_GUBUN=z.S_CODE and z.L_CODE='300' 	 \n ");  //300번 :원자재 부자재 구분
        sb.append("where 1=1");

        sb.append(condition);

        sb.append(" order by S_CODE	 ");
        JSONArray = dbInfo.SelectDB(sb.toString());
        System.out.println(sb.toString());
        return JSONArray;
    }
}
