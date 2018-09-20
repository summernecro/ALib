package com.siweisoft.app.main;

//by summer on 2018-03-29.

import android.content.res.ColorStateList;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.siweisoft.app.R;
import com.siweisoft.app.databinding.ActMainBinding;

import java.util.ArrayList;

public class MainUIOpe extends BaseUIOpe<ActMainBinding>{

    @Override
    public void initUI() {
        super.initUI();
        ArrayList<BottomMenuBean> bottomMenuBeans = new ArrayList<>();
        bottomMenuBeans.add(new BottomMenuBean("视频", R.drawable.video_icon,null,null, ColorStateList.valueOf(R.color.black)));
        bottomMenuBeans.add(new BottomMenuBean("图片", R.drawable.video_icon,null,null, ColorStateList.valueOf(R.color.black)));
        bottomMenuBeans.add(new BottomMenuBean("文字", R.drawable.video_icon,null,null, ColorStateList.valueOf(R.color.black)));
        bottomMenuBeans.add(new BottomMenuBean("设置", R.drawable.video_icon,null,null, ColorStateList.valueOf(R.color.black)));
        getBind().bottommenu.initItems(bottomMenuBeans);
    }
}
