package com.mes_app;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mes_app.R;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction;
    raw_viewActivity frag_raw_view = new raw_viewActivity(); //프래그먼트 객채셍성
    raw_inputActivity frag_raw_input = new raw_inputActivity();
    work_viewActivity frag_work_view = new work_viewActivity();

    Button tab1;//메뉴바
    Button tab2;
    Button tab3;
    Button tab4;


    FragmentManager fragmentManager;

    InputMethodManager imm; //키보드 내리기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dg
        //jg

        tab1 = (Button) findViewById(R.id.btn_rawmenu);
        tab2 = (Button) findViewById(R.id.btn_workmenu);
        tab3 = (Button) findViewById(R.id.btn_itemmenu);
        tab4 = (Button) findViewById(R.id.btn_setting);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //키보드 내리기
        //프래그먼트 추가
        fragmentManager = getSupportFragmentManager();


        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentBorC, frag_raw_view);
        transaction.commitNow();
        System.out.println("첫페이지 실행");

        tab1.setOnClickListener(menuClickListenr);
        tab2.setOnClickListener(menuClickListenr);
        tab3.setOnClickListener(menuClickListenr);
        tab4.setOnClickListener(menuClickListenr);

    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentBorC, fragment).commit();
    }
    View.OnClickListener menuClickListenr = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            PopupMenu p = new PopupMenu(
                    getApplicationContext(), // 현재 화면의 제어권자
                    v);

            System.out.print(v.getId());
            switch (v.getId()) {
                case R.id.btn_rawmenu:
                    getMenuInflater().inflate(R.menu.menu_raw, p.getMenu());

                    break;
                case  R.id.btn_workmenu:
                    getMenuInflater().inflate(R.menu.menu_work, p.getMenu());

                    break;
                case R.id.btn_itemmenu:
                    getMenuInflater().inflate(R.menu.menu_item, p.getMenu());
                    break;
                case R.id.btn_setting:
                    getMenuInflater().inflate(R.menu.menu_setting, p.getMenu());
                    break;
                default:
                    break;
            }


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

                        case R.id.menu_work1:
                            Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();

                            try {

                                replaceFragment(frag_work_view);
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
    };

}


