package com.mes_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.common.CompInfo;
import com.example.mes_app.R;

public class loginActivity extends AppCompatActivity {

    static public Connection conn;
    static public Connection LoginConn;
    InputMethodManager imm; //키보드 내리기
    private Context mContext; //사업자 번호 기억
   // CompInfo compInfo;
   EditText edit_saipNO;
    EditText edit_ID;
    EditText edit_PW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //dg
        //jg
        mContext = this; //사업자 번호 기억 할때 필요
        if (tryConnect(true))
            Toast.makeText(this, "준비완료", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "접속오류", Toast.LENGTH_LONG).show();


        Button btnLoginButton = (Button) findViewById(R.id.btn_login);
          edit_saipNO = (EditText) findViewById(R.id.edit_사업자번호);
          edit_ID = (EditText) findViewById(R.id.edit_ID);
          edit_PW = (EditText) findViewById(R.id.edit_PW);
        final ConstraintLayout layour_main = (ConstraintLayout)  findViewById(R.id.layout_maIn);
        final ConstraintLayout layour_login = (ConstraintLayout)  findViewById(R.id.layout_login);
        final ImageView  image_saup = (ImageView) findViewById(R.id.image_saup);



        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //키보드 내리기


        edit_saipNO.setText( PreferenceManager.getString(mContext, "saupNo"));  //사업자 번호 기억한거 불러오기

// 클릭 이벤트주기 -> 키보드 숨김
        layour_main.setOnClickListener(myClickListener);
        layour_login.setOnClickListener(myClickListener);
        image_saup.setOnClickListener(myClickListener);

        image_saup.setFocusableInTouchMode(true);


        image_saup.requestFocus();//시작시 이미지에 포커스 주기






        btnLoginButton.setOnClickListener(new View.OnClickListener() {
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

                    if (getcompinfo(edit_saipNO.getText().toString(), edit_ID.getText().toString(), edit_PW.getText().toString()) == true) {


                        PreferenceManager.setString(mContext, "saupNo", edit_saipNO.getText().toString()); //사업자 번호 기억 저장하기
                        Intent intent = new Intent(loginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }

    View.OnClickListener myClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            imm.hideSoftInputFromWindow(edit_saipNO.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(edit_ID.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(edit_PW.getWindowToken(), 0);



            switch (v.getId())
            {
                case R.id.layout_maIn :
                    break;

                case R.id.layout_login :
                    break;
                case R.id.image_saup :
                    break;
            }
        }
    };


    public boolean tryConnect(boolean showMessage) {
        try {
            if (conn != null && !conn.isClosed())
                return false;
            // TODO
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String dbIp = "218.38.14.36";    // 뒤에 :1443 은 입력하지 않는다.
            String dbName = "SM_FACTORY_COM"; //임시로 COM에들어감
            String dbUser = "smartUser";
            String dbUserPass = "smart/?25";
            ConnectionClass connClass = new ConnectionClass();
            conn = connClass.getConnection(dbUser, dbUserPass, dbName, dbIp);

            if (conn == null) {
                if (showMessage)
                    Toast.makeText(this, connClass.getLastErrMsg(), Toast.LENGTH_LONG).show();
                return false;
            } else {
                if (conn.isClosed()) {
                    if (showMessage)
                        Toast.makeText(this, "DataBase 연결 실패", Toast.LENGTH_LONG).show();
                    return false;
                } else {
                    if (showMessage) {
                        Toast.makeText(this, "DataBase 연결 성공", Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            if (showMessage)
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
    }

    public boolean getcompinfo(String saupNo, String Id, String Pw) throws SQLException {

        StringBuilder query = new StringBuilder();
        ResultSet rs = null;

        query.append("SELECT A.COM_LOCATION,");
        query.append("COMPANY_NM, ");
        query.append("A.SP_CODE,");
        query.append("B.SP_SITE ");
        query.append("FROM [SM_FACTORY_COM].[dbo].[T_COMP_LOGIN] A ");
        query.append("left outer join [SM_FACTORY_COM].[dbo].[T_SUPPLY_CODE] B ");
        query.append("on A.SP_CODE = B.SP_CODE ");
        query.append("where COM_SAUP_NO = '" + saupNo + "'");

        Statement stmt = conn.createStatement();

        rs = stmt.executeQuery(query.toString());
        try {
            if (rs.next()) {
                StringBuilder query2 = new StringBuilder();
                ResultSet rs2 = null;

                if (LoginConn != null && !LoginConn.isClosed())
                    return  false;

                String dbIp = "218.38.14.36";    // 뒤에 :1443 은 입력하지 않는다.
                String dbName = rs.getString(1); // 사업자 번호에 해당되는 DB 접속
                String dbUser = "smartUser";
                String dbUserPass = "smart/?25";
                ConnectionClass connClass = new ConnectionClass();
                LoginConn = connClass.getConnection(dbUser, dbUserPass, dbName, dbIp);

                query2.append("select * from [" + rs.getString(1) + "].[dbo].[N_STAFF_CODE]");
                query2.append("where LOGIN_ID = '" + Id + "' and PW = '" + Pw + "'");


                Statement stmt2 = LoginConn.createStatement();

                rs2 = stmt2.executeQuery(query2.toString());

                if (rs2.next()) {
//                    compInfo.setSaupNo(saupNo);
//                    compInfo.setSaupNm(rs.getString(2));
//                    compInfo.setSpCode(rs.getString(3));
//                    compInfo.setSpSite(rs.getString(4));
                    return true;
                } else {
                    Toast.makeText(this, "아이디 혹은 비밀번호가 틀립니다", Toast.LENGTH_LONG).show();
                    return false;
                }

            } else {
                Toast.makeText(this, "등록된 사업자가 아닙니다", Toast.LENGTH_LONG).show();
                return false;
            }
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }
}



