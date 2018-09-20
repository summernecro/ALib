package com.siweisoft.app.main;

//by summer on 2018-09-20.

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.android.lib.util.LogUtil;

public class AView extends LinearLayout {

    public AView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                LogUtil.E(getClass().getSimpleName()+"-->onTouchEvent"+"ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                LogUtil.E(getClass().getSimpleName()+"-->onTouchEvent"+"ACTION_MOVE");
//                break;
//            case MotionEvent.ACTION_UP:
//                LogUtil.E(getClass().getSimpleName()+"-->onTouchEvent"+"ACTION_UP");
//                break;
//        }
//        super.onTouchEvent(event);
//        return false;
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                LogUtil.E(getClass().getSimpleName()+"-->dispatchTouchEvent"+"ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                LogUtil.E(getClass().getSimpleName()+"-->dispatchTouchEvent"+"ACTION_MOVE");
//                break;
//            case MotionEvent.ACTION_UP:
//                LogUtil.E(getClass().getSimpleName()+"-->dispatchTouchEvent"+"ACTION_UP");
//                break;
//        }
//        super.dispatchTouchEvent(event);
//        return false;
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                LogUtil.E(getClass().getSimpleName()+"-->onInterceptTouchEvent"+"ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                LogUtil.E(getClass().getSimpleName()+"-->onInterceptTouchEvent"+"ACTION_MOVE");
//                break;
//            case MotionEvent.ACTION_UP:
//                LogUtil.E(getClass().getSimpleName()+"-->onInterceptTouchEvent"+"ACTION_UP");
//                break;
//        }
//        super.onInterceptTouchEvent(event);
//        return false;
//    }
}
