package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class OCRActivity extends AppCompatActivity {

    private GridView gridView;
    private ImageView imageView;
    private List<String> thumbs;  //存放縮圖的id
    private List<String> imagePaths;  //存放圖片的路徑
    private ImageAdapter imageAdapter;  //用來顯示縮圖

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_c_r);

        gridView = (GridView) findViewById(R.id.gridView1);
        imageView = (ImageView) findViewById(R.id.imageView1);

        ContentResolver cr = getContentResolver();
        String[] projection = { MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA };

        //查詢SD卡的圖片
        Cursor cursor = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null);

        thumbs = new ArrayList<String>();
        imagePaths = new ArrayList<String>();

        for (int i = 0; i < cursor.getCount(); i++) {

            cursor.moveToPosition(cursor.getCount()-(i+1));
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Images.Media._ID));// ID
            thumbs.add(id + "");

            String filepath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));//抓路徑

            imagePaths.add(filepath);
        }

        cursor.close();

        imageAdapter = new ImageAdapter(OCRActivity.this, thumbs);
        gridView.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();


        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                imageView.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
            }

        });
        imageView.setVisibility(View.GONE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void setImageView(int position){
        Bitmap bm = BitmapFactory.decodeFile(imagePaths.get(position));
        imageView.setImageBitmap(bm);
        imageView.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.GONE);
    }

}
