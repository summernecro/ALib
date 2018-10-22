package com.android.lib.view.image.imagepager;

//by summer on 2018-06-25.

import com.android.lib.base.ope.BaseValue;

import java.util.ArrayList;

public class ImagePagerValue extends BaseValue {

    ArrayList<String> urls;

    private int postion;



    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }
}
