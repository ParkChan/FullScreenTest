package com.example.fullscreentest;


import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DeviceUtil {

    // 기기 모델 확인
    public static String getModel() {
        return Build.MODEL;
    }

    // 기기 OS version 확인
    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    // 화면 너비 반환 (pixel)
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
        int statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        return statusBarHeight;
    }

    //하단 소프트키 있는지 체크
    public static boolean isNavigationBar(Context context) {
        int id = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && context.getResources().getBoolean(id);
    }

    //하단 소프트키 높이 반환
//    public static int getNavigationBarHeight(Context context) {
//        int bottomSoftKeyHeight = 0;
//        if (isNavigationBar(context)) {
//            int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
//            if (resourceId > 0) {
//                bottomSoftKeyHeight = context.getResources().getDimensionPixelSize(resourceId);
//            }
//        }
//        return bottomSoftKeyHeight;
//    }

    //하단 소프트키 높이 반환
    public static int getNavigationBarHeight(Context context) {

        int bottomSoftKeyHeight = 0;

        if (isNavigationBar(context)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                DisplayMetrics metrics = new DisplayMetrics();
                WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                manager.getDefaultDisplay().getMetrics(metrics);
                int usableHeight = metrics.heightPixels;
                manager.getDefaultDisplay().getRealMetrics(metrics);
                int realHeight = metrics.heightPixels;
                if (realHeight > usableHeight) {
                    bottomSoftKeyHeight = realHeight - usableHeight - DeviceUtil.getStatusBarHeight(context);
                }
            }
        }
        return bottomSoftKeyHeight;
    }


    // 화면 높이 반환 (pixel)
    public static int getScreenHeight(Context context) {
        if (context == null) {
            return 0;
        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();

        if (wm == null) {
            return 0;
        }
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    // 화면 밀도 반환
    public static int getScreenDensityDpi(Context context) {
        if (context == null) {
            return 0;
        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();

        if (wm == null) {
            return 0;
        }
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.densityDpi;
    }

    public static float pxToDp(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPx(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

}