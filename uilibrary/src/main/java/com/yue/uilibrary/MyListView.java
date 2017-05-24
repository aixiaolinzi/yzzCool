package com.yue.uilibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by fengfan on 2017/5/24.
 */

public class MyListView extends ListView implements View.OnTouchListener, GestureDetector.OnGestureListener {
    private GestureDetector gestureDetector;
    private OnDeleteListener deleteListener;

    private View deleteButton;
    private ViewGroup itemLayout;
    private int selectedItem;
    private boolean isDeleteShown;


    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, this);
        setOnTouchListener(this);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (!isDeleteShown) {
            selectedItem = pointToPosition((int) e.getX(), (int) e.getY());
        } else {
            selectedItem = pointToPosition((int) e.getX(), (int) e.getY());
        }
        Log.e("TSAG", "位置" + selectedItem);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (!isDeleteShown && Math.abs(velocityX) > Math.abs(velocityY)) {
            deleteButton = LayoutInflater.from(getContext()).inflate(R.layout.button_delete, null);
            deleteButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemLayout.removeView(deleteButton);
                    deleteButton = null;
                    isDeleteShown = false;
                    deleteListener.onDelete(selectedItem);
                }
            });
            itemLayout = (ViewGroup) getChildAt(selectedItem - getFirstVisiblePosition());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            itemLayout.addView(deleteButton, params);
            isDeleteShown = true;
        }

        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            selectedItem = pointToPosition((int) event.getX(), (int) event.getY());
            Log.e("TSAG", "位置Touch" + selectedItem);
        }
        if (isDeleteShown) {
            itemLayout.removeView(deleteButton);
            deleteButton = null;
            isDeleteShown = false;
            return false;
        } else {
            return gestureDetector.onTouchEvent(event);
        }
    }

    public void setOnDeleteListener(OnDeleteListener l) {
        deleteListener = l;
    }

    public interface OnDeleteListener {
        void onDelete(int index);
    }
}
