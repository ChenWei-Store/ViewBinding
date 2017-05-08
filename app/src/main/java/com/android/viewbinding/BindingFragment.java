package com.android.viewbinding;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.viewBinding.BindView;
import com.viewBinding.OnClick;


public class BindingFragment extends Fragment {
    @BindView(viewId = R.id.tv1)
    static TextView tv;
    @BindView(viewId = R.id.ib)
    ImageButton imageBtn;

    public BindingFragment() {
        // Required empty public constructor
    }

    public static BindingFragment newInstance() {
        BindingFragment fragment = new BindingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_binding, container, false);
        ViewBinding.bind(this, rootView);
        tv.setText("Textview");
        return rootView;
    }

    @OnClick(viewId = R.id.btn)
    void onBtnClick(){
        Toast.makeText(getActivity(), "onBtnClick", Toast.LENGTH_SHORT).show();
    }

    @OnClick(viewId = R.id.tv1)
    public void onTvClick(){
        Toast.makeText(getActivity(),  "onTvClick", Toast.LENGTH_SHORT).show();
    }
}
