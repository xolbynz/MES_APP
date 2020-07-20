package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.VO.RawVo;
import com.common.DBInfo;
import com.example.mes_app.R;

import org.json.JSONArray;

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


    TextView tv_itemNm;
    TextView tv_date;
    TextView tv_delDate;
    TextView tv_lotNo;
    TextView tv_workNum;
    TextView tv_instAmt;
    TextView tv_workAmt;
    TextView tv_workRate;





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

        return rootView;
    }





    View.OnClickListener inst_Search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                final pop_workList oDialog = new pop_workList(context, new pop_workList.pop_workListListner() {
                    @Override
                    public void ClickBtn(String lotNo, String custNm, String itemNm, String instAmt, String spec) {
                        selected_lotNo=lotNo;
                        selected_custNm=custNm;
                        selected_itemNm=itemNm;
                        selected_instAmt=instAmt;
                        selected_spec=spec;


                        try{

                            tv_itemNm.setText(selected_itemNm);
                            tv_lotNo.setText(selected_lotNo);
                            tv_instAmt.setText(selected_instAmt);


                        }catch (Exception ex){
                            System.out.println(ex.toString());
                        }
                    }
                });


                WindowManager.LayoutParams wm = oDialog.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
                wm.copyFrom(oDialog.getWindow().getAttributes());  //여기서 설정한값을 그대로 다이얼로그에 넣겠다는의미




             //   oDialog.setCancelable(false);


                oDialog.show();


            }
            catch (Exception ex){
                System.out.println(ex.toString());
            }


        }
    };





}