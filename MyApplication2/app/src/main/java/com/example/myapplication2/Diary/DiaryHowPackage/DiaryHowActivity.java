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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication2.Diary.DiaryPreviewActivity;
import com.example.myapplication2.Diary.DiaryTravelWhereActivity;
import com.example.myapplication2.Diary.DiaryWhenActivity;
import com.example.myapplication2.Diary.DiaryWhereActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

public class DiaryHowActivity extends AppCompatActivity {

    private Button btn_eye, btn_mouth, btn_nose;
    private TextView mPreview, btn_skip;
    private ImageButton imBtnNextHow;
    private ProgressBar progressBarHow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_how);

        progressBarHow = findViewById(R.id.progressBarHow);
        if(DiaryValue.txtWhat.equals("美食")){
            progressBarHow.setProgress(65);
        }else {
            progressBarHow.setProgress(100);
        }
        final TextView textView_How = findViewById(R.id.textView_How);
        int randomNum = (int)(Math.random()* 3 + 1);
        How_dictionary dict = new How_dictionary();
        String total = DiaryValue.txtMood+"_"+DiaryValue.txtTag+"_How_"+String.valueOf(randomNum);
        textView_How.setText(dict.dict.get(total));
        // 反回上一頁
        final ImageButton imbtnReturnFrontPage5 = findViewById(R.id.imbtnReturnFrontPage5);
        imbtnReturnFrontPage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i< 5; i++){
                    DiaryValue.txtHow_choose[i] = "";
                }
                DiaryValue.howCount = 0;
                DiaryValue.Eye_Count = 0;
                DiaryValue.Mouth_Count = 0;
                DiaryValue.Smell_Count = 0;
                if(DiaryValue.txtTag.equals("美食")){
                    Intent intent = new Intent(DiaryHowActivity.this, DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                    DiaryHowActivity.this.startActivity(intent,options.toBundle());
                }else {
                    Intent intent = new Intent(DiaryHowActivity.this, DiaryTravelWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                    DiaryHowActivity.this.startActivity(intent,options.toBundle());
                }
            }
        });


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
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWhen = "";
                    DiaryValue.txtWho = "";
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
                }else{
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
            }
        });

        //跳題
        btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    for(int i = 0; i< 5; i++){
                        DiaryValue.txtHow_choose[i] = "";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryHowActivity.this, DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                    startActivity(intent,options.toBundle());
                }else {
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
            }
        });

        imBtnNextHow = findViewById(R.id.imBtnNextHow);
        if(DiaryValue.howCount!=0){
            btn_skip.setText("下一題");
            btn_skip.setVisibility(View.INVISIBLE);
            btn_skip.setEnabled(false);
            imBtnNextHow.setVisibility(View.VISIBLE);
            imBtnNextHow.setEnabled(true);
        }else{
            btn_skip.setText("跳題");
            btn_skip.setVisibility(View.VISIBLE);
            btn_skip.setEnabled(true);
            imBtnNextHow.setVisibility(View.INVISIBLE);
            imBtnNextHow.setEnabled(false);
        }

        imBtnNextHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    Intent intent = new Intent();
                    intent.setClass(DiaryHowActivity.this, DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                    startActivity(intent,options.toBundle());
                }else {
                    Intent intent = new Intent();
                    intent.setClass(DiaryHowActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryHowActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        if(DiaryValue.txtTag.equals("旅遊")){
            btn_mouth.setEnabled(false);
            btn_mouth.setVisibility(View.INVISIBLE);
            btn_nose.setY(100);
        }

    }


    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            for(int i = 0; i< 5; i++){
                DiaryValue.txtHow_choose[i] = "";
            }
            DiaryValue.howCount = 0;
            DiaryValue.Eye_Count = 0;
            DiaryValue.Mouth_Count = 0;
            DiaryValue.Smell_Count = 0;
            if(DiaryValue.txtTag.equals("美食")){
                Intent intent = new Intent(DiaryHowActivity.this, DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                DiaryHowActivity.this.startActivity(intent,options.toBundle());
            }else {
                Intent intent = new Intent(DiaryHowActivity.this, DiaryTravelWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowActivity.this);
                DiaryHowActivity.this.startActivity(intent,options.toBundle());
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
