package com.example.myapplication2.Diary;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

public class DiaryTagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarytag);

        // 返回心情
        final ImageButton imbtnReturnToDiary = (ImageButton) findViewById(R.id.imbtnReturnToDiary);
        imbtnReturnToDiary.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(DiaryTagActivity.this, DiaryMoodActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTagActivity.this);
                DiaryTagActivity.this.startActivity(intent,options.toBundle());
            }
        });

        // 前往下一頁 美食
        final Button btn_food = findViewById(R.id.btn_food);
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtTag ="美食";
                Intent intent = new Intent(DiaryTagActivity.this, DiaryWhatActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTagActivity.this);
                DiaryTagActivity.this.startActivity(intent,options.toBundle());
            }
        });

        // 前往下一頁 購物
        final Button btn_clothes = findViewById(R.id.btn_clothes);
        btn_clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtTag ="購物";
                Intent intent = new Intent(DiaryTagActivity.this, DiaryWhenActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTagActivity.this);
                DiaryTagActivity.this.startActivity(intent,options.toBundle());
            }
        });
        // 前往下一頁 travel
        final Button btn_travel = findViewById(R.id.btn_travel);
        btn_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtTag ="旅遊";
                Intent intent = new Intent(DiaryTagActivity.this, DiaryWhoActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTagActivity.this);
                DiaryTagActivity.this.startActivity(intent,options.toBundle());
            }
        });
        // 前往下一頁 emotion
        final Button btn_emotion = findViewById(R.id.btn_emotion);
        btn_emotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtTag ="戀愛";
                Intent intent = new Intent(DiaryTagActivity.this, DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTagActivity.this);
                DiaryTagActivity.this.startActivity(intent,options.toBundle());
            }
        });
        // 前往下一頁 休閒娛樂
        final Button btn_play = findViewById(R.id.btn_play);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtTag ="休閒娛樂";
                Intent intent = new Intent(DiaryTagActivity.this, DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTagActivity.this);
                DiaryTagActivity.this.startActivity(intent,options.toBundle());
            }
        });
        // 前往 preview
        final TextView btn_preview = findViewById(R.id.btn_preview);
        btn_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtTag ="";
                DiaryValue.txtWhat = "";
                DiaryValue.txtWhy = "";
                DiaryValue.txtWhere = "";
                DiaryValue.txtWhen = "";
                DiaryValue.txtWho = "";
                for(int i = 0; i< 5; i++){
                    DiaryValue.txtHow_choose[i] = "";
                }
                Intent intent = new Intent();
                intent.setClass(DiaryTagActivity.this,DiaryPreviewActivity.class);
                Bundle tagData = new Bundle();
                tagData.putString("1","DiaryTagActivity");
                intent.putExtras(tagData);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTagActivity.this);
                startActivity(intent,options.toBundle());
            }
        });
    }


    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent intent = new Intent(DiaryTagActivity.this, DiaryMoodActivity.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTagActivity.this);
            DiaryTagActivity.this.startActivity(intent,options.toBundle());
        }
        return super.onKeyDown(keyCode, event);
    }
}
