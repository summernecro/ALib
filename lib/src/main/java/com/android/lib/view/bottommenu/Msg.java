package com.android.lib.view.bottommenu;

import com.android.lib.util.GsonUtil;

public class Msg {

    public int position;

    public String sender;

    public String dealer;//sender

    public Object data;

    public Object id;

    public boolean isme = true;

    public Object ext;



    public Msg() {
    }

    public Msg(String sender, String dealer, Object data) {
        this.sender = sender;
        this.dealer = dealer;
        this.data = data;
    }

    @Override
    public String toString() {
        return GsonUtil.getInstance().toJson(this);
    }
}