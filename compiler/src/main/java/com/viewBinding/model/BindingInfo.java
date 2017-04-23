package com.viewBinding.model;


import java.util.ArrayList;

import javax.lang.model.element.TypeElement;

/**
 * Created by Chenwei on 2017/4/20.
 */

public class BindingInfo {
    public static final int ACTIVITY = 0;
    public static final int FRAGMENT = 1;
    public static final int ADAPTER = 2;

    private String packageName;
    private String className;
    private ArrayList<ViewBindInfo> viewBindInfos;
    private ArrayList<OnClickInfo> onClickInfos;
    private TypeElement typeElement;
    private int targetType;
    public BindingInfo(String className, String packageName, TypeElement typeElement){
        viewBindInfos = new ArrayList<>();
        onClickInfos = new ArrayList<>();
        this.className = (className + "$$ViewBinding");
        this.packageName = packageName;
        this.typeElement = typeElement;
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

    public ArrayList<OnClickInfo> getOnClickInfo() {
        return onClickInfos;
    }

    public void setOnClickInfo(ArrayList<OnClickInfo> onClickInfos) {
        this.onClickInfos = onClickInfos;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    public void addOnClickInfo(OnClickInfo onClickInfo){
        onClickInfos.add(onClickInfo);
    }

    public void addViewBindInfo(ViewBindInfo viewBindInfo){
        viewBindInfos.add(viewBindInfo);
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public String generateClass(){
      StringBuilder sb = generateHeader();

        return sb.toString();
    }

    private StringBuilder generateHeader(){
        StringBuilder sb = new StringBuilder();
        sb.append("package ")
                .append(getPackageName())
                .append(";")
                .append("\n")
                .append("public class ")
                .append(getClassName());
        return sb;
    }

    private StringBuilder generateContent(StringBuilder sb){
        ArrayList<Integer> ids = new ArrayList<>();
        for(int i = 0; i < viewBindInfos.size(); i++){
            ids.add(viewBindInfos.get(i).getViewId());
        }

        for(int i = 0; i < onClickInfos.size(); i++){
            ids.add(onClickInfos.get(i).getViewId());
        }


        return sb;
    }
}
