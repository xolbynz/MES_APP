package com.mes_app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.common.CompInfo;
import com.common.DBInfo;
import com.example.mes_app.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class loginActivity extends AppCompatActivity {

    static public Connection conn;
    CompInfo compInfo;
    DBInfo dbInfo;
    InputMethodManager imm; //키보드 내리기
    Button btn_Login;
    EditText Saup_No;
    EditText Id;
    EditText Pw;
    private Context mContext; // 사업자번호 저장
    ConstraintLayout layour_main;
    ConstraintLayout layour_login;
    ImageView image_saup;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //dg
        //jg
        if (tryConnect(true))
            Toast.makeText(this, "준비완료", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "접속오류", Toast.LENGTH_LONG).show();
        mContext = this;
        dbInfo = new DBInfo();
        compInfo = new CompInfo();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //키보드 내리기
        btn_Login = (Button) findViewById(R.id.btn_login);
        Saup_No = (EditText) findViewById(R.id.edit_사업자번호);
        Id = (EditText) findViewById(R.id.edit_ID);
        Pw = (EditText) findViewById(R.id.edit_PW);
        layour_main = (ConstraintLayout) findViewById(R.id.layout_maIn);
        layour_login = (ConstraintLayout) findViewById(R.id.layout_login);
        image_saup = (ImageView) findViewById(R.id.image_saup);


        Saup_No.setText(PreferenceManager.getString(mContext, "saupNo")); //사업자번호 불러오기
        btn_Login.setOnClickListener(Btn_Login_CLickListner);
        layour_main.setOnClickListener(myClickListener);
        layour_login.setOnClickListener(myClickListener);
        image_saup.setOnClickListener(myClickListener);


        image_saup.setFocusableInTouchMode(true);
        Pw.setOnKeyListener(PasswordEnterKeyListener);


        image_saup.requestFocus();//시작시 이미지에 포커스 주기

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
    }

    // 이벤트 핸들러


    View.OnKeyListener PasswordEnterKeyListener = new View.OnKeyListener() { // 패스워드에서 엔터치면 키보드 내려가게 하기
        @Override
        public boolean onKey(View v, int i, KeyEvent event) {
            switch (i){
                case KeyEvent.KEYCODE_ENTER:
                    imm.hideSoftInputFromWindow(Pw.getWindowToken(), 0);
                    break;
                default:
                    return false;
            }
            return true;
        }
    };


    View.OnClickListener Btn_Login_CLickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Saup_No.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "사업자 번호를 입력해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (Id.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "아이디를 입력해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (Pw.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "패스워드를 입력해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {

                if (getcompinfo(Saup_No.getText().toString(), Id.getText().toString(), Pw.getText().toString()) == true) {

                    PreferenceManager.setString(mContext, "saupNo", Saup_No.getText().toString());// 사업자번호 저장
                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    return;
                }

            } catch (Exception e) {
                return;
            }
        }
    };

    ///모든 뷰 클릭시 이벤트 발동
    /// 사업자 번호 , 아이디 , 패스워드를 제외한  뷰 클릭시 키보드 내림
    /// 6.17 문세진 리스너 객체 생성해서 불러내기 수정
    View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            imm.hideSoftInputFromWindow(Saup_No.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(Id.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(Pw.getWindowToken(), 0);
            switch (v.getId()) {
                case R.id.layout_maIn:
                    break;
                case R.id.layout_login:
                    break;
                case R.id.image_saup:
                    break;
            }
        }
    };


    // DB 연동


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
                dbInfo.Location = rs.getString(1);

                if (dbInfo.mainConn != null && !dbInfo.mainConn.isClosed())
                    return false;

                String dbIp = "218.38.14.36";    // 뒤에 :1443 은 입력하지 않는다.
                String dbName = dbInfo.Location; // 사업자 번호에 해당되는 DB 접속
                String dbUser = "smartUser";
                String dbUserPass = "smart/?25";
                ConnectionClass connClass = new ConnectionClass();
                dbInfo.mainConn = connClass.getConnection(dbUser, dbUserPass, dbName, dbIp);

                query2.append("select * from [" + dbInfo.Location + "].[dbo].[N_STAFF_CODE]");
                query2.append("where LOGIN_ID = '" + Id + "' and PW = '" + Pw + "'");


                Statement stmt2 = dbInfo.mainConn.createStatement();

                rs2 = stmt2.executeQuery(query2.toString());

                if (rs2.next()) {
                    compInfo.setCOM_SAPU_NO(saupNo);
                    compInfo.setCOM_LOCATION(rs.getString(1));
                    compInfo.setCOMPANY_NM(rs.getString(2));
                    compInfo.setSP_CODE(rs.getString(3));
                    compInfo.setPACK_GUBUN(rs.getString(4));

//                    dbInfo.mainConn.close();
                    return true;
                } else {
                    Toast.makeText(this, "아이디 혹은 비밀번호가 틀립니다", Toast.LENGTH_LONG).show();
                    dbInfo.mainConn = null;
                    return false;
                }

            } else {
                Toast.makeText(this, "등록된 사업자가 아닙니다", Toast.LENGTH_LONG).show();
                dbInfo.mainConn = null;
                return false;
            }
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }
}



