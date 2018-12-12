package com.android.lib.util;

//import com.summer.imga.R;
//import com.victor.loading.rotate.RotateLoading;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.lib.R;
import com.android.lib.view.loading.BallTrianglePathIndicator;
import com.android.lib.view.title.TitleView;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;
import com.wang.avi.indicators.LineScaleIndicator;

import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-05-10.
 */
public class LoadUtil {
    /**
     * 加载控件动画样式
     */
    public static final String[][] INDICATORS = new String[][]{
            { "BallPulseIndicator",
                    "BallGridPulseIndicator",
                    "BallClipRotateIndicator",
                    "BallClipRotatePulseIndicator"},
            {"SquareSpinIndicator",
                    "BallClipRotateMultipleIndicator",
                    "BallPulseRiseIndicator",
                    "BallRotateIndicator"},
            {"CubeTransitionIndicator",
                    "BallZigZagIndicator",
                    "BallZigZagDeflectIndicator",
                    "BallTrianglePathIndicator"},
            {"BallScaleIndicator",
                    "LineScaleIndicator",
                    "LineScalePartyIndicator",
                    "BallScaleMultipleIndicator"},
            {"BallPulseSyncIndicator",
                    "BallBeatIndicator",
                    "LineScalePulseOutIndicator",
                    "LineScalePulseOutRapidIndicator"},
            { "BallScaleRippleIndicator",
                    "BallScaleRippleMultipleIndicator",
                    "BallSpinFadeLoaderIndicator",
                    "LineSpinFadeLoaderIndicator"},
            { "TriangleSkewSpinIndicator",
                    "PacmanIndicator",
                    "BallGridBeatIndicator",
                    "SemiCircleSpinIndicator",},
            {"com.wang.avi.sample.MyCustomIndicator"}
    };
    ArrayList<MyDialog> dialogs = new ArrayList<>();

    private static LoadUtil instance;

    public static LoadUtil getInstance(){
        if(instance==null){
            instance = new LoadUtil();
        }
        return instance;
    }

    private LoadUtil(){

    }

    public void onStartLoading(Context activity, String tag) {
        onStartLoading(activity,"LineScalePulseOutRapidIndicator","加载中...",tag);
    }


    public void onStartLoading(Context activity, String type,String text,String tag) {
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
        imageView.setIndicatorColor(activity.getResources().getColor(R.color.black));
        TextView textView = dialog.findViewById(R.id.text);
        textView.setText(text);
        imageView.setIndicator(type);
        imageView.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        ViewAnimator.animate(dialog.getWindow().getDecorView()).scale(0.5f,1f).duration(300).start();
    }

    /**
     * 去除加载
     * @param tag
     */
    public void onStopLoading(String tag) {
        for (int i = 0; i < dialogs.size(); i++) {
            if (dialogs.get(i) != null && dialogs.get(i).getTag() != null && dialogs.get(i).getTag().equals(tag)) {
                final int finalI = i;
                ViewAnimator.animate(dialogs.get(i).getWindow().getDecorView()).fadeOut().duration(1000).start().onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        dialogs.get(finalI).dismiss();
                        dialogs.get(finalI).cancel();
                    }
                });
            }
        }
    }



    public void startLoading(Context context,ViewGroup viewGroup,String str,Indicator indicator){
        if( viewGroup.getChildAt(0) instanceof ViewGroup){
            View loadingView;
            ViewGroup viewGroup1 = (ViewGroup) viewGroup.getChildAt(0);
            if(viewGroup1.getChildCount()>0&&viewGroup1.getChildAt(0) instanceof TitleView){
                loadingView = LayoutInflater.from(context).inflate(R.layout.dialog_loading_havetitle,null);
            }else{
                loadingView = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null);
            }
            TextView textView  = loadingView.findViewById(R.id.text);
            textView.setText(str);
            AVLoadingIndicatorView avLoadingIndicatorView = (AVLoadingIndicatorView) loadingView.findViewById(R.id.av);
            viewGroup.addView(loadingView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            loadingView.setTag(R.id.data1,"dialog");
            avLoadingIndicatorView.setIndicator(indicator);
            avLoadingIndicatorView.show();
        }
    }

    public void startLoading(Context context,ViewGroup viewGroup,String str){
        startLoading(context,viewGroup,str,new LineScaleIndicator());
    }


    public void startLoading(Context context,ViewGroup viewGroup){
        startLoading(context,viewGroup,"加载中...",new LineScaleIndicator());
    }


    public void stopLoading(ViewGroup viewGroup){
        for(int i=0;i<viewGroup.getChildCount();i++){
            View view = viewGroup.getChildAt(i);
            if("dialog".equals(view.getTag(R.id.data1))){
                viewGroup.removeView(view);
                i--;
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

}
