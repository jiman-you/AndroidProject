package com.hansung.android.androidproject1;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;

public class MonthViewActivity extends AppCompatActivity {
        //MonthViewActivity를 메인으로 다른 프래그먼트에 데이터 전달
        String mainDate;
        int mainYear;
        int mainMonth;
        int mainDay;
        int mainStartTime;
        int mainHour;
        int mainMinute;
        int mainMeridiem;
        int mainEndTime;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //시작 프래그먼트를 월간달력으로 지정.--첫 시작때 달력 불러오기 오류있음.
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, new MonthViewFragment()).commit();
        }

        //앱바연결
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }
            }
        });
    }
