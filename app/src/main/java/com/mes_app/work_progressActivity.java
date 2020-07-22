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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.VO.RawVo;
import com.common.DBInfo;
import com.example.mes_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

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
   LinearLayout llo_bottom;

   ConstraintLayout llo_mian;
   TextView tv_flow_title;




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

                            llo_mian.setVisibility(View.VISIBLE);


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

}