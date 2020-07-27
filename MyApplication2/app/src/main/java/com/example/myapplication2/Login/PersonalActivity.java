package com.example.myapplication2.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;


public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        new AlertDialog.Builder(this)
                .setTitle("歡迎使用Guidary")
                .setMessage("請填寫個人基本資料以幫助我們更了解您")
                .setPositiveButton("OK", null)
                .show();
        sqlReturn.RegisterFirstLogin = true;

    }

}
