package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import java.io.File;

public class OCRActivity extends AppCompatActivity {

    private DisplayMetrics mPhone;
    private final static int CAMERA = 66 ;
    private final static int PHOTO = 99 ;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_c_r);

        imgPhoto = findViewById(R.id.imgPhoto);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bitmap mbmp = (Bitmap) data.getExtras().get("data");
            imgPhoto.setImageBitmap(mbmp);
        }
    }


}
