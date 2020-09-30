package com.example.myapplication2.ui.friend;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication2.Diary.DiaryActivity;
import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.Login.LoginActivity;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.ModifyPersonalActivity;
import com.example.myapplication2.PersonalActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;
import com.example.myapplication2.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class FriendFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinkedList<HashMap<String,String>> data;
    private MyAdapter myAdapter;
    private SwipeRefreshLayout RefreshLayoutFriend;
    private ImageButton imBtnPersonal;
    private Button btn_addfriend;

    public static int FriendTag;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_friend, container, false);

        if(HomeFragment.changeBtn == true){
            HomeFragment.changeBtn = false;
        }

        btn_addfriend = root.findViewById(R.id.btn_addfriend);
        btn_addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendFragment.super.getActivity(),FriendListActivity.class);
                startActivity(intent);
            }
        });
        // adapter
        mRecyclerView = root.findViewById(R.id.RecyclerView_1);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(FriendFragment.super.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        doData();
        searchFriendList();
        searchFriend();
        myAdapter = new MyAdapter();
        mRecyclerView.setAdapter(myAdapter);

        RefreshLayoutFriend = root.findViewById(R.id.RefreshLayoutFriend);
        RefreshLayoutFriend.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchFriend();
            }
        });

        final ImageButton imBtnSearchFriend = root.findViewById(R.id.imBtnSearchFriend);
        imBtnSearchFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendFragment.super.getActivity(),FriendListActivity.class);
                startActivity(intent);
            }
        });

        mRecyclerView = root.findViewById(R.id.RecyclerView_1);

        imBtnPersonal = root.findViewById(R.id.imBtnPersonal);
        imBtnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendFragment.super.getActivity(), PersonalActivity.class);
                intent.putExtra("pageId",3);
                startActivity(intent);
            }
        });


        return root;
    }

    private void doData(){

        data = new LinkedList<>();
        for(int i = 0; i < sqlReturn.SearchCountFriend; i++){
            HashMap<String,String> row = new HashMap<>();
            row.put("place_text",sqlReturn.friendName[i]);
            row.put("place_description_text",sqlReturn.dateFriend[i]);
            row.put("tag_text",sqlReturn.tagNameFriend[i]);
            data.add(row);
        }
//        for(int i = 0; i < 5; i++){
//            HashMap<String,String> row = new HashMap<>();
//            row.put("place_text","姓名"+i);
//            row.put("place_description_text","日期"+i);
//            row.put("tag_text","文章開頭"+i);
//            data.add(row);
//        }
    }

    private class  MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public TextView place_text ,place_description_text,tag_text;
            public ImageView photo_image;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                place_text = itemView.findViewById(R.id.place_text);
                place_description_text = itemView.findViewById(R.id.place_description_text);
                tag_text = itemView.findViewById(R.id.tag_text);
                photo_image = itemView.findViewById(R.id.photo_image);
                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        FriendTag = getAdapterPosition();
                        Intent intent = new Intent(FriendFragment.super.getActivity(),SocialArticalActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.place_text.setText(data.get(position).get("place_text"));
            holder.place_description_text.setText(data.get(position).get("place_description_text"));
            holder.tag_text.setText(data.get(position).get("tag_text"));
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

    // 此為社群好友貼文全抓
    public void searchFriend(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendList");
        map.put("uid", uid);
        new searchFriend(super.getActivity()).execute((HashMap)map);
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
                for(int i = 0; i<sqlReturn.SearchCountFriend; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.contentFriend[i] = obj.getString("content");
                    sqlReturn.tagNameFriend[i] = obj.getString("tagName");
                    sqlReturn.moodFriend[i] = obj.getString("mood");
                    sqlReturn.dateFriend[i] = obj.getString("date");
                    sqlReturn.friendName[i] = obj.getString("friendName01");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (sqlReturn.textViewContextFriend!=null){
                doData();
                // 這裡是 adapter
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(FriendFragment.super.getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                myAdapter = new MyAdapter();
                mRecyclerView.setAdapter(myAdapter);
                RefreshLayoutFriend.setRefreshing(false);
            }
        }

    }

    public void searchFriendList(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendInfoList");
        map.put("uid", uid);
        new searchFriendList(super.getActivity()).execute((HashMap)map);
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
                sqlReturn.friendListName = new String[sqlReturn.SearchCountFriendList];
                for(int i = 0; i<sqlReturn.SearchCountFriendList; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.friendListName[i] = obj.getString("friendName01");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (sqlReturn.textViewContextFriendList!=null){
                //Toast.makeText(activity, String.valueOf(sqlReturn.SearchCountMood), Toast.LENGTH_LONG).show();
            }else {

            }
        }
    }

}
