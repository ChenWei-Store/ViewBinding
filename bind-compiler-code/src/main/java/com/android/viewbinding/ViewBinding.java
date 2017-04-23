package com.android.viewbinding;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.view.animation.PathInterpolator;

/**
 * Created by Chenwei on 2017/4/22.
 */

public class ViewBinding {
    private static void bindActivity(Activity activity){

    }

    private static void bindFragment(Fragment fragment){

    }

    private static void bindAdapter(View rootView){

    }

    public void bind(Object obj){
        if(obj instanceof Activity){
            bindActivity((Activity)obj);
        }else if(obj instanceof Fragment){
            bindFragment((Fragment)obj);
        }else if(obj instanceof View){
            bindAdapter((View) obj);
        }else{
            throw new IllegalArgumentException("传参错误");
        }
    }
}