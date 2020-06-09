package com.mes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.mes_app.R;
import com.google.android.material.tabs.TabItem;

public class    MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dg
        //jg

        Button tab1 = (Button) findViewById(R.id.btn_rawmenu);
        Button tab2 = (Button) findViewById(R.id.btn_workmenu);
        Button tab3 = (Button) findViewById(R.id.btn_itemmenu);

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(
                        getApplicationContext(), // 현재 화면의 제어권자
                        v);
                getMenuInflater().inflate(R.menu.menu_raw, p.getMenu());
                // 이벤트 처리
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_raw1:
                                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();


                                break;

                            case R.id.menu_raw2:
                                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();

                                break;

                            case R.id.menu_raw3:
                                Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();

                                break;


                        }
                        return true;
                    }



                });
                p.show(); // 메뉴를 띄우기




            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(
                        getApplicationContext(), // 현재 화면의 제어권자
                        v);
                getMenuInflater().inflate(R.menu.menu_work, p.getMenu());
                // 이벤트 처리
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_work1:
                                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();


                                break;

                            case R.id.menu_work2:
                                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();

                                break;

                            case R.id.menu_work3:
                                Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();

                                break;
                            case R.id.menu_work4:
                                Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();

                                break;
                            case R.id.menu_work5:
                                Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();

                                break;


                        }
                        return true;
                    }



                });
                p.show(); // 메뉴를 띄우기




            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(
                        getApplicationContext(), // 현재 화면의 제어권자
                        v);
                getMenuInflater().inflate(R.menu.menu_item, p.getMenu());
                // 이벤트 처리
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_item1:
                                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();


                                break;
                            case R.id.menu_item2:
                                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();


                                break; case R.id.menu_item3:
                                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();


                                break;



                        }
                        return true;
                    }



                });
                p.show(); // 메뉴를 띄우기




            }
        });
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(
                        getApplicationContext(), // 현재 화면의 제어권자
                        v);
                getMenuInflater().inflate(R.menu.menu_item, p.getMenu());
                // 이벤트 처리
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_item1:
                                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();


                                break;
                            case R.id.menu_item2:
                                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();


                                break; case R.id.menu_item3:
                                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();


                                break;



                        }
                        return true;
                    }



                });
                p.show(); // 메뉴를 띄우기




            }
        });




    }




}


