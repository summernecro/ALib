package com.siweisoft.app.video;

//by summer on 2018-07-13.

import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.view.ItemDecoration.MyItemDecoration2;
import com.siweisoft.app.BR;
import com.siweisoft.app.R;
import com.siweisoft.app.databinding.ActVideoBinding;
import com.siweisoft.app.databinding.ItemVideoBinding;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;

public class VideoUIOpe extends BaseUIOpe<ActVideoBinding>{

    public void initList(ArrayList<Integer> list, View.OnClickListener listener){
        getBind().actVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBind().actVideo.addItemDecoration(new MyItemDecoration2(getActivity(),5));
        getBind().actVideo.setAdapter(new AppsDataBindingAdapter(getActivity(), R.layout.item_video, BR.itemVideo, list, listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemVideoBinding itemVideoBinding = (ItemVideoBinding) holder.viewDataBinding;
//                GlideApp.with(getActivity()).asBitmap().centerCrop()
//                        .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539001808886&di=e499e91ee53e9c2585e3df0d47be0c4a&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F5%2F56f0e9ca2ccae.jpg")
//                        .into(itemVideoBinding.image);
            }
        });
    }
}
