package com.example.myapplication2.Diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication2.Diary.DiaryWhoFragment.DiaryWhoFirstFragment;
import com.example.myapplication2.Diary.DiaryWhoFragment.DiaryWhoSecondFragment;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;
import com.google.android.material.tabs.TabLayout;

public class DiaryWhoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_who);

        // 反回上一頁
        final ImageButton imbtnReturnFrontPage7 = findViewById(R.id.imbtnReturnFrontPage7);
        imbtnReturnFrontPage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhenActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                DiaryWhoActivity.this.startActivity(intent,options.toBundle());
            }
        });

        InnerPagerAdapter pagerAdapter = new InnerPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        // 前往preview
        final TextView mPreview = findViewById(R.id.btn_preview_who);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWho = "";
                Intent intent = new Intent();
                intent.setClass(DiaryWhoActivity.this,DiaryPreviewActivity.class);
                Bundle tagData = new Bundle();
                tagData.putString("1","DiaryWhoActivity");
                intent.putExtras(tagData);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        //跳題
        final TextView btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWho = "";
                Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                DiaryWhoActivity.this.startActivity(intent,options.toBundle());
            }
        });
    }
    public class InnerPagerAdapter extends FragmentPagerAdapter {
        public InnerPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = new DiaryWhoFirstFragment();
                    break;
                case 1:
                    fragment = new DiaryWhoSecondFragment();
                    break;
            }
            return fragment;
        }
        @Override
        public int getCount(){
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position){
            switch (position) {
                case 0:
                    return "";
                case 1:
                    return " ";
                default:
                    return null;
            }
        }

    }
    // 擋住手機上回上一頁鍵
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.ECLAIR) {
                event.startTracking();
            } else {
                onBackPressed(); // 是其他按鍵則再Call Back方法
            }
        }
        return false;
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }
}
