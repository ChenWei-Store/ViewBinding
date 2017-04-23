package com.viewBinding.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenwei on 2017/4/20.
 */

public class OnClickInfo {
    private int viewId;
    private String methodName;

    public OnClickInfo(int viewId, String methodName) {
        this.viewId = viewId;
        this.methodName = methodName;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


}
