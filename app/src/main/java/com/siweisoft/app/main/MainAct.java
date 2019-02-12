package com.siweisoft.app.main;

//by summer on 2018-03-29.

import android.content.IntentFilter;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.network.news.NetGet;
import com.android.lib.service.main.KeepLiveService;
import com.android.lib.util.ToastUtil;
//import com.raizlabs.android.dbflow.sql.language.Select;
import com.android.lib.base.fragment.FragUtil;
import com.siweisoft.app.text.TextFrag;

public class MainAct extends BaseUIActivity<MainUIOpe,MainValue> implements View.OnClickListener,OnAppItemSelectListener {

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

    OneReceiver oneReceiver;

    @Override
    protected void initNow() {
        super.initNow();
//        Glide.get(getActivity()).clearMemory();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Glide.get(getActivity()).clearDiskCache();
//            }
//        }).start();
        //getP().getV().getLoadUtil().startLoadingDefault(this, (ViewGroup) getP().getU().getBind().bottommenu,getResources().getColor(R.color.color_red_500));
        getPU().initFrag(this,getPV().getFragments(),MainValue.模块ID,MainValue.模块);

        KeepLiveService.startBackGroundOne(this);
        oneReceiver = new OneReceiver();
        registerReceiver(oneReceiver, new IntentFilter(KeepLiveService.class.getSimpleName()));

        TextFrag textFrag = new TextFrag();

    }

    public void onClick(View v) {
        ToastUtil.getInstance().showShort(getActivity(),"fdfdfdf");
        NetGet.downLoadFile("http://222.186.36.75:8888/record/20170629/20170626154234Wb7TFgsJ.jpg", Environment.getExternalStorageDirectory() + "/records/1.jpg", new NetAdapter(this) {
            @Override
            public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                super.onNetFinish(haveData, url, baseResBean);

            }
        });
    }

    @Override
    public void onBackPressed() {
        FragUtil.getInstance().activityFinish(this,getMoudle(),true);
    }

    @Override
    public void onAppItemSelect(ViewGroup viewGroup, View view, int position) {
        getPU().showView(position,getPV().getPos());
        setMoudle(MainValue.模块[position]);
        getPV().setPos(position);
    }
}
