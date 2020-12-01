package com.example.myapplication2.Login;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;



public class LoginActivity extends AppCompatActivity {
    private static ProgressBar pr1;
    private String mail;
    private String pwd;
    private String uid;
    private String name;
    private EditText edUserEmail;
    private EditText edPasswd;
    public static boolean a = false;
    private Button btnLogin;
    int RC_SIGN_IN = 100; //自己定義的int在onActivityResult才可以抓到是利用Google登入的
    GoogleSignInClient mGoogleSignInClient;
    SignInButton sign_in;
    private boolean googleIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pr1 = findViewById(R.id.progressBar1);

        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(36);

        edUserEmail = findViewById(R.id.userEmail);
        edUserEmail.setFilters(filters);
        edUserEmail.setText(sqlReturn.RegisterEmail);
        edPasswd = findViewById(R.id.password);
        edPasswd.setFilters(filters);
        edPasswd.setText(sqlReturn.LoginPassword);
        sign_in = findViewById(R.id.sign_in_button);

        final Button registerLink = findViewById(R.id.register);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                googleIn = true;
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }

        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    //google登入開始
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
            Log.d("121","handleSignInResult getUid:"+account.getId());
            Log.d("111","handleSignInResult getName:"+account.getDisplayName());
            Log.d("2222","handleSignInResult getEmail:"+account.getEmail());
            uid = account.getId();
            name = account.getDisplayName();
            mail = account.getEmail();
//            Intent intent = new Intent(LoginActivity.this,GoogleLoginSignOutActivity.class);
//            startActivity(intent);
            googleLogin();
        } catch (ApiException e) {
            Log.w("3333", "signInResult:failed code=" + e.getStatusCode());
        }
    }
