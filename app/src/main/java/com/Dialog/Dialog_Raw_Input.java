package com.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mes_app.R;


public class Dialog_Raw_Input extends Dialog {

    private Button btn_Ok;
    private Button btn_Cancel;
    private String Cust_nm;
    private String Raw_mat_nm;
    private Boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);



    }

    public Dialog_Raw_Input(@NonNull Context context) {
        super(context);
    }

    public Dialog_Raw_Input(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected Dialog_Raw_Input(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
