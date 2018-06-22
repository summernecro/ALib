package com.android.lib.network.netadapter;

import android.content.Context;
import android.os.Handler;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.LoadUtil;


/**
 * Created by ${viwmox} on 2016-12-02.
 */
public abstract class DelayUINetAdapter extends UINetAdapter {


    Handler handler = new Handler();
    private int delay = 500;
    LoadUtil loadUtil = new LoadUtil();

    public DelayUINetAdapter(Context context, int delay) {
        super(context);
    }

    public DelayUINetAdapter(Context context) {
        super(context);
    }


    @Override
    public void onNetWorkReqFinish(boolean haveData, String url, final BaseResBean baseResBean) {
        if (!haveData) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onNetWorkResult(false, baseResBean);
                    loadUtil.onStopLoading(tag);
                }
            }, delay);
        } else {
            if (baseResBean.isException()) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onNetWorkResult(false, baseResBean);
                        loadUtil.onStopLoading(tag);
                    }
                }, delay);
            } else {
                if (baseResBean.getData() == null) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onNetWorkResult(true, baseResBean);
                            loadUtil.onStopLoading(tag);
                        }
                    }, delay);
                } else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onNetWorkResult(true, baseResBean);
                            loadUtil.onStopLoading(tag);
                        }
                    }, delay);
                }
            }
        }
    }
}
