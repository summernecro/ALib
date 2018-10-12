package com.siweisoft.app.text;

//by summer on 2018-07-13.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.fragment.FragUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.system.HandleUtil;
import com.siweisoft.app.R;
import com.siweisoft.app.video.VideoFrag;
import com.siweisoft.app.video.VideoValue;

public class TextFrag extends BaseUIFrag<TextUIOpe,VideoValue> {


    public static TextFrag getInstance(String url){
        TextFrag textFrag = new TextFrag();
        textFrag.getPV().setUrl(url);
        return textFrag;
    }

    @Override
    public void initNow() {
        super.initNow();
        LogUtil.E(getPU().getView().getWidth());
        getPU().initimage(getPV().getUrl());
        //getPV().getLoadUtil().onStartLoading(getBaseUIAct(),"123");
       // getPU().initList(getPV().getList(),this);
        HandleUtil.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                //getPV().getLoadUtil().onStopLoading("123");
                //onClick(null);
            }
        }, 2000);
    }

    @Override
    protected boolean is注册事件总线() {
        return true;
    }

 /*   protected void update(Msg event) {
        LogUtil.E(event.toString());
        switch (event.sender){
            case "TextFrag":
                getPV().getLoadUtil().onStartLoading(getBaseUIAct(), LoadUtil.INDICATORS[4][2],"加载中..","1");
                HandleUtil.getInstance().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getPV().getLoadUtil().onStopLoading("1");
                        FragManager2.getInstance().start(getBaseUIAct(),"1", R.id.rootr,new TextFrag());
                    }
                }, 2000);
                break;
        }
    }*/

    @Override
    public void onClick(View v) {
        super.onClick(v);
//        getPV().getLoadUtil().onStartLoading(getBaseUIAct(), LoadUtil.INDICATORS[4][2],"加载中..","1");
//        HandleUtil.getInstance().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getPV().getLoadUtil().onStopLoading("1");
//            }
//        }, 500);
        FragUtil.getInstance().start(getBaseUIAct(),this, get容器(),TextFrag.getInstance(getPV().getUrl()));
    }

    @Override
    protected int delayTime() {
        return super.delayTime();
    }
}
