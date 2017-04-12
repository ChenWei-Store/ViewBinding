package com.viewBinding.model;

import java.util.Map;

/**
 * Created by chenw on 2017/4/12.
 */

public class FieldInfo {
    public static final int PUBLIC = 0;
    public static final int PROTECTEED = 1;
    public static final int PRIVATE = 2;
    private String name;
    private int modifier;
    private boolean isStatic;
    private boolean isFinal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}
