package com.bstu.fit.yarmolik.cinema;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SliderFragment extends Fragment {
    private static ViewPager mPager;
    private static int currentPage = 0;
    Context context;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.raw.gigantic,R.raw.wonder_woman,R.raw.onward,R.raw.artemis_fowl,R.raw.sonic,R.raw.mulan};
    private static final String[] DESCRIPTION={"Гигантик \n 2021","Чудо женщина \n 2020","Вперед \n 2020","Артемис Фаул \n 2020","Соник \n 2020","Мулан \n 2020"};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private ArrayList<String> DescriptionArray = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        mPager =view.findViewById(R.id.pager);

        for(int i=0;i<IMAGES.length;i++)
                ImagesArray.add(IMAGES[i]);
            for(int j=0;j<DESCRIPTION.length;j++)
                DescriptionArray.add(DESCRIPTION[j]);
        mPager =view.findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(2);
        mPager.setAdapter(new SlidingImage_Adapter(getContext(),ImagesArray,DescriptionArray));
        CirclePageIndicator indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;


        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
        return view;
    }
}
