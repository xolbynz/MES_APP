package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import java.text.DecimalFormat;
import java.util.jar.JarEntry;

public class work_processingActivity extends Fragment {

    DBInfo dbInfo;
    Context context;
    MainActivity activity;
    ViewGroup rootView;
    GridView grd_flow;
    GridView grd_flow_detail;

    WorkProcessVo workProcessVo;
    WorkProcessAdapter workProcessAdapter;
    JSONArray JArray;

    Button DynamicFlow;

    LinearLayout linearLayout;

    public work_processingActivity() {
        dbInfo = new DBInfo();

    }

    public work_processingActivity(Context context) {
        dbInfo = new DBInfo();
        this.context = context;
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
        linearLayout = rootView.findViewById(R.id.workprocess_linearlayout);
        grd_flow = rootView.findViewById(R.id.workprocessing_gv);
        grd_flow_detail = rootView.findViewById(R.id.workprocessing_gv_detail);
        grd_flow.setOnItemClickListener(getDetail);



        getLogic();

        return rootView;
    }

    private final int DYNAMIC_VIEW_ID = 0x8000;

    GridView.OnItemClickListener getDetail = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String item_cd = workProcessAdapter.workProcessVoArrayList.get(position).getItem_cd();
            String lot_no = workProcessAdapter.workProcessVoArrayList.get(position).getLot_no();
            try {
                JArray = null;
                JArray = getItemFlowList(JArray, item_cd);

                if (JArray.length() != 0 && JArray != null) {
                    for (int i = 0; i < JArray.length(); i++) {

                        JSONObject jo = JArray.getJSONObject(i);

                        DynamicFlow = new Button(context);
                        DynamicFlow.setId(DYNAMIC_VIEW_ID + i);
                        DynamicFlow.setText(jo.getString("FLOW_NM"));
                        DynamicFlow.setTag(jo.getString("FLOW_CD"));
                        DynamicFlow.setHint(lot_no + "/" + item_cd);
                        DynamicFlow.setTextSize(20);
                        DynamicFlow.setWidth(250);
                        DynamicFlow.setHeight(30);
                        DynamicFlow.setOnClickListener(dynamicListner);

                        linearLayout.addView(DynamicFlow, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    Button.OnClickListener dynamicListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            JArray = null;
            try {
                Button btntemp = (Button) v;
                String[] temp = btntemp.getHint().toString().split("/");
                String lot_no = temp[0];
                String item_cd = temp[1];
                JArray = getWorkFlowDetail(JArray, lot_no, item_cd);

                String LOT_NO = "";
                String LOT_SUB = "";
                String F_STEP = "";
                String FLOW_CD = "";
                String SEQ = "";
                String F_SUB_DATE = "";
                String LOSS = "";
                String F_SUB_AMT = "";
                String POOR_CD = "";
                String POOR_AMT = "";
                String COMPLETE_YN = "";
                String CHECK_YN = "";
                String INPUT_YN = "";
                String INPUT_AMT = "";
                String FLOW_CHK_YN = "";
                String FLOW_NM = "";
                String INST_AMT = "";
                String Non_Input_amt = "";

                if (JArray.length() != 0 && JArray != null) {
                    for (int i = 0; i < JArray.length(); i++) {

                        JSONObject jo = JArray.getJSONObject(i);

                        if (jo.has("LOT_NO")) // Data값이 NULL인 경우 빈값으로 처리
                            LOT_NO = jo.getString("LOT_NO");
                        if (jo.has("LOT_SUB"))
                            LOT_SUB = jo.getString("LOT_SUB");
                        if (jo.has("F_STEP"))
                            F_STEP = jo.getString("F_STEP");
                        if (jo.has("FLOW_CD"))
                            FLOW_CD = jo.getString("FLOW_CD");
                        if (jo.has("SEQ"))
                            SEQ = jo.getString("SEQ");
                        if (jo.has("F_SUB_DATE"))
                            F_SUB_DATE = jo.getString("F_SUB_DATE");
                        if (jo.has("LOSS"))
                            LOSS = jo.getString("LOSS");
                        if (jo.has("F_SUB_AMT"))
                            F_SUB_AMT = jo.getString("F_SUB_AMT");
                        if (jo.has("POOR_CD"))
                            POOR_CD = jo.getString("POOR_CD");
                        if (jo.has("POOR_AMT"))
                            POOR_AMT = jo.getString("POOR_AMT");
                        if (jo.has("COMPLETE_YN"))
                            COMPLETE_YN = jo.getString("COMPLETE_YN");
                        if (jo.has("CHECK_YN"))
                            CHECK_YN = jo.getString("CHECK_YN");
                        if (jo.has("INPUT_YN"))
                            INPUT_YN = jo.getString("INPUT_YN");
                        if (jo.has("INPUT_AMT"))
                            INPUT_AMT = jo.getString("INPUT_AMT");
                        if (jo.has("FLOW_CHK_YN"))
                            FLOW_CHK_YN = jo.getString("FLOW_CHK_YN");
                        if (jo.has("FLOW_NM"))
                            FLOW_NM = jo.getString("FLOW_NM");
                        if (jo.has("INST_AMT"))
                            INST_AMT = jo.getString("INST_AMT");
                        if (jo.has("NON"))
                            Non_Input_amt = jo.getString("NON");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

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

                    DecimalFormat df = new DecimalFormat("#.#");

                    if (jo.has("INST_DATE")) // Data값이 NULL인 경우 빈값으로 처리
                        inst_date = jo.getString("INST_DATE");
                    if (jo.has("DELI_DATE"))
                        deli_date = jo.getString("DELI_DATE");
                    if (jo.has("ITEM_CD"))
                        item_cd = jo.getString("ITEM_CD");
                    if (jo.has("ITEM_NM"))
                        item_nm = jo.getString("ITEM_NM");
                    if (jo.has("LOT_NO"))
                        lot_no = jo.getString("LOT_NO");
                    if (jo.has("FLOW_COUNT"))
                        flow_count = jo.getString("FLOW_COUNT");
                    if (jo.has("INST_AMT")) {
                        inst_amt = jo.getString("INST_AMT");
                        inst_amt = df.format(Double.parseDouble(inst_amt));
                    }
                    if (jo.has("INPUT_AMT")) {
                        input_amt = jo.getString("INPUT_AMT");
                        input_amt = df.format(Double.parseDouble(input_amt));
                    }
                    if (jo.has("INPUT_PER")) {
                        input_per = jo.getString("INPUT_PER");
                        input_per = df.format(Double.parseDouble(input_per)) + "%";
                    }

                    workProcessVo = new WorkProcessVo(inst_date, deli_date, item_cd, item_nm,
                            lot_no, flow_count, inst_amt, input_amt,
                            input_per);

                    workProcessAdapter.addItem(workProcessVo);
                }
                grd_flow.setAdapter(workProcessAdapter);

            } else {
                Toast.makeText(context, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
                grd_flow.setAdapter(null);
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
        query.append("         , (SELECT ITEM_NM FROM N_ITEM_CODE WHERE W.ITEM_CD = ITEM_CD)AS ITEM_NM ");
        query.append("         ,W.LOT_NO ");
        query.append("         , (SELECT COUNT( *)FROM N_ITEM_FLOW WHERE ITEM_CD = W.ITEM_CD)AS FLOW_COUNT ");
        query.append("         ,W.INST_AMT AS INST_AMT ");
        query.append("         , ISNULL(I.INPUT_AMT, 0) AS INPUT_AMT ");
        query.append("         ,ISNULL(INPUT_AMT ,  0) / W.INST_AMT * 100 AS INPUT_PER ");
        query.append(" from F_WORK_FLOW A ");
        query.append(" inner join F_WORK_INST W ");
        query.append(" on A.LOT_NO = W.LOT_NO ");
        query.append(" left outer join( ");
        query.append("         SELECT LOT_NO, ");
        query.append("         ISNULL(SUM(INPUT_AMT), 0)AS INPUT_AMT ");
        query.append("         FROM F_ITEM_INPUT ");
        query.append("         group by LOT_NO ");
        query.append(" ) I ");
        query.append(" on I.LOT_NO = A.LOT_NO ");
        query.append(" where A.COMPLETE_YN = 'S' and W.INST_AMT is not null and W.INST_AMT > 0 ");

        jsonArray = dbInfo.SelectDB(query.toString());
        return jsonArray;
    }

    public JSONArray fn_select_processing_flow(JSONArray jsonArray, String condition) throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();
        query.append("select ITEM_CD");
        query.append("      ,A.SEQ ");
        query.append("      ,A.FLOW_CD");
        query.append("      ,B.FLOW_INSERT_YN");
        query.append("      ,A.ITEM_IDEN_YN");
        query.append("      ,B.FLOW_CHK_YN");
        query.append("      ,A.FLOW_SEQ");
        query.append("      ,A.COMMENT");
        query.append("      ,B.FLOW_NM");
        query.append("      ,case when A.ITEM_IDEN_YN ='Y' then '발행'else '미발행' end 식별표");
        query.append("      ,C.TYPE_CD");
        query.append("      ,A.COMMENT AS FLOW_COMMENT ");
        query.append(" from N_ITEM_FLOW A ");
        query.append(" LEFT OUTER JOIN N_FLOW_CODE B ");
        query.append(" ON A.FLOW_CD = B.FLOW_CD ");
        query.append(" LEFT OUTER JOIN N_TYPE_CODE C  ");
        query.append(" ON B.POOR_TYPE_CD = C.TYPE_CD ");


        query.append(condition);
        query.append(" order by A.ITEM_CD,A.SEQ ");

        jsonArray = dbInfo.SelectDB(query.toString());
        return jsonArray;
    }

    private JSONArray getItemFlowList(JSONArray jsonArray, String condition) throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();

        query.append("select ITEM_CD");
        query.append("     ,A.SEQ ");
        query.append("     ,A.FLOW_CD");
        query.append("     ,B.FLOW_INSERT_YN");
        query.append("     ,A.ITEM_IDEN_YN");
        query.append("      ,B.FLOW_CHK_YN");
        query.append("      ,A.FLOW_SEQ");
        query.append("     ,A.COMMENT");
        query.append("     ,B.FLOW_NM");
        query.append("     ,case when A.ITEM_IDEN_YN ='Y' then '발행'else '미발행' end 식별표");
        query.append("     ,C.TYPE_CD");
        query.append("     ,A.COMMENT AS FLOW_COMMENT ");
        query.append(" from N_ITEM_FLOW A ");
        query.append(" LEFT OUTER JOIN N_FLOW_CODE B ");
        query.append(" ON A.FLOW_CD = B.FLOW_CD ");
        query.append(" LEFT OUTER JOIN N_TYPE_CODE C  ");
        query.append(" ON B.POOR_TYPE_CD = C.TYPE_CD ");


        query.append(" where A.ITEM_CD = '" + condition + "' ");
        query.append(" and B.FLOW_INSERT_YN = 'Y' ");
        query.append(" order by A.ITEM_CD,A.SEQ ");

        jsonArray = dbInfo.SelectDB(query.toString());
        return jsonArray;
    }

        private JSONArray getWorkFlowDetail(JSONArray jsonArray, String Lot_no, String Flow_cd) throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();
        query.append("SELECT   A.LOT_NO ");
        query.append("        ,A.LOT_SUB ");
        query.append("        ,A.F_STEP ");
        query.append("        ,A.FLOW_CD ");
        query.append("        ,A.SEQ ");
        query.append("        ,A.F_SUB_DATE ");
        query.append("        ,A.LOSS ");
        query.append("        ,A.F_SUB_AMT ");
        query.append("        ,A.POOR_CD ");
        query.append("        ,A.POOR_AMT ");
        query.append("        ,A.COMPLETE_YN ");
        query.append("        ,A.CHECK_YN ");
        query.append("        ,A.INPUT_YN ");
        query.append("        ,A.INPUT_AMT ");
        query.append("        ,C.FLOW_CHK_YN ");
        query.append("        ,C.FLOW_NM ");
        query.append("        ,E.INST_AMT ");
        query.append("        ,F.NON ");
        query.append("from F_WORK_FLOW_DETAIL A ");
        query.append("left outer join N_POOR_CODE B ");
        query.append("on A.POOR_CD = B.POOR_CD ");
        query.append("left outer join N_FLOW_CODE C ");
        query.append("on A.FLOW_CD = C.FLOW_CD ");
        query.append("inner join F_WORK_INST as E ");
        query.append("on E.LOT_NO=A.LOT_NO ");
        query.append("inner join (  select LOT_NO ");
        query.append("                     ,FLOW_CD ");
        query.append("                     ,SEQ ");
        query.append("                     ,SUM(isnull(f_sub_amt,0)) as f_sub_amt ");
        query.append("			           ,INPUT_AMT - F_SUB_AMT AS NON ");
        query.append("              from F_WORK_FLOW_DETAIL ");
        query.append("              where LOT_NO = ' " + Lot_no + "' AND FLOW_CD = '" + Flow_cd + "' ");
        query.append("              group by LOT_NO,FLOW_CD,SEQ,INPUT_AMT,F_SUB_AMT ) F ");
        query.append("              on A.LOT_NO = f.LOT_NO and A.FLOW_CD = f.FLOW_CD AND A.SEQ = f.SEQ ");
        query.append("where LOT_NO = ' " + Lot_no + "' AND FLOW_CD = '" + Flow_cd + "' ");
        query.append("order by A.LOT_NO,A.LOT_SUB,A.SEQ,A.F_STEP ");

        jsonArray = dbInfo.SelectDB(query.toString());
        return jsonArray;
    }

}
