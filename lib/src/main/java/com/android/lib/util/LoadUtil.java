package com.android.lib.util;

//import com.summer.imga.R;
//import com.victor.loading.rotate.RotateLoading;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.android.lib.R;
import com.android.lib.view.loading.BallTrianglePathIndicator;
import com.android.lib.view.title.TitleView;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-05-10.
 */
public class LoadUtil {
    private static final String[] INDICATORS = new String[]{
            "BallPulseIndicator",
            "BallGridPulseIndicator",
            "BallClipRotateIndicator",
            "BallClipRotatePulseIndicator",
            "SquareSpinIndicator",
            "BallClipRotateMultipleIndicator",
            "BallPulseRiseIndicator",
            "BallRotateIndicator",
            "CubeTransitionIndicator",
            "BallZigZagIndicator",
            "BallZigZagDeflectIndicator",
            "BallTrianglePathIndicator",
            "BallScaleIndicator",
            "LineScaleIndicator",
            "LineScalePartyIndicator",
            "BallScaleMultipleIndicator",
            "BallPulseSyncIndicator",
            "BallBeatIndicator",
            "LineScalePulseOutIndicator",
            "LineScalePulseOutRapidIndicator",
            "BallScaleRippleIndicator",
            "BallScaleRippleMultipleIndicator",
            "BallSpinFadeLoaderIndicator",
            "LineSpinFadeLoaderIndicator",
            "TriangleSkewSpinIndicator",
            "PacmanIndicator",
            "BallGridBeatIndicator",
            "SemiCircleSpinIndicator",
            "com.wang.avi.sample.MyCustomIndicator"
    };
    private static LoadUtil instance;
    ArrayList<MyDialog> dialogs = new ArrayList<>();

    public static LoadUtil getInstance() {
        if (instance == null) {
            instance = new LoadUtil();
        }
        return instance;
    }

    public void onStartLoading(Context activity, String tag) {
        if(activity==null){
            return;
        }
        MyDialog dialog = new MyDialog(activity, R.style.swdialog);
        dialogs.add(dialog);
        dialog.setTag(tag);
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().getAttributes().alpha = 1;
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.setContentView(R.layout.dialog_loading);
        AVLoadingIndicatorView imageView;
        imageView = (AVLoadingIndicatorView) dialog.findViewById(R.id.av);
        imageView.setIndicatorColor(activity.getResources().getColor(R.color.color_base));
//        imageView.setIndicator("LineScalePulseOutRapidIndicator");
        imageView.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
    }

    public void onStopLoading(String tag) {
        for (int i = 0; i < dialogs.size(); i++) {
            if (dialogs.get(i) != null && dialogs.get(i).getTag() != null && dialogs.get(i).getTag().equals(tag)) {
                dialogs.get(i).dismiss();
                dialogs.get(i).cancel();
            }
        }
    }

    public class MyDialog extends Dialog {

        private String tag;

        public MyDialog(Context context) {
            super(context);
        }

        protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }

        public MyDialog(Context context, int themeResId) {
            super(context, themeResId);
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

    View loadingView;

    public static Indicator indicator;

    public void startLoading(Context context,ViewGroup viewGroup){
       if( viewGroup.getChildAt(0) instanceof ViewGroup){
           ViewGroup viewGroup1 = (ViewGroup) viewGroup.getChildAt(0);
           if(viewGroup1.getChildCount()>0&&viewGroup1.getChildAt(0) instanceof TitleView){
               loadingView = LayoutInflater.from(context).inflate(R.layout.dialog_loading_havetitle,null);
           }else{
               loadingView = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null);
           }
           AVLoadingIndicatorView avLoadingIndicatorView = (AVLoadingIndicatorView) loadingView.findViewById(R.id.av);
           viewGroup.addView(loadingView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
           avLoadingIndicatorView.setIndicator(indicator==null?new BallTrianglePathIndicator():indicator);
           avLoadingIndicatorView.show();
       }

    }

    public void stopLoading(ViewGroup viewGroup){
        if(loadingView !=null && loadingView.getParent()==viewGroup){
            viewGroup.removeView(loadingView);
        }
    }



}
