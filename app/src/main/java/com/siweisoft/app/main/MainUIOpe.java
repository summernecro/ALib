package com.siweisoft.app.main;

//by summer on 2018-03-29.

import android.content.res.ColorStateList;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.rd.animation.type.AnimationType;
import com.siweisoft.app.R;
import com.siweisoft.app.databinding.ActMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainUIOpe extends BaseUIOpe<ActMainBinding>{

    @Override
    public void initUI() {
        super.initUI();
    }

    public void initViewPager(List<Fragment> fragments){
        getBind().viewpager.setAdapter(new AppBasePagerAdapter2(getActivity().getSupportFragmentManager(),getActivity(),fragments));
        getBind().pageIndicatorView.setCount(fragments.size());
        getBind().pageIndicatorView.setViewPager(getBind().viewpager);
        getBind().pageIndicatorView.setSelectedColor(getActivity().getResources().getColor(R.color.black));
        getBind().pageIndicatorView.setAnimationType(AnimationType.SCALE);
    }

    @Override
    protected View[] initOnClick() {
        return new View[]{};
    }
}
