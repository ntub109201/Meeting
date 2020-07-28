package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.DatePickerDialog;
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
import java.util.HashMap;
import java.util.Map;

public class ModifyPersonalActivity extends AppCompatActivity {

    private ImageButton imBackHome_ModifyPersonal;
    private Button btnBirthday, btnPassword;
    private EditText edtBirthday, edtPassword, edtEmail, edtName;
    private Spinner spinJob, spinTag;
    private String birthday ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_personal);

        personalData();
        spinJob = findViewById(R.id.spinJob);

        spinTag = findViewById(R.id.spinTag);

        edtEmail = findViewById(R.id.edtEmail);
        edtEmail.setText(sqlReturn.RegisterEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtPassword.setText(sqlReturn.RegisterPassword);
        edtName = findViewById(R.id.edtName);
        //edtName.setText(sqlReturn.PersonalName[0]);
        edtName.setText(sqlReturn.PersonalName);

        final int pageId = getIntent().getIntExtra("pageId",0);
        imBackHome_ModifyPersonal = findViewById(R.id.imBackHome_ModifyPersonal);
        imBackHome_ModifyPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //personalData();
                Intent intent = new Intent(ModifyPersonalActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",pageId);
                startActivity(intent);
            }
        });

        btnBirthday = findViewById(R.id.btnBirthday);
        edtBirthday = findViewById(R.id.edtBirthday);
        //edtBirthday.setText(sqlReturn.PersonalBirthday[0]);
        edtBirthday.setText(sqlReturn.PersonalBirthday);
        btnBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(ModifyPersonalActivity.this,datePickerDlgOnDateSet,now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle("選擇日期");
                datePickerDialog.setMessage("請選擇您的生日日期");
                datePickerDialog.setIcon(android.R.drawable.ic_dialog_info);
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();
            }
        });

    }
    private DatePickerDialog.OnDateSetListener datePickerDlgOnDateSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            edtBirthday.setText(String.valueOf(year)+"/"+String.valueOf(month+1)+"/"+String.valueOf(dayOfMonth));
        }
    };

    // 取個人資料
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
                    sqlReturn.PersonalContext = jsonObject.getString("results");
//                    int rowcount = jsonObject.getInt("rowcount");
//                    jsonArray = new JSONArray(sqlReturn.PersonalContext);
//                    sqlReturn.PersonalName = new String[rowcount];
//                    sqlReturn.PersonalBirthday = new String[rowcount];
//                    sqlReturn.PersonalJob = new String[rowcount];
//                    sqlReturn.PersonalHobby = new String[rowcount];
//                    for(int i = 0; i<rowcount; i++){
//                        JSONObject obj = new JSONObject(String.valueOf(jsonArray.get(i)));
//                        sqlReturn.PersonalName[i] = obj.getString("userName");
//                        sqlReturn.PersonalBirthday[i] = obj.getString("birthday");
//                        sqlReturn.PersonalJob[i] = obj.getString("job");
//                        sqlReturn.PersonalHobby[i] = obj.getString("hobby");
//                    }
                    sqlReturn.PersonalName = jsonObject.getString("userName");
                    sqlReturn.PersonalHobby = jsonObject.getString("hobby");
                    sqlReturn.PersonalJob = jsonObject.getString("job");
                    sqlReturn.PersonalBirthday = jsonObject.getString("birthday");
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            if(status){
                Toast.makeText(activity, "填寫成功", Toast.LENGTH_LONG).show();
            }
        }
    }



    public void sendPersonalData(){
        String uid = sqlReturn.GetUserID;
        //String job = sqlReturn.PersonalJob[0];
        String job = sqlReturn.PersonalJob;
        //String hobby = sqlReturn.PersonalHobby[0];
        String hobby = sqlReturn.PersonalHobby;
        Map<String,String> map = new HashMap<>();
        map.put("command", "newPersonInfo");
        map.put("uid", uid);
        map.put("birthday", birthday);
        map.put("job", job);
        map.put("hobby", hobby);
        new personalData(this).execute((HashMap)map);
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
            }catch (JSONException e){
                e.printStackTrace();
            }
            if(status){
                Toast.makeText(activity, "填寫成功", Toast.LENGTH_LONG).show();
//                sqlReturn.PersonalBirthday[0] = edtBirthday.getText().toString();
//                sqlReturn.PersonalJob[0] = spinJob.getSelectedItem().toString();
//                sqlReturn.PersonalHobby[0] = spinTag.getSelectedItem().toString();
                sqlReturn.PersonalBirthday = edtBirthday.getText().toString();
                sqlReturn.PersonalJob = spinJob.getSelectedItem().toString();
                sqlReturn.PersonalHobby = spinTag.getSelectedItem().toString();
                Intent intent = new Intent(ModifyPersonalActivity.this, MainActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ModifyPersonalActivity.this);
                intent.putExtra("id",1);
                startActivity(intent,options.toBundle());
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
