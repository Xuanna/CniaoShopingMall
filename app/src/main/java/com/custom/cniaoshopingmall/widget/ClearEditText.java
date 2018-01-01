package com.custom.cniaoshopingmall.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by xuchichi on 2018/1/1.
 */

public class ClearEditText extends AppCompatEditText {
    public ClearEditText(Context context){
        this(context,null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
