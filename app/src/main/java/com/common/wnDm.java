package com.common;

import org.json.JSONArray;
import org.json.JSONException;

import java.sql.SQLException;

public class wnDm {

    DBInfo dbInfo;
    JSONArray jsonArray;

    public wnDm(){
        dbInfo = new DBInfo();
        jsonArray = new JSONArray();
    }

    public boolean isCustDayTotal(String inputDate, String cust_cd) throws SQLException, JSONException {


        StringBuilder sb = new StringBuilder();
        sb.append("  select *  ");
        sb.append("  FROM [" + dbInfo.Location + "].[dbo].[T_CUST_DAY_TOTAL]  ");
        sb.append("  WHERE INPUT_DATE ='" + inputDate + "' AND CUST_CD = '" + cust_cd + "'  ");

        jsonArray = dbInfo.SelectDB(sb.toString());
        if(jsonArray != null && jsonArray.length() != 0){
            return true;
        }else{
            return false;
        }
    }
}
