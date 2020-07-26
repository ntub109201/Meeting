package com.example.myapplication2.ui.maybelike;

import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication2.MainActivity;
import com.example.myapplication2.ModifyPersonalActivity;
import com.example.myapplication2.R;
import com.google.android.material.tabs.TabLayout;

public class MaybelikeFragment extends Fragment {

    private TabLayout mTabs;
    private ViewPager mViewPager;
    private ImageButton imBtnPersonal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_maybelike, container, false);

        if(MainActivity.changeBtn == true){
            MainActivity.changeBtn = false;
        }
        mTabs = root.findViewById(R.id.mTabs);
        mTabs.addTab(mTabs.newTab().setText("文章"));
        mTabs.addTab(mTabs.newTab().setText("地點"));
        mViewPager = root.findViewById(R.id.mViewPager);
        mViewPager.setAdapter(new MyPagerAdapter());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));

        imBtnPersonal = root.findViewById(R.id.imBtnPersonal);
        imBtnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaybelikeFragment.super.getActivity(), ModifyPersonalActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MaybelikeFragment.super.getActivity());
                intent.putExtra("pageId",4);
                startActivity(intent,options.toBundle());
            }
        });


        return root;
    }
    private class MyPagerAdapter extends PagerAdapter {
        private int pageCount = 2;
        @Override
        public int getCount() {
            return pageCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return obj == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "MyPage " + (position + 1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = getLayoutInflater().inflate(R.layout.maybelike_item, container, false);
            container.addView(view);
            ImageView imageView = view.findViewById(R.id.imgMayBeLike);
            if(position == 0){
                imageView.setImageResource(R.drawable.maybelike_fake1);
            }else {
                imageView.setImageResource(R.drawable.maybelike_fake2);
            }

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
