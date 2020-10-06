package com.example.myapplication2.ui.home;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class HomeContextActivity extends AppCompatActivity {

    private TextView txtHistoryDiary,textTitle,textDescription;
    private ImageView ContextImageView;
    private Button btnDelete,btnSave;
    private int Getdata;
    private ProgressBar proBarHomeContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_context);
        final ImageButton imbtnBackHome = findViewById(R.id.imbtnBackHome);
        imbtnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeContextActivity.this.finish();
            }
        });

        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        txtHistoryDiary = findViewById(R.id.txtHistoryDiary);
        textTitle = findViewById(R.id.textTitle);
        textDescription = findViewById(R.id.textDescription);
        ContextImageView = findViewById(R.id.ContextImageView);
        proBarHomeContext = findViewById(R.id.proBarHomeContext);


        Getdata = getIntent().getIntExtra("data",0);
        if(Getdata == 1){
            String total = "    "+sqlReturn.LoginContent[HomeFragment.homeTag];
            String mood = sqlReturn.LoginMood[HomeFragment.homeTag];
            String date = sqlReturn.LoginDate[HomeFragment.homeTag];
            if(mood.equals("晴天")){
                mood = "心情超棒的";
            }else if(mood.equals("時晴")){
                mood = "心情不錯歐";
            }else if(mood.equals("多雲")){
                mood = "心情普普呢";
            }else if(mood.equals("陣雨")){
                mood = "心情不好啊";
            }else if(mood.equals("雷雨")){
                mood = "心情很差呢";
            }
            txtHistoryDiary.setText(total);
            textTitle.setText(mood);
            textDescription.setText(date);
            if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("null")){
                ContextImageView.setImageResource(R.mipmap.ic_no_tag_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("1")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("2")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("4")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("5")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("6")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("7")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("8")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("9")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("10")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("11")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("12")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("13")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("42")){
                ContextImageView.setImageResource(R.drawable.handwrite);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("45")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("46")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("47")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("48")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("49")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("50")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("72")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("73")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("74")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("75")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("76")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("77")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("99")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("100")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("101")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("102")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("103")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("104")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("126")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("127")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("128")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("129")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("130")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("131")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("153")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("154")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("155")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("156")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("157")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("158")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("182")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("183")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("184")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("185")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("186")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("187")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("188")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("189")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("190")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("191")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("192")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("193")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("224")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("225")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("226")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("227")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("228")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("229")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("230")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("231")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("232")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("233")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("234")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("235")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("266")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("267")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("268")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("269")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("270")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("271")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("272")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("273")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("274")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("275")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("276")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("277")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("308")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("309")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("310")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("311")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("312")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("313")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("314")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("315")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("316")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("317")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("318")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("319")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("350")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("351")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("352")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("353")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("354")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("355")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("356")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("380")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("381")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("382")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("383")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("384")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("385")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("386")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("410")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("411")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("412")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("413")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("414")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("415")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("416")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("440")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("441")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("442")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("443")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("444")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("445")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("446")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("470")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("471")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("472")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("473")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("474")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("475")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("476")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("499")){
                ContextImageView.setImageResource(R.mipmap.ic_what_date_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("500")){
                ContextImageView.setImageResource(R.mipmap.ic_movielover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("501")){
                ContextImageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("502")){
                ContextImageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("503")){
                ContextImageView.setImageResource(R.mipmap.ic_singlover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("504")){
                ContextImageView.setImageResource(R.mipmap.ic_shoplover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("525")){
                ContextImageView.setImageResource(R.mipmap.ic_what_date_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("526")){
                ContextImageView.setImageResource(R.mipmap.ic_movielover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("527")){
                ContextImageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("528")){
                ContextImageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("529")){
                ContextImageView.setImageResource(R.mipmap.ic_singlover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("530")){
                ContextImageView.setImageResource(R.mipmap.ic_shoplover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("551")){
                ContextImageView.setImageResource(R.mipmap.ic_what_date_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("552")){
                ContextImageView.setImageResource(R.mipmap.ic_movielover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("553")){
                ContextImageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("554")){
                ContextImageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("555")){
                ContextImageView.setImageResource(R.mipmap.ic_singlover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("556")){
                ContextImageView.setImageResource(R.mipmap.ic_shoplover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("577")){
                ContextImageView.setImageResource(R.mipmap.ic_what_date_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("578")){
                ContextImageView.setImageResource(R.mipmap.ic_movielover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("579")){
                ContextImageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("580")){
                ContextImageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("581")){
                ContextImageView.setImageResource(R.mipmap.ic_singlover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("582")){
                ContextImageView.setImageResource(R.mipmap.ic_shoplover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("603")){
                ContextImageView.setImageResource(R.mipmap.ic_quarrellover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("604")){
                ContextImageView.setImageResource(R.mipmap.ic_coldwar_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("605")){
                ContextImageView.setImageResource(R.mipmap.ic_fighting_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("606")){
                ContextImageView.setImageResource(R.mipmap.ic_breakup_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("607")){
                ContextImageView.setImageResource(R.mipmap.ic_arguelover_foreground);
            }else if(sqlReturn.LoginOption[HomeFragment.homeTag].equals("608")){
                ContextImageView.setImageResource(R.mipmap.ic_complain_foreground);
            }
        }else if(Getdata == 2){
            String total = "    "+ sqlReturn.content1[HomeFragment.homeTag];
            String mood = sqlReturn.mood1[HomeFragment.homeTag];
            String date = sqlReturn.date1[HomeFragment.homeTag];
            if(mood.equals("晴天")){
                mood = "心情超棒的";
            }else if(mood.equals("時晴")){
                mood = "心情不錯歐";
            }else if(mood.equals("多雲")){
                mood = "心情普普呢";
            }else if(mood.equals("陣雨")){
                mood = "心情不好啊";
            }else if(mood.equals("雷雨")){
                mood = "心情很差呢";
            }
            txtHistoryDiary.setText(total);
            textTitle.setText(mood);
            textDescription.setText(date);
            if(sqlReturn.Option1[HomeFragment.homeTag].equals("null")){
                ContextImageView.setImageResource(R.mipmap.ic_no_tag_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("1")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("2")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("4")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("5")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("6")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("7")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("8")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("9")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("10")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("11")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("12")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("13")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("42")){
                ContextImageView.setImageResource(R.drawable.handwrite);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("45")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("46")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("47")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("48")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("49")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("50")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("72")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("73")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("74")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("75")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("76")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("77")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("99")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("100")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("101")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("102")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("103")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("104")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("126")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("127")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("128")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("129")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("130")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("131")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("153")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("154")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("155")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("156")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("157")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("158")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("182")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("183")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("184")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("185")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("186")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("187")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("188")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("189")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("190")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("191")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("192")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("193")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("224")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("225")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("226")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("227")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("228")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("229")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("230")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("231")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("232")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("233")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("234")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("235")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("266")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("267")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("268")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("269")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("270")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("271")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("272")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("273")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("274")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("275")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("276")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("277")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("308")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("309")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("310")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("311")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("312")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("313")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("314")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("315")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("316")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("317")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("318")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("319")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("350")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("351")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("352")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("353")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("354")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("355")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("356")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("380")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("381")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("382")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("383")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("384")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("385")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("386")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("410")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("411")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("412")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("413")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("414")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("415")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("416")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("440")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("441")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("442")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("443")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("444")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("445")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("446")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("470")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("471")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("472")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("473")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("474")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("475")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("476")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("499")){
                ContextImageView.setImageResource(R.mipmap.ic_what_date_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("500")){
                ContextImageView.setImageResource(R.mipmap.ic_movielover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("501")){
                ContextImageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("502")){
                ContextImageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("503")){
                ContextImageView.setImageResource(R.mipmap.ic_singlover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("504")){
                ContextImageView.setImageResource(R.mipmap.ic_shoplover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("525")){
                ContextImageView.setImageResource(R.mipmap.ic_what_date_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("526")){
                ContextImageView.setImageResource(R.mipmap.ic_movielover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("527")){
                ContextImageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("528")){
                ContextImageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("529")){
                ContextImageView.setImageResource(R.mipmap.ic_singlover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("530")){
                ContextImageView.setImageResource(R.mipmap.ic_shoplover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("551")){
                ContextImageView.setImageResource(R.mipmap.ic_what_date_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("552")){
                ContextImageView.setImageResource(R.mipmap.ic_movielover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("553")){
                ContextImageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("554")){
                ContextImageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("555")){
                ContextImageView.setImageResource(R.mipmap.ic_singlover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("556")){
                ContextImageView.setImageResource(R.mipmap.ic_shoplover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("577")){
                ContextImageView.setImageResource(R.mipmap.ic_what_date_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("578")){
                ContextImageView.setImageResource(R.mipmap.ic_movielover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("579")){
                ContextImageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("580")){
                ContextImageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("581")){
                ContextImageView.setImageResource(R.mipmap.ic_singlover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("582")){
                ContextImageView.setImageResource(R.mipmap.ic_shoplover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("603")){
                ContextImageView.setImageResource(R.mipmap.ic_quarrellover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("604")){
                ContextImageView.setImageResource(R.mipmap.ic_coldwar_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("605")){
                ContextImageView.setImageResource(R.mipmap.ic_fighting_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("606")){
                ContextImageView.setImageResource(R.mipmap.ic_breakup_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("607")){
                ContextImageView.setImageResource(R.mipmap.ic_arguelover_foreground);
            }else if(sqlReturn.Option1[HomeFragment.homeTag].equals("608")){
                ContextImageView.setImageResource(R.mipmap.ic_complain_foreground);
            }
        }else if(Getdata == 3){
            String total = "    "+ sqlReturn.content2[HomeFragment.homeTag];
            String mood = sqlReturn.mood2[HomeFragment.homeTag];
            String date = sqlReturn.date2[HomeFragment.homeTag];
            if(mood.equals("心情1")){
                mood = "心情超棒的";
            }else if(mood.equals("心情2")){
                mood = "心情不錯歐";
            }else if(mood.equals("心情3")){
                mood = "心情普普呢";
            }else if(mood.equals("心情4")){
                mood = "心情不好啊";
            }else if(mood.equals("心情5")){
                mood = "心情很差呢";
            }
            txtHistoryDiary.setText(total);
            textTitle.setText(mood);
            textDescription.setText(date);
            if(sqlReturn.Option2[HomeFragment.homeTag].equals("null")){
                ContextImageView.setImageResource(R.mipmap.ic_no_tag_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("1")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("2")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("4")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("5")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("6")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("7")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("8")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("9")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("10")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("11")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("12")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("13")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("42")){
                ContextImageView.setImageResource(R.drawable.handwrite);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("45")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("46")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("47")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("48")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("49")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("50")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("72")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("73")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("74")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("75")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("76")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("77")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("99")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("100")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("101")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("102")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("103")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("104")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("126")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("127")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("128")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("129")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("130")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("131")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("153")){
                ContextImageView.setImageResource(R.mipmap.ic_daily_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("154")){
                ContextImageView.setImageResource(R.mipmap.ic_buy_food_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("155")){
                ContextImageView.setImageResource(R.mipmap.ic_3c_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("156")){
                ContextImageView.setImageResource(R.mipmap.ic_makeup_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("157")){
                ContextImageView.setImageResource(R.mipmap.ic_car_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("158")){
                ContextImageView.setImageResource(R.mipmap.ic_clothes_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("182")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("183")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("184")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("185")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("186")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("187")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("188")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("189")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("190")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("191")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("192")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("193")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("224")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("225")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("226")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("227")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("228")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("229")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("230")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("231")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("232")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("233")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("234")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("235")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("266")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("267")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("268")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("269")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("270")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("271")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("272")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("273")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("274")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("275")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("276")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("277")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("308")){
                ContextImageView.setImageResource(R.mipmap.ic_japan_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("309")){
                ContextImageView.setImageResource(R.mipmap.ic_korea_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("310")){
                ContextImageView.setImageResource(R.mipmap.ic_taiwan_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("311")){
                ContextImageView.setImageResource(R.mipmap.ic_thailand_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("312")){
                ContextImageView.setImageResource(R.mipmap.ic_italy_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("313")){
                ContextImageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("314")){
                ContextImageView.setImageResource(R.mipmap.ic_china_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("315")){
                ContextImageView.setImageResource(R.mipmap.ic_hongkong_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("316")){
                ContextImageView.setImageResource(R.mipmap.ic_otherfood_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("317")){
                ContextImageView.setImageResource(R.mipmap.ic_random_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("318")){
                ContextImageView.setImageResource(R.mipmap.ic_drinks_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("319")){
                ContextImageView.setImageResource(R.mipmap.ic_alcohol_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("350")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("351")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("352")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("353")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("354")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("355")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("356")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("380")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("381")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("382")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("383")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("384")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("385")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("386")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("410")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("411")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("412")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("413")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("414")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("415")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("416")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("440")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("441")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("442")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("443")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("444")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("445")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("446")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("470")){
                ContextImageView.setImageResource(R.mipmap.ic_movie_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("471")){
                ContextImageView.setImageResource(R.mipmap.ic_workout_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("472")){
                ContextImageView.setImageResource(R.mipmap.ic_listen_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("473")){
                ContextImageView.setImageResource(R.mipmap.ic_sing_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("474")){
                ContextImageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("475")){
                ContextImageView.setImageResource(R.mipmap.ic_broadgame_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("476")){
                ContextImageView.setImageResource(R.mipmap.ic_videogame_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("499")){
                ContextImageView.setImageResource(R.mipmap.ic_what_date_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("500")){
                ContextImageView.setImageResource(R.mipmap.ic_movielover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("501")){
                ContextImageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("502")){
                ContextImageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("503")){
                ContextImageView.setImageResource(R.mipmap.ic_singlover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("504")){
                ContextImageView.setImageResource(R.mipmap.ic_shoplover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("525")){
                ContextImageView.setImageResource(R.mipmap.ic_what_date_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("526")){
                ContextImageView.setImageResource(R.mipmap.ic_movielover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("527")){
                ContextImageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("528")){
                ContextImageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("529")){
                ContextImageView.setImageResource(R.mipmap.ic_singlover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("530")){
                ContextImageView.setImageResource(R.mipmap.ic_shoplover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("551")){
                ContextImageView.setImageResource(R.mipmap.ic_what_date_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("552")){
                ContextImageView.setImageResource(R.mipmap.ic_movielover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("553")){
                ContextImageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("554")){
                ContextImageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("555")){
                ContextImageView.setImageResource(R.mipmap.ic_singlover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("556")){
                ContextImageView.setImageResource(R.mipmap.ic_shoplover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("577")){
                ContextImageView.setImageResource(R.mipmap.ic_what_date_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("578")){
                ContextImageView.setImageResource(R.mipmap.ic_movielover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("579")){
                ContextImageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("580")){
                ContextImageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("581")){
                ContextImageView.setImageResource(R.mipmap.ic_singlover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("582")){
                ContextImageView.setImageResource(R.mipmap.ic_shoplover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("603")){
                ContextImageView.setImageResource(R.mipmap.ic_quarrellover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("604")){
                ContextImageView.setImageResource(R.mipmap.ic_coldwar_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("605")){
                ContextImageView.setImageResource(R.mipmap.ic_fighting_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("606")){
                ContextImageView.setImageResource(R.mipmap.ic_breakup_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("607")){
                ContextImageView.setImageResource(R.mipmap.ic_arguelover_foreground);
            }else if(sqlReturn.Option2[HomeFragment.homeTag].equals("608")){
                ContextImageView.setImageResource(R.mipmap.ic_complain_foreground);
            }
        }else if(Getdata == 4){
            String total = "    "+ sqlReturn.content3[HomeFragment.homeTag];
            String mood = sqlReturn.mood3[HomeFragment.homeTag];
            String date = sqlReturn.date3[HomeFragment.homeTag];
            txtHistoryDiary.setText(total);
            textTitle.setText(mood);
            textDescription.setText(date);
            ContextImageView.setImageResource(R.drawable.handwrite);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(HomeContextActivity.this)
                        .setTitle("提醒")
                        .setMessage("請確認要儲存本篇日記!!")
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                proBarHomeContext.setVisibility(View.VISIBLE);
                                EditDiary();
                            }
                        }).setNegativeButton("cancel",null).create().show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(HomeContextActivity.this)
                        .setTitle("警告")
                        .setMessage("您即將要刪除本篇日記!!")
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                proBarHomeContext.setVisibility(View.VISIBLE);
                                DeleteDiary();
                            }
                        }).setNegativeButton("cancel",null).create().show();
            }
        });

    }

    public void EditDiary(){
        if(Getdata == 1){
            Map<String,String> map = new HashMap<>();
            map.put("command", "editDiary");
            map.put("diaryNo",sqlReturn.LoginDiaryID[HomeFragment.homeTag]);
            map.put("diaryNewContent",txtHistoryDiary.getText().toString());
            new EditDiary(this).execute((HashMap)map);
        }else if(Getdata == 2){
            Map<String,String> map = new HashMap<>();
            map.put("command", "editDiary");
            map.put("diaryNo",sqlReturn.DiaryID1[HomeFragment.homeTag]);
            map.put("diaryNewContent",txtHistoryDiary.getText().toString());
            new EditDiary(this).execute((HashMap)map);
        }else if(Getdata == 3){
            Map<String,String> map = new HashMap<>();
            map.put("command", "editDiary");
            map.put("diaryNo",sqlReturn.DiaryID2[HomeFragment.homeTag]);
            map.put("diaryNewContent",txtHistoryDiary.getText().toString());
            new EditDiary(this).execute((HashMap)map);
        }else if(Getdata == 4){
            Map<String,String> map = new HashMap<>();
            map.put("command", "editDiary");
            map.put("diaryNo",sqlReturn.DiaryID3[HomeFragment.homeTag]);
            map.put("diaryNewContent",txtHistoryDiary.getText().toString());
            new EditDiary(this).execute((HashMap)map);
        }
    }
    private class EditDiary extends HttpURLConnection_AsyncTask {
        WeakReference<Activity> activityReference;
        EditDiary(Activity context){ activityReference = new WeakReference<>(context); }
        @Override
        protected void onPostExecute(String result){
            JSONObject jsonObject = null;
            boolean status = false;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;
            try {
                jsonObject = new JSONObject(result);
                status = jsonObject.getBoolean("status");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                proBarHomeContext.setVisibility(View.INVISIBLE);
                Toast.makeText(activity, "修改成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeContextActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",1);
                startActivity(intent);
            }else {
                proBarHomeContext.setVisibility(View.INVISIBLE);
                new AlertDialog.Builder(activity)
                        .setTitle("日記修改失敗")
                        .setMessage("請確認網路是否連通!!")
                        .setPositiveButton("OK", null)
                        .show();
            }
        }
    }

    public void DeleteDiary(){

        if(Getdata == 1){
            Map<String,String> map = new HashMap<>();
            map.put("command", "deleteDiary");
            map.put("diaryNo",sqlReturn.LoginDiaryID[HomeFragment.homeTag]);
            new DeleteDiary(this).execute((HashMap)map);
        }else if(Getdata == 2){
            Map<String,String> map = new HashMap<>();
            map.put("command", "deleteDiary");
            map.put("diaryNo",sqlReturn.DiaryID1[HomeFragment.homeTag]);
            new DeleteDiary(this).execute((HashMap)map);
        }else if(Getdata == 3){
            Map<String,String> map = new HashMap<>();
            map.put("command", "deleteDiary");
            map.put("diaryNo",sqlReturn.DiaryID2[HomeFragment.homeTag]);
            new DeleteDiary(this).execute((HashMap)map);
        }else if(Getdata == 4){
            Map<String,String> map = new HashMap<>();
            map.put("command", "deleteDiary");
            map.put("diaryNo",sqlReturn.DiaryID3[HomeFragment.homeTag]);
            new DeleteDiary(this).execute((HashMap)map);
        }

    }
    private class DeleteDiary extends HttpURLConnection_AsyncTask {
        // 建立弱連結
        WeakReference<Activity> activityReference;
        DeleteDiary(Activity context){
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonObject = null;
            boolean status = false;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            try {
                jsonObject = new JSONObject(result);
                status = jsonObject.getBoolean("status");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                proBarHomeContext.setVisibility(View.INVISIBLE);
                Toast.makeText(activity, "刪除成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeContextActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",1);
                startActivity(intent);
            }else {
                new AlertDialog.Builder(activity)
                        .setTitle("日記刪除失敗")
                        .setMessage("請確認網路是否連通!!")
                        .setPositiveButton("OK", null)
                        .show();
                proBarHomeContext.setVisibility(View.INVISIBLE);
            }
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
