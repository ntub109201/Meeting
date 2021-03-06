package com.example.myapplication2.ui.friend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
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
import android.widget.EditText;
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

public class SearchFriendActivity extends AppCompatActivity {

    private EditText editText;
    private Button btn_friendsearch;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinkedList<HashMap<String,String>> data;
    private MyAdapter myAdapter;
    private ProgressBar progressBar1;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);


        // adapter
        recyclerView = findViewById(R.id.recyclerView);
        progressBar1 = findViewById(R.id.progressBar1);

        final Button btn_friendback = findViewById(R.id.btn_friendback);
        btn_friendback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchFriendActivity.this,FriendListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        editText = findViewById(R.id.editText);
        btn_friendsearch = findViewById(R.id.btn_friendsearch);
        btn_friendsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar1.setVisibility(View.VISIBLE);
                if (editText.getText().toString().equals("")){
                    new AlertDialog.Builder(SearchFriendActivity.this)
                            .setTitle("提醒")
                            .setMessage("請至少輸入一個字!!")
                            .setPositiveButton("OK", null)
                            .show();
                    progressBar1.setVisibility(View.INVISIBLE);
                }else {
                    searchFriend();
                }
            }
        });

    }

    public void searchFriend(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "strangerSearch");
        map.put("searchStranger", editText.getText().toString());
        map.put("uid", uid);
        new searchFriend(this).execute((HashMap)map);
    }

    private class searchFriend extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        searchFriend(Activity context){
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonObject = null;
            boolean status = false;
            JSONArray jsonArray = null;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            try {
                jsonObject = new JSONObject(result);
                status = jsonObject.getBoolean("status");
                sqlReturn.Message = jsonObject.getString("message");
                if(sqlReturn.Message.equals("找不到此人")){
                    new AlertDialog.Builder(SearchFriendActivity.this)
                            .setTitle("提醒")
                            .setMessage("查無"+editText.getText().toString()+"這個人!!")
                            .setPositiveButton("OK", null)
                            .show();
                    status = false;
                }else{
                    sqlReturn.SearchFriend = jsonObject.getInt("rowcount");
                    sqlReturn.textViewSearchFriend = jsonObject.getString("results");
                    jsonArray = new JSONArray(sqlReturn.textViewSearchFriend);
                    sqlReturn.SearchFriendUserId = new String[sqlReturn.SearchFriend];
                    sqlReturn.SearchFriendName = new String[sqlReturn.SearchFriend];
                    sqlReturn.SearchFriendPersonImage = new String[sqlReturn.SearchFriend];
                    for (int i = 0; i < sqlReturn.SearchFriend; i++) {
                        JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                        sqlReturn.SearchFriendUserId[i] = obj.getString("userID");
                        sqlReturn.SearchFriendName[i] = obj.getString("strangerName");
                        sqlReturn.SearchFriendPersonImage[i] = obj.getString("userPicture");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                doData();
                // 這裡是 adapter
                recyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(SearchFriendActivity.this);
                recyclerView.setLayoutManager(mLayoutManager);
                myAdapter = new MyAdapter();
                recyclerView.setAdapter(myAdapter);
                progressBar1.setVisibility(View.INVISIBLE);
            } else {
                progressBar1.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void doData(){

        data = new LinkedList<>();
        for(int i = 0; i < sqlReturn.SearchFriend; i++){
            HashMap<String,String> row = new HashMap<>();
            row.put("textName",sqlReturn.SearchFriendName[i]);
            row.put("textUserID",sqlReturn.SearchFriendUserId[i]);
            data.add(row);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public TextView textName, textUserID;
            public Button btnAdd;
            public RoundedImageView roundedImageView;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                textName = itemView.findViewById(R.id.textName);
                textUserID = itemView.findViewById(R.id.textUserID);
                btnAdd = itemView.findViewById(R.id.btnAdd);
                roundedImageView = itemView.findViewById(R.id.roundedImageView);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(SearchFriendActivity.this)
                                .setCancelable(false)
                                .setTitle("提醒您")
                                .setMessage("確定新增"+textName.getText().toString()+"為好友?")
                                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        userID = textUserID.getText().toString();
                                        addFriend();
                                        progressBar1.setVisibility(View.VISIBLE);
                                    }
                                }).setNegativeButton("我選錯了",null).create()
                                .show();
                    }
                });
            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.searchfriend_view1,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.textName.setText(data.get(position).get("textName"));
            holder.textUserID.setText(data.get(position).get("textUserID"));
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
            }.execute(sqlReturn.SearchFriendPersonImage[position]);
        }

        @Override
        public int getItemCount() {
            return data.size();
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

    public void addFriend(){
        Map<String,String> map = new HashMap<>();
        map.put("command", "addFriend");
        map.put("uid", sqlReturn.GetUserID);
        map.put("friendNum",userID);
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
            boolean status = false;
            JSONArray jsonArray = null;
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
                progressBar1.setVisibility(View.INVISIBLE);
                new AlertDialog.Builder(SearchFriendActivity.this)
                        .setCancelable(false)
                        .setTitle("提醒您")
                        .setMessage("加入的好友需要對方按下接受後才能成為好友歐!!")
                        .setPositiveButton("好", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent = new Intent(SearchFriendActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("id",2);
                                startActivity(intent);
                            }
                        }).show();
            } else {
                progressBar1.setVisibility(View.INVISIBLE);
            }
        }
    }
    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent intent = new Intent(SearchFriendActivity.this,FriendListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
