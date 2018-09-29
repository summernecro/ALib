package com.android.lib.view.photoview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.luck.picture.lib.photoview.PhotoView;


/**
 * Created by ${viwmox} on 2016-11-01.
 */
public class AppPhotoView extends PhotoView {


    public AppPhotoView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        Matrix m = new Matrix();
         getDisplayMatrix(m);
        float[] values = new float[9];
        m.getValues(values);

        if (values[5] < 0.0f) {
            m.postTranslate(0, -2 * values[5]);
            setDisplayMatrix(m);
        }
    }

}
