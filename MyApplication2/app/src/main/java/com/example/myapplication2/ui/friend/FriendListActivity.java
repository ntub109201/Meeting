package com.example.myapplication2.ui.friend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.Login.LoginActivity;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FriendListActivity extends AppCompatActivity {

    private RecyclerView RecyclerView1, RecyclerView2;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager LayoutManager1 ,LayoutManager2;
    private LinkedList<HashMap<String,String>> data1, data2;
    private MyAdapter1 myAdapter1;
    private MyAdapter2 myAdapter2;
    private SwipeRefreshLayout RefreshLayoutFriendList1, RefreshLayoutFriendList2;
    public static int data1_list;
    public static int position1;
    public static int position2;
    private ImageView imgNoFriend1,imgNoFriend2,imageDown1,imageDown2;
    private TextView test01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        imgNoFriend1 = findViewById(R.id.imgNoFriend1);
        imgNoFriend2 = findViewById(R.id.imgNoFriend2);
        imageDown1 = findViewById(R.id.imageDown1);
        imageDown2 = findViewById(R.id.imageDown2);
        test01 = findViewById(R.id.test01);
        test01.setText(String.valueOf(sqlReturn.add_friendCount));

        final Button btn_friendlist = findViewById(R.id.btn_friendlist);
        btn_friendlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendListActivity.this,BestFriendActivity.class);
                startActivity(intent);
            }
        });


        final Button btn_friendsearch = findViewById(R.id.btn_friendsearch);
        btn_friendsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendListActivity.this,SearchFriendActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView1 = findViewById(R.id.recyclerView1);
        RecyclerView1.setHasFixedSize(true);
        LayoutManager1 = new LinearLayoutManager(this);
        RecyclerView1.setLayoutManager(LayoutManager1);
        myAdapter1 = new MyAdapter1();
        RecyclerView1.setAdapter(myAdapter1);
        doData1();
        RefreshLayoutFriendList1 = findViewById(R.id.RefreshLayoutFriendList1);
        RefreshLayoutFriendList1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doData1();
                RefreshLayoutFriendList1.setRefreshing(false);
            }
        });

        RecyclerView2 = findViewById(R.id.recyclerView2);
        RecyclerView2.setHasFixedSize(true);
        LayoutManager2 = new GridLayoutManager(this,3);
        RecyclerView2.setLayoutManager(LayoutManager2);
        myAdapter2 = new MyAdapter2();
        RecyclerView2.setAdapter(myAdapter2);
        doData2();
        RefreshLayoutFriendList2 = findViewById(R.id.RefreshLayoutFriendList2);
        RefreshLayoutFriendList2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchFriendList();
                RefreshLayoutFriendList2.setRefreshing(false);
            }
        });

    }

    private void doData1(){
        data1 = new LinkedList<>();
        if(sqlReturn.add_friendCount != 0){
            imgNoFriend1.setVisibility(View.INVISIBLE);
            imgNoFriend2.setVisibility(View.INVISIBLE);
            data1_list = sqlReturn.add_friendCount;
            for(int i = 0; i < data1_list; i++){
                HashMap<String,String> row = new HashMap<>();
                data1.add(row);
            }
        }else {
            imgNoFriend1.setVisibility(View.VISIBLE);
            imgNoFriend2.setVisibility(View.VISIBLE);
        }
    }

    private class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public TextView textName;
            public Button btn_confirm,btn_cancel;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                btn_confirm = itemView.findViewById(R.id.btn_confirm);
                btn_cancel = itemView.findViewById(R.id.btn_cancel);
            }
        }

        @NonNull
        @Override
        public MyAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.friend_list_item1,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter1.MyViewHolder holder, int position) {
            holder.btn_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(FriendListActivity.this)
                            .setTitle("提醒")
                            .setMessage("您確定要加入他為好友嗎?")
                            .setPositiveButton("確定加入",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    position1 = position;
                                    data1_list -=1;
                                    friendConfirmBack("y");
                                }
                            }).setNegativeButton("取消",null).create().show();
                }
            });
            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(FriendListActivity.this)
                            .setTitle("提醒")
                            .setMessage("您確定要拒絕他嗎?")
                            .setPositiveButton("確定拒絕",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    position1 = position;
                                    data1_list -=1;
                                    friendConfirmBack("n");
                                }
                            }).setNegativeButton("取消",null).create().show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data1.size();
        }
    }


    private void doData2(){

        data2 = new LinkedList<>();
        if(sqlReturn.SearchCountFriendList > 0){
            imageDown1.setVisibility(View.INVISIBLE);
            imageDown2.setVisibility(View.INVISIBLE);
            for(int i = 0; i< sqlReturn.SearchCountFriendList;i++){
                HashMap<String, String> row = new HashMap<>();
                row.put("textName", sqlReturn.friendListName[i]);
                data2.add(row);
            }
        }else{
            imageDown1.setVisibility(View.VISIBLE);
            imageDown2.setVisibility(View.VISIBLE);
        }

    }

    private class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public TextView textName;
            public RoundedImageView roundedImageView;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                textName = itemView.findViewById(R.id.textName);
                roundedImageView = itemView.findViewById(R.id.roundedImageView);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position2 = getAdapterPosition();
                        friendSearch();
                    }
                });
            }
        }

        @NonNull
        @Override
        public MyAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.friend_list_item,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder holder, int position) {
            holder.textName.setText(data2.get(position).get("textName"));
            holder.roundedImageView.setImageResource(R.mipmap.ic_usericon_foreground);
        }

        @Override
        public int getItemCount() {
            return data2.size();
        }
    }

    public void searchFriendList(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendInfoList");
        map.put("uid", uid);
        new searchFriendList(this).execute((HashMap)map);
    }

    private class searchFriendList extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        searchFriendList(Activity context){
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
                sqlReturn.SearchCountFriendList = jsonObject.getInt("rowcount");
                sqlReturn.textViewContextFriendList = jsonObject.getString("results");
                jsonArray = new JSONArray(sqlReturn.textViewContextFriendList);
                sqlReturn.friendListName = new String[sqlReturn.SearchCountFriendList];
                for(int i = 0; i<sqlReturn.SearchCountFriendList; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.friendListName[i] = obj.getString("friendName01");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (sqlReturn.textViewContextFriendList!=null){
                doData2();
                //Toast.makeText(activity, "成功", Toast.LENGTH_LONG).show();
            }else {
                //Toast.makeText(activity, "失敗", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void friendSearch(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendSearch");
        map.put("uid", uid);
        map.put("searchFriend",sqlReturn.friendListName[position2]);
        new friendSearch(this).execute((HashMap)map);
    }

    private class friendSearch extends HttpURLConnection_AsyncTask{
        // 建立弱連結
        WeakReference<Activity> activityReference;
        friendSearch(Activity context){activityReference = new WeakReference<>(context);}
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
                sqlReturn.friendSearchCount = jsonObject.getInt("rowcount");
                sqlReturn.textViewContextfriendSearch = jsonObject.getString("results");
                jsonArray = new JSONArray(sqlReturn.textViewContextfriendSearch);
                sqlReturn.friendSearchContent = new String[sqlReturn.friendSearchCount];
                sqlReturn.friendSearchMood = new String[sqlReturn.friendSearchCount];
                sqlReturn.friendSearchTagName = new String[sqlReturn.friendSearchCount];
                sqlReturn.friendSearchDate = new String[sqlReturn.friendSearchCount];
                sqlReturn.friendSearchName = new String[sqlReturn.friendSearchCount];
                for(int i = 0; i<sqlReturn.SearchCountFriendList; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.friendSearchContent[i] = obj.getString("content");
                    sqlReturn.friendSearchMood[i] = obj.getString("mood");
                    sqlReturn.friendSearchTagName[i] = obj.getString("tagName");
                    sqlReturn.friendSearchDate[i] = obj.getString("date");
                    sqlReturn.friendSearchName[i] = obj.getString("friendName01");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                Intent intent = new Intent(FriendListActivity.this,SingleFriendListActivity.class);
                startActivity(intent);
            }else {
                new AlertDialog.Builder(activity)
                        .setTitle("提醒您")
                        .setMessage("好友"+sqlReturn.friendListName[position2]+"尚未新增日記")
                        .setPositiveButton("了解", null)
                        .show();
            }
        }
    }

    public void friendConfirmBack(String yesNo){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendSearch");
        map.put("uid", uid);
        map.put("friendNum",sqlReturn.add_friendNum[position1]);
        map.put("yesNo",yesNo);
        new friendConfirmBack(this).execute((HashMap)map);
    }

    private class friendConfirmBack extends HttpURLConnection_AsyncTask{
        // 建立弱連結
        WeakReference<Activity> activityReference;
        friendConfirmBack(Activity context){activityReference = new WeakReference<>(context);}
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                Intent intent = new Intent(FriendListActivity.this,FriendListActivity.class);
                startActivity(intent);
                FriendListActivity.this.finish();
            }else {
                new AlertDialog.Builder(activity)
                        .setTitle("提醒您")
                        .setMessage("請檢察網路是否連通")
                        .setPositiveButton("好", null)
                        .show();
            }
        }
    }


}
