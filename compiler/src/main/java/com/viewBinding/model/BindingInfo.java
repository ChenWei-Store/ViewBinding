package com.viewBinding.model;

import java.util.ArrayList;

/**
 * Created by Chenwei on 2017/4/20.
 */

public class BindingInfo {
    private String packageName;
    private String className;
    private ArrayList<ViewBindInfo> viewBindInfos;
    private OnClickInfo onClickInfo;
    public BindingInfo(String className, String packageName){
        viewBindInfos = new ArrayList<>();
        this.className = className;
        this.packageName = packageName;
    }
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ArrayList<ViewBindInfo> getViewBindInfos() {
        return viewBindInfos;
    }

    public void setViewBindInfos(ArrayList<ViewBindInfo> viewBindInfos) {
        this.viewBindInfos = viewBindInfos;
    }

    public OnClickInfo getOnClickInfo() {
        return onClickInfo;
    }

    public void setOnClickInfo(OnClickInfo onClickInfo) {
        this.onClickInfo = onClickInfo;
    }
}
