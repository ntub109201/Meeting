package com.example.myapplication2.Diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication2.Diary.DiaryHowPackage.DiaryHowActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

import java.util.Calendar;
import java.util.Date;

public class DiaryWhenActivity extends AppCompatActivity {

    private TextView txtDate;
    private Button btnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_when);

        final TextView txtWhen_title = findViewById(R.id.txtWhen_title);
        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("購物")){
            txtWhen_title.setText("什麼時間去買的呢?");
        }else if(DiaryValue.txtTag.equals("休閒娛樂")){
            txtWhen_title.setText("什麼時候做這個事呢?");
        }else if(DiaryValue.txtTag.equals("戀愛")){
            txtWhen_title.setText("大概什麼時候呢?");
        }

        final ProgressBar progressBarWhen = findViewById(R.id.progressBarWhen);
        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("購物")){
            progressBarWhen.setProgress(20);
        }else if(DiaryValue.txtTag.equals("休閒娛樂")){
            progressBarWhen.setProgress(50);
        }else if(DiaryValue.txtTag.equals("戀愛")){
            progressBarWhen.setProgress(80);
        }

        // 反回上一頁
        final ImageButton imbtnReturnFrontPage6 = findViewById(R.id.imbtnReturnFrontPage6);
        imbtnReturnFrontPage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")) {
                    if(DiaryValue.txtWhat.equals("")){
                        Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhereActivity.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                        DiaryWhenActivity.this.startActivity(intent, options.toBundle());
                    }else {
                        Intent intent = new Intent(DiaryWhenActivity.this, DiaryHowActivity.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                        DiaryWhenActivity.this.startActivity(intent, options.toBundle());
                    }
                }else if(DiaryValue.txtTag.equals("購物")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryTagActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }
            }
        });

        // 前往preview
        final TextView mPreview = findViewById(R.id.btn_preview_when);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")) {
                    DiaryValue.txtWhen = "";
                    DiaryValue.txtWho = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhenActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhenActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWhat = "";
                    DiaryValue.txtWhy = "";
                    DiaryValue.txtWhere = "";
                    DiaryValue.txtWhen = "";
                    DiaryValue.txtWho = "";
                    for(int i = 0; i< 5; i++){
                        DiaryValue.txtHow_choose[i] = "";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhenActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhenActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWhere = "";
                    DiaryValue.txtWhen = "";
                    DiaryValue.txtWho = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhenActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhenActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWhen = "";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhenActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhenActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        //跳題
        final TextView btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhen = "";
                if(DiaryValue.txtTag.equals("美食")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhenActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhenActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        btnDate = findViewById(R.id.btnDate);
        txtDate = findViewById(R.id.txtDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(DiaryWhenActivity.this,datePickerDlgOnDateSet,now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle("日記日期");
                datePickerDialog.setMessage("請選擇日期");
                datePickerDialog.setIcon(android.R.drawable.ic_dialog_info);
                datePickerDialog.setCancelable(false);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });

        final Button btn_morning = findViewById(R.id.btn_morning);
        btn_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhen = "早上";
                if(DiaryValue.txtTag.equals("美食")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhenActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhenActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_noon = findViewById(R.id.btn_noon);
        btn_noon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhen = "中午";
                if(DiaryValue.txtTag.equals("美食")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhenActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhenActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_afternoon = findViewById(R.id.btn_afternoon);
        btn_afternoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhen = "下午";
                if(DiaryValue.txtTag.equals("美食")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhenActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhenActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_night = findViewById(R.id.btn_night);
        btn_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhen = "晚上";
                if(DiaryValue.txtTag.equals("美食")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhenActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhenActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_latenight = findViewById(R.id.btn_latenight);
        btn_latenight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhen = "深夜";
                if(DiaryValue.txtTag.equals("美食")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    Intent intent = new Intent(DiaryWhenActivity.this, DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    DiaryWhenActivity.this.startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhenActivity.this,DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhenActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhenActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        });

    }

    private DatePickerDialog.OnDateSetListener datePickerDlgOnDateSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            txtDate.setText(String.valueOf(year)+"/"+String.valueOf(month+1)+"/"+String.valueOf(dayOfMonth));
            DiaryValue.Time = txtDate.getText().toString();
            DiaryValue.EndTime = (String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(dayOfMonth));
        }
    };
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
