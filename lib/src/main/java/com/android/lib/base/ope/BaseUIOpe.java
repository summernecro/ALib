package com.android.lib.base.ope;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.bean.AppViewHolder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import com.android.lib.databinding.ActBaseuiBinding;
import com.android.lib.util.ClickUtil;
import com.android.lib.util.LogUtil;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

/**
 * ui处理操作者 处理对象 uibean fragment view
 */
public class BaseUIOpe<A extends ViewDataBinding> implements View.OnClickListener,View.OnLongClickListener{

    private A bind;
    private Context context;
    private  BaseUIFrag frag;
    private View view;

    public BaseUIOpe(){

    }


    public void init(Context context) {
        this.context = context;
        bind = initViewDataBinding();
        bind.executePendingBindings();
        initclick();
    }

    private void initclick(){
        View[] clickviews = initOnClick();
        View[] longclickviews = initOnLongClick();
        for(int i=0;i<clickviews.length;i++){
            clickviews[i].setOnClickListener(this);
        }
        for(int i=0;i<longclickviews.length;i++){
            longclickviews[i].setOnLongClickListener(this);
        }
    }

    public void init(BaseUIFrag frag) {
        this.frag = frag;
        this.context = frag.getBaseUIAct();
        bind = initViewDataBinding();
        bind.executePendingBindings();
        initclick();
    }


    public void copy(BaseUIOpe baseUIOpe){
        this.context = baseUIOpe.context;
        this.frag = baseUIOpe.frag;
        this.bind = (A) baseUIOpe.bind;
        initUI();
    }


    public void initUI(){

    }



    public A initViewDataBinding() {
        A viewDataBinding = null;
        if (viewDataBinding == null) {
            if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
                Class<A> a = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                Method method = null;
                try {
                    method = a.getMethod("inflate", LayoutInflater.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    viewDataBinding = (A) method.invoke(null, LayoutInflater.from(context));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    LogUtil.E(e.getMessage());
                }
            } else {
                viewDataBinding = getBind();
            }
        }
        return viewDataBinding;
    }



    protected A getBind() {
        return bind;
    }


    public BaseUIFrag getFrag() {
        return frag;
    }

    public BaseUIActivity getActivity(){
        if(frag!=null ){
            return frag.getBaseUIAct();
        }else{
            return (BaseUIActivity) context;
        }
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onClick(View v) {
      if(getFrag()==null){
          if(ClickUtil.getInstance().init(v)){
              getActivity().onClick(v);
          }
      }else{
         if(ClickUtil.getInstance().init(v)){
             getFrag().onClick(v);
         }
      }
    }

    protected  View[] initOnClick(){
        return new View[]{};
    }

    protected  View[] initOnLongClick(){
        return new View[]{};
    }

    @Override
    public boolean onLongClick(View v) {
        return true;
    }

    public void added(ViewGroup viewGroup){
        if(getBind()!=null){
            viewGroup.addView(getBind().getRoot(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            setView(viewGroup);
        }
    }
}
