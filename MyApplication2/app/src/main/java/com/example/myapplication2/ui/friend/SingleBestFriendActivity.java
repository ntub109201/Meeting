package com.example.myapplication2.ui.friend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;

public class SingleBestFriendActivity extends AppCompatActivity {

    private TextView DiaryTitle,friendName,DiaryDateTime,txtContext,friendMood;
    private ImageView img_singleBestFriend_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_best_friend);

        img_singleBestFriend_photo = findViewById(R.id.img_singleBestFriend_photo);
        img_singleBestFriend_photo.setImageResource(R.drawable.test_photo);

        final ImageButton imbtnReturnToFriendList = findViewById(R.id.imbtnReturnToFriendList);
        imbtnReturnToFriendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleBestFriendActivity.this.finish();
            }
        });

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
