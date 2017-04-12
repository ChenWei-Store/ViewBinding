package com.viewBinding.generateClass;

import com.viewBinding.model.ClassInfo;

/**
 * Created by chenw on 2017/4/12.
 */

public class GenerateClassHelper {
    private StringBuilder classSb;
    private ClassInfo classInfo;
    public GenerateClassHelper(ClassInfo classInfo){
        classSb = new StringBuilder();
        this.classInfo = classInfo;
    }



}
