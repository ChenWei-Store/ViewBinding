package com.android.viewbinding;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Chenwei on 2017/4/22.
 */

public class MainActivity$$ViewBinding {
    private View view123;
    private View rootView;
    private Activity activity;
    public void bind(){
        view123 = rootView.findViewById(R.id.tv);
        ((MainActivity)activity).tv = (TextView)view123;
        view123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)activity).onTextClick();
            }
        });
    }
}
