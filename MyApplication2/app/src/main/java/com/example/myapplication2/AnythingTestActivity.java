package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication2.handwritepackage.hand_write_firstFragment;
import com.example.myapplication2.handwritepackage.hand_write_secondFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;

public class AnythingTestActivity extends AppCompatActivity {

    // 以下是滑動圖片
//    private Button btn_addphoto;
//    private ImageView imgPhoto;
//    private RecyclerView recyclerview;
//    private RecyclerView.Adapter mAdapter;
//    private LinearLayoutManager mLayoutManager;
//    private LinkedList<HashMap<String,String>> data1;
//    private AnythingTestActivity.MyAdapter myAdapter;
//    private int count;
//    private Bitmap mbmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anything_test);

        // 以下是滑動圖片
//        imgPhoto = findViewById(R.id.imgPhoto);
//        btn_addphoto = findViewById(R.id.btn_addphoto);
//        btn_addphoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 1);
//            }
//        });
//
//        recyclerview = findViewById(R.id.recyclerview);
//        recyclerview.setHasFixedSize(false);
//        mLayoutManager = new LinearLayoutManager(AnythingTestActivity.this);
//        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerview.setLayoutManager(mLayoutManager);
//        myAdapter = new MyAdapter();
//        recyclerview.setAdapter(myAdapter);
//        doData();

        // 以下是手寫日記
        InnerPagerAdapter pagerAdapter = new InnerPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    // 以下是滑動圖片
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            count +=1;
//            mbmp = (Bitmap) data.getExtras().get("data");
//            imgPhoto.setImageBitmap(mbmp);
//            doData();
//        }
//    }
//
//    private void doData(){
//        data1 = new LinkedList<>();
//        for(int i = 0; i < count; i++){
//            HashMap<String,String> row = new HashMap<>();
//            data1.add(row);
//        }
//    }
//
//    private class  MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//
//        class MyViewHolder extends RecyclerView.ViewHolder{
//            public View itemView;
//            public ImageView imgPhoto;
//            public MyViewHolder(View view){
//                super(view);
//                itemView = view;
//                imgPhoto = itemView.findViewById(R.id.imgPhoto);
//
//            }
//        }
//
//        @NonNull
//        @Override
//        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//            View itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.photo_item,parent,false);
//            MyViewHolder vh = new MyViewHolder(itemView);
//            return vh;
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
//            holder.imgPhoto.setImageBitmap(mbmp);
//        }
//
//        @Override
//        public int getItemCount() {
//            return data1.size();
//        }
//    }


    // 以下是手寫日記
    public class InnerPagerAdapter extends FragmentPagerAdapter {
        public InnerPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = new hand_write_firstFragment();
                    break;
                case 1:
                    fragment = new hand_write_secondFragment();
                    break;
            }
            return fragment;
        }
        @Override
        public int getCount(){
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position){

            String a = "";
            switch (position) {
                case 0:
                    a = "第一頁";
                    break;
                case 1:
                    a = "第二頁";
                    break;
                default:
                    a = null;
            }
            return a;
        }

    }

}
