package com.mes_app;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mes_app.R;

/**
 *  Created by Administrator on 2020-06-19.
 *  <summry>
 *      입고진행시 팝업으로 확인 취소
 *  </summry>
 */
public class pop_raw_input {

    Context context;
    Button btn_ok ;
    Button btn_cancel;
    TextView txt_title;
    TextView txt_rawName;
    TextView txt_process;

    public pop_raw_input(Context context) {
        this.context = context;
    }
    public pop_raw_input( ) {

    }

    public void callFunction(final TextView main_label) {
        final Dialog dlg = new Dialog(context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.custom_dialog);

        dlg.show();

        btn_ok= (Button) dlg.findViewById(R.id.btn_raw_input_ok);
        btn_cancel= (Button) dlg.findViewById(R.id.btn_raw_input_cancel);
        txt_title =(TextView) dlg.findViewById(R.id.txt_title);
        txt_process =(TextView) dlg.findViewById(R.id.txt_process);
        txt_rawName =(TextView) dlg.findViewById(R.id.txt_rawName);


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
                // 커스텀 다이얼로그에서 입력한 메시지를 대입한다.

                Toast.makeText(context, "\"" + "\" 을 입력하였습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
    }



}


