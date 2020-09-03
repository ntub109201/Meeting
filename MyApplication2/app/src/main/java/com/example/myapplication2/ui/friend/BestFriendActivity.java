package com.example.myapplication2.ui.friend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication2.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.LinkedList;

public class BestFriendActivity extends AppCompatActivity {

    private RecyclerView RecyclerView1;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager LayoutManager1;
    private LinkedList<HashMap<String,String>> data1;
    private MyAdapter1 myAdapter1;
    private SwipeRefreshLayout RefreshLayoutBestFriendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_friend);

        final Button btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestFriendActivity.this,FriendListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                BestFriendActivity.this.startActivity(intent);
            }
        });

        final Button btn_addBestFriend = findViewById(R.id.btn_addBestFriend);
        btn_addBestFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestFriendActivity.this,SetBestFriendActivity.class);
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
        RefreshLayoutBestFriendList = findViewById(R.id.RefreshLayoutBestFriendList);
        RefreshLayoutBestFriendList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doData1();
                RefreshLayoutBestFriendList.setRefreshing(false);
            }
        });

    }

    private void doData1(){
        data1 = new LinkedList<>();

        String[] b = {"陳昱","振宇","景婷","詩庭","允謙","臭鼬"};
        int a = 0;
        for(int i = 0; i < 2; i++) {
            HashMap<String, String> row = new HashMap<>();
            row.put("textName",b[a]);
            a ++;
            row.put("textName1",b[a]);
            a ++;
            row.put("textName2",b[a]);
            a ++;
            data1.add(row);
        }
    }

    private class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

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
        public MyAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.bestfriendlist_view1,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter1.MyViewHolder holder, int position) {
            holder.textName.setText(data1.get(position).get("textName"));
            holder.textName1.setText(data1.get(position).get("textName1"));
            holder.textName2.setText(data1.get(position).get("textName2"));
        }

        @Override
        public int getItemCount() {
            return data1.size();
        }
    }
}
