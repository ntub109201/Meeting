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

import com.example.myapplication2.Diary.DiaryHowPackage.DiaryHowActivity;
import com.example.myapplication2.Diary.DiaryTravelWhereFragment.DiaryTravelFirstFragment;
import com.example.myapplication2.Diary.DiaryTravelWhereFragment.DiaryTravelSecondFragment;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;
import com.google.android.material.tabs.TabLayout;

public class DiaryTravelWhereActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_travel_where);

        final TextView txtWhere_title_travel = findViewById(R.id.txtWhere_title_travel);
        int randomNum = (int)(Math.random()* 3 + 1);
        Diary_dictionary dict = new Diary_dictionary();
        String total = DiaryValue.txtMood+"_"+DiaryValue.txtTag+"_Where_"+String.valueOf(randomNum);
        txtWhere_title_travel.setText(dict.dict.get(total));

        final ImageButton ReturnFrontPage = findViewById(R.id.ReturnFrontPage);
        ReturnFrontPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryTravelWhereActivity.this, DiaryTravelWhenActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTravelWhereActivity.this);
                DiaryTravelWhereActivity.this.startActivity(intent, options.toBundle());
            }
        });

        final TextView btn_preview = findViewById(R.id.btn_preview);
        btn_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhere = "";
                for(int i = 0; i< 5; i++){
                    DiaryValue.txtHow_choose[i] = "";
                }
                Intent intent = new Intent();
                intent.setClass(DiaryTravelWhereActivity.this,DiaryPreviewActivity.class);
                Bundle tagData = new Bundle();
                tagData.putString("1","DiaryTravelWhereActivity");
                intent.putExtras(tagData);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTravelWhereActivity.this);
                startActivity(intent,options.toBundle());
            }
        });
        final TextView btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhere = "";
                if(DiaryValue.txtWhat.equals("")){
                    Intent intent = new Intent();
                    intent.setClass(DiaryTravelWhereActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryTravelWhereActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTravelWhereActivity.this);
                    startActivity(intent,options.toBundle());
                }else {
                    Intent intent = new Intent(DiaryTravelWhereActivity.this, DiaryHowActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTravelWhereActivity.this);
                    startActivity(intent, options.toBundle());
                }
            }
        });

        InnerPagerAdapter pagerAdapter = new InnerPagerAdapter(getSupportFragmentManager());

        final ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

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
                    fragment = new DiaryTravelFirstFragment();
                    break;
                case 1:
                    fragment = new DiaryTravelSecondFragment();
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

            String a = "";

            switch (position) {
                case 0:
                    a = "";
                case 1:
                    a = " ";
                default:
                    a = null;
            }
            return a;
        }

    }


    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent intent = new Intent(DiaryTravelWhereActivity.this, DiaryTravelWhenActivity.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTravelWhereActivity.this);
            DiaryTravelWhereActivity.this.startActivity(intent, options.toBundle());
        }
        return super.onKeyDown(keyCode, event);
    }
}
