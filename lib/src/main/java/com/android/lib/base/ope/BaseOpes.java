package com.android.lib.base.ope;

import java.io.Serializable;

/**
 * Created by ${viwmox} on 2016-12-21.
 */

public class BaseOpes<A extends BaseUIOpe, B extends BaseDAOpe,C extends BaseValue> implements Serializable {

    /**
     * 操作者
     */
    A ui;
    /**
     * 数据操作者
     */
    B da;

    /**数据*/
    C va;




    public BaseOpes(A ui, B da,C va) {
        this.ui = ui;
        this.da = da;
        this.va = va;
    }

    public B getD() {
        return da;
    }

    public void setDa(B da) {
        this.da = da;
    }

    public A getU() {
        return ui;
    }

    public void setUi(A ui) {
        this.ui = ui;
    }

    public C getVa() {
        return va;
    }

    public void setVa(C va) {
        this.va = va;
    }
}
