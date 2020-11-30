package com.example.myapplication2.ui.friend;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;

public class SocialArticalActivity extends AppCompatActivity {

    private TextView SocialDiaryTitle,SocialUserName,SocialDiaryDateTime,txtSocialContext,SocialMood;
    private ImageView img_friend_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socialartical);

        img_friend_photo = findViewById(R.id.img_friend_photo);
        img_friend_photo.setImageResource(R.drawable.test_photo);

        final ImageButton imbtnReturnToSocial = findViewById(R.id.imbtnReturnToSocial);
        imbtnReturnToSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocialArticalActivity.this.finish();
            }
        });

        SocialUserName = findViewById(R.id.SocialUserName);
        SocialUserName.setText(sqlReturn.friendName[FriendFragment.FriendTag]);
        SocialDiaryDateTime = findViewById(R.id.SocialDiaryDateTime);
        SocialDiaryDateTime.setText(sqlReturn.dateFriend[FriendFragment.FriendTag]);
        SocialDiaryTitle = findViewById(R.id.SocialDiaryTitle);
        SocialDiaryTitle.setText(sqlReturn.tagNameFriend[FriendFragment.FriendTag]);
        txtSocialContext = findViewById(R.id.txtSocialContext);
        txtSocialContext.setText(sqlReturn.contentFriend[FriendFragment.FriendTag]);
        SocialMood = findViewById(R.id.SocialMood);
        String context = sqlReturn.moodFriend[FriendFragment.FriendTag];
        String realContext = "";
        if(context.equals("心情1")){
            realContext = "心情超棒的";
        }else if(context.equals("心情2")){
            realContext = "心情不錯歐";
        }else if(context.equals("心情3")){
            realContext = "心情普普呢";
        }else if(context.equals("心情4")){
            realContext = "心情不好啊";
        }else if(context.equals("心情5")){
            realContext = "心情很差呢";
        }

        SocialMood.setText(realContext);

    }
    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            SocialArticalActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
