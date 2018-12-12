package com.siweisoft.app.video;

//by summer on 2018-07-13.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.LogUtil;
import com.android.lib.base.fragment.FragUtil;
import com.android.lib.view.bottommenu.Msg;
import com.siweisoft.app.R;
import com.siweisoft.app.sett.SettAct;
import com.siweisoft.app.text.TextFrag;

import org.greenrobot.eventbus.EventBus;

public class VideoFrag extends BaseUIFrag<VideoUIOpe,VideoValue> {



    @Override
    public void initNow() {
        super.initNow();
        getPU().initList(getPV().getList(),this);
    }

    protected void update(Msg event) {
        LogUtil.E(event.toString());
        switch (event.sender){
            case "ItemVideoBinding":
//                getPV().getLoadUtil().onStartLoading(getBaseUIAct(),LoadUtil.INDICATORS[4][2],"加载中..","1");
//                HandleUtil.getInstance().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        getPV().getLoadUtil().onStopLoading("1");
//
//                    }
//                }, 2000);
                break;
        }
    }

    public void onClick(View v) {
        super.onClick(v);
        EventBus.getDefault().post(new Msg("ItemVideoBinding",getUniqueid()+"",v.getTag(R.id.data)));
    }

    @Override
    protected boolean isRegistEventBus() {
        return true;
    }
}
