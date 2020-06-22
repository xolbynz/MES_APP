package com.mes_app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.mes_app.R;

/**
 * Created by kj.lee on 2017. 8. 31..
 */

public class CustomDialog extends Dialog
{
    CustomDialog m_oDialog;
    public CustomDialog(Context context)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.custom_dialog);

        m_oDialog = this;

        TextView oView = (TextView) this.findViewById(R.id.textView);


        Button oBtn = (Button)this.findViewById(R.id.btnOK);
        Button oBtn2 = (Button)this.findViewById(R.id.btnCancel);
        oBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickBtn(v);
            }
        });
        oBtn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickBtn2(v);
            }
        });
    }

    public void onClickBtn(View _oView)
    {
        this.dismiss();
    }

    public void onClickBtn2(View _oView)
    {
        this.dismiss();
    }
}
