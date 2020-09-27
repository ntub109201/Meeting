package com.example.myapplication2.Diary.DiaryWhoFragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication2.Diary.DiaryPreviewActivity;
import com.example.myapplication2.Diary.DiaryWhyActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;


public class DiaryWhoThirdFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_diary_who_third, container, false);

        final Button btn_colleague = root.findViewById(R.id.btn_colleague);
        btn_colleague.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWho = "同事";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoThirdFragment.super.getActivity(), DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoThirdFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    DiaryValue.txtWho = "同事";
                    Intent intent = new Intent(DiaryWhoThirdFragment.super.getActivity(), DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoThirdFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWho = "同事";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoThirdFragment.super.getActivity(), DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoThirdFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        final Button btn_ambiguous = root.findViewById(R.id.btn_ambiguous);
        final ImageView imageView9 = root.findViewById(R.id.imageView9);

        btn_ambiguous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    DiaryValue.txtWho = "曖昧對象";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoThirdFragment.super.getActivity(),DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoThirdFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWho = "同學";
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhoThirdFragment.super.getActivity(),DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1","DiaryWhoActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhoThirdFragment.super.getActivity());
                    startActivity(intent,options.toBundle());
                }
            }
        });

        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("購物")){
            btn_ambiguous.setVisibility(View.INVISIBLE);
            btn_ambiguous.setEnabled(false);
            imageView9.setVisibility(View.INVISIBLE);
        }else if(DiaryValue.txtTag.equals("休閒娛樂")){
            btn_ambiguous.setBackgroundResource(R.mipmap.btn_student_foreground);
            btn_ambiguous.setPadding(0,30,0,0);
        }

        return root;
    }
}
