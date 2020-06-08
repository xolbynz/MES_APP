package com.mes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mes_app.R;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //dg
        //jg



        Button btn1 = (Button)findViewById(R.id.btn_login);

 btn1.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Toast.makeText(getApplicationContext(),"Login0000224093 ",Toast.LENGTH_LONG).show();


     }
 });
    }
}