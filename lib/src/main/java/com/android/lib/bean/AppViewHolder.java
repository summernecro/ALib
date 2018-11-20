package com.android.lib.bean;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class AppViewHolder extends RecyclerView.ViewHolder {

    public ViewDataBinding viewDataBinding;

    public AppViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.viewDataBinding = viewDataBinding;
    }


}