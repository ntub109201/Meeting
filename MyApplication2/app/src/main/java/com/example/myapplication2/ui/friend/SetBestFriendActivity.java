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
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SetBestFriendActivity extends AppCompatActivity {

    private RecyclerView RecyclerView1;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager LayoutManager1;
    private LinkedList<HashMap<String,String>> data1;
    private SetBestFriendActivity.MyAdapter1 myAdapter1;
    private SwipeRefreshLayout RefreshLayoutSetBestFriendList;
    private Button btn_confirmBestFriend;
    private int[] btn_check;
    private String friendNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_best_friend);

        final Button btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetBestFriendActivity.this,BestFriendActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                SetBestFriendActivity.this.startActivity(intent);
            }
        });

        btn_check = new int[sqlReturn.SearchCountFriendList];

        btn_confirmBestFriend = findViewById(R.id.btn_confirmBestFriend);
        btn_confirmBestFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SetBestFriendActivity.this)
                        .setTitle("提醒")
                        .setMessage("您確定要將他們設為摯友嗎?")
                        .setPositiveButton("確定",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for(int i = 0; i < sqlReturn.SearchCountFriendList; i++) {
                                    if (btn_check[i] == 1){
                                        friendNum = sqlReturn.friendListNum[i];
                                        addBestFriend();
                                    }
                                }
                            }
                        }).setNegativeButton("取消",null).create().show();
            }
        });

        RecyclerView1 = findViewById(R.id.recyclerView1);
        RecyclerView1.setHasFixedSize(true);
        LayoutManager1 = new LinearLayoutManager(this);
        RecyclerView1.setLayoutManager(LayoutManager1);
        myAdapter1 = new MyAdapter1();
        RecyclerView1.setAdapter(myAdapter1);
        doData1();
        RefreshLayoutSetBestFriendList = findViewById(R.id.RefreshLayoutSetBestFriendList);
        RefreshLayoutSetBestFriendList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doData1();
                RefreshLayoutSetBestFriendList.setRefreshing(false);
            }
        });


    }
    private void doData1(){
        data1 = new LinkedList<>();

        for(int i = 0; i < sqlReturn.SearchCountFriendList; i++) {
            HashMap<String, String> row = new HashMap<>();
            row.put("txtName",sqlReturn.friendListName[i]);
            row.put("bff",sqlReturn.friendListBFF[i]);
            data1.add(row);
        }
    }

    private class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public TextView textName;
            public Button btn_add;
            public RoundedImageView roundedImageView;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                textName = itemView.findViewById(R.id.textName);
                roundedImageView = itemView.findViewById(R.id.roundedImageView);
                btn_add = itemView.findViewById(R.id.btn_add);
            }
        }

        @NonNull
        @Override
        public MyAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.setbestfriend_listview1,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter1.MyViewHolder holder, int position) {
            holder.textName.setText(data1.get(position).get("txtName"));
            if(data1.get(position).get("bff").equals("y")){
                holder.btn_add.setText("已是摯友");
                holder.btn_add.setTextColor(Color.parseColor("#FFFFFF"));
                holder.btn_add.setBackgroundResource(R.drawable.btn_friendconfirm2);
                btn_check[position] = 1;
                holder.btn_add.setEnabled(false);
            }
            holder.btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.btn_add.getText().toString().equals("新增")){
                        holder.btn_add.setText("已新增");
                        holder.btn_add.setTextColor(Color.parseColor("#FFFFFF"));
                        holder.btn_add.setBackgroundResource(R.drawable.btn_friendconfirm2);
                        btn_check[position] = 1;
                    }else if(holder.btn_add.getText().toString().equals("已新增")){
                        holder.btn_add.setText("新增");
                        holder.btn_add.setTextColor(Color.parseColor("#73AF00"));
                        holder.btn_add.setBackgroundResource(R.drawable.btn_friendconfirm);
                        btn_check[position] = 0;
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return data1.size();
        }
    }

    public void addBestFriend(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "addBestFriend");
        map.put("uid", uid);
        map.put("friendNum",friendNum);
        new addBestFriend(this).execute((HashMap)map);
    }

    private class addBestFriend extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        addBestFriend(Activity context){
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(status){
                SetBestFriendActivity.this.finish();
            }else {
                new AlertDialog.Builder(activity)
                        .setTitle("加入失敗")
                        .setMessage("請確認網路是否連線!!")
                        .setPositiveButton("好的", null)
                        .show();
            }
        }
    }
}
