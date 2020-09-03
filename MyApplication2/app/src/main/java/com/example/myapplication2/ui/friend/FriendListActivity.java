package com.example.myapplication2.ui.friend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

//        final ImageButton imbtnReturnToSocial = findViewById(R.id.imbtnReturnToSocial);
//        imbtnReturnToSocial.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(FriendListActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra("id",3);
//                startActivity(intent);
//            }
//        });

        final Button btn_friendlist = findViewById(R.id.btn_friendlist);
        btn_friendlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendListActivity.this,BestFriendActivity.class);
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
        LayoutManager2 = new LinearLayoutManager(this);
        RecyclerView2.setLayoutManager(LayoutManager2);
        myAdapter2 = new MyAdapter2();
        RecyclerView2.setAdapter(myAdapter2);
        doData2();
        RefreshLayoutFriendList2 = findViewById(R.id.RefreshLayoutFriendList2);
        RefreshLayoutFriendList2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doData2();
                RefreshLayoutFriendList2.setRefreshing(false);
            }
        });

    }

    private void doData1(){
        data1 = new LinkedList<>();
        for(int i = 0; i < 3; i++){
            HashMap<String,String> row = new HashMap<>();
            data1.add(row);
        }
    }

    private class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public TextView textName;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
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

        }

        @Override
        public int getItemCount() {
            return data1.size();
        }
    }




    private void doData2(){
        data2 = new LinkedList<>();

        int a = 0 , b = 0, c = 0;
        a = sqlReturn.SearchCountFriendList/3;
        b = sqlReturn.SearchCountFriendList%3;
        if (b != 0){
            a+=1;
        }
        for(int i = 0; i < a; i++) {
            HashMap<String, String> row = new HashMap<>();
            row.put("textName", sqlReturn.friendListName[c]);
            c ++;
            if(c < sqlReturn.SearchCountFriendList){
                row.put("textName1", sqlReturn.friendListName[c]);
                c++;
            }
            if (c < sqlReturn.SearchCountFriendList){
                row.put("textName2", sqlReturn.friendListName[c]);
                c++;
            }
            data2.add(row);
        }

    }

    private class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public TextView textName, textName1, textName2;
            public RoundedImageView roundedImageView, roundedImageView1, roundedImageView2;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                textName = itemView.findViewById(R.id.textName);
                roundedImageView = itemView.findViewById(R.id.roundedImageView);
                textName1 = itemView.findViewById(R.id.textName1);
                roundedImageView1 = itemView.findViewById(R.id.roundedImageView1);
                textName2 = itemView.findViewById(R.id.textName2);
                roundedImageView2 = itemView.findViewById(R.id.roundedImageView2);
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
            holder.textName1.setText(data2.get(position).get("textName1"));
            holder.textName2.setText(data2.get(position).get("textName2"));
        }

        @Override
        public int getItemCount() {
            return data2.size();
        }
    }




}
