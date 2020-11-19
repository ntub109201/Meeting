package com.example.myapplication2.Diary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

import java.util.Calendar;
import java.util.Date;

public class DiaryTravelWhenActivity extends AppCompatActivity {


    private Button btn_startDate,btn_endDate;
    private ImageButton imBtnNext;
    private int pick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_travel_when);


        imBtnNext = findViewById(R.id.imBtnNext);
        imBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pick == 2){
                    DiaryValue.txtWhen = "多天";
                }else if(pick == 1){
                    DiaryValue.txtWhen = "一天";
                }
                Intent intent = new Intent(DiaryTravelWhenActivity.this, DiaryTravelWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTravelWhenActivity.this);
                DiaryTravelWhenActivity.this.startActivity(intent,options.toBundle());
            }
        });


        final TextView txtWhen_title_travel = findViewById(R.id.txtWhen_title_travel);
        int randomNum = (int)(Math.random()* 3 + 1);
        Diary_dictionary dict = new Diary_dictionary();
        String total = DiaryValue.txtMood+"_"+DiaryValue.txtTag+"_When_"+String.valueOf(randomNum);
        txtWhen_title_travel.setText(dict.dict.get(total));

        // 反回上一頁
        final ImageButton imbtnReturnFrontPage_travel = findViewById(R.id.imbtnReturnFrontPage_travel);
        imbtnReturnFrontPage_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryTravelWhenActivity.this, DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTravelWhenActivity.this);
                DiaryTravelWhenActivity.this.startActivity(intent,options.toBundle());
            }
        });

        // 跳題
        final TextView btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_skip.getText().toString().equals("跳題")){
                    DiaryValue.txtWhen = "";
                    DiaryValue.Time = "";
                    DiaryValue.EndTime = "";
                    Intent intent = new Intent(DiaryTravelWhenActivity.this, DiaryTravelWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTravelWhenActivity.this);
                    DiaryTravelWhenActivity.this.startActivity(intent,options.toBundle());
                }
            }
        });


        // 前往preview
        final TextView btn_preview_when_travel = findViewById(R.id.btn_preview_when_travel);
        btn_preview_when_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhen = "";
                DiaryValue.txtWhere = "";
                for(int i = 0; i< 5; i++){
                    DiaryValue.txtHow_choose[i] = "";
                }
                Intent intent = new Intent();
                intent.setClass(DiaryTravelWhenActivity.this,DiaryPreviewActivity.class);
                Bundle tagData = new Bundle();
                tagData.putString("1","DiaryTravelWhenActivity");
                intent.putExtras(tagData);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTravelWhenActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        btn_startDate = findViewById(R.id.btn_startDate);
        btn_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick = 1;
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(DiaryTravelWhenActivity.this,datePickerDlgOnDateSet,now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle("日記日期");
                datePickerDialog.setMessage("請選擇日期");
                datePickerDialog.setIcon(android.R.drawable.ic_dialog_info);
                datePickerDialog.setCancelable(false);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
                btn_skip.setText("下一題");
                btn_skip.setVisibility(View.INVISIBLE);
                imBtnNext.setVisibility(View.VISIBLE);
            }
        });

        btn_endDate = findViewById(R.id.btn_endDate);
        btn_endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick = 2;
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(DiaryTravelWhenActivity.this,datePickerDlgOnDateSet,now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle("日記日期");
                datePickerDialog.setMessage("請選擇日期");
                datePickerDialog.setIcon(android.R.drawable.ic_dialog_info);
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();
                btn_skip.setText("下一題");
                btn_skip.setVisibility(View.INVISIBLE);
                imBtnNext.setVisibility(View.VISIBLE);
            }
        });
    }
    private DatePickerDialog.OnDateSetListener datePickerDlgOnDateSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if(pick == 1){
                btn_startDate.setText(String.valueOf(year)+"/"+String.valueOf(month+1)+"/"+String.valueOf(dayOfMonth));
                DiaryValue.Time = (String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(dayOfMonth));
            }else if(pick == 2){
                btn_endDate.setText(String.valueOf(year)+"/"+String.valueOf(month+1)+"/"+String.valueOf(dayOfMonth));
                DiaryValue.EndTime = (String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(dayOfMonth));
            }
        }
    };

    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent intent = new Intent(DiaryTravelWhenActivity.this, DiaryWhyActivity.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTravelWhenActivity.this);
            DiaryTravelWhenActivity.this.startActivity(intent,options.toBundle());
        }
        return super.onKeyDown(keyCode, event);
    }
}
