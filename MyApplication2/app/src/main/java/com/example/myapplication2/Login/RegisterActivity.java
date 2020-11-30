package com.example.myapplication2.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etPasswordCheck;
    private TextView txtMessage;
    private Button btnGoLogin;
    private ProgressBar progressBar;
    private Button btn_addCamera;
    private RoundedImageView roundedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Button imBtnBack = findViewById(R.id.imbtnBackToLogin);

        progressBar = findViewById(R.id.progressBar);
        roundedImageView = findViewById(R.id.roundedImageView);

        imBtnBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                RegisterActivity.this.startActivity(registerIntent);
            }
        });

        btnGoLogin = findViewById(R.id.btnGoLogin);
        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Register();
            }
        });

        btn_addCamera = findViewById(R.id.btn_addCamera);
        btn_addCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });

    }

    //取得相片後返回的監聽式
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //當使用者按下確定後
        if (resultCode == RESULT_OK) {
            //取得圖檔的路徑位置
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();
            try {
                //由抽象資料接口轉換圖檔路徑為Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                //取得圖片控制項ImageView
                // 將Bitmap設定到ImageView
                roundedImageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void Register(){
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPasswordCheck = findViewById(R.id.etPasswordCheck);
        txtMessage = findViewById(R.id.txtMessage);
        String Name = etName.getText().toString();
        String Email = etEmail.getText().toString();
        String password1 = etPassword.getText().toString();
        String password2 = etPasswordCheck.getText().toString();
        if(Name.equals("")){
            if(Email.equals("")){
                if(password1.equals("")){
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("提醒您")
                            .setMessage("未輸入基本資料")
                            .setPositiveButton("了解", null)
                            .show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }else {
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("提醒您")
                            .setMessage("未輸入使用者名稱及帳號")
                            .setPositiveButton("了解", null)
                            .show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
            }else if(password1.equals("")){
                new AlertDialog.Builder(RegisterActivity.this)
                        .setTitle("提醒您")
                        .setMessage("未輸入使用者名稱及密碼")
                        .setPositiveButton("了解", null)
                        .show();
                progressBar.setVisibility(View.INVISIBLE);
                return;
            }else{
                new AlertDialog.Builder(RegisterActivity.this)
                        .setTitle("提醒您")
                        .setMessage("未輸入使用者名稱")
                        .setPositiveButton("了解", null)
                        .show();
                progressBar.setVisibility(View.INVISIBLE);
                return;
            }
        }else if(Email.equals("")){
            if(password1.equals("")){
                new AlertDialog.Builder(RegisterActivity.this)
                        .setTitle("提醒您")
                        .setMessage("未輸入帳號及密碼")
                        .setPositiveButton("了解", null)
                        .show();
                progressBar.setVisibility(View.INVISIBLE);
                return;
            }else{
                new AlertDialog.Builder(RegisterActivity.this)
                        .setTitle("提醒您")
                        .setMessage("未輸入帳號")
                        .setPositiveButton("了解", null)
                        .show();
                progressBar.setVisibility(View.INVISIBLE);
                return;
            }
        }else if(password1.equals("")){
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("提醒您")
                    .setMessage("未輸入密碼")
                    .setPositiveButton("了解", null)
                    .show();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }else{
            if(password1.equals(password2)){
                Map<String,String> map = new HashMap<>();
                map.put("command", "userRegister");
                map.put("pwd", password1);
                map.put("name", Name);
                map.put("email", Email);
                new Regsiter(this).execute((HashMap)map);
            }else{
                new AlertDialog.Builder(RegisterActivity.this)
                        .setTitle("提醒您")
                        .setMessage("密碼不相同")
                        .setPositiveButton("了解", null)
                        .show();
                progressBar.setVisibility(View.INVISIBLE);
                return;
            }
        }

    }
    public class Regsiter extends HttpURLConnection_AsyncTask{
        WeakReference<Activity> activityReference;
        Regsiter(Activity context){
            activityReference = new WeakReference<>(context);
        }
        protected void onPostExecute(String result) {
            JSONObject jsonObject = null;
            boolean status = false;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            try {
                jsonObject = new JSONObject(result);
                status = jsonObject.getBoolean("status");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){

//                Toast.makeText(activity, "註冊成功", Toast.LENGTH_LONG).show();
                // 對Context進行操作
                sqlReturn.RegisterEmail = etEmail.getText().toString();
                sqlReturn.LoginPassword = etPassword.getText().toString();
                sqlReturn.RegisterFirstLogin = false;
                new AlertDialog.Builder(RegisterActivity.this)
                        .setCancelable(false)
                        .setTitle("歡迎您使用Guidary")
                        .setMessage("接下來只要登入就能使用囉!!!")
                        .setPositiveButton("好", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }else {
                new AlertDialog.Builder(activity)
                        .setTitle("註冊失敗")
                        .setMessage("伺服器維護中，請稍後再嘗試")
                        .setPositiveButton("OK", null)
                        .show();
            }
            progressBar.setVisibility(View.INVISIBLE);

        }
    }
}
