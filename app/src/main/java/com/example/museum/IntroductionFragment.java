package com.example.museum;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class IntroductionFragment extends Fragment{
    private View view;
    private TextView textView;
    private String string;

    public IntroductionFragment(){}

    public static IntroductionFragment fragment;

    public static IntroductionFragment newInstance(String text){
        Bundle args = new Bundle();
        args.putString("text",text);
        IntroductionFragment fragment = new IntroductionFragment();
        fragment.setArguments(args);
        fragment.string=text;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_introduction,container,false);
        initView(v);
        return v;
    }

    private void initView(View v){
        textView=(TextView)v.findViewById(R.id.text);
        textView.setText(string);
    }
}
