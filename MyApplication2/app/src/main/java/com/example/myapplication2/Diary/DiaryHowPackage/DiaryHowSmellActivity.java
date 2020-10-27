package com.example.myapplication2.Diary.DiaryHowPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

public class DiaryHowSmellActivity extends AppCompatActivity {


    private Button btn_spice,btn_stink,btn_thick,btn_light_incense,btn_Pungent;
    private boolean spiceClick, stinkClick, thickClick, light_incenseClick, PungentClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_how_smell);

        // 反回上一頁
        final ImageButton imbtnReturnHow = findViewById(R.id.imbtnReturnHow);
        imbtnReturnHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 很重要
                DiaryValue.howCount = 0;
                DiaryValue.Smell_Count = 0;
                Intent intent = new Intent(DiaryHowSmellActivity.this, DiaryHowActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowSmellActivity.this);
                DiaryHowSmellActivity.this.startActivity(intent,options.toBundle());
            }
        });

        final ImageButton imBtnNextHow = findViewById(R.id.imBtnNextHow);
        imBtnNextHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.Smell_Count==0){
                    new AlertDialog.Builder(DiaryHowSmellActivity.this)
                            .setTitle("提醒")
                            .setMessage("請至少點選一個選項")
                            .setPositiveButton("OK", null)
                            .show();
                }else {
                    if(DiaryValue.txtTag.equals("美食")){
                        DiaryValue.txtHow_choose[DiaryValue.howCount] = "嗅覺";
                        DiaryValue.howCount += 1;
                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                            if(spiceClick){
                                DiaryValue.txtHow_food_Smell[j] = "香";
                                j++;
                            }
                            if(stinkClick){
                                DiaryValue.txtHow_food_Smell[j] = "臭";
                                j++;
                            }
                            if(thickClick){
                                DiaryValue.txtHow_food_Smell[j] = "濃厚";
                                j++;
                            }
                            if(light_incenseClick){
                                DiaryValue.txtHow_food_Smell[j] = "淡香";
                                j++;
                            }
                            if(PungentClick){
                                DiaryValue.txtHow_food_Smell[j] = "刺鼻";
                                j++;
                            }
                        }
                    }else {
                        DiaryValue.txtHow_choose[DiaryValue.howCount] = "嗅覺";
                        DiaryValue.howCount += 1;
                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                            if(spiceClick){
                                DiaryValue.txtHow_food_Smell[j] = "芬多精（享受）";
                                j++;
                            }
                            if(stinkClick){
                                DiaryValue.txtHow_food_Smell[j] = "芬芳";
                                j++;
                            }
                            if(thickClick){
                                DiaryValue.txtHow_food_Smell[j] = "清新";
                                j++;
                            }
                            if(light_incenseClick){
                                DiaryValue.txtHow_food_Smell[j] = "污染";
                                j++;
                            }
                        }
                    }
                    Intent intent = new Intent(DiaryHowSmellActivity.this, DiaryHowActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryHowSmellActivity.this);
                    DiaryHowSmellActivity.this.startActivity(intent,options.toBundle());
                }
            }
        });

        btn_spice = findViewById(R.id.btn_spice);
        btn_spice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spiceClick){
                    spiceClick = false;
                    DiaryValue.Smell_Count -=1;
                    btn_spice.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!spiceClick){
                    spiceClick = true;
                    DiaryValue.Smell_Count +=1;
                    btn_spice.setBackgroundTintList(getColorStateList(R.color.how_color2));
                }
            }
        });
        btn_stink = findViewById(R.id.btn_stink);
        btn_stink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stinkClick){
                    stinkClick = false;
                    DiaryValue.Smell_Count -=1;
                    btn_stink.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!stinkClick){
                    stinkClick = true;
                    DiaryValue.Smell_Count +=1;
                    btn_stink.setBackgroundTintList(getColorStateList(R.color.how_color2));
                }
            }
        });
        btn_thick = findViewById(R.id.btn_thick);
        btn_thick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thickClick){
                    thickClick = false;
                    DiaryValue.Smell_Count -=1;
                    btn_thick.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!thickClick){
                    thickClick = true;
                    DiaryValue.Smell_Count +=1;
                    btn_thick.setBackgroundTintList(getColorStateList(R.color.how_color2));
                }
            }
        });
        btn_light_incense = findViewById(R.id.btn_light_incense);
        btn_light_incense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(light_incenseClick){
                    light_incenseClick = false;
                    DiaryValue.Smell_Count -=1;
                    btn_light_incense.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!light_incenseClick){
                    light_incenseClick = true;
                    DiaryValue.Smell_Count +=1;
                    btn_light_incense.setBackgroundTintList(getColorStateList(R.color.how_color2));
                }
            }
        });
        btn_Pungent = findViewById(R.id.btn_Pungent);
        btn_Pungent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PungentClick){
                    PungentClick = false;
                    DiaryValue.Smell_Count -=1;
                    btn_Pungent.setBackgroundTintList(getColorStateList(R.color.how_color1));
                }else if(!PungentClick){
                    PungentClick = true;
                    DiaryValue.Smell_Count +=1;
                    btn_Pungent.setBackgroundTintList(getColorStateList(R.color.how_color2));
                }
            }
        });

        if (DiaryValue.txtTag.equals("旅遊")){
            btn_Pungent.setVisibility(View.INVISIBLE);
            btn_Pungent.setEnabled(false);

            btn_spice.setX(-50);
            btn_spice.setBackgroundResource(R.drawable.btn_diarybtn);
            btn_spice.setBackgroundTintList(getColorStateList(R.color.how_color1));
            Drawable spice = getResources().getDrawable(R.mipmap.ic_btn_fendozin_foreground);
            btn_spice.setCompoundDrawablesWithIntrinsicBounds(null,spice,null,null);

            btn_stink.setBackgroundResource(R.drawable.btn_diarybtn);
            btn_stink.setBackgroundTintList(getColorStateList(R.color.how_color1));
            Drawable stink = getResources().getDrawable(R.mipmap.ic_btn_fragrance_foreground);
            btn_stink.setCompoundDrawablesWithIntrinsicBounds(null,stink,null,null);

            btn_thick.setY(100);
            btn_thick.setX(50);
            btn_thick.setBackgroundResource(R.drawable.btn_diarybtn);
            btn_thick.setBackgroundTintList(getColorStateList(R.color.how_color1));
            Drawable thick = getResources().getDrawable(R.mipmap.ic_btn_pollution_foreground);
            btn_thick.setCompoundDrawablesWithIntrinsicBounds(null,thick,null,null);

            btn_light_incense.setY(30);
            btn_light_incense.setBackgroundResource(R.drawable.btn_diarybtn);
            btn_light_incense.setBackgroundTintList(getColorStateList(R.color.how_color1));
            Drawable light_incense = getResources().getDrawable(R.mipmap.ic_btn_refreshing_foreground);
            btn_light_incense.setCompoundDrawablesWithIntrinsicBounds(null,light_incense,null,null);
        }
    }
}
