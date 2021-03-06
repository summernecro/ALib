package com.android.lib.service.main;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.android.lib.appthread.AppThread;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;

import androidx.annotation.Nullable;


/**
 * Created by ${viwmox} on 2016-07-12.
 */
public class AppService extends Service {


    int index = -1;

    private OnFinishListener onFinishListener;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.E("onStartCommand:"+System.currentTimeMillis());
        AppThread appThread = new AppThread(new OnFinishWithObjI() {
            @Override
            public void onNetFinish(Object o) {
                int i = (int) o;
                //isThisParentHaveChild(NoteBookID.BASE_PARENT_ID, name);
                LogUtil.E(getPackageName());
                Intent intent1 = new Intent(getPackageName() +"."+ ValueConstant.ACITON_GLOB_CAST);
                intent1.putExtra(ValueConstant.DATA_DATA, i);
                sendBroadcast(intent1);
            }
        });
        appThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (onFinishListener != null) {
            onFinishListener.onFinish(null);
        }
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }


    public static void startBackGround(Context context, BroadcastReceiver receiver){
        context.startService(new Intent(context,AppService.class));

        IntentFilter filter = new IntentFilter();
        filter.addAction(context.getPackageName() +"."+ ValueConstant.ACITON_GLOB_CAST);
        context.registerReceiver(receiver,filter);
    }
}
