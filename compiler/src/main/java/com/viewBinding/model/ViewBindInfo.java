package com.viewBinding.model;


/**
 * Created by Chenwei on 2017/4/20.
 */

public class ViewBindInfo {
    private int viewId;
    private String viewQualifiedType;
    private String fieldName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public String getViewQualifiedType() {
        return viewQualifiedType;
    }

    public void setViewQualifiedType(String viewQualifiedType) {
        this.viewQualifiedType = viewQualifiedType;
    }
}
