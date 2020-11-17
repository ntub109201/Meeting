package com.example.myapplication2.ui.friend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;

public class SingleBestFriendActivity extends AppCompatActivity {

    private TextView DiaryTitle,friendName,DiaryDateTime,txtContext,friendMood;

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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.ECLAIR) {
                event.startTracking();
            } else {
                onBackPressed(); // 是其他按鍵則再Call Back方法
            }
        }
        return false;
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }
}
