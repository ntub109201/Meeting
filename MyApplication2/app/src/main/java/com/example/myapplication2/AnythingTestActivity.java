package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;

public class AnythingTestActivity extends AppCompatActivity {

    private Button btn_addphoto;
    private ImageView imgPhoto;

    private RecyclerView recyclerview;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private LinkedList<HashMap<String,String>> data1;
    private AnythingTestActivity.MyAdapter myAdapter;
    private int count;
    private Bitmap mbmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anything_test);

        imgPhoto = findViewById(R.id.imgPhoto);

        btn_addphoto = findViewById(R.id.btn_addphoto);
        btn_addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(AnythingTestActivity.this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(mLayoutManager);
        myAdapter = new MyAdapter();
        recyclerview.setAdapter(myAdapter);
        doData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            count +=1;
            mbmp = (Bitmap) data.getExtras().get("data");
            imgPhoto.setImageBitmap(mbmp);
            doData();
        }
    }

    private void doData(){
        data1 = new LinkedList<>();
        for(int i = 0; i < count; i++){
            HashMap<String,String> row = new HashMap<>();
            data1.add(row);
        }
    }

    private class  MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public ImageView imgPhoto;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                imgPhoto = itemView.findViewById(R.id.imgPhoto);

            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.photo_item,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.imgPhoto.setImageBitmap(mbmp);
        }

        @Override
        public int getItemCount() {
            return data1.size();
        }
    }

}
