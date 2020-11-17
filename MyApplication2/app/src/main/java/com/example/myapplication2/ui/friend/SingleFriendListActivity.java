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
import android.widget.TextView;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_friend_list);

        textName = findViewById(R.id.textName);
        textName.setText(sqlReturn.friendSearchName[0]);

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
        RefreshLayoutFriendList = findViewById(R.id.RefreshLayoutFriend);
        RefreshLayoutFriendList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doData();
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
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                date = itemView.findViewById(R.id.date);
                tag = itemView.findViewById(R.id.tag);
                photo_image = itemView.findViewById(R.id.photo_image);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position = getAdapterPosition();
                        Intent intent = new Intent(SingleFriendListActivity.this,SingleFriendActivity.class);
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
            if(position % 3 == 0){
                holder.photo_image.setImageResource(R.drawable.images);
            }else if(position % 3 == 1){
                holder.photo_image.setImageResource(R.drawable.image2);
            }else {
                holder.photo_image.setImageResource(R.mipmap.ic_wallpaper_foreground);
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

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

    public void deleteFriend(){
        Map<String,String> map = new HashMap<>();
        map.put("command", "deleteFriend");
        map.put("uid",sqlReturn.GetUserID);
        map.put("friendNum",sqlReturn.friendSearchNum[0]);
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
                Intent intent = new Intent(SingleFriendListActivity.this,FriendListActivity.class);
                startActivity(intent);
                SingleFriendListActivity.this.finish();
            }else{
                new AlertDialog.Builder(activity)
                        .setTitle("伺服器擁擠中")
                        .setMessage("信箱登入失敗")
                        .setPositiveButton("OK", null)
                        .show();
                Log.d("222","error");
            }
        }
    }
}
