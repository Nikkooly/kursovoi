package com.bstu.fit.yarmolik.cinema.Adapters;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.bstu.fit.yarmolik.cinema.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

    public class SlidingImage_Adapter extends PagerAdapter {


        private ArrayList<Integer> IMAGES;
        private ArrayList<String> DESCRIPTION;
        private LayoutInflater inflater;
        private Context context;


        public SlidingImage_Adapter(Context context,ArrayList<Integer> IMAGES,ArrayList<String> DESCRIPTION) {
            this.context = context;
            this.IMAGES=IMAGES;
            this.DESCRIPTION=DESCRIPTION;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return IMAGES.size();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);
            assert imageLayout != null;
            final ImageView imageView = (ImageView) imageLayout
                    .findViewById(R.id.image);
            final TextView textView=imageLayout.findViewById(R.id.filmDescription);
            imageView.setImageResource(IMAGES.get(position));
            textView.setText(DESCRIPTION.get(position));
            view.addView(imageLayout, 0);
            return imageLayout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }


    }
