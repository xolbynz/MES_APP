package com.mes_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;

import com.example.mes_app.R;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //dg
        //jg



        Button btn1 = (Button)findViewById(R.id.btn_login);
        final EditText edit_saipNO = (EditText) findViewById(R.id.edit_사업자번호);
        final EditText edit_ID = (EditText) findViewById(R.id.edit_ID);
        final EditText edit_PW = (EditText) findViewById(R.id.edit_PW);

        btn1.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {


          if (edit_saipNO.getText().toString().equals("")) {
             Toast.makeText(getApplicationContext(), "사업자 번호를 입력해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
             return;
         }
         if (edit_ID.getText().toString().equals("")) {
             Toast.makeText(getApplicationContext(), "아이디를 입력해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
             return;
         }
         if (edit_PW.getText().toString().equals("")) {
             Toast.makeText(getApplicationContext(), "패스워드를 입력해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
             return;
         }
         try {
             Toast.makeText(getApplicationContext(), "Login0000224093 ", Toast.LENGTH_LONG).show();




             Intent intent = new Intent(loginActivity.this, MainActivity.class);
             startActivity(intent);
             finish();
         }
         catch (Exception e) {
             Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
             return;
         }
     }
 });
    }
}