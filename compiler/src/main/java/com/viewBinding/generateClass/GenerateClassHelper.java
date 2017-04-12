package com.viewBinding.generateClass;

import com.viewBinding.model.ClassInfo;
import com.viewBinding.model.FieldInfo;

/**
 * Created by chenwei on 2017/4/12.
 */

public class GenerateClassHelper {
    private StringBuilder classSb;
    private ClassInfo classInfo;
    public GenerateClassHelper(ClassInfo classInfo){
        classSb = new StringBuilder();
        this.classInfo = classInfo;
    }

    public String generateClass(){
        generateClassHeader();
        generateClassName();
        generateClassField();
        return classSb.toString();
    }


    private void generateClassHeader(){
        classSb.append("package ").append(classInfo.getPackageName())
                .append(";")
                .append("\\n");

        for(String className : classInfo.getImportCls()){
            classSb.append("import ")
                    .append(className)
                    .append(";")
                    .append("\\n");
        }
    }

    private void generateClassName(){
        classSb.append("public class ")
                .append(classInfo.getClsName());

        if(classInfo.getExtendName() != null && !classInfo.getExtendName().equals("")){
            classSb.append(" extends ")
                    .append(classInfo.getExtendName());
        }

        if(classInfo.getImplNames() != null && classInfo.getImplNames().size() > 0){
            classSb.append(" implements ");
            for(String interfaceName : classInfo.getImplNames()){
                classSb.append(interfaceName)
                        .append(", ");
            }

            classSb.deleteCharAt(classSb.length());
        }

        classSb.append("\\n");
    }


    private void generateClassField(){
        if(classInfo.getFields() == null || classInfo.getFields().size() == 0){
            return;
        }

        for(FieldInfo field : classInfo.getFields()){
            
        }
    }



}
