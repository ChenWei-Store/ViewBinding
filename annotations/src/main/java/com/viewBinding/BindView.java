package com.viewBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Chenwei
 * description:
 * date:2017/4/12
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface BindView {
    int viewId() default -1;
}
