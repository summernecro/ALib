package com.android.lib.ope.uiope;

//by summer on 2017-08-01.


import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.LayoutDABean;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleUIOpe extends BaseUIOpe {


    public void initRecycle(RecyclerView recyclerView, int layout, int br, ArrayList<LayoutDABean> data) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new AppsDataBindingAdapter(getActivity(), layout, br, data));
    }
}
