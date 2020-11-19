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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication2.Diary.DiaryWhoFragment.DiaryWhoFirstFragment;
import com.example.myapplication2.Diary.DiaryWhoFragment.DiaryWhoSecondFragment;
import com.example.myapplication2.Diary.DiaryWhoFragment.DiaryWhoThirdFragment;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;
import com.google.android.material.tabs.TabLayout;

public class DiaryWhoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_who);

        final TextView txtWho_title = findViewById(R.id.txtWho_title);

        int randomNum = (int)(Math.random()* 3 + 1);
        Diary_dictionary dict = new Diary_dictionary();
        String total = DiaryValue.txtMood+"_"+DiaryValue.txtTag+"_Who_"+String.valueOf(randomNum);
        txtWho_title.setText(dict.dict.get(total));

        final ProgressBar progressBarWho = findViewById(R.id.progressBarWho);
        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("購物")){
            progressBarWho.setProgress(40);
        }else if(DiaryValue.txtTag.equals("休閒娛樂")){
            progressBarWho.setProgress(80);
        }else if(DiaryValue.txtTag.equals("戀愛")){
            progressBarWho.setProgress(60);
        }else if(DiaryValue.txtTag.equals("旅遊")){
            progressBarWho.setProgress(20);
        }

        // 反回上一頁
        final ImageButton imbtnReturnFrontPage7 = findViewById(R.id.imbtnReturnFrontPage7);
        imbtnReturnFrontPage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    DiaryWhoActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    DiaryWhoActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    DiaryWhoActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    DiaryWhoActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("旅遊")){
                    Intent intent = new Intent(DiaryWhoActivity.this, DiaryTagActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    DiaryWhoActivity.this.startActivity(intent,options.toBundle());
                }
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
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWho = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWhat = "";
                    DiaryValue.txtWhy = "";
                    DiaryValue.txtWhere = "";
                    DiaryValue.txtWho = "";
                    for(int i = 0; i< 5; i++){
                        DiaryValue.txtHow_choose[i] = "";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWho = "";
                    for(int i = 0; i< 5; i++){
                        DiaryValue.txtHow_choose[i] = "";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    DiaryValue.txtWhen = "";
                    DiaryValue.txtWho = "";
                    for(int i = 0; i< 5; i++){
                        DiaryValue.txtHow_choose[i] = "";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("旅遊")){
                    DiaryValue.txtWhat = "";
                    DiaryValue.txtWhy = "";
                    DiaryValue.txtWhere = "";
                    DiaryValue.txtWho = "";
                    DiaryValue.txtWhen = "";
                    for(int i = 0; i< 5; i++){
                        DiaryValue.txtHow_choose[i] = "";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
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
                    DiaryValue.txtWho = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWho = "";
                    Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    DiaryWhoActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWho = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    DiaryValue.txtWho = "";
                    Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    DiaryWhoActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("旅遊")){
                    DiaryValue.txtWho = "";
                    Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                    DiaryWhoActivity.this.startActivity(intent,options.toBundle());
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
            if(DiaryValue.txtTag.equals("戀愛")){
                switch (position){
                    case 0:
                        fragment = new DiaryWhoFirstFragment();
                        break;
                    case 1:
                        fragment = new DiaryWhoSecondFragment();
                        break;
                }
            }else {
                switch (position){
                    case 0:
                        fragment = new DiaryWhoFirstFragment();
                        break;
                    case 1:
                        fragment = new DiaryWhoSecondFragment();
                        break;
                    case 2:
                        fragment = new DiaryWhoThirdFragment();
                        break;
                }
            }

            return fragment;
        }
        @Override
        public int getCount(){
            int a = 0;
            if(DiaryValue.txtTag.equals("美食")){
                a = 3;
            }else if(DiaryValue.txtTag.equals("購物")){
                a = 3;
            }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                a = 3;
            }else if(DiaryValue.txtTag.equals("戀愛")){
                a = 2;
            }else if(DiaryValue.txtTag.equals("旅遊")){
                a = 3;
            }
            return a;
        }
        @Override
        public CharSequence getPageTitle(int position){

            String a = "";
            if(DiaryValue.txtTag.equals("戀愛")){
                switch (position) {
                    case 0:
                        a = "";
                    case 1:
                        a = " ";
                    default:
                        a = null;
                }
            }else {
                switch (position) {
                    case 0:
                        a = "";
                    case 1:
                        a = " ";
                    case 2:
                        a = "   ";
                    default:
                        a = null;
                }
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
            if(DiaryValue.txtTag.equals("美食")){
                Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhenActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                DiaryWhoActivity.this.startActivity(intent,options.toBundle());
            }else if(DiaryValue.txtTag.equals("購物")){
                Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhenActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                DiaryWhoActivity.this.startActivity(intent,options.toBundle());
            }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                DiaryWhoActivity.this.startActivity(intent,options.toBundle());
            }else if(DiaryValue.txtTag.equals("戀愛")){
                Intent intent = new Intent(DiaryWhoActivity.this, DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                DiaryWhoActivity.this.startActivity(intent,options.toBundle());
            }else if(DiaryValue.txtTag.equals("旅遊")){
                Intent intent = new Intent(DiaryWhoActivity.this, DiaryTagActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoActivity.this);
                DiaryWhoActivity.this.startActivity(intent,options.toBundle());
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
