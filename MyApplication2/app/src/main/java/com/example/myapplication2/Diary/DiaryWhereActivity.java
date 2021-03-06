package com.example.myapplication2.Diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication2.Diary.DiaryHowPackage.DiaryHowActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

import java.util.HashMap;
import java.util.LinkedList;

public class DiaryWhereActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DiaryWhereActivity.MyAdapter myAdapter;
    private LinkedList<HashMap<String, String>> data;
    public static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_where);


        final TextView txtWhere_title = findViewById(R.id.txtWhere_title);
        int randomNum = (int)(Math.random()* 3 + 1);
        Diary_dictionary dict = new Diary_dictionary();
        String total = DiaryValue.txtMood+"_"+DiaryValue.txtTag+"_Where_"+String.valueOf(randomNum);
        txtWhere_title.setText(dict.dict.get(total));

        final ProgressBar progressBarWhere = findViewById(R.id.progressBarWhere);
        if(DiaryValue.txtTag.equals("美食")){

        }else if(DiaryValue.txtTag.equals("購物")){
            progressBarWhere.setProgress(80);
        }else if(DiaryValue.txtTag.equals("休閒娛樂")){
            progressBarWhere.setProgress(65);
        }else if(DiaryValue.txtTag.equals("戀愛")){
            progressBarWhere.setProgress(20);
        }

        // 反回上一頁
        final ImageButton imbtnReturnFrontPage4 = findViewById(R.id.imbtnReturnFrontPage4);
        imbtnReturnFrontPage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DiaryValue.txtTag.equals("美食")){
                    Intent intent = new Intent(DiaryWhereActivity.this, DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                    DiaryWhereActivity.this.startActivity(intent, options.toBundle());
                }else if(DiaryValue.txtTag.equals("購物")){
                    Intent intent = new Intent(DiaryWhereActivity.this, DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                    DiaryWhereActivity.this.startActivity(intent, options.toBundle());
                }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    Intent intent = new Intent(DiaryWhereActivity.this, DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                    DiaryWhereActivity.this.startActivity(intent, options.toBundle());
                }else if(DiaryValue.txtTag.equals("戀愛")){
                    Intent intent = new Intent(DiaryWhereActivity.this, DiaryTagActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                    DiaryWhereActivity.this.startActivity(intent, options.toBundle());
                }
            }
        });

        // adapter
        mRecyclerView = findViewById(R.id.WhereRecyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(DiaryWhereActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myAdapter = new MyAdapter();
        mRecyclerView.setAdapter(myAdapter);

        doData();

        // 前往preview
        final TextView mPreview = findViewById(R.id.btn_preview_where);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiaryValue.txtTag.equals("美食")) {
                    DiaryValue.txtWhere = "";
                    DiaryValue.txtWhen = "";
                    DiaryValue.txtWho = "";
                    for (int i = 0; i < 5; i++) {
                        DiaryValue.txtHow_choose[i] = "";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhereActivity.this, DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1", "DiaryWhereActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                    startActivity(intent, options.toBundle());
                } else if (DiaryValue.txtTag.equals("購物")) {
                    DiaryValue.txtWhat = "";
                    DiaryValue.txtWhere = "";
                    for (int i = 0; i < 5; i++) {
                        DiaryValue.txtHow_choose[i] = "";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhereActivity.this, DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1", "DiaryWhereActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                    startActivity(intent, options.toBundle());
                } else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    DiaryValue.txtWho = "";
                    DiaryValue.txtWhere = "";
                    for (int i = 0; i < 5; i++) {
                        DiaryValue.txtHow_choose[i] = "";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhereActivity.this, DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1", "DiaryWhereActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                    startActivity(intent, options.toBundle());
                } else if(DiaryValue.txtTag.equals("戀愛")){
                    DiaryValue.txtWhat = "";
                    DiaryValue.txtWhy = "";
                    DiaryValue.txtWhere = "";
                    DiaryValue.txtWhen = "";
                    DiaryValue.txtWho = "";
                    for (int i = 0; i < 5; i++) {
                        DiaryValue.txtHow_choose[i] = "";
                    }
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhereActivity.this, DiaryPreviewActivity.class);
                    Bundle tagData = new Bundle();
                    tagData.putString("1", "DiaryWhereActivity");
                    intent.putExtras(tagData);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                    startActivity(intent, options.toBundle());
                }
            }
        });

        //跳題
        final TextView btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhere = "";
                if (DiaryValue.txtTag.equals("美食")) {
                    if(DiaryValue.txtWhat.equals("")){
                        Intent intent = new Intent();
                        intent.setClass(DiaryWhereActivity.this, DiaryWhenActivity.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                        startActivity(intent, options.toBundle());
                    }else {
                        Intent intent = new Intent();
                        intent.setClass(DiaryWhereActivity.this, DiaryHowActivity.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                        startActivity(intent, options.toBundle());
                    }
                } else if (DiaryValue.txtTag.equals("購物")) {
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                    startActivity(intent, options.toBundle());
                } else if(DiaryValue.txtTag.equals("休閒娛樂")){
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhereActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                    startActivity(intent, options.toBundle());
                } else if(DiaryValue.txtTag.equals("戀愛")){
                    Intent intent = new Intent();
                    intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                    startActivity(intent, options.toBundle());
                }
            }
        });
    }


    public void doData() {
        data = new LinkedList<>();
        if (DiaryValue.txtTag.equals("美食")) {
            for (int i = 0; i < 9; i++) {
                HashMap<String, String> row = new HashMap<>();
                data.add(row);
            }
        } else if (DiaryValue.txtTag.equals("購物")) {
            for (int i = 0; i < 5; i++) {
                HashMap<String, String> row = new HashMap<>();
                data.add(row);
            }
        } else if (DiaryValue.txtTag.equals("休閒娛樂")) {
            for (int i = 0; i < 7; i++) {
                HashMap<String, String> row = new HashMap<>();
                data.add(row);
            }
        } else if (DiaryValue.txtTag.equals("戀愛")) {
            for (int i = 0; i < 7; i++) {
                HashMap<String, String> row = new HashMap<>();
                data.add(row);
            }
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder {
            public View itemView;
            public ImageView imageView;

            public MyViewHolder(View view) {
                super(view);
                itemView = view;
                imageView = itemView.findViewById(R.id.imageView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        position = getAdapterPosition();
                        if (DiaryValue.txtTag.equals("美食")) {
                            if (position == 0) {
                                DiaryValue.txtWhere = "夜市";
                                if(DiaryValue.txtWhat.equals("")){
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryWhenActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }else {
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryHowActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }
                            } else if (position == 1) {
                                DiaryValue.txtWhere = "咖啡廳";
                                if(DiaryValue.txtWhat.equals("")){
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryWhenActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }else {
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryHowActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }
                            } else if (position == 2) {
                                DiaryValue.txtWhere = "速食店";
                                if(DiaryValue.txtWhat.equals("")){
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryWhenActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }else {
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryHowActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }
                            } else if (position == 3) {
                                DiaryValue.txtWhere = "居酒屋";
                                if(DiaryValue.txtWhat.equals("")){
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryWhenActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }else {
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryHowActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }
                            } else if (position == 4) {
                                DiaryValue.txtWhere = "辦公室";
                                if(DiaryValue.txtWhat.equals("")){
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryWhenActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }else {
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryHowActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }
                            } else if (position == 5) {
                                DiaryValue.txtWhere = "餐廳";
                                if(DiaryValue.txtWhat.equals("")){
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryWhenActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }else {
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryHowActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }
                            } else if (position == 6) {
                                DiaryValue.txtWhere = "路邊攤";
                                if(DiaryValue.txtWhat.equals("")){
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryWhenActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }else {
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryHowActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }
                            } else if (position == 7) {
                                DiaryValue.txtWhere = "學校";
                                if(DiaryValue.txtWhat.equals("")){
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryWhenActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }else {
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryHowActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }
                            } else if (position == 8) {
                                DiaryValue.txtWhere = "小吃店";
                                if(DiaryValue.txtWhat.equals("")){
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryWhenActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }else {
                                    Intent intent = new Intent();
                                    intent.setClass(DiaryWhereActivity.this, DiaryHowActivity.class);
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                    startActivity(intent, options.toBundle());
                                }
                            }
                        } else if (DiaryValue.txtTag.equals("購物")) {
                            if (position == 0) {
                                DiaryValue.txtWhere = "大賣場";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 1) {
                                DiaryValue.txtWhere = "百貨公司";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 2) {
                                DiaryValue.txtWhere = "傳統市場";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 3) {
                                DiaryValue.txtWhere = "商圈";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 4) {
                                DiaryValue.txtWhere = "線上購物";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            }
                        } else if(DiaryValue.txtTag.equals("休閒娛樂")){
                            if (position == 0) {
                                DiaryValue.txtWhere = "電影院";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhoActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 1) {
                                DiaryValue.txtWhere = "健身房";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhoActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 2) {
                                DiaryValue.txtWhere = "家";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhoActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 3) {
                                DiaryValue.txtWhere = "桌遊店";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhoActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 4) {
                                DiaryValue.txtWhere = "KTV";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhoActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 5) {
                                DiaryValue.txtWhere = "公園";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhoActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 6) {
                                DiaryValue.txtWhere = "交通工具";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhoActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            }
                        }else if(DiaryValue.txtTag.equals("戀愛")){
                            if (position == 0) {
                                DiaryValue.txtWhere = "家裡";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 1) {
                                DiaryValue.txtWhere = "公園";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 2) {
                                DiaryValue.txtWhere = "餐廳";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 3) {
                                DiaryValue.txtWhere = "賣場(超市)";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 4) {
                                DiaryValue.txtWhere = "百貨公司";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 5) {
                                DiaryValue.txtWhere = "商圈";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            } else if (position == 6) {
                                DiaryValue.txtWhere = "夜市";
                                Intent intent = new Intent();
                                intent.setClass(DiaryWhereActivity.this, DiaryWhatActivity.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                                startActivity(intent, options.toBundle());
                            }
                        }
                    }
                });
            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.where_foodlist, parent, false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            if (DiaryValue.txtTag.equals("美食")) {
                if (position == 0) {
                    holder.imageView.setImageResource(R.mipmap.ic_nightmarket2_foreground);
                } else if (position == 1) {
                    holder.imageView.setImageResource(R.mipmap.ic_cafe_foreground);
                } else if (position == 2) {
                    holder.imageView.setImageResource(R.mipmap.ic_fastfood_foreground);
                } else if (position == 3) {
                    holder.imageView.setImageResource(R.mipmap.ic_japanbar_foreground);
                } else if (position == 4) {
                    holder.imageView.setImageResource(R.mipmap.ic_office_foreground);
                } else if (position == 5) {
                    holder.imageView.setImageResource(R.mipmap.ic_restaurant_foreground);
                } else if (position == 6) {
                    holder.imageView.setImageResource(R.mipmap.ic_roadfood_foreground);
                } else if (position == 7) {
                    holder.imageView.setImageResource(R.mipmap.ic_school_foreground);
                } else if (position == 8) {
                    holder.imageView.setImageResource(R.mipmap.ic_snack_foreground);
                }
            } else if (DiaryValue.txtTag.equals("購物")) {
                if (position == 0) {
                    holder.imageView.setImageResource(R.mipmap.ic_grocery_foreground);
                } else if (position == 1) {
                    holder.imageView.setImageResource(R.mipmap.ic_departmentstore_foreground);
                } else if (position == 2) {
                    holder.imageView.setImageResource(R.mipmap.ic_market_foreground);
                } else if (position == 3) {
                    holder.imageView.setImageResource(R.mipmap.ic_downtown_foreground);
                } else if (position == 4) {
                    holder.imageView.setImageResource(R.mipmap.ic_onlineshop_foreground);
                }
            } else if(DiaryValue.txtTag.equals("休閒娛樂")){
                if (position == 0) {
                    holder.imageView.setImageResource(R.mipmap.ic_movietheater_foreground);
                } else if(position == 1){
                    holder.imageView.setImageResource(R.mipmap.ic_gym_foreground);
                } else if(position == 2){
                    holder.imageView.setImageResource(R.mipmap.ic_home_foreground);
                } else if(position == 3){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgameshop_foreground);
                } else if(position == 4){
                    holder.imageView.setImageResource(R.mipmap.ic_ktv_foreground);
                } else if(position == 5){
                    holder.imageView.setImageResource(R.mipmap.ic_park_foreground);
                } else if(position == 6){
                    holder.imageView.setImageResource(R.mipmap.ic_mrt_foreground);
                }
            } else if(DiaryValue.txtTag.equals("戀愛")){
                if (position == 0) {
                    holder.imageView.setImageResource(R.mipmap.ic_home_foreground);
                } else if(position == 1){
                    holder.imageView.setImageResource(R.mipmap.ic_park_foreground);
                } else if(position == 2){
                    holder.imageView.setImageResource(R.mipmap.ic_restaurant_foreground);
                } else if(position == 3){
                    holder.imageView.setImageResource(R.mipmap.ic_supermarket_foreground);
                } else if(position == 4){
                    holder.imageView.setImageResource(R.mipmap.ic_departmentstore_foreground);
                } else if(position == 5){
                    holder.imageView.setImageResource(R.mipmap.ic_downtown_foreground);
                } else if(position == 6){
                    holder.imageView.setImageResource(R.mipmap.ic_nightmarket2_foreground);
                }
            }

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }


    //----------------------------------------------------------------------------------------------

    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            if(DiaryValue.txtTag.equals("美食")){
                Intent intent = new Intent(DiaryWhereActivity.this, DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                DiaryWhereActivity.this.startActivity(intent, options.toBundle());
            }else if(DiaryValue.txtTag.equals("購物")){
                Intent intent = new Intent(DiaryWhereActivity.this, DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                DiaryWhereActivity.this.startActivity(intent, options.toBundle());
            }else if(DiaryValue.txtTag.equals("休閒娛樂")){
                Intent intent = new Intent(DiaryWhereActivity.this, DiaryWhenActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                DiaryWhereActivity.this.startActivity(intent, options.toBundle());
            }else if(DiaryValue.txtTag.equals("戀愛")){
                Intent intent = new Intent(DiaryWhereActivity.this, DiaryTagActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                DiaryWhereActivity.this.startActivity(intent, options.toBundle());
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
