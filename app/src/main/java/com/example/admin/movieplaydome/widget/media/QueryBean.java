package com.example.admin.movieplaydome.widget.media;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 17/2/4.
 */

public class QueryBean {
    private final Activity activity;
    private View view;

    public QueryBean(Activity activity) {
        this.activity=activity;
    }

    public QueryBean id(int id) {
        view = activity.findViewById(id);
        return this;
    }

    public QueryBean image(int resId) {
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(resId);
        }
        return this;
    }

    public QueryBean visible() {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public QueryBean gone() {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
        return this;
    }

    public QueryBean invisible() {
        if (view != null) {
            view.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    public QueryBean clicked(View.OnClickListener handler) {
        if (view != null) {
            view.setOnClickListener(handler);
        }
        return this;
    }

    public QueryBean text(CharSequence text) {
        if (view!=null && view instanceof TextView) {
            ((TextView) view).setText(text);
        }
        return this;
    }

    public QueryBean visibility(int visible) {
        if (view != null) {
            view.setVisibility(visible);
        }
        return this;
    }

    private void size(boolean width, int n, boolean dip){

        if(view != null){

            ViewGroup.LayoutParams lp = view.getLayoutParams();


            if(n > 0 && dip){
                n = dip2pixel(activity, n);
            }

            if(width){
                lp.width = n;
            }else{
                lp.height = n;
            }

            view.setLayoutParams(lp);

        }

    }

    public void height(int height, boolean dip) {
        size(false,height,dip);
    }

    public int dip2pixel(Context context, float n){
        int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, n, context.getResources().getDisplayMetrics());
        return value;
    }

    public float pixel2dip(Context context, float n){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = n / (metrics.densityDpi / 160f);
        return dp;

    }
}
