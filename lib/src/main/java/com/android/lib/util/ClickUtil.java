package com.android.lib.util;

//by summer on 2018-10-12.

import android.view.View;

public class ClickUtil {

    private static ClickUtil instance;

    private View currentView;

    private long lastTime;

    public static ClickUtil getInstance(){
        if(instance==null){
            instance = new ClickUtil();
        }
        return instance;
    }

    public boolean init(View view){
        if(view==currentView&&((System.currentTimeMillis()-lastTime)<500)){
            return false;
        }
        currentView = view;
        lastTime = System.currentTimeMillis();
        return  true;
    }
}
