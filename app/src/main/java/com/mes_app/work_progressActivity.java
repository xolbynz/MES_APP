package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.VO.RawVo;
import com.common.CompInfo;
import com.common.DBInfo;
import com.common.wnDm;
import com.example.mes_app.R;
import com.github.mikephil.charting.charts.BarChart;
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

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class work_progressActivity extends Fragment {

    ViewGroup rootView;
    MainActivity activity;
    DBInfo dbInfo;
    GridView RecordWork;
    GridView Recordraw1;
    GridView Recordraw2;
    Context context;
    Button btn_search;
    Spinner spinner_search;
    CompInfo compInfo;
    GridView gridView;
    EditText editSearch;
    JSONArray JArray;
    RawVo rawVo;
    ArrayList<RawVo> rawVoArrayList;
    public String selected_lotNo;
    public String selected_custNm;
    public String selected_itemNm;
    public String selected_instAmt;
    public String selected_spec;
    public String selected_instDate;
    public String selected_deleveryDate;
    public String selected_maxSeq;
    public String selected_itemCd;
    public String selected_custCd;


    TextView tv_itemNm;
    TextView tv_date;
    TextView tv_delDate;
    TextView tv_lotNo;
    TextView tv_workNum;
    TextView tv_instAmt;
    TextView tv_workAmt;
    TextView tv_workRate;

    EditText ed_instAmt;
    EditText ed_faulty;
    LinearLayout llo_bottom;
    wnDm wnDm;
    ConstraintLayout llo_mian;
    TextView tv_flow_title;

    Button btn_complete;


    private BarChart chart;

    public work_progressActivity(Context context) {
        dbInfo = new DBInfo();
        this.context=context;
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
        super.onStart();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.activity_work_progress, container, false);


        btn_search= rootView.findViewById(R.id.workPrg_btn_view);

        btn_search.setOnClickListener(inst_Search);



        tv_itemNm=rootView.findViewById(R.id.workPrg_tv_itemNm2);
        tv_date=rootView.findViewById(R.id.workPrg_tv_date2);
        tv_delDate=rootView.findViewById(R.id.workPrg_tv_delDate2);
        tv_lotNo=rootView.findViewById(R.id.workPrg_tv_lotNo2);
        tv_workNum=rootView.findViewById(R.id.workPrg_tv_workNum2);
        tv_instAmt=rootView.findViewById(R.id.workPrg_tv_instAmt2);
        tv_workAmt=rootView.findViewById(R.id.workPrg_tv_workAmt2);
        tv_workRate=rootView.findViewById(R.id.workPrg_tv_workRate2);
        tv_flow_title=rootView.findViewById(R.id.workPrg_tv_title);
        llo_bottom=rootView.findViewById(R.id.workPrg_llo_bottom);
        llo_mian=rootView.findViewById(R.id.workPrg_llo_main);
        llo_mian.setVisibility(View.INVISIBLE);

        btn_complete=rootView.findViewById(R.id.workPrg_btn_complete);
        btn_complete.setOnClickListener(combplteclick);
        ed_instAmt=rootView.findViewById(R.id.workPrg_ed_instAmt);
        ed_faulty=rootView.findViewById(R.id.workPrg_ed_faultyAmt);
        chart=rootView.findViewById(R.id.chart);



        return rootView;
    }





    View.OnClickListener inst_Search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                final pop_workList oDialog = new pop_workList(context, new pop_workList.pop_workListListner() {
                    @Override
                    public void ClickBtn(String lotNo, String custNm, String itemNm, String instAmt, String spec,String instDate, String deleveryDate ,String maxSeq,String itemCd,String custCd) {
                        selected_lotNo=lotNo;
                        selected_custNm=custNm;
                        selected_itemNm=itemNm;
                        selected_instAmt=instAmt;
                        selected_spec=spec;
                        selected_instDate=instDate;
                        selected_deleveryDate=deleveryDate;
                        selected_maxSeq=maxSeq;
                        selected_itemCd=itemCd;
                        selected_custCd=custCd;


                        try{

                            tv_itemNm.setText(selected_itemNm);
                            tv_lotNo.setText(selected_lotNo);
                            tv_instAmt.setText(selected_instAmt);
                            tv_date.setText(selected_instDate);
                            tv_delDate.setText(selected_deleveryDate);
                            tv_workNum.setText(selected_maxSeq);


                            pushButton(Integer.valueOf(maxSeq));


                        }catch (Exception ex){
                            System.out.println(ex.toString());
                        }
                    }
                });



                oDialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);





                //   oDialog.setCancelable(false)ss;


                oDialog.show();


            }
            catch (Exception ex){
                System.out.println(ex.toString());
            }


        }
    };

    private  final int DYNAMIC_VIEW_ID = 0x8000;

    String selected_flow_Cd="";
    //동적으로 버튼 생성
    private  void pushButton(int seq){
        llo_bottom.removeAllViews(); // 포함 뷰 클리어
        JArray = null;
        try {
            JArray = item_flow_info(JArray, selected_itemCd);

            if (JArray!=null){
                for (int i=0;i<JArray.length();i++)
                {
                    JSONObject jo = JArray.getJSONObject(i);

                    final   Button dynamicButton = new Button(context);

                    dynamicButton.setId(DYNAMIC_VIEW_ID + i);
                    dynamicButton.setText(jo.getString("FLOW_NM"));
                    dynamicButton.setTag(jo.getString("FLOW_CD"));
                    dynamicButton.setTextSize(30);
                    final  int posiotion=i;
                    dynamicButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println(posiotion + dynamicButton.getText().toString());
                            tv_flow_title.setText(dynamicButton.getText().toString());
                            selected_flow_Cd=dynamicButton.getTag().toString();
                            llo_mian.setVisibility(View.VISIBLE);
                            drawChart();


                        }
                    });

                    llo_bottom.addView(dynamicButton, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }





    }


    public JSONArray item_flow_info(JSONArray JSONArray, String condition) throws SQLException, JSONException {


        StringBuilder sb = new StringBuilder();
        sb.append("select B.FLOW_NM,A.ITEM_CD,B.FLOW_CD \n");
        sb.append("from  [" + dbInfo.Location + "].[dbo].[N_ITEM_FLOW] A  \n");
        sb.append("INNER JOIN  [" + dbInfo.Location + "].[dbo].[N_FLOW_CODE] AS B ON B.FLOW_CD=A.FLOW_CD\n");
        sb.append("where 1=1 \n");
        sb.append("and ITEM_CD='"+condition+"'\n");



        JSONArray = dbInfo.SelectDB(sb.toString());

        System.out.println("쿼리: \n" + sb.toString());
        return JSONArray;
    }

    View.OnClickListener combplteclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {
                input_Logic();
                Toast.makeText(context,"생산완료",Toast.LENGTH_LONG);

            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
            }
        }
    };

    public void drawChart()
    {
chart.clear();

        XAxis xAxis = chart.getXAxis(); // x 축 설정
        xAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.design_default_color_background)); // X축 텍스트컬러설정
        xAxis.setGridColor(ContextCompat.getColor(getContext(), R.color.design_default_color_background)); // X축 줄의 컬러 설정


        YAxis yAxis = chart.getAxisLeft();
        yAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.design_default_color_background)); // Y축 텍스트컬러설정
        yAxis.setGridColor(ContextCompat.getColor(getContext(), R.color.design_default_color_background)); // Y축 줄의 컬러 설정
        yAxis.setAxisMaxValue( Integer.valueOf(selected_instAmt));
        chart.getAxisLeft().setAxisLineColor(R.color.design_default_color_background);
        chart.getAxisLeft().setGridColor(R.color.design_default_color_background);




        ArrayList <String>title = new ArrayList<String>();
        title.add("통과");
        title.add("불량");
        title.add("미투입량");
        ArrayList arrayList=진행중인공정일때(selected_lotNo,selected_flow_Cd);
        BarDataSet barDataSet = new BarDataSet(arrayList,"공정"); // 바 텍스트 컬러
        barDataSet.setValueFormatter(new PercentFormatter());
        barDataSet.setValueTextColor( R.color.design_default_color_background);
        chart.animateY(1000);

        BarData barData = new BarData(title,barDataSet);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS); //
        chart.setData(barData);
        chart.invalidate();


    }


    public JSONArray Processing(JSONArray JSONArray, String LOT_NO,String Flow_cd) throws SQLException, JSONException {


        StringBuilder sb = new StringBuilder();
        sb.append("select \n");
        sb.append("isnull(convert(int,F_SUB_AMT),0) as F_SUB_AMT \n" +
                ",isnull(convert(int,POOR_AMT),0) as  POOR_AMT\n" +
                ",isnull(convert(int,INPUT_AMT),0) as INPUT_AMT ");
        sb.append("from  [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW_DETAIL] A  \n");
        sb.append("where 1=1 \n");
        sb.append("and LOT_NO='"+LOT_NO+"'\n");
        sb.append("and FLOW_CD='"+Flow_cd+"'\n");




        JSONArray = dbInfo.SelectDB(sb.toString());

        System.out.println("쿼리: \n" + sb.toString());
        return JSONArray;
    }


    public ArrayList 진행중인공정일때(String lotNo,String flow_cd) {
        ArrayList<BarEntry> arrayList = new ArrayList<>();

        try {

            JArray = null;
            JArray = Processing(JArray,lotNo,flow_cd );

            if (JArray.length() != 0) {
                float[] F_SubAmtFloats = new float [JArray.length()];
                float[] PoorAmtFloats = new float [JArray.length()];
float sumSubAmt=0;
                for (int i = 0; i < JArray.length(); i++) {
                    JSONObject jo = JArray.getJSONObject(i);

                    float F_SUB_AMT = 0;
                    float POOR_AMT = 0;
                    int INPUT_AMT = 0;


                    if (jo.has("F_SUB_AMT"))
                        F_SUB_AMT = jo.getInt("F_SUB_AMT");
                    if (jo.has("POOR_AMT"))
                        POOR_AMT = jo.getInt("POOR_AMT");
                    if (jo.has("INPUT_AMT"))
                        INPUT_AMT = jo.getInt("INPUT_AMT");

                    F_SubAmtFloats[i]=F_SUB_AMT;
                    PoorAmtFloats[i]=POOR_AMT;

                    sumSubAmt+=F_SUB_AMT;






                }
                float instAmtfloat=Integer.valueOf(selected_instAmt);
                float instAmtfloat2=instAmtfloat-sumSubAmt;
                arrayList.add(new BarEntry( F_SubAmtFloats,0 ));
                arrayList.add(new BarEntry( PoorAmtFloats,1 ));
                arrayList.add(new BarEntry( instAmtfloat2,2 ));
            }

            else{

                arrayList.add(new BarEntry( Integer.valueOf(selected_instAmt),2 ));

            }

        } catch (Exception ex) {
            System.out.println("어댑터"+ex.toString());
        }
        return  arrayList;
    }

    private void input_Logic() throws SQLException, JSONException {

        Calendar cal = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String Today = sdf.format(cal.getTime()); // 오늘 날짜 ( #### - ## - ## )

        StringBuilder sb = new StringBuilder();

        sb.append("DECLARE @NUM INT");
        sb.append(" select @NUM=COUNT(*)  from [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW] ");
        sb.append("where LOT_NO='" + selected_lotNo + "'");
        sb.append("declare @f_step int \n ");
        sb.append("select @f_step=SEQ  from [" + dbInfo.Location + "].[dbo].[N_ITEM_FLOW] \n ");
        sb.append("where ITEM_CD = '" + selected_itemCd + "' \n ");
        sb.append("and FLOW_CD = '" + selected_flow_Cd + "' \n ");

        sb.append("declare @seq int \n ");
        sb.append("select @seq=ISNULL(MAX(SEQ),0)+1  from [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW_DETAIL] \n ");
        sb.append("where LOT_NO = '" + selected_lotNo + "' \n ");
        sb.append("and FLOW_CD = '" + selected_flow_Cd + "' \n ");


        sb.append("IF(@NUM != 1)\n");
        sb.append("insert into [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW] (");

        sb.append("      LOT_NO \n");
        sb.append("     ,FLOW_DATE\n");
        sb.append("     ,ITEM_CD\n");
        sb.append("     ,COMPLETE_YN ");
        sb.append("     ,INSTAFF \n");
        sb.append("     ,INTIME \n");
        sb.append(" ) values ( ");

        sb.append("     '" + selected_lotNo + "'\n");
        sb.append("      ,'" + Today + "'\n");
        sb.append("      ,'" + selected_itemCd + "' \n");
        sb.append("     ,'S'");
        sb.append("     ,'" + compInfo.getStaffCd() + "' \n");
        sb.append("     ,convert(varchar, getdate(), 120)\n ");
        sb.append(" ) ");

        sb.append(" INSERT INTO [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW_DETAIL] ( ");
        sb.append("  LOT_NO ");
        sb.append("  ,LOT_SUB ");
        sb.append("  ,F_STEP ");
        sb.append("  ,FLOW_CD ");
        sb.append("  ,SEQ ");
        sb.append("  ,F_SUB_DATE ");
        sb.append("  ,F_SUB_AMT ");
        sb.append("  ,INPUT_AMT ");
        sb.append("  ,POOR_AMT ");
        sb.append("  ,LOSS ");

        sb.append("  ,COMPLETE_YN ");
        sb.append("  ,CHECK_YN ");
        sb.append("  ,ITEM_CHECK_YN ");
        sb.append("  ,INPUT_YN ");
        sb.append("  ,CUST_CD ");
        sb.append("  ,ITEM_CD ");
        sb.append("  ,INSTAFF ");
        sb.append("  ,INTIME ");
        sb.append("  ,COMMENT ");
        sb.append(" ) VALUES ( ");
        sb.append(" '" + selected_lotNo + "' \n "); //lotno
        sb.append(" ,'" + 1 + "' \n "); //lot sub
        sb.append(" ,@f_step  \n"); //f_step
        sb.append(" ,'"+selected_flow_Cd+"'  \n"); // flow_cd
        sb.append(" ,@seq  \n"); //f_step
        sb.append("     ,'" + Today + "' \n ");
        sb.append("     ,'" + Integer.valueOf(ed_instAmt.getText().toString()) + "' \n ");
        sb.append("     ,'" + Integer.valueOf(ed_instAmt.getText().toString()) + "' \n "); //input_AMT 일단 보류
        sb.append("     ,'" + Integer.valueOf(ed_faulty.getText().toString()) + "' \n ");
        sb.append("     ,'0' \n ");
        sb.append("     ,'Y' \n ");
        sb.append("     ,'S' \n ");
        sb.append("     ,'S' \n ");
        sb.append("     ,'Y' \n ");

        sb.append(" ,'"+selected_custCd+"'  \n");
        sb.append(" ,'"+selected_itemCd+"'  \n");
        sb.append("     ,'" + compInfo.getStaffCd() + "' \n ");
        sb.append("     ,convert(varchar, getdate(), 120) \n ");
        sb.append(" ,'모바일입고'  \n");
        sb.append(" ) \n ");





        dbInfo.Insert(sb.toString());
    }
}