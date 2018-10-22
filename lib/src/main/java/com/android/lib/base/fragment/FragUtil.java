package com.android.lib.base.fragment;

//by summer on 2018-01-12.

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.Container;
import com.github.florent37.viewanimator.AnimationListener;

import java.util.HashMap;

public class FragUtil {

    private static HashMap<String,Container> map = new HashMap<>();

    private boolean hideLast = true;


    public static FragUtil getInstance(){
        return new FragUtil();
    }



    public void start(BaseUIActivity activity, BaseUIFrag current,String moudle, int viewid,BaseUIFrag fragment){
        checkMap(moudle,viewid);
        checkArguments(fragment);
        boolean havelast = map.get(moudle).haveLast();
        BaseUIFrag lastfrag = null;
        if(havelast){
            lastfrag = map.get(moudle).getLast();
        }

        activity.setMoudle(moudle);
        fragment.setLastFrag(current);
        fragment.setActivity(activity);
        fragment.initUI();
        fragment.getArguments().putString(ValueConstant.容器,moudle);
        fragment.getArguments().putInt(ValueConstant.VIEW_ID,viewid);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        transaction.add(viewid,fragment,fragment.getUniqueid()+"");
        transaction.commitNowAllowingStateLoss();
        if(havelast){
            if(hideLast){
                //transaction.hide(map.get(moudle).getLast());
                //lastfrag.getPU().onBackOut();
            }
        }
        map.get(moudle).addFrag(fragment);
    }


    public void start(BaseUIActivity activity, BaseUIFrag current, String moudle,BaseUIFrag fragment){
       if(map.get(moudle)==null){
           return;
       }
       if( map.get(moudle).getViewid()==-1){
           return;
       }
       start(activity,current,moudle,map.get(moudle).getViewid(),fragment);
    }




    public boolean finish(BaseUIActivity activity, final String moudle, boolean keepone){
        if(moudle==null || map.get(moudle)==null){
            return false;
        }
        boolean havelastbefore = map.get(moudle).haveLastBefore();
        final boolean havelast = map.get(moudle).haveLast();
        BaseUIFrag lastbefore = null;
        BaseUIFrag last = null;
        if(havelast){
            last = map.get(moudle).getLast();
        }
        if(havelastbefore){
            lastbefore = map.get(moudle).getLastBefore();
        }
        if(keepone){
            if(!havelastbefore){
                return true;
            }
        }else{
            if(!havelast){
                return false;
            }
        }
        final FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        final Bundle bundle = last.getArguments();
        final int res = last.getArguments().getInt(ValueConstant.FARG_REQ);
        transaction.remove(last);
        if(havelastbefore){
            //transaction.show(map.get(moudle).getLastBefore());
            lastbefore.getPU().onBackIn();
        }
        final BaseUIFrag finalLast = last;
        last.getPU().onRemove(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                map.get(moudle).removeLasUIUnit(finalLast);
                transaction.commitNowAllowingStateLoss();
                if(havelast){
                    finalLast.onResult(res,bundle);
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
        BaseUIFrag last = map.get(moudle).getLast();
        if(map.get(moudle)==null){
            return;
        }
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        while (map.get(moudle).haveLast()){
            transaction.remove(last);
            map.get(moudle).removeLasUIUnit(last);
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
