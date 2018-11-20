package com.siweisoft.app.main;

//by summer on 2018-09-20.

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.android.lib.util.LogUtil;

import androidx.annotation.Nullable;

public class CView extends View {

    public CView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtil.E(getClass().getSimpleName()+"-->dispatchTouchEvent"+"ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.E(getClass().getSimpleName()+"-->dispatchTouchEvent"+"ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.E(getClass().getSimpleName()+"-->dispatchTouchEvent"+"ACTION_UP");
                break;
        }

        return super.dispatchTouchEvent(event);
    }
}
