package com.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.VO.LocVo;
import com.VO.OrderVo;
import com.VO.StorageVo;
import com.common.CompInfo;
import com.common.DBInfo;
import com.common.wnDm;
import com.example.mes_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RawInputAdapter extends BaseAdapter {

    Context context;
    OrderVo orderVo;
    private ArrayList<OrderVo> arrayList = new ArrayList<>();

    DBInfo dbInfo;
    CompInfo compInfo;
    private Activity activity;
    private int layout;
    boolean flag = false;

    String Order_Date;
    String Order_Cd;
    String Order_Seq;
    String Storage_cd;
    String Loc_cd;
    String Loc_nm;

    wnDm wnDm;

    InputMethodManager imm;
    JSONArray jsonArray;
//    ArrayList<String> storageList;
//    ArrayAdapter<String> storageAdapter;

    StorageAdapter storageAdapter;
    StorageVo storageVo;
    ArrayList<StorageVo> storageVoArrayList;

    LocAdapter locAdapter;
    LocVo locVo;
    ArrayList<LocVo> locVoArrayList;


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
        orderVo = new OrderVo();
        orderVo = arrayList.get(position);
        dbInfo = new DBInfo();
        RawInputAdapter.ListViewHolder holder = null;

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
            spin_loc = convertView.findViewById(R.id.rawInp_Spin_Loc);


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
            holder.spin_loc = spin_loc;

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
            spin_loc = holder.spin_loc;

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
            storageVoArrayList = new ArrayList<>();
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
                spin_storage.setAdapter(storageAdapter);
            } else {
                spin_storage.setAdapter(null);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        spin_storage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String storage_cd = storageAdapter.arrayList.get(position).getStorage_cd();
                try {
                    jsonArray = new JSONArray();
                    jsonArray = getLoc(storage_cd); //위치 정보 가져오기
                    if (jsonArray.length() != 0 || jsonArray == null) {

                        locAdapter = new LocAdapter();
                        locVoArrayList = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jo = jsonArray.getJSONObject(i);

                            String loc_cd = "";
                            String loc_nm = "";
                            String comment = "";

                            if (jo.has("LOC_CD")) // Data값이 NULL인 경우 빈값으로 처리
                                loc_cd = jo.getString("LOC_CD");
                            if (jo.has("LOC_NM"))
                                loc_nm = jo.getString("LOC_NM");
                            if (jo.has("COMMENT"))
                                comment = jo.getString("COMMENT");

                            locVo = new LocVo(storage_cd, loc_cd, loc_nm, comment);

                            locAdapter.addItem(locVo);
                        }
                        spin_loc.setAdapter(locAdapter);
                    } else {
                        spin_loc.setAdapter(null);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
                    input_amt.setText("0\n ");
                } else if (!hasFocus && input_amt.getText().toString().equals("")) {
                    input_amt.setText("0\n ");
                }
            }
        });

        btn_input.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                orderVo = arrayList.get(position);
                Order_Date = order_date.getText().toString();
                Order_Cd = order_date.getTag().toString();
                Order_Seq = order_date.getHint().toString();

                int position2 = spin_storage.getSelectedItemPosition();
                int position3 = spin_loc.getSelectedItemPosition();

                Storage_cd = storageAdapter.arrayList.get(position2).getStorage_cd();
                Loc_cd = locAdapter.arrayList.get(position3).getLoc_cd();
                Loc_nm = locAdapter.arrayList.get(position3).getLoc_nm();

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

                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        // ----
                        public void onClick(DialogInterface dialog, int id) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage(cust_nm.getText().toString() + " / " + raw_mat_nm.getText().toString() + " / " + input_amt.getText().toString() +
                                    orderVo.getUnit_Nm() + " 입고를 진행 하시겠습니까?");
                            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    try {
                                        flag = Check(Order_Date, Order_Cd, Order_Seq);
                                        if (flag) {
                                            flag = input_Logic(orderVo, Storage_cd, Loc_cd, Loc_nm, input_amt.getText().toString());
                                            if (flag) {
                                                Toast.makeText(context, "입고 완료", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(context, "입고 실패", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            Toast.makeText(context, "발주서 조회 오류", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                            return;
                        }
                        //           ----//
                    });

                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                    return;
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(cust_nm.getText().toString() + " / " + raw_mat_nm.getText().toString() + " / " + input_amt.getText().toString() +
                            orderVo.getUnit_Nm() + " 입고를 진행 하시겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                flag = Check(Order_Date, Order_Cd, Order_Seq);
                                if (flag) {
                                    flag = input_Logic(orderVo, Storage_cd, Loc_cd, Loc_nm, input_amt.getText().toString());
                                    if (flag) {
                                        Toast.makeText(context, "입고 완료", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(context, "입고 실패", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(context, "발주서 조회 오류", Toast.LENGTH_LONG).show();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    return;
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
        Spinner spin_loc;

    }

    private boolean input_Logic(OrderVo orderVo, String Storage_cd, String Loc_cd, String Loc_nm, String total_amt) throws SQLException, JSONException {

        boolean flag = true;

        Calendar cal = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String Today = sdf.format(cal.getTime()); // 오늘 날짜 ( #### - ## - ## )

        StringBuilder sb = new StringBuilder();

        String all_total_money = "0";
        String all_supply_money = "0";
        String all_tax_money = "0";

        sb.append("declare @seq int \n ");
        sb.append("select @seq =ISNULL(MAX(INPUT_CD),0)+1 from [" + dbInfo.Location + "].[dbo].[F_RAW_INPUT] \n");
        sb.append("where INPUT_DATE = '" + Today + "' \n ");

        sb.append("declare @buy_seq int \n ");
        sb.append("select @buy_seq =ISNULL(convert(int,MAX(BUY_CD)),0)+1 from [" + dbInfo.Location + "].[dbo].[F_BUY] \n");
        sb.append("where BUY_DATE = '" + Today + "' \n ");

        sb.append("insert into [" + dbInfo.Location + "].[dbo].[F_RAW_INPUT]( \n");
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

        if (!isCustDay) {
            sb.append("insert into [" + dbInfo.Location + "].[dbo].[T_CUST_DAY_TOTAL](\n ");
            sb.append("     INPUT_DATE \n ");
            sb.append("     ,CUST_CD \n ");
            sb.append(" ) values ( \n ");
            sb.append("      '" + Today + "'  \n ");
            sb.append("      ,'" + orderVo.getCust_Cd() + "'  \n ");
            sb.append(" ) \n ");
        }

        sb.append("declare @input_seq int, @chk_gbn  nvarchar(1), @chk_yn nvarchar(1), @final_amt nvarchar(20) \n ");
        sb.append("select @input_seq =ISNULL(MAX(SEQ),0)+1 from [" + dbInfo.Location + "].[dbo].[F_RAW_DETAIL] \n ");
        sb.append("where INPUT_DATE = '" + Today + "' \n ");
        sb.append("and INPUT_CD =  @seq \n ");

        sb.append("select @chk_gbn = check_gubun from [" + dbInfo.Location + "].[dbo].[N_RAW_CODE] \n ");
        sb.append("where RAW_MAT_CD = '" + orderVo.getRawmat_Cd() + "' \n ");

        sb.append("IF @chk_gbn = '1' BEGIN set @chk_yn = 'S' set @final_amt = '0' END \n "); //원자재 검사여부가 검사일 경우 'S' 대기
        sb.append("ELSE BEGIN set @chk_yn = 'O' set @final_amt = '" + total_amt + "' END \n "); //원자재 검사여부가 생략일 경우 'O'

//        sb.append("declare @buy_seq int \n ");
//        sb.append("select @buy_seq =ISNULL(convert(int,MAX(SEQ)),0)+1 from [" + dbInfo.Location + "].[dbo].[F_BUY_DETAIL] \n ");
//        sb.append("where BUY_DATE = '" + Today + "' \n ");
//        sb.append("and BUY_CD =  @buy_seq \n ");

        sb.append("insert into [" + dbInfo.Location + "].[dbo].[F_RAW_DETAIL](\n ");
        sb.append("     INPUT_DATE \n ");
        sb.append("     ,INPUT_CD \n ");
        sb.append("     ,SEQ \n ");
        sb.append("     ,RAW_MAT_CD \n ");
        sb.append("     ,SPEC \n ");
        sb.append("     ,UNIT_CD \n ");
        sb.append("     ,TEMP_AMT \n ");
        sb.append("     ,TOTAL_AMT \n ");
        sb.append("     ,CURR_AMT \n ");
        sb.append("     ,PRICE \n ");
        sb.append("     ,TOTAL_MONEY \n ");
        sb.append("     ,HEAT_NO \n ");
        sb.append("     ,HEAT_TIME \n ");
        sb.append("     ,ORDER_DATE \n ");
        sb.append("     ,ORDER_CD \n ");
        sb.append("     ,ORDER_SEQ \n ");
        sb.append("     ,COMPLETE_YN \n ");
        sb.append("     ,CHECK_YN \n ");
        sb.append("     ,INSTAFF \n ");
        sb.append("     ,INTIME \n ");
        sb.append("     ,COMMENT \n ");
        sb.append("     ,STORAGE_CD \n ");
        sb.append("     ,LOC_CD \n ");
        sb.append("     ,LOC_NM \n ");
        sb.append("  )values ( \n ");
        sb.append("     '" + Today + "' \n ");
        sb.append("     ,@seq \n ");
        sb.append("     ,@input_seq \n ");
        sb.append("     ,'" + orderVo.getRawmat_Cd() + "' \n ");
        sb.append("     ,'" + orderVo.getSpec() + "' \n ");
        sb.append("     ,'" + orderVo.getUnit_cd() + "' \n ");
        sb.append("     ,'" + total_amt.replace(",", "") + "' \n "); //가입고
        sb.append("     ,@final_amt \n "); //최종입고
        sb.append("     ,@final_amt \n "); //현재입고
        sb.append("     ,'0' \n ");
        sb.append("     ,'0' \n ");
        sb.append("     ,'' \n ");
        sb.append("     ,'' \n ");
        sb.append("     ,'" + orderVo.getOrder_Date() + "' \n ");
        sb.append("     ,'" + orderVo.getOrder_Cd() + "' \n ");
        sb.append("     ,'" + orderVo.getOrder_Seq() + "' \n ");
        sb.append("     ,'N' \n ");

        sb.append("     , @chk_yn  \n "); //BE
        sb.append("     ,'" + compInfo.getStaffCd() + "' \n ");
        sb.append("     ,convert(varchar, getdate(), 120)  \n ");
        sb.append("     ,'모바일 입고' \n ");
        sb.append("     ,'" + Storage_cd + "' \n ");
        sb.append("     ,'" + Loc_cd + "' \n ");
        sb.append("     ,'" + Loc_nm + "' \n ");
        sb.append("  )\n ");

        sb.append("insert into [" + dbInfo.Location + "].[dbo].[F_BUY_DETAIL](\n ");
        sb.append("      BUY_DATE   \n ");      //1
        sb.append("     ,BUY_CD    \n ");       //2
        sb.append("     ,SEQ    \n ");          //3
        sb.append("     ,RAW_MAT_CD    \n ");   //4
        sb.append("     ,INPUT_DATE    \n ");   //5
        sb.append("     ,INPUT_CD    \n ");     //6
        sb.append("     ,INPUT_SEQ    \n ");    //7
        sb.append("     ,TOTAL_AMT    \n ");    //8
        sb.append("     ,TOTAL_PRICE    \n ");  //9
        sb.append("     ,TOTAL_MONEY    \n ");  //10
        sb.append("     ,SUPPLY_MONEY    \n "); //11
        sb.append("     ,TAX_MONEY    \n ");    //12
        sb.append("     ,INTIME    \n ");       //13
        sb.append("     ,INSTAFF    \n ");      //14
        sb.append("     ,TAX_CD    \n ");       //15
        sb.append("  ) values ( \n ");
        sb.append("     '" + Today + "' \n ");                                                                   //1
        sb.append("      ,@buy_seq \n ");                                                                             // 2
        sb.append("     ,@buy_seq \n ");                                                                     //  3
        sb.append("     ,'" + orderVo.getRawmat_Cd() + "' \n ");                                 //  4
        sb.append("     ,'" + Today + "' \n ");                                                                  //  5
        sb.append("     ,@seq  \n ");                                                                                 //  6
        sb.append("     ,@input_seq \n ");                                                                  //  7
        sb.append("     ,'" + total_amt + "' \n ");       //  8
        sb.append("     ,'0' \n ");           //  9
        sb.append("     ,'0' \n ");
        sb.append("     ,'0' \n ");
        sb.append("     ,'0' \n ");
        sb.append("     ,convert(varchar, getdate(), 120)  \n ");                                                     //  13
        sb.append("     ,'" + compInfo.getStaffCd() + "' \n ");                                                         //  14
        sb.append("     ,'" + orderVo.getTax_cd() + "' \n ");                          //  15
        sb.append("  )\n ");


        sb.append(" update [" + dbInfo.Location + "].[dbo].[N_RAW_CODE] set \n ");
        sb.append("     BAL_STOCK = ISNULL(BAL_STOCK,0) +" + total_amt + " \n ");
        sb.append(" where RAW_MAT_CD = '" + orderVo.getRawmat_Cd() + "' \n ");


//        if (input_yn == "Y") // 원자재는 수입검사 생략이기에 이부분 패스
//        {
//            sb.append(" update F_ORDER set ");
//            sb.append("   COMPLETE_YN='Y'");
//            sb.append("   where ORDER_DATE='" + in_rm_dgv.Rows[i].Cells["ORDER_DATE"].Value + "' and ORDER_CD='" + in_rm_dgv.Rows[i].Cells["ORDER_CD"].Value + "'");
//
//        }
        flag = dbInfo.Insert(sb.toString());
        if (flag)
            return true;
        else
            return false;
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

        query.append("SELECT STORAGE_CD, STORAGE_NM, COMMENT FROM [" + dbInfo.Location + "].[dbo].[N_STORAGE_CODE] \n ");
        jsonArray = dbInfo.SelectDB(query.toString());
        return jsonArray;

    }

    public JSONArray getLoc(String Storage_cd) throws SQLException, JSONException {

        JSONArray jsonArray = null;

        StringBuilder query = new StringBuilder();

        query.append("SELECT STORAGE_CD, LOC_CD, LOC_NM, COMMENT FROM [" + dbInfo.Location + "].[dbo].[N_LOC_CODE] \n ");
        query.append("WHERE STORAGE_CD = '" + Storage_cd + "'\n ");
        jsonArray = dbInfo.SelectDB(query.toString());
        return jsonArray;

    }


}
