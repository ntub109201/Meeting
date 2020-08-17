package com.example.myapplication2.Diary.DiaryHowPackage;

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

import com.example.myapplication2.Diary.DiaryPreviewActivity;
import com.example.myapplication2.Diary.DiaryWhereActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

public class DiaryHowActivity extends AppCompatActivity {

    private Button btn_eye, btn_mouth, btn_nose;
    private TextView mPreview, btn_skip, txtTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_how);

        // 反回上一頁
        final ImageButton imbtnReturnFrontPage5 = findViewById(R.id.imbtnReturnFrontPage5);
        imbtnReturnFrontPage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.howCount = 0;
                DiaryValue.Eye_Count = 0;
                DiaryValue.Mouth_Count = 0;
                DiaryValue.Smell_Count = 0;
                Intent intent = new Intent(DiaryHowActivity.this, DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                DiaryHowActivity.this.startActivity(intent,options.toBundle());
            }
        });

        txtTest = findViewById(R.id.txtTest);
        txtTest.setText(String.valueOf(DiaryValue.howCount));


        btn_mouth = findViewById(R.id.btn_mouth);
        btn_mouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 很重要
                if(btn_skip.getText().toString().equals("跳題")){
                    DiaryValue.howCount = 0;
                }
                if(DiaryValue.Mouth_Count != 0){
                    DiaryValue.howCount -= 1;
                }
                DiaryValue.Mouth_Count = 0;
                Intent intent = new Intent(DiaryHowActivity.this, DiaryHowMouthActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                DiaryHowActivity.this.startActivity(intent,options.toBundle());
            }
        });

        btn_eye = findViewById(R.id.btn_eye);
        btn_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 很重要
                if(btn_skip.getText().toString().equals("跳題")){
                    DiaryValue.howCount = 0;
                }
                if(DiaryValue.Eye_Count != 0){
                    DiaryValue.howCount -= 1;
                }
                DiaryValue.Eye_Count = 0;
                Intent intent = new Intent(DiaryHowActivity.this, DiaryHowEyeActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                DiaryHowActivity.this.startActivity(intent,options.toBundle());
            }
        });

        btn_nose = findViewById(R.id.btn_nose);
        btn_nose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 很重要
                if(btn_skip.getText().toString().equals("跳題")){
                    DiaryValue.howCount = 0;
                }
                if(DiaryValue.Smell_Count != 0){
                    DiaryValue.howCount -= 1;
                }
                DiaryValue.Smell_Count = 0;
                Intent intent = new Intent(DiaryHowActivity.this, DiaryHowSmellActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                DiaryHowActivity.this.startActivity(intent,options.toBundle());
            }
        });

        // 前往preview
        mPreview = findViewById(R.id.btn_preview_how);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i< 5; i++){
                    DiaryValue.txtHow_choose[i] = "";
                }
                Intent intent = new Intent();
                intent.setClass(DiaryHowActivity.this,DiaryPreviewActivity.class);
                Bundle tagData = new Bundle();
                tagData.putString("1","DiaryHowActivity");
                intent.putExtras(tagData);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        //跳題
        btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_skip.getText().toString().equals("跳題")){
                    DiaryValue.txtHow_choose[0] = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryHowActivity.this, DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryHowActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                    startActivity(intent,options.toBundle());
                }else {
                    Intent intent = new Intent();
                    intent.setClass(DiaryHowActivity.this, DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryHowActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });
        if(DiaryValue.howCount!=0){
            btn_skip.setText("下一題");
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
