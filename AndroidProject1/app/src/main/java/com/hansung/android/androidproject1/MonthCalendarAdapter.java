package com.hansung.android.androidproject1;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;

public class MonthCalendarAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS=100;

    public MonthCalendarAdapter(FragmentActivity fragment) {
        super(fragment);
    }
    //어댑터에서 달력 정보를 받아 MonthViewfragment로 전달
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day;

    public Fragment createFragment(int position) {
        return MonthCalendarFragment.newInstance(year + ((month + position + 10) / 12 - 5), (month + position + 10) % 12);
    }

    public int getItemCount() {
        return NUM_ITEMS;
    }
}