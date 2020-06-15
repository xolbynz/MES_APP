package com.mes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.common.CompInfo;
import com.example.mes_app.R;

public class loginActivity extends AppCompatActivity {

    static public Connection conn;
    static public Connection LoginConn;
    CompInfo compInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //dg
        //jg
        if (tryConnect(true))
            Toast.makeText(this, "준비완료", Toast.LENGTH_LONG).show();



        Button btnLoginButton = (Button) findViewById(R.id.btn_login);
        final EditText edit_saipNO = (EditText) findViewById(R.id.edit_사업자번호);
        final EditText edit_ID = (EditText) findViewById(R.id.edit_ID);
        final EditText edit_PW = (EditText) findViewById(R.id.edit_PW);

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
                    Toast.makeText(getApplicationContext(), "Login0000224093 ", Toast.LENGTH_LONG).show();

                    if(getcompinfo(edit_saipNO.getText().toString(), edit_ID.getText().toString(),edit_PW.getText().toString()) == true) {
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

    public boolean tryConnect(boolean showMessage) {
        try {
            if (conn != null && !conn.isClosed())
                return true;
            // TODO
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
                        Toast.makeText(this, "DataBase 연결에 성공", Toast.LENGTH_LONG).show();
                        conn.close();
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            if (showMessage)
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
    }

    public boolean getcompinfo(String saupNo, String Id, String Pw) throws SQLException {
        boolean check = false;

        String query = null;
        ResultSet rs = null;

        query = "select COM_LOCATION,COMPANY_NM,A.SP_CODE,B.SP_SITE";
        query += "from T_COMP_LOGIN A ";
        query += "left outer join T_SUPPLY_CODE B";
        query += "on A.SP_CODE = B.SP_CODE";
        query += "where COM_SAUP_NO = ' " + saupNo + "'";

        Statement stmt = conn.createStatement();

        rs = stmt.executeQuery(query);

        if (rs.next()) {
            String query2 = null;
            ResultSet rs2 = null;

            if (LoginConn != null && !LoginConn.isClosed()) {
                String dbIp = "218.38.14.36";    // 뒤에 :1443 은 입력하지 않는다.
                String dbName = rs.getString(1); // 사업자 번호에 해당되는 DB 접속
                String dbUser = "smartUser";
                String dbUserPass = "smart/?25";
                ConnectionClass connClass = new ConnectionClass();
                LoginConn = connClass.getConnection(dbUser, dbUserPass, dbName, dbIp);
            }

            query2 = "select * from N_STAFF_CODE";
            query2 += "where * LOGIN_ID = '" + Id + "' and PW = '" + Pw + "'";

            Statement stmt2 = LoginConn.createStatement();

            rs2 = stmt2.executeQuery(query2);

            ResultSetMetaData rsmd2 = rs2.getMetaData();

            if (rs2.next()) {
                compInfo.setSaupNo(saupNo);
                compInfo.setSaupNm(rs.getString(2));
                compInfo.setSpCode(rs.getString(3));
                compInfo.setSpSite(rs.getString(4));

                conn.close();

                return true;
            } else {
                Toast.makeText(this, "아이디 혹은 비밀번호가 틀립니다", Toast.LENGTH_LONG).show();

                conn.close();

                return false;
            }

        } else {
            Toast.makeText(this, "등록된 사업자가 아닙니다", Toast.LENGTH_LONG).show();

            conn.close();

            return false;
        }
    }
}

