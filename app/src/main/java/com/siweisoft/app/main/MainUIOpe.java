package com.siweisoft.app.main;

//by summer on 2018-03-29.

import androidx.fragment.app.Fragment;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.fragment.FragUtil;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.siweisoft.app.R;
import com.siweisoft.app.databinding.ActMainBinding;

import java.util.ArrayList;

public class MainUIOpe extends BaseUIOpe<ActMainBinding>{


    ArrayList<BottomMenuBean> bottomMenuBeans = new ArrayList<>();

    @Override
    public void initUI() {
        super.initUI();

        bottomMenuBeans.add(new BottomMenuBean("视频", R.drawable.drawable_record_main_bottom_video,null,getBind().containA,getActivity().getResources().getColorStateList(R.color.color_white_black)));
        bottomMenuBeans.add(new BottomMenuBean("图片", R.drawable.drawable_record_main_bottom_video,null,getBind().containB,getActivity().getResources().getColorStateList(R.color.color_white_black)));
        bottomMenuBeans.add(new BottomMenuBean("文字", R.drawable.drawable_record_main_bottom_video,null,getBind().containC,getActivity().getResources().getColorStateList(R.color.color_white_black)));
        getBind().bottommenu.initItems(bottomMenuBeans);
        if(getActivity() instanceof OnAppItemSelectListener){
            getBind().bottommenu.setOnAppItemClickListener((OnAppItemSelectListener)getActivity());
            getBind().bottommenu.setIndex(0);
        }

    }

    public void initFrag(BaseUIActivity activity, ArrayList<BaseUIFrag> fragments, int[] 模块ID, String[] 模块){
        if(fragments.size()==0){
            return;
        }
        for(int i=0;i<fragments.size();i++){
            FragUtil.getInstance().start(activity,null,模块[i],模块ID[i],fragments.get(i));
        }
        showView(0,-1);
    }

    public void showView(int pos,int beforepos){
        if(beforepos==pos){
            return;
        }
        for(int i=0;i<bottomMenuBeans.size();i++){
            if(i==pos){
                bottomMenuBeans.get(i).getContainerView().setVisibility(View.VISIBLE);
            }else{
                final int finalI = i;
                bottomMenuBeans.get(finalI).getContainerView().setVisibility(View.GONE);
            }
        }
    }
    @Override
    protected View[] initOnClick() {
        return new View[]{};
    }
}
