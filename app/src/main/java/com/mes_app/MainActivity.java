package com.mes_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.mes_app.R;
import com.google.android.material.tabs.TabItem;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction;
    raw_viewActivity frag_raw_view = new raw_viewActivity(); //프래그먼트 객채셍성
    raw_inputActivity frag_raw_input = new raw_inputActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dg
        //jg

        Button tab1 = (Button) findViewById(R.id.btn_rawmenu);
        Button tab2 = (Button) findViewById(R.id.btn_workmenu);
        Button tab3 = (Button) findViewById(R.id.btn_itemmenu);
        Button tab4 = (Button) findViewById(R.id.btn_setting);


        //프래그먼트 추가
        FragmentManager fragmentManager = getSupportFragmentManager();


        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentBorC, frag_raw_view);
        transaction.commitNow();
        System.out.println("첫페이지 실행");

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

//                                Intent intent = new Intent(MainActivity.this, raw_viewActivity.class);
//                                startActivity(intent);
                                try {


                                    replaceFragment(frag_raw_view);
                                } catch (Exception ex) {

                                    System.out.println(ex.toString());
                                }

                                break;

                            case R.id.menu_raw2:
                                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();

                                try {

                                    replaceFragment(frag_raw_input);
                                } catch (Exception ex) {

                                    System.out.println(ex.toString());
                                }


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


                                break;
                            case R.id.menu_item3:
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


                                break;
                            case R.id.menu_item3:
                                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();


                                break;


                        }
                        return true;
                    }


                });
                p.show(); // 메뉴를 띄우기


            }
        });

        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(
                        getApplicationContext(), // 현재 화면의 제어권자
                        v);
                getMenuInflater().inflate(R.menu.menu_setting, p.getMenu());
                // 이벤트 처리
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_setting1:
                                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();


                                break;

                            case R.id.menu_setting2:
                                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();

                                break;


                        }
                        return true;
                    }


                });
                p.show(); // 메뉴를 띄우기


            }
        });


    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentBorC, fragment).commit();
    }

}


