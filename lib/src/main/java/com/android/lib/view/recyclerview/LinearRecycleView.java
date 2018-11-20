package com.android.lib.view.recyclerview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by summer on 2018/2/3 18:24.
 */

public class LinearRecycleView extends RecyclerView {

    public LinearRecycleView(Context context) {
        super(context);
        init();
    }

    public LinearRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinearRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init(){
        setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
