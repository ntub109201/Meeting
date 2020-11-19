package com.example.myapplication2.Diary.DiaryHowPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

public class DiaryHowEyeActivity extends AppCompatActivity {

    private Button btn_normal,btn_phase,btn_strange,btn_delicate,btn_special,btn_vast;
    private boolean normalClick, phaseClick, strangeClick, delicateClick, specialClick, vastClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_how_eye);

        // 反回上一頁
        final ImageButton imbtnReturnHow = findViewById(R.id.imbtnReturnHow);
        imbtnReturnHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 很重要
                //DiaryValue.howCount = 0;
                DiaryValue.Eye_Count = 0;
                Intent intent = new Intent(DiaryHowEyeActivity.this, DiaryHowActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowEyeActivity.this);
                DiaryHowEyeActivity.this.startActivity(intent,options.toBundle());
            }
        });

        final ImageButton imBtnNextHow = findViewById(R.id.imBtnNextHow);
        imBtnNextHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.Eye_Count==0){
                    new AlertDialog.Builder(DiaryHowEyeActivity.this)
                            .setTitle("提醒")
                            .setMessage("請至少點選一個選項")
                            .setPositiveButton("OK", null)
                            .show();
                }else {
                    if(DiaryValue.txtTag.equals("美食")){
                        DiaryValue.txtHow_choose[DiaryValue.howCount] = "視覺";
                        DiaryValue.howCount += 1;
                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                            if(normalClick){
                                DiaryValue.txtHow_food_Eye[j] = "樸素";
                                j++;
                            }
                            if(phaseClick){
                                DiaryValue.txtHow_food_Eye[j] = "有層次感";
                                j++;
                            }
                            if(strangeClick){
                                DiaryValue.txtHow_food_Eye[j] = "怪異";
                                j++;
                            }
                            if(delicateClick){
                                DiaryValue.txtHow_food_Eye[j] = "精緻";
                                j++;
                            }
                            if(specialClick){
                                DiaryValue.txtHow_food_Eye[j] = "特別";
                                j++;
                            }
                        }
                    }else {
                        DiaryValue.txtHow_choose[DiaryValue.howCount] = "視覺";
                        DiaryValue.howCount += 1;
                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                            if(normalClick){
                                DiaryValue.txtHow_food_Eye[j] = "繽紛";
                                j++;
                            }
                            if(phaseClick){
                                DiaryValue.txtHow_food_Eye[j] = "擁擠";
                                j++;
                            }
                            if(strangeClick){
                                DiaryValue.txtHow_food_Eye[j] = "療癒";
                                j++;
                            }
                            if(delicateClick){
                                DiaryValue.txtHow_food_Eye[j] = "狹隘";
                                j++;
                            }
                            if(specialClick){
                                DiaryValue.txtHow_food_Eye[j] = "震撼";
                                j++;
                            }
                            if(vastClick){
                                DiaryValue.txtHow_food_Eye[j] = "遼闊";
                                j++;
                            }
                        }
                    }
                    Intent intent = new Intent(DiaryHowEyeActivity.this, DiaryHowActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowEyeActivity.this);
                    DiaryHowEyeActivity.this.startActivity(intent,options.toBundle());
                }
            }
        });


        btn_normal = findViewById(R.id.btn_normal);
        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(normalClick){
                    normalClick = false;
                    DiaryValue.Eye_Count -=1;
                    btn_normal.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!normalClick){
                    normalClick = true;
                    DiaryValue.Eye_Count +=1;
                    btn_normal.setBackgroundTintList(getColorStateList(R.color.how_color2));
                }
            }
        });

        btn_phase = findViewById(R.id.btn_phase);
        btn_phase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phaseClick){
                    phaseClick = false;
                    DiaryValue.Eye_Count -=1;
                    btn_phase.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!phaseClick){
                    phaseClick = true;
                    DiaryValue.Eye_Count +=1;
                    btn_phase.setBackgroundTintList(getColorStateList(R.color.how_color2));
                }
            }
        });
        btn_strange = findViewById(R.id.btn_strange);
        btn_strange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strangeClick){
                    strangeClick = false;
                    DiaryValue.Eye_Count -=1;
                    btn_strange.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!strangeClick){
                    strangeClick = true;
                    DiaryValue.Eye_Count +=1;
                    btn_strange.setBackgroundTintList(getColorStateList(R.color.how_color2));
                }
            }
        });
        btn_delicate = findViewById(R.id.btn_delicate);
        btn_delicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(delicateClick){
                    delicateClick = false;
                    DiaryValue.Eye_Count -=1;
                    btn_delicate.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!delicateClick){
                    delicateClick = true;
                    DiaryValue.Eye_Count +=1;
                    btn_delicate.setBackgroundTintList(getColorStateList(R.color.how_color2));
                }
            }
        });
        btn_special = findViewById(R.id.btn_special);
        btn_special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(specialClick){
                    specialClick = false;
                    DiaryValue.Eye_Count -=1;
                    btn_special.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!specialClick){
                    specialClick = true;
                    DiaryValue.Eye_Count +=1;
                    btn_special.setBackgroundTintList(getColorStateList(R.color.how_color2));
                }
            }
        });

        btn_vast = findViewById(R.id.btn_vast);
        btn_vast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vastClick){
                    vastClick = false;
                    DiaryValue.Eye_Count -=1;
                    btn_special.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!vastClick){
                    vastClick = true;
                    DiaryValue.Eye_Count +=1;
                    btn_special.setBackgroundTintList(getColorStateList(R.color.how_color2));
                }
            }
        });

        if(DiaryValue.txtTag.equals("旅遊")){

            btn_vast.setVisibility(View.VISIBLE);
            btn_vast.setEnabled(true);
            btn_vast.setBackgroundResource(R.drawable.btn_normal);
            Drawable vast = getResources().getDrawable(R.mipmap.ic_btn_vast_foreground);
            btn_vast.setCompoundDrawablesWithIntrinsicBounds(null,vast,null,null);
            btn_vast.setY(-60);
            btn_vast.setX(-20);

            btn_normal.setBackgroundResource(R.drawable.btn_normal);
            Drawable normal = getResources().getDrawable(R.mipmap.ic_btn_colorful_foreground);
            btn_normal.setCompoundDrawablesWithIntrinsicBounds(null,normal,null,null);
            btn_special.setX(-70);

            btn_phase.setBackgroundResource(R.drawable.btn_normal);
            Drawable phase = getResources().getDrawable(R.mipmap.ic_btn_crowded_foreground);
            btn_phase.setCompoundDrawablesWithIntrinsicBounds(null,phase,null,null);
            btn_phase.setY(-90);
            btn_normal.setX(-60);

            btn_strange.setBackgroundResource(R.drawable.btn_normal);
            Drawable strange = getResources().getDrawable(R.mipmap.ic_btn_healing_foreground);
            btn_strange.setCompoundDrawablesWithIntrinsicBounds(null,strange,null,null);

            btn_delicate.setBackgroundResource(R.drawable.btn_normal);
            Drawable delicate = getResources().getDrawable(R.mipmap.ic_btn_narrow_foreground);
            btn_delicate.setCompoundDrawablesWithIntrinsicBounds(null,delicate,null,null);
            btn_delicate.setY(-120);

            btn_special.setBackgroundResource(R.drawable.btn_normal);
            Drawable special = getResources().getDrawable(R.mipmap.ic_btn_shock_foreground);
            btn_special.setCompoundDrawablesWithIntrinsicBounds(null,special,null,null);
            btn_special.setX(-100);

        }

    }

    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            // 很重要
            //DiaryValue.howCount = 0;
            DiaryValue.Eye_Count = 0;
            Intent intent = new Intent(DiaryHowEyeActivity.this, DiaryHowActivity.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowEyeActivity.this);
            DiaryHowEyeActivity.this.startActivity(intent,options.toBundle());
        }
        return super.onKeyDown(keyCode, event);
    }
}
