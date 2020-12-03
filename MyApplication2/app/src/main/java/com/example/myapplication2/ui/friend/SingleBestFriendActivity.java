package com.example.myapplication2.ui.friend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SingleBestFriendActivity extends AppCompatActivity {

    private TextView DiaryTitle,friendName,DiaryDateTime,txtContext,friendMood;
    private String uri,personUri;
    private ImageView img_singleBestFriend_photo;
    private ProgressBar progressBar;
    private RoundedImageView roundedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_best_friend);


        final ImageButton imbtnReturnToFriendList = findViewById(R.id.imbtnReturnToFriendList);
        imbtnReturnToFriendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleBestFriendActivity.this.finish();
            }
        });

        progressBar = findViewById(R.id.progressBar);
        img_singleBestFriend_photo = findViewById(R.id.img_singleFriend_photo);
        roundedImageView = findViewById(R.id.roundedImageView);
        Intent intent = getIntent();
        uri = intent.getStringExtra("uri");
        personUri = intent.getStringExtra("personUri");

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
                img_singleBestFriend_photo.setImageBitmap(result);
                progressBar.setVisibility(View.INVISIBLE);
                super.onPostExecute(result);
            }
        }.execute(sqlReturn.bestfriendSearchImage[SingleBestFriendListActivity.position1]);

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
                roundedImageView.setImageBitmap(result);
                super.onPostExecute(result);
            }
        }.execute(sqlReturn.bestfriendSearchPersonImage[SingleBestFriendListActivity.position1]);

        DiaryTitle = findViewById(R.id.DiaryTitle);
        DiaryTitle.setText(sqlReturn.bestfriendSearchTagName[BestFriendActivity.position1]);
        friendName = findViewById(R.id.friendName);
        friendName.setText(sqlReturn.bestfriendSearchName[BestFriendActivity.position1]);
        DiaryDateTime = findViewById(R.id.DiaryDateTime);
        DiaryDateTime.setText(sqlReturn.bestfriendSearchDate[BestFriendActivity.position1]);
        txtContext = findViewById(R.id.txtContext);
        txtContext.setText(sqlReturn.bestfriendSearchContent[BestFriendActivity.position1]);
        friendMood = findViewById(R.id.friendMood);
        friendMood.setText(sqlReturn.bestfriendSearchMood[BestFriendActivity.position1]);
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
            SingleBestFriendActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
