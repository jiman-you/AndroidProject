package com.hansung.android.androidproject1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class    MonthViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MonthViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonthViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthViewFragment newInstance(String param1, String param2) {
        MonthViewFragment fragment = new MonthViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate으로 fragment_month_view.xml 연결
        View rootview = inflater.inflate(R.layout.fragment_month_view, container, false);

        GridView day_of_the_week = rootview.findViewById(R.id.day_of_the_week_month);
        final String[] dayOfTheWeek = new String[]{"일", "월", "화", "수", "목", "금", "토"};
        ArrayAdapter<String> DOWadapter = new ArrayAdapter<>(getActivity(),
                R.layout.day_of_the_week,
                dayOfTheWeek);
        day_of_the_week.setAdapter(DOWadapter);

        //화면 전환을 위해 ViewPager2를 사용함.
        ViewPager2 vpPager = rootview.findViewById(R.id.month_vp1);
        //MonthCalendarAdapter에서 데이터 전달.
        FragmentStateAdapter adapter = new MonthCalendarAdapter(getActivity());
        vpPager.setAdapter(adapter);
        
        //MonthCalendarAdapter에서 데이터 가져옴.
        int year = ((MonthCalendarAdapter) adapter).year;
        int month = ((MonthCalendarAdapter) adapter).month;
        //초기 포지션 지정.
        vpPager.setCurrentItem(50);
        //초기 타이틀 설정.
        ((MonthViewActivity)getActivity()).getSupportActionBar().setTitle(year + "년 " + (month+1) + "월");
        //화면 전환시 타이틀 설정.
        //초기 position값이 50이기 때문에 12의 배수인 60에 맞추기 위해 10을 더해주고 계산.
        vpPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                ((MonthViewActivity) getActivity()).getSupportActionBar().setTitle((year + (month + position + 10) / 12 - 5) + "년 " + ((month + position + 10) % 12 + 1) + "월");

            }
        });
        return rootview;
    }
}