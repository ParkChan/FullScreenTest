package com.example.fullscreentest.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.fullscreentest.DeviceUtil;
import com.example.fullscreentest.R;
import com.orhanobut.logger.Logger;

public class NavigationBarView extends ConstraintLayout {

    private View viewNavigationBar;
    private Context context;

    public NavigationBarView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public NavigationBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public NavigationBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(infService);
        View view = null;
        if (layoutInflater != null) {
            view = layoutInflater.inflate(
                    R.layout.view_navigation_bar,
                    this,
                    false
            );
        }
        addView(view);

        if (view != null) {
            viewNavigationBar = view.findViewById(R.id.view_navigation_bar);
            viewNavigationBar.getLayoutParams().height = DeviceUtil.getCustomNavigationBarHeight(context);
            viewNavigationBar.requestLayout();
            Logger.d("viewNavigationBar bar height is >>> " + DeviceUtil.getCustomNavigationBarHeight(context));

        }
    }
}
