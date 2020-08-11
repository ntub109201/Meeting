package com.example.myapplication2.Diary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;
public class DiaryWhyActivity extends AppCompatActivity{

    //public static String txtWhy = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarywhy);

        // 反回上一頁
        final ImageButton imbtnReturnFrontPage3 = findViewById(R.id.imbtnReturnFrontPage3);
        imbtnReturnFrontPage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.WhatLock = true;
                Intent intent = new Intent(DiaryWhyActivity.this, DiaryWhatActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                DiaryWhyActivity.this.startActivity(intent,options.toBundle());
            }
        });

        final Button btn_nowhy = findViewById(R.id.btn_nowhy);
        btn_nowhy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhy = "沒為什麼";
                Intent intent = new Intent();
                intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        final Button btn_party = findViewById(R.id.btn_party);
        btn_party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhy = "聚會";
                Intent intent = new Intent();
                intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        final Button btn_hungry = findViewById(R.id.btn_hungry);
        btn_hungry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhy = "餓了";
                Intent intent = new Intent();
                intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        final Button btn_celebrate = findViewById(R.id.btn_celebrate);
        btn_celebrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhy = "慶祝";
                Intent intent = new Intent();
                intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        final Button btn_try = findViewById(R.id.btn_try);
        btn_try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhy = "嚐鮮";
                Intent intent = new Intent();
                intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        final Button btn_yummy = findViewById(R.id.btn_yummy);
        btn_yummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhy = "合胃口";
                Intent intent = new Intent();
                intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        final Button btn_tired = findViewById(R.id.btn_tired);
        btn_tired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhy = "嘴饞";
                Intent intent = new Intent();
                intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        // 前往preview
        final TextView mPreview = findViewById(R.id.btn_preview_why);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhy = "";
                DiaryValue.txtWhere = "";
                for(int i = 0; i< 5; i++){
                    DiaryValue.txtHow_choose[i] = "";
                }
                Intent intent = new Intent();
                intent.setClass(DiaryWhyActivity.this,DiaryPreviewActivity.class);
                Bundle tagData = new Bundle();
                tagData.putString("1","DiaryWhyActivity");
                intent.putExtras(tagData);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        //跳題
        final TextView btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhy = "";
                Intent intent = new Intent();
                intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                startActivity(intent,options.toBundle());
            }
        });


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
