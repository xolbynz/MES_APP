package com.common;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mes_app.ConnectionClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class DBInfo {

    static public Connection mainConn = null;

    static void InsertDB() {

    }

    static ResultSet SelectDB(Connection conn, String query) throws SQLException {

        ResultSet rs = null;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rs = stmt.executeQuery(query);
        return rs;
    }
}


