package com.example.myapplication2.takePhoto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PhotoActivity extends Activity implements SurfaceHolder.Callback, Camera.AutoFocusCallback  {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private Button mTaskPicture;
    private Camera camera;

    String pmPhotoFileName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selfcreate_photo);
        // 全螢幕
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.selfcreate_photo);


        Intent intent = this.getIntent();
        pmPhotoFileName = intent.getStringExtra("pmPhotoFileName");

        initViews();
    }

    private void initViews() {
        mSurfaceView = (SurfaceView) this.findViewById(R.id.svPreview);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mTaskPicture = (Button) this.findViewById(R.id.taskPicture);
        mTaskPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    camera.autoFocus(PhotoActivity.this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    @Override
    public void onAutoFocus(boolean success, Camera camera) {
        if (success) {
            camera.takePicture(null, null, jpeg);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();

        if (Build.VERSION.SDK_INT >= 8)
            camera.setDisplayOrientation(90);

        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException exception) {
            camera.release();
            camera = null;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // 取得相機參數
        Camera.Parameters parameters = camera.getParameters();
        // 取得照片尺寸
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        int sptw = supportedPictureSizes.get(supportedPictureSizes.size() - 1).width;
        int spth = supportedPictureSizes.get(supportedPictureSizes.size() - 1).height;


        // 取得預覽尺寸
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        int prvw = supportedPreviewSizes.get(0).width;
        int prvh = supportedPreviewSizes.get(0).height;

        parameters.setPictureFormat(PixelFormat.JPEG);
        parameters.setPreviewSize(1920, 1080);
//        parameters.setPreviewSize(prvw, prvh);
        parameters.setPictureSize(1920, 1080);


        camera.setParameters(parameters);
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    public void saveBitmap(Bitmap bitmap) {

        try
        {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            String s = new String(bitmapdata);
            Uri uri = Uri.parse(s);
            String encoded = Base64.encodeToString(bitmapdata, Base64.DEFAULT);
            Intent intent = new Intent(PhotoActivity.this, TakePhotoActivity.class);
            intent.putExtra("fileName", encoded);
            intent.putExtra("Uri",String.valueOf(uri));
            startActivity(intent);
        }
        catch(Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    Camera.PictureCallback jpeg = new Camera.PictureCallback() {

        public void onPictureTaken(byte[] imgData, Camera camera) {
            if (imgData != null) {
                Bitmap picture = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                picture = rotationBitmap(picture);
                saveBitmap(picture);
            }

            camera.startPreview();
        }
    };

    public Bitmap rotationBitmap(Bitmap picture) {
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(picture,picture.getWidth(),picture.getHeight(),true);
        Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap , 0, 0, scaledBitmap .getWidth(), scaledBitmap .getHeight(), matrix, true);
        return rotatedBitmap;
    }

    public void sendToGallery(Context ctx, String path) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(new File(path));
        intent.setData(contentUri);
        ctx.sendBroadcast(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent it1 = new Intent(this, MainActivity.class);
            it1.putExtra("id",1);
            startActivity(it1);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
