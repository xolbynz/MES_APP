package com.mes_app;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.mes_app.R;

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

    int mYear, mMonth, mDay;


    public stock_statusActivity(Context context) {
        this.context = context;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.activity_stock_status, container, false);
        imm = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);

        startDate = rootView.findViewById(R.id.edit_startDate);

        endDate = rootView.findViewById(R.id.edit_endDate);
        btn_stockSearch= rootView.findViewById(R.id.btn_stock_search);



        startDate.setOnClickListener(onClickListener);
        endDate.setOnClickListener(onClickListener);
        btn_stockSearch.setOnClickListener(stockSearch);

        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);

        mMonth = cal.get(Calendar.MONTH);

        mDay = cal.get(Calendar.DAY_OF_MONTH);





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

            mMonth = month;

            mDay = dayOfMonth;

            startDate.setText(String.format("%d/%d/%d", mYear,

                    mMonth + 1, mDay));


        }


    };

    private DatePickerDialog.OnDateSetListener listener2 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;

            mMonth = month;

            mDay = dayOfMonth;

            endDate.setText(String.format("%d/%d/%d", mYear,

                    mMonth + 1, mDay));


        }


    };


    View.OnClickListener stockSearch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            System.out.println(startDate.getText()+"부터"+endDate.getText()+"까지 검색하겠습니다.");


        }
    };


}