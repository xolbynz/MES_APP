package com.mes_app;

import android.content.Context;
import android.graphics.Color;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.Adapter.FlowDetailAdapter;
import com.Adapter.WorkProcessAdapter;
import com.VO.FlowDetailVo;
import com.VO.WorkProcessVo;
import com.common.DBInfo;
import com.example.mes_app.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.Array;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

public class work_processingActivity extends Fragment {

    DBInfo dbInfo;
    Context context;
    MainActivity activity;
    ViewGroup rootView;
    GridView grd_flow;
    GridView grd_flow_detail;

    TextView colSeq;
    TextView colFlow_Date;
    TextView colSub_amt;
    TextView colPoor;
    TextView colNon;


    WorkProcessVo workProcessVo;
    WorkProcessAdapter workProcessAdapter;
    FlowDetailVo flowDetailVo;
    FlowDetailAdapter flowDetailAdapter;
    JSONArray JArray;

    private BarChart chart;

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

        colSeq = rootView.findViewById(R.id.workprocess_tv_seq);
        colFlow_Date = rootView.findViewById(R.id.workprocess_tv_flow_date);
        colSub_amt = rootView.findViewById(R.id.workprocess_tv_sub_amt);
        colPoor = rootView.findViewById(R.id.workprocess_tv_poor_amt);
        colNon = rootView.findViewById(R.id.workprocess_tv_non_amt);

        colSeq.setVisibility(View.INVISIBLE);
        colFlow_Date.setVisibility(View.INVISIBLE);
        colSub_amt.setVisibility(View.INVISIBLE);
        colPoor.setVisibility(View.INVISIBLE);
        colNon.setVisibility(View.INVISIBLE);

        chart = rootView.findViewById(R.id.workProcessing_chart);

        resetting();
        getLogic();

        return rootView;
    }

    private void resetting(){
        grd_flow_detail.setAdapter(null);
        chart.clear();
        linearLayout.removeAllViews();
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
                resetting();

                if (JArray.length() != 0 && JArray != null) {
                    for (int i = 0; i < JArray.length(); i++) {

                        JSONObject jo = JArray.getJSONObject(i);

                        DynamicFlow = new Button(context);
                        DynamicFlow.setId(DYNAMIC_VIEW_ID + i);
                        DynamicFlow.setText(jo.getString("FLOW_NM"));
                        DynamicFlow.setTag(jo.getString("FLOW_CD"));
                        DynamicFlow.setHint(lot_no + "/" + item_cd);
                        DynamicFlow.setTextSize(16);
                        DynamicFlow.setWidth(250);
                        DynamicFlow.setHeight(10);
                        DynamicFlow.layout(1,1,1,1);
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
                String flow_cd = btntemp.getTag().toString();

                JArray = getWorkFlowDetail(JArray, lot_no, flow_cd, item_cd);

                if (JArray.length() != 0 && JArray != null) {

                    String LOT_NO = "";
                    String LOT_SUB = "";
                    String F_STEP = "";
                    String FLOW_CD = "";
                    String SEQ = "";
                    String F_SUB_DATE = "";
                    String LOSS = "";
                    String F_SUB_AMT = "0";
                    String POOR_CD = "";
                    String POOR_AMT = "0";
                    String COMPLETE_YN = "";
                    String CHECK_YN = "";
                    String INPUT_YN = "";
                    String INPUT_AMT = "0";
                    String FLOW_CHK_YN = "";
                    String FLOW_NM = "";
                    String INST_AMT = "0";
                    String NON_INPUT_AMT = "0";

                    DecimalFormat df = new DecimalFormat("#.#");
                    flowDetailAdapter = new FlowDetailAdapter();
                    for (int i = 0; i < JArray.length(); i++) {

                        JSONObject jo = JArray.getJSONObject(i);

                        if (jo.has("LOT_NO")) // Data값이 NULL인 경우 빈값 혹은 0으로 처리
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
                        if (jo.has("F_SUB_AMT")) {
                            F_SUB_AMT = jo.getString("F_SUB_AMT");
                            F_SUB_AMT = df.format(Double.parseDouble(F_SUB_AMT));
                        }
                        if (jo.has("POOR_CD"))
                            POOR_CD = jo.getString("POOR_CD");
                        if (jo.has("POOR_AMT")) {
                            POOR_AMT = jo.getString("POOR_AMT");
                            POOR_AMT = df.format(Double.parseDouble(POOR_AMT));
                        }
                        if (jo.has("COMPLETE_YN"))
                            COMPLETE_YN = jo.getString("COMPLETE_YN");
                        if (jo.has("CHECK_YN"))
                            CHECK_YN = jo.getString("CHECK_YN");
                        if (jo.has("INPUT_YN"))
                            INPUT_YN = jo.getString("INPUT_YN");
                        if (jo.has("INPUT_AMT")) {
                            INPUT_AMT = jo.getString("INPUT_AMT");
                            INPUT_AMT = df.format(Double.parseDouble(INPUT_AMT));
                        }
                        if (jo.has("FLOW_CHK_YN"))
                            FLOW_CHK_YN = jo.getString("FLOW_CHK_YN");
                        if (jo.has("FLOW_NM"))
                            FLOW_NM = jo.getString("FLOW_NM");
                        if (jo.has("INST_AMT")) {
                            INST_AMT = jo.getString("INST_AMT");
                            INST_AMT = df.format(Double.parseDouble(INST_AMT));
                        }
                        if (jo.has("NON")) {
                            NON_INPUT_AMT = jo.getString("NON");
                            NON_INPUT_AMT = df.format(Double.parseDouble(NON_INPUT_AMT));
                        }

                        flowDetailVo = new FlowDetailVo(LOT_NO, LOT_SUB, F_STEP, FLOW_CD, SEQ,
                                F_SUB_DATE, LOSS, F_SUB_AMT, POOR_CD,
                                POOR_AMT, COMPLETE_YN, CHECK_YN, INPUT_YN,
                                INPUT_AMT, FLOW_CHK_YN, FLOW_NM, INST_AMT,
                                NON_INPUT_AMT);

                        flowDetailAdapter.additem(flowDetailVo);
                    }
                    colSeq.setVisibility(View.VISIBLE);
                    colFlow_Date.setVisibility(View.VISIBLE);
                    colSub_amt.setVisibility(View.VISIBLE);
                    colPoor.setVisibility(View.VISIBLE);
                    colNon.setVisibility(View.VISIBLE);

                    grd_flow_detail.setAdapter(flowDetailAdapter);

                    DrawChart(flowDetailAdapter);
                } else {

                    colSeq.setVisibility(View.INVISIBLE);
                    colFlow_Date.setVisibility(View.INVISIBLE);
                    colSub_amt.setVisibility(View.INVISIBLE);
                    colPoor.setVisibility(View.INVISIBLE);
                    colNon.setVisibility(View.INVISIBLE);

                    grd_flow_detail.setAdapter(null);
                    chart.clear();
                    Toast.makeText(context, "해당 공정은 진행중이지 않습니다.", Toast.LENGTH_LONG).show();
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
                DecimalFormat df = new DecimalFormat("#.#");

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

    private void DrawChart(FlowDetailAdapter flowDetailAdapter) {

        DecimalFormat df = new DecimalFormat("#");

        chart.clear();
        String inst_amt = flowDetailAdapter.flowDetailVoArrayList.get(0).getINST_AMT();

        XAxis xAxis = chart.getXAxis(); // x 축 설정
        xAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.design_default_color_background)); // X축 텍스트컬러설정
        xAxis.setGridColor(ContextCompat.getColor(getContext(), R.color.design_default_color_background)); // X축 줄의 컬러 설정

        YAxis yAxis = chart.getAxisLeft(); // 좌측 Y축
        yAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.design_default_color_background)); // Y축 텍스트컬러설정
        yAxis.setGridColor(ContextCompat.getColor(getContext(), R.color.design_default_color_background)); // Y축 줄의 컬러 설정
        yAxis.setAxisMaxValue(Integer.valueOf(df.format(Double.parseDouble(inst_amt) + Double.parseDouble(inst_amt) * 0.2)));
        yAxis.setTextSize(16f);

        yAxis = chart.getAxisRight();
        yAxis.setDrawLabels(false); // 우측은 안보이게

        chart.getAxisLeft().setAxisLineColor(R.color.design_default_color_background);
        chart.getAxisLeft().setGridColor(R.color.design_default_color_background);
        chart.animateY(1000);
        chart.getLegend().setTextSize(16f);
        chart.getLegend().setTextColor(Color.WHITE);

        chart.getLegend().setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        chart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);

        chart.getXAxis().setTextSize(20f);
        chart.getXAxis().setTextColor(Color.WHITE);
        chart.getRendererLeftYAxis().getPaintAxisLabels().setTextSize(16f);

        ArrayList<String> title = new ArrayList<String>();
        title.add("완료");
        title.add("불량");
        title.add("미투입량");
        ArrayList<BarEntry> contents = new ArrayList<>();
        float[] float_sub_amt = new float[flowDetailAdapter.getCount()];
        float[] float_poor_amt = new float[flowDetailAdapter.getCount()];
        float[] float_non_amt = new float[flowDetailAdapter.getCount()];
        String[] label = new String[flowDetailAdapter.getCount() + 1];

        float f_sub_amt = 0;
        float poor_amt = 0;
        float non_amt = 0;
        String seq = "";

        for (int i = 0; i < flowDetailAdapter.getCount()-1; i++) {

            if (!flowDetailAdapter.flowDetailVoArrayList.get(i).getSEQ().equals("99999")) {
                System.out.println(flowDetailAdapter.flowDetailVoArrayList.get(i).getSEQ());
                f_sub_amt = Float.parseFloat(flowDetailAdapter.flowDetailVoArrayList.get(i).getF_SUB_AMT());
                poor_amt = Float.parseFloat(flowDetailAdapter.flowDetailVoArrayList.get(i).getPOOR_AMT());
                non_amt = Float.parseFloat(flowDetailAdapter.flowDetailVoArrayList.get(i).getNON_INPUT_AMT());
                seq = flowDetailAdapter.flowDetailVoArrayList.get(i).getSEQ();

                float_sub_amt[i] = f_sub_amt;
                float_poor_amt[i] = poor_amt;
                float_non_amt[i] = 0;
                label[i] = "SEQ" + seq;
            }
        }
        float_non_amt[flowDetailAdapter.getCount()-1] = non_amt; // 미투입량은 마지막으로 실행된 공정 미투입량 기입
        label[flowDetailAdapter.getCount()-1] = "최종 미투입량";

        contents.add(new BarEntry(float_sub_amt, 0));
        contents.add(new BarEntry(float_poor_amt, 1));
        contents.add(new BarEntry(float_non_amt, 2));

        BarDataSet barDataSet = new BarDataSet(contents, "");
        barDataSet.setValueFormatter(new PercentFormatter());
        barDataSet.setValueTextColor(R.color.design_default_color_background);
        barDataSet.setStackLabels(label);

        barDataSet.setDrawValues(false);


        BarData barData = new BarData(title, barDataSet);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS); //
        chart.setData(barData);
        chart.invalidate();


    }

    private JSONArray workProcssing(JSONArray jsonArray) throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();

        query.append(" select \n");
        query.append("          W.W_INST_DATE AS INST_DATE \n");
        query.append("         ,W.DELIVERY_DATE AS DELI_DATE \n");
        query.append("         ,W.ITEM_CD \n");
        query.append("         ,(SELECT ITEM_NM FROM N_ITEM_CODE WHERE W.ITEM_CD = ITEM_CD)AS ITEM_NM \n");
        query.append("         ,W.LOT_NO \n");
        query.append("         ,(SELECT COUNT( *)FROM N_ITEM_FLOW WHERE ITEM_CD = W.ITEM_CD)AS FLOW_COUNT \n");
        query.append("         ,W.INST_AMT AS INST_AMT \n");
        query.append("         ,ISNULL(I.INPUT_AMT, 0) AS INPUT_AMT \n");
        query.append("         ,ISNULL(INPUT_AMT ,  0) / W.INST_AMT * 100 AS INPUT_PER \n");
        query.append(" from F_WORK_FLOW A \n");
        query.append(" inner join F_WORK_INST W \n");
        query.append(" on A.LOT_NO = W.LOT_NO \n");
        query.append(" left outer join( \n");
        query.append("         SELECT LOT_NO, \n");
        query.append("         ISNULL(SUM(INPUT_AMT), 0)AS INPUT_AMT \n");
        query.append("         FROM F_ITEM_INPUT \n");
        query.append("         group by LOT_NO \n");
        query.append(" ) I \n");
        query.append(" on I.LOT_NO = A.LOT_NO \n");
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

        query.append("select ITEM_CD\n");
        query.append("     ,A.SEQ \n");
        query.append("     ,A.FLOW_CD\n");
        query.append("     ,B.FLOW_INSERT_YN\n");
        query.append("     ,A.ITEM_IDEN_YN\n");
        query.append("      ,B.FLOW_CHK_YN\n");
        query.append("      ,A.FLOW_SEQ\n");
        query.append("     ,A.COMMENT\n");
        query.append("     ,B.FLOW_NM\n");
        query.append("     ,case when A.ITEM_IDEN_YN ='Y' then '발행'else '미발행' end 식별표\n");
        query.append("     ,C.TYPE_CD\n");
        query.append("     ,A.COMMENT AS FLOW_COMMENT \n");
        query.append(" from N_ITEM_FLOW A \n");
        query.append(" LEFT OUTER JOIN N_FLOW_CODE B \n");
        query.append(" ON A.FLOW_CD = B.FLOW_CD \n");
        query.append(" LEFT OUTER JOIN N_TYPE_CODE C  \n");
        query.append(" ON B.POOR_TYPE_CD = C.TYPE_CD \n");


        query.append(" where A.ITEM_CD = '" + condition + "' \n");
        query.append(" and B.FLOW_INSERT_YN = 'Y' \n");
        query.append(" order by A.ITEM_CD,A.SEQ ");

        jsonArray = dbInfo.SelectDB(query.toString());
        return jsonArray;
    }

    private JSONArray getWorkFlowDetail(JSONArray jsonArray, String Lot_no, String Flow_cd, String Item_cd) throws SQLException, JSONException {

        StringBuilder query = new StringBuilder();
        query.append("SELECT   A.LOT_NO \n");
        query.append("        ,A.LOT_SUB \n");
        query.append("        ,A.F_STEP \n");
        query.append("        ,A.FLOW_CD \n");
        query.append("        ,A.SEQ \n");
        query.append("        ,A.F_SUB_DATE \n");
        query.append("        ,A.LOSS \n");
        query.append("        ,A.F_SUB_AMT \n");
        query.append("        ,A.POOR_CD \n");
        query.append("        ,A.POOR_AMT \n");
        query.append("        ,A.COMPLETE_YN \n");
        query.append("        ,A.CHECK_YN \n");
        query.append("        ,A.INPUT_YN \n");
        query.append("        ,A.INPUT_AMT \n");
        query.append("        ,C.FLOW_CHK_YN \n");
        query.append("        ,C.FLOW_NM \n");
        query.append("        ,E.INST_AMT \n");
        query.append("        ,F.NON \n");
        query.append("from F_WORK_FLOW_DETAIL A \n");
        query.append("left outer join N_POOR_CODE B \n");
        query.append("on A.POOR_CD = B.POOR_CD \n");
        query.append("left outer join N_FLOW_CODE C \n");
        query.append("on A.FLOW_CD = C.FLOW_CD \n");
        query.append("inner join F_WORK_INST as E \n");
        query.append("on E.LOT_NO=A.LOT_NO \n");
        query.append("inner join (  select LOT_NO \n");
        query.append("                     ,FLOW_CD \n");
        query.append("                     ,SEQ \n");
        query.append("                     ,SUM(isnull(f_sub_amt,0)) as f_sub_amt \n");
        query.append("			           ,INPUT_AMT - F_SUB_AMT AS NON \n");
        query.append("              from F_WORK_FLOW_DETAIL \n");
        query.append("              where LOT_NO = '" + Lot_no + "' AND FLOW_CD = '" + Flow_cd + "' \n");
        query.append("              group by LOT_NO,FLOW_CD,SEQ,INPUT_AMT,F_SUB_AMT ) F \n");
        query.append("              on A.LOT_NO = f.LOT_NO and A.FLOW_CD = f.FLOW_CD AND A.SEQ = f.SEQ \n");
        query.append("where A.LOT_NO = '" + Lot_no + "' AND A.ITEM_CD = '" + Item_cd + "' AND A.FLOW_CD = '" + Flow_cd + "'\n");

        query.append("union all \n");

        query.append("select DISTINCT A.LOT_NO \n");
        query.append("      ,'-' as lOT_SUB \n");
        query.append("      ,'-' as F_STEP \n");
        query.append("      ,'-' as FlOW_CD \n");
        query.append("      ,99999 as SEQ \n");
        query.append("      ,'-'  as F_SUB_DATE \n");
        query.append("      ,0 as LOSS \n");
        query.append("      ,B.F_SUB_AMT \n");
        query.append("      ,'' as POOR_CD \n");
        query.append("      ,C.POOR_AMT \n");
        query.append("      ,'-' as COMPLETE_YN \n");
        query.append("      ,'-' as CHECK_YN \n");
        query.append("      ,'-' as INPUT_YN \n");
        query.append("      ,0 as INPUT_AMT \n");
        query.append("      ,'-' as FLOW_CHK_YN \n");
        query.append("      ,'-' as FLOW_NM \n");
        query.append("      ,0 AS INPUT_AMT \n");
        query.append("      ,D.POOR_AMT AS POOR_AMT \n");
        query.append("from F_WORK_FLOW_DETAIL A \n");

        query.append("left outer join (select LOT_NO \n");
        query.append("        ,SUM(ISNULL(F_SUB_AMT,0)) as F_SUB_AMT \n");
        query.append("        from F_WORK_FLOW_DETAIL \n");
        query.append("        where LOT_NO = '" + Lot_no + "' AND FLOW_CD = '" + Flow_cd + "' AND ITEM_CD = '" + Item_cd + "' \n");
        query.append("        group by LOT_NO ) B \n");
        query.append("on A.LOT_NO = B.LOT_NO \n");

        query.append("left outer join (select LOT_NO \n");
        query.append("        ,SUM(ISNULL(POOR_AMT,0)) as POOR_AMT \n");
        query.append("        from F_WORK_FLOW_DETAIL \n");
        query.append("        where LOT_NO = '" + Lot_no + "' AND FLOW_CD = '" + Flow_cd + "' AND ITEM_CD = '" + Item_cd + "' \n");
        query.append("        group by LOT_NO ) C \n");
        query.append("ON A.LOT_NO = C.LOT_NO \n");

        query.append("left outer join (select A.LOT_NO, A.INST_AMT - B.F_SUB_AMT AS POOR_AMT \n");
        query.append("                  from F_WORK_INST A \n");
        query.append("                  inner join (select LOT_NO \n");
        query.append("                              ,SUM(ISNULL(F_SUB_AMT,0)) as F_SUB_AMT \n");
        query.append("                              from F_WORK_FLOW_DETAIL \n");
        query.append("                              where LOT_NO = '" + Lot_no + "' AND FLOW_CD = '" + Flow_cd + "' AND ITEM_CD = '" + Item_cd + "' \n");
        query.append("                              group by LOT_NO) B \n");
        query.append("                  ON A.LOT_NO = B.LOT_NO) D \n");
        query.append("ON A.LOT_NO = D.LOT_NO \n");
        query.append("        where A.LOT_NO = '" + Lot_no + "' AND A.FLOW_CD = '" + Flow_cd + "' AND A.ITEM_CD = '" + Item_cd + "' \n");
        query.append("order by A.SEQ \n");

        jsonArray = dbInfo.SelectDB(query.toString());
        return jsonArray;
    }

}
