package com.android.lib.base.adapter;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.Fragment;



import android.view.ViewGroup;

import java.util.List;

/**
 * viewpager适配器的基类
 */
public class AppBasePagerAdapter2 extends FragmentPagerAdapter {

    /**
     * 上下文
     */
    protected Context context;

    protected List<Fragment> fragments;


    public AppBasePagerAdapter2(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
}
