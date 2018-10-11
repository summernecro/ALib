package com.siweisoft.app.video;

//by summer on 2018-07-13.

import android.content.Intent;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseValue;
import com.android.lib.util.LoadUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.dialog.DialogUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.android.lib.util.fragment.two.FragUtil;
import com.android.lib.util.system.HandleUtil;
import com.android.lib.view.bottommenu.Msg;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.siweisoft.app.R;
import com.siweisoft.app.databinding.ItemDialogBinding;
import com.siweisoft.app.databinding.ItemVideoBinding;
import com.siweisoft.app.main.MainAct;
import com.siweisoft.app.sett.SettAct;
import com.siweisoft.app.text.TextAct;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;
import butterknife.Optional;

public class VideoAct extends BaseUIFrag<VideoUIOpe,VideoValue> {

    @Override
    public void initNow() {
        super.initNow();
        getPU().initList(getPV().getList());
    }

    protected void update(Msg event) {
        LogUtil.E(event.toString());
        switch (event.sender){
            case "ItemVideoBinding":
                getPV().getLoadUtil().onStartLoading(getBaseUIAct(),LoadUtil.INDICATORS[4][2],"加载中..","1");
                HandleUtil.getInstance().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getPV().getLoadUtil().onStopLoading("1");
                        FragUtil.getInstance().start(getBaseUIAct(),"1",R.id.rootr,new TextAct());
                    }
                }, 2000);
                break;
        }
    }

    public void onClick(View v) {
        super.onClick(v);
        Msg msg = new Msg(SettAct.class.getName(), TextAct.class.getName(),"ssssssssssssssdddddddddddd");
        EventBus.getDefault().post(msg);
    }

    @Override
    protected boolean is注册事件总线() {
        return true;
    }
}
