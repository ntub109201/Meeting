package com.example.myapplication2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ModifyPersonalActivity extends AppCompatActivity {

    private Button imBackPersonal;
    private Button btnSave;
    private EditText edtName;
    private Spinner spinJob, spinTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_personal);

        spinJob = findViewById(R.id.spinJob);
        switch (sqlReturn.PersonalJob){
            case "學生":
                spinJob.setSelection(0);
                break;
            case "上班族":
                spinJob.setSelection(1);
                break;
            case "農業":
                spinJob.setSelection(2);
                break;
            case "漁業":
                spinJob.setSelection(3);
                break;
            case "交通運輸業":
                spinJob.setSelection(4);
                break;
            case "餐旅業":
                spinJob.setSelection(5);
                break;
            case "製造業":
                spinJob.setSelection(6);
                break;
            case "娛樂業":
                spinJob.setSelection(7);
                break;
            case "其他":
                spinJob.setSelection(8);
                break;
            default:
                spinJob.setSelection(0);
                break;
        }
        spinTag = findViewById(R.id.spinTag);
        switch (sqlReturn.PersonalHobby){
            case "美食":
                spinTag.setSelection(0);
                break;
            case "購物":
                spinTag.setSelection(1);
                break;
            case "戀愛":
                spinTag.setSelection(2);
                break;
            case "旅遊":
                spinTag.setSelection(3);
                break;
            case "休閒娛樂":
                spinTag.setSelection(4);
                break;
            case "其他":
                spinTag.setSelection(5);
                break;
            default:
                spinTag.setSelection(0);
                break;
        }


        edtName = findViewById(R.id.edtName);
        edtName.setText(sqlReturn.PersonalName);

        final int pageId = getIntent().getIntExtra("pageId",0);
        imBackPersonal = findViewById(R.id.imBackPersonal);
        imBackPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyPersonalActivity.this, PersonalActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",pageId);
                startActivity(intent);
            }
        });

        if(sqlReturn.firstUse == true){
            sendPersonalData();
            sqlReturn.firstUse = false;
        }


        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ModifyPersonalActivity.this)
                        .setCancelable(false)
                        .setTitle("提醒您")
                        .setMessage("確定修改個人資料?")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sendPersonalData();
                            }
                        }).setNegativeButton("取消",null).create()
                        .show();
            }
        });

    }


    public void sendPersonalData(){
        String uid = sqlReturn.GetUserID;
        String job = spinJob.getSelectedItem().toString();
        String hobby = spinTag.getSelectedItem().toString();
        String userName = edtName.getText().toString();
        String email = sqlReturn.RegisterEmail;
        Map<String,String> map = new HashMap<>();
        map.put("command", "newPersonInfo");
        map.put("uid", uid);
        map.put("job", job);
        map.put("hobby", hobby);
        map.put("userName",userName);
        map.put("email",email);
        new sendPersonalData(this).execute((HashMap)map);
    }
    private class sendPersonalData extends HttpURLConnection_AsyncTask {
        // 建立弱連結
        WeakReference<Activity> activityReference;
        sendPersonalData(Activity context){
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
                if(status){
                    Toast.makeText(activity, "修改成功", Toast.LENGTH_LONG).show();
                    sqlReturn.PersonalJob = spinJob.getSelectedItem().toString();
                    sqlReturn.PersonalHobby = spinTag.getSelectedItem().toString();
                    sqlReturn.PersonalName = edtName.getText().toString();
                    Intent intent = new Intent(ModifyPersonalActivity.this,PersonalActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    final int pageId = getIntent().getIntExtra("pageId",0);
                    intent.putExtra("id",pageId);
                    startActivity(intent);
                }
            }catch (JSONException e){
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
