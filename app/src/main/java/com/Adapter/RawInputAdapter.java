package com.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.VO.OrderVo;
import com.VO.StorageVo;
import com.common.CompInfo;
import com.common.DBInfo;
import com.common.wnDm;
import com.example.mes_app.R;
import com.google.gson.JsonArray;
import com.mes_app.CustomDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RawInputAdapter extends BaseAdapter {

    Context context;
    OrderVo orderVo = new OrderVo();
    private ArrayList<OrderVo> arrayList = new ArrayList<>();

    DBInfo dbInfo;
    CompInfo compInfo;
    private Activity activity;
    private int layout;
    boolean flag = false;

    String Order_Date;
    String Order_Cd;
    String Order_Seq;

    wnDm wnDm;

    InputMethodManager imm;
    JSONArray jsonArray;
//    ArrayList<String> storageList;
//    ArrayAdapter<String> storageAdapter;

    StorageAdapter storageAdapter;
    StorageVo storageVo;
    ArrayList<StorageVo> storageList;


    public void addItem(OrderVo orderVo) {
        arrayList.add(orderVo);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        context = parent.getContext(); // activity 정보를 읽어오기
        orderVo = arrayList.get(position);
        dbInfo = new DBInfo();
        RawInputAdapter.ListViewHolder holder = null;

        final int adptPosition = position;

        final TextView raw_mat_nm;
        final TextView cust_nm;
        final TextView order_date;
        final TextView spec;
        final TextView order_amt;
        final TextView orderNon_amt;
        final EditText input_amt;
        final Button btn_input;
        final Spinner spin_storage;
        final Spinner spin_loc;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_raw_input, parent, false);

            raw_mat_nm = convertView.findViewById(R.id.rawInp_tv_rawNm);
            cust_nm = convertView.findViewById(R.id.rawInp_tv_custNm);
            order_date = convertView.findViewById(R.id.rawInp_tv_orderDate);
            spec = convertView.findViewById(R.id.rawInp_tv_spec);
            order_amt = convertView.findViewById(R.id.rawInp_tv_orderAmt);
            orderNon_amt = convertView.findViewById(R.id.rawInp_tv_nonInpAmt);
            input_amt = convertView.findViewById(R.id.rawInp_et_inpAmt);
            btn_input = convertView.findViewById(R.id.rawInp_btn_input);
            spin_storage = convertView.findViewById(R.id.rawInp_Spin_Storage);


            holder = new RawInputAdapter.ListViewHolder();

            holder.raw_mat_nm = raw_mat_nm;
            holder.cust_nm = cust_nm;
            holder.order_date = order_date;
            holder.spec = spec;
            holder.order_amt = order_amt;
            holder.orderNon_amt = orderNon_amt;
            holder.input_amt = input_amt;
            holder.btn_input = btn_input;
            holder.spin_storage = spin_storage;

            convertView.setTag(holder);

        } else {

            holder = (RawInputAdapter.ListViewHolder) convertView.getTag();

            raw_mat_nm = holder.raw_mat_nm;
            cust_nm = holder.cust_nm;
            order_date = holder.order_date;
            spec = holder.spec;
            order_amt = holder.order_amt;
            orderNon_amt = holder.orderNon_amt;
            input_amt = holder.input_amt;
            btn_input = holder.btn_input;
            spin_storage = holder.spin_storage;

        }

        raw_mat_nm.setText(orderVo.getRawmat_Nm());
        cust_nm.setText(orderVo.getCust_Nm());
        cust_nm.setTag(orderVo.getCust_Cd());

        order_date.setText(orderVo.getOrder_Date()); // 발주서 DATE, CD ,SEQ
        order_date.setTag(orderVo.getOrder_Cd());
        order_date.setHint(orderVo.getOrder_Seq());

        order_amt.setText(orderVo.getOrder_Amt() + " " + orderVo.getUnit_Nm());
        order_amt.setTag(orderVo.getTax_cd());
        order_amt.setHint(orderVo.getVat_cd());
        orderNon_amt.setTag(orderVo.getUnit_Nm());
        orderNon_amt.setText(orderVo.getInput_NeedAmt() + " " + orderVo.getUnit_Nm());
        spec.setText(orderVo.getSpec());


        try { // 창고 스피너

            jsonArray = new JSONArray();
            jsonArray = getStorage(); //창고 정보 가져오기
            storageAdapter = new StorageAdapter();
            storageList = new ArrayList<>();
            if (jsonArray.length() != 0) {

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jo = jsonArray.getJSONObject(i);

                    String storage_cd = "";
                    String storage_nm = "";
                    String comment = "";

                    if (jo.has("STORAGE_CD")) // Data값이 NULL인 경우 빈값으로 처리
                        storage_cd = jo.getString("STORAGE_CD");
                    if (jo.has("STORAGE_NM"))
                        storage_nm = jo.getString("STORAGE_NM");
                    if (jo.has("COMMENT"))
                        comment = jo.getString("COMMENT");

                    storageVo = new StorageVo(storage_cd, storage_nm, comment);

                    storageAdapter.addItem(storageVo);

//                    storageList.add(0, storage_cd);
                }
            } else {
                spin_storage.setAdapter(null);
            }

