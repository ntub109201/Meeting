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
    }
}
