package com.example.myapplication2.ui.dashboard;

import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication2.MainActivity;
import com.example.myapplication2.ModifyPersonalActivity;
import com.example.myapplication2.PersonalActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.ui.home.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import static com.example.myapplication2.R.layout.fragment_dashboard;

public class DashboardFragment extends Fragment {

    private ImageButton imBtnPersonal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(fragment_dashboard, container, false);

        if(HomeFragment.changeBtn == true){
            HomeFragment.changeBtn = false;
        }

        imBtnPersonal = root.findViewById(R.id.imBtnPersonal);
        imBtnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardFragment.super.getActivity(), PersonalActivity.class);
                intent.putExtra("pageId",2);
                startActivity(intent);
            }
        });

        return root;
    }
}
