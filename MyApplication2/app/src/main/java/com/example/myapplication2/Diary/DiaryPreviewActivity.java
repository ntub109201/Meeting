package com.example.myapplication2.Diary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication2.Diary.DiaryHowPackage.DiaryHowActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;

import java.util.Calendar;


public class DiaryPreviewActivity extends AppCompatActivity{


    public static String total = "";
    public static String totalPlus = "";
    private String finalTotal;
    private TextView textView;
    private static String a ="";
    private static String b;
    private Button btn_SaveEdit;
    private ImageButton imBtnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_diarypreview);

        Bundle tag = getIntent().getExtras();
        final String tag1 = tag.getString("1");
        textView = findViewById(R.id.txtPreview);
        final String edit = getIntent().getStringExtra("Edit");

        // 返回主題頁
        final TextView mChangeTag = findViewById(R.id.btn_changeTag);
        mChangeTag.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(edit == null){
                    total += totalPlus;
                }else {
                    if(edit.equals(totalPlus)){
                        total = totalPlus;
                    }else{
                        total = edit;
                    }
                }
                // 很重要
                DiaryValue.howCount = 0;
                DiaryValue.Eye_Count = 0;
                DiaryValue.Mouth_Count = 0;
                DiaryValue.Smell_Count = 0;
                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryTagActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        // 回上一頁
        imBtnBack = findViewById(R.id.imbtnReturn);
        imBtnBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (tag1.equals("DiaryTagActivity")){
                    Intent intent = new Intent(DiaryPreviewActivity.this,DiaryTagActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent,options.toBundle());
                }else if(tag1.equals("DiaryWhatActivity")){
                    Intent intent = new Intent(DiaryPreviewActivity.this,DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent,options.toBundle());
                }else if(tag1.equals("DiaryWhyActivity")) {
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
                }else if(tag1.equals("DiaryWhereActivity")) {
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent,options.toBundle());
                }else if(tag1.equals("DiaryWhenActivity")) {
                    DiaryValue.Time = "";
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent,options.toBundle());
                }else if(tag1.equals("DiaryWhoActivity")) {
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent,options.toBundle());
                }else if(tag1.equals("DiaryHowActivity")) {
                    // 很重要
                    DiaryValue.howCount = 0;
                    DiaryValue.Eye_Count = 0;
                    DiaryValue.Mouth_Count = 0;
                    DiaryValue.Smell_Count = 0;
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryHowActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent,options.toBundle());
                }
            }
        });



        // total
        final Guidor guidor = new Guidor(getApplicationContext(),"diary.db",null,1);
        String a = "";
        if(DiaryValue.txtTag.equals("")){
            if(DiaryValue.txtWhat.equals("")){
                if(DiaryValue.txtWhy.equals("")){
                    if(DiaryValue.txtWhere.equals("")){
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWho(DiaryValue.txtWho);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }

                        }
                    }else {
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }
                }else {
                    if(DiaryValue.txtWhere.equals("")){
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhy(DiaryValue.txtWhy);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }

                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }

                        }
                    }else {
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            }else{
                if(DiaryValue.txtWhy.equals("")) {
                    if(DiaryValue.txtWhere.equals("")){
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }else {
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }
                }else{
                    if(DiaryValue.txtWhere.equals("")){
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }else {
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            }
        }else{
            if(DiaryValue.txtWhat.equals("")){
                if(DiaryValue.txtWhy.equals("")){
                    if(DiaryValue.txtWhere.equals("")){
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }else {
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }

                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }

                        }
                    }
                }else {
                    if(DiaryValue.txtWhere.equals("")){
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }else {
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            }else{
                if(DiaryValue.txtWhy.equals("")){
                    if(DiaryValue.txtWhere.equals("")){
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                        i++;
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                        i++;
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                        i++;
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                        i++;
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }else {
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                        i++;
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                        i++;
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                        i++;
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                        i++;
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }
                }else {
                    if(DiaryValue.txtWhere.equals("")){
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }else {
                        if(DiaryValue.txtWhen.equals("")){
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }else {
                            if(DiaryValue.txtWho.equals("")){
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for(int i = 0; i< DiaryValue.howCount; i++){
                                    if(DiaryValue.txtHow_choose[i].equals("視覺")){
                                        for(int j = 0; j < DiaryValue.Eye_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("味覺")){
                                        for(int j = 0; j < DiaryValue.Mouth_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    }else if(DiaryValue.txtHow_choose[i].equals("嗅覺")){
                                        for(int j = 0; j < DiaryValue.Smell_Count; j++){
                                            guidor.setHow(DiaryValue.txtHow_choose[i],DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            }
        }

        totalPlus = ("    "+ a+"\n");
        if(edit == null){
            finalTotal = total+ totalPlus;
            if(DiaryValue.Time.equals("")){
                Calendar c = Calendar.getInstance();
                textView.setText(finalTotal+"\n"+"撰寫時間"+c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH));
            }else {
                textView.setText(finalTotal+"\n"+"撰寫時間"+DiaryValue.Time);
            }
        }else{
            finalTotal = edit;
            if(DiaryValue.Time.equals("")){
                Calendar c = Calendar.getInstance();
                textView.setText(finalTotal+"\n"+"撰寫時間"+c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH));
            }else {
                textView.setText(finalTotal+"\n"+"撰寫時間"+DiaryValue.Time);
            }
        }



        // 結束
        final TextView mEnd = findViewById(R.id.btn_end);
        mEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTextView = textView.getText().toString();
                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryEndActivity.class);
                intent.putExtra("total",getTextView);
                intent.putExtra("1",tag1);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                startActivity(intent,options.toBundle());

            }
        });

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
