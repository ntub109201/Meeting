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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
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
    public static int data1_list,data2_list;
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
        test01.setText("");

        final Button btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendListActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",2);
                FriendListActivity.this.startActivity(intent);
                finish();
            }
        });

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
        addFriend();
        RefreshLayoutFriendList1 = findViewById(R.id.RefreshLayoutFriendList1);
        RefreshLayoutFriendList1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addFriend();
            }
        });

        RecyclerView2 = findViewById(R.id.recyclerView2);
        RecyclerView2.setHasFixedSize(true);
        LayoutManager2 = new GridLayoutManager(this,3);
        RecyclerView2.setLayoutManager(LayoutManager2);
        myAdapter2 = new MyAdapter2();
        RecyclerView2.setAdapter(myAdapter2);
        doData2();
        searchFriendList();
        RefreshLayoutFriendList2 = findViewById(R.id.RefreshLayoutFriendList2);
        RefreshLayoutFriendList2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchFriendList();
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
                row.put("textName",sqlReturn.add_friendName[i]);
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
                textName = itemView.findViewById(R.id.textName);
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
                                    sqlReturn.add_friendCount -=1;
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
                                    sqlReturn.add_friendCount -=1;
                                    friendConfirmBack("n");
                                }
                            }).setNegativeButton("取消",null).create().show();
                }
            });
            holder.textName.setText(data1.get(position).get("textName"));
        }

        @Override
        public int getItemCount() {
            return data1.size();
        }
    }


    private void doData2(){

        data2 = new LinkedList<>();
        if(sqlReturn.SearchCountFriendList != 0){
            imageDown1.setVisibility(View.INVISIBLE);
            imageDown2.setVisibility(View.INVISIBLE);
            data2_list = sqlReturn.SearchCountFriendList;
            for(int i = 0; i< data2_list;i++){
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
                    holder.roundedImageView.setImageBitmap(result);
                    super.onPostExecute(result);
                }
            }.execute(sqlReturn.friendListPersonImage[position]);
        }

        @Override
        public int getItemCount() {
            return data2.size();
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
                sqlReturn.friendListNum = new String[sqlReturn.SearchCountFriendList];
                sqlReturn.friendListName = new String[sqlReturn.SearchCountFriendList];
                sqlReturn.friendListBFF = new String[sqlReturn.SearchCountFriendList];
                sqlReturn.friendListPersonImage = new String[sqlReturn.SearchCountFriendList];
                for(int i = 0; i<sqlReturn.SearchCountFriendList; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.friendListNum[i] = obj.getString("friendNum");
                    sqlReturn.friendListName[i] = obj.getString("friendName01");
                    sqlReturn.friendListBFF[i] = obj.getString("BFF");
                    sqlReturn.friendListPersonImage[i] = obj.getString("userPicture");
                }
                doData2();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RefreshLayoutFriendList1.setRefreshing(false);
        }
    }

    public void friendSearch(){
        String uid = sqlReturn.GetUserID;
        String friendName = sqlReturn.friendListName[position2];
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendSearch");
        map.put("uid", uid);
        map.put("searchFriend",friendName);
        map.put("bff",sqlReturn.friendListBFF[position2]);
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
                sqlReturn.friendSearchNum = new String[sqlReturn.friendSearchCount];
                sqlReturn.friendSearchContent = new String[sqlReturn.friendSearchCount];
                sqlReturn.friendSearchMood = new String[sqlReturn.friendSearchCount];
                sqlReturn.friendSearchTagName = new String[sqlReturn.friendSearchCount];
                sqlReturn.friendSearchDate = new String[sqlReturn.friendSearchCount];
                sqlReturn.friendSearchName = new String[sqlReturn.friendSearchCount];
                sqlReturn.friendSearchImage = new String[sqlReturn.friendSearchCount];
                sqlReturn.friendSearchPersonImage = new String[sqlReturn.friendSearchCount];
                for(int i = 0; i<sqlReturn.friendSearchCount; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.friendSearchNum[i] = obj.getString("friendNum");
                    sqlReturn.friendSearchContent[i] = obj.getString("content");
                    sqlReturn.friendSearchMood[i] = obj.getString("mood");
                    sqlReturn.friendSearchTagName[i] = obj.getString("tagName");
                    sqlReturn.friendSearchDate[i] = obj.getString("date");
                    sqlReturn.friendSearchName[i] = obj.getString("friendName01");
                    sqlReturn.friendSearchImage[i] = obj.getString("image_path");
                    sqlReturn.friendSearchPersonImage[i] = obj.getString("userPicture");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                Intent intent = new Intent(FriendListActivity.this,SingleFriendListActivity.class);
                intent.putExtra("position",FriendListActivity.position2);
                startActivity(intent);
                RefreshLayoutFriendList2.setRefreshing(false);
            }else {
                for(int i = 0; i<sqlReturn.friendSearchCount; i++){
                    sqlReturn.friendSearchNum[i] = "";
                    sqlReturn.friendSearchContent[i] = "";
                    sqlReturn.friendSearchMood[i] = "";
                    sqlReturn.friendSearchTagName[i] = "";
                    sqlReturn.friendSearchDate[i] = "";
                    sqlReturn.friendSearchName[i] = "";
                    sqlReturn.friendSearchImage[i] = "";
                    sqlReturn.friendSearchPersonImage[i] = "";
                }
                sqlReturn.friendSearchCount = 0;
                sqlReturn.friendSearchNum = new String[1];
                sqlReturn.friendSearchNum[0] = sqlReturn.friendListNum[position2];
                Intent intent = new Intent(FriendListActivity.this,SingleFriendListActivity.class);
                startActivity(intent);
                RefreshLayoutFriendList2.setRefreshing(false);
            }
        }
    }

    public void friendConfirmBack(String yesNo){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendConfirmBack");
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
                addFriend();
                searchFriendList();
                new AlertDialog.Builder(FriendListActivity.this)
                        .setCancelable(false)
                        .setTitle("恭喜您")
                        .setMessage("兩位已經是好友囉，可以欣賞好友分享的日記了!!")
                        .setPositiveButton("好", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(FriendListActivity.this,MainActivity.class);
                                intent.putExtra("id",2);
                                startActivity(intent);
                            }
                        }).show();
            }else {
                new AlertDialog.Builder(activity)
                        .setTitle("提醒您")
                        .setMessage("請檢察網路是否連通")
                        .setPositiveButton("好", null)
                        .show();
            }
        }
    }


    // 取待加入好友
    public void addFriend(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendConfirm");
        map.put("uid", uid);
        new addFriend(this).execute((HashMap)map);
    }
    private class addFriend extends HttpURLConnection_AsyncTask {
        // 建立弱連結
        WeakReference<Activity> activityReference;
        addFriend(Activity context){
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
                    sqlReturn.textAdd_friend = jsonObject.getString("results");
                    sqlReturn.add_friendCount = jsonObject.getInt("rowcount");
                    data1_list = sqlReturn.add_friendCount;
                    jsonArray = new JSONArray(sqlReturn.textAdd_friend);
                    sqlReturn.add_friendNum = new String[sqlReturn.add_friendCount];
                    sqlReturn.add_friendName = new String[sqlReturn.add_friendCount];
                    sqlReturn.add_friendBFF = new String[sqlReturn.add_friendCount];
                    for (int i = 0; i<sqlReturn.add_friendCount; i++){
                        JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                        sqlReturn.add_friendNum[i] = obj.getString("friendNum");
                        sqlReturn.add_friendName[i] = obj.getString("friendName01");
                        sqlReturn.add_friendBFF[i] = obj.getString("BFF");
                    }
                    doData1();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent intent = new Intent(FriendListActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("id",2);
            FriendListActivity.this.startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
