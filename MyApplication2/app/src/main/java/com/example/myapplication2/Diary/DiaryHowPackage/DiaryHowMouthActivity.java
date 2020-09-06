package com.example.myapplication2.Diary.DiaryHowPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

public class DiaryHowMouthActivity extends AppCompatActivity {


    private Button btn_homeTown, btn_single, btn_simple, btn_plenty, btn_bitter, btn_hot, btn_acid, btn_sweet, btn_salty, btn_fantastic, btn_unique, btn_nausea;
    private boolean homeTownClick, singleClick, simpleClick, plentyClick, bitterClick, hotClick, acidClick, sweetClick, saltyClick, fantasticClick, uniqueClick, nauseaClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_how_mouth);

        // 反回上一頁
        final ImageButton imbtnReturnHow = findViewById(R.id.imbtnReturnHow);
        imbtnReturnHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 很重要
                //DiaryValue.howCount = 0;
                DiaryValue.Mouth_Count = 0;
                Intent intent = new Intent(DiaryHowMouthActivity.this, DiaryHowActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowMouthActivity.this);
                DiaryHowMouthActivity.this.startActivity(intent,options.toBundle());
            }
        });

        final ImageButton imBtnNextHow = findViewById(R.id.imBtnNextHow);
        imBtnNextHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.Mouth_Count==0){
                    new AlertDialog.Builder(DiaryHowMouthActivity.this)
                            .setTitle("提醒")
                            .setMessage("請至少點選一個選項")
                            .setPositiveButton("OK", null)
                            .show();
                }else {
                    DiaryValue.txtHow_choose[DiaryValue.howCount] = "味覺";
                    DiaryValue.howCount += 1;
                    for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                        if(homeTownClick){
                            DiaryValue.txtHow_food_Mouth[j] = "家鄉味";
                            j++;
                        }
                        if(singleClick){
                            DiaryValue.txtHow_food_Mouth[j] = "單調";
                            j++;
                        }
                        if(simpleClick){
                            DiaryValue.txtHow_food_Mouth[j] = "樸實";
                            j++;
                        }
                        if(plentyClick){
                            DiaryValue.txtHow_food_Mouth[j] = "豐富";
                            j++;
                        }
                        if(bitterClick){
                            DiaryValue.txtHow_food_Mouth[j] = "苦";
                            j++;
                        }
                        if(hotClick){
                            DiaryValue.txtHow_food_Mouth[j] = "辣";
                            j++;
                        }
                        if(acidClick){
                            DiaryValue.txtHow_food_Mouth[j] = "酸";
                            j++;
                        }
                        if(sweetClick){
                            DiaryValue.txtHow_food_Mouth[j] = "甜";
                            j++;
                        }
                        if(saltyClick){
                            DiaryValue.txtHow_food_Mouth[j] = "鹹";
                            j++;
                        }
                        if(fantasticClick){
                            DiaryValue.txtHow_food_Mouth[j] = "奇妙";
                            j++;
                        }
                        if(uniqueClick){
                            DiaryValue.txtHow_food_Mouth[j] = "獨特";
                            j++;
                        }
                        if(nauseaClick){
                            DiaryValue.txtHow_food_Mouth[j] = "噁心";
                            j++;
                        }
                    }

                    Intent intent = new Intent(DiaryHowMouthActivity.this, DiaryHowActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowMouthActivity.this);
                    DiaryHowMouthActivity.this.startActivity(intent,options.toBundle());
                }
            }
        });

        btn_homeTown = findViewById(R.id.btn_homeTown);
        btn_homeTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(homeTownClick){
                    homeTownClick = false;
                    DiaryValue.Mouth_Count -=1;
                    btn_homeTown.setBackgroundResource(R.drawable.button_shape);
                }else if(!homeTownClick){
                    homeTownClick = true;
                    DiaryValue.Mouth_Count +=1;
                    btn_homeTown.setBackgroundResource(R.drawable.button_shape2);
                }
            }
        });
        btn_single = findViewById(R.id.btn_single);
        btn_single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(singleClick){
                    singleClick = false;
                    DiaryValue.Mouth_Count -=1;
                    btn_single.setBackgroundResource(R.drawable.button_shape);
                }else if(!singleClick){
                    singleClick = true;
                    DiaryValue.Mouth_Count +=1;
                    btn_single.setBackgroundResource(R.drawable.button_shape2);
                }
            }
        });
        btn_simple = findViewById(R.id.btn_simple);
        btn_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(simpleClick){
                    simpleClick = false;
                    DiaryValue.Mouth_Count -=1;
                    btn_simple.setBackgroundResource(R.drawable.button_shape);
                }else if(!simpleClick){
                    simpleClick = true;
                    DiaryValue.Mouth_Count +=1;
                    btn_simple.setBackgroundResource(R.drawable.button_shape2);
                }
            }
        });
        btn_plenty = findViewById(R.id.btn_plenty);
        btn_plenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(plentyClick){
                    plentyClick = false;
                    DiaryValue.Mouth_Count -=1;
                    btn_plenty.setBackgroundResource(R.drawable.button_shape);
                }else if(!plentyClick){
                    plentyClick = true;
                    DiaryValue.Mouth_Count +=1;
                    btn_plenty.setBackgroundResource(R.drawable.button_shape2);
                }
            }
        });
        btn_bitter = findViewById(R.id.btn_bitter);
        btn_bitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bitterClick){
                    bitterClick = false;
                    DiaryValue.Mouth_Count -=1;
                    btn_bitter.setBackgroundResource(R.drawable.button_shape);
                }else if(!bitterClick){
                    bitterClick = true;
                    DiaryValue.Mouth_Count +=1;
                    btn_bitter.setBackgroundResource(R.drawable.button_shape2);
                }
            }
        });
        btn_hot = findViewById(R.id.btn_hot);
        btn_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hotClick){
                    hotClick = false;
                    DiaryValue.Mouth_Count -=1;
                    btn_hot.setBackgroundResource(R.drawable.button_shape);
                }else if(!hotClick){
                    hotClick = true;
                    DiaryValue.Mouth_Count +=1;
                    btn_hot.setBackgroundResource(R.drawable.button_shape2);
                }
            }
        });
        btn_acid = findViewById(R.id.btn_acid);
        btn_acid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(acidClick){
                    acidClick = false;
                    DiaryValue.Mouth_Count -=1;
                    btn_acid.setBackgroundResource(R.drawable.button_shape);
                }else if(!acidClick){
                    acidClick = true;
                    DiaryValue.Mouth_Count +=1;
                    btn_acid.setBackgroundResource(R.drawable.button_shape2);
                }
            }
        });
        btn_sweet = findViewById(R.id.btn_sweet);
        btn_sweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sweetClick){
                    sweetClick = false;
                    DiaryValue.Mouth_Count -=1;
                    btn_sweet.setBackgroundResource(R.drawable.button_shape);
                }else if(!sweetClick){
                    sweetClick = true;
                    DiaryValue.Mouth_Count +=1;
                    btn_sweet.setBackgroundResource(R.drawable.button_shape2);
                }
            }
        });
        btn_salty = findViewById(R.id.btn_salty);
        btn_salty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saltyClick){
                    saltyClick = false;
                    DiaryValue.Mouth_Count -=1;
                    btn_salty.setBackgroundResource(R.drawable.button_shape);
                }else if(!saltyClick){
                    saltyClick = true;
                    DiaryValue.Mouth_Count +=1;
                    btn_salty.setBackgroundResource(R.drawable.button_shape2);
                }
            }
        });
        btn_fantastic = findViewById(R.id.btn_fantastic);
        btn_fantastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fantasticClick){
                    fantasticClick = false;
                    DiaryValue.Mouth_Count -=1;
                    btn_fantastic.setBackgroundResource(R.drawable.button_shape);
                }else if(!fantasticClick){
                    fantasticClick = true;
                    DiaryValue.Mouth_Count +=1;
                    btn_fantastic.setBackgroundResource(R.drawable.button_shape2);
                }
            }
        });
        btn_unique = findViewById(R.id.btn_unique);
        btn_unique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uniqueClick){
                    uniqueClick = false;
                    DiaryValue.Mouth_Count -=1;
                    btn_unique.setBackgroundResource(R.drawable.button_shape);
                }else if(!uniqueClick){
                    uniqueClick = true;
                    DiaryValue.Mouth_Count +=1;
                    btn_unique.setBackgroundResource(R.drawable.button_shape2);
                }
            }
        });
        btn_nausea = findViewById(R.id.btn_nausea);
        btn_nausea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nauseaClick){
                    nauseaClick = false;
                    DiaryValue.Mouth_Count -=1;
                    btn_nausea.setBackgroundResource(R.drawable.button_shape);
                }else if(!nauseaClick){
                    nauseaClick = true;
                    DiaryValue.Mouth_Count +=1;
                    btn_nausea.setBackgroundResource(R.drawable.button_shape2);
                }
            }
        });

    }
}
