package com.mes_app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.Adapter.WorkListAdapter;
import com.VO.WorkListVo;
import com.common.DBInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class pop_workList extends Dialog {

    Context context;
    GridView gridView;
    DBInfo dbInfo;
    JSONArray JArray;
    MainActivity activity;
    WorkListVo workListVo;

    pop_workList m_oDialog;
    ArrayList<WorkListVo> arrayList= new ArrayList<>();


    public pop_workList(Context context)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        datebind();
    }

    public  void datebind(
)
{
    try {
        JArray = null;
        JArray = work_inst(JArray, "and ISNULL(C.COMPLETE_YN,'N') <> 'Y'");

        if (JArray != null) {

            WorkListAdapter workListAdapter = new WorkListAdapter();
            for (int i = 0; i < JArray.length(); i++) {

                JSONObject jo = JArray.getJSONObject(i);
                String custNm="";
                String itemNm="";
                String spec="";
                String instAmt;
                String lotNo="";
                String completeYN;

                custNm=jo.getString("CUST_NM");
                lotNo=jo.getString("LOT_NO");
                itemNm=jo.getString("ITEM_NM");
                instAmt=jo.getString("INST_AMT");
                spec=jo.getString("SPEC");
                completeYN=jo.getString("COMPLETE_YN");

                workListVo= new WorkListVo(custNm,lotNo,itemNm,instAmt,spec,completeYN);
                workListAdapter.addItem(workListVo);

            }

            if (workListAdapter.getCount() == 0) {
                Toast.makeText(activity, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
            }
            gridView.setAdapter(workListAdapter);

        } else {
            Toast.makeText(activity, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
        }


    } catch (SQLException | JSONException e) {
        e.printStackTrace();
    } catch (Exception ex) {
        System.out.println(ex.toString());
    }
}


    public JSONArray work_inst(JSONArray JSONArray, String condition) throws SQLException, JSONException {



        StringBuilder sb = new StringBuilder();
        sb.append("select A.W_INST_DATE ");
        sb.append("     ,A.W_INST_CD ");
        sb.append("     ,A.LOT_NO ");
        sb.append("     ,A.ITEM_CD ");
        sb.append("     ,B.ITEM_NM ");
        sb.append("     ,B.ITEM_GUBUN ");
        sb.append("     ,B.SPEC ");
        sb.append("     ,A.INST_AMT ");
        sb.append("     ,A.CHARGE_AMT ");
        sb.append("     ,A.PACK_AMT ");
        sb.append("     ,A.PLAN_NUM ");
        sb.append("     ,A.PLAN_ITEM ");
        sb.append("     ,A.INSTAFF ");
        sb.append("     ,A.INST_NOTICE ");

        sb.append("     ,A.CUST_CD  ");
        sb.append("    ,isnull('0',B.BAL_STOCK) as  BAL_STOCK ");
        sb.append("        ,D.CUST_NM ");
        sb.append("        ,A.DELIVERY_DATE ");
        sb.append("        ,C.COMPLETE_YN  as COMPLETE ");
        sb.append("     ,CASE WHEN ISNULL(C.COMPLETE_YN,'N')='Y' THEN '완료' ELSE ( CASE WHEN ISNULL(C.COMPLETE_YN,'N')='S' THEN '진행중' ELSE '미완료' END ) END COMPLETE_YN   ");
        sb.append(" from F_WORK_INST A ");
        sb.append(" LEFT OUTER JOIN N_ITEM_CODE B ");
        sb.append(" ON A.ITEM_CD = B.ITEM_CD ");
        sb.append(" LEFT OUTER JOIN F_WORK_FLOW C ");
        sb.append(" ON A.LOT_NO = C.LOT_NO ");
        sb.append(" left join N_CUST_CODE as D on D.CUST_CD=A.CUST_CD  ");
        sb.append("where 1=1");
        sb.append(condition);
        sb.append(" order by A.W_INST_DATE desc, A.W_INST_CD desc ");

        JSONArray = dbInfo.SelectDB(sb.toString());

        System.out.println("쿼리: \n" + sb.toString());
        return JSONArray;
    }
}
