package com.android.viewbinding;

import android.app.Activity;
import android.util.Log;
import android.view.View;


/**
 * Created by Chenwei on 2017/4/22.
 */

public class ViewBinding {

    private static String getGeneratedClsName(Object obj){
        Class cls = obj.getClass();
        String clsCanonicalName = cls.getCanonicalName();
        if(cls.isMemberClass()){
            String packageName = cls.getPackage().getName();
            String clsName = clsCanonicalName.substring(clsCanonicalName.lastIndexOf("."), clsCanonicalName.length());
            clsCanonicalName = packageName + clsName;
        }
        String generatedClsName = clsCanonicalName + "$$ViewBinding";
        return generatedClsName;
    }

    private static void doBinding(String generatedClsName, View targetView, Object obj){
        try {
            Class<BindingObject> cls = (Class<BindingObject>)Class.forName(generatedClsName);
            cls.newInstance().bindTo(targetView, obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Did not find generated class " + generatedClsName);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void bind(Object obj){
        if(obj == null){
            throw new IllegalArgumentException("obj == null");
        }

        String generatedClsName = getGeneratedClsName(obj);
        View targetView = null;
        if(obj instanceof Activity){
            Activity activity = (Activity)obj;
            targetView = activity.getWindow().getDecorView();
        }

        if(targetView == null){
            throw new IllegalArgumentException("can not get view by binded object");
        }
        doBinding(generatedClsName, targetView, obj);
    }

    public static void bind(Object obj, View targetView){
        if(targetView == null){
            throw new IllegalArgumentException("can not get view by binded object");
        }
        if(obj == null){
            throw new IllegalArgumentException("obj == null");
        }
        String generatedClsName = getGeneratedClsName(obj);
        doBinding(generatedClsName, targetView, obj);
    }
}