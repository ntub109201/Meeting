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

import com.example.myapplication2.Diary.DiaryPreviewActivity;
import com.example.myapplication2.Diary.DiaryWhenActivity;
import com.example.myapplication2.Diary.DiaryWhyActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;


public class DiaryWhatFirstFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diary_what_first, container, false);


        // 前往下一頁 taiwan
        final Button btn_taiwan = root.findViewById(R.id.btn_taiwan);
        btn_taiwan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWhat = "台式料理";
                    Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWhat = "生活用品";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhatFirstFragment.super.getActivity(), DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhatActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWhat = "看電影";
                    Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        // 前往下一頁 Kong
        final Button btn_kong = root.findViewById(R.id.btn_kong);
        btn_kong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWhat = "港式料理";
                    Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWhat = "食物及食品";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhatFirstFragment.super.getActivity(), DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhatActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWhat = "運動";
                    Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        // 前往下一頁 japan
        final Button btn_japan = root.findViewById(R.id.btn_japan);
        btn_japan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWhat = "日式料理";
                    Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWhat = "家電及3C產品";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhatFirstFragment.super.getActivity(), DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhatActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWhat = "聽歌";
                    Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        // 前往下一頁 korea
        final Button btn_korea = root.findViewById(R.id.btn_korea);
        btn_korea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWhat = "韓式料理";
                    Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWhat = "美妝護理品";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhatFirstFragment.super.getActivity(), DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhatActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWhat = "唱歌";
                    Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        // 前往下一頁 random
        final Button btn_random = root.findViewById(R.id.btn_random);
        btn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWhat = "無菜單料理";
                    Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWhat = "車用品";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhatFirstFragment.super.getActivity(), DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhatActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        // 前往下一頁 Ider
        final Button btn_ider = root.findViewById(R.id.btn_ider);
        btn_ider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWhat = "創意料理";
                    Intent intent = new Intent(DiaryWhatFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWhat = "服飾鞋包";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhatFirstFragment.super.getActivity(), DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhatActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhatFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("購物")){

            btn_taiwan.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable taiwan = getResources().getDrawable(R.mipmap.ic_daily_foreground);
            btn_taiwan.setCompoundDrawablesWithIntrinsicBounds(null,taiwan,null,null);

            btn_kong.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable kong = getResources().getDrawable(R.mipmap.ic_buy_food_foreground);
            btn_kong.setCompoundDrawablesWithIntrinsicBounds(null,kong,null,null);

            btn_japan.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable japan = getResources().getDrawable(R.mipmap.ic_3c_foreground);
            btn_japan.setCompoundDrawablesWithIntrinsicBounds(null,japan,null,null);

            btn_korea.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable korea = getResources().getDrawable(R.mipmap.ic_makeup_foreground);
            btn_korea.setCompoundDrawablesWithIntrinsicBounds(null,korea,null,null);

            btn_random.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable random = getResources().getDrawable(R.mipmap.ic_car_foreground);
            btn_random.setCompoundDrawablesWithIntrinsicBounds(null,random,null,null);

            btn_ider.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable ider = getResources().getDrawable(R.mipmap.ic_clothes_foreground);
            btn_ider.setCompoundDrawablesWithIntrinsicBounds(null,ider,null,null);
        }else if(DiaryValue.txtTag.equals("休閒娛樂")){

            btn_taiwan.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable taiwan = getResources().getDrawable(R.mipmap.ic_movie_foreground);
            btn_taiwan.setCompoundDrawablesWithIntrinsicBounds(null,taiwan,null,null);
            btn_taiwan.setY(150);

            btn_kong.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable kong = getResources().getDrawable(R.mipmap.ic_workout_foreground);
            btn_kong.setCompoundDrawablesWithIntrinsicBounds(null,kong,null,null);
            btn_kong.setY(150);

            btn_japan.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable japan = getResources().getDrawable(R.mipmap.ic_listen_foreground);
            btn_japan.setCompoundDrawablesWithIntrinsicBounds(null,japan,null,null);
            btn_japan.setY(300);

            btn_korea.setBackgroundResource(R.drawable.btn_selectfood);
            Drawable korea = getResources().getDrawable(R.mipmap.ic_sing_foreground);
            btn_korea.setCompoundDrawablesWithIntrinsicBounds(null,korea,null,null);
            btn_korea.setY(300);

            btn_random.setVisibility(View.INVISIBLE);
            btn_random.setEnabled(false);
            btn_ider.setVisibility(View.INVISIBLE);
            btn_ider.setEnabled(false);
        }

        return root;
    }
}
