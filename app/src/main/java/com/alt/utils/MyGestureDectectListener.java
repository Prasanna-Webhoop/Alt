package com.alt.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Capternal on 03/11/16.
 */

public class MyGestureDectectListener extends GestureDetector.SimpleOnGestureListener {
    //handle 'swipe left' action only
    // SWIPE GESTURE NOT WORKING PROPER. NEED TO WORK ON IT.
    boolean status = false;
    swipeDetectListener swipeDetectListener;

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    public interface swipeDetectListener {
        public void isSwipeToLeft(boolean status);
    }

    public MyGestureDectectListener(Context context) {
        swipeDetectListener = (swipeDetectListener) context;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        float diffY = e2.getY() - e1.getY();
        float diffX = e2.getX() - e1.getX();
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    swipeDetectListener.isSwipeToLeft(false);
                } else {
                    swipeDetectListener.isSwipeToLeft(true);
                }
            }
        }
        return true;
    }
}
