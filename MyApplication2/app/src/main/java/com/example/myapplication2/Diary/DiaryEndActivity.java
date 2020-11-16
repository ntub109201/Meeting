package com.example.myapplication2.Diary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.Login.LoginActivity;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DiaryEndActivity extends AppCompatActivity {

    private TextView editText3;
    private String EditDiaryContext;
    private DisplayMetrics mPhone;
    private ImageView imageDiaryGetPhoto;
    private String DiaryContext;
    private ImageButton btn_DiaryEnd;
    private String currentDate;
    private Animation mOpen,mClose;
    private Button btn_sharebestfriend,btn_sharefriend,btn_sharediary;
    private boolean btnChange;
    private String sharefriend = "n", sharebestfriend = "n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_end);

        final String LastView = getIntent().getStringExtra("1");

        mOpen = AnimationUtils.loadAnimation(DiaryEndActivity.this,R.anim.button_open);
        mClose = AnimationUtils.loadAnimation(DiaryEndActivity.this,R.anim.button_close);
        btnChange = false;
        btn_sharebestfriend = findViewById(R.id.btn_sharebestfriend);
        btn_sharefriend = findViewById(R.id.btn_sharefriend);
        btn_sharediary = findViewById(R.id.btn_sharediary);
        btn_sharediary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnChange){
                    btn_sharebestfriend.setAnimation(mClose);
                    btn_sharefriend.setAnimation(mClose);
                    btn_sharebestfriend.setVisibility(View.INVISIBLE);
                    btn_sharebestfriend.setEnabled(false);
                    btn_sharefriend.setVisibility(View.INVISIBLE);
                    btn_sharefriend.setEnabled(false);
                    btnChange = false;
                }else {
                    btn_sharebestfriend.setAnimation(mOpen);
                    btn_sharefriend.setAnimation(mOpen);
                    btn_sharebestfriend.setVisibility(View.VISIBLE);
                    btn_sharebestfriend.setEnabled(true);
                    btn_sharefriend.setVisibility(View.VISIBLE);
                    btn_sharefriend.setEnabled(true);
                    btnChange = true;
                }
            }
        });

        btn_sharefriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharefriend.equals("n")){
                    sharefriend = "y";
                    btn_sharefriend.setBackgroundResource(R.drawable.btn_sharediaryend2);
                }else{
                    sharefriend = "n";
                    btn_sharefriend.setBackgroundResource(R.drawable.btn_sharediaryend);
                }
            }
        });

        btn_sharebestfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharebestfriend.equals("n")){
                    sharebestfriend = "y";
                    btn_sharebestfriend.setBackgroundResource(R.drawable.btn_sharediaryend2);
                }else{
                    sharebestfriend = "n";
                    btn_sharebestfriend.setBackgroundResource(R.drawable.btn_sharediaryend);
                }
            }
        });

        //接收日記
        editText3 = findViewById(R.id.editText3);
        DiaryContext = getIntent().getStringExtra("total");
        editText3.setText(DiaryContext);

        //回到上一頁
        final ImageButton imbtnReturnLast = findViewById(R.id.imbtnReturnLast);
        imbtnReturnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditDiaryContext = editText3.getText().toString();
                DiaryValue.Time = "";
                DiaryValue.EndTime = "123";
                Intent intent = new Intent();
                intent.setClass(DiaryEndActivity.this,DiaryPreviewActivity.class);
                Bundle tagData = new Bundle();
                tagData.putString("1",LastView);
                intent.putExtras(tagData);
                intent.putExtra("Edit", EditDiaryContext);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryEndActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        mPhone = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mPhone);
        final ImageButton getPhoto = findViewById(R.id.DiarygetPhoto);
        imageDiaryGetPhoto = findViewById(R.id.imageDiaryGetPhoto);
        getPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //開啟Pictures畫面Type設定為image
                intent.setType("image/*");
                //使用Intent.ACTION_GET_CONTENT這個Action
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //取得相片後返回本畫面
                startActivityForResult(intent, 1);
            }
        });

        btn_DiaryEnd = findViewById(R.id.btn_DiaryEnd);
        btn_DiaryEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(DiaryEndActivity.this)
                        .setCancelable(false)
                        .setTitle("提醒您")
                        .setMessage("確定完成日記?")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(editText3.getText().toString().equals(DiaryContext)){
                                    DiaryInsert();
                                }else{
                                    DiaryContext = editText3.getText().toString();
                                    DiaryInsert();
                                }
                            }
                        }).setNegativeButton("取消",null).create()
                        .show();
            }
        });

    }

    private String diaryOptionClass = "";
    public void DiaryInsert(){

        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        if(DiaryValue.firstWhat.equals("")){
            diaryOptionClass = DiaryValue.txtWhat;
        }else {
            diaryOptionClass = DiaryValue.firstWhat;
        }
        Map<String,String> map = new HashMap<>();
        map.put("command", "newDiary");
        map.put("uid", sqlReturn.GetUserID);
        map.put("diaryContent",DiaryContext);
        map.put("diaryTag",DiaryValue.txtTag);
        map.put("diaryDate",currentDate);
        map.put("diaryMood", DiaryValue.txtMood);
        map.put("diaryOptionClass", diaryOptionClass);
        map.put("share",sharefriend);
        map.put("bff",sharebestfriend);
        new DiaryInsert(this).execute((HashMap)map);
    }

    private class DiaryInsert extends HttpURLConnection_AsyncTask {

        // 建立弱連結
        WeakReference<Activity> activityReference;
        DiaryInsert(Activity context){
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status){
                DiaryPreviewActivity.totalPlus = "";
                DiaryPreviewActivity.total = "";
                DiaryValue.howCount = 0;
                Toast.makeText(activity, "日記新增成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DiaryEndActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryEndActivity.this);
                startActivity(intent,options.toBundle());
            }else {
                new AlertDialog.Builder(activity)
                        .setTitle("伺服器擁擠中")
                        .setMessage("請重複點選結束按鈕!!")
                        .setPositiveButton("OK", null)
                        .show();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //當使用者按下確定後
        if (resultCode == RESULT_OK) {
            //取得圖檔的路徑位置
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();
            try
            {
                //讀取照片，型態為Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                //判斷照片為橫向或者為直向，並進入ScalePic判斷圖片是否要進行縮放
                if(bitmap.getWidth()>bitmap.getHeight())ScalePic(bitmap,mPhone.heightPixels);
                else ScalePic(bitmap,mPhone.widthPixels);
            }
            catch (FileNotFoundException e)
            {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ScalePic(Bitmap bitmap,int phone)
    {
        //縮放比例預設為1
        float mScale = 1 ;

        //如果圖片寬度大於手機寬度則進行縮放，否則直接將圖片放入ImageView內
        if(bitmap.getWidth() > phone )
        {
            //判斷縮放比例
            mScale = (float)phone/(float)bitmap.getWidth();

            Matrix mMat = new Matrix() ;
            mMat.setScale(mScale, mScale);

            Bitmap mScaleBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),mMat,false);
            imageDiaryGetPhoto.setImageBitmap(mScaleBitmap);
        }
        else imageDiaryGetPhoto.setImageBitmap(bitmap);
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
