package com.siweisoft.app.main;

//by summer on 2018-06-25.

import com.android.lib.base.ope.BaseValue;
import com.android.lib.util.LoadUtil;

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
}
