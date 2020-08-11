package com.example.myapplication2.Diary.DiaryHowPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

public class DiaryHowEyeActivity extends AppCompatActivity {

    private Button btn_normal,btn_phase,btn_strange,btn_delicate,btn_special;
    private boolean normalClick, phaseClick, strangeClick, delicateClick, specialClick;

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
                DiaryValue.howCount = 0;
                DiaryValue.SelectCount = 0;
                Intent intent = new Intent(DiaryHowEyeActivity.this, DiaryHowActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowEyeActivity.this);
                DiaryHowEyeActivity.this.startActivity(intent,options.toBundle());
            }
        });

        final ImageButton imBtnNextHow = findViewById(R.id.imBtnNextHow);
        imBtnNextHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.SelectCount==0){
                    new AlertDialog.Builder(DiaryHowEyeActivity.this)
                            .setTitle("提醒")
                            .setMessage("請至少點選一個選項")
                            .setPositiveButton("OK", null)
                            .show();
                }else {
                    DiaryValue.txtHow_choose[0] = "視覺";
                    DiaryValue.howCount += 1;
                    for(int j = 0; j < DiaryValue.SelectCount; j++){
                        if(normalClick){
                            DiaryValue.txtHow_food[j] = "樸素";
                            j++;
                        }
                        if(phaseClick){
                            DiaryValue.txtHow_food[j] = "有層次感";
                            j++;
                        }
                        if(strangeClick){
                            DiaryValue.txtHow_food[j] = "怪異";
                            j++;
                        }
                        if(delicateClick){
                            DiaryValue.txtHow_food[j] = "精緻";
                            j++;
                        }
                        if(specialClick){
                            DiaryValue.txtHow_food[j] = "特別";
                            j++;
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
                    DiaryValue.SelectCount -=1;
                    btn_normal.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!normalClick){
                    normalClick = true;
                    DiaryValue.SelectCount +=1;
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
                    DiaryValue.SelectCount -=1;
                    btn_phase.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!phaseClick){
                    phaseClick = true;
                    DiaryValue.SelectCount +=1;
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
                    DiaryValue.SelectCount -=1;
                    btn_strange.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!strangeClick){
                    strangeClick = true;
                    DiaryValue.SelectCount +=1;
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
                    DiaryValue.SelectCount -=1;
                    btn_delicate.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!delicateClick){
                    delicateClick = true;
                    DiaryValue.SelectCount +=1;
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
                    DiaryValue.SelectCount -=1;
                    btn_special.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!specialClick){
                    specialClick = true;
                    DiaryValue.SelectCount +=1;
                    btn_special.setBackgroundTintList(getColorStateList(R.color.how_color2));
                }
            }
        });


    }
}
