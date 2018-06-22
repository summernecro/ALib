package com.android.lib.network.netadapter;

import android.content.Context;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqCacheAdapter;
import com.android.lib.util.LoadUtil;


/**
 * Created by ${viwmox} on 2016-12-01.
 */
public abstract class UINetChacheAdapter extends OnNetWorkReqCacheAdapter {

    LoadUtil loadUtil = new LoadUtil();

    public UINetChacheAdapter(Context context) {
        super(context);
    }

    @Override
    public boolean onNetWorkReqStart(String tag, String reqjson) {
        loadUtil.onStartLoading(context, tag);
        return super.onNetWorkReqStart(tag, reqjson);
    }

    @Override
    public void onNetWorkReqFinish(boolean haveData, String url, BaseResBean baseResBean) {
        super.onNetWorkReqFinish(haveData, url, baseResBean);
        loadUtil.onStopLoading(tag);
    }


}
