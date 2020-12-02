package com.example.myapplication2.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.User.ApiService;
import com.example.myapplication2.User.FileUtils;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModifyPersonalActivity extends AppCompatActivity {

    private Button imBackPersonal;
    private Button btnSave,btn_setuserphoto;
    private EditText edtName;
    private Spinner spinJob, spinTag;
    private int pageId;
    private ImageView imageView;
    private static String userId = "";
    private ArrayList<Uri> arrayList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_personal);

        progressBar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.imageView);
        new AsyncTask<String, Void, Bitmap>(){
            @Override
            protected Bitmap doInBackground(String... params) //實作doInBackground
            {
                String url = params[0];
                return getBitmapFromURL(url);
            }

            @Override
            protected void onPostExecute(Bitmap result) //當doinbackground完成後
            {
                imageView.setImageBitmap(result);
                super.onPostExecute(result);
            }
        }.execute(sqlReturn.PersonalPicture);

        btn_setuserphoto = findViewById(R.id.btn_setuserphoto);
        btn_setuserphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList = new ArrayList<>();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });

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
        if(sqlReturn.googleLogin){
            edtName.setEnabled(false);
        }else {
            edtName.setEnabled(true);
        }
        edtName.setText(sqlReturn.PersonalName);

        pageId = getIntent().getIntExtra("pageId",0);
        imBackPersonal = findViewById(R.id.imBackPersonal);
        imBackPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyPersonalActivity.this, PersonalActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("pageId",pageId);
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
                sendPersonalData();
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
            arrayList.add(uri);
            ContentResolver cr = this.getContentResolver();
            try {
                //由抽象資料接口轉換圖檔路徑為Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                //取得圖片控制項ImageView
                // 將Bitmap設定到ImageView
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
        map.put("userPass",sqlReturn.RegisterPassword);
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
                userId = jsonObject.getString("userID");
                if (userId.equals("")){
                    new AlertDialog.Builder(activity)
                            .setTitle("修改失敗")
                            .setMessage("伺服器維護中，請稍後再嘗試")
                            .setPositiveButton("OK", null)
                            .show();
                }else {
                    //Toast.makeText(activity, "修改成功", Toast.LENGTH_LONG).show();
                    sqlReturn.PersonalJob = spinJob.getSelectedItem().toString();
                    sqlReturn.PersonalHobby = spinTag.getSelectedItem().toString();
                    sqlReturn.PersonalName = edtName.getText().toString();
                    if(sqlReturn.googleLogin){
                        finish();
                    }else {
                        uploadImagesToServer();
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    private static Bitmap getBitmapFromURL(String imageUrl)
    {
        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private void uploadImagesToServer() {
        if (InternetConnection.checkConnection(ModifyPersonalActivity.this)) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(com.example.myapplication2.Login.ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            showProgress();
            // create list of file parts (photo, video, ...)
            List<MultipartBody.Part> parts = new ArrayList<>();

            // create upload service client
            ApiService service = retrofit.create(ApiService.class);


            try {
                if (arrayList != null) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        parts.add(prepareFilePart("image" + i, arrayList.get(i)));
                    }
                }
            }catch (Exception e){

            }

            RequestBody description = createPartFromString("https://10836008.000webhostapp.com");
            RequestBody size = createPartFromString(""+parts.size());
            RequestBody uidToserver = createPartFromString(userId);
            RequestBody picTarget = createPartFromString("user");

            // finally, execute the request
            Call<ResponseBody> call = service.uploadMultiple(description, size,uidToserver,picTarget, parts);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    hideProgress();
                    if(response.isSuccessful()) {
                        finish();
                    } else {
                        Snackbar.make(findViewById(android.R.id.content),
                                R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    hideProgress();
                    Snackbar.make(findViewById(android.R.id.content),
                            "Image upload failed!", Snackbar.LENGTH_LONG).show();
                }
            });

        } else {
            hideProgress();
        }
    }
    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);

    }
    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(MediaType.parse(FileUtils.MIME_TYPE_TEXT), descriptionString);
        //return RequestBody.create(MediaType.parse(FileUtils.MIME_TYPE_IMAGE), descriptionString);
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(this, fileUri);

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create (MediaType.parse(FileUtils.MIME_TYPE_IMAGE), file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            pageId = getIntent().getIntExtra("pageId",0);
            Intent intent = new Intent(ModifyPersonalActivity.this, PersonalActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("pageId",pageId);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
