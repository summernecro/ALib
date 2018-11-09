//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.android.lib.service.main;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.lib.R;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.util.data.DateFormatUtil;

import java.util.Date;
import java.util.List;

public class KeepLiveService extends Service {

    MediaPlayer mPlayer;
    int i = 0;

    public KeepLiveService() {
    }

    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1,new Notification()); //这个id不要和应用内的其他同志id一样，不行就写 int.maxValue()        //context.startForeground(SERVICE_ID, builder.getNotification());
        }
    }


    public int onStartCommand(Intent intent, int flags, int startId) {

        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.a60);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                Intent intent1 = new Intent(KeepLiveService.class.getSimpleName());
                intent1.putExtra("DATA_DATA", ++i);
                KeepLiveService.this.sendBroadcast(intent1);
            }
        });
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if(mPlayer!=null&&mPlayer.isPlaying()){
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
        super.onDestroy();
    }



    public static  void startBackGroundOne(Context context) {
        if(isServiceRunning(context,KeepLiveService.class.getName())){
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context, KeepLiveService.class));
        } else {
            context.startService(new Intent(context, KeepLiveService.class));
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(KeepLiveService.class.getSimpleName());
    }

    public static boolean isServiceRunning(Context mContext, String className) {

        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(30);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        Log.e("OnlineService：",className);
        for (int i = 0; i < serviceList.size(); i++) {
            Log.e("serviceName：",serviceList.get(i).service.getClassName());
            if (serviceList.get(i).service.getClassName().contains(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}