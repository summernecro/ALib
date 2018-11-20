package com.android.lib.view.viewpager;

//by summer on 2018-03-21.

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.AttributeSet;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.listener.BaseOnPagerChangeListener;

import java.util.ArrayList;

public class MyViewPager extends ViewPager {

    public interface OnPageSelected{
        public void onPageSelected(int position);
    }


    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(@Nullable PagerAdapter adapter) {
        super.setAdapter(adapter);
    }

    public void init(BaseUIFrag frag, ArrayList<Fragment> fragments, final OnPageSelected onPageSelected){
        setOffscreenPageLimit(fragments.size());
        setAdapter(new AppBasePagerAdapter2(frag.getChildFragmentManager(),frag.getBaseUIAct(),fragments));
        final BaseOnPagerChangeListener baseOnPagerChangeListener = new BaseOnPagerChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                onPageSelected.onPageSelected(position);
            }
        };
        addOnPageChangeListener(baseOnPagerChangeListener);
        if(fragments.size()>0){
            post(new Runnable() {
                @Override
                public void run() {
                    baseOnPagerChangeListener.onPageSelected(0);
                }
            });
        }
    }
}
