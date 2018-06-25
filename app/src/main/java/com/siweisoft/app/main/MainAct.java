package com.siweisoft.app.main;

//by summer on 2018-03-29.

import android.graphics.Color;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.network.news.NetGet;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.FragManager2;
//import com.raizlabs.android.dbflow.sql.language.Select;
import com.android.lib.view.loading.BallTrianglePathIndicator;
import com.siweisoft.app.R;

import java.util.ArrayList;

import butterknife.OnClick;

public class MainAct extends BaseUIActivity<MainUIOpe,MainDAOpe,MainValue> implements View.OnClickListener {

    public static long time = 0;
//    @Override
//    protected void initNow() {
//        super.initNow();
//
//        DBFlowModel dbFlowModel = new DBFlowModel();
//        dbFlowModel.name = "Ruomiz";
//        dbFlowModel.address = "Wuhan";
//        dbFlowModel.age = 100;
//        dbFlowModel.phone = 12345555;
//        dbFlowModel.save();
//
//        ArrayList<DBFlowModel> list = (ArrayList<DBFlowModel>) new Select().from(DBFlowModel.class).queryList();
//        for(int i=0;i<list.size();i++){
//            LogUtil.E(list.get(i).toString());
//        }
//        findViewById(R.id.tv_text).setOnClickListener(this);
//    }


    @Override
    protected void initNow() {
        super.initNow();
        findViewById(R.id.aaa).setOnClickListener(this);
        getP().getV().getLoadUtil().startLoadingDefault(this, (ViewGroup) getP().getU().getBind().bottommenu,getResources().getColor(R.color.color_red_500));
    }

    @Override
    public void onClick(View v) {
        NetGet.downLoadFile("http://www.summernecro.com:8888/record/20170629/20170626154234Wb7TFgsJ.jpg", Environment.getExternalStorageDirectory() + "/records/1.jpg", new NetAdapter(this) {
            @Override
            public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                super.onNetFinish(haveData, url, baseResBean);

            }
        });
    }
}