//            storageAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, storageList);
            spin_storage.setAdapter(storageAdapter);
//            spin_storage.setSelection(0); // 기본값 설정

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        spin_storage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String storage_cd = storageAdapter.arrayList.get(position).getStorage_cd();
                if(position != 0)
                    Toast.makeText(context,storage_cd,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        input_amt.setText("0");

        input_amt.setOnKeyListener(onKeyListener);
        input_amt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && input_amt.getText().toString().equals("0")) {
                    input_amt.setText("");
                } else if (!hasFocus && input_amt.getText().toString().equals("0")) {
                    input_amt.setText("0");
                } else if (!hasFocus && input_amt.getText().toString().equals("")) {
                    input_amt.setText("0");
                }
            }
        });

        btn_input.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Order_Date = order_date.getText().toString();
                Order_Cd = order_date.getTag().toString();
                Order_Seq = order_date.getHint().toString();


                final double Dneedamt;
                final double Dinputamt;

                if (input_amt.getText().toString().contains(" ")) {
                    String[] sTemp = input_amt.getText().toString().split(" ");
                    Dinputamt = Double.parseDouble(sTemp[0]);
                } else if (input_amt.getText().toString().equals("")) {
                    Dinputamt = 0;
                } else {
                    Dinputamt = Double.parseDouble(input_amt.getText().toString());
                }
                if (orderNon_amt.getText().toString().contains(" ")) {
                    String[] sTemp2 = orderNon_amt.getText().toString().split(" ");
                    Dneedamt = Double.parseDouble(sTemp2[0]);
                } else {
                    Dneedamt = Double.parseDouble(orderNon_amt.getText().toString());
                }

                if (Dinputamt == 0) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("입고 수량을 기입해주세요");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            return;
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                    return;
                }

                if (Dneedamt < Dinputamt) { // 입고량, 미입고량 비교

                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("현재 입고하려는 수량이 미입고 수량보다 많습니다. 진행하시겠습니까?");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                flag = Check(Order_Date, Order_Cd, Order_Seq); // 발주서 정상 확인

                                if (flag == true) {
                                    try {
                                        input_Logic(Order_Date, Order_Cd, Order_Seq);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(context, "발주서 조회 중 오류가 있습니다", Toast.LENGTH_LONG).show();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            input_amt.setText((int) Dneedamt + "");
                        }
                    });

