package com.example.myapplication2.Diary.DiaryTravelWhereFragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication2.Diary.DiaryHowPackage.DiaryHowActivity;
import com.example.myapplication2.Diary.DiaryPreviewActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

import java.util.HashMap;
import java.util.LinkedList;


public class DiaryTravelFirstFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DiaryTravelFirstFragment.MyAdapter myAdapter;
    private LinkedList<HashMap<String, String>> data;
    public static int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_diary_travel_first, container, false);

        // adapter
        mRecyclerView = root.findViewById(R.id.WhereRecyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(DiaryTravelFirstFragment.super.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        myAdapter = new MyAdapter();
        mRecyclerView.setAdapter(myAdapter);
        doData();

        return root;
    }

    public void doData() {
        data = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            HashMap<String, String> row = new HashMap<>();
            data.add(row);
        }
    }

    private class  MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public ImageView imageView;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                imageView = itemView.findViewById(R.id.imageView);
                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        position = getAdapterPosition();
                        if (position == 0) {
                            DiaryValue.txtWhere = "國內/北部";
                        } else if (position == 1) {
                            DiaryValue.txtWhere = "國內/中部";
                        } else if (position == 2) {
                            DiaryValue.txtWhere = "國內/南部";
                        } else if (position == 3) {
                            DiaryValue.txtWhere = "國內/東部";
                        } else if (position == 4) {
                            DiaryValue.txtWhere = "國內/離島";
                        }
                        if(DiaryValue.txtWhat.equals("")){
                            Intent intent = new Intent();
                            intent.setClass(DiaryTravelFirstFragment.super.getActivity(),DiaryPreviewActivity.class);
                            Bundle tagData = new Bundle();
                            tagData.putString("1","DiaryTravelWhereActivity");
                            intent.putExtras(tagData);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTravelFirstFragment.super.getActivity());
                            startActivity(intent,options.toBundle());
                        }else {
                            Intent intent = new Intent(DiaryTravelFirstFragment.super.getActivity(), DiaryHowActivity.class);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryTravelFirstFragment.super.getActivity());
                            startActivity(intent, options.toBundle());
                        }
                    }
                });
            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.where_foodlist,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            if (position == 0) {
                holder.imageView.setImageResource(R.mipmap.ic_btn_north_foreground);
            } else if (position == 1) {
                holder.imageView.setImageResource(R.mipmap.ic_btn_central_foreground);
            } else if (position == 2) {
                holder.imageView.setImageResource(R.mipmap.ic_btn_south_foreground);
            } else if (position == 3) {
                holder.imageView.setImageResource(R.mipmap.ic_btn_east_foreground);
            } else if (position == 4) {
                holder.imageView.setImageResource(R.mipmap.ic_btn_islands_foreground);
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
