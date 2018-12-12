package com.siweisoft.app.sett;

//by summer on 2018-07-13.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseValue;
import com.android.lib.util.LogUtil;
import com.android.lib.view.bottommenu.Msg;
import com.siweisoft.app.R;
import com.siweisoft.app.text.TextFrag;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;
import butterknife.Optional;

public class SettAct extends BaseUIFrag<SettUIOpe,BaseValue> {


    protected void update(Msg event) {
        LogUtil.E(event.toString());
    }


    @Optional
    @OnClick({R.id.act_sett})
    public void onClick(View v) {
        super.onClick(v);
        Msg msg = new Msg(SettAct.class.getName(), TextFrag.class.getName(),"ssssssssssssssdddddddddddd");
        EventBus.getDefault().post(msg);
    }

    @Override
    protected boolean isRegistEventBus() {
        return true;
    }
}