//google登入結束

    private String password = "";
    private String userId = "";

    public void login(){
        edUserEmail = findViewById(R.id.userEmail);
        edPasswd = findViewById(R.id.password);
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
                userId = jsonObject.getString("userID");
                password = jsonObject.getString("userPass");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                MainActivity.login = true;
                a = true;
                history();
                searchFriend();
                personalData();
                searchBestFriendList();
                searchFriendList();
                sqlReturn.LoginPassword = edPasswd.getText().toString();
                sqlReturn.RegisterEmail = edUserEmail.getText().toString();
                sqlReturn.RegisterPassword = password;
                sqlReturn.GetUserID = userId;
            }else {
                new AlertDialog.Builder(activity)
                        .setTitle("登入失敗")
                        .setMessage("伺服器維護中，請稍後再嘗試")
                        .setPositiveButton("OK", null)
                        .show();
            }


        }
    }



    private String message;
    // Google登入
    public void googleLogin(){
        Map<String,String> map = new HashMap<>();
        map.put("command", "googleLogin");
        map.put("googleID", uid);
        map.put("userName", "google藍允謙");
        //map.put("mail", mail);
        pr1.setVisibility(View.VISIBLE);
        new googleLogin(this).execute((HashMap)map);
    }

    private class googleLogin extends HttpURLConnection_AsyncTask{
        // 建立弱連結
        WeakReference<Activity> activityReference;
        googleLogin(Activity context){
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
                status = jsonObject.getBoolean("status");
                message = jsonObject.getString("message");
                sqlReturn.GetUserID = uid;
                sqlReturn.RegisterEmail = mail;
                sqlReturn.PersonalName = name;
            }catch (JSONException e){
                e.printStackTrace();
            }

            if(status == true){
                MainActivity.login = true;
                a = true;
                sqlReturn.googleLogin = true;
                history();
                searchFriend();
                personalData();
                searchBestFriendList();
                searchFriendList();
                mGoogleSignInClient.signOut();
            }else{
                mGoogleSignInClient.signOut();
                new AlertDialog.Builder(activity)
                        .setTitle("信箱登入失敗")
                        .setMessage("伺服器維護中，請稍後再嘗試")
                        .setPositiveButton("OK", null)
                        .show();
                Log.d("222","error");
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

            if(sqlReturn.LoginTextViewContext!=null){
                LoginAccess();
            }else {
                LoginAccess();
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
                sqlReturn.friendImage = new String[sqlReturn.SearchCountFriend];
                for(int i = 0; i<sqlReturn.SearchCountFriend; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.contentFriend[i] = obj.getString("content");
                    sqlReturn.tagNameFriend[i] = obj.getString("tagName");
                    sqlReturn.moodFriend[i] = obj.getString("mood");
                    sqlReturn.dateFriend[i] = obj.getString("date");
                    sqlReturn.friendName[i] = obj.getString("friendName01");
                    sqlReturn.friendImage[i] = obj.getString("image_path");
                }
                if(sqlReturn.SearchCountFriend>0){
                    sqlReturn.check_friend = true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    // 取加入好友
    public void personalData(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "getInfo");
        map.put("uid", uid);
        new personalData(this).execute((HashMap)map);
    }
    private class personalData extends HttpURLConnection_AsyncTask {
        // 建立弱連結
        WeakReference<Activity> activityReference;
        personalData(Activity context){
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonObject = null;
            //JSONArray jsonArray = null;
            boolean status = false;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            try {
                jsonObject = new JSONObject(result);
                status = jsonObject.getBoolean("status");
                if(status){
                    sqlReturn.PersonalName = jsonObject.getString("userName");
                    sqlReturn.PersonalHobby = jsonObject.getString("hobby");
                    sqlReturn.PersonalJob = jsonObject.getString("job");
                    sqlReturn.PersonalBirthday = jsonObject.getString("birthday");
                    if(sqlReturn.PersonalBirthday.equals("null")){
                        sqlReturn.PersonalBirthday = "";
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    public void searchBestFriendList(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "bestFriendInfoList");
        map.put("uid", uid);
        new searchBestFriendList(this).execute((HashMap)map);
    }

    private class searchBestFriendList extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        searchBestFriendList(Activity context){
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
                sqlReturn.SearchCountBestFriendList = jsonObject.getInt("rowcount");
                sqlReturn.textViewContextBestFriendList = jsonObject.getString("results");
                jsonArray = new JSONArray(sqlReturn.textViewContextBestFriendList);
                sqlReturn.BestFriendListName = new String[sqlReturn.SearchCountBestFriendList];
                sqlReturn.BestFriendListNum = new String[sqlReturn.SearchCountBestFriendList];
                for(int i = 0; i<sqlReturn.SearchCountBestFriendList; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.BestFriendListName[i] = obj.getString("friendName01");
                    sqlReturn.BestFriendListNum[i] = obj.getString("friendNum");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchFriendList(){
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "friendInfoList");
        map.put("uid", uid);
        new searchFriendList(this).execute((HashMap)map);
    }

    private class searchFriendList extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        searchFriendList(Activity context){
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
                sqlReturn.SearchCountFriendList = jsonObject.getInt("rowcount");
                sqlReturn.textViewContextFriendList = jsonObject.getString("results");
                jsonArray = new JSONArray(sqlReturn.textViewContextFriendList);
                sqlReturn.friendListNum = new String[sqlReturn.SearchCountFriendList];
                sqlReturn.friendListName = new String[sqlReturn.SearchCountFriendList];
                sqlReturn.friendListBFF = new String[sqlReturn.SearchCountFriendList];
                for(int i = 0; i<sqlReturn.SearchCountFriendList; i++){
                    JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
                    sqlReturn.friendListNum[i] = obj.getString("friendNum");
                    sqlReturn.friendListName[i] = obj.getString("friendName01");
                    sqlReturn.friendListBFF[i] = obj.getString("BFF");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void LoginAccess(){
        if(googleIn == true){
            if(!sqlReturn.RegisterFirstLogin){
                sqlReturn.RegisterFirstLogin = true;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
                intent.putExtra("id",1);
                startActivity(intent,options.toBundle());
            }else {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
                intent.putExtra("id",1);
                startActivity(intent,options.toBundle());
            }
        }else {
            if(!sqlReturn.RegisterFirstLogin){
                if(sqlReturn.RegisterEmail == edUserEmail.getText().toString()){
                    Intent intent = new Intent(LoginActivity.this, FirstLoginActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
                    startActivity(intent,options.toBundle());
                }else {
                    sqlReturn.RegisterFirstLogin = true;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
                    intent.putExtra("id",1);
                    startActivity(intent,options.toBundle());
                }
            }else {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
                intent.putExtra("id",1);
                startActivity(intent,options.toBundle());
            }
        }
        pr1.setVisibility(View.INVISIBLE);
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

