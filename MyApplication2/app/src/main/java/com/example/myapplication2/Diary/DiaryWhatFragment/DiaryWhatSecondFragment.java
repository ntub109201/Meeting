package com.example.myapplication2.Diary.DiaryWhatFragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication2.Diary.DiaryWhenActivity;
import com.example.myapplication2.Diary.DiaryWhyActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;


public class DiaryWhatSecondFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diary_what_second, container, false);

        // 前往下一頁 italy
        final Button btnItaly = root.findViewById(R.id.btn_italy);

        btnItaly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWhat = "義式料理";
                    Intent intent = new Intent(DiaryWhatSecondFragment.super.getActivity(), DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatSecondFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWhat = "追劇";
                    Intent intent = new Intent(DiaryWhatSecondFragment.super.getActivity(), DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatSecondFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        // 前往下一頁 france
        final Button btnFrance = root.findViewById(R.id.btn_france);
        btnFrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhat = "法式料理";
                Intent intent = new Intent(DiaryWhatSecondFragment.super.getActivity(), DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatSecondFragment.super.getActivity());
                startActivity(intent,options.toBundle());
            }
        });

        // 前往下一頁 china
        final Button btnChina = root.findViewById(R.id.btn_china);
        btnChina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhat = "中式料理";
                Intent intent = new Intent(DiaryWhatSecondFragment.super.getActivity(), DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatSecondFragment.super.getActivity());
                startActivity(intent,options.toBundle());
            }
        });

        // 前往下一頁 usa
        final Button btnUsa = root.findViewById(R.id.btn_usa);
        btnUsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhat = "美式料理";
                Intent intent = new Intent(DiaryWhatSecondFragment.super.getActivity(), DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatSecondFragment.super.getActivity());
                startActivity(intent,options.toBundle());
            }
        });

        // 前往下一頁
        final Button btnAlcohol = root.findViewById(R.id.btn_alcohol);
        btnAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWhat = "酒類";
                    Intent intent = new Intent(DiaryWhatSecondFragment.super.getActivity(), DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatSecondFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWhat = "玩桌遊";
                    Intent intent = new Intent(DiaryWhatSecondFragment.super.getActivity(), DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatSecondFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        // 前往下一頁
        final Button btnDrinks = root.findViewById(R.id.btn_drinks);
        btnDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWhat = "飲料";
                    Intent intent = new Intent(DiaryWhatSecondFragment.super.getActivity(), DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatSecondFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWhat = "打電動";
                    Intent intent = new Intent(DiaryWhatSecondFragment.super.getActivity(), DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatSecondFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("休閒娛樂")){

            btnItaly.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable Italy = getResources().getDrawable(R.mipmap.ic_watchdrama_foreground);
            btnItaly.setCompoundDrawablesWithIntrinsicBounds(null,Italy,null,null);
            btnItaly.setY(-200);
            btnItaly.setX(-220);

            btnAlcohol.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable Alcohol = getResources().getDrawable(R.mipmap.ic_broadgame_foreground);
            btnAlcohol.setCompoundDrawablesWithIntrinsicBounds(null,Alcohol,null,null);
            btnAlcohol.setY(-70);

            btnDrinks.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable Drinks = getResources().getDrawable(R.mipmap.ic_videogame_foreground);
            btnDrinks.setCompoundDrawablesWithIntrinsicBounds(null,Drinks,null,null);
            btnDrinks.setY(-70);

            btnUsa.setVisibility(View.INVISIBLE);
            btnUsa.setEnabled(false);
            btnChina.setVisibility(View.INVISIBLE);
            btnChina.setEnabled(false);
            btnFrance.setVisibility(View.INVISIBLE);
            btnFrance.setEnabled(false);
        }


        return root;
    }
}
