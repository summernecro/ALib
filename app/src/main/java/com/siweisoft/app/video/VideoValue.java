package com.siweisoft.app.video;

//by summer on 2018-10-08.

import com.android.lib.base.ope.BaseValue;
import com.android.lib.util.LoadUtil;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoValue extends BaseValue {
    @Getter
    @Setter
    private ArrayList<Integer> list = new ArrayList<>();

    private String url = "";

    @Override
    public void initValue() {
        super.initValue();
        for(int i=0;i<1000;i++){
            list.add(i);
        }
    }
}
