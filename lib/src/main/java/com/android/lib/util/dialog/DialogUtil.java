package com.android.lib.util.dialog;

import android.app.AlertDialog;
import android.content.Context;
import androidx.fragment.app.FragmentManager;

import android.view.View;

import com.android.lib.R;
import com.github.florent37.viewanimator.ViewAnimator;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.HashMap;

/**
 * Created by ${viwmox} on 2016-11-30.
 */
public class DialogUtil {

    private static DialogUtil instance;

    private HashMap<String, AlertDialog> dialogs = new HashMap<>();

    public static DialogUtil getInstance() {
        if (instance == null) {
            instance = new DialogUtil();
        }
        return instance;
    }

    public void showDialog(Context context, View view, View.OnClickListener listener, int... id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        dialogs.put(System.currentTimeMillis() + "", alertDialog);
        alertDialog.setView(view);
        alertDialog.show();
        for (int i = 0; i < id.length; i++) {
            view.findViewById(id[i]).setOnClickListener(listener);
            view.findViewById(id[i]).setTag(R.id.data1,view);
        }
        show = true;
        ViewAnimator.animate(alertDialog.getWindow().getDecorView()).scale(0.5f,1.1f,0.8f,1f).duration(1500).start();
    }

    public boolean show = false;

    public boolean isShow() {
        return show;
    }


    public void showDialog(Context context, View view, String tag,View.OnClickListener listener, int... id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        dialogs.put(tag, alertDialog);
        alertDialog.setView(view);
        alertDialog.show();
        for (int i = 0; i < id.length; i++) {
            view.findViewById(id[i]).setOnClickListener(listener);
            view.findViewById(id[i]).setTag(R.id.data1,view);
        }
        show = true;
        ViewAnimator.animate(alertDialog.getWindow().getDecorView()).scale(0.5f,1.1f,0.8f,1f).duration(1500).start();
    }


    public void dismiss() {
        String[] longs = dialogs.keySet().toArray(new String[dialogs.size()]);
        for (int i = 0; i < longs.length; i++) {
            dialogs.get(longs[i]).dismiss();
            dialogs.remove(longs[i]);
        }
        show = false;
    }

    public AlertDialog getDialog(String o) {
        return dialogs.get(o);
    }

    public void showTimePick(Context context, FragmentManager fragmentManager, String name, Type type, OnDateSetListener onDateSetListener) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(onDateSetListener)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("选择日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setMaxMillseconds(System.currentTimeMillis()+tenYears)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(context.getResources().getColor(R.color.color_base_txt_gray))
                .setType(type)
                .setWheelItemTextNormalColor(context.getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(context.getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(16)
                .build();
        mDialogAll.show(fragmentManager, name);
    }
}
