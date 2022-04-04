package com.hansung.android.androidproject1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthViewActivity extends AppCompatActivity {

    int year;
    int month;
    int day;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = findViewById(R.id.gridview);

        ArrayList<String> days = new ArrayList<String>();
        TextView YearMonthTV = findViewById(R.id.year_month);
        Calendar cal = Calendar.getInstance();

        Intent intent = getIntent();
        year = intent.getIntExtra("year", -1);
        month = intent.getIntExtra("month", -1);

        if (year == -1 || month == -1) {
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DATE);
        }
        //날짜 받아오기.

        cal.set(year, month, 1);
        day = cal.get(Calendar.DAY_OF_WEEK) - 1;
        //첫 날 요일 구하기. 0부터 시작해야해서 1을 빼줌.
        //  DAY_OF_WEEK는 Calendar 내 상수로 정의되어있음.

        for (int i = 0; i < day; i++) days.add("");
        // 1일의 앞에 빈 칸 만큼 비워둠.

        if (month == 1 && year % 4 != 0)
            for (int i = 1; i <= 28; i++)
                days.add("" + i);
        // 윤달이 아닌 해 2월 -28일
        if (month == 1 && year % 4 == 0)
            for (int i = 1; i <= 29; i++)
                days.add("" + i);
        // 윤달 일 시 2월 - 29일
        if (month == 3 || month == 5 || month == 8 || month == 10)
            for (int i = 1; i <= 30; i++)
                days.add("" + i);
        // 4,6,9,11월 - 30일
        if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11)
            for (int i = 1; i <= 31; i++)
                days.add("" + i);
        // 1,3,5,7,8,10,12월 - 31일

        YearMonthTV.setText(year + "년 " + (month + 1) + "월");
        //연월표시.

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.day,
                days);
        gridView.setAdapter(adapter);
        //달력 날짜부분 표시.

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (position >= day) { // 1일보다 앞이면 반응하지 않기.
                    Toast.makeText(MonthViewActivity.this,
                            year + "년" + (month + 1) + "월" + (position - day + 1)+ "일",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button Prevbtn = findViewById(R.id.previous_button);
        Button Nextbtn = findViewById(R.id.next_button);

        //이전 버튼 클릭시
        Prevbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bt_Intent = new Intent(getApplicationContext(), MonthViewActivity.class);
                if (month < 1) {
                    bt_Intent.putExtra("year", year - 1);
                    bt_Intent.putExtra("month", 11);
                }//1월에서 이전 버튼을 누르면 이전 연도로 넘어감
                else {
                    bt_Intent.putExtra("year", year);
                    bt_Intent.putExtra("month", (month - 1));
                }
                startActivity(bt_Intent);
                finish();
            }
        });

        //다음 버튼 클릭시
        Nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bt_Intent = new Intent(getApplicationContext(), MonthViewActivity.class);
                if (month > 10) {
                    bt_Intent.putExtra("year", year + 1);
                    bt_Intent.putExtra("month", 0);
                }//12월에서 다음 버튼을 누르면 다음년도로 넘어감
                else {
                    bt_Intent.putExtra("year", year);
                    bt_Intent.putExtra("month", (month + 1));
                }
                startActivity(bt_Intent);
                finish();
            }
        });
    }
}
