package com.ruiwenliu.kotlin.moudle;

import android.view.View;
import android.widget.TableLayout;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class YYY {
    TabLayout dd;
    ViewPager pager;
    void getss(){
        pager.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        dd.setupWithViewPager(pager);
    }

}
