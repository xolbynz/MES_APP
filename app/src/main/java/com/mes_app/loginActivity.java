package com.mes_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.common.CompInfo;
import com.common.DBInfo;
import com.example.mes_app.R;

public class loginActivity extends AppCompatActivity {

    static public Connection conn;
    CompInfo compInfo;
    DBInfo dbInfo;

    Button btn_Login;
    EditText Saup_No;
    EditText Id;
    EditText Pw;
    InputMethodManager imm;
    View view;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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


        btn_Login = (Button) findViewById(R.id.btn_login);
        Saup_No = (EditText) findViewById(R.id.edit_사업자번호);
        Id = (EditText) findViewById(R.id.edit_ID);
        Pw = (EditText) findViewById(R.id.edit_PW);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        btn_Login.setOnClickListener(btn1Listener);

        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(Pw.getWindowToken(), 0);
        }

    }

    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    View.OnClickListener btn1Listener = new View.OnClickListener() {

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
                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(intent);

                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(Pw.getWindowToken(), 0);
                    }
                    finish();
                }

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                return;
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
                    return false

                            ;
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

                if (dbInfo.mainConn != null && !dbInfo.mainConn.isClosed())
                    return false;

                String dbIp = "218.38.14.36";    // 뒤에 :1443 은 입력하지 않는다.
                String dbName = rs.getString(1); // 사업자 번호에 해당되는 DB 접속
                String dbUser = "smartUser";
                String dbUserPass = "smart/?25";
                ConnectionClass connClass = new ConnectionClass();
                dbInfo.mainConn = connClass.getConnection(dbUser, dbUserPass, dbName, dbIp);

                query2.append("select * from [" + rs.getString(1) + "].[dbo].[N_STAFF_CODE]");
                query2.append("where LOGIN_ID = '" + Id + "' and PW = '" + Pw + "'");


                Statement stmt2 = dbInfo.mainConn.createStatement();

                rs2 = stmt2.executeQuery(query2.toString());

                if (rs2.next()) {

                    compInfo = new CompInfo();
                    compInfo.setSaupNo(rs.getString(1));
                    compInfo.setSaupNm(rs.getString(2));
                    compInfo.setSpCode(rs.getString(3));
                    compInfo.setSpSite(rs.getString(4));
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



