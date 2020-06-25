package com.mes_app;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.Adapter.stock_statusAdapter;
import com.VO.StockInputVo;
import com.common.CompInfo;
import com.common.DBInfo;
import com.example.mes_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class stock_statusActivity extends Fragment {

    MainActivity activity;  //매인 소환
    InputMethodManager imm; //키보드 내리기
    ViewGroup rootView;
    EditText startDate;
    EditText endDate;
    ImageButton btn_stockSearch;
    Context context;
GridView gridView;

    int mYear, mMonth, mDay;


    CompInfo compInfo;
    DBInfo dbInfo;
    ArrayList<String> list;
    JSONArray JArray;
    ArrayAdapter<String> adapter;
    TableLayout tableLayout;
    StockInputVo stockInputVo;
    ArrayList<StockInputVo> stockInputVoArrayList = new ArrayList<>();

    public stock_statusActivity(Context context) {
        this.context = context;
        dbInfo = new DBInfo();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.activity_stock_status, container, false);
        imm = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);

        startDate = rootView.findViewById(R.id.edit_startDate);

        endDate = rootView.findViewById(R.id.edit_endDate);
        btn_stockSearch= rootView.findViewById(R.id.btn_stock_search);

gridView=rootView.findViewById(R.id.grid_stock_status);

        startDate.setOnClickListener(onClickListener);
        endDate.setOnClickListener(onClickListener);
        btn_stockSearch.setOnClickListener(stockSearch);

        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);

        mMonth = cal.get(Calendar.MONTH);

        mDay = cal.get(Calendar.DAY_OF_MONTH);



        dbInfo = new DBInfo();


        return rootView;
    }






    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            System.out.println("달력소환");
            imm.hideSoftInputFromWindow(startDate.getWindowToken(), 0);



            switch (v.getId()) {
                case R.id.edit_startDate:

                    new DatePickerDialog(context, listener, mYear,

                            mMonth, mDay).show();
                    break;
                case R.id.edit_endDate:

                    new DatePickerDialog(context, listener2, mYear,

                            mMonth, mDay).show();
                    break;

            }

        }
    };


    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;

            mMonth = month+1;

            mDay = dayOfMonth;





            String m_Month=String.valueOf( mMonth);
            if (mMonth<10) {
                m_Month="0"+mMonth;
            }
            String m_Day=String.valueOf( mDay);

            if(mDay<10){
                m_Day="0"+mDay;
            }

            String selectedDate=year+"-"+m_Month+"-"+m_Day;
            startDate.setText(selectedDate);


        }


    };

    private DatePickerDialog.OnDateSetListener listener2 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;

            mMonth = month+1;

            mDay = dayOfMonth;


            String m_Month=String.valueOf( mMonth);
            if (mMonth<10) {
                m_Month="0"+mMonth;
            }
            String m_Day=String.valueOf( mDay);

            if(mDay<10){
                m_Day="0"+mDay;
            }

            String selectedDate=year+"-"+m_Month+"-"+m_Day;
            endDate.setText(selectedDate);


        }


    };


    View.OnClickListener stockSearch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            System.out.println(startDate.getText()+"부터"+endDate.getText()+"까지 검색하겠습니다.");


            try{

                JArray = Stock_status_Detail(JArray, "");

                if (JArray!=null){

                    stockInputVoArrayList.clear();
                    stock_statusAdapter stock_statusAdapter = new stock_statusAdapter();
                    for (int i =0; i <JArray.length();i++){
                        JSONObject jo = JArray.getJSONObject(i);
                        String packing_date = jo.getString("INPUT_DATE");
                        String lot_no = jo.getString("LOT_BAR");
                        String plan_no = jo.getString("PLAN_NUM");
                        String CUST_NM = jo.getString("CUST_NM");
                        String ITEM_NM = jo.getString("ITEM_NM");
                        String item_std = jo.getString("SPEC");
                        String item_unit = jo.getString("UNIT_NM");
                        String quantity = jo.getString("CURR_AMT");

                        stockInputVo=new StockInputVo(packing_date,lot_no,plan_no,CUST_NM,ITEM_NM,item_std,item_unit,quantity);

                        stock_statusAdapter.addItem(stockInputVo);
                    }

                    if(stock_statusAdapter.getCount() == 0) {
                        Toast.makeText(activity, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
                    }
                  gridView.setAdapter(stock_statusAdapter);




                }
                else{
                    Toast.makeText(activity, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
                }




            }
            catch (SQLException | JSONException e) {
                e.printStackTrace();
            }
            catch (Exception ex) {
            System.out.println(ex.toString());
            }



        }
    };


    public JSONArray Stock_status_Detail(JSONArray JSONArray, String condition) throws SQLException, JSONException{

        StringBuilder query = new StringBuilder();

        query.append("select \n");
        query.append("A.INPUT_DATE \n");
        query.append(",A.LOT_NO + RIGHT('00'+ convert(varchar, A.LOT_SUB), 3) as LOT_BAR \n");
        query.append(",C.PLAN_NUM \n");
        query.append(",D.CUST_NM \n");
        query.append(",B.ITEM_NM \n");
        query.append(",B.SPEC \n");
        query.append(",E.UNIT_NM \n");
        query.append(",A.CURR_AMT \n");


        query.append("FROM [" + dbInfo.Location + "].[dbo].[F_ITEM_INPUT] as A  \n");

        query.append("inner join  [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] as B on B.ITEM_CD=A.ITEM_CD \n");
        query.append("left join [" + dbInfo.Location + "].[dbo].[F_WORK_INST] as C on C.LOT_NO=A.LOT_NO \n");
        query.append("inner join [" + dbInfo.Location + "].[dbo].[N_CUST_CODE] as D on D.CUST_CD= C.CUST_CD \n");
        query.append("inner join [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] as E on E.UNIT_CD=B.UNIT_CD \n");

        query.append("WHERE 1=1 \n");

        if (!startDate.getText().equals("")) {
            query.append(" and A.INPUT_DATE>='"+startDate.getText()+"' \n");
        }
        if (!endDate.getText().equals("")) {
            query.append(" and A.INPUT_DATE<='"+endDate.getText()+"' \n");
        }
        JSONArray = dbInfo.SelectDB(query.toString());
        return JSONArray;
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