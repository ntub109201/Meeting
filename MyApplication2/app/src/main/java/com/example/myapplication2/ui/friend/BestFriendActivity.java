package com.example.myapplication2.ui.friend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
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

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BestFriendActivity extends AppCompatActivity {

    private RecyclerView RecyclerView1;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager LayoutManager1;
    private LinkedList<HashMap<String,String>> data1;
    private MyAdapter1 myAdapter1;
    private SwipeRefreshLayout RefreshLayoutBestFriendList;
    private ConstraintLayout bestFriend_layout,noBestFriend;
    public static int position1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_friend);

        bestFriend_layout = findViewById(R.id.bestFriend_layout);
        noBestFriend = findViewById(R.id.noBestFriend);

        final Button btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestFriendActivity.this,FriendListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                BestFriendActivity.this.startActivity(intent);
                finish();
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
        LayoutManager1 = new GridLayoutManager(this,3);
        RecyclerView1.setLayoutManager(LayoutManager1);
        myAdapter1 = new MyAdapter1();
        RecyclerView1.setAdapter(myAdapter1);
        doData1();
        searchBestFriendList();
        RefreshLayoutBestFriendList = findViewById(R.id.RefreshLayoutBestFriendList);
        RefreshLayoutBestFriendList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchBestFriendList();
            }
        });

    }

    private void doData1(){
        data1 = new LinkedList<>();
        if(sqlReturn.SearchCountBestFriendList != 0){
            bestFriend_layout.setVisibility(View.VISIBLE);
            noBestFriend.setVisibility(View.INVISIBLE);
            for(int i = 0; i < sqlReturn.SearchCountBestFriendList; i++) {
                HashMap<String, String> row = new HashMap<>();
                row.put("textName",sqlReturn.BestFriendListName[i]);
                data1.add(row);
            }
        }else{
            bestFriend_layout.setVisibility(View.INVISIBLE);
            noBestFriend.setVisibility(View.VISIBLE);
        }
    }

    private class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

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
                        position1 = getAdapterPosition();
                        bestFriendSearch();
                    }
                });
            }
        }

        @NonNull
        @Override
        public MyAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.friend_list_item,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter1.MyViewHolder holder, int position) {
            holder.textName.setText(data1.get(position).get("textName"));
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
            }.execute(sqlReturn.BestFriendListPersonImage[position]);
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


    public void searchBestFriendList(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "bestFriendInfoList");
        map.put("uid", uid);
        new searchBestFriendList(this).execute((HashMap)map);
    }

    private class searchBestFriendList extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        searchBestFriendList(Activity context){
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
                sqlReturn.SearchCountBestFriendList = jsonObject.getInt("rowcount");
                sqlReturn.textViewContextBestFriendList = jsonObject.getString("results");
                jsonArray = new JSONArray(sqlReturn.textViewContextBestFriendList);
                sqlReturn.BestFriendListName = new String[sqlReturn.SearchCountBestFriendList];
                sqlReturn.BestFriendListNum = new String[sqlReturn.SearchCountBestFriendList];
                sqlReturn.BestFriendListPersonImage = new String[sqlReturn.SearchCountBestFriendList];
                for(int i = 0; i<sqlReturn.SearchCountBestFriendList; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.BestFriendListName[i] = obj.getString("friendName01");
                    sqlReturn.BestFriendListNum[i] = obj.getString("friendNum");
                    sqlReturn.BestFriendListPersonImage[i] = obj.getString("userPicture");
                }
                doData1();
                RefreshLayoutBestFriendList.setRefreshing(false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void  bestFriendSearch(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "bestFriendListSearch");
        map.put("uid", uid);
        map.put("searchBestFriend",sqlReturn.BestFriendListName[position1]);
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
                sqlReturn.bestfriendSearchImage = new String[sqlReturn.bestfriendSearchCount];
                sqlReturn.bestfriendSearchPersonImage = new String[sqlReturn.bestfriendSearchCount];
                for(int i = 0; i<sqlReturn.bestfriendSearchCount; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.bestfriendSearchNum[i] = obj.getString("friendNum");
                    sqlReturn.bestfriendSearchContent[i] = obj.getString("content");
                    sqlReturn.bestfriendSearchMood[i] = obj.getString("mood");
                    sqlReturn.bestfriendSearchTagName[i] = obj.getString("tagName");
                    sqlReturn.bestfriendSearchDate[i] = obj.getString("date");
                    sqlReturn.bestfriendSearchName[i] = obj.getString("friendName01");
                    sqlReturn.bestfriendSearchImage[i] = obj.getString("image_path");
                    sqlReturn.bestfriendSearchPersonImage[i] = obj.getString("userPicture");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                Intent intent = new Intent(BestFriendActivity.this,SingleBestFriendListActivity.class);
                startActivity(intent);
                RefreshLayoutBestFriendList.setRefreshing(false);
            }else {
                for(int i = 0; i<sqlReturn.bestfriendSearchCount; i++){
                    sqlReturn.bestfriendSearchNum[i] = "";
                    sqlReturn.bestfriendSearchContent[i] = "";
                    sqlReturn.bestfriendSearchMood[i] = "";
                    sqlReturn.bestfriendSearchTagName[i] = "";
                    sqlReturn.bestfriendSearchDate[i] = "";
                    sqlReturn.bestfriendSearchName[i] = "";
                    sqlReturn.bestfriendSearchImage[i] = "";
                    sqlReturn.bestfriendSearchPersonImage[i] = "";
                }
                sqlReturn.bestfriendSearchCount = 0;
                sqlReturn.bestfriendSearchNum = new String[1];
                sqlReturn.bestfriendSearchNum[0] = sqlReturn.BestFriendListNum[position1];
                Intent intent = new Intent(BestFriendActivity.this,SingleBestFriendListActivity.class);
                startActivity(intent);
                RefreshLayoutBestFriendList.setRefreshing(false);
            }
        }
    }

    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent intent = new Intent(BestFriendActivity.this,FriendListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            BestFriendActivity.this.startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
