package com.siweisoft.app.text;

//by summer on 2018-07-13.

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.GlideApp;
import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.fragment.FragUtil;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.RandomUtil;
import com.github.florent37.viewanimator.ViewAnimator;
import com.siweisoft.app.BR;
import com.siweisoft.app.R;
import com.siweisoft.app.databinding.ActTextBinding;
import com.siweisoft.app.databinding.ItemTestBinding;

import java.util.ArrayList;

public class TextUIOpe extends BaseUIOpe<ActTextBinding>{

    @Override
    public void initUI() {
        super.initUI();
        getBind().getRoot().setBackgroundColor(Color.rgb(255*RandomUtil.getInstance().nextFloat(),255*RandomUtil.getInstance().nextFloat(),255*RandomUtil.getInstance().nextFloat()));
    }

    public void initimage(String url){
        GlideApp.with(getActivity()).asBitmap().load(url)
                .into(getBind().actText);
    }

    public void initList(ArrayList<Integer> list, View.OnClickListener listener){
        getBind().recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBind().recycle.setAdapter(new AppsDataBindingAdapter(getActivity(), R.layout.item_test, BR.item_test,list,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemTestBinding itemTestBinding = (ItemTestBinding) holder.viewDataBinding;
                GlideApp.with(context).asBitmap().load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539936186&di=2932df927f9adc77ddd1ff1f265b5058&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.wallcoo.com%2Fcartoon%2FKitsunenoir_Design_Illustration_III%2Fwallpapers%2F1920x1200%2Fandy-miller-st.jpg")
                        .into(itemTestBinding.image);
            }
        });
    }

    @Override
    protected View[] initOnClick() {
        return new View[]{getBind().getRoot()};
    }
}
