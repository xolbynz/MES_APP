package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Adapter.ItemStatusAdapter;
import com.VO.ItemStatusVo;
import com.common.DBInfo;
import com.example.mes_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class item_statusActivity extends Fragment {

    MainActivity activity;
    Context context;
    DBInfo dbInfo;
    ViewGroup rootView;
    InputMethodManager imm;
    EditText et_item;
    JSONArray jsonArray;
    ItemStatusVo itemStatusVo;

    GridView gridView;
    ImageButton btn_search;

    public item_statusActivity() {
    }

    public item_statusActivity(Context context) {
        this.context = context;
        dbInfo = new DBInfo();
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        rootView = (ViewGroup) inflater.inflate(R.layout.activity_item_status, container, false);
        imm = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);

        gridView = rootView.findViewById(R.id.itemTracking_gv_grid);
        btn_search = rootView.findViewById(R.id.itemStatus_btn_search);

        et_item = rootView.findViewById(R.id.itemStatus_et_item);
        btn_search.setOnClickListener(btn_SearchClick);
        Calendar cal = new GregorianCalendar();


        datebind();
        return rootView;
    }

    View.OnClickListener btn_SearchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            datebind();
        }
    };


    public void datebind() {
        try {

            jsonArray = null;
            jsonArray = item_status(jsonArray, "and A.ITEM_NM LIKE '%" + et_item.getText() + "%' ");

            if (jsonArray.length() != 0) {

                final ItemStatusAdapter itemStatusAdapter = new ItemStatusAdapter();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jo = jsonArray.getJSONObject(i);

                    String ITEM_NM = "";
                    String SPEC = "";
                    String PROP_STOCK = "0";
                    String BAL_STOCK = "0";
                    String POOR_AMT = "0";
                    String UNIT_NM = "";


                    if (jo.has("ITEM_NM")) // Data값이 NULL인 경우 빈값으로 처리
                        ITEM_NM = jo.getString("ITEM_NM");
                    if (jo.has("SPEC")) // Data값이 NULL인 경우 빈값으로 처리
                        SPEC = jo.getString("SPEC");

                    if (jo.has("PROP_STOCK")) // Data값이 NULL인 경우 빈값으로 처리
                        PROP_STOCK = jo.getString("PROP_STOCK");
                    if (jo.has("BAL_STOCK")) // Data값이 NULL인 경우 빈값으로 처리
                        BAL_STOCK = jo.getString("BAL_STOCK");
                    if (jo.has("POOR_AMT")) // Data값이 NULL인 경우 빈값으로 처리
                        POOR_AMT = jo.getString("POOR_AMT");

                    if (jo.has("UNIT_NM")) // Data값이 NULL인 경우 빈값으로 처리
                        UNIT_NM = jo.getString("UNIT_NM");


                    itemStatusVo = new ItemStatusVo(ITEM_NM, SPEC, PROP_STOCK, POOR_AMT, BAL_STOCK, UNIT_NM);
                    itemStatusAdapter.addItem(itemStatusVo);
                }
                gridView.setAdapter(itemStatusAdapter);

            } else {
                Toast.makeText(context, "검색된 정보가 없습니다", Toast.LENGTH_SHORT).show();
                gridView.setAdapter(null);
            }
        } catch (SQLException | JSONException e) {
            System.out.println(e.toString());
            e.printStackTrace();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private JSONArray item_status(JSONArray Jarray, String condition) throws SQLException, JSONException {


        StringBuilder sb = new StringBuilder();

        sb.append("     select A.ITEM_CD, A.ITEM_NM, A.SPEC,      ");
        sb.append("     convert(int,isnull(A.BASIC_STOCK,0)) as BASIC_STOCK,       ");
        sb.append("     convert(int,isnull(A.BAL_STOCK,0)) as BAL_STOCK,     ");
        sb.append("     convert(int,isnull(A.PROP_STOCK,0)) as PROP_STOCK,     ");
        sb.append("     convert(int,ISNULL(SUM(C.POOR_AMT),0)) AS POOR_AMT,     ");
        sb.append("      (SELECT UNIT_NM FROM [" + dbInfo.Location + "].[dbo].[N_UNIT_CODE] WHERE UNIT_CD = A.UNIT_CD) AS UNIT_NM,     ");
        sb.append("      A.ITEM_GUBUN     ");
        sb.append("     from [" + dbInfo.Location + "].[dbo].[N_ITEM_CODE] A     ");
        sb.append("     left outer join [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW] B ON A.ITEM_CD = B.ITEM_CD     ");
        sb.append("     left outer join [" + dbInfo.Location + "].[dbo].[F_WORK_FLOW_DETAIL] C ON B.LOT_NO = C.LOT_NO     ");
        sb.append("      WHERE 1=1   ");
        sb.append(condition);
        sb.append("     group by A.ITEM_CD ,A.ITEM_NM, A.SPEC, BASIC_STOCK,  BAL_STOCK, PROP_STOCK ,UNIT_CD,ITEM_GUBUN ");
        sb.append("     order by A.ITEM_CD     ");

        Jarray = dbInfo.SelectDB(sb.toString());
        return Jarray;
    }
}
