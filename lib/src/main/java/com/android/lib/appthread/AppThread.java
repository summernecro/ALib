package com.android.lib.appthread;


import com.android.lib.base.interf.OnFinishWithObjI;

/**
 * Created by ${viwmox} on 2016-07-22.
 */
public class AppThread extends Thread {

    public  OnFinishWithObjI o;
    public boolean stop = true;
    public boolean pause = true;
    //时间间隔
    public long sleepTime = 10000;
    private int count = 0;

    public AppThread(OnFinishWithObjI o) {
        stop = false;
        pause = false;
        count = 0;
        this.o = o;
    }

    @Override
    public void run() {
        super.run();
        while (!stop) {
            if (!pause) {
                doThing(count);
                try {
                    Thread.sleep(sleepTime);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //具体事务
    public void doThing(int count) {
        if (o != null) {
            o.onNetFinish(count);
        }
    }

    public int getCount() {
        return count;
    }
}
