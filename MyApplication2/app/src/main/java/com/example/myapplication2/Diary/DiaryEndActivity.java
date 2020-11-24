package com.example.myapplication2.Diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.myapplication2.Diary.ApiService;
import com.example.myapplication2.Diary.FileUtils;
import com.example.myapplication2.sqlReturn;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
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

public class DiaryEndActivity extends AppCompatActivity {

    private TextView editText3;
    private String EditDiaryContext;
    private DisplayMetrics mPhone;
    public String DiaryContext;
    private ImageButton btn_DiaryEnd;
    private String currentDate;
    private Animation mOpen,mClose;
    private Button btn_sharebestfriend,btn_sharefriend,btn_sharediary;
    private boolean btnChange;
    private String sharefriend = "n", sharebestfriend = "n";
    private RecyclerView recyclerview;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private DiaryEndActivity.MyAdapter myAdapter;
    private LinkedList<HashMap<String,String>> data1;
    private int currentItem = 0;
    //多張圖片
    private static final String TAG = DiaryEndActivity.class.getSimpleName();
    private Uri imageUri;
    private ArrayList<Uri> arrayList;
    private final int REQUEST_CODE_PERMISSIONS  = 1;
    private final int REQUEST_CODE_READ_STORAGE = 2;
    private CardView noImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_end);

        noImageView = findViewById(R.id.noImageView);

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(mLayoutManager);
        myAdapter = new MyAdapter();
        recyclerview.setAdapter(myAdapter);
        doData();


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
                    sharebestfriend = "y";
                    btn_sharefriend.setBackgroundResource(R.drawable.btn_sharediaryend2);
                }else{
                    sharefriend = "n";
                    sharebestfriend = "n";
                    btn_sharefriend.setBackgroundResource(R.drawable.btn_sharediaryend);
                }
            }
        });

        btn_sharebestfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharebestfriend.equals("n")){
                    sharebestfriend = "y";
                    sharefriend = "n";
                    btn_sharebestfriend.setBackgroundResource(R.drawable.btn_sharediaryend2);
                }else{
                    sharebestfriend = "n";
                    sharefriend = "n";
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
                DiaryEndActivity.this.finish();
            }
        });

        mPhone = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mPhone);
        final ImageButton getPhoto = findViewById(R.id.DiarygetPhoto);
        getPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noImageView.setVisibility(View.INVISIBLE);
                arrayList = new ArrayList<>();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    askForPermission();
                } else {
                    showChooser();
                }
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
                                DiaryInsert();
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
                DiaryValue.txtTag = "";
                DiaryValue.txtMood = "";
                DiaryValue.firstWhat = "";
                DiaryValue.txtWhat = "";
                DiaryValue.txtWhy = "";
                DiaryValue.txtWhere = "";
                DiaryValue.txtWhen = "";
                DiaryValue.txtWho = "";
                DiaryValue.Time = "";
                DiaryValue.EndTime = "";
                DiaryValue.howCount = 0;
                DiaryValue.Eye_Count = 0;
                DiaryValue.Mouth_Count = 0;
                DiaryValue.Smell_Count = 0;
                DiaryPreviewActivity.total = "";
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

    private void showChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_READ_STORAGE);
    }

    private void doData(){
        data1 = new LinkedList<>();
        for(int i = 0; i < currentItem; i++){
            HashMap<String,String> row = new HashMap<>();
            data1.add(row);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public ImageView imgPhoto;
            public MyViewHolder(View view){
                super(view);
                itemView = view;
                imgPhoto = itemView.findViewById(R.id.imgPhoto);

            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.handwrite_item,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.imgPhoto.setImageURI(arrayList.get(position));
        }

        @Override
        public int getItemCount() {
            return data1.size();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == REQUEST_CODE_READ_STORAGE) {
            if (resultData != null) {
                if (resultData.getClipData() != null) {
                    int count = resultData.getClipData().getItemCount();
                    currentItem = 0;
                    while (currentItem < count) {
                        imageUri = resultData.getClipData().getItemAt(currentItem).getUri();
                        currentItem = currentItem + 1;

                        Log.d("Uri Selected", imageUri.toString());

                        try {
                            arrayList.add(imageUri);
                            recyclerview.setHasFixedSize(false);
                            mLayoutManager = new LinearLayoutManager(this);
                            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            recyclerview.setLayoutManager(mLayoutManager);
                            MyAdapter MyAdapter = new MyAdapter();
                            recyclerview.setAdapter(MyAdapter);
                            doData();
                        } catch (Exception e) {
                            Log.e(TAG, "File select error", e);
                        }
                    }
                } else if (resultData.getData() != null) {
                    currentItem = 0;
                    currentItem = currentItem + 1;
                    imageUri = resultData.getData();
                    Log.i(TAG, "Uri = " + imageUri.toString());

                    try {
                        arrayList.add(imageUri);
                        recyclerview.setHasFixedSize(false);
                        mLayoutManager = new LinearLayoutManager(this);
                        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recyclerview.setLayoutManager(mLayoutManager);
                        MyAdapter MyAdapter = new MyAdapter();
                        recyclerview.setAdapter(MyAdapter);
                        doData();
                    } catch (Exception e) {
                        Log.e(TAG, "File select error", e);
                    }
                }
            }
        }
    }

    private void uploadImagesToServer() {
        if (InternetConnection.checkConnection(DiaryEndActivity.this)) {

//            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .readTimeout(5, TimeUnit.MINUTES)
//                    .connectTimeout(5, TimeUnit.MINUTES)
//                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(okHttpClient)
                    .build();

            showProgress();
            // create list of file parts (photo, video, ...)
            List<MultipartBody.Part> parts = new ArrayList<>();

            // create upload service client
            ApiService service = retrofit.create(ApiService.class);


            try {
                if (arrayList != null) {
                    // create part for file (photo, video, ...)
                    for (int i = 0; i < arrayList.size(); i++) {
                        parts.add(prepareFilePart("image" + i, arrayList.get(i)));
                    }
                }
            }catch (Exception e){
                Log.e(TAG, "File select error", e);
            }
            // create a map of data to pass along
            RequestBody description = createPartFromString("https://10836008.000webhostapp.com");
            RequestBody size = createPartFromString(""+parts.size());

            // finally, execute the request
            Call<ResponseBody> call = service.uploadMultiple(description, size, parts);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    hideProgress();
                    if(response.isSuccessful()) {
                        Toast.makeText(DiaryEndActivity.this,
                                "Images successfully uploaded!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DiaryEndActivity.this,MainActivity.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryEndActivity.this);
                        intent.putExtra("id",1);
                        startActivity(intent,options.toBundle());
                    } else {
                        Snackbar.make(findViewById(android.R.id.content),
                                R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    hideProgress();
                    Log.e(TAG, "Image upload failed!", t);
                    Snackbar.make(findViewById(android.R.id.content),
                            "Image upload failed!", Snackbar.LENGTH_LONG).show();
                }
            });

        } else {
            hideProgress();
            Toast.makeText(DiaryEndActivity.this,
                    R.string.string_internet_connection_not_available, Toast.LENGTH_SHORT).show();
        }
    }
    private void showProgress() {
//        new AlertDialog.Builder(HandwriteActivity.this)
//                .setTitle("提醒您")
//                .setMessage("上傳時間較常請耐心等候")
//                .setPositiveButton("了解", null)
//                .show();
//        progressBarHandWrite.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
//        progressBarHandWrite.setVisibility(View.INVISIBLE);

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

    /**
     *  Runtime Permission
     */
    private void askForPermission() {
        if ((ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) +
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE))
                != PackageManager.PERMISSION_GRANTED) {
            /* Ask for permission */
            // need to request permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Snackbar.make(this.findViewById(android.R.id.content),
                        "Please grant permissions to write data in sdcard",
                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        v -> ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_CODE_PERMISSIONS)).show();
            } else {
                /* Request for permission */
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_PERMISSIONS);
            }

        } else {
            showChooser();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                showChooser();
            } else {
                // Permission Denied
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(DialogInterface.OnClickListener okListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.setMessage("You need to grant access to Read External Storage")
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create();

        dialog.setOnShowListener(arg0 -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                    ContextCompat.getColor(this, android.R.color.holo_blue_light));
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                    ContextCompat.getColor(this, android.R.color.holo_red_light));
        });

        dialog.show();
    }


    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            DiaryEndActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
