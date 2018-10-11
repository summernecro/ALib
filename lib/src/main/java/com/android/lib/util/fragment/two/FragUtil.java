package com.android.lib.util.fragment.two;

//by summer on 2018-01-12.

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;
import com.github.florent37.viewanimator.AnimationListener;

import java.util.HashMap;

public class FragUtil {

    private static HashMap<String,Container> map = new HashMap<>();

    private boolean hideLast = true;


    public static FragUtil getInstance(){
        return new FragUtil();
    }




    public void start(BaseUIActivity activity, String moudle, int viewid, BaseUIFrag fragment, Bundle bundle){
        checkMap(moudle,viewid);
        checkArguments(fragment);
        fragment.getArguments().putAll(bundle);
        start(activity,moudle,viewid,fragment);
    }

    public void start(BaseUIActivity activity, String moudle, int viewid,BaseUIFrag fragment){
        checkMap(moudle,viewid);
        checkArguments(fragment);
        activity.setMoudle(moudle);
        fragment.getArguments().putString(ValueConstant.容器,moudle);
        fragment.getArguments().putInt(ValueConstant.VIEW_ID,viewid);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        if(map.get(moudle).haveLast()){
            if(hideLast){
            //transaction.hide(map.get(moudle).getLast());
                map.get(moudle).getLast().getPU().onBackOut();
            }
        }
        transaction.add(viewid,fragment,fragment.getUniqueid()+"");
        transaction.commitNowAllowingStateLoss();
        map.get(moudle).addFrag(fragment);
    }


    public void start(BaseUIActivity activity, String moudle,BaseUIFrag fragment){
       if(map.get(moudle)==null){
           return;
       }
       if( map.get(moudle).getViewid()==-1){
           return;
       }
       start(activity,moudle,map.get(moudle).getViewid(),fragment);
    }

    long aa = 0;

    public void start(BaseUIActivity activity, String moudle,BaseUIFrag fragment,Bundle bundle){
        aa = System.currentTimeMillis();
        checkArguments(fragment);
        fragment.getArguments().putAll(bundle);
        start(activity,moudle,fragment);
        LogUtil.E("startstart"+(System.currentTimeMillis()-aa));
    }



    public boolean finish(BaseUIActivity activity, final String moudle, boolean keepone){
        if(moudle==null || map.get(moudle)==null){
            return false;
        }
        if(keepone){
            if(!map.get(moudle).haveLastBefore()){
                return true;
            }
        }else{
            if(!map.get(moudle).haveLast()){
                return false;
            }
        }
        final FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        final Bundle bundle = map.get(moudle).getLast().getArguments();
        final int res = map.get(moudle).getLast().getArguments().getInt(ValueConstant.FARG_REQ);
        transaction.remove(map.get(moudle).getLast());
        if(map.get(moudle).haveLastBefore()){
            //transaction.show(map.get(moudle).getLastBefore());
            map.get(moudle).getLastBefore().getPU().onBackIn();
        }
        map.get(moudle).getLast().getPU().onRemove(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                map.get(moudle).removeLast();
                transaction.commitNowAllowingStateLoss();
                if(map.get(moudle).haveLast()){
                    map.get(moudle).getLast().onResult(res,bundle);
                }
            }
        });
        return true;
    }

    public void activityFinish(BaseUIActivity activity, String moudle,boolean keepone){
        if(!finish(activity,moudle,keepone)){
            activity.finish();
        }
    }


    public void clear(BaseUIActivity activity,String moudle){
        if(map.get(moudle)==null){
            return;
        }
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        while (map.get(moudle).haveLast()){
            transaction.remove(map.get(moudle).getLast());
            map.get(moudle).removeLast();
        }
        transaction.commitNowAllowingStateLoss();
    }

    public BaseUIFrag getCurrentFrag(String moudle){
       if(map.get(moudle)==null){
           return null;
       }
        LogUtil.E(moudle+""+map.size()+""+map.get(moudle).getUiUnit().size());
       return map.get(moudle).getLast();
    }



    private void checkMap(String moudle,int viewid){
        if(map.get(moudle)==null){
            map.put(moudle,new Container(moudle,viewid));
        }
    }

    private void checkArguments(BaseUIFrag baseUIFrag){
        if(baseUIFrag.getArguments()==null){
            baseUIFrag.setArguments(new Bundle());
        }
    }

    public void clear(){
        map = new HashMap<>();
    }


    public FragUtil setHideLast(boolean hideLast) {
        this.hideLast = hideLast;
        return this;
    }

    public int getMoudleFragSize(String moudle){
        if(moudle==null||map.get(moudle)==null){
            return 0 ;
        }
        return map.get(moudle).getUiUnit().size();
    }
}
