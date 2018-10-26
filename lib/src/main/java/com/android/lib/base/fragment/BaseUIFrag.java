package com.android.lib.base.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.android.lib.R;
import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.FragI;
import com.android.lib.base.ope.BaseOpes;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.base.ope.BaseValue;
import com.android.lib.constant.ValueConstant;
import com.android.lib.databinding.LayoutBaseuiBinding;
import com.android.lib.util.LoadUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.system.HandleUtil;
import com.android.lib.util.video.TipUtil;
import com.android.lib.view.bottommenu.Msg;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by summer on 2016/4/16 0016 16:03.
 */
public abstract class BaseUIFrag<A extends BaseUIOpe,C extends BaseValue> extends Fragment implements View.OnClickListener, View.OnLongClickListener {


    private Unbinder unbinder;

    private BaseOpes<A,C> opes;

    private FragIs fragIs = new FragIs();

    private boolean isFiistVisibleinit = false;

    protected ViewGroup baseUIRoot;


    private LoadUtil loadUtil = new LoadUtil();

    private TipUtil tipUtil = new TipUtil();


    private BaseUIFrag baseUIFrag;

    private LayoutBaseuiBinding layoutBaseuiBinding;

    private BaseUIActivity activity;

    private  BaseUIFrag frag;

    private long uniqueid;

    private BaseUIFrag lastFrag;


    public BaseUIFrag() {
        baseUIFrag = this;
        setArguments(new Bundle());
        opes = new BaseOpes<>(null,null);
        initcc(getClass());
        getP().getV().initValue();
    }

    public void initUI(){
        initaa(baseUIFrag.getClass());
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        synchronized (BaseUIActivity.class){
            uniqueid = System.currentTimeMillis();
        }
        if(savedInstanceState!=null){
            //getP().setVa((C) savedInstanceState.getSerializable(ValueConstant.DATA_VALUE));
        }
        frag = this;
        if(is注册事件总线()){
            EventBus.getDefault().register(this);
        }
        fragIs.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //getArguments().putSerializable(ValueConstant.DATA_VALUE,getPV());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View group = inflater.inflate(getBaseUILayout(),container,false);
        fragIs.onCreateView(inflater,container,savedInstanceState);
        baseUIRoot = group.findViewById(R.id.container);
        if(getPU()==null){
            initUI();
        }
        getPU().added(baseUIRoot);
        //group.setVisibility(View.INVISIBLE);
        return group;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getU().initUI();
        unbinder = ButterKnife.bind(baseUIFrag, baseUIRoot);
        initNow();
        fragIs.onViewCreated(view,savedInstanceState);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //getView().setVisibility(View.VISIBLE);
                onStart();
                if(getLastFrag()!=null){
                    getLastFrag().onBackOut();
                }
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseUIActivity) {
            activity = (BaseUIActivity) context;
        }
    }

    protected int delayTime(){
        return 300;
    }

    protected void animRoot(View root){
        //ViewAnimator.animate(root).duration(300).fadeIn().start();
    }


    public void initNow() {
        if(getView()==null){
            return;
        }
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
        fragIs.onDestroyView();
    }

    /**
     * 重新此方法获取布局文件
     */
    public int getBaseUILayout() {
        return R.layout.layout_baseui;
    }

    /**
     * 获取操作类
     */
    public BaseOpes<A,C> getP() {
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
            opes.getU().init(getBaseUIFrag());
            return;
        }
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            Class<A> a = (Class<A>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[0];
            try {
                Constructor<A> ac = a.getConstructor();
                A aa = ac.newInstance();
                aa.init(getBaseUIFrag());
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

    @Override
    public boolean onLongClick(View v) {
        return true;
    }

    public void onResult(int req, Bundle bundle) {

    }

    @Override
    public void onDestroy() {
        if(is注册事件总线()){
            EventBus.getDefault().unregister(this);
        }
        fragIs.onDestroy();
        super.onDestroy();
    }

    /**
     * 消息总线处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealMesage(Msg event) {
        LogUtil.E(event.dealer + ":" + baseUIFrag.getClass().getName());
        if (!event.dealer.equals(uniqueid+"")) {
            event.isme = false;
            return;
        }
        update(event);
    }

    protected void update(Msg event){

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void addFragListener(FragI fragI){
        fragIs.getFragIs().add(fragI);
    }

    public void startLoading(){
        loadUtil.startLoading(getActivity(), (ViewGroup) getView());
    }

    public void stopLoading(){
        loadUtil.stopLoading( (ViewGroup) getView());
    }

    public void showTips(String txt){
//        if(getBaseUIAct()==null){
//            return;
//        }
        tipUtil.showTips(getBaseUIAct(),  (ViewGroup) getView(),txt);
    }

    public void removeTips(){
        tipUtil.removeTips( (ViewGroup) getView());
    }


    public void onStart(){
        ViewAnimator.animate(getView()).translationX(getView().getWidth(),0).alpha(1,1).duration(300).start();
    }

    public void onBackOut(){
        ViewAnimator.animate(getView()).translationX(0,-getView().getWidth()).alpha(1,1).duration(300).start();
    }

    public void onRemove(AnimationListener.Stop stopListener){
        ViewAnimator.animate(getView()).translationX(0,getView().getWidth()).alpha(1,1).duration(300).start().onStop(stopListener);
    }

    public void onBackIn(){
        ViewAnimator.animate(getView()).translationX(-getView().getWidth()/2,0).alpha(1,1).duration(300).start();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected boolean is注册事件总线(){
        return false;
    }

    public ViewGroup getBaseUIRoot() {
        return baseUIRoot;
    }

    public String get容器() {
        return getArguments().getString(ValueConstant.容器);
    }

    public void set容器(String 容器){
        getArguments().putString(ValueConstant.容器,容器);
    }

    public BaseUIActivity getBaseUIAct() {
        return activity;
    }

    public void setActivity(BaseUIActivity activity) {
        this.activity = activity;
    }

    public BaseUIFrag getBaseUIFrag() {
        return baseUIFrag;
    }

    public long getUniqueid() {
        return uniqueid;
    }

    public BaseUIFrag getLastFrag() {
        return lastFrag;
    }

    public void setLastFrag(BaseUIFrag lastFrag) {
        this.lastFrag = lastFrag;
    }
}
