package com.example.myapplication2.ui.home;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HomeContextActivity extends AppCompatActivity {

    private TextView txtHistoryDiary,textTitle,textDescription;
    private ImageView ContextImageView;
    private Button btnDelete,btnSave,btn_share,btn_share_best_friend,btn_share_friend;
    private int Getdata;
    private ProgressBar proBarHomeContext;
    private boolean changeBtn;
    private Animation mOpen,mClose;
    private String sharefriend = "n", sharebestfriend = "n";
    private boolean check_sharefriend = true,check_sharebestfriend = true;

    private RecyclerView recyclerview;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private HomeContextActivity.MyAdapter myAdapter;
    private LinkedList<HashMap<String,String>> data1;

    private static String context;
    private static int rowcount;
    private static String[] image_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_context);


        getPhoto();
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(mLayoutManager);
        myAdapter = new MyAdapter();
        recyclerview.setAdapter(myAdapter);
        doData();

        mOpen = AnimationUtils.loadAnimation(HomeContextActivity.this,R.anim.button_open);
        mClose = AnimationUtils.loadAnimation(HomeContextActivity.this,R.anim.button_close);

        final ImageButton imbtnBackHome = findViewById(R.id.imbtnBackHome);
        imbtnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeContextActivity.this.finish();
            }
        });

        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        txtHistoryDiary = findViewById(R.id.txtHistoryDiary);
        textTitle = findViewById(R.id.textTitle);
        textDescription = findViewById(R.id.textDescription);
        ContextImageView = findViewById(R.id.ContextImageView);
        proBarHomeContext = findViewById(R.id.proBarHomeContext);

        changeBtn = false;
        btn_share = findViewById(R.id.btn_share);
        btn_share_friend = findViewById(R.id.btn_share_friend);
        btn_share_best_friend = findViewById(R.id.btn_share_best_friend);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(changeBtn){
                    btn_share_friend.setAnimation(mClose);
                    btn_share_best_friend.setAnimation(mClose);
                    btn_share_friend.setVisibility(View.INVISIBLE);
                    btn_share_best_friend.setVisibility(View.INVISIBLE);
                    btn_share_friend.setEnabled(false);
                    btn_share_best_friend.setEnabled(false);
                    changeBtn = false;
                }else {
                    btn_share_friend.setAnimation(mOpen);
                    btn_share_best_friend.setAnimation(mOpen);
                    btn_share_friend.setVisibility(View.VISIBLE);
                    btn_share_best_friend.setVisibility(View.VISIBLE);
                    btn_share_friend.setEnabled(true);
                    btn_share_best_friend.setEnabled(true);
                    changeBtn = true;
                }
            }
        });

        btn_share_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_sharefriend == true){
                    sharefriend = "y";
                    sharebestfriend = "y";
                    check_sharefriend = false;
                    check_sharebestfriend = true;
                    btn_share_friend.setBackgroundResource(R.drawable.btn_sharediaryend2);
                    btn_share_best_friend.setBackgroundResource(R.drawable.btn_sharediaryend);

                }else{
                    sharefriend = "n";
                    sharebestfriend = "n";
                    check_sharefriend = true;
                    check_sharebestfriend = true;
                    btn_share_friend.setBackgroundResource(R.drawable.btn_sharediaryend);
                    btn_share_best_friend.setBackgroundResource(R.drawable.btn_sharediaryend);
                }
            }
        });

        btn_share_best_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_sharebestfriend == true){
                    sharebestfriend = "y";
                    sharefriend = "n";
                    check_sharefriend = true;
                    check_sharebestfriend = false;
                    btn_share_friend.setBackgroundResource(R.drawable.btn_sharediaryend);
                    btn_share_best_friend.setBackgroundResource(R.drawable.btn_sharediaryend2);
                }else{
                    sharebestfriend = "n";
                    sharefriend = "n";
                    check_sharefriend = true;
                    check_sharebestfriend = true;
                    btn_share_friend.setBackgroundResource(R.drawable.btn_sharediaryend);
                    btn_share_best_friend.setBackgroundResource(R.drawable.btn_sharediaryend);
                }
            }
        });

        dictionary dict = new dictionary();
        Getdata = getIntent().getIntExtra("data",0);
        if(Getdata == 1){
            String total = sqlReturn.LoginContent[HomeFragment.homeTag];
            String mood = sqlReturn.LoginMood[HomeFragment.homeTag];
            String date = sqlReturn.LoginDate[HomeFragment.homeTag];
            if(mood.equals("晴天")){
                mood = "心情超棒的";
            }else if(mood.equals("時晴")){
                mood = "心情不錯歐";
            }else if(mood.equals("多雲")){
                mood = "心情普普呢";
            }else if(mood.equals("陣雨")){
                mood = "心情不好啊";
            }else if(mood.equals("雷雨")){
                mood = "心情很差呢";
            }
            txtHistoryDiary.setText(total);
            textTitle.setText(mood);
            textDescription.setText(date);
            ContextImageView.setImageResource(dict.dict.get(sqlReturn.LoginOption[HomeFragment.homeTag]));
        }else if(Getdata == 2){
            String total = sqlReturn.content1[HomeFragment.homeTag];
            String mood = sqlReturn.mood1[HomeFragment.homeTag];
            String date = sqlReturn.date1[HomeFragment.homeTag];
            if(mood.equals("晴天")){
                mood = "心情超棒的";
            }else if(mood.equals("時晴")){
                mood = "心情不錯歐";
            }else if(mood.equals("多雲")){
                mood = "心情普普呢";
            }else if(mood.equals("陣雨")){
                mood = "心情不好啊";
            }else if(mood.equals("雷雨")){
                mood = "心情很差呢";
            }
            txtHistoryDiary.setText(total);
            textTitle.setText(mood);
            textDescription.setText(date);
            ContextImageView.setImageResource(dict.dict.get(sqlReturn.Option1[HomeFragment.homeTag]));
        }else if(Getdata == 3){
            String total = sqlReturn.content2[HomeFragment.homeTag];
            String mood = sqlReturn.mood2[HomeFragment.homeTag];
            String date = sqlReturn.date2[HomeFragment.homeTag];
            if(mood.equals("心情1")){
                mood = "心情超棒的";
            }else if(mood.equals("心情2")){
                mood = "心情不錯歐";
            }else if(mood.equals("心情3")){
                mood = "心情普普呢";
            }else if(mood.equals("心情4")){
                mood = "心情不好啊";
            }else if(mood.equals("心情5")){
                mood = "心情很差呢";
            }
            txtHistoryDiary.setText(total);
            textTitle.setText(mood);
            textDescription.setText(date);
            ContextImageView.setImageResource(dict.dict.get(sqlReturn.Option2[HomeFragment.homeTag]));
        }else if(Getdata == 4){
            String total = sqlReturn.content3[HomeFragment.homeTag];
            String mood = sqlReturn.mood3[HomeFragment.homeTag];
            String date = sqlReturn.date3[HomeFragment.homeTag];
            txtHistoryDiary.setText(total);
            textTitle.setText(mood);
            textDescription.setText(date);
            ContextImageView.setImageResource(R.drawable.handwrite);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(HomeContextActivity.this)
                        .setTitle("提醒")
                        .setMessage("請確認要儲存本篇日記!!")
                        .setPositiveButton("確認儲存",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                proBarHomeContext.setVisibility(View.VISIBLE);
                                EditDiary();
                            }
                        }).setNegativeButton("取消",null).create().show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(HomeContextActivity.this)
                        .setTitle("警告")
                        .setMessage("您即將要刪除本篇日記!!")
                        .setPositiveButton("確認刪除",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                proBarHomeContext.setVisibility(View.VISIBLE);
                                DeleteDiary();
                            }
                        }).setNegativeButton("取消",null).create().show();
            }
        });

    }




    public void EditDiary(){
        if(Getdata == 1){
            Map<String,String> map = new HashMap<>();
            map.put("command", "editDiary");
            map.put("diaryNo",sqlReturn.LoginDiaryID[HomeFragment.homeTag]);
            map.put("diaryNewContent",txtHistoryDiary.getText().toString());
            map.put("share",sharefriend);
            map.put("bff",sharebestfriend);
            new EditDiary(this).execute((HashMap)map);
        }else if(Getdata == 2){
            Map<String,String> map = new HashMap<>();
            map.put("command", "editDiary");
            map.put("diaryNo",sqlReturn.DiaryID1[HomeFragment.homeTag]);
            map.put("diaryNewContent",txtHistoryDiary.getText().toString());
            map.put("share",sharefriend);
            map.put("bff",sharebestfriend);
            new EditDiary(this).execute((HashMap)map);
        }else if(Getdata == 3){
            Map<String,String> map = new HashMap<>();
            map.put("command", "editDiary");
            map.put("diaryNo",sqlReturn.DiaryID2[HomeFragment.homeTag]);
            map.put("diaryNewContent",txtHistoryDiary.getText().toString());
            map.put("share",sharefriend);
            map.put("bff",sharebestfriend);
            new EditDiary(this).execute((HashMap)map);
        }else if(Getdata == 4){
            Map<String,String> map = new HashMap<>();
            map.put("command", "editDiary");
            map.put("diaryNo",sqlReturn.DiaryID3[HomeFragment.homeTag]);
            map.put("diaryNewContent",txtHistoryDiary.getText().toString());
            map.put("share",sharefriend);
            map.put("bff",sharebestfriend);
            new EditDiary(this).execute((HashMap)map);
        }
    }
    private class EditDiary extends HttpURLConnection_AsyncTask {
        WeakReference<Activity> activityReference;
        EditDiary(Activity context){ activityReference = new WeakReference<>(context); }
        @Override
        protected void onPostExecute(String result){
            JSONObject jsonObject = null;
            boolean status = false;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;
            try {
                jsonObject = new JSONObject(result);
                status = jsonObject.getBoolean("status");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                proBarHomeContext.setVisibility(View.INVISIBLE);
                //Toast.makeText(activity, "修改成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeContextActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",1);
                startActivity(intent);
            }else {
                proBarHomeContext.setVisibility(View.INVISIBLE);
                new AlertDialog.Builder(activity)
                        .setTitle("日記修改失敗")
                        .setMessage("請確認網路是否連通!!")
                        .setPositiveButton("了解", null)
                        .show();
            }
        }
    }

    public void DeleteDiary(){

        if(Getdata == 1){
            Map<String,String> map = new HashMap<>();
            map.put("command", "deleteDiary");
            map.put("diaryNo",sqlReturn.LoginDiaryID[HomeFragment.homeTag]);
            new DeleteDiary(this).execute((HashMap)map);
        }else if(Getdata == 2){
            Map<String,String> map = new HashMap<>();
            map.put("command", "deleteDiary");
            map.put("diaryNo",sqlReturn.DiaryID1[HomeFragment.homeTag]);
            new DeleteDiary(this).execute((HashMap)map);
        }else if(Getdata == 3){
            Map<String,String> map = new HashMap<>();
            map.put("command", "deleteDiary");
            map.put("diaryNo",sqlReturn.DiaryID2[HomeFragment.homeTag]);
            new DeleteDiary(this).execute((HashMap)map);
        }else if(Getdata == 4){
            Map<String,String> map = new HashMap<>();
            map.put("command", "deleteDiary");
            map.put("diaryNo",sqlReturn.DiaryID3[HomeFragment.homeTag]);
            new DeleteDiary(this).execute((HashMap)map);
        }

    }
    private class DeleteDiary extends HttpURLConnection_AsyncTask {
        // 建立弱連結
        WeakReference<Activity> activityReference;
        DeleteDiary(Activity context){
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonObject = null;
            boolean status = false;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            try {
                jsonObject = new JSONObject(result);
                status = jsonObject.getBoolean("status");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                proBarHomeContext.setVisibility(View.INVISIBLE);
                if(sqlReturn.LoginCount == 1){
                    sqlReturn.LoginCount=0;
                }
                //Toast.makeText(activity, "刪除成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeContextActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",1);
                startActivity(intent);
            }else {
                new AlertDialog.Builder(activity)
                        .setTitle("日記刪除失敗")
                        .setMessage("請確認網路是否連通!!")
                        .setPositiveButton("OK", null)
                        .show();
                proBarHomeContext.setVisibility(View.INVISIBLE);
            }
        }
    }



    // 此為抓圖片
    public void getPhoto(){
        Intent intent = getIntent();
        String diaryNo = intent.getStringExtra("diaryNo");
        Map<String,String> map = new HashMap<>();
        map.put("command", "historyContent");
        map.put("diaryNo", diaryNo);
        new getPhoto(this).execute((HashMap)map);
    }

    public class getPhoto extends HttpURLConnection_AsyncTask {
        // 建立弱連結
        WeakReference<Activity> activityReference;
        getPhoto(Activity context){
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonObject = null;
            JSONArray jsonArray = null;
            boolean status = false;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            try {
                jsonObject = new JSONObject(result);
                status = jsonObject.getBoolean("status");
                context = jsonObject.getString("results");
                rowcount = jsonObject.getInt("rowcount");
                jsonArray = new JSONArray(context);
                image_path = new String[rowcount];
                for(int i =0 ;i < rowcount;i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    image_path[i] = obj.getString("image_path");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void doData(){
        data1 = new LinkedList<>();
        for(int i = 0; i < rowcount; i++){
            HashMap<String,String> row = new HashMap<>();
            data1.add(row);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public ImageView imgPhoto;
            public ProgressBar progressBarNoData;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                imgPhoto = itemView.findViewById(R.id.imgPhoto);
                progressBarNoData = itemView.findViewById(R.id.progressBarNoData);
                progressBarNoData.setVisibility(View.VISIBLE);
            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.handwrite_item,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

            new AsyncTask<String, Void, Bitmap>(){
                @Override
                protected Bitmap doInBackground(String... params) //實作doInBackground
                {
                    String url = params[0];
                    return getBitmapFromURL(url);
                }

                @Override
                protected void onPostExecute(Bitmap result) //當doinbackground完成後
                {
                    holder.imgPhoto.setImageBitmap(result);
                    holder.progressBarNoData.setVisibility(View.INVISIBLE);
                    super.onPostExecute(result);
                }
            }.execute(image_path[position]);
        }

        @Override
        public int getItemCount() {
            return data1.size();
        }
    }

    private static Bitmap getBitmapFromURL(String imageUrl)
    {
        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            HomeContextActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
