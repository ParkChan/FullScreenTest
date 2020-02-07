package com.example.fullscreentest;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtil {

    public static void setDefaultImage(Context context, ImageView thumbImage, Uri uri) {
        Glide.with(context)
                .load(uri)
                .thumbnail(1.0f)
                .into(thumbImage);
    }

    public static void setDefaultImage(Context context, ImageView thumbImage, int resource) {
        Glide.with(context)
                .load(resource)
                .centerCrop()
                .thumbnail(1.0f)
                .into(thumbImage);
    }

    public static void setDefaultImage(Context context, ImageView thumbImage, String url) {
        Glide.with(context)
                .load(url)
                .thumbnail(1.0f)
                .into(thumbImage);
    }


    //제휴점 상세 대표 이미지
    public static void setStoreDetialImage(Context context, ImageView thumbImage, String url) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .thumbnail(1.0f)
                .into(thumbImage);
    }

    public static void setStoreDetialImage(Context context, ImageView thumbImage, int resource) {
        Glide.with(context)
                .load(resource)
                .thumbnail(1.0f)
                .into(thumbImage);
    }

}
