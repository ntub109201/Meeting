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
import android.widget.TextView;

import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

import java.util.HashMap;
import java.util.LinkedList;

public class DiaryWhereActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DiaryWhereActivity.MyAdapter myAdapter;
    private LinkedList<HashMap<String,String>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_where);

        // 反回上一頁
        final ImageButton imbtnReturnFrontPage4 = findViewById(R.id.imbtnReturnFrontPage4);
        imbtnReturnFrontPage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryWhereActivity.this, DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                DiaryWhereActivity.this.startActivity(intent,options.toBundle());
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

        //跳題
        final TextView btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhere = "";
                Intent intent = new Intent();
                intent.setClass(DiaryWhereActivity.this,DiaryPreviewActivity.class);
                Bundle tagData = new Bundle();
                tagData.putString("1","DiaryWhereActivity");
                intent.putExtras(tagData);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        // 前往preview
        final TextView mPreview = findViewById(R.id.btn_preview_where);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtWhere = "";
                Intent intent = new Intent();
                intent.setClass(DiaryWhereActivity.this,DiaryPreviewActivity.class);
                Bundle tagData = new Bundle();
                tagData.putString("1","DiaryWhereActivity");
                intent.putExtras(tagData);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

    }
    public void doData(){
        data = new LinkedList<>();
        for(int i = 0; i < 9; i++){
            HashMap<String,String> row = new HashMap<>();
            data.add(row);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public ImageView imageView;
            int position;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                imageView = itemView.findViewById(R.id.imageView);
                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        position = getAdapterPosition();
                        if(position == 0){
                            DiaryValue.txtWhere = "夜市";
                            Intent intent = new Intent();
                            intent.setClass(DiaryWhereActivity.this,DiaryPreviewActivity.class);
                            Bundle tagData = new Bundle();
                            tagData.putString("1","End");
                            intent.putExtras(tagData);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                            startActivity(intent,options.toBundle());
                        }else if(position == 1){
                            DiaryValue.txtWhere = "咖啡廳";
                            Intent intent = new Intent();
                            intent.setClass(DiaryWhereActivity.this,DiaryPreviewActivity.class);
                            Bundle tagData = new Bundle();
                            tagData.putString("1","End");
                            intent.putExtras(tagData);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                            startActivity(intent,options.toBundle());
                        }else if(position == 2){
                            DiaryValue.txtWhere = "速食店";
                            Intent intent = new Intent();
                            intent.setClass(DiaryWhereActivity.this,DiaryPreviewActivity.class);
                            Bundle tagData = new Bundle();
                            tagData.putString("1","End");
                            intent.putExtras(tagData);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                            startActivity(intent,options.toBundle());
                        }else if(position == 3){
                            DiaryValue.txtWhere = "居酒屋";
                            Intent intent = new Intent();
                            intent.setClass(DiaryWhereActivity.this,DiaryPreviewActivity.class);
                            Bundle tagData = new Bundle();
                            tagData.putString("1","End");
                            intent.putExtras(tagData);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                            startActivity(intent,options.toBundle());
                        }else if(position == 4){
                            DiaryValue.txtWhere = "辦公室";
                            Intent intent = new Intent();
                            intent.setClass(DiaryWhereActivity.this,DiaryPreviewActivity.class);
                            Bundle tagData = new Bundle();
                            tagData.putString("1","End");
                            intent.putExtras(tagData);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                            startActivity(intent,options.toBundle());
                        }else if(position == 5){
                            DiaryValue.txtWhere = "餐廳";
                            Intent intent = new Intent();
                            intent.setClass(DiaryWhereActivity.this,DiaryPreviewActivity.class);
                            Bundle tagData = new Bundle();
                            tagData.putString("1","End");
                            intent.putExtras(tagData);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                            startActivity(intent,options.toBundle());
                        }else if(position == 6){
                            DiaryValue.txtWhere = "路邊攤";
                            Intent intent = new Intent();
                            intent.setClass(DiaryWhereActivity.this,DiaryPreviewActivity.class);
                            Bundle tagData = new Bundle();
                            tagData.putString("1","End");
                            intent.putExtras(tagData);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                            startActivity(intent,options.toBundle());
                        }else if(position == 7){
                            DiaryValue.txtWhere = "學校";
                            Intent intent = new Intent();
                            intent.setClass(DiaryWhereActivity.this,DiaryPreviewActivity.class);
                            Bundle tagData = new Bundle();
                            tagData.putString("1","End");
                            intent.putExtras(tagData);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                            startActivity(intent,options.toBundle());
                        }else if(position == 8){
                            DiaryValue.txtWhere = "小吃店";
                            Intent intent = new Intent();
                            intent.setClass(DiaryWhereActivity.this,DiaryPreviewActivity.class);
                            Bundle tagData = new Bundle();
                            tagData.putString("1","End");
                            intent.putExtras(tagData);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryWhereActivity.this);
                            startActivity(intent,options.toBundle());
                        }
                    }
                });
            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.where_foodlist,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

            if(position == 0){
                holder.imageView.setImageResource(R.mipmap.ic_nightmarket2_foreground);
            }else if(position == 1){
                holder.imageView.setImageResource(R.mipmap.ic_cafe_foreground);
            }else if(position == 2){
                holder.imageView.setImageResource(R.mipmap.ic_fastfood_foreground);
            }else if(position == 3){
                holder.imageView.setImageResource(R.mipmap.ic_japanbar_foreground);
            }else if(position == 4){
                holder.imageView.setImageResource(R.mipmap.ic_office_foreground);
            }else if(position == 5){
                holder.imageView.setImageResource(R.mipmap.ic_restaurant_foreground);
            }else if(position == 6){
                holder.imageView.setImageResource(R.mipmap.ic_roadfood_foreground);
            }else if(position == 7){
                holder.imageView.setImageResource(R.mipmap.ic_school_foreground);
            }else if(position == 8){
                holder.imageView.setImageResource(R.mipmap.ic_snack_foreground);
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
    //----------------------------------------------------------------------------------------------

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