//                    builder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                        }
//                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                } else {

                    try {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(cust_nm.getText().toString() + " | " + raw_mat_nm.getText().toString() + " " + input_amt.getText().toString()
                                + orderNon_amt.getTag() + "입고를 진행 하시겠습니까?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {

                                    flag = Check(Order_Date, Order_Cd, Order_Seq); // 발주서 정상 확인
                                    if (flag == true) {
                                        try {
                                            input_Logic(Order_Date, Order_Cd, Order_Seq);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        Toast.makeText(context, "발주서 조회 중 오류가 있습니다", Toast.LENGTH_LONG).show();
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                return;
                            }
                        });

//                    builder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                        }
//                    });
                        AlertDialog alert = builder.create();
                        alert.show();
                        flag = Check(Order_Date, Order_Cd, Order_Seq);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return convertView;
    }

    EditText.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        }
    };


    private class ListViewHolder {
        TextView raw_mat_nm;
        TextView cust_nm;
        TextView order_date;
        TextView spec;
        TextView order_amt;
        TextView orderNon_amt;
        EditText input_amt;
        Button btn_input;
        Spinner spin_storage;

    }

    private void input_Logic(String Order_date, String Order_cd, String Orcder_seq) throws SQLException, JSONException {
        Calendar cal = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String Today = sdf.format(cal.getTime()); // 오늘 날짜 ( #### - ## - ## )

        StringBuilder sb = new StringBuilder();

        String all_total_money = "0";
        String all_supply_money = "0";
        String all_tax_money = "0";

        sb.append("declare @seq int \n ");
        sb.append("select @seq =ISNULL(MAX(INPUT_CD),0)+1 from [" + dbInfo.Location + "].[dbo].[F_RAW_INPUT] \n ");
        sb.append("where INPUT_DATE = '" + Today + "' \n ");

        sb.append("declare @buy_seq int \n ");
        sb.append("select @buy_seq =ISNULL(convert(int,MAX(BUY_CD)),0)+1 from [" + dbInfo.Location + "].[dbo].[F_BUY] \n ");
        sb.append("where BUY_DATE = '" + Today + "' \n ");

        sb.append("insert into [" + dbInfo.Location + "].[dbo].[F_RAW_INPUT](\n ");
        sb.append("     INPUT_DATE\n ");
        sb.append("     ,INPUT_CD \n ");
        sb.append("     ,CUST_CD \n ");
        sb.append("     ,COMPLETE_YN \n ");
        sb.append("     ,COMMENT \n ");
        sb.append("     ,INSTAFF \n ");
        sb.append("     ,INTIME \n ");

        sb.append(" ) values ( \n ");
        sb.append("      '" + Today + "' \n ");
        sb.append("     ,@seq\n ");
        sb.append("     ,'" + orderVo.getCust_Cd() + "' \n ");
        sb.append("     ,'N' \n ");
        sb.append("     ,'모바일 입고' \n ");
        sb.append("     ,'" + compInfo.getStaffCd() + "' \n ");
        sb.append("     ,convert(varchar, getdate(), 120) \n ");
        sb.append(" ) \n ");

        sb.append("insert into [" + dbInfo.Location + "].[dbo].[F_BUY] (\n ");
        sb.append("     BUY_DATE\n ");
        sb.append("     ,BUY_CD \n ");
        sb.append("     ,BUY_GUBUN \n ");
        sb.append("     ,CUST_CD \n ");
        sb.append("     ,ALL_TOTAL_MONEY \n ");
        sb.append("     ,ALL_SUPPLY_MONEY \n ");
        sb.append("     ,ALL_TAX_MONEY \n ");
        sb.append("     ,INSTAFF \n ");
        sb.append("     ,INTIME \n ");
        sb.append("     ,VAT_CD \n ");
        sb.append("     ,INPUT_DATE \n ");
        sb.append("     ,INPUT_CD \n ");
        sb.append(" ) values ( \n ");
        sb.append("      '" + Today + "' \n ");
        sb.append("     ,@buy_seq\n ");
        sb.append("     ,'1' \n ");
        sb.append("     ,'" + orderVo.getCust_Cd() + "' \n ");
        sb.append("     ,'" + all_total_money.replace(",", "") + "' \n ");
        sb.append("     ,'" + all_supply_money.replace(",", "") + "' \n ");
        sb.append("     ,'" + all_tax_money.replace(",", "") + "' \n ");
        sb.append("     ,'" + compInfo.STAFF_CD + "' \n ");
        sb.append("     ,convert(varchar, getdate(), 120) \n ");
        sb.append("     ,'" + orderVo.getVat_cd() + "' \n ");
        sb.append("     ,'" + Today + "' \n ");
        sb.append("     ,@seq \n ");
        sb.append(" ) \n ");
        wnDm = new wnDm();
        boolean isCustDay = wnDm.isCustDayTotal(Today, orderVo.getCust_Cd());

        if(!isCustDay){
            sb.append("insert into [" + dbInfo.Location + "].[dbo].[T_CUST_DAY_TOTAL](\n ");
            sb.append("     INPUT_DATE \n ");
            sb.append("     ,CUST_CD \n ");
            sb.append(" ) values ( \n ");
            sb.append("      '" + Today + "'  \n ");
            sb.append("      ,'" + orderVo.getCust_Cd() + "'  \n ");
            sb.append(" ) \n ");
        }


        dbInfo.Insert(sb.toString());
    }

    private boolean Check(String Order_date, String Order_cd, String Order_seq) throws SQLException, JSONException {


        StringBuilder sb = new StringBuilder();
        JSONArray jsonArray;

        sb.append(" select A.ORDER_DATE,A.ORDER_CD,B.SEQ,C.ORDER_AMT, C.INPUT_AMT\n ");
        sb.append(" FROM [" + dbInfo.Location + "].[dbo].[F_ORDER] A \n ");
        sb.append(" LEFT OUTER JOIN [" + dbInfo.Location + "].[dbo].[F_ORDER_DETAIL] B  \n ");
        sb.append(" ON A.ORDER_DATE = B.ORDER_DATE \n ");
        sb.append("     AND A.ORDER_CD = B.ORDER_CD \n ");
        sb.append(" LEFT OUTER JOIN(	 \n ");
        sb.append("                     SELECT AA.ORDER_DATE	 \n ");
        sb.append("                            ,AA.ORDER_CD       \n ");
        sb.append("                            ,AA.SEQ \n ");
        sb.append("                            ,FLOOR(ISNULL(AA.TOTAL_AMT,0)) AS ORDER_AMT \n ");
        sb.append("                            ,ISNULL(SUM(BB.TOTAL_AMT),0) AS INPUT_AMT \n ");
        sb.append("                            ,ISNULL(AA.TOTAL_AMT,0)-ISNULL(SUM(BB.TOTAL_AMT),0) AS NO_INPUT_AMT \n ");
        sb.append("                     FROM [" + dbInfo.Location + "].[dbo].[F_ORDER_DETAIL] AA \n ");
        sb.append("                     LEFT OUTER JOIN [" + dbInfo.Location + "].[dbo].[F_RAW_DETAIL] BB \n ");
        sb.append("                     ON AA.ORDER_DATE = BB.ORDER_DATE \n ");
        sb.append("                     AND AA.ORDER_CD = BB.ORDER_CD \n ");
        sb.append("                     AND AA.SEQ = BB.ORDER_SEQ \n ");
        sb.append("                     GROUP BY AA.ORDER_DATE,AA.ORDER_CD,AA.SEQ,AA.TOTAL_AMT) C \n ");
        sb.append(" ON A.ORDER_DATE = C.ORDER_DATE  \n ");
        sb.append("     AND A.ORDER_CD = C.ORDER_CD \n ");
        sb.append("     AND B.SEQ = C.SEQ  \n ");
        sb.append(" WHERE A.ORDER_DATE = '" + Order_date + "' \n ");
        sb.append("      AND A.ORDER_CD = '" + Order_cd + "' \n ");
        sb.append("      AND B.SEQ = '" + Order_seq + "' \n ");

        jsonArray = dbInfo.SelectDB(sb.toString());

        if (jsonArray.length() == 0 || jsonArray == null) {
            return false;
        } else {
            return true;
        }
    }

    public JSONArray getStorage() throws SQLException, JSONException {

        JSONArray jsonArray = null;

        StringBuilder query = new StringBuilder();

        query.append("SELECT STORAGE_CD, STORAGE_NM, COMMENT FROM [" + dbInfo.Location + "].[dbo].[N_STORAGE_CODE]");
        jsonArray = dbInfo.SelectDB(query.toString());
        return jsonArray;

    }


}
