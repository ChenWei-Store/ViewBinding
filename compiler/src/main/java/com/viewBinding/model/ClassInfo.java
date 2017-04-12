package com.viewBinding.model;

import java.util.ArrayList;

/**
 * Created by Chenwei on 2017/4/12.
 */

public class ClassInfo {
    private String clsName;
    private ArrayList<String> implNames;
    private String extendName;
    private ArrayList<MethodInfo> methodInfos;
    private ArrayList<FieldInfo> fields;
    private ArrayList<String> importCls;
    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public ArrayList<String> getImplNames() {
        return implNames;
    }

    public void setImplNames(ArrayList<String> implNames) {
        this.implNames = implNames;
    }

    public String getExtendName() {
        return extendName;
    }

    public void setExtendName(String extendName) {
        this.extendName = extendName;
    }

    public ArrayList<MethodInfo> getMethodInfos() {
        return methodInfos;
    }

    public void setMethodInfos(ArrayList<MethodInfo> methodInfos) {
        this.methodInfos = methodInfos;
    }

    public ArrayList<FieldInfo> getFields() {
        return fields;
    }

    public void setFields(ArrayList<FieldInfo> fields) {
        this.fields = fields;
    }

    public ArrayList<String> getImportCls() {
        return importCls;
    }

    public void setImportCls(ArrayList<String> importCls) {
        this.importCls = importCls;
    }
}
