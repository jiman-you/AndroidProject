package com.hansung.android.androidproject1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Year;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthCalendarFragment extends Fragment {
    int Last_day;
    int dayOfWeek;
    TextView sel_day;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int mParam1;
    private int mParam2;

    public MonthCalendarFragment() { }

    //PARAM1,2에 year,month값을 넣어줌.
    public static MonthCalendarFragment newInstance(int year, int month) {
        MonthCalendarFragment fragment = new MonthCalendarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //fragment_month_calendar을 레이아웃 설정.
        View rootview = inflater.inflate(R.layout.fragment_month_calendar, container, false);
        GridView gridView = rootview.findViewById(R.id.gridview);//그리드뷰 등록.
        //달력 정보 가져오기.
        Calendar cal = Calendar.getInstance();
        cal.set(mParam1, mParam2, 1);

        Last_day = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //해당 월의 마지막 날 구하기
        dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1; // 해당 월의 첫 날 요일 구하기.

        //총 배열-칸의 갯수 42개.
        String[] days = new String[42];

        //첫날 이전, 마지막날 이후에 빈칸 채워주기
        for (int i = 0; i < days.length; i++) {
            if(i<dayOfWeek||i>Last_day+dayOfWeek-1) days[i]= "";
            else days[i] = Integer.toString(i-dayOfWeek+1);
        }

        //monthday 레이아웃을 그리드뷰에 연결
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.monthday,
                days);
        gridView.setAdapter(adapter);
        //그리드뷰 클릭시
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(position>=dayOfWeek&&position<Last_day+dayOfWeek) { // 1의 요일보다 앞에 있을 때에는 클릭에 반응하지 않음
                    Toast.makeText(getActivity(),
                            mParam1 + "." + (mParam2 + 1) + "." + (position - dayOfWeek + 1),
                            Toast.LENGTH_SHORT).show();
                    if(sel_day!=null) sel_day.setBackgroundColor(Color.WHITE); //이전 선택된 텍스트 뷰 흰색으로 돌리기.
                    sel_day = v.findViewById(R.id.day_text);
                    sel_day.setBackgroundColor(Color.CYAN); //선택된 텍스트 뷰 CYAN으로 색변경.
                }
            }
        });
        return rootview;
    }
}