package com.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.common.DBInfo;
import com.example.mes_app.R;

public class Dailog_Raw_View extends Activity {

    TextView raw_mat_nm;
    String raw_mat_cd;
    DBInfo dbInfo;
    Context context;

    final Dialog dlg = new Dialog(context);

    public Dailog_Raw_View() {
    }

    public Dailog_Raw_View(Context context) {
        this.context = context;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dailog_raw_view);

        Intent intent = getIntent();
        String raw_mat_cd = intent.getStringExtra("raw_mat_cd");
        dlg.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
