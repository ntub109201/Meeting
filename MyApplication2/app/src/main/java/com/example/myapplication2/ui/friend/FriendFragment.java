package com.example.myapplication2.ui.friend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.User.PersonalActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;
import com.example.myapplication2.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
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
    private ConstraintLayout no_friend_layout;
    private ImageView no_friend_picture1,no_friend_picture2;
    private ConstraintLayout FriendLayout;

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
        addFriend();
        searchFriend();
        searchFriendList();
        myAdapter = new MyAdapter();
        mRecyclerView.setAdapter(myAdapter);

        RefreshLayoutFriend = root.findViewById(R.id.RefreshLayoutFriend);
        RefreshLayoutFriend.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchFriend();
                searchFriendList();
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
                intent.putExtra("pageId",2);
                startActivity(intent);
            }
        });

        no_friend_layout = root.findViewById(R.id.no_friend_layout);
        no_friend_picture1 = root.findViewById(R.id.no_friend_picture1);
        no_friend_picture2 = root.findViewById(R.id.no_friend_picture2);
        FriendLayout = root.findViewById(R.id.FriendLayout);

        if(sqlReturn.check_friend){
            no_friend_layout.setVisibility(View.GONE);
            no_friend_picture1.setVisibility(View.GONE);
            no_friend_picture2.setVisibility(View.GONE);
            FriendLayout.setVisibility(View.VISIBLE);
        }

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
                holder.photo_image.setImageResource(R.drawable.test_photo);
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
            if (sqlReturn.SearchCountFriend!=0){
                doData();
                // 這裡是 adapter
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(FriendFragment.super.getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                myAdapter = new MyAdapter();
                mRecyclerView.setAdapter(myAdapter);
                RefreshLayoutFriend.setRefreshing(false);
                no_friend_layout.setVisibility(View.GONE);
                no_friend_layout.setEnabled(false);
                no_friend_picture1.setVisibility(View.GONE);
                no_friend_picture1.setEnabled(false);
                no_friend_picture2.setVisibility(View.GONE);
                no_friend_picture2.setEnabled(false);
                FriendLayout.setVisibility(View.VISIBLE);
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
                sqlReturn.friendListNum = new String[sqlReturn.SearchCountFriendList];
                sqlReturn.friendListName = new String[sqlReturn.SearchCountFriendList];
                sqlReturn.friendListBFF = new String[sqlReturn.SearchCountFriendList];
                for(int i = 0; i<sqlReturn.SearchCountFriendList; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.friendListNum[i] = obj.getString("friendNum");
                    sqlReturn.friendListName[i] = obj.getString("friendName01");
                    sqlReturn.friendListBFF[i] = obj.getString("BFF");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // 取待加入好友
    public void addFriend(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendConfirm");
        map.put("uid", uid);
        new addFriend(FriendFragment.super.getActivity()).execute((HashMap)map);
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
            JSONArray jsonArray = null;
            boolean status = false;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            try {
                jsonObject = new JSONObject(result);
                status = jsonObject.getBoolean("status");
                if(status){
                    sqlReturn.textAdd_friend = jsonObject.getString("results");
                    sqlReturn.add_friendCount = jsonObject.getInt("rowcount");
                    jsonArray = new JSONArray(sqlReturn.textAdd_friend);
                    sqlReturn.add_friendNum = new String[sqlReturn.add_friendCount];
                    sqlReturn.add_friendName = new String[sqlReturn.add_friendCount];
                    sqlReturn.add_friendBFF = new String[sqlReturn.add_friendCount];
                    for (int i = 0; i<sqlReturn.add_friendCount; i++){
                        JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                        sqlReturn.add_friendNum[i] = obj.getString("friendNum");
                        sqlReturn.add_friendName[i] = obj.getString("friendName01");
                        sqlReturn.add_friendBFF[i] = obj.getString("BFF");
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

}
