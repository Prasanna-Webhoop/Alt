package com.alt.others;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.EditText;

import com.alt.R;


/**
 * Created by student on 23/11/16.
 */

public class OnBoardingEditText extends EditText {

    // DEFAULT FONT NAME
    private String fontType = "";

    public OnBoardingEditText(Context context) {
        super(context);
    }

    public OnBoardingEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);

    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.OnBoarding);
        fontType = a.getString(R.styleable.OnBoarding_fontType);
        if (fontType != null) {
            if (!fontType.isEmpty()) {
                this.setTypeface(Typeface.createFromAsset(context.getAssets(), fontType));
            } else {
                this.setTypeface(Typeface.DEFAULT);
            }
        }
    }

    public OnBoardingEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public OnBoardingEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }
}
