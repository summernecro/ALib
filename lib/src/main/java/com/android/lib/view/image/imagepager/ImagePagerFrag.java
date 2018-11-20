package com.android.lib.view.image.imagepager;

//by summer on 2017-06-15.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class ImagePagerFrag extends BaseUIFrag<ImagePagerUIOpe,ImagePagerValue> {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null && getArguments().getSerializable(ValueConstant.DATA_DATA) != null) {
            getPV().setUrls((ArrayList<String>) getArguments().getSerializable(ValueConstant.DATA_DATA));
            getPV().setPostion(getArguments().getInt(ValueConstant.DATA_POSITION));
        } else {
            getPV().setUrls(new ArrayList<String>());
        }
        getP().getU().initPager(getActivity().getSupportFragmentManager(), getPV().getUrls(), getPV().getPostion());
    }
}
