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
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.common.DBInfo;
import com.example.mes_app.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class item_statusActivity extends Fragment {

    MainActivity activity;
    Context context;
    DBInfo dbInfo;
    ViewGroup rootView;
    InputMethodManager imm;
    EditText et_start;
    EditText et_end;

    int mYear, mMonth, mDay;
    GridView gridView;
    ImageButton btn_search;
    public  item_statusActivity(){}
    public  item_statusActivity(Context context){
        this.context=context;
        dbInfo = new DBInfo();
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        rootView = (ViewGroup) inflater.inflate(R.layout.activity_item_status, container, false);
        imm = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);

        gridView=rootView.findViewById(R.id.itemStatus_gv);
        btn_search=rootView.findViewById(R.id.itemStatus_btn_search);

        et_end= rootView.findViewById(R.id.itemStatus_start);
        et_start=rootView.findViewById(R.id.itemStatus_end);
        btn_search.setOnClickListener(btn_SearchClick);
        et_end.setOnClickListener(showDate);
        et_start.setOnClickListener(showDate);
        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);

        mMonth = cal.get(Calendar.MONTH);

        mDay = cal.get(Calendar.DAY_OF_MONTH);
        return rootView;
    }

   View.OnClickListener btn_SearchClick = new View.OnClickListener() {
       @Override
       public void onClick(View v) {

       }
   };

    View.OnClickListener showDate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //   imm.hideSoftInputFromWindow(edit_startDate.getWindowToken(), 0);
            //   imm.hideSoftInputFromWindow(edit_endDate.getWindowToken(), 0);
            switch (v.getId()) {
                case R.id.itemStatus_start:

                    new DatePickerDialog(context, listener, mYear,

                            mMonth, mDay).show();
                    break;
                case R.id.itemStatus_end:

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

            mMonth = month + 1;

            mDay = dayOfMonth;
            String m_Month = String.valueOf(mMonth);
            if (mMonth < 10) {
                m_Month = "0" + mMonth;
            }
            String m_Day = String.valueOf(mDay);

            if (mDay < 10) {
                m_Day = "0" + mDay;
            }

            String selectedDate = year + "-" + m_Month + "-" + m_Day;
            et_start.setText(selectedDate);


        }


    };

    private DatePickerDialog.OnDateSetListener listener2 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            mYear = year;

            mMonth = month + 1;

            mDay = dayOfMonth;


            String m_Month = String.valueOf(mMonth);
            if (mMonth < 10) {
                m_Month = "0" + mMonth;
            }
            String m_Day = String.valueOf(mDay);

            if (mDay < 10) {
                m_Day = "0" + mDay;
            }

            String selectedDate = year + "-" + m_Month + "-" + m_Day;
            et_end.setText(selectedDate);


        }


    };
}
