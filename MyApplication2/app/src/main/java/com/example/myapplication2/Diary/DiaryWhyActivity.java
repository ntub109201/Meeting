package com.example.myapplication2.Diary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;
public class DiaryWhyActivity extends AppCompatActivity{

    //public static String txtWhy = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarywhy);

        final TextView txtWhy_title = findViewById(R.id.txtWhy_title);
        int randomNum = (int)(Math.random()* 3 + 1);
        Diary_dictionary dict = new Diary_dictionary();
        String total = DiaryValue.txtMood+"_"+DiaryValue.txtTag+"_Why_"+String.valueOf(randomNum);
        txtWhy_title.setText(dict.dict.get(total));

        final ProgressBar progressBarWhy = findViewById(R.id.progressBarWhy);
        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("購物")){
            progressBarWhy.setProgress(60);
        }else if(DiaryValue.txtTag.equals("休閒娛樂")){
            progressBarWhy.setProgress(20);
        }else if(DiaryValue.txtTag.equals("戀愛")){
            progressBarWhy.setProgress(50);
        }else if(DiaryValue.txtTag.equals("旅遊")){
            progressBarWhy.setProgress(50);
        }


        // 反回上一頁
        final ImageButton imbtnReturnFrontPage3 = findViewById(R.id.imbtnReturnFrontPage3);
        imbtnReturnFrontPage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiaryValue.txtTag.equals("美食")) {
                    Intent intent = new Intent(DiaryWhyActivity.this, DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    DiaryWhyActivity.this.startActivity(intent,options.toBundle());
                } else if (DiaryValue.txtTag.equals("購物")) {
                    Intent intent = new Intent(DiaryWhyActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    DiaryWhyActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    Intent intent = new Intent(DiaryWhyActivity.this, DiaryTagActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    DiaryWhyActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    Intent intent = new Intent(DiaryWhyActivity.this, DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    DiaryWhyActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("旅遊")){
                    Intent intent = new Intent(DiaryWhyActivity.this, DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    DiaryWhyActivity.this.startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_nowhy = findViewById(R.id.btn_nowhy);

        btn_nowhy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiaryValue.txtTag.equals("美食")) {
                    DiaryValue.txtWhy = "沒為什麼";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if (DiaryValue.txtTag.equals("購物")) {
                    DiaryValue.txtWhy = "沒為什麼";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWhy = "沒為什麼";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if(DiaryValue.txtTag.equals("旅遊")){
                    DiaryValue.txtWhy = "挑戰刺激";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_party = findViewById(R.id.btn_party);
        btn_party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiaryValue.txtTag.equals("美食")) {
                    DiaryValue.txtWhy = "聚會";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if (DiaryValue.txtTag.equals("購物")) {
                    DiaryValue.txtWhy = "衝動性消費";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    if(DiaryValue.txtMood.equals("心情5")){
                        DiaryValue.txtWhy = "跟別人鬧不開心";
                    }else {
                        DiaryValue.txtWhy = "想瘦身";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    if(DiaryValue.txtMood.equals("心情5")){
                        DiaryValue.txtWhy = "意見不合";
                    }else {
                        DiaryValue.txtWhy = "慶祝節日";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }else if (DiaryValue.txtTag.equals("旅遊")) {
                    DiaryValue.txtWhy = "節慶";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_hungry = findViewById(R.id.btn_hungry);
        btn_hungry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiaryValue.txtTag.equals("美食")) {
                    DiaryValue.txtWhy = "餓了";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if (DiaryValue.txtTag.equals("購物")) {
                    DiaryValue.txtWhy = "想逛街";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if (DiaryValue.txtTag.equals("旅遊")) {
                    DiaryValue.txtWhy = "工作出差";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_celebrate = findViewById(R.id.btn_celebrate);
        btn_celebrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiaryValue.txtTag.equals("美食")) {
                    DiaryValue.txtWhy = "慶祝";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if (DiaryValue.txtTag.equals("購物")) {
                    DiaryValue.txtWhy = "有需求";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if(DiaryValue.txtTag.equals("休閒娛樂")){

                    if(DiaryValue.txtMood.equals("心情5")){
                        DiaryValue.txtWhy = "壓力過大";
                    }else {
                        DiaryValue.txtWhy = "無聊";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    if(DiaryValue.txtMood.equals("心情5")){
                        DiaryValue.txtWhy = "價值觀不同";
                    }else {
                        DiaryValue.txtWhy = "一時興起";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }else if (DiaryValue.txtTag.equals("旅遊")) {
                    DiaryValue.txtWhy = "網路推薦";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_try = findViewById(R.id.btn_try);

        btn_try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiaryValue.txtTag.equals("美食")) {
                    DiaryValue.txtWhy = "嚐鮮";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }else if (DiaryValue.txtTag.equals("旅遊")) {
                    DiaryValue.txtWhy = "拜訪親友";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_yummy = findViewById(R.id.btn_yummy);
        btn_yummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiaryValue.txtTag.equals("美食")) {
                    DiaryValue.txtWhy = "合胃口";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if (DiaryValue.txtTag.equals("購物")) {
                    DiaryValue.txtWhy = "本來沒有要去";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    if(DiaryValue.txtMood.equals("心情5")){
                        DiaryValue.txtWhy = "做事情不順";
                    }else {
                        DiaryValue.txtWhy = "打發時間";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    if(DiaryValue.txtMood.equals("心情5")){
                        DiaryValue.txtWhy = "失去新鮮感";
                    }else {
                        DiaryValue.txtWhy = "預定好的行程";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }else if (DiaryValue.txtTag.equals("旅遊")) {
                    DiaryValue.txtWhy = "放鬆身心";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_tired = findViewById(R.id.btn_tired);
        btn_tired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiaryValue.txtTag.equals("美食")) {
                    DiaryValue.txtWhy = "嘴饞";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if (DiaryValue.txtTag.equals("購物")) {
                    DiaryValue.txtWhy = "送禮";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    if(DiaryValue.txtMood.equals("心情5")){
                        DiaryValue.txtWhy = "考試考不好";
                    }else {
                        DiaryValue.txtWhy = "紓壓";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    if(DiaryValue.txtMood.equals("心情5")){
                        DiaryValue.txtWhy = "不想忍耐了";
                    }else {
                        DiaryValue.txtWhy = "情侶日常";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }else if (DiaryValue.txtTag.equals("旅遊")) {
                    DiaryValue.txtWhy = "休閒度假";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        if (DiaryValue.txtTag.equals("購物")) {
            btn_try.setEnabled(false);
            btn_try.setVisibility(View.INVISIBLE);
        } else if(DiaryValue.txtTag.equals("休閒娛樂")){
            btn_try.setEnabled(false);
            btn_try.setVisibility(View.INVISIBLE);
            btn_hungry.setEnabled(false);
            btn_hungry.setVisibility(View.INVISIBLE);
        }else if(DiaryValue.txtTag.equals("戀愛")){
            btn_try.setEnabled(false);
            btn_try.setVisibility(View.INVISIBLE);
            btn_hungry.setEnabled(false);
            btn_hungry.setVisibility(View.INVISIBLE);
            btn_nowhy.setEnabled(false);
            btn_nowhy.setVisibility(View.INVISIBLE);
        }

        // 前往preview
        final TextView mPreview = findViewById(R.id.btn_preview_why);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiaryValue.txtTag.equals("美食")) {
                    DiaryValue.txtWhy = "";
                    DiaryValue.txtWhere = "";
                    DiaryValue.txtWhen = "";
                    DiaryValue.txtWho = "";
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
                } else if (DiaryValue.txtTag.equals("購物")) {
                    DiaryValue.txtWhat = "";
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
                } else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWhat = "";
                    DiaryValue.txtWhy = "";
                    DiaryValue.txtWhere = "";
                    DiaryValue.txtWhen = "";
                    DiaryValue.txtWho = "";
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
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    DiaryValue.txtWhy = "";
                    DiaryValue.txtWhen = "";
                    DiaryValue.txtWho = "";
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
                } else if (DiaryValue.txtTag.equals("旅遊")) {
                    DiaryValue.txtWhy = "";
                    DiaryValue.txtWhen = "";
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
            }
        });

        //跳題
        final TextView btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiaryValue.txtTag.equals("美食")) {
                    DiaryValue.txtWhy = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if(DiaryValue.txtTag.equals("購物")) {
                    DiaryValue.txtWhy = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWhy = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if(DiaryValue.txtTag.equals("戀愛")){
                    DiaryValue.txtWhy = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                } else if(DiaryValue.txtTag.equals("旅遊")){
                    DiaryValue.txtWhy = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        // 控制圖案
        if(DiaryValue.txtTag.equals("美食")){

        }if(DiaryValue.txtTag.equals("購物")){
            btn_party.setBackgroundResource(R.drawable.btn_tired);
            Drawable party = getResources().getDrawable(R.mipmap.ic_impulsive_foreground);
            btn_party.setCompoundDrawablesWithIntrinsicBounds(null,party,null,null);
            btn_party.setPadding(0,30,0,0);
            btn_party.setX(60);
            btn_party.setY(50);

            btn_yummy.setBackgroundResource(R.drawable.btn_nogo);
            Drawable yummy = getResources().getDrawable(R.mipmap.btn_nogo_foreground);
            btn_yummy.setCompoundDrawablesWithIntrinsicBounds(null,yummy,null,null);
            btn_yummy.setPadding(0,15,0,0);
            btn_yummy.setX(70);
            btn_yummy.setY(-30);

            btn_hungry.setBackgroundResource(R.drawable.btn_shopping);
            Drawable hungry = getResources().getDrawable(R.mipmap.btn_shopping_foreground);
            btn_hungry.setCompoundDrawablesWithIntrinsicBounds(null,hungry,null,null);
            btn_hungry.setPadding(0,15,0,0);

            btn_celebrate.setBackgroundResource(R.drawable.btn_request);
            Drawable celebrate = getResources().getDrawable(R.mipmap.btn_requests_foreground);
            btn_celebrate.setCompoundDrawablesWithIntrinsicBounds(null,celebrate,null,null);
            btn_celebrate.setPadding(0,15,0,0);
            btn_celebrate.setX(-70);
            btn_celebrate.setY(50);

            btn_tired.setBackgroundResource(R.drawable.btn_impulsive);
            Drawable tired = getResources().getDrawable(R.mipmap.ic_gift_foreground);
            btn_tired.setCompoundDrawablesWithIntrinsicBounds(null,tired,null,null);
            btn_tired.setPadding(0,50,0,0);
            btn_tired.setX(-50);
            btn_tired.setY(-30);
        } else if(DiaryValue.txtTag.equals("休閒娛樂")){

            if(DiaryValue.txtMood.equals("心情5")){
                btn_party.setBackgroundResource(R.drawable.btn_meeting);
                Drawable party = getResources().getDrawable(R.mipmap.ic_argue_foreground);
                btn_party.setCompoundDrawablesWithIntrinsicBounds(null,party,null,null);
                btn_party.setPadding(0,15,0,0);
                btn_party.setX(100);
                btn_party.setY(-200);

                btn_yummy.setBackgroundResource(R.drawable.btn_yummy);
                Drawable yummy = getResources().getDrawable(R.mipmap.ic_badthings_foreground);
                btn_yummy.setCompoundDrawablesWithIntrinsicBounds(null,yummy,null,null);
                btn_yummy.setPadding(0,15,0,0);
                btn_yummy.setX(100);
                btn_yummy.setY(-250);

                btn_celebrate.setBackgroundResource(R.drawable.btn_celebrate);
                Drawable celebrate = getResources().getDrawable(R.mipmap.ic_stressful_foreground);
                btn_celebrate.setCompoundDrawablesWithIntrinsicBounds(null,celebrate,null,null);
                btn_celebrate.setPadding(5,15,0,0);
                btn_celebrate.setX(-70);
                btn_celebrate.setY(-150);

                btn_tired.setBackgroundResource(R.drawable.btn_tired);
                Drawable tired = getResources().getDrawable(R.mipmap.ic_testfail_foreground);
                btn_tired.setCompoundDrawablesWithIntrinsicBounds(null,tired,null,null);
                btn_tired.setPadding(0,50,0,0);
                btn_tired.setX(-70);
                btn_tired.setY(-200);

                btn_nowhy.setVisibility(View.INVISIBLE);
                btn_nowhy.setEnabled(false);
            }else {
                btn_nowhy.setY(50);
                btn_nowhy.setX(100);

                btn_party.setBackgroundResource(R.drawable.btn_meeting);
                Drawable party = getResources().getDrawable(R.mipmap.ic_loseweight_foreground);
                btn_party.setCompoundDrawablesWithIntrinsicBounds(null,party,null,null);
                btn_party.setPadding(0,15,0,0);
                btn_party.setX(50);
                btn_party.setY(30);

                btn_yummy.setBackgroundResource(R.drawable.btn_yummy);
                Drawable yummy = getResources().getDrawable(R.mipmap.ic_passtime_foreground);
                btn_yummy.setCompoundDrawablesWithIntrinsicBounds(null,yummy,null,null);
                btn_yummy.setPadding(0,15,0,0);
                btn_yummy.setX(100);
                btn_yummy.setY(-100);

                btn_celebrate.setBackgroundResource(R.drawable.btn_celebrate);
                Drawable celebrate = getResources().getDrawable(R.mipmap.ic_boring_foreground);
                btn_celebrate.setCompoundDrawablesWithIntrinsicBounds(null,celebrate,null,null);
                btn_celebrate.setPadding(5,15,0,0);
                btn_celebrate.setX(-70);
                btn_celebrate.setY(-100);

                btn_tired.setBackgroundResource(R.drawable.btn_tired);
                Drawable tired = getResources().getDrawable(R.mipmap.ic_relax_foreground);
                btn_tired.setCompoundDrawablesWithIntrinsicBounds(null,tired,null,null);
                btn_tired.setPadding(0,50,0,0);
                btn_tired.setX(-70);
                btn_tired.setY(-200);
            }
        }else if(DiaryValue.txtTag.equals("戀愛")){

            if(DiaryValue.txtMood.equals("心情5")){
                btn_party.setBackgroundResource(R.drawable.btn_meeting);
                Drawable party = getResources().getDrawable(R.mipmap.ic_conflictlover_foreground);
                btn_party.setCompoundDrawablesWithIntrinsicBounds(null,party,null,null);
                btn_party.setPadding(0,15,0,0);
                btn_party.setX(70);
                btn_party.setY(-100);

                btn_yummy.setBackgroundResource(R.drawable.btn_yummy);
                Drawable yummy = getResources().getDrawable(R.mipmap.ic_lostfresh_foreground);
                btn_yummy.setCompoundDrawablesWithIntrinsicBounds(null,yummy,null,null);
                btn_yummy.setPadding(0,15,0,0);
                btn_yummy.setX(100);
                btn_yummy.setY(-200);

                btn_celebrate.setBackgroundResource(R.drawable.btn_celebrate);
                Drawable celebrate = getResources().getDrawable(R.mipmap.ic_diffvalue_foreground);
                btn_celebrate.setCompoundDrawablesWithIntrinsicBounds(null,celebrate,null,null);
                btn_celebrate.setPadding(5,15,0,0);
                btn_celebrate.setX(-100);
                btn_celebrate.setY(-200);

                btn_tired.setBackgroundResource(R.drawable.btn_tired);
                Drawable tired = getResources().getDrawable(R.mipmap.ic_canthandle_foreground);
                btn_tired.setCompoundDrawablesWithIntrinsicBounds(null,tired,null,null);
                btn_tired.setPadding(0,50,0,0);
                btn_tired.setX(-70);
                btn_tired.setY(-300);

            }else {

                btn_party.setBackgroundResource(R.drawable.btn_meeting);
                Drawable party = getResources().getDrawable(R.mipmap.ic_celebratelover_foreground);
                btn_party.setCompoundDrawablesWithIntrinsicBounds(null,party,null,null);
                btn_party.setPadding(0,15,0,0);
                btn_party.setX(100);
                btn_party.setY(-250);

                btn_yummy.setBackgroundResource(R.drawable.btn_yummy);
                Drawable yummy = getResources().getDrawable(R.mipmap.ic_schedule_foreground);
                btn_yummy.setCompoundDrawablesWithIntrinsicBounds(null,yummy,null,null);
                btn_yummy.setPadding(0,15,0,0);
                btn_yummy.setX(100);
                btn_yummy.setY(-300);

                btn_celebrate.setBackgroundResource(R.drawable.btn_celebrate);
                Drawable celebrate = getResources().getDrawable(R.mipmap.ic_idealover_foreground);
                btn_celebrate.setCompoundDrawablesWithIntrinsicBounds(null,celebrate,null,null);
                btn_celebrate.setPadding(5,15,0,0);
                btn_celebrate.setX(-70);
                btn_celebrate.setY(-150);

                btn_tired.setBackgroundResource(R.drawable.btn_tired);
                Drawable tired = getResources().getDrawable(R.mipmap.ic_loverdaily_foreground);
                btn_tired.setCompoundDrawablesWithIntrinsicBounds(null,tired,null,null);
                btn_tired.setPadding(0,50,0,0);
                btn_tired.setX(-70);
                btn_tired.setY(-200);
            }
        }else if(DiaryValue.txtTag.equals("旅遊")){

            btn_nowhy.setBackgroundResource(R.drawable.btn_nowhy);
            Drawable nowhy = getResources().getDrawable(R.mipmap.ic_btn_challenge_foreground);
            btn_nowhy.setCompoundDrawablesWithIntrinsicBounds(null,nowhy,null,null);
            btn_nowhy.setPadding(0,30,0,0);

            btn_party.setBackgroundResource(R.drawable.btn_meeting);
            Drawable party = getResources().getDrawable(R.mipmap.ic_btn_festival_foreground);
            btn_party.setCompoundDrawablesWithIntrinsicBounds(null,party,null,null);
            btn_party.setPadding(0,40,0,0);

            btn_hungry.setBackgroundResource(R.drawable.btn_hungry);
            Drawable hungry = getResources().getDrawable(R.mipmap.ic_btn_jobs_foreground);
            btn_hungry.setCompoundDrawablesWithIntrinsicBounds(null,hungry,null,null);
            btn_hungry.setPadding(0,30,0,0);

            btn_celebrate.setBackgroundResource(R.drawable.btn_celebrate);
            Drawable celebrate = getResources().getDrawable(R.mipmap.ic_btn_network_foreground);
            btn_celebrate.setCompoundDrawablesWithIntrinsicBounds(null,celebrate,null,null);
            btn_celebrate.setPadding(0,35,0,0);

            btn_try.setBackgroundResource(R.drawable.btn_new);
            Drawable try1 = getResources().getDrawable(R.mipmap.ic_btn_relative_foreground);
            btn_try.setCompoundDrawablesWithIntrinsicBounds(null,try1,null,null);
            btn_try.setPadding(0,30,0,0);

            btn_yummy.setBackgroundResource(R.drawable.btn_yummy);
            Drawable yummy = getResources().getDrawable(R.mipmap.ic_btn_relax_foreground);
            btn_yummy.setCompoundDrawablesWithIntrinsicBounds(null,yummy,null,null);
            btn_yummy.setPadding(0,15,0,0);

            btn_tired.setBackgroundResource(R.drawable.btn_tired);
            Drawable tired = getResources().getDrawable(R.mipmap.ic_btn_vacation_foreground);
            btn_tired.setCompoundDrawablesWithIntrinsicBounds(null,tired,null,null);
            btn_tired.setPadding(0,50,0,0);

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
