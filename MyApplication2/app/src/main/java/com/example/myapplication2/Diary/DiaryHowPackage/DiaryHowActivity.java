package com.example.myapplication2.Diary.DiaryHowPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication2.Diary.DiaryPreviewActivity;
import com.example.myapplication2.Diary.DiaryWhereActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

public class DiaryHowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_how);

        // 反回上一頁
        final ImageButton imbtnReturnFrontPage5 = findViewById(R.id.imbtnReturnFrontPage5);
        imbtnReturnFrontPage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryHowActivity.this, DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                DiaryHowActivity.this.startActivity(intent,options.toBundle());
            }
        });

        // 前往preview
        final TextView mPreview = findViewById(R.id.btn_preview_how);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtHow_1[0] = "";
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
        final TextView btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_skip.getText().toString().equals("跳題")){
                    DiaryValue.txtHow_1[0] = "";
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
