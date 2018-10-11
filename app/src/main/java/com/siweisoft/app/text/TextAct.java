package com.siweisoft.app.text;

//by summer on 2018-07-13.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseValue;
import com.android.lib.util.LoadUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.android.lib.util.fragment.two.FragUtil;
import com.android.lib.util.system.HandleUtil;
import com.android.lib.view.bottommenu.Msg;
import com.siweisoft.app.R;
import com.siweisoft.app.video.VideoValue;

public class TextAct extends BaseUIFrag<TextUIOpe,VideoValue> {


    @Override
    protected boolean is注册事件总线() {
        return true;
    }

 /*   protected void update(Msg event) {
        LogUtil.E(event.toString());
        switch (event.sender){
            case "TextAct":
                getPV().getLoadUtil().onStartLoading(getBaseUIAct(), LoadUtil.INDICATORS[4][2],"加载中..","1");
                HandleUtil.getInstance().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getPV().getLoadUtil().onStopLoading("1");
                        FragManager2.getInstance().start(getBaseUIAct(),"1", R.id.rootr,new TextAct());
                    }
                }, 2000);
                break;
        }
    }*/

    @Override
    public void onClick(View v) {
        super.onClick(v);
        getPV().getLoadUtil().onStartLoading(getBaseUIAct(), LoadUtil.INDICATORS[4][2],"加载中..","1");
        HandleUtil.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                getPV().getLoadUtil().onStopLoading("1");
                FragUtil.getInstance().start(getBaseUIAct(),"1", R.id.rootr,new TextAct());
            }
        }, 500);
    }
}
