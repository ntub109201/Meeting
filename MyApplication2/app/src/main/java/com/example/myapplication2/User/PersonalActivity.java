package com.example.myapplication2.User;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.Login.LoginActivity;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class PersonalActivity extends AppCompatActivity {

    private TextView textName;
    private String password = "";
    private ProgressBar progressBar;
    private int pageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        textName = findViewById(R.id.textName);
        textName.setText(sqlReturn.PersonalName);
        progressBar = findViewById(R.id.progressBar);

        pageId = getIntent().getIntExtra("pageId",0);
        final ImageButton btn_setback = findViewById(R.id.btn_setback);
        btn_setback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",pageId);
                startActivity(intent);
            }
        });

        final Button btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PersonalActivity.this)
                        .setCancelable(false)
                        .setTitle("提醒您")
                        .setMessage("確定登出?")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PersonalActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("取消",null).create()
                        .show();
            }
        });

        final Button btn_chapersoninfo = findViewById(R.id.btn_chapersoninfo);
        btn_chapersoninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, ModifyPersonalActivity.class);
                intent.putExtra("pageId",pageId);
                startActivity(intent);
            }
        });



        final Button btn_chapassword = findViewById(R.id.btn_chapassword);
        if(sqlReturn.googleLogin){
            btn_chapassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(PersonalActivity.this)
                            .setTitle("很抱歉")
                            .setMessage("google登入不提供變更密碼")
                            .setPositiveButton("OK", null)
                            .show();
                }
            });
        }else {
            btn_chapassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final EditText editText = new EditText(PersonalActivity.this);
                    final InputFilter[] filters = new InputFilter[1];
                    filters[0] = new InputFilter.LengthFilter(36);
                    editText.setFilters(filters);
                    new AlertDialog.Builder(PersonalActivity.this)
                            .setTitle("請輸入您舊的密碼")
                            .setView(editText)
                            .setPositiveButton("確認", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface arg0, int arg1) {
                                    if(editText.getText().toString().equals(sqlReturn.LoginPassword)){
                                        final EditText editText1 = new EditText(PersonalActivity.this);
                                        editText1.setFilters(filters);
                                        new AlertDialog.Builder(PersonalActivity.this)
                                                .setTitle("請輸入您新的密碼")
                                                .setView(editText1)
                                                .setPositiveButton("確認", new DialogInterface.OnClickListener(){
                                                    public void onClick(DialogInterface arg0, int arg1) {
                                                        if(!(editText.getText().toString().equals(""))){
                                                            final EditText editText2 = new EditText(PersonalActivity.this);
                                                            editText2.setFilters(filters);
                                                            new AlertDialog.Builder(PersonalActivity.this)
                                                                    .setTitle("請再次輸入您的密碼")
                                                                    .setView(editText2)
                                                                    .setPositiveButton("確認", new DialogInterface.OnClickListener(){
                                                                        public void onClick(DialogInterface arg0, int arg1) {
                                                                            if(editText1.getText().toString().equals(editText2.getText().toString())){
                                                                                progressBar.setVisibility(View.VISIBLE);
                                                                                password = editText1.getText().toString();
                                                                                userChangePassword();
                                                                            }else{
                                                                                new AlertDialog.Builder(PersonalActivity.this)
                                                                                        .setTitle("兩次輸入密碼不相同")
                                                                                        .setMessage("請再次嘗試")
                                                                                        .setPositiveButton("OK", null)
                                                                                        .show();
                                                                            }
                                                                        }
                                                                    }).setNegativeButton("取消",null).create()
                                                                    .show();
                                                        }
                                                    }
                                                }).setNegativeButton("取消",null).create()
                                                .show();
                                    }else{
                                        new AlertDialog.Builder(PersonalActivity.this)
                                                .setTitle("舊的密碼不正確")
                                                .setMessage("請再次嘗試")
                                                .setPositiveButton("OK", null)
                                                .show();
                                    }
                                }
                            }).setNegativeButton("取消",null).create()
                            .show();
                }
            });
        }
    }

    public void userChangePassword(){
        Map<String,String> map = new HashMap<>();
        map.put("command", "addFriend");
        map.put("uid", sqlReturn.GetUserID);
        map.put("changePass",password);
        new userChangePassword(this).execute((HashMap)map);
    }

    private class userChangePassword extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        userChangePassword(Activity context){
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonObject = null;
            boolean status = false;
            JSONArray jsonArray = null;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;
            try {
                jsonObject = new JSONObject(result);
                status = jsonObject.getBoolean("status");
                if (status){
                    sqlReturn.RegisterPassword = jsonObject.getString("userPass");
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    new AlertDialog.Builder(activity)
                            .setTitle("修改密碼失敗")
                            .setMessage("伺服器維護中，請稍後再嘗試")
                            .setPositiveButton("OK", null)
                            .show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            pageId = getIntent().getIntExtra("pageId",0);
            Intent intent = new Intent(PersonalActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("id",pageId);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
