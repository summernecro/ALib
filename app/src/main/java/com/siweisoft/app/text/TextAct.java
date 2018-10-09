package com.siweisoft.app.text;

//by summer on 2018-07-13.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseValue;
import com.android.lib.util.LogUtil;
import com.android.lib.view.bottommenu.Msg;

public class TextAct extends BaseUIFrag<TextUIOpe,BaseValue> {

    protected void update(Msg event) {
        LogUtil.E(event.toString());
    }


    @Override
    protected boolean is注册事件总线() {
        return true;
    }
}
