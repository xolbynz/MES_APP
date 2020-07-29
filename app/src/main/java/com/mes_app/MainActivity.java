package com.mes_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.common.CompInfo;
import com.common.DBInfo;
import com.example.mes_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction;
    raw_viewActivity frag_raw_view = new raw_viewActivity(); //프래그먼트 객채셍성
    raw_inputActivity frag_raw_input = new raw_inputActivity(this);
    work_viewActivity frag_work_view = new work_viewActivity(this);
    work_progressActivity frag_work_process = new work_progressActivity(this);
    stock_statusActivity frag_stock_status = new stock_statusActivity(this);
    monitoringActivity frag_monMonitoring = new monitoringActivity(this);
    item_releaseActivity frag_itemRleace = new item_releaseActivity(this);

    item_statusActivity frag_itemStatus = new item_statusActivity(this);
    item_trackingActivity frag_itemTracking = new item_trackingActivity(this);
    Button tab1;//메뉴바
    Button tab2;
    Button tab3;
    Button tab4;
    TextView tv_logout;
    TextView tv_staffNm;
    CompInfo compInfo;
    Context context = this;
    LinearLayout llo_subMenu;
    JSONArray jsonArray;
    FragmentManager fragmentManager;
    LinearLayout llo_topMenu;
    DBInfo dbInfo;
    InputMethodManager imm; //키보드 내리기

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, loginActivity.class);
            DBInfo.mainConn = null;
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dg
        //jg

        dbInfo = new DBInfo();


//
//try {
//    Bitmap bmp2 = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
//    ByteBuffer buffer = ByteBuffer.wrap(bytes);
//    bmp2.copyPixelsFromBuffer(buffer);
//    Drawable drawable = new BitmapDrawable(bmp2);
//    getSupportActionBar().setHomeAsUpIndicator(drawable);
//}
//catch (Exception ex)
//{
//    System.out.println(ex.toString());
//}

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //키보드 내리기
        //프래그먼트 추가
//        fragmentManager = getSupportFragmentManager();
//
//
//        transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.fragmentBorC, frag_raw_view);
//        transaction.commitNow();
        System.out.println("첫페이지 실행");


        tv_logout = (TextView) findViewById(R.id.Main_tv_logout);
        tv_logout.setOnClickListener(logoutClick);
        tv_staffNm = (TextView) findViewById(R.id.Main_tv_staffNm);
        tv_staffNm.setText(compInfo.getStaffNm());


        llo_subMenu = (LinearLayout) findViewById(R.id.Main_llo_submenu);
        llo_topMenu = (LinearLayout) findViewById(R.id.Main_llo_Topmene);

        TopMenuCreate();
    }


    public static void reverseByteArray(byte[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentBorC, fragment).commit();


    }

    View.OnClickListener logoutClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Log Out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, loginActivity.class);
            DBInfo.mainConn = null;
            startActivity(intent);
            finish();
        }
    };


    Button TopMenu;
    Button SubMenu;

    public void TopMenuCreate() {

        try {
            jsonArray = null;
            jsonArray = TopMenuSelect(jsonArray, "");
            dbInfo = new DBInfo();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);

                String TopID = "";
                String TopName = "";


                if (jo.has("TopID"))
                    TopID = jo.getString("TopID");
                if (jo.has("TopName"))
                    TopName = jo.getString("TopName");

                TopMenu = new Button(context);
                TopMenu.setId(Integer.parseInt(TopID));
                TopMenu.setText(TopName);
                TopMenu.setTag(TopID);

                TopMenu.setTextSize(25);

                TopMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jsonArray = null;

                        try {
                            System.out.println(v.getId());
                            dbInfo = new DBInfo();
                            jsonArray = SubMenuSelect(jsonArray, String.valueOf(v.getId()));
                            llo_subMenu.removeAllViews();// 뷰 클리어

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);

                                String SubID = "";
                                String SubName = "";
                                String path="";

                                if (jo.has("SubID"))
                                    SubID = jo.getString("SubID");
                                if (jo.has("SubName"))
                                    SubName = jo.getString("SubName");
                                if (jo.has("path"))
                                    path = jo.getString("path");
                                SubMenu = new Button(context);
                                SubMenu.setId(Integer.parseInt(SubID));
                                SubMenu.setText(SubName);
                                SubMenu.setTag(path);


                                SubMenu.setTextSize(19);
                                SubMenu.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Fragment fragment = null;
                                        try {
                                            dbInfo = new DBInfo();
                                            System.out.println(v.getTag().toString());
                                            fragment = (Fragment) Class.forName("com.mes_app."+v.getTag().toString()).newInstance();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        replaceFragment(fragment);

                                    }
                                });
                                llo_subMenu.addView(SubMenu, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


                llo_topMenu.addView(TopMenu, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }


        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }


    private JSONArray TopMenuSelect(JSONArray Jarray, String condition) throws SQLException, JSONException {

        StringBuilder sb = new StringBuilder();

        sb.append("Select TopID,TopName ");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[M_TopMenu]   \n");
        System.out.println(sb.toString());
        Jarray = dbInfo.SelectDB(sb.toString());

        return Jarray;
    }

    private JSONArray SubMenuSelect(JSONArray Jarray, String condition) throws SQLException, JSONException {

        StringBuilder sb = new StringBuilder();

        sb.append("Select A.SubName,A.SubID,A.TopID,B.TopName,A.path \n ");
        sb.append(" from [" + dbInfo.Location + "].[dbo].[M_SubMenu]  as A \n");
        sb.append("inner join [" + dbInfo.Location + "].[dbo].[M_TopMenu] as B on A.TopID=B.TopID \n");
        sb.append("where 1=1 and \n");
        sb.append("A.TopID='" + condition + "'");
        System.out.println(sb.toString());
        Jarray = dbInfo.SelectDB(sb.toString());

        return Jarray;
    }
}


