package com.example.myapplication2.Diary.DiaryWhatFragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication2.Diary.DiaryWhyActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;


public class DiaryWhatFirstFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diary_what_first, container, false);

        // 前往下一頁 taiwan
        final Button btnTaiwan = root.findViewById(R.id.btn_taiwan);
        btnTaiwan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhat = "台式料理";
                if(DiaryValue.WhatLock){
                    DiaryValue.option = DiaryValue.txtWhat;
                    DiaryValue.WhatLock = false;
                }
                Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                startActivity(intent,options.toBundle());
            }
        });

        // 前往下一頁 Kong
        final Button btnKong = root.findViewById(R.id.btn_kong);
        btnKong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhat = "港式料理";
                if(DiaryValue.WhatLock){
                    DiaryValue.option = DiaryValue.txtWhat;
                    DiaryValue.WhatLock = false;
                }
                Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                startActivity(intent,options.toBundle());
            }
        });

        // 前往下一頁 japan
        final Button btnJapan = root.findViewById(R.id.btn_japan);
        btnJapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhat = "日式料理";
                if(DiaryValue.WhatLock){
                    DiaryValue.option = DiaryValue.txtWhat;
                    DiaryValue.WhatLock = false;
                }
                Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                startActivity(intent,options.toBundle());
            }
        });

        // 前往下一頁 korea
        final Button btnKorea = root.findViewById(R.id.btn_korea);
        btnKorea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhat = "韓式料理";
                if(DiaryValue.WhatLock){
                    DiaryValue.option = DiaryValue.txtWhat;
                    DiaryValue.WhatLock = false;
                }
                Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                startActivity(intent,options.toBundle());
            }
        });

        // 前往下一頁 random
        final Button btnRandom = root.findViewById(R.id.btn_random);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhat = "無菜單料理";
                if(DiaryValue.WhatLock){
                    DiaryValue.option = DiaryValue.txtWhat;
                    DiaryValue.WhatLock = false;
                }
                Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                startActivity(intent,options.toBundle());
            }
        });

        // 前往下一頁 Ider
        final Button btnIder = root.findViewById(R.id.btn_ider);
        btnIder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhat = "創意料理";
                if(DiaryValue.WhatLock){
                    DiaryValue.option = DiaryValue.txtWhat;
                    DiaryValue.WhatLock = false;
                }
                Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                startActivity(intent,options.toBundle());
            }
        });

        return root;
    }
}
