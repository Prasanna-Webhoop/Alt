package com.alt.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alt.R;
import com.alt.others.OnBoardingTextView;
import com.alt.utils.Utils;


/**
 * Created by student on 25/11/16.
 */

public class HeaderView extends LinearLayout {

    // STEPS LIST.
    private OnBoardingTextView[] onBoardingTextView;
    private Context context;
    private AttributeSet attrs;
    private String TAG = "HEADER_VIEW";
    private int startIndex = 0;
    private String flag = "";

    // Interface objects to be used in AltBaseActivity.
    public OnHeaderViewItemClickListener onHeaderViewItemClickListener;

    public OnHeaderViewItemClickListener getOnHeaderViewItemClickListener() {
        return onHeaderViewItemClickListener;
    }

    public void setOnHeaderViewItemClickListener(OnHeaderViewItemClickListener onHeaderViewItemClickListener) {
        this.onHeaderViewItemClickListener = onHeaderViewItemClickListener;
    }

    // COLORS
    private int selectedColor = Color.BLACK, unselectedColor = 0x7f0b0027;

    public interface OnHeaderViewItemClickListener {
        public void onHeaderViewClick(int position);
    }

    public HeaderView(Context context) {
        super(context);
        this.context = context;
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
//        initHeader();
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;
        initMainLayout(context, attrs);
//        initHeader();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        this.attrs = attrs;
        initMainLayout(context, attrs);
//        initHeader();
    }

    private void initMainLayout(Context context, AttributeSet attrs) {
        this.setBackgroundColor(Color.WHITE);
    }


    /**
     * SET UP THE INDICATOR.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initHeader() {
        TypedArray a = null;
        try {
            this.removeAllViews();
            Utils.out("INIT Header Component.");
//              this.setPadding(10, 10, 10, 10);
            a = getContext().obtainStyledAttributes(attrs, R.styleable.HeaderView);
            flag = getFlag();
            Utils.out("SHOWING THE FLAG :" + getFlag());
            final String[] stepsList;
            try {
//                stepsList = a.getTextArray(R.styleable.HeaderView_android_entries);
                if (getResources().getString(R.string.flag_sign_up).equals(getFlag())) {
                    stepsList = context.getResources().getStringArray(R.array.onboarding_steps);
                } else {
                    stepsList = context.getResources().getStringArray(R.array.main_board_steps);

                }
                Utils.out("INIT Header LENGTH :  " + stepsList.length);
                if (stepsList != null) {
                    //do something with the array if you want
//                    this.setPadding(30, 0, 0, 0);

                    onBoardingTextView = new OnBoardingTextView[stepsList.length];

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    layoutParams.setMargins(0, 0, 15, 0);

                    for (int i = startIndex; i < onBoardingTextView.length; i++) {
                        onBoardingTextView[i] = new OnBoardingTextView(context);
                        onBoardingTextView[i].setLayoutParams(layoutParams);
                        onBoardingTextView[i].setGravity(Gravity.CENTER);
                        onBoardingTextView[i].setPadding(0, 20, 15, 25);
                        onBoardingTextView[i].setSingleLine(true);
                        onBoardingTextView[i].setTextSize(10);
//                        onBoardingTextView[i].setTextAppearance();
                        onBoardingTextView[i].setTypeface(Typeface.createFromAsset(context.getAssets(), getResources().getString(R.string.avenirRegular)));
                        // SET THE TEXT TO THE TEXTVIEW OBTAINED FROM THE stepList Array.
                        onBoardingTextView[i].setText(stepsList[i]);
                        Utils.out("INIT Header Component:  " + stepsList[i]);
                        final int finalI = i;
                        onBoardingTextView[i].setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Utils.out("HEADER CLICK POSITION :" + finalI + ", NAME : " + stepsList[finalI]);
                                onHeaderViewItemClickListener.onHeaderViewClick(finalI);
                            }
                        });
                        this.addView(onBoardingTextView[i]);
                    }
                }
            } catch (NullPointerException n) {
                Log.d(TAG, "No of view for the view is zero / null.");
                n.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * This will set the selected index in HeaderView.
     *
     * @param index Index to be set as selected.
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void setSelectedIndex(int index) {

        this.startIndex = index;
        initHeader();

        Utils.out("Selected index : " + index);
        // Set selected black color.

        for (int i = startIndex; i < onBoardingTextView.length; i++) {
//            headerView_text_color
            onBoardingTextView[i].setTextColor(getUnselectedColor());
        }
        // color black
        onBoardingTextView[index].setTextColor(getSelectedColor());
//        onBoardingTextView[index].setTypeface(Typeface.DEFAULT_BOLD);
//            onBoardingTextView[index].setTextColor(context.getResources().getIdentifier("color", "color", context.getPackageName()));
        onBoardingTextView[index].setTypeface(Typeface.createFromAsset(context.getAssets(), getResources().getString(R.string.avenirBold)));
        invalidate();
        refreshDrawableState();

    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public int getUnselectedColor() {
        return unselectedColor;
    }

    public void setUnselectedColor(int unselectedColor) {
        this.unselectedColor = unselectedColor;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * This is the flow name to show in header view, according this array will
     * be fetched from the strings.xml file.
     */
    public String getFlag() {
        return this.flag;
    }
}

