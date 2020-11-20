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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SingleBestFriendListActivity extends AppCompatActivity {


    private androidx.recyclerview.widget.RecyclerView RecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager LayoutManager;
    private LinkedList<HashMap<String,String>> data;
    private MyAdapter myAdapter;
    private SwipeRefreshLayout RefreshLayoutFriendList;
    private TextView textName;
    public static int position1;
    private ImageButton imbtnReturnToFriendList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_best_friend_list);

        textName = findViewById(R.id.textName);
        textName.setText(sqlReturn.BestFriendListName[BestFriendActivity.position1]);

        imbtnReturnToFriendList = findViewById(R.id.imbtnReturnToFriendList);
        imbtnReturnToFriendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleBestFriendListActivity.this.finish();
            }
        });

        RecyclerView = findViewById(R.id.RecyclerView);
        RecyclerView.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        RecyclerView.setLayoutManager(LayoutManager);
        myAdapter = new MyAdapter();
        RecyclerView.setAdapter(myAdapter);
        doData();
        bestFriendSearch();
        RefreshLayoutFriendList = findViewById(R.id.RefreshLayoutFriend);
        RefreshLayoutFriendList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bestFriendSearch();
            }
        });

    }
    private void doData(){

        data = new LinkedList<>();
        for(int i = 0; i< sqlReturn.bestfriendSearchCount; i++){
            HashMap<String, String> row = new HashMap<>();
            row.put("date", sqlReturn.bestfriendSearchDate[i]);
            row.put("tag", sqlReturn.bestfriendSearchTagName[i]);
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
                        position1 = getAdapterPosition();
                        Intent intent = new Intent(SingleBestFriendListActivity.this,SingleFriendActivity.class);
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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    public void bestFriendSearch(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "bestFriendListSearch");
        map.put("uid", uid);
        map.put("searchBestFriend",textName.getText().toString());
        new bestFriendSearch(this).execute((HashMap)map);
    }

    private class bestFriendSearch extends HttpURLConnection_AsyncTask{
        // 建立弱連結
        WeakReference<Activity> activityReference;
        bestFriendSearch(Activity context){activityReference = new WeakReference<>(context);}
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
                sqlReturn.bestfriendSearchCount = jsonObject.getInt("rowcount");
                sqlReturn.textViewContextbestfriendSearch = jsonObject.getString("results");
                jsonArray = new JSONArray(sqlReturn.textViewContextbestfriendSearch);
                sqlReturn.bestfriendSearchNum = new String[sqlReturn.bestfriendSearchCount];
                sqlReturn.bestfriendSearchContent = new String[sqlReturn.bestfriendSearchCount];
                sqlReturn.bestfriendSearchMood = new String[sqlReturn.bestfriendSearchCount];
                sqlReturn.bestfriendSearchTagName = new String[sqlReturn.bestfriendSearchCount];
                sqlReturn.bestfriendSearchDate = new String[sqlReturn.bestfriendSearchCount];
                sqlReturn.bestfriendSearchName = new String[sqlReturn.bestfriendSearchCount];
                for(int i = 0; i<sqlReturn.SearchCountFriendList; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.bestfriendSearchNum[i] = obj.getString("friendNum");
                    sqlReturn.bestfriendSearchContent[i] = obj.getString("content");
                    sqlReturn.bestfriendSearchMood[i] = obj.getString("mood");
                    sqlReturn.bestfriendSearchTagName[i] = obj.getString("tagName");
                    sqlReturn.bestfriendSearchDate[i] = obj.getString("date");
                    sqlReturn.bestfriendSearchName[i] = obj.getString("friendName01");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                RefreshLayoutFriendList.setRefreshing(false);
            }else{
                RefreshLayoutFriendList.setRefreshing(false);
            }
        }
    }
    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            SingleBestFriendListActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
