package com.example.myapplication2.ui.home;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication2.Diary.DiaryActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.HandwriteActivity;
import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.Login.LoginActivity;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.ModifyPersonalActivity;
import com.example.myapplication2.OCRActivity;
import com.example.myapplication2.PersonalActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.sql.Ref;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinkedList<HashMap<String,String>> data;
    private HomeFragment.MyAdapter myAdapter;
    private Button btnWriteTodayDiary;
    public static int homeTag;
    private Button searchBtnMood;
    private Button searchBtnTag;
    private Button searchBtn1;
    private Button searchBtn2;
    private Button searchBtn3;
    private Button searchBtn4;
    private Button searchBtn5;
    private Button buttonTest;
    private Button searchBtnHandWrite;
    private ProgressBar progressBarHome;
    private SwipeRefreshLayout RefreshLayoutHome;
    private ImageButton imBtnPersonal;
    private Button btnAnim;
    private ConstraintLayout mLayout;
    private Button goToHandwritebutton,goToDiarybutton,goToOCRbutton;
    public static boolean changeBtn = false;
    private static boolean camera = false;


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        btnWriteTodayDiary = root.findViewById(R.id.btnWriteTodayDiary);
        btnWriteTodayDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFragment.super.getActivity(), DiaryActivity.class);
                startActivity(intent);
            }
        });

        if(changeBtn == true){
            changeBtn = false;
        }

        btnAnim = root.findViewById(R.id.btnAnim);
        btnAnim.setOnClickListener(btnChangeColorOnClick);
        mLayout = root.findViewById(R.id.testConstraint);
        // 前往引導日記
        goToDiarybutton = root.findViewById(R.id.goToDiarybutton);
        goToDiarybutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changeBtn = false;
                Intent registerIntent = new Intent(HomeFragment.super.getActivity(), DiaryActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeFragment.super.getActivity());
                HomeFragment.super.getActivity().startActivity(registerIntent,options.toBundle());
            }
        });

        // 前往手寫日記
        goToHandwritebutton = root.findViewById(R.id.goToHandwritebutton);
        goToHandwritebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changeBtn = false;
                Intent registerIntent = new Intent(HomeFragment.super.getActivity(), HandwriteActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeFragment.super.getActivity());
                HomeFragment.super.getActivity().startActivity(registerIntent,options.toBundle());
            }
        });

        // OCR暫時沒有
        goToOCRbutton = root.findViewById(R.id.goToOCRbutton);
        goToOCRbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getPermissionsCamera();
                if(camera){
                    Intent intent = new Intent(HomeFragment.super.getActivity(), OCRActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeFragment.super.getActivity());
                    HomeFragment.super.getActivity().startActivity(intent,options.toBundle());
                }
            }
        });

        getPermissionsCamera();

        // adapter
        mRecyclerView = root.findViewById(R.id.HistoryRecyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(HomeFragment.super.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        RefreshLayoutHome = root.findViewById(R.id.RefreshLayoutHome);
        RefreshLayoutHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (buttonTest.getText().equals("晴天") || buttonTest.getText().equals("時晴") || buttonTest.getText().equals("多雲") || buttonTest.getText().equals("陣雨") || buttonTest.getText().equals("雷雨")) {
                    searchByMood();
                } else if (buttonTest.getText().equals("美食") || buttonTest.getText().equals("購物")) {
                    searchByTag();
                } else if(buttonTest.getText().equals("手寫日記")){
                    SearchHandWrite();
                } else{
                    history();
                }
            }
        });


        doData();
        history();
        myAdapter = new MyAdapter();
        mRecyclerView.setAdapter(myAdapter);

        // 搜尋切換
        searchBtnMood = root.findViewById(R.id.searchBtnMood);
        searchBtnTag = root.findViewById(R.id.searchBtnTag);
        searchBtnHandWrite = root.findViewById(R.id.searchBtnHandWrite);
        searchBtn1 = root.findViewById(R.id.searchBtn1);
        searchBtn2 = root.findViewById(R.id.searchBtn2);
        searchBtn3 = root.findViewById(R.id.searchBtn3);
        searchBtn4 = root.findViewById(R.id.searchBtn4);
        searchBtn5 = root.findViewById(R.id.searchBtn5);
        buttonTest = root.findViewById(R.id.buttonTest);
        progressBarHome = root.findViewById(R.id.progressBarHome);

        searchBtnTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBtn1.setText("晴天");
                searchBtn2.setText("時晴");
                searchBtn3.setText("多雲");
                searchBtn4.setText("陣雨");
                searchBtn5.setText("雷雨");
            }
        });
        searchBtnMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBtn1.setText("美食");
                searchBtn2.setText("購物");
                searchBtn3.setText("愛情");
                searchBtn4.setText("旅遊");
                searchBtn5.setText("娛樂");
            }
        });
        searchBtnHandWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonTest.setText("手寫日記");
                progressBarHome.setVisibility(View.VISIBLE);
                SearchHandWrite();
            }
        });
        searchBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBtn1.getText().equals("晴天")){
                    buttonTest.setText("晴天");
                    progressBarHome.setVisibility(View.VISIBLE);
                    searchByMood();
                }else if(searchBtn1.getText().equals("美食")){
                    buttonTest.setText("美食");
                    progressBarHome.setVisibility(View.VISIBLE);
                    searchByTag();
                }

            }
        });
        searchBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBtn2.getText().equals("時晴")){
                    buttonTest.setText("時晴");
                    progressBarHome.setVisibility(View.VISIBLE);
                    searchByMood();
                }else if(searchBtn2.getText().equals("購物")){
                    buttonTest.setText("購物");
                    searchByTag();
                }
            }
        });
        searchBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBtn3.getText().equals("多雲")){
                    buttonTest.setText("多雲");
                    progressBarHome.setVisibility(View.VISIBLE);
                    searchByMood();
                    //doDataSearchMood();
                }else if(searchBtn3.getText().equals("愛情")){
                    buttonTest.setText("愛情");
                    //searchTag();
                }
            }
        });
        searchBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBtn4.getText().equals("陣雨")){
                    buttonTest.setText("陣雨");
                    progressBarHome.setVisibility(View.VISIBLE);
                    searchByMood();
                    //doDataSearchMood();
                }else if(searchBtn4.getText().equals("旅遊")){
                    buttonTest.setText("旅遊");
                    //searchTag();
                }
            }
        });
        searchBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBtn5.getText().equals("雷雨")){
                    buttonTest.setText("雷雨");
                    progressBarHome.setVisibility(View.VISIBLE);
                    searchByMood();
                    //doDataSearchMood();
                }else if(searchBtn5.getText().equals("娛樂")){
                    buttonTest.setText("娛樂");
                    //searchTag();
                }
            }
        });

        // Personal
        imBtnPersonal = root.findViewById(R.id.imBtnPersonal);
        imBtnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFragment.super.getActivity(), PersonalActivity.class);
                intent.putExtra("pageId",1);
                startActivity(intent);
            }
        });

        // 時間
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        final TextView DateTime = root.findViewById(R.id.DateTime);
        DateTime.setText(currentDate);

        return root;
    }

    public void getPermissionsCamera(){
        if(ActivityCompat.checkSelfPermission(HomeFragment.super.getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(HomeFragment.super.getActivity(),
                    Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(HomeFragment.super.getActivity())
                        .setCancelable(false)
                        .setTitle("提醒您")
                        .setMessage("需要開啟相機權限才可使用照相功能歐!!")
                        .setPositiveButton("了解", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(HomeFragment.super.getActivity(),new String[]{Manifest.permission.CAMERA},1);
                            }
                        })
                        .show();
            }else{
                ActivityCompat.requestPermissions(HomeFragment.super.getActivity(),new String[]{Manifest.permission.CAMERA},1);
            }
        }else {
            camera = true;
        }
    }

    // 此為全抓
    //----------------------------------------------------------------------------------------------
    public void history(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "history");
        map.put("uid", uid);
        new history(super.getActivity()).execute((HashMap)map);
    }

    public class history extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        history(Activity context){
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
                sqlReturn.LoginTextViewContext = jsonObject.getString("results");
                sqlReturn.LoginCount = jsonObject.getInt("rowcount");
                jsonArray = new JSONArray(sqlReturn.LoginTextViewContext);
                sqlReturn.LoginContent = new String[sqlReturn.LoginCount];
                sqlReturn.LoginTagName = new String[sqlReturn.LoginCount];
                sqlReturn.LoginMood = new String[sqlReturn.LoginCount];
                sqlReturn.LoginDate = new String[sqlReturn.LoginCount];
                sqlReturn.LoginOption = new String[sqlReturn.LoginCount];
                sqlReturn.LoginDiaryID = new String[sqlReturn.LoginCount];
                for(int i = 0; i<sqlReturn.LoginCount; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.LoginContent[i] = obj.getString("content");
                    sqlReturn.LoginTagName[i] = obj.getString("tagName");
                    if(obj.getString("mood").equals("心情1")){
                        sqlReturn.LoginMood[i] = "晴天";
                    }else if(obj.getString("mood").equals("心情2")){
                        sqlReturn.LoginMood[i] = "時晴";
                    }else if(obj.getString("mood").equals("心情3")){
                        sqlReturn.LoginMood[i] = "多雲";
                    }else if(obj.getString("mood").equals("心情4")){
                        sqlReturn.LoginMood[i] = "陣雨";
                    }else if(obj.getString("mood").equals("心情5")){
                        sqlReturn.LoginMood[i] = "雷雨";
                    }else if(obj.getString("mood").equals("手寫日記心情")){
                        sqlReturn.LoginMood[i] = "手寫日記";
                    }
                    sqlReturn.LoginDate[i] = obj.getString("date");
                    sqlReturn.LoginOption[i] = obj.getString("optionNo");
                    sqlReturn.LoginDiaryID[i] = obj.getString("diaryNo");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (sqlReturn.LoginTextViewContext!=null){
                doData();
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(HomeFragment.super.getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                myAdapter = new MyAdapter();
                mRecyclerView.setAdapter(myAdapter);
                RefreshLayoutHome.setRefreshing(false);
            }else {

            }
        }

    }
    public void doData(){
        data = new LinkedList<>();
        for(int i = 0; i < sqlReturn.LoginCount; i++){
            HashMap<String,String> row = new HashMap<>();
            row.put("textTitle",sqlReturn.LoginMood[i]);
            row.put("textDescription",sqlReturn.LoginDate[i]);
            data.add(row);
            sqlReturn.model = 1;
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public ImageView imageView;
            public TextView textTitle, textDescription;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                imageView = itemView.findViewById(R.id.imageView);
                textTitle = itemView.findViewById(R.id.textTitle);
                textDescription = itemView.findViewById(R.id.textDescription);
                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        if(sqlReturn.model==1){
                            homeTag = getAdapterPosition();
                            Intent intent = new Intent(HomeFragment.super.getActivity(),HomeContextActivity.class);
                            intent.putExtra("data",1);
                            startActivity(intent);
                        }else if(sqlReturn.model==2){
                            homeTag = getAdapterPosition();
                            Intent intent = new Intent(HomeFragment.super.getActivity(),HomeContextActivity.class);
                            intent.putExtra("data",2);
                            startActivity(intent);
                        }else if(sqlReturn.model==3){
                            homeTag = getAdapterPosition();
                            Intent intent = new Intent(HomeFragment.super.getActivity(),HomeContextActivity.class);
                            intent.putExtra("data",3);
                            startActivity(intent);
                        }else if(sqlReturn.model==4){
                            homeTag = getAdapterPosition();
                            Intent intent = new Intent(HomeFragment.super.getActivity(),HomeContextActivity.class);
                            intent.putExtra("data",4);
                            startActivity(intent);
                        }
                    }
                });
            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.homeitem1,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {


            if(sqlReturn.model == 1){
                if(sqlReturn.LoginOption[position].equals("null")){
                    holder.imageView.setImageResource(R.mipmap.ic_no_tag_foreground);
                }else if(sqlReturn.LoginOption[position].equals("1")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.LoginOption[position].equals("2")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.LoginOption[position].equals("4")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.LoginOption[position].equals("5")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.LoginOption[position].equals("6")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.LoginOption[position].equals("7")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.LoginOption[position].equals("8")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.LoginOption[position].equals("9")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.LoginOption[position].equals("10")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.LoginOption[position].equals("11")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.LoginOption[position].equals("12")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.LoginOption[position].equals("13")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.LoginOption[position].equals("42")){
                    holder.imageView.setImageResource(R.drawable.handwrite);
                }else if(sqlReturn.LoginOption[position].equals("45")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.LoginOption[position].equals("46")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.LoginOption[position].equals("47")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.LoginOption[position].equals("48")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.LoginOption[position].equals("49")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.LoginOption[position].equals("50")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.LoginOption[position].equals("72")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.LoginOption[position].equals("73")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.LoginOption[position].equals("74")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.LoginOption[position].equals("75")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.LoginOption[position].equals("76")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.LoginOption[position].equals("77")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.LoginOption[position].equals("99")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.LoginOption[position].equals("100")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.LoginOption[position].equals("101")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.LoginOption[position].equals("102")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.LoginOption[position].equals("103")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.LoginOption[position].equals("104")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.LoginOption[position].equals("126")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.LoginOption[position].equals("127")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.LoginOption[position].equals("128")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.LoginOption[position].equals("129")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.LoginOption[position].equals("130")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.LoginOption[position].equals("131")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.LoginOption[position].equals("153")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.LoginOption[position].equals("154")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.LoginOption[position].equals("155")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.LoginOption[position].equals("156")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.LoginOption[position].equals("157")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.LoginOption[position].equals("158")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.LoginOption[position].equals("182")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.LoginOption[position].equals("183")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.LoginOption[position].equals("184")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.LoginOption[position].equals("185")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.LoginOption[position].equals("186")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.LoginOption[position].equals("187")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.LoginOption[position].equals("188")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.LoginOption[position].equals("189")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.LoginOption[position].equals("190")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.LoginOption[position].equals("191")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.LoginOption[position].equals("192")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.LoginOption[position].equals("193")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.LoginOption[position].equals("224")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.LoginOption[position].equals("225")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.LoginOption[position].equals("226")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.LoginOption[position].equals("227")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.LoginOption[position].equals("228")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.LoginOption[position].equals("229")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.LoginOption[position].equals("230")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.LoginOption[position].equals("231")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.LoginOption[position].equals("232")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.LoginOption[position].equals("233")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.LoginOption[position].equals("234")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.LoginOption[position].equals("235")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.LoginOption[position].equals("266")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.LoginOption[position].equals("267")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.LoginOption[position].equals("268")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.LoginOption[position].equals("269")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.LoginOption[position].equals("270")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.LoginOption[position].equals("271")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.LoginOption[position].equals("272")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.LoginOption[position].equals("273")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.LoginOption[position].equals("274")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.LoginOption[position].equals("275")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.LoginOption[position].equals("276")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.LoginOption[position].equals("277")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.LoginOption[position].equals("308")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.LoginOption[position].equals("309")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.LoginOption[position].equals("310")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.LoginOption[position].equals("311")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.LoginOption[position].equals("312")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.LoginOption[position].equals("313")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.LoginOption[position].equals("314")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.LoginOption[position].equals("315")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.LoginOption[position].equals("316")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.LoginOption[position].equals("317")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.LoginOption[position].equals("318")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.LoginOption[position].equals("319")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.LoginOption[position].equals("350")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.LoginOption[position].equals("351")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.LoginOption[position].equals("352")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.LoginOption[position].equals("353")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.LoginOption[position].equals("354")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.LoginOption[position].equals("355")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.LoginOption[position].equals("356")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.LoginOption[position].equals("380")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.LoginOption[position].equals("381")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.LoginOption[position].equals("382")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.LoginOption[position].equals("383")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.LoginOption[position].equals("384")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.LoginOption[position].equals("385")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.LoginOption[position].equals("386")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.LoginOption[position].equals("410")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.LoginOption[position].equals("411")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.LoginOption[position].equals("412")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.LoginOption[position].equals("413")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.LoginOption[position].equals("414")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.LoginOption[position].equals("415")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.LoginOption[position].equals("416")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.LoginOption[position].equals("440")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.LoginOption[position].equals("441")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.LoginOption[position].equals("442")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.LoginOption[position].equals("443")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.LoginOption[position].equals("444")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.LoginOption[position].equals("445")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.LoginOption[position].equals("446")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.LoginOption[position].equals("470")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.LoginOption[position].equals("471")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.LoginOption[position].equals("472")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.LoginOption[position].equals("473")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.LoginOption[position].equals("474")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.LoginOption[position].equals("475")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.LoginOption[position].equals("476")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.LoginOption[position].equals("499")){
                    holder.imageView.setImageResource(R.mipmap.ic_what_date_foreground);
                }else if(sqlReturn.LoginOption[position].equals("500")){
                    holder.imageView.setImageResource(R.mipmap.ic_movielover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("501")){
                    holder.imageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("502")){
                    holder.imageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("503")){
                    holder.imageView.setImageResource(R.mipmap.ic_singlover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("504")){
                    holder.imageView.setImageResource(R.mipmap.ic_shoplover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("525")){
                    holder.imageView.setImageResource(R.mipmap.ic_what_date_foreground);
                }else if(sqlReturn.LoginOption[position].equals("526")){
                    holder.imageView.setImageResource(R.mipmap.ic_movielover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("527")){
                    holder.imageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("528")){
                    holder.imageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("529")){
                    holder.imageView.setImageResource(R.mipmap.ic_singlover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("530")){
                    holder.imageView.setImageResource(R.mipmap.ic_shoplover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("551")){
                    holder.imageView.setImageResource(R.mipmap.ic_what_date_foreground);
                }else if(sqlReturn.LoginOption[position].equals("552")){
                    holder.imageView.setImageResource(R.mipmap.ic_movielover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("553")){
                    holder.imageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("554")){
                    holder.imageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("555")){
                    holder.imageView.setImageResource(R.mipmap.ic_singlover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("556")){
                    holder.imageView.setImageResource(R.mipmap.ic_shoplover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("577")){
                    holder.imageView.setImageResource(R.mipmap.ic_what_date_foreground);
                }else if(sqlReturn.LoginOption[position].equals("578")){
                    holder.imageView.setImageResource(R.mipmap.ic_movielover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("579")){
                    holder.imageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("580")){
                    holder.imageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("581")){
                    holder.imageView.setImageResource(R.mipmap.ic_singlover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("582")){
                    holder.imageView.setImageResource(R.mipmap.ic_shoplover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("603")){
                    holder.imageView.setImageResource(R.mipmap.ic_quarrellover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("604")){
                    holder.imageView.setImageResource(R.mipmap.ic_coldwar_foreground);
                }else if(sqlReturn.LoginOption[position].equals("605")){
                    holder.imageView.setImageResource(R.mipmap.ic_fighting_foreground);
                }else if(sqlReturn.LoginOption[position].equals("606")){
                    holder.imageView.setImageResource(R.mipmap.ic_breakup_foreground);
                }else if(sqlReturn.LoginOption[position].equals("607")){
                    holder.imageView.setImageResource(R.mipmap.ic_arguelover_foreground);
                }else if(sqlReturn.LoginOption[position].equals("608")){
                    holder.imageView.setImageResource(R.mipmap.ic_complain_foreground);
                }
            }else if(sqlReturn.model == 2){
                if(sqlReturn.Option1[position].equals("null")){
                    holder.imageView.setImageResource(R.mipmap.ic_no_tag_foreground);
                }else if(sqlReturn.Option1[position].equals("1")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.Option1[position].equals("2")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.Option1[position].equals("4")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.Option1[position].equals("5")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.Option1[position].equals("6")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.Option1[position].equals("7")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.Option1[position].equals("8")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.Option1[position].equals("9")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.Option1[position].equals("10")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.Option1[position].equals("11")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.Option1[position].equals("12")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.Option1[position].equals("13")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.Option1[position].equals("42")){
                    holder.imageView.setImageResource(R.drawable.handwrite);
                }else if(sqlReturn.Option1[position].equals("45")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.Option1[position].equals("46")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.Option1[position].equals("47")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.Option1[position].equals("48")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.Option1[position].equals("49")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.Option1[position].equals("50")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.Option1[position].equals("72")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.Option1[position].equals("73")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.Option1[position].equals("74")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.Option1[position].equals("75")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.Option1[position].equals("76")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.Option1[position].equals("77")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.Option1[position].equals("99")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.Option1[position].equals("100")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.Option1[position].equals("101")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.Option1[position].equals("102")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.Option1[position].equals("103")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.Option1[position].equals("104")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.Option1[position].equals("126")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.Option1[position].equals("127")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.Option1[position].equals("128")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.Option1[position].equals("129")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.Option1[position].equals("130")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.Option1[position].equals("131")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.Option1[position].equals("153")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.Option1[position].equals("154")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.Option1[position].equals("155")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.Option1[position].equals("156")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.Option1[position].equals("157")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.Option1[position].equals("158")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.Option1[position].equals("182")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.Option1[position].equals("183")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.Option1[position].equals("184")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.Option1[position].equals("185")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.Option1[position].equals("186")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.Option1[position].equals("187")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.Option1[position].equals("188")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.Option1[position].equals("189")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.Option1[position].equals("190")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.Option1[position].equals("191")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.Option1[position].equals("192")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.Option1[position].equals("193")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.Option1[position].equals("224")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.Option1[position].equals("225")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.Option1[position].equals("226")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.Option1[position].equals("227")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.Option1[position].equals("228")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.Option1[position].equals("229")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.Option1[position].equals("230")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.Option1[position].equals("231")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.Option1[position].equals("232")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.Option1[position].equals("233")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.Option1[position].equals("234")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.Option1[position].equals("235")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.Option1[position].equals("266")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.Option1[position].equals("267")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.Option1[position].equals("268")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.Option1[position].equals("269")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.Option1[position].equals("270")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.Option1[position].equals("271")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.Option1[position].equals("272")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.Option1[position].equals("273")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.Option1[position].equals("274")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.Option1[position].equals("275")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.Option1[position].equals("276")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.Option1[position].equals("277")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.Option1[position].equals("308")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.Option1[position].equals("309")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.Option1[position].equals("310")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.Option1[position].equals("311")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.Option1[position].equals("312")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.Option1[position].equals("313")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.Option1[position].equals("314")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.Option1[position].equals("315")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.Option1[position].equals("316")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.Option1[position].equals("317")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.Option1[position].equals("318")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.Option1[position].equals("319")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.Option1[position].equals("350")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.Option1[position].equals("351")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.Option1[position].equals("352")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.Option1[position].equals("353")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.Option1[position].equals("354")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.Option1[position].equals("355")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.Option1[position].equals("356")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.Option1[position].equals("380")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.Option1[position].equals("381")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.Option1[position].equals("382")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.Option1[position].equals("383")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.Option1[position].equals("384")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.Option1[position].equals("385")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.Option1[position].equals("386")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.Option1[position].equals("410")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.Option1[position].equals("411")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.Option1[position].equals("412")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.Option1[position].equals("413")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.Option1[position].equals("414")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.Option1[position].equals("415")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.Option1[position].equals("416")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.Option1[position].equals("440")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.Option1[position].equals("441")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.Option1[position].equals("442")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.Option1[position].equals("443")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.Option1[position].equals("444")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.Option1[position].equals("445")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.Option1[position].equals("446")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.Option1[position].equals("470")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.Option1[position].equals("471")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.Option1[position].equals("472")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.Option1[position].equals("473")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.Option1[position].equals("474")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.Option1[position].equals("475")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.Option1[position].equals("476")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.Option1[position].equals("499")){
                    holder.imageView.setImageResource(R.mipmap.ic_what_date_foreground);
                }else if(sqlReturn.Option1[position].equals("500")){
                    holder.imageView.setImageResource(R.mipmap.ic_movielover_foreground);
                }else if(sqlReturn.Option1[position].equals("501")){
                    holder.imageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
                }else if(sqlReturn.Option1[position].equals("502")){
                    holder.imageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
                }else if(sqlReturn.Option1[position].equals("503")){
                    holder.imageView.setImageResource(R.mipmap.ic_singlover_foreground);
                }else if(sqlReturn.Option1[position].equals("504")){
                    holder.imageView.setImageResource(R.mipmap.ic_shoplover_foreground);
                }else if(sqlReturn.Option1[position].equals("525")){
                    holder.imageView.setImageResource(R.mipmap.ic_what_date_foreground);
                }else if(sqlReturn.Option1[position].equals("526")){
                    holder.imageView.setImageResource(R.mipmap.ic_movielover_foreground);
                }else if(sqlReturn.Option1[position].equals("527")){
                    holder.imageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
                }else if(sqlReturn.Option1[position].equals("528")){
                    holder.imageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
                }else if(sqlReturn.Option1[position].equals("529")){
                    holder.imageView.setImageResource(R.mipmap.ic_singlover_foreground);
                }else if(sqlReturn.Option1[position].equals("530")){
                    holder.imageView.setImageResource(R.mipmap.ic_shoplover_foreground);
                }else if(sqlReturn.Option1[position].equals("551")){
                    holder.imageView.setImageResource(R.mipmap.ic_what_date_foreground);
                }else if(sqlReturn.Option1[position].equals("552")){
                    holder.imageView.setImageResource(R.mipmap.ic_movielover_foreground);
                }else if(sqlReturn.Option1[position].equals("553")){
                    holder.imageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
                }else if(sqlReturn.Option1[position].equals("554")){
                    holder.imageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
                }else if(sqlReturn.Option1[position].equals("555")){
                    holder.imageView.setImageResource(R.mipmap.ic_singlover_foreground);
                }else if(sqlReturn.Option1[position].equals("556")){
                    holder.imageView.setImageResource(R.mipmap.ic_shoplover_foreground);
                }else if(sqlReturn.Option1[position].equals("577")){
                    holder.imageView.setImageResource(R.mipmap.ic_what_date_foreground);
                }else if(sqlReturn.Option1[position].equals("578")){
                    holder.imageView.setImageResource(R.mipmap.ic_movielover_foreground);
                }else if(sqlReturn.Option1[position].equals("579")){
                    holder.imageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
                }else if(sqlReturn.Option1[position].equals("580")){
                    holder.imageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
                }else if(sqlReturn.Option1[position].equals("581")){
                    holder.imageView.setImageResource(R.mipmap.ic_singlover_foreground);
                }else if(sqlReturn.Option1[position].equals("582")){
                    holder.imageView.setImageResource(R.mipmap.ic_shoplover_foreground);
                }else if(sqlReturn.Option1[position].equals("603")){
                    holder.imageView.setImageResource(R.mipmap.ic_quarrellover_foreground);
                }else if(sqlReturn.Option1[position].equals("604")){
                    holder.imageView.setImageResource(R.mipmap.ic_coldwar_foreground);
                }else if(sqlReturn.Option1[position].equals("605")){
                    holder.imageView.setImageResource(R.mipmap.ic_fighting_foreground);
                }else if(sqlReturn.Option1[position].equals("606")){
                    holder.imageView.setImageResource(R.mipmap.ic_breakup_foreground);
                }else if(sqlReturn.Option1[position].equals("607")){
                    holder.imageView.setImageResource(R.mipmap.ic_arguelover_foreground);
                }else if(sqlReturn.Option1[position].equals("608")){
                    holder.imageView.setImageResource(R.mipmap.ic_complain_foreground);
                }
            }else if(sqlReturn.model == 3){
                if(sqlReturn.Option2[position].equals("null")){
                    holder.imageView.setImageResource(R.mipmap.ic_no_tag_foreground);
                }else if(sqlReturn.Option2[position].equals("1")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.Option2[position].equals("2")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.Option2[position].equals("4")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.Option2[position].equals("5")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.Option2[position].equals("6")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.Option2[position].equals("7")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.Option2[position].equals("8")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.Option2[position].equals("9")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.Option2[position].equals("10")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.Option2[position].equals("11")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.Option2[position].equals("12")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.Option2[position].equals("13")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.Option2[position].equals("42")){
                    holder.imageView.setImageResource(R.drawable.handwrite);
                }else if(sqlReturn.Option2[position].equals("45")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.Option2[position].equals("46")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.Option2[position].equals("47")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.Option2[position].equals("48")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.Option2[position].equals("49")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.Option2[position].equals("50")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.Option2[position].equals("72")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.Option2[position].equals("73")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.Option2[position].equals("74")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.Option2[position].equals("75")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.Option2[position].equals("76")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.Option2[position].equals("77")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.Option2[position].equals("99")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.Option2[position].equals("100")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.Option2[position].equals("101")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.Option2[position].equals("102")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.Option2[position].equals("103")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.Option2[position].equals("104")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.Option2[position].equals("126")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.Option2[position].equals("127")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.Option2[position].equals("128")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.Option2[position].equals("129")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.Option2[position].equals("130")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.Option2[position].equals("131")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.Option2[position].equals("153")){
                    holder.imageView.setImageResource(R.mipmap.ic_daily_foreground);
                }else if(sqlReturn.Option2[position].equals("154")){
                    holder.imageView.setImageResource(R.mipmap.ic_buy_food_foreground);
                }else if(sqlReturn.Option2[position].equals("155")){
                    holder.imageView.setImageResource(R.mipmap.ic_3c_foreground);
                }else if(sqlReturn.Option2[position].equals("156")){
                    holder.imageView.setImageResource(R.mipmap.ic_makeup_foreground);
                }else if(sqlReturn.Option2[position].equals("157")){
                    holder.imageView.setImageResource(R.mipmap.ic_car_foreground);
                }else if(sqlReturn.Option2[position].equals("158")){
                    holder.imageView.setImageResource(R.mipmap.ic_clothes_foreground);
                }else if(sqlReturn.Option2[position].equals("182")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.Option2[position].equals("183")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.Option2[position].equals("184")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.Option2[position].equals("185")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.Option2[position].equals("186")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.Option2[position].equals("187")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.Option2[position].equals("188")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.Option2[position].equals("189")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.Option2[position].equals("190")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.Option2[position].equals("191")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.Option2[position].equals("192")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.Option2[position].equals("193")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.Option2[position].equals("224")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.Option2[position].equals("225")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.Option2[position].equals("226")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.Option2[position].equals("227")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.Option2[position].equals("228")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.Option2[position].equals("229")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.Option2[position].equals("230")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.Option2[position].equals("231")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.Option2[position].equals("232")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.Option2[position].equals("233")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.Option2[position].equals("234")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.Option2[position].equals("235")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.Option2[position].equals("266")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.Option2[position].equals("267")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.Option2[position].equals("268")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.Option2[position].equals("269")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.Option2[position].equals("270")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.Option2[position].equals("271")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.Option2[position].equals("272")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.Option2[position].equals("273")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.Option2[position].equals("274")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.Option2[position].equals("275")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.Option2[position].equals("276")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.Option2[position].equals("277")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.Option2[position].equals("308")){
                    holder.imageView.setImageResource(R.mipmap.ic_japan_foreground);
                }else if(sqlReturn.Option2[position].equals("309")){
                    holder.imageView.setImageResource(R.mipmap.ic_korea_foreground);
                }else if(sqlReturn.Option2[position].equals("310")){
                    holder.imageView.setImageResource(R.mipmap.ic_taiwan_foreground);
                }else if(sqlReturn.Option2[position].equals("311")){
                    holder.imageView.setImageResource(R.mipmap.ic_thailand_foreground);
                }else if(sqlReturn.Option2[position].equals("312")){
                    holder.imageView.setImageResource(R.mipmap.ic_italy_foreground);
                }else if(sqlReturn.Option2[position].equals("313")){
                    holder.imageView.setImageResource(R.mipmap.ic_southeastasia_foreground_foreground);
                }else if(sqlReturn.Option2[position].equals("314")){
                    holder.imageView.setImageResource(R.mipmap.ic_china_foreground);
                }else if(sqlReturn.Option2[position].equals("315")){
                    holder.imageView.setImageResource(R.mipmap.ic_hongkong_foreground);
                }else if(sqlReturn.Option2[position].equals("316")){
                    holder.imageView.setImageResource(R.mipmap.ic_otherfood_foreground);
                }else if(sqlReturn.Option2[position].equals("317")){
                    holder.imageView.setImageResource(R.mipmap.ic_random_foreground);
                }else if(sqlReturn.Option2[position].equals("318")){
                    holder.imageView.setImageResource(R.mipmap.ic_drinks_foreground);
                }else if(sqlReturn.Option2[position].equals("319")){
                    holder.imageView.setImageResource(R.mipmap.ic_alcohol_foreground);
                }else if(sqlReturn.Option2[position].equals("350")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.Option2[position].equals("351")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.Option2[position].equals("352")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.Option2[position].equals("353")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.Option2[position].equals("354")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.Option2[position].equals("355")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.Option2[position].equals("356")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.Option2[position].equals("380")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.Option2[position].equals("381")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.Option2[position].equals("382")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.Option2[position].equals("383")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.Option2[position].equals("384")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.Option2[position].equals("385")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.Option2[position].equals("386")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.Option2[position].equals("410")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.Option2[position].equals("411")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.Option2[position].equals("412")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.Option2[position].equals("413")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.Option2[position].equals("414")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.Option2[position].equals("415")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.Option2[position].equals("416")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.Option2[position].equals("440")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.Option2[position].equals("441")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.Option2[position].equals("442")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.Option2[position].equals("443")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.Option2[position].equals("444")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.Option2[position].equals("445")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.Option2[position].equals("446")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.Option2[position].equals("470")){
                    holder.imageView.setImageResource(R.mipmap.ic_movie_foreground);
                }else if(sqlReturn.Option2[position].equals("471")){
                    holder.imageView.setImageResource(R.mipmap.ic_workout_foreground);
                }else if(sqlReturn.Option2[position].equals("472")){
                    holder.imageView.setImageResource(R.mipmap.ic_listen_foreground);
                }else if(sqlReturn.Option2[position].equals("473")){
                    holder.imageView.setImageResource(R.mipmap.ic_sing_foreground);
                }else if(sqlReturn.Option2[position].equals("474")){
                    holder.imageView.setImageResource(R.mipmap.ic_watchdrama_foreground);
                }else if(sqlReturn.Option2[position].equals("475")){
                    holder.imageView.setImageResource(R.mipmap.ic_broadgame_foreground);
                }else if(sqlReturn.Option2[position].equals("476")){
                    holder.imageView.setImageResource(R.mipmap.ic_videogame_foreground);
                }else if(sqlReturn.Option2[position].equals("499")){
                    holder.imageView.setImageResource(R.mipmap.ic_what_date_foreground);
                }else if(sqlReturn.Option2[position].equals("500")){
                    holder.imageView.setImageResource(R.mipmap.ic_movielover_foreground);
                }else if(sqlReturn.Option2[position].equals("501")){
                    holder.imageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
                }else if(sqlReturn.Option2[position].equals("502")){
                    holder.imageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
                }else if(sqlReturn.Option2[position].equals("503")){
                    holder.imageView.setImageResource(R.mipmap.ic_singlover_foreground);
                }else if(sqlReturn.Option2[position].equals("504")){
                    holder.imageView.setImageResource(R.mipmap.ic_shoplover_foreground);
                }else if(sqlReturn.Option2[position].equals("525")){
                    holder.imageView.setImageResource(R.mipmap.ic_what_date_foreground);
                }else if(sqlReturn.Option2[position].equals("526")){
                    holder.imageView.setImageResource(R.mipmap.ic_movielover_foreground);
                }else if(sqlReturn.Option2[position].equals("527")){
                    holder.imageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
                }else if(sqlReturn.Option2[position].equals("528")){
                    holder.imageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
                }else if(sqlReturn.Option2[position].equals("529")){
                    holder.imageView.setImageResource(R.mipmap.ic_singlover_foreground);
                }else if(sqlReturn.Option2[position].equals("530")){
                    holder.imageView.setImageResource(R.mipmap.ic_shoplover_foreground);
                }else if(sqlReturn.Option2[position].equals("551")){
                    holder.imageView.setImageResource(R.mipmap.ic_what_date_foreground);
                }else if(sqlReturn.Option2[position].equals("552")){
                    holder.imageView.setImageResource(R.mipmap.ic_movielover_foreground);
                }else if(sqlReturn.Option2[position].equals("553")){
                    holder.imageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
                }else if(sqlReturn.Option2[position].equals("554")){
                    holder.imageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
                }else if(sqlReturn.Option2[position].equals("555")){
                    holder.imageView.setImageResource(R.mipmap.ic_singlover_foreground);
                }else if(sqlReturn.Option2[position].equals("556")){
                    holder.imageView.setImageResource(R.mipmap.ic_shoplover_foreground);
                }else if(sqlReturn.Option2[position].equals("577")){
                    holder.imageView.setImageResource(R.mipmap.ic_what_date_foreground);
                }else if(sqlReturn.Option2[position].equals("578")){
                    holder.imageView.setImageResource(R.mipmap.ic_movielover_foreground);
                }else if(sqlReturn.Option2[position].equals("579")){
                    holder.imageView.setImageResource(R.mipmap.ic_dinnerlover_foreground);
                }else if(sqlReturn.Option2[position].equals("580")){
                    holder.imageView.setImageResource(R.mipmap.ic_workoutlover_foreground);
                }else if(sqlReturn.Option2[position].equals("581")){
                    holder.imageView.setImageResource(R.mipmap.ic_singlover_foreground);
                }else if(sqlReturn.Option2[position].equals("582")){
                    holder.imageView.setImageResource(R.mipmap.ic_shoplover_foreground);
                }else if(sqlReturn.Option2[position].equals("603")){
                    holder.imageView.setImageResource(R.mipmap.ic_quarrellover_foreground);
                }else if(sqlReturn.Option2[position].equals("604")){
                    holder.imageView.setImageResource(R.mipmap.ic_coldwar_foreground);
                }else if(sqlReturn.Option2[position].equals("605")){
                    holder.imageView.setImageResource(R.mipmap.ic_fighting_foreground);
                }else if(sqlReturn.Option2[position].equals("606")){
                    holder.imageView.setImageResource(R.mipmap.ic_breakup_foreground);
                }else if(sqlReturn.Option2[position].equals("607")){
                    holder.imageView.setImageResource(R.mipmap.ic_arguelover_foreground);
                }else if(sqlReturn.Option2[position].equals("608")){
                    holder.imageView.setImageResource(R.mipmap.ic_complain_foreground);
                }
            }else if(sqlReturn.model == 4){
                holder.imageView.setImageResource(R.drawable.handwrite);
            }

            holder.textTitle.setText(data.get(position).get("textTitle"));
            holder.textDescription.setText(data.get(position).get("textDescription"));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    // 此為抓Mood
    private String searchMood;
    public void searchByMood(){
        if(buttonTest.getText().equals("晴天")){
            searchMood = "心情1";
        }else if(buttonTest.getText().equals("時晴")){
            searchMood = "心情2";
        }else if(buttonTest.getText().equals("多雲")){
            searchMood = "心情3";
        }else if(buttonTest.getText().equals("陣雨")){
            searchMood = "心情4";
        }else if(buttonTest.getText().equals("雷雨")){
            searchMood = "心情5";
        }
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "historyMood");
        map.put("uid", uid);
        map.put("searchMood",searchMood);
        new searchMoodGo(super.getActivity()).execute((HashMap)map);
    }
    private class searchMoodGo extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        searchMoodGo(Activity context){
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
                if(status){
                    sqlReturn.textViewContext1 = jsonObject.getString("results");
                    sqlReturn.SearchCountMood = jsonObject.getInt("rowcount");
                    jsonArray = new JSONArray(sqlReturn.textViewContext1);
                    sqlReturn.content1 = new String[sqlReturn.SearchCountMood];
                    sqlReturn.tagName1 = new String[sqlReturn.SearchCountMood];
                    sqlReturn.mood1 = new String[sqlReturn.SearchCountMood];
                    sqlReturn.date1 = new String[sqlReturn.SearchCountMood];
                    sqlReturn.Option1 = new String[sqlReturn.SearchCountMood];
                    sqlReturn.DiaryID1 = new String[sqlReturn.SearchCountMood];
                    for(int i = 0; i<sqlReturn.SearchCountMood; i++){
                        JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                        sqlReturn.content1[i] = obj.getString("content");
                        sqlReturn.tagName1[i] = obj.getString("tagName");
                        if(obj.getString("mood").equals("心情1")){
                            sqlReturn.mood1[i] = "晴天";
                        }else if(obj.getString("mood").equals("心情2")){
                            sqlReturn.mood1[i] = "時晴";
                        }else if(obj.getString("mood").equals("心情3")){
                            sqlReturn.mood1[i] = "多雲";
                        }else if(obj.getString("mood").equals("心情4")){
                            sqlReturn.mood1[i] = "陣雨";
                        }else if(obj.getString("mood").equals("心情5")){
                            sqlReturn.mood1[i] = "雷雨";
                        }else if(obj.getString("mood").equals("手寫日記心情")){
                            sqlReturn.mood1[i] = "手寫日記";
                        }
                        sqlReturn.date1[i] = obj.getString("date");
                        sqlReturn.Option1[i] = obj.getString("optionNo");
                        sqlReturn.DiaryID1[i] = obj.getString("diaryNo");
                    }
                }else {
                    sqlReturn.textViewContext1 = "";
                    sqlReturn.SearchCountMood = 0;
                    sqlReturn.content1 = new String[sqlReturn.SearchCountMood];
                    sqlReturn.tagName1 = new String[sqlReturn.SearchCountMood];
                    sqlReturn.mood1 = new String[sqlReturn.SearchCountMood];
                    sqlReturn.date1 = new String[sqlReturn.SearchCountMood];
                    sqlReturn.Option1 = new String[sqlReturn.SearchCountMood];
                    sqlReturn.DiaryID1 = new String[sqlReturn.SearchCountMood];
                    for(int i = 0; i<sqlReturn.SearchCountMood; i++){
                        sqlReturn.content1[i] = "";
                        sqlReturn.tagName1[i] = "";
                        sqlReturn.mood1[i] = "";
                        sqlReturn.date1[i] = "";
                        sqlReturn.Option1[i] = "";
                        sqlReturn.DiaryID1[i] = "";
                    }
                    new AlertDialog.Builder(activity)
                            .setTitle("提醒")
                            .setMessage("您沒有撰寫過心情為 ("+buttonTest.getText()+") 的日記")
                            .setPositiveButton("OK", null)
                            .show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (sqlReturn.textViewContext1!=null){
                //Toast.makeText(activity, String.valueOf(sqlReturn.SearchCountMood), Toast.LENGTH_LONG).show();
                doDataSearchMood();
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(HomeFragment.super.getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                myAdapter = new MyAdapter();
                mRecyclerView.setAdapter(myAdapter);
                progressBarHome.setVisibility(View.INVISIBLE);
                RefreshLayoutHome.setRefreshing(false);
            }else {
                progressBarHome.setVisibility(View.INVISIBLE);
            }
        }

    }
    private void doDataSearchMood(){
        data = new LinkedList<>();
        final int SearchCountMood = sqlReturn.SearchCountMood;
        for(int i = 0; i < SearchCountMood; i++){
            HashMap<String,String> row = new HashMap<>();
            row.put("textTitle",sqlReturn.mood1[i]);
            row.put("textDescription",sqlReturn.date1[i]);
            data.add(row);
            sqlReturn.model = 2;
        }
    }


    // 此為抓Tag
    public String searchTag;
    public void searchByTag(){

        if(buttonTest.getText().equals("美食")){
            searchTag = "美食";
        }else if(buttonTest.getText().equals("購物")){
            searchTag = "購物";
        }
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "historyTag");
        map.put("uid", uid);
        map.put("searchTag",searchTag);
        new searchTagGo(super.getActivity()).execute((HashMap)map);
    }


    private class searchTagGo extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        searchTagGo(Activity context){
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

                sqlReturn.textViewContext2 = jsonObject.getString("results");
                sqlReturn.SearchCountTag = jsonObject.getInt("rowcount");
                jsonArray = new JSONArray(sqlReturn.textViewContext2);
                sqlReturn.content2 = new String[sqlReturn.SearchCountTag];
                sqlReturn.tagName2 = new String[sqlReturn.SearchCountTag];
                sqlReturn.mood2 = new String[sqlReturn.SearchCountTag];
                sqlReturn.date2 = new String[sqlReturn.SearchCountTag];
                sqlReturn.Option2 = new String[sqlReturn.SearchCountTag];
                sqlReturn.DiaryID2 = new String[sqlReturn.SearchCountTag];
                for(int i = 0; i<sqlReturn.SearchCountTag; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.content2[i] = obj.getString("content");
                    sqlReturn.tagName2[i] = obj.getString("tagName");
                    sqlReturn.mood2[i] = obj.getString("mood");
                    sqlReturn.date2[i] = obj.getString("date");
                    sqlReturn.Option2[i] = obj.getString("optionNo");
                    sqlReturn.DiaryID2[i] = obj.getString("diaryNo");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (sqlReturn.textViewContext2!=null){
                //Toast.makeText(activity, String.valueOf(sqlReturn.SearchCountTag), Toast.LENGTH_LONG).show();
                doDataSearchTag();
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(HomeFragment.super.getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                myAdapter = new MyAdapter();
                mRecyclerView.setAdapter(myAdapter);
                progressBarHome.setVisibility(View.INVISIBLE);
                RefreshLayoutHome.setRefreshing(false);
            }
        }

    }
    private void doDataSearchTag(){
        data = new LinkedList<>();
        for(int i = 0; i < sqlReturn.SearchCountTag; i++){
            HashMap<String,String> row = new HashMap<>();
            row.put("textTitle",sqlReturn.tagName2[i]);
            row.put("textDescription",sqlReturn.date2[i]);
            data.add(row);
            sqlReturn.model = 3;
        }
    }


    // 此為抓手寫日記

    public void SearchHandWrite(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "historyHandWrite");
        map.put("uid", uid);
        map.put("searchHandWrite","手寫日記心情");
        new SearchHandWrite(super.getActivity()).execute((HashMap)map);
    }
    private class SearchHandWrite extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        SearchHandWrite(Activity context){
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
                if(status){
                    sqlReturn.textViewContext3 = jsonObject.getString("results");
                    sqlReturn.SearchCountHandWrite = jsonObject.getInt("rowcount");
                    jsonArray = new JSONArray(sqlReturn.textViewContext3);
                    sqlReturn.content3 = new String[sqlReturn.SearchCountHandWrite];
                    sqlReturn.tagName3 = new String[sqlReturn.SearchCountHandWrite];
                    sqlReturn.mood3 = new String[sqlReturn.SearchCountHandWrite];
                    sqlReturn.date3 = new String[sqlReturn.SearchCountHandWrite];
                    sqlReturn.Option3 = new String[sqlReturn.SearchCountHandWrite];
                    sqlReturn.DiaryID3 = new String[sqlReturn.SearchCountHandWrite];
                    for(int i = 0; i<sqlReturn.SearchCountHandWrite; i++){
                        JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                        sqlReturn.content3[i] = obj.getString("content");
                        sqlReturn.tagName3[i] = obj.getString("tagName");
                        if(obj.getString("mood").equals("手寫日記心情")){
                            sqlReturn.mood3[i] = "手寫日記";
                        }
                        sqlReturn.date3[i] = obj.getString("date");
                        sqlReturn.Option3[i] = obj.getString("optionNo");
                        sqlReturn.DiaryID3[i] = obj.getString("diaryNo");
                    }
                }else {
                    sqlReturn.textViewContext3 = "";
                    sqlReturn.SearchCountHandWrite = 0;
                    sqlReturn.content3 = new String[sqlReturn.SearchCountHandWrite];
                    sqlReturn.tagName3 = new String[sqlReturn.SearchCountHandWrite];
                    sqlReturn.mood3 = new String[sqlReturn.SearchCountHandWrite];
                    sqlReturn.date3 = new String[sqlReturn.SearchCountHandWrite];
                    sqlReturn.Option3 = new String[sqlReturn.SearchCountHandWrite];
                    sqlReturn.DiaryID3 = new String[sqlReturn.SearchCountHandWrite];
                    for(int i = 0; i<sqlReturn.SearchCountHandWrite; i++){
                        sqlReturn.content3[i] = "";
                        sqlReturn.tagName3[i] = "";
                        sqlReturn.mood3[i] = "";
                        sqlReturn.date3[i] = "";
                        sqlReturn.Option3[i] = "";
                        sqlReturn.DiaryID3[i] = "";
                    }
                    new AlertDialog.Builder(activity)
                            .setTitle("提醒")
                            .setMessage("您沒有撰寫過手寫日記")
                            .setPositiveButton("OK", null)
                            .show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (sqlReturn.textViewContext3!=null){
                //Toast.makeText(activity, String.valueOf(sqlReturn.SearchCountMood), Toast.LENGTH_LONG).show();
                doDataSearchHandWrite();
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(HomeFragment.super.getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                myAdapter = new MyAdapter();
                mRecyclerView.setAdapter(myAdapter);
                progressBarHome.setVisibility(View.INVISIBLE);
                RefreshLayoutHome.setRefreshing(false);
            }else {
                progressBarHome.setVisibility(View.INVISIBLE);
            }
        }

    }
    private void doDataSearchHandWrite(){
        data = new LinkedList<>();
        final int SearchCountHandWrite = sqlReturn.SearchCountHandWrite;
        for(int i = 0; i < SearchCountHandWrite; i++){
            HashMap<String,String> row = new HashMap<>();
            row.put("textTitle",sqlReturn.mood3[i]);
            row.put("textDescription",sqlReturn.date3[i]);
            data.add(row);
            sqlReturn.model = 4;
        }
    }


    // 變化背景動畫
    private View.OnClickListener btnChangeColorOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            int iBackColorRedVal, iBackColorRedEnd;

            if(!changeBtn){
                btnAnim.animate().rotation(btnAnim.getRotation()+135).start();
                mLayout.setVisibility(View.VISIBLE);
                goToHandwritebutton.setEnabled(true);
                goToHandwritebutton.setVisibility(View.VISIBLE);
                goToDiarybutton.setEnabled(true);
                goToDiarybutton.setVisibility(View.VISIBLE);
                goToOCRbutton.setEnabled(true);
                goToOCRbutton.setVisibility(View.VISIBLE);
                changeBtn = true;
            }else if(changeBtn){
                btnAnim.animate().rotation(btnAnim.getRotation()-135).start();
                goToHandwritebutton.setEnabled(false);
                goToHandwritebutton.setVisibility(View.INVISIBLE);
                goToDiarybutton.setEnabled(false);
                goToDiarybutton.setVisibility(View.INVISIBLE);
                goToOCRbutton.setEnabled(false);
                goToOCRbutton.setVisibility(View.INVISIBLE);
                changeBtn = false;
            }
            final int iBackColor =
                    ((ColorDrawable)(mLayout.getBackground())).getColor();
            iBackColorRedVal = (iBackColor & 0xFF);

            if (iBackColorRedVal > 127)
                iBackColorRedEnd = 0;
            else
                iBackColorRedEnd = 255;
            ValueAnimator animScreenBackColor =
                    ValueAnimator.ofInt(iBackColorRedVal, iBackColorRedEnd);
            animScreenBackColor.setDuration(500);
            animScreenBackColor.setInterpolator(new LinearInterpolator());
            animScreenBackColor.start();
            animScreenBackColor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int val = (Integer)animation.getAnimatedValue();
                    mLayout.setBackgroundColor(
                            iBackColor & 0x33000000 | val << 16 | val << 8 | val );
                }
            });
        }
    };

    //----------------------------------------------------------------------------------------------

}
