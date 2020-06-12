package com.mes_app;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    public Connection connection = null;
    public String ConnectionURL = null;
    public String errMsg = "";

    @SuppressLint("NewApi")
    public Connection getConnection(String user, String password, String database, String server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            DriverManager.registerDriver((Driver) Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance());
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://" + server ,user,password);
            Log.d("#DB", "after connection");
        } catch (
                SQLException se) {
            Log.e("error here 1 : ", se.getMessage());
            errMsg = se.getMessage();
        } catch (
                ClassNotFoundException e) {
            Log.e("error here 2 : ", e.getMessage());
            errMsg = e.getMessage();
        } catch (
                Exception e) {
            Log.e("error here 3 : ", e.getMessage());
            errMsg = e.getMessage();
        }
        return connection;
    }

    public String getLastErrMsg() {
        return errMsg;
    }
}