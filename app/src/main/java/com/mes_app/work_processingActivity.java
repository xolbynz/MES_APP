package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Adapter.WorkProcessAdapter;
import com.VO.WorkProcessVo;
import com.common.DBInfo;
import com.example.mes_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class work_processingActivity extends Fragment {

    DBInfo dbInfo;
    Context context;
    MainActivity activity;
    ViewGroup rootView;
    GridView gridView;

    WorkProcessVo workProcessVo;
    WorkProcessAdapter workProcessAdapter;
    JSONArray JArray;

    public work_processingActivity( ) {
        dbInfo = new DBInfo();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
    @Override
    public void onStart() {
        dbInfo = new DBInfo();
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = (ViewGroup) inflater.inflate(R.layout.activity_work_processing, container, false);

        gridView = rootView.findViewById(R.id.workprocessing_gv);

        getLogic();

        return rootView;
    }

    private void getLogic() {
        try {
            JArray = null;
            JArray = workProcssing(JArray);

            if (JArray.length() != 0) {

                workProcessAdapter = new WorkProcessAdapter();

                for (int i = 0; i < JArray.length(); i++) {

                    JSONObject jo = JArray.getJSONObject(i);

                    String inst_date = "";
                    String deli_date = "";
                    String item_cd = "";
                    String item_nm = "";
                    String lot_no = "";
                    String flow_count = "";
                    String inst_amt = "";
                    String input_amt = "";
                    String input_per = "";

                    if (jo.has("RAW_MAT_CD")) // Data값이 NULL인 경우 빈값으로 처리
                        inst_date = jo.getString("RAW_MAT_CD");
                    if (jo.has("RAW_MAT_NM"))
                        deli_date = jo.getString("RAW_MAT_NM");
                    if (jo.has("SPEC"))
                        item_cd = jo.getString("SPEC");
                    if (jo.has("UNIT_NM"))
                        item_nm = jo.getString("UNIT_NM");
                    if (jo.has("CUST_NM"))
                        lot_no = jo.getString("CUST_NM");
                    if (jo.has("INPUT_AMT"))
                        flow_count = jo.getString("INPUT_AMT");
                    if (jo.has("OUTPUT_AMT"))
                        inst_amt = jo.getString("OUTPUT_AMT");
                    if (jo.has("CURR_AMT"))
                        input_amt = jo.getString("CURR_AMT");
                    if (jo.has("LOC"))
                        input_per = jo.getString("LOC");

                    workProcessVo = new WorkProcessVo(inst_date, deli_date, item_cd, item_nm,
                            lot_no, flow_count, inst_amt, input_amt,
                            input_per);

                    workProcessAdapter.addItem(workProcessVo);
                }
                gridView.setAdapter(workProcessAdapter);

            } else {
                Toast.makeText(context, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
                gridView.setAdapter(null);
            }
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONArray workProcssing(JSONArray jsonArray) throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();

        query.append(" select ");
        query.append("         W.W_INST_DATE AS INST_DATE ");
        query.append("         , W.DELIVERY_DATE AS DELI_DATE ");
        query.append("         ,W.ITEM_CD ");
        query.append("         , (SELECT ITEM_NM FROM [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] WHERE W.ITEM_CD = ITEM_CD)AS ITEM_NM ");
        query.append("         ,W.LOT_NO ");
        query.append("         , (SELECT COUNT( *)FROM [" + dbInfo.Location + "].[dbo].[N_ITEM_FLOW] WHERE ITEM_CD = W.ITEM_CD)AS FLOW_COUNT ");
        query.append("         ,W.INST_AMT AS INST_AMT ");
        query.append("         , ISNULL(I.INPUT_AMT, 0) AS INPUT_AMT ");
        query.append("         ,ISNULL(INPUT_AMT, 0) / W.INST_AMT * 100 AS INPUT_PER ");
        query.append(" from [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW] A ");
        query.append(" inner join [" + dbInfo.Location + "].[dbo].[F_WORK_INST] W ");
        query.append(" on A.LOT_NO = W.LOT_NO ");
        query.append(" left outer join( ");
        query.append("         SELECT LOT_NO, ");
        query.append("         ISNULL(SUM(INPUT_AMT), 0)AS INPUT_AMT ");
        query.append("         FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] ");
        query.append("         group by LOT_NO ");
        query.append(" ) I ");
        query.append(" on I.LOT_NO = A.LOT_NO ");
        query.append(" where A.COMPLETE_YN = 'S' and W.INST_AMT is not null and W.INST_AMT > 0 ");

        jsonArray = dbInfo.SelectDB(query.toString());
        return jsonArray;
    }
}
