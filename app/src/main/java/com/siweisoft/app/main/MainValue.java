package com.siweisoft.app.main;

//by summer on 2018-06-25.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseValue;
import com.android.lib.util.LoadUtil;
import com.siweisoft.app.R;
import com.siweisoft.app.text.TextFrag;
import com.siweisoft.app.video.VideoFrag;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class MainValue extends BaseValue {

    LoadUtil loadUtil = new LoadUtil();

    public LoadUtil getLoadUtil() {
        return loadUtil;
    }

    public void setLoadUtil(LoadUtil loadUtil) {
        this.loadUtil = loadUtil;
    }

    public static final String 视频 = "视频";

    public static final String 图片 = "图片";

    public static final String 文字 ="文字";



    public static final String[] 模块 = new String[]{视频,图片,文字};

    public static final int[] 模块ID = new int[]{R.id.contain_a,R.id.contain_b,R.id.contain_c};

    @Getter
    @Setter
    private ArrayList<BaseUIFrag> fragments = new ArrayList<>();
    @Getter
    @Setter
    private int pos = 0;

    public MainValue(){
        fragments.add(TextFrag.getInstance("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4154655165,2205424397&fm=26&gp=0.jpg"));
        fragments.add(TextFrag.getInstance("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539348786564&di=824f82d981502b72ca00530ca8155c22&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0191db57f52c52a84a0e282be8bf87.jpg%402o.jpg"));
        fragments.add(TextFrag.getInstance("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539348799294&di=cdf808cdd7b138daeff6b8c46bb2604f&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F018a75591bcce9a801216a3e64a70b.png"));
    }
}
