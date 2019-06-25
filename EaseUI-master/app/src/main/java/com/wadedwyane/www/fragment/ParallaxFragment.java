package com.wadedwyane.www.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wadedwyane.www.widget.ParallaxInflater;

import java.util.ArrayList;
import java.util.List;

public class ParallaxFragment extends Fragment {

    private List<View> parallaxList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int layoutId = bundle.getInt("layoutId");
        ParallaxInflater inflater1 = new ParallaxInflater(inflater, getActivity(), this);
        return inflater1.inflate(layoutId, null);
    }

    public List<View> getParallaxList() {
        return parallaxList;
    }
}
