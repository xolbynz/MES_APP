package com.mes_app;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.Adapter.WorkListAdapter;
import com.VO.WorkListVo;
import com.common.DBInfo;
import com.example.mes_app.R;

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
    ArrayList<WorkListVo> arrayList = new ArrayList<>();
    Button btn_cancel;
    public String selected_lotNo;
    public String selected_custNm;
    public String selected_itemNm;
    public String selected_instAmt;
    public String selected_spec;
    public String selected_instDate;
    public String selected_deleveryDate;

private  pop_workListListner pop_workListListner;

public  interface pop_workListListner{
    void ClickBtn(String lotNo, String custNm, String itemNm, String instAmt,String spec,String instDate,String deleveryDate);
}

    public pop_workList(Context context, pop_workListListner pop_workListListner) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);



        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.
this.context=context;
this.pop_workListListner= pop_workListListner;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        m_oDialog = this;

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;
        getWindow().setAttributes(lpWindow);
        dbInfo = new DBInfo();
        setContentView(R.layout.pop_worklist);
        gridView=findViewById(R.id.worklist_gv);
        btn_cancel = findViewById(R.id.worklist_btn_cancel);
        btn_cancel.setOnClickListener(btn_cancelClick);
        datebind();
    }

    public void datebind(
    ) {
        try {
            JArray = null;
            JArray = work_inst(JArray, " and A.RAW_OUT_YN = 'Y' and ISNULL(C.COMPLETE_YN,'N') <> 'Y'");

            if (JArray != null) {

                final  WorkListAdapter workListAdapter = new WorkListAdapter();
                for (int i = 0; i < JArray.length(); i++) {

                    JSONObject jo = JArray.getJSONObject(i);
                    String custNm = "";
                    String itemNm = "";
                    String spec = "";
                    String instAmt;
                    String lotNo = "";
                    String instDate ="";
                    String deleveryDate="";

                    String completeYN;

                    custNm = jo.getString("CUST_NM");
                    lotNo = jo.getString("LOT_NO");
                    itemNm = jo.getString("ITEM_NM");
                    instAmt = jo.getString("INST_AMT");
                    spec = jo.getString("SPEC");
                    completeYN = jo.getString("COMPLETE_YN");
                    instDate =jo.getString("W_INST_DATE");
                    deleveryDate=jo.getString("DELIVERY_DATE");

                    workListVo = new WorkListVo(custNm, lotNo, itemNm, instAmt, spec, completeYN,instDate,deleveryDate);
                    workListAdapter.addItem(workListVo);

                }

                if (workListAdapter.getCount() == 0) {
                    Toast.makeText(activity, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
                    m_oDialog.dismiss();
                }
                gridView.setAdapter(workListAdapter);


                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        selected_lotNo=workListAdapter.arrayList.get(position).getLotNo().toString();
                        selected_custNm=workListAdapter.arrayList.get(position).getCustNm().toString();
                        selected_itemNm=workListAdapter.arrayList.get(position).getItemNm().toString();
                        selected_instAmt=workListAdapter.arrayList.get(position).getInstAmt().toString();
                        selected_spec=workListAdapter.arrayList.get(position).getSpec().toString();
                        selected_instAmt=workListAdapter.arrayList.get(position).getInstDate().toString();
                        selected_deleveryDate=workListAdapter.arrayList.get(position).getDelivertDate().toString();

                        pop_workListListner.ClickBtn(selected_lotNo,selected_custNm,selected_itemNm,selected_instAmt,selected_spec,selected_instDate,selected_deleveryDate);
                        m_oDialog.dismiss();

                    }
                });

            } else {
                Toast.makeText(activity, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
                m_oDialog.dismiss();
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
        sb.append("     ,convert(int,A.INST_AMT) as INST_AMT");
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
        sb.append(" from  [" + dbInfo.Location + "].[dbo].[F_WORK_INST] A ");
        sb.append(" LEFT OUTER JOIN  [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] B ");
        sb.append(" ON A.ITEM_CD = B.ITEM_CD ");
        sb.append(" LEFT OUTER JOIN  [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW] C ");
        sb.append(" ON A.LOT_NO = C.LOT_NO ");
        sb.append(" left join  [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] as D on D.CUST_CD=A.CUST_CD  ");
        sb.append("where 1=1");
        sb.append(condition);
        sb.append(" order by A.W_INST_DATE desc, A.W_INST_CD desc ");

        JSONArray = dbInfo.SelectDB(sb.toString());

        System.out.println("쿼리: \n" + sb.toString());
        return JSONArray;
    }


    View.OnClickListener btn_cancelClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            m_oDialog.dismiss();
        }
    };

}
