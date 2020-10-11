package com.example.myapplication2.Diary.DiaryWhoFragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication2.Diary.DiaryPreviewActivity;
import com.example.myapplication2.Diary.DiaryWhatActivity;
import com.example.myapplication2.Diary.DiaryWhenActivity;
import com.example.myapplication2.Diary.DiaryWhyActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;


public class DiaryWhoFirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_diary_who_first, container, false);

        final Button btn_family = root.findViewById(R.id.btn_family);
        btn_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWho = "家人";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoFirstFragment.super.getActivity(),DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWho = "家人";
                    Intent intent = new Intent(DiaryWhoFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWho = "家人";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoFirstFragment.super.getActivity(),DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    DiaryValue.txtWho = "家人";
                    Intent intent = new Intent(DiaryWhoFirstFragment.super.getActivity(), DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("旅遊")){
                    DiaryValue.txtWho = "自由行";
                    Intent intent = new Intent(DiaryWhoFirstFragment.super.getActivity(), DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_friend = root.findViewById(R.id.btn_friend);
        btn_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWho = "朋友";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoFirstFragment.super.getActivity(),DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWho = "朋友";
                    Intent intent = new Intent(DiaryWhoFirstFragment.super.getActivity(), DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWho = "朋友";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoFirstFragment.super.getActivity(),DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    DiaryValue.txtWho = "朋友";
                    Intent intent = new Intent(DiaryWhoFirstFragment.super.getActivity(), DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("旅遊")){
                    DiaryValue.txtWho = "家族旅遊";
                    Intent intent = new Intent(DiaryWhoFirstFragment.super.getActivity(), DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoFirstFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        if(DiaryValue.txtTag.equals("旅遊")){
            //btn_friend.setBackgroundResource(R.drawable.btn_selectfood);
            btn_friend.setBackgroundResource(R.drawable.button_shape);
            btn_friend.setBackgroundColor(Color.parseColor("#C2D2DA"));
            btn_friend.setText("自由行");
            btn_friend.setTextColor(Color.parseColor("#FFFFFF"));

            btn_family.setBackgroundResource(R.drawable.button_shape);
            btn_family.setBackgroundColor(Color.parseColor("#C2D2DA"));
            btn_family.setText("家族旅遊");
            btn_family.setTextColor(Color.parseColor("#FFFFFF"));
        }

        return root;
    }
}
