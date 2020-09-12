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
        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("購物")){
            txtWhy_title.setText("為甚麼想買這個呢?");
        }

        final ProgressBar progressBarWhy = findViewById(R.id.progressBarWhy);
        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("購物")){
            progressBarWhy.setProgress(60);
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
                }
            }
        });

        final Button btn_try = findViewById(R.id.btn_try);
        if (DiaryValue.txtTag.equals("美食")) {
            btn_try.setEnabled(true);
            btn_try.setVisibility(View.VISIBLE);
        } else if (DiaryValue.txtTag.equals("購物")) {
            btn_try.setEnabled(false);
            btn_try.setVisibility(View.INVISIBLE);
        }
        btn_try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiaryValue.txtTag.equals("美食")) {
                    DiaryValue.txtWhy = "嚐鮮";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
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
                }
            }
        });

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
                } else if (DiaryValue.txtTag.equals("購物")) {
                    DiaryValue.txtWhy = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhyActivity.this,DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhyActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        // 控制圖案
        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("購物")){
            //btn_party.setBackgroundResource(R.mipmap);


            btn_yummy.setBackgroundResource(R.drawable.btn_nogo);
            Drawable yummy = getResources().getDrawable(R.mipmap.btn_nogo_foreground);
            btn_yummy.setCompoundDrawablesWithIntrinsicBounds(null,yummy,null,null);
            btn_yummy.setPadding(0,15,0,0);
            btn_yummy.setX(70);

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
            Drawable tired = getResources().getDrawable(R.mipmap.btn_impulsive_foreground);
            btn_tired.setCompoundDrawablesWithIntrinsicBounds(null,tired,null,null);
            btn_tired.setPadding(0,50,0,0);
            btn_tired.setX(-50);
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
