package com.mes_app;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mes_app.R;

public class monitoringActivity  extends Fragment {
    Context context;
    ViewGroup rootView;
    Button btn_startDate;
    InputMethodManager imm;
    int mYear, mMonth, mDay;
    EditText edit_startDate;
    EditText edit_endDate;

//    public monitoringActivity(){
//
//    }

    public monitoringActivity(Context context){
this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_monitoring, container, false);

        imm = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        edit_endDate=rootView.findViewById(R.id.edit_end_moniter);
        edit_startDate=rootView.findViewById(R.id.edit_start_moniter);

        edit_endDate.setOnClickListener(showDate);
        edit_startDate.setOnClickListener(showDate);
        return rootView;
    }


    View.OnClickListener showDate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            System.out.println("달력소환");
            imm.hideSoftInputFromWindow(edit_endDate.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(edit_startDate.getWindowToken(), 0);



            switch (v.getId()) {
                case R.id.edit_end_moniter:

                    new DatePickerDialog(context, listener, mYear,

                            mMonth, mDay).show();
                    break;
                case R.id.edit_start_moniter:

                    new DatePickerDialog(context, listener2, mYear,

                            mMonth, mDay).show();
                    break;

            }




        }
    };

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            mYear = year;

            mMonth = monthOfYear;

            mDay = dayOfMonth;

            edit_endDate.setText(String.format("%d/%d/%d", mYear,

                    mMonth + 1, mDay));

        }

    };

    private DatePickerDialog.OnDateSetListener listener2 = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            mYear = year;

            mMonth = monthOfYear;

            mDay = dayOfMonth;

            edit_startDate.setText(String.format("%d/%d/%d", mYear,

                    mMonth + 1, mDay));

        }

    };



}
