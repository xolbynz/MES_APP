package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

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

public class  work_viewActivity  extends Fragment {

    InputMethodManager imm; //키보드 내리기
    DBInfo dbInfo;
    GridView gv_inst;
    JSONArray JArray;

    public  work_viewActivity() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        imm =(InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

dbInfo= new DBInfo();
        return inflater.inflate(R.layout.activity_work_view, container, false);
    }

    public void datebinding()
    {
        try{
            JArray = null;
            JArray =work_inst(JArray,"");

            if (JArray.length()!=0){
                WorkInstVo workInstVo = new WorkInstVo();

                for (int i=0; i<JArray.length();i++){
                    JSONObject jo = JArray.getJSONObject(i);

                }
            }

        }catch (Exception ex){

        }
    }

    public  JSONArray work_inst(JSONArray JSONArray,String condition) throws SQLException, JSONException
    {

        StringBuilder query = new StringBuilder();
        query.append("select A.W_INST_DATE \n");
        query.append(",A.W_INST_CD \n");
        query.append(" ,A.LOT_NO \n");
        query.append(",B.ITEM_CD \n");
        query.append(",B.ITEM_GUBUN \n");
        query.append(" ,B.SPEC  \n");
        query.append(" ,A.INST_AMT \n");
        query.append(",A.CHARGE_AMT  \n");
        query.append(",A.PACK_AMT  \n");
        query.append(" ,A.PLAN_NUM  \n");
        query.append(" ,A.PLAN_ITEM  \n");
        query.append(" ,A.INSTAFF  \n");
        query.append("  ,A.INST_NOTICE   \n");
        query.append(" ,A.CUST_CD  \n");
        query.append(" ,isnull('0',B.BAL_STOCK) as  BAL_STOCK ,D.CUST_NM     ,A.DELIVERY_DATE    ,C.COMPLETE_YN  as COMPLETE  ,CASE WHEN ISNULL(C.COMPLETE_YN,'N')='Y' THEN '완료' ELSE ( CASE WHEN ISNULL(C.COMPLETE_YN,'N')='S' THEN '진행중' ELSE '미완료' END ) END COMPLETE_YN     \n");


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

        return  JSONArray;
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
}
