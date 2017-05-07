package com.android.viewbinding;

import android.view.View;

import java.util.Objects;

/**
 * Created by Chenwei on 2017/5/7.
 */

public interface BindingObject {
    void bindTo(View view, Object object);
}
