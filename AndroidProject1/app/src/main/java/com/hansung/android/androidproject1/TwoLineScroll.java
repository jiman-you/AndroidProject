package com.hansung.android.androidproject1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;

//주간달력에 시간과 격자를 한번에 스크롤 하기위해 사용.
public class TwoLineScroll extends GridView
{
    boolean expanded = false;
    public TwoLineScroll(Context context)  {super(context);}
    public TwoLineScroll(Context context, AttributeSet attrs) {super(context, attrs);}
    public TwoLineScroll(Context context, AttributeSet attrs, int defStyle) {super(context, attrs, defStyle);}
    public boolean isExpanded(){return expanded;}
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (isExpanded())
        {
            int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
        }
        else {super.onMeasure(widthMeasureSpec, heightMeasureSpec);}
    }
    public void setExpanded(boolean expanded) {this.expanded = expanded;}
}