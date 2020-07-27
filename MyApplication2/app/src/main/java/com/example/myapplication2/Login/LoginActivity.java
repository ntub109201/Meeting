package com.example.myapplication2.Login;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private static ProgressBar pr1;
    private String mail;
    private String pwd;
    private EditText edUserEmail;
    private EditText edPasswd;
    //public static String GetUserID;
    public static boolean a = false;
    private Button btnLogin;
    private ImageButton imbtnFacebook;
    private ImageButton imbtnGoogle;
//    SignInButton sign_in_button;
    int RC_SIGN_IN = 100; //自己定義的int在onActivityResult才可以抓到是利用Google登入的
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUserEmail = findViewById(R.id.userEmail);
        edUserEmail.setText(sqlReturn.RegisterEmail);
        edPasswd = findViewById(R.id.password);
        edPasswd.setText(sqlReturn.RegisterPassword);

        final TextView test = findViewById(R.id.register2);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,PersonalActivity.class);
                startActivity(intent);
            }
        });

        final TextView registerLink = (TextView) findViewById(R.id.register);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
                LoginActivity.this.startActivity(registerIntent,options.toBundle());
            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        imbtnFacebook = findViewById(R.id.imbtnFacebook);
        imbtnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faceBookIntent = new Intent(LoginActivity.this,FacebookLoginActivity.class);
                LoginActivity.this.startActivity(faceBookIntent);
            }
        });

        imbtnGoogle = findViewById(R.id.imbtnGoogle);
        imbtnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.imbtnGoogle:
                        signIn();
                        break;
                }
            }
        });
//        sign_in_button = findViewById(R.id.sign_in_button);
//        sign_in_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()){
//                    case R.id.sign_in_button:
//                        signIn();
//                        break;
//                }
//            }
//        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // 取得使用者的資料，取得後再看你想要對這些資料做什麼用途
            Log.d("111","handleSignInResult getName:"+account.getDisplayName());
            Log.d("2222","handleSignInResult getEmail:"+account.getEmail());
            Intent intent=new Intent(LoginActivity.this,GoogleLoginActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("3333", "signInResult:failed code=" + e.getStatusCode());

        }
    }
    public void login(){
        edUserEmail = findViewById(R.id.userEmail);
        edPasswd = findViewById(R.id.password);
        pr1 = findViewById(R.id.progressBar1);
        mail = edUserEmail.getText().toString();
        pwd = edPasswd.getText().toString();
        if(mail.equals("")){
            return;
        }else if(pwd.equals("")){
            return;
        }else{
            Map<String,String> map = new HashMap<>();
            map.put("command", "userLogin");
            map.put("email", mail);
            map.put("pwd", pwd);
            new login(this).execute((HashMap)map);
            pr1.setVisibility(View.VISIBLE);
        }


    }

    private class login extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        login(Activity context){
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonObject = null;
            boolean status = false;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            try {
                jsonObject = new JSONObject(result);
                sqlReturn.GetUserID = jsonObject.getString("userID");
                status = jsonObject.getBoolean("status");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                MainActivity.login = true;
                a = true;
                history();
                searchFriend();
            }else {
                new AlertDialog.Builder(activity)
                        .setTitle("登入失敗")
                        .setMessage("請確認帳號或密碼是否正確!!")
                        .setPositiveButton("OK", null)
                        .show();
                pr1.setVisibility(View.INVISIBLE);
            }

        }
    }

    public void history(){
        while (LoginActivity.a){
            String uid = sqlReturn.GetUserID;
            Map<String,String> map = new HashMap<>();
            map.put("command", "history");
            map.put("uid", uid);
            new history(this).execute((HashMap)map);
            break;
        }

    }

    private class history extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        history(Activity context){
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonObject = null;
            JSONArray jsonArray = null;
            boolean status = false;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            try {
                jsonObject = new JSONObject(result);

                sqlReturn.LoginTextViewContext = jsonObject.getString("results");
                sqlReturn.LoginCount = jsonObject.getInt("rowcount");
                jsonArray = new JSONArray(sqlReturn.LoginTextViewContext);
                sqlReturn.LoginContent = new String[sqlReturn.LoginCount];
                sqlReturn.LoginTagName = new String[sqlReturn.LoginCount];
                sqlReturn.LoginMood = new String[sqlReturn.LoginCount];
                sqlReturn.LoginDate = new String[sqlReturn.LoginCount];
                sqlReturn.LoginOption = new String[sqlReturn.LoginCount];
                sqlReturn.LoginDiaryID = new String[sqlReturn.LoginCount];
                for(int i = 0; i<sqlReturn.LoginCount; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.LoginContent[i] = obj.getString("content");
                    sqlReturn.LoginTagName[i] = obj.getString("tagName");
                    if(obj.getString("mood").equals("心情1")){
                        sqlReturn.LoginMood[i] = "晴天";
                    }else if(obj.getString("mood").equals("心情2")){
                        sqlReturn.LoginMood[i] = "時晴";
                    }else if(obj.getString("mood").equals("心情3")){
                        sqlReturn.LoginMood[i] = "多雲";
                    }else if(obj.getString("mood").equals("心情4")){
                        sqlReturn.LoginMood[i] = "陣雨";
                    }else if(obj.getString("mood").equals("心情5")){
                        sqlReturn.LoginMood[i] = "雷雨";
                    }else if(obj.getString("mood").equals("手寫日記心情")){
                        sqlReturn.LoginMood[i] = "手寫日記";
                    }
                    sqlReturn.LoginDate[i] = obj.getString("date");
                    sqlReturn.LoginOption[i] = obj.getString("optionNo");
                    sqlReturn.LoginDiaryID[i] = obj.getString("diaryNo");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (sqlReturn.LoginTextViewContext!=null){
                if(!sqlReturn.RegisterFirstLogin){
                    Intent intent = new Intent(LoginActivity.this, PersonalActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
                    startActivity(intent,options.toBundle());
                }else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
                    intent.putExtra("id",1);
                    startActivity(intent,options.toBundle());
                }
            }else {
                if(!sqlReturn.RegisterFirstLogin){
                    Intent intent = new Intent(LoginActivity.this, PersonalActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
                    startActivity(intent,options.toBundle());
                }else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("id",1);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
                    startActivity(intent,options.toBundle());
                }
            }
        }

    }
    // 此為社群好友貼文全抓
    public void searchFriend(){
        while (LoginActivity.a) {
            String uid = sqlReturn.GetUserID;
            Map<String, String> map = new HashMap<>();
            map.put("command", "friendList");
            map.put("uid", uid);
            new searchFriend(this).execute((HashMap) map);
            break;
        }
    }
    private class searchFriend extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        searchFriend(Activity context){
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonObject = null;
            JSONArray jsonArray = null;
            boolean status = false;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            try {
                jsonObject = new JSONObject(result);

                sqlReturn.textViewContextFriend = jsonObject.getString("results");
                sqlReturn.SearchCountFriend = jsonObject.getInt("rowcount");
                jsonArray = new JSONArray(sqlReturn.textViewContextFriend);
                sqlReturn.contentFriend = new String[sqlReturn.SearchCountFriend];
                sqlReturn.tagNameFriend = new String[sqlReturn.SearchCountFriend];
                sqlReturn.moodFriend = new String[sqlReturn.SearchCountFriend];
                sqlReturn.dateFriend = new String[sqlReturn.SearchCountFriend];
                sqlReturn.friendName = new String[sqlReturn.SearchCountFriend];
                for(int i = 0; i<sqlReturn.SearchCountFriend; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.contentFriend[i] = obj.getString("content");
                    sqlReturn.tagNameFriend[i] = obj.getString("tagName");
                    sqlReturn.moodFriend[i] = obj.getString("mood");
                    sqlReturn.dateFriend[i] = obj.getString("date");
                    sqlReturn.friendName[i] = obj.getString("friendName01");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

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

