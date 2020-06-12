package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mes_app.R;

public class raw_viewActivity extends Fragment {

                MainActivity activity;
    public raw_viewActivity() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        
        activity = (MainActivity) getActivity();
    }


    @Override
    public void onDetach() {
        super.onDetach();

        activity=null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_raw_view , container, false);

         Button btn_search =         rootView. findViewById(R.id.btn_search);
         final Spinner spinner_search = rootView. findViewById(R.id.spinner_raw_search);

         btn_search.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                                         System.out.println(spinner_search.getSelectedItem().toString());
             }
         });

                                  
        return rootView;


    }


}
