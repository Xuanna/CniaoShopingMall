package com.custom.cniaoshopingmall.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.utils.LogUtils;

/**
 * Created by xuchichi on 2018/1/16.
 */
public class NumberAddView extends LinearLayout implements View.OnClickListener{

    private Button btnAdd;
    private Button btnReduce;
    private TextView textView;
    private LayoutInflater inflater;
    private int value;
    private int minValue;
    private int maxValue=DEFUALT_MAX;
    public static final int DEFUALT_MAX=1000;
    OnButtonClickListener onButtonClickListener;

    public int getValue() {
        String currentValue=textView.getText().toString();
        if (!TextUtils.isEmpty(currentValue)){
            value=Integer.parseInt(currentValue);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public NumberAddView(Context context) {
        this(context, null);
    }

    public NumberAddView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberAddView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);
        initView();

        if (attrs!=null){

            TypedArray ty= context.obtainStyledAttributes(attrs,R.styleable.NumberAddView);
//            final TintTypedArray ty = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
//                    R.styleable.NumberAddView, defStyleAttr, 0);
           value= ty.getInt(R.styleable.NumberAddView_value,0);
            setValue(value);
            textView.setText(value+"");

             minValue= ty.getInt(R.styleable.NumberAddView_minValue,0);
            setMinValue(minValue);

             maxValue= ty.getInt(R.styleable.NumberAddView_maxValue,0);
            setMaxValue(maxValue);

            Drawable btnAddDrawable= ty.getDrawable(R.styleable.NumberAddView_btnAddBackground);
            Drawable btnReduceDrawable= ty.getDrawable(R.styleable.NumberAddView_btnReduceBackground);
            Drawable textDrawable= ty.getDrawable(R.styleable.NumberAddView_textViewBackground);

            setAddBackground(btnAddDrawable);
            setReduceBackground(btnReduceDrawable);
            setTextBackground(textDrawable);

            ty.recycle();
        }

    }

    private void initView() {
        View view = inflater.inflate(R.layout.layout_number_add, this, true);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnReduce = (Button) view.findViewById(R.id.btnReduce);
        textView = (TextView) view.findViewById(R.id.tvNum);

        btnAdd.setOnClickListener(this);
        btnReduce.setOnClickListener(this);
    }

    private void setAddBackground(Drawable drawable){
        if (drawable!=null){
            btnAdd.setBackgroundDrawable(drawable);
        }
    }

    private void setReduceBackground(Drawable drawable){
        if (drawable!=null){
            btnReduce.setBackgroundDrawable(drawable);
        }
    }

    private void setTextBackground(Drawable drawable){
        if (drawable!=null){
            textView.setBackgroundDrawable(drawable);
        }
    }

    @Override
    public void onClick(View view) {
       LogUtils.e("onclick");
        switch (view.getId()){
            case R.id.btnReduce:
                numberReduce();
                if (onButtonClickListener!=null){
                    onButtonClickListener.reduce(value);
                }
                break;
            case R.id.btnAdd:
                numberAdd();
                if (onButtonClickListener!=null){
                    onButtonClickListener.adds(value);
                }
                break;
        }

    }

    public void setOnButtonClickListsner(OnButtonClickListener onButtonClickListener){
        this.onButtonClickListener=onButtonClickListener;
    }


    public interface  OnButtonClickListener{
        void adds(int value);
        void reduce(int value);
    }


    private void numberAdd(){
        getValue();
        LogUtils.e("maxValue:"+maxValue);
        if (value<maxValue){
            value=value+1;
            textView.setText(value+"");
        }
    }

    private void numberReduce(){
        getValue();
        if (value>minValue){
            value=value-1;
            textView.setText(value+"");
        }
    }
}
