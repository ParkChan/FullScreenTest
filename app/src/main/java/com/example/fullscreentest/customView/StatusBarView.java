package com.example.fullscreentest.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.fullscreentest.DeviceUtil;
import com.example.fullscreentest.R;
import com.orhanobut.logger.Logger;


public class StatusBarView extends ConstraintLayout {

    private View viewStatusBar;
    private Context context;

    public StatusBarView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public StatusBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
        getAttr(attrs);

    }

    public StatusBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
        getAttr(attrs);
    }

    private void getAttr(AttributeSet attrs) {
        @SuppressLint("CustomViewStyleable")
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.StatusBar);
        int color = typedArray.getColor(R.styleable.StatusBar_customBackGroundColor,
                ContextCompat.getColor(context, R.color.colorPrimary));
        viewStatusBar.setBackgroundColor(color);
        typedArray.recycle();
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(infService);
        View view = null;
        if (layoutInflater != null) {
            view = layoutInflater.inflate(
                    R.layout.view_status_bar,
                    this,
                    false
            );
        }

        addView(view);

        if (view != null) {
            viewStatusBar = view.findViewById(R.id.view_status_bar);
            viewStatusBar.getLayoutParams().height = DeviceUtil.getStatusBarHeight(context);
            viewStatusBar.requestLayout();

            Logger.d("StatusBarHeight is >>> " + DeviceUtil.getCustomNavigationBarHeight(context));
        }
    }
}
