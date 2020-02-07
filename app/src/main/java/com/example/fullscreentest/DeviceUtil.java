package com.example.fullscreentest;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;

import java.util.Objects;

public class DeviceUtil {

    //화면 너비 반환 (pixel)
    public static int getScreenWidth(Context context) {
        if (context == null) {
            return 0;
        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (wm == null) {
            return 0;
        }
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    //statusBarHeight
    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    //하단 소프트키 여부 체크
    private static boolean isNavigationBar(Context context) {
        int id = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && context.getResources().getBoolean(id);
    }

    //하단 소프트키 높이 반환
    public static int getNavigationBarHeight(Context context) {
        int bottomSoftKeyHeight = 0;
        if (isNavigationBar(context)) {
            int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                bottomSoftKeyHeight = context.getResources().getDimensionPixelSize(resourceId);
            }
        }
        return bottomSoftKeyHeight;
    }

    //하단 소프트키 높이 반환
    public static int getCustomNavigationBarHeight(Context context) {

        int bottomSoftKeyHeight = 0;

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Objects.requireNonNull(manager).getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        manager.getDefaultDisplay().getRealMetrics(metrics);

        int realHeight = metrics.heightPixels;

        if (realHeight > usableHeight) {
            int tempBottomSoftKeyHeight = realHeight - usableHeight;
            Logger.d("realHeight >>> " + realHeight + "   usableHeight >>> " + usableHeight
                    + "    StatusBar >>> " + DeviceUtil.getStatusBarHeight(context)
                    + "    tempHeight >>> " + tempBottomSoftKeyHeight
            );
            if (tempBottomSoftKeyHeight > DeviceUtil.getStatusBarHeight(context)) {
                if (tempBottomSoftKeyHeight > 168) {
                    bottomSoftKeyHeight = tempBottomSoftKeyHeight - DeviceUtil.getStatusBarHeight(context);
                } else {
                    bottomSoftKeyHeight = tempBottomSoftKeyHeight;
                }
            } else {
                bottomSoftKeyHeight = tempBottomSoftKeyHeight;
            }
            //Test Log....
            //StatusBar >>> 171 bottomSoftKeyHeight >>> 339  pixcel3
            //StatusBar >>> 150 bottomSoftKeyHeight >>> 341  s10
            //StatusBar >>> 84  bottomSoftKeyHeight >>> 168  s8+
        }
        return bottomSoftKeyHeight;
    }
}