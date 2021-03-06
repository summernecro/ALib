package com.android.lib.base.adapter;

//by summer on 2017-06-20.

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.R;
import com.android.lib.bean.AppViewHolder;

import java.util.List;

public class AppsDataBindingAdapter extends RecyclerView.Adapter<AppViewHolder> implements View.OnClickListener, View.OnLongClickListener {


    protected List list;
    protected int vari;
    protected int layout;
    protected Context context;
    protected View.OnClickListener listener;
    protected int selecPos = -1;
    protected  boolean load = false;


    public AppsDataBindingAdapter(Context context, int layout, int vari, List list) {
        this.context = context;
        this.layout = layout;
        this.vari = vari;
        this.list = list;
    }

    public AppsDataBindingAdapter(Context context, int layout, int vari, List list, View.OnClickListener listener) {
        this.context = context;
        this.layout = layout;
        this.vari = vari;
        this.list = list;
        this.listener = listener;
    }

    public AppsDataBindingAdapter(Context context, int layout, int vari, List list,boolean load, View.OnClickListener listener) {
        this.context = context;
        this.layout = layout;
        this.vari = vari;
        this.list = list;
        this.listener = listener;
        this.load = load;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layout, parent, false));
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        ViewDataBinding viewDataBinding = holder.viewDataBinding;
        viewDataBinding.getRoot().setTag(R.id.data, list.get(position));
        viewDataBinding.getRoot().setTag(R.id.position, position);
        viewDataBinding.getRoot().setOnClickListener(this);
        viewDataBinding.getRoot().setOnLongClickListener(this);
        if(load){
            viewDataBinding.setVariable(vari, list.get(position));
            viewDataBinding.executePendingBindings();//加一行，问题解决
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            v.setTag(R.id.data1,"onClick");
            listener.onClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (listener != null) {
            v.setTag(R.id.data1,"onLongClick");
            listener.onClick(v);
        }
        return true;
    }

    public void setTag(View view,int pos){
        view.setOnClickListener(this);
        view.setTag(R.id.data,list.get(pos));
        view.setTag(R.id.position,pos);

    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
