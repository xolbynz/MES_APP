package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;
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

public class  work_viewActivity  extends Fragment {

    InputMethodManager imm; //키보드 내리기
    DBInfo dbInfo;
    GridView gv_inst;
    JSONArray JArray;
    WorkInstVo workInstVo;
    MainActivity activity;
    ViewGroup rootView;

    public  work_viewActivity() {

    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        imm =(InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        rootView=(ViewGroup) inflater.inflate(R.layout.activity_work_view, container, false);
        gv_inst=rootView.findViewById(R.id.work_view_gv_workInst);
dbInfo= new DBInfo();
        datebinding();
        return rootView;
    }

    public void datebinding()
    {
        try{
            JArray = null;
            JArray =work_inst(JArray,"");

            if (JArray.length()!=0){
                WorkInstAdapter workInstAdapter = new WorkInstAdapter();

                for (int i=0; i<JArray.length();i++){
                    JSONObject jo = JArray.getJSONObject(i);

                    String lotNo= jo.getString("LOT_NO");
                    String itemNm= jo.getString("ITEM_NM");
                    String custNm= jo.getString("CUST_NM");
                    String instAmt= jo.getString("INST_AMT");

                    workInstVo=new WorkInstVo(lotNo,custNm,itemNm,instAmt);

                    workInstAdapter.addItem(workInstVo);

                }

                if(workInstAdapter.getCount() == 0) {
                    Toast.makeText(activity, "오류 발생", Toast.LENGTH_SHORT).show();
                }


                gv_inst.setAdapter(workInstAdapter);

               gv_inst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       Toast.makeText(getContext(),""+position, Toast.LENGTH_SHORT).show();


                   }
               });
            }

        }catch (Exception ex){
System.out.println(ex.toString());
        }
    }

    public  JSONArray work_inst(JSONArray JSONArray,String condition) throws SQLException, JSONException
    {

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
