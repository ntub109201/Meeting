package com.example.myapplication2.ui.friend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
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

public class SingleFriendListActivity extends AppCompatActivity {

    private RecyclerView RecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager LayoutManager;
    private LinkedList<HashMap<String,String>> data;
    private MyAdapter myAdapter;
    private SwipeRefreshLayout RefreshLayoutFriendList;
    private TextView textName;
    public static int position;
    private ImageButton imbtnReturnToFriendList;
    private Button deleteFriend;
    private RoundedImageView roundedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_friend_list);

        textName = findViewById(R.id.textName);

        textName.setText(sqlReturn.friendListName[FriendListActivity.position2]);
        roundedImageView = findViewById(R.id.roundedImageView);
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
                roundedImageView.setImageBitmap(result);
                super.onPostExecute(result);
            }
        }.execute(sqlReturn.friendListPersonImage[FriendListActivity.position2]);

        imbtnReturnToFriendList = findViewById(R.id.imbtnReturnToFriendList);
        imbtnReturnToFriendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleFriendListActivity.this.finish();
            }
        });

        RecyclerView = findViewById(R.id.RecyclerView);
        RecyclerView.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        RecyclerView.setLayoutManager(LayoutManager);
        myAdapter = new MyAdapter();
        RecyclerView.setAdapter(myAdapter);
        doData();
        friendSearch();
        RefreshLayoutFriendList = findViewById(R.id.RefreshLayoutFriend);
        RefreshLayoutFriendList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                friendSearch();
            }
        });

        deleteFriend = findViewById(R.id.deleteFriend);
        deleteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SingleFriendListActivity.this)
                        .setTitle("提醒")
                        .setMessage("您確定要刪除他嗎?")
                        .setPositiveButton("確定",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFriend();
                            }
                        }).setNegativeButton("取消",null).create().show();

            }
        });

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

    private void doData(){

        data = new LinkedList<>();
        for(int i = 0; i< sqlReturn.friendSearchCount; i++){
            HashMap<String, String> row = new HashMap<>();
            row.put("date", sqlReturn.friendSearchDate[i]);
            row.put("tag", sqlReturn.friendSearchTagName[i]);
            data.add(row);
        }

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public TextView date,tag;
            public ImageView photo_image;
            public ProgressBar progressBar;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                date = itemView.findViewById(R.id.date);
                tag = itemView.findViewById(R.id.tag);
                progressBar = itemView.findViewById(R.id.progressBar);
                photo_image = itemView.findViewById(R.id.photo_image);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position = getAdapterPosition();
                        Intent intent = new Intent(SingleFriendListActivity.this,SingleFriendActivity.class);
                        intent.putExtra("uri",sqlReturn.friendSearchImage[position]);
                        intent.putExtra("personUri",sqlReturn.friendSearchPersonImage[position]);
                        startActivity(intent);
                    }
                });
            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.single_friendlist_item,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.date.setText(data.get(position).get("date"));
            holder.tag.setText(data.get(position).get("tag"));
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
                    holder.progressBar.setVisibility(View.INVISIBLE);
                    holder.photo_image.setImageBitmap(result);
                    super.onPostExecute(result);
                }
            }.execute(sqlReturn.friendSearchImage[position]);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    public void deleteFriend(){
        int position = FriendListActivity.position2;
        Map<String,String> map = new HashMap<>();
        map.put("command", "deleteFriend");
        map.put("uid",sqlReturn.GetUserID);
        map.put("friendNum",sqlReturn.friendListNum[position]);
        new deleteFriend(this).execute((HashMap)map);
    }

    private class deleteFriend extends HttpURLConnection_AsyncTask {
        // 建立弱連結
        WeakReference<Activity> activityReference;
        deleteFriend(Activity context){
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

            }catch (JSONException e){
                e.printStackTrace();
            }

            if(status == true){
                sqlReturn.SearchCountFriendList = 0;
                sqlReturn.SearchCountFriend = 0;
                friendList();

            }else{
                new AlertDialog.Builder(activity)
                        .setTitle("刪除好友失敗")
                        .setMessage("伺服器擁擠中")
                        .setPositiveButton("OK", null)
                        .show();
                Log.d("222","error");
            }
        }
    }
    public void friendSearch(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendSearch");
        map.put("uid", uid);
        map.put("searchFriend",textName.getText().toString());
        map.put("bff",sqlReturn.friendListBFF[FriendListActivity.position2]);
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
                RefreshLayoutFriendList.setRefreshing(false);
            }else{
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
                RefreshLayoutFriendList.setRefreshing(false);
            }
        }
    }

    // 此為社群好友貼文全抓
    public void friendList(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendList");
        map.put("uid", uid);
        new friendList(this).execute((HashMap)map);
    }
    private class friendList extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        friendList(Activity context){
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
                sqlReturn.SearchCountFriend = jsonObject.getInt("rowcount");
                sqlReturn.textViewContextFriend = jsonObject.getString("results");
                jsonArray = new JSONArray(sqlReturn.textViewContextFriend);
                sqlReturn.contentFriend = new String[sqlReturn.SearchCountFriend];
                sqlReturn.tagNameFriend = new String[sqlReturn.SearchCountFriend];
                sqlReturn.moodFriend = new String[sqlReturn.SearchCountFriend];
                sqlReturn.dateFriend = new String[sqlReturn.SearchCountFriend];
                sqlReturn.friendName = new String[sqlReturn.SearchCountFriend];
                sqlReturn.friendBFF = new String[sqlReturn.SearchCountFriend];
                for(int i = 0; i<sqlReturn.SearchCountFriend; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.contentFriend[i] = obj.getString("content");
                    sqlReturn.tagNameFriend[i] = obj.getString("tagName");
                    sqlReturn.moodFriend[i] = obj.getString("mood");
                    sqlReturn.dateFriend[i] = obj.getString("date");
                    sqlReturn.friendName[i] = obj.getString("friendName01");
                    sqlReturn.friendBFF[i] = obj.getString("BFF");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            friendInfoList();
        }
    }

    public void friendInfoList(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendInfoList");
        map.put("uid", uid);
        new friendInfoList(this).execute((HashMap)map);
    }

    private class friendInfoList extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        friendInfoList(Activity context){
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
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(SingleFriendListActivity.this, MainActivity.class);
            intent.putExtra("id",2);
            startActivity(intent);
        }
    }

    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            SingleFriendListActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
