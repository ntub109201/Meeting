package com.example.myapplication2.Diary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DiaryMoodActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        imageView = findViewById(R.id.imageView);
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
                imageView.setImageBitmap(result);
                super.onPostExecute(result);
            }
        }.execute(sqlReturn.PersonalPicture);

        final TextView txtHelloName = findViewById(R.id.txtHelloName);
        String getName = sqlReturn.PersonalName;
        txtHelloName.setText("Hello "+getName+"：）");

        // 前往下一頁 sun -----------------------------------------------
        final Button btnsun = findViewById(R.id.btn_sun);
        btnsun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DiaryValue.txtMood = "心情1";
                Intent registerIntent = new Intent(DiaryMoodActivity.this, DiaryTagActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryMoodActivity.this);
                DiaryMoodActivity.this.startActivity(registerIntent,options.toBundle());
            }
        });

        // 前往下一頁 suncloud -----------------------------------------------
        final Button btnsuncloud = findViewById(R.id.btn_suncloud);
        btnsuncloud.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DiaryValue.txtMood = "心情2";
                Intent registerIntent = new Intent(DiaryMoodActivity.this, DiaryTagActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryMoodActivity.this);
                DiaryMoodActivity.this.startActivity(registerIntent,options.toBundle());
            }
        });

        // 前往下一頁 cloud -----------------------------------------------
        final Button btncloud = findViewById(R.id.btn_cloud);
        btncloud.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DiaryValue.txtMood = "心情3";
                Intent registerIntent = new Intent(DiaryMoodActivity.this, DiaryTagActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryMoodActivity.this);
                DiaryMoodActivity.this.startActivity(registerIntent,options.toBundle());
            }
        });

        // 前往下一頁 rain -----------------------------------------------
        final Button btnrain = findViewById(R.id.btn_rain);
        btnrain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DiaryValue.txtMood = "心情4";
                Intent registerIntent = new Intent(DiaryMoodActivity.this, DiaryTagActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryMoodActivity.this);
                DiaryMoodActivity.this.startActivity(registerIntent,options.toBundle());
            }
        });

        // 前往下一頁 thunder -----------------------------------------------
        final Button btnthunder = findViewById(R.id.btn_thunder);
        btnthunder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DiaryValue.txtMood = "心情5";
                Intent registerIntent = new Intent(DiaryMoodActivity.this, DiaryTagActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryMoodActivity.this);
                DiaryMoodActivity.this.startActivity(registerIntent,options.toBundle());
            }
        });

        // 返回主畫面
        final ImageButton imbtnReturnFrontPage = findViewById(R.id.imbtnReturnFrontPage);
        imbtnReturnFrontPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryValue.txtTag = "";
                DiaryValue.txtMood = "";
                DiaryValue.firstWhat = "";
                DiaryValue.txtWhat = "";
                DiaryValue.txtWhy = "";
                DiaryValue.txtWhere = "";
                DiaryValue.txtWhen = "";
                DiaryValue.txtWho = "";
                DiaryValue.Time = "";
                DiaryValue.EndTime = "";
                DiaryValue.howCount = 0;
                DiaryValue.Eye_Count = 0;
                DiaryValue.Mouth_Count = 0;
                DiaryValue.Smell_Count = 0;
                Intent intent = new Intent(DiaryMoodActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",1);
                startActivity(intent);
                finish();
            }
        });
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


    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            DiaryValue.txtTag = "";
            DiaryValue.txtMood = "";
            DiaryValue.firstWhat = "";
            DiaryValue.txtWhat = "";
            DiaryValue.txtWhy = "";
            DiaryValue.txtWhere = "";
            DiaryValue.txtWhen = "";
            DiaryValue.txtWho = "";
            DiaryValue.Time = "";
            DiaryValue.EndTime = "";
            DiaryValue.howCount = 0;
            DiaryValue.Eye_Count = 0;
            DiaryValue.Mouth_Count = 0;
            DiaryValue.Smell_Count = 0;
            Intent intent = new Intent(DiaryMoodActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("id",1);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


}
