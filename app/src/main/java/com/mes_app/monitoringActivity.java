package com.mes_app;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mes_app.R;

public class monitoringActivity  extends Fragment {
    Context context;
    ViewGroup rootView;
    Button btn_startDate;
    Button btn_endDate;

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

        btn_startDate=rootView.findViewById(R.id.btn_start_moniter);
        btn_endDate=rootView.findViewById(R.id.btn_end_moniter);
        edit_endDate=rootView.findViewById(R.id.edit_end_moniter);
        edit_startDate=rootView.findViewById(R.id.edit_start_moniter);

        btn_startDate.setOnClickListener(showDate);
        btn_endDate.setOnClickListener(showDate);
        return rootView;
    }


    View.OnClickListener showDate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            DatePickerDialog dialog = new DatePickerDialog(context, listener, 2013, 10, 22);

            dialog.show();





        }
    };

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            Toast.makeText(getContext(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();

        }

    };





}
