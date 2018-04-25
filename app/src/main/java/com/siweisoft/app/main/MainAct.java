package com.siweisoft.app.main;

//by summer on 2018-03-29.

import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.FragManager2;
//import com.raizlabs.android.dbflow.sql.language.Select;
import com.siweisoft.app.R;

import java.util.ArrayList;

import butterknife.OnClick;

public class MainAct extends BaseUIActivity<MainUIOpe,MainDAOpe> {

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


}
