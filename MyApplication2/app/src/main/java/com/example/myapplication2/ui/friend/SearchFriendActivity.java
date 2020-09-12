package com.example.myapplication2.ui.friend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class SearchFriendActivity extends AppCompatActivity {

    private EditText editText;
    private Button btn_friendsearch;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinkedList<HashMap<String,String>> data;
    private MyAdapter myAdapter;
    private ProgressBar progressBar1;

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
        Map<String,String> map = new HashMap<>();
        map.put("command", "strangerSearch");
        map.put("searchStranger", editText.getText().toString());
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
                sqlReturn.textViewSearchFriend = jsonObject.getString("results");
                status = jsonObject.getBoolean("status");
                sqlReturn.SearchFriend = jsonObject.getInt("rowcount");
                jsonArray = new JSONArray(sqlReturn.textViewSearchFriend);
                sqlReturn.SearchFriendUserId = new String[sqlReturn.SearchFriend];
                sqlReturn.SearchFriendName = new String[sqlReturn.SearchFriend];
                for(int i = 0; i<sqlReturn.SearchFriend; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.SearchFriendUserId[i] = obj.getString("userID");
                    sqlReturn.SearchFriendName[i] = obj.getString("strangerName");
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
                new AlertDialog.Builder(SearchFriendActivity.this)
                        .setTitle("提醒")
                        .setMessage("查無"+editText.getText().toString()+"這個人!!")
                        .setPositiveButton("OK", null)
                        .show();
                progressBar1.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void doData(){

        data = new LinkedList<>();
        for(int i = 0; i < sqlReturn.SearchFriend; i++){
            HashMap<String,String> row = new HashMap<>();
            row.put("textName",sqlReturn.SearchFriendName[i]);
            data.add(row);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public TextView textName;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                textName = itemView.findViewById(R.id.textName);
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
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
