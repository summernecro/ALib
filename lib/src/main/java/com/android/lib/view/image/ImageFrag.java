package com.android.lib.view.image;

//by summer on 2017-06-15.

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.GlideApp;
import com.android.lib.R;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.NullUtil;
import com.luck.picture.lib.photoview.PhotoView;


public class ImageFrag extends Fragment implements View.OnLongClickListener, View.OnClickListener {


    PhotoView photoView;

    String url;

    View.OnLongClickListener onLongClickListener;

    View.OnClickListener onClickListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_image, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        photoView = (PhotoView) view.findViewById(R.id.iv_image);
        if (getArguments() == null || getArguments().getString(ValueConstant.DATA_DATA) == null) {
            return;
        }
        url = getArguments().getString(ValueConstant.DATA_DATA);
        if (!NullUtil.isStrEmpty(url)) {
            GlideApp.with(photoView).asBitmap().load(url);
        }
        photoView.setOnLongClickListener(this);
        photoView.setOnClickListener(this);
        view.findViewById(R.id.iv_download).setOnClickListener(this);
        getView().findViewById(R.id.tv_back).setOnClickListener(this);
        getView().findViewById(R.id.iv_download).setVisibility(getArguments().getBoolean(ValueConstant.DATA_POSITION) ? View.GONE : View.VISIBLE);
    }


    @Override
    public boolean onLongClick(View v) {
//        if (!url.startsWith("http:")) {
//            ToastUtil.getInstance().showShort(activity, "已经是本地文件");
//            return true;
//        }
//        ToastUtil.getInstance().showShort(activity, "开始保存");
//        BitmapUtil.saveImage(activity, url);
        if (onLongClickListener != null) {
            onLongClickListener.onLongClick(v);
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onClick(v);
        }
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
