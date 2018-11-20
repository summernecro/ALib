package com.android.lib.view.textview;

//by summer on 2018-10-09.

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.android.lib.R;
import com.android.lib.util.ScreenUtil;

import androidx.annotation.Nullable;

public class SharpTextView extends TextView {




    public SharpTextView(Context context) {
        super(context);
        init();
    }

    public SharpTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){


        StateListDrawable stateListDrawable = new StateListDrawable();

        GradientDrawable gradientDrawable2 = (GradientDrawable) getResources().getDrawable(R.drawable.drawable_sharpe);
        gradientDrawable2.setStroke((int) (ScreenUtil.最小DIMEN*1), Color.RED);
        gradientDrawable2.setCornerRadius((int) (ScreenUtil.最小DIMEN*3));
        gradientDrawable2.setColor(Color.WHITE);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed},gradientDrawable2);

        GradientDrawable gradientDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.drawable_sharpe);
        gradientDrawable.setStroke((int) (ScreenUtil.最小DIMEN*1), Color.BLACK);
        gradientDrawable.setCornerRadius((int) (ScreenUtil.最小DIMEN*3));
        gradientDrawable.setColor(Color.YELLOW);
        stateListDrawable.addState(new int[]{},gradientDrawable);

        setBackground(stateListDrawable);

        int[][] states = new int[2][];
        states[0] = new int[] { android.R.attr.state_pressed};
        states[1] = new int[] {};
        ColorStateList colorStateList = new ColorStateList(states,new int[]{Color.RED,Color.BLACK});
        setTextColor(colorStateList);
    }


    private void init(int state,int[] stokewidth,int[] stokecolor,int[] solidecolor,int[] cornerradius,int[] textcolor){

        StateListDrawable stateListDrawable = new StateListDrawable();

        GradientDrawable gradientDrawable2 = (GradientDrawable) getResources().getDrawable(R.drawable.drawable_sharpe);
        gradientDrawable2.setStroke(stokewidth[0],stokecolor[0]);
        gradientDrawable2.setCornerRadius(cornerradius[0]);
        gradientDrawable2.setColor(solidecolor[0]);
        stateListDrawable.addState(new int[]{state},gradientDrawable2);

        GradientDrawable gradientDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.drawable_sharpe);
        gradientDrawable.setStroke(stokewidth[1], stokecolor[1]);
        gradientDrawable.setCornerRadius(cornerradius[1]);
        gradientDrawable.setColor(solidecolor[1]);
        stateListDrawable.addState(new int[]{},gradientDrawable);

        setBackground(stateListDrawable);

        int[][] states = new int[2][];
        states[0] = new int[] {state};
        states[1] = new int[] {};
        ColorStateList colorStateList = new ColorStateList(states,textcolor);
        setTextColor(colorStateList);

    }
}
