package com.example.myapplication2.Diary.DiaryWhoFragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication2.Diary.DiaryPreviewActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

public class DiaryWhoSecondFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_diary_who_second, container, false);

        final Button btn_lover = root.findViewById(R.id.btn_lover);
        btn_lover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWho = "情人";
                Intent intent = new Intent();
                intent.setClass(DiaryWhoSecondFragment.super.getActivity(), DiaryPreviewActivity.class);
                Bundle tagData = new Bundle();
                tagData.putString("1","DiaryWhoActivity");
                intent.putExtras(tagData);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoSecondFragment.super.getActivity());
                startActivity(intent,options.toBundle());
            }
        });

        final Button btn_self = root.findViewById(R.id.btn_self);
        btn_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWho = "自己";
                Intent intent = new Intent();
                intent.setClass(DiaryWhoSecondFragment.super.getActivity(),DiaryPreviewActivity.class);
                Bundle tagData = new Bundle();
                tagData.putString("1","DiaryWhoActivity");
                intent.putExtras(tagData);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoSecondFragment.super.getActivity());
                startActivity(intent,options.toBundle());
            }
        });

        return root;
    }
}
