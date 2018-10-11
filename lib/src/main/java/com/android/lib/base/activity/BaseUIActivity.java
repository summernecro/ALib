package com.android.lib.base.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.R;
import com.android.lib.aplication.LibAplication;
import com.android.lib.base.ope.BaseOpes;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.base.ope.BaseValue;
import com.android.lib.util.LogUtil;
import com.android.lib.util.activity.ActivityUtil;
import com.android.lib.view.bottommenu.Msg;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by summer on 2016/4/16 0016 11:51.
 */
public abstract class BaseUIActivity<A extends BaseUIOpe,C extends BaseValue> extends AppCompatActivity implements View.OnClickListener{

    protected ViewGroup baseUIRoot;

    protected BaseOpes<A,C> opes;

    private String moudle;

    private ArrayList<String> moudles = new ArrayList<>();

    private long uniqueid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        synchronized (BaseUIActivity.class){
            uniqueid= System.currentTimeMillis();
            LogUtil.E("uniqueid:"+uniqueid);
        }
        if (getApplication() instanceof LibAplication) {
            ActivityUtil.getInstance().getActMap().put(getClass().getSimpleName(), getActivity());
            ActivityUtil.getInstance().getActList().add(getActivity());
        } else {
            LogUtil.E("你的application最好继承LibAplication以便享有方便的方法");
        }
        setContentView(getBaseUILayout());
        baseUIRoot = findViewById(R.id.act_base_root);
        getPU().added(baseUIRoot);
        getPU().setView(getBaseUIRoot());
        getPU().onStart();
        getP().getV().initValue();
        getP().getU().initUI();
        initNow();
        ButterKnife.bind(getActivity());
        if(registerEventBus()){
            EventBus.getDefault().register(this);
        }
    }

    protected void initNow(){

    }

    protected void animRoot(View root){
        //ViewAnimator.animate(root).duration(300).fadeIn().start();
    }

    protected int getBaseUILayout() {
        return R.layout.act_baseui;
    }


    public BaseOpes<A,C> getP() {
        if (opes == null) {
            opes= new BaseOpes<>(null,null);
            initcc(getClass());
            initaa(getClass());
        }
        return opes;
    }

    public A getPU(){
        return getP().getU();
    }


    public C getPV(){
        return getP().getV();
    }


    private void initcc(Class<?> c) {
        if (c == null) {
            opes.setVa((C)(new BaseValue()));
            return;
        }
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            Class<C> b = (Class<C>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[1];
            try {
                Constructor<C> bc = b.getConstructor();
                C cc = bc.newInstance();
                opes.setVa(cc);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.E(e.getMessage());
            }
        } else {
            initcc(c.getSuperclass());
        }
    }


    private void initaa(Class<?> c) {
        if (c == null) {
            opes.setUi((A)(new BaseUIOpe<ViewDataBinding>()));
            opes.getU().init(getActivity());
            return;
        }
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            Class<A> a = (Class<A>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[0];
            try {
                Constructor<A> ac = a.getConstructor();
                A aa = ac.newInstance();
                aa.init(getActivity());
                opes.setUi(aa);
            } catch (Exception e) {
                LogUtil.E(e.getMessage());
                e.printStackTrace();
            }
        } else {
            initaa(c.getSuperclass());
        }
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 消息总线处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealMesage(Msg event) {
        LogUtil.E(event.dealer + ":" + getClass().getName());
        if (!event.dealer.equals(uniqueid+"")) {
            event.isme = false;
            return;
        }
        update(event);
    }

    protected void update(Msg event){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moudles.clear();
        if(registerEventBus()){
            EventBus.getDefault().unregister(this);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected boolean registerEventBus(){
        return false;
    }

    public String getMoudle() {
        return moudle;
    }

    public void setMoudle(String moudle) {
        this.moudle = moudle;
        moudles.add(moudle);
    }

    public BaseUIActivity getActivity() {
        return this;
    }

    public ViewGroup getBaseUIRoot() {
        return baseUIRoot;
    }

    public ArrayList<String> getMoudles() {
        return moudles;
    }

}
