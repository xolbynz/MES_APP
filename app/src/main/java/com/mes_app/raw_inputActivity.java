package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mes_app.R;

public class raw_inputActivity  extends Fragment {


Button btn_input;
    MainActivity activity;
    ViewGroup rootView;
    Context context;
    TextView main_label;
    public raw_inputActivity() {
    }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {


            rootView = (ViewGroup) inflater.inflate(R.layout.activity_raw_input, container, false);
            btn_input = rootView.findViewById(R.id.btn_input);

            btn_input.setOnClickListener(Rawinput);
            return rootView;
        }
    View.OnClickListener Rawinput = new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            CustomDialog oDialog = new CustomDialog(this);
            oDialog.setCancelable(false);
            oDialog.show();


        }

        };




}
