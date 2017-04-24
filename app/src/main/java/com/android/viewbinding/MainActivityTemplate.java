package com.android.viewbinding;

import android.view.View;

/**
 * Created by Chenwei on 2017/4/22.
 * 编译后MainActivity需要生成的对应代码
 */

public class MainActivityTemplate {
    public void bind(final MainActivity target){
        View decorView = target.getWindow().getDecorView();
//        target.tv = (TextView) decorView.findViewById(R.id.tv);

        //被注解的类中没有
        decorView.findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target.onTextClick();
            }
        });

        //被注解的类中声明字段
//        target.tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                target.onTextClick();
//            }
//        });
    }
}
