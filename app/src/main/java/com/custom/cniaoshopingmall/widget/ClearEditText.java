package com.custom.cniaoshopingmall.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.tools.CommonUtil;

/**
 * Created by xuchichi on 2018/1/1.
 */

public class ClearEditText extends AppCompatEditText implements TextWatcher{
    private Drawable mClearTextIcon;
    private Context context;
    private  int deleteImgIcon;
    private int drawableWidth, drawableHeight;//圖片的宽高
    public ClearEditText(Context context){
        this(context,null);
        this.context=context;
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context,attrs);
        init();
        this.context=context;
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.ClearEditText);
        drawableWidth= a.getInteger(R.styleable.ClearEditText_drawableWidth,10);
        drawableHeight= a.getInteger(R.styleable.ClearEditText_drawableHeight,10);
        deleteImgIcon= a.getResourceId(R.styleable.ClearEditText_deleteImgIcon,R.drawable.icon_delete_32);
        a.recycle();
        init();
    }
    private void init() {
        mClearTextIcon=getCompoundDrawables()[2];
        if (mClearTextIcon==null){
            mClearTextIcon=getResources().getDrawable(R.drawable.icon_delete_32);
            if (drawableWidth>0&&drawableHeight>0){//必须设置图片大小,否则不显示
                mClearTextIcon.setBounds(0, 0, CommonUtil.dp2Px(context,drawableWidth),CommonUtil.dp2Px(context,drawableHeight));
            }else{
                mClearTextIcon.setBounds(0, 0, mClearTextIcon.getIntrinsicWidth(), mClearTextIcon.getIntrinsicHeight());
            }
        }
        setClearIconVisible(false);
        addTextChangedListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_UP){
            int x= (int) event.getX();
            if (x>(getWidth()-getPaddingRight()-mClearTextIcon.getIntrinsicWidth())
                    &&x<(getWidth()-getPaddingRight())){//点击的坐标在图片两点之间
                this.setText("");
                setClearIconVisible(false);
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 输入框的监听事件
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (!TextUtils.isEmpty(getText().toString())){
            setClearIconVisible(true);
        }else{
            setClearIconVisible(false);
        }

    }

    /**
     * 设置图片位置
     * @param visible
     */
    private void setClearIconVisible(final boolean visible) {
        setCompoundDrawables(null, null, visible ? mClearTextIcon : null, null);
    }
}
