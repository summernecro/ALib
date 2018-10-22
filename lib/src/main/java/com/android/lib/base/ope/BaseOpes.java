package com.android.lib.base.ope;

import java.io.Serializable;

/**
 * Created by ${viwmox} on 2016-12-21.
 */

public class BaseOpes<A extends BaseUIOpe,C extends BaseValue> implements Serializable {

    /**
     * 操作者
     */
    A ui;
    /**数据*/
    C va;




    public BaseOpes(A ui,C va) {
        this.ui = ui;

        this.va = va;
    }


    public A getU() {
        return ui;
    }

    public void setUi(A ui) {
        this.ui = ui;
    }

    public C getV() {
        return va;
    }

    public void setVa(C va) {
        this.va = va;
    }
}
