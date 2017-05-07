package com.viewBinding.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.TypeElement;

/**
 * Created by Chenwei on 2017/4/20.
 */

public class BindingInfo {
    private String packageName;
    //    private String className;
    private String newClassName;
    private String classQualifiedName;
    private ArrayList<ViewBindInfo> viewBindInfos;
    private ArrayList<OnClickInfo> onClickInfos;
    private TypeElement typeElement;
    private int targetType;
    public BindingInfo(String className, String packageName, TypeElement typeElement){
        viewBindInfos = new ArrayList<>();
        onClickInfos = new ArrayList<>();
        this.newClassName = (className + "$$ViewBinding");
        this.packageName = packageName;
        this.typeElement = typeElement;
    }



    public String getClassQualifiedName() {
        return classQualifiedName;
    }

    public void setClassQualifiedName(String classQuaName) {
        this.classQualifiedName = classQuaName;
    }

    public ArrayList<OnClickInfo> getOnClickInfos() {
        return onClickInfos;
    }

    public void setOnClickInfos(ArrayList<OnClickInfo> onClickInfos) {
        this.onClickInfos = onClickInfos;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

//    public String getClassName() {
//        return className;
//    }

    public String getNewClassName() {
        return newClassName;
    }

    public void setNewClassName(String newClassName) {
        this.newClassName = newClassName;
    }

//    public void setClassName(String className) {
//        this.className = className;
//    }

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
        sb = generateContent(sb);
        sb.append("}");
        return sb.toString();
    }

    private StringBuilder generateHeader(){
        StringBuilder sb = new StringBuilder();
        sb.append("package ")
                .append(getPackageName())
                .append(";")
                .append("\n")
                .append("public class ")
                .append(getNewClassName())
                .append(" implements ")
                .append("com.android.viewbinding.BindingObject")
                .append("{\n");
        return sb;
    }

    private StringBuilder generateContent(StringBuilder sb){

        //生成方法名、方法参数
        sb.append("\tpublic void bindTo(android.view.View targetView, ")
                .append("Object ")
                .append(" targetObj){\n");

        //生成decorview
//        sb.append("\t\tandroid.view.View decorView = null;\n")
//                .append("\t\tif(targetView instanceof android.app.Activity){\n")
//                .append("\t\t\tdecorView = ((android.app.Activity)targetView).getWindow().getDecorView();\n")
//                .append("\t\t}else if(targetView instanceof android.support.v4.app.Fragment ){\n")
//                .append("\t\t\tdecorView = ((android.support.v4.app.Fragment)targetView).getActivity().getWindow().getDecorView();\n")
//                .append("\t\t}else if(targetView instanceof android.app.Fragment ){\n")
//                .append("\t\t\tdecorView = ((android.app.Fragment)targetView).getActivity().getWindow().getDecorView();\n")
//                .append("\t\t}\n");
        sb.append("\t\t")
                .append("final ")
                .append(classQualifiedName)
                .append(" target = ")
                .append("(")
                .append(classQualifiedName)
                .append(")targetObj;\n");

        Map<Integer, String> foundView = new HashMap<>();
        for(int i = 0; i < viewBindInfos.size(); i++){
            //生成findViewyId代码
            ViewBindInfo viewBindInfo = viewBindInfos.get(i);
            sb.append("\t\ttarget.")
                    .append(viewBindInfo.getFieldName())
                    .append(" = ")
                    .append("(")
                    .append(viewBindInfo.getViewQualifiedType())
                    .append(")targetView.findViewById(")
                    .append(viewBindInfo.getViewId())
                    .append(");\n");
            //保存id和field
            foundView.put(viewBindInfo.getViewId(), viewBindInfo.getFieldName());
        }

        for(int i = 0; i < onClickInfos.size(); i++){
            OnClickInfo onClickInfo = onClickInfos.get(i);
            String field = foundView.get(onClickInfo.getViewId());
            //事件监听前判断是否有findView操作并生成onClickListener
            if(field == null || field.equals("")){
                sb.append("\t\ttargetView.findViewById(")
                        .append(onClickInfo.getViewId())
                        .append(").setOnClickListener(new android.view.View.OnClickListener(){\n");

            }else{
                sb.append("\t\ttarget.")
                        .append(field)
                        .append(".setOnClickListener(new android.view.View.OnClickListener(){\n");
            }

            sb.append("\t\t\tpublic void onClick(android.view.View v) {\n")
                    .append("\t\t\t\ttarget.")
                    .append(onClickInfo.getMethodName())
                    .append("();\n")
                    .append( "\t\t\t}\n")
                    .append("\t\t});\n");
        }
        sb.append("\t}\n");
        return sb;
    }


}
