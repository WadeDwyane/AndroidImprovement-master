package com.wadedwyane.www.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wadedwyane.www.fragment.ParallaxFragment;

import java.util.ArrayList;
import java.util.List;

public class ParallaxAdapter extends FragmentPagerAdapter {

    private List<ParallaxFragment> fragments = new ArrayList<>();

    public ParallaxAdapter(FragmentManager fm, List<ParallaxFragment> list) {
        super(fm);
        this.fragments = list;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
