package com.siweisoft.app.text;

//by summer on 2018-07-13.

import android.graphics.Color;
import android.view.View;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.RandomUtil;
import com.github.florent37.viewanimator.ViewAnimator;
import com.siweisoft.app.databinding.ActTextBinding;

public class TextUIOpe extends BaseUIOpe<ActTextBinding>{

    @Override
    public void initUI() {
        super.initUI();
        getBind().getRoot().setBackgroundColor(Color.rgb(255*RandomUtil.getInstance().nextFloat(),255*RandomUtil.getInstance().nextFloat(),255*RandomUtil.getInstance().nextFloat()));
    }

    @Override
    protected View[] initOnClick() {
        return new View[]{getBind().getRoot()};
    }
}
