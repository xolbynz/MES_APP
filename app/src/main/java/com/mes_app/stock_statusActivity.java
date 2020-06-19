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

import androidx.fragment.app.Fragment;

import com.example.mes_app.R;

public class stock_statusActivity extends Fragment  {

    MainActivity activity;  //매인 소환
    InputMethodManager imm; //키보드 내리기
    ViewGroup rootView;
    EditText startDate;
    EditText endDate;
    Button btn_startDate;


    public stock_statusActivity() {

    }

    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.activity_stock_status, container, false);
        imm = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);

        startDate = rootView.findViewById(R.id.edit_startDate);

        endDate = rootView.findViewById(R.id.edit_endDate);

        btn_startDate =rootView.findViewById(R.id.btn_startDate);
        btn_startDate.setOnClickListener(onClickListener);





        return inflater.inflate(R.layout.activity_stock_status, container, false);
    }





    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), listener, 2013, 10, 22);
            dialog.show();
        }
    };


    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }


    };


}