package com.example.myapplication2.Diary;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication2.Diary.DiaryWhatFragment.DiaryWhatFirstFragment;
import com.example.myapplication2.Diary.DiaryWhatFragment.DiaryWhatSecondFragment;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;
import com.google.android.material.tabs.TabLayout;

public class DiaryWhatActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_what);

        final TextView txtWhat_title = findViewById(R.id.txtWhat_title);
        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("購物")){
            txtWhat_title.setText("今天去買了什麼呢?");
        }

        final ProgressBar progressBarWhat = findViewById(R.id.progressBarWhat);
        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("購物")){
            progressBarWhat.setProgress(100);
        }



        // 反回上一頁
        final ImageButton imbtnReturnFrontPage1 = findViewById(R.id.imbtnReturnFrontPage1);
        imbtnReturnFrontPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    Intent intent = new Intent(DiaryWhatActivity.this, DiaryTagActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatActivity.this);
                    DiaryWhatActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    Intent intent = new Intent(DiaryWhatActivity.this, DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatActivity.this);
                    DiaryWhatActivity.this.startActivity(intent,options.toBundle());
                }
            }
        });

        InnerPagerAdapter pagerAdapter = new InnerPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        // 前往preview
        final TextView mPreview = findViewById(R.id.btn_preview_what);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWhat = "";
                    DiaryValue.txtWhy = "";
                    DiaryValue.txtWhere = "";
                    DiaryValue.txtWhen = "";
                    DiaryValue.txtWho = "";
                    for(int i = 0; i< 5; i++){
                        DiaryValue.txtHow_choose[i] = "";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhatActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhatActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWhat = "";
                    for(int i = 0; i< 5; i++){
                        DiaryValue.txtHow_choose[i] = "";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhatActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhatActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        //跳題
        final TextView btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWhat = "";
                    Intent intent = new Intent(DiaryWhatActivity.this, DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatActivity.this);
                    DiaryWhatActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWhat = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhatActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhatActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatActivity.this);
                    startActivity(intent,options.toBundle());
                }
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

            if(DiaryValue.txtTag.equals("美食")){
                switch (position){
                    case 0:
                        fragment = new DiaryWhatFirstFragment();
                        break;
                    case 1:
                        fragment = new DiaryWhatSecondFragment();
                        break;
                }
            }else if(DiaryValue.txtTag.equals("購物")){
                switch (position){
                    case 0:
                        fragment = new DiaryWhatFirstFragment();
                        break;
                }
            }

            return fragment;
        }
        @Override
        public int getCount(){

            int a = 0;
            if(DiaryValue.txtTag.equals("美食")){
                a = 2;
            }else if(DiaryValue.txtTag.equals("購物")){
                a = 1;
            }
            return a;
        }
        @Override
        public CharSequence getPageTitle(int position){

            String a = "";

            if(DiaryValue.txtTag.equals("美食")){
                switch (position) {
                    case 0:
                        a = "";
                    case 1:
                        a = " ";
                    default:
                        a = null;
                }
            }else if(DiaryValue.txtTag.equals("購物")){
                switch (position) {
                    case 0:
                        a = "";
                    default:
                        a = null;
                }
            }

            return a;
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
