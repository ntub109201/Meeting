package com.example.myapplication2.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PersonalActivity extends AppCompatActivity {

    private Button goToMain,btnBirthday;
    private EditText edtBirthday;
    private TextView txtSkip;
    private Spinner spinGender, spinJob, spinTag;

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
        goToMain = findViewById(R.id.goToMain);
        goToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //personalData();
                Intent intent = new Intent(PersonalActivity.this, MainActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PersonalActivity.this);
                intent.putExtra("id",1);
                startActivity(intent,options.toBundle());
            }
        });
        edtBirthday = findViewById(R.id.edtBirthday);
        btnBirthday = findViewById(R.id.btnBirthday);
        btnBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(PersonalActivity.this,datePickerDlgOnDateSet,now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle("選擇日期");
                datePickerDialog.setMessage("請選擇您的生日日期");
                datePickerDialog.setIcon(android.R.drawable.ic_dialog_info);
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();
            }
        });
        txtSkip = findViewById(R.id.txtSkip);
        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PersonalActivity.this)
                        .setTitle("提醒您")
                        .setMessage("之後可以再到個人資料修改歐!!!")
                        .setPositiveButton("OK", null)
                        .show();
//                Intent intent = new Intent(PersonalActivity.this, MainActivity.class);
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PersonalActivity.this);
//                intent.putExtra("id",1);
//                startActivity(intent,options.toBundle());
            }

        });

    }

    public void personalData(){
        spinGender = findViewById(R.id.spinGender);
        spinJob = findViewById(R.id.spinJob);
        spinTag = findViewById(R.id.spinTag);
        String gender = spinGender.getSelectedItem().toString();
        String job = spinJob.getSelectedItem().toString();
        String hobby = spinTag.getSelectedItem().toString();
        String birthday = edtBirthday.getText().toString();
        String uid = sqlReturn.GetUserID;
        Map<String,String> map = new HashMap<>();
        map.put("command", "newPersonInfo");
        map.put("uid", uid);
        map.put("birthday", birthday);
        map.put("job", job);
        map.put("hobby", hobby);
        map.put("gender", gender);
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
            JSONArray jsonArray = null;
            boolean status = false;
            // 取得弱連結的Context
            Activity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            try {
                jsonObject = new JSONObject(result);
                status = jsonObject.getBoolean("status");
                if(status){

                }

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
    private DatePickerDialog.OnDateSetListener datePickerDlgOnDateSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            edtBirthday.setText(String.valueOf(year)+"/"+String.valueOf(month+1)+"/"+String.valueOf(dayOfMonth));
        }
    };
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
