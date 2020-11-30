package com.example.myapplication2.takePhoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class TakePhotoActivity extends AppCompatActivity {

    private boolean paper_sunnyClick = false, paper_mcClick = false, paper_cloudyClick = false, paper_thunderClick = false, paper_rainClick = false;
    private boolean paper_tripClick = false, paper_shoppingClick = false, paper_loveClick = false, paper_foodClick = false, paper_casualClick = false;
    private String mood = "";
    private String tag = "";
    private int countMood = 0, countTag = 0;
    private ProgressBar progressBar;
    private Button btn_addphoto;

    private RecyclerView recyclerview;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private LinkedList<HashMap<String,String>> data1;
    private TakePhotoActivity.MyAdapter myAdapter;
    private Bitmap mbmp;
    private Uri imageUri;
    private ArrayList<Uri> arrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_c_r);

        arrayList = new ArrayList<>();

        Intent intent = getIntent();
        String filePath = intent.getStringExtra("fileName");
        byte[] decodedBytes = Base64.decode(filePath,Base64.DEFAULT);
        mbmp = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

        String uri = intent.getStringExtra("Uri");
        imageUri = Uri.parse(uri);
        arrayList.add(imageUri);

        progressBar = findViewById(R.id.progressBar);

        btn_addphoto = findViewById(R.id.btn_addphoto);

        recyclerview = findViewById(R.id.recyclerview);


        recyclerview.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(TakePhotoActivity.this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(mLayoutManager);
        myAdapter = new MyAdapter();
        recyclerview.setAdapter(myAdapter);
        doData();


        final ImageButton btn_back_paper = findViewById(R.id.btn_back_paper);
        btn_back_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TakePhotoActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });

        final Button btn_paper_sunny = findViewById(R.id.btn_paper_sunny);
        final Button btn_paper_mc = findViewById(R.id.btn_paper_mc);
        final Button btn_paper_cloudy = findViewById(R.id.btn_paper_cloudy);
        final Button btn_paper_thunder = findViewById(R.id.btn_paper_thunder);
        final Button btn_paper_rain = findViewById(R.id.btn_paper_rain);
        final Button btn_paper_trip = findViewById(R.id.btn_paper_trip);
        final Button btn_paper_shopping = findViewById(R.id.btn_paper_shopping);
        final Button btn_paper_love = findViewById(R.id.btn_paper_love);
        final Button btn_paper_food = findViewById(R.id.btn_paper_food);
        final Button btn_paper_casual = findViewById(R.id.btn_paper_casual);


        btn_paper_sunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_sunnyClick){
                    paper_sunnyClick = false;
                    btn_paper_sunny.setBackgroundTintList(getColorStateList(R.color.white));
                    mood = "";
                }else if(!paper_sunnyClick){
                    paper_sunnyClick = true;
                    paper_mcClick = false;
                    paper_cloudyClick = false;
                    paper_thunderClick = false;
                    paper_rainClick = false;
                    btn_paper_sunny.setBackgroundTintList(getColorStateList(R.color.orange));
                    btn_paper_mc.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_cloudy.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_thunder.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_rain.setBackgroundTintList(getColorStateList(R.color.white));
                    mood = "心情1";
                }
            }
        });

        btn_paper_mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_mcClick){
                    paper_mcClick = false;
                    btn_paper_mc.setBackgroundTintList(getColorStateList(R.color.white));
                    mood = "";
                }else if(!paper_mcClick){
                    paper_sunnyClick = false;
                    paper_mcClick = true;
                    paper_cloudyClick = false;
                    paper_thunderClick = false;
                    paper_rainClick = false;
                    btn_paper_sunny.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_mc.setBackgroundTintList(getColorStateList(R.color.orange));
                    btn_paper_cloudy.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_thunder.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_rain.setBackgroundTintList(getColorStateList(R.color.white));
                    mood = "心情2";
                }
            }
        });

        btn_paper_cloudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_cloudyClick){
                    paper_cloudyClick = false;
                    btn_paper_cloudy.setBackgroundTintList(getColorStateList(R.color.white));
                    mood = "";
                }else if(!paper_cloudyClick){
                    paper_sunnyClick = false;
                    paper_mcClick = false;
                    paper_cloudyClick = true;
                    paper_thunderClick = false;
                    paper_rainClick = false;
                    btn_paper_sunny.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_mc.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_cloudy.setBackgroundTintList(getColorStateList(R.color.orange));
                    btn_paper_thunder.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_rain.setBackgroundTintList(getColorStateList(R.color.white));
                    mood = "心情3";
                }
            }
        });

        btn_paper_rain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_rainClick){
                    paper_rainClick = false;
                    btn_paper_rain.setBackgroundTintList(getColorStateList(R.color.white));
                    mood = "";
                }else if(!paper_rainClick){
                    paper_sunnyClick = false;
                    paper_mcClick = false;
                    paper_cloudyClick = false;
                    paper_thunderClick = false;
                    paper_rainClick = true;
                    btn_paper_sunny.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_mc.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_cloudy.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_thunder.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_rain.setBackgroundTintList(getColorStateList(R.color.orange));
                    mood = "心情4";
                }
            }
        });

        btn_paper_thunder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_thunderClick){
                    paper_thunderClick = false;
                    btn_paper_thunder.setBackgroundTintList(getColorStateList(R.color.white));
                    mood = "";
                }else if(!paper_thunderClick){
                    paper_sunnyClick = false;
                    paper_mcClick = false;
                    paper_cloudyClick = false;
                    paper_thunderClick = true;
                    paper_rainClick = false;
                    btn_paper_sunny.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_mc.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_cloudy.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_thunder.setBackgroundTintList(getColorStateList(R.color.orange));
                    btn_paper_rain.setBackgroundTintList(getColorStateList(R.color.white));
                    mood = "心情5";
                }
            }
        });

        btn_paper_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_tripClick){
                    paper_tripClick = false;
                    btn_paper_trip.setBackgroundTintList(getColorStateList(R.color.white));
                    tag = "";
                }else if(!paper_tripClick){
                    paper_tripClick = true;
                    paper_shoppingClick = false;
                    paper_loveClick = false;
                    paper_foodClick = false;
                    paper_casualClick = false;
                    btn_paper_trip.setBackgroundTintList(getColorStateList(R.color.orange));
                    btn_paper_shopping.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_love.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_food.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_casual.setBackgroundTintList(getColorStateList(R.color.white));
                    tag = "旅遊";
                }
            }
        });

        btn_paper_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_shoppingClick){
                    paper_shoppingClick = false;
                    btn_paper_shopping.setBackgroundTintList(getColorStateList(R.color.white));
                    tag = "";
                }else if(!paper_shoppingClick){
                    paper_tripClick = false;
                    paper_shoppingClick = true;
                    paper_loveClick = false;
                    paper_foodClick = false;
                    paper_casualClick = false;
                    btn_paper_trip.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_shopping.setBackgroundTintList(getColorStateList(R.color.orange));
                    btn_paper_love.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_food.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_casual.setBackgroundTintList(getColorStateList(R.color.white));
                    tag = "購物";
                }
            }
        });

        btn_paper_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_loveClick){
                    paper_loveClick = false;
                    btn_paper_love.setBackgroundTintList(getColorStateList(R.color.white));
                    tag = "";
                }else if(!paper_loveClick){
                    paper_tripClick = false;
                    paper_shoppingClick = false;
                    paper_loveClick = true;
                    paper_foodClick = false;
                    paper_casualClick = false;
                    btn_paper_trip.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_shopping.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_love.setBackgroundTintList(getColorStateList(R.color.orange));
                    btn_paper_food.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_casual.setBackgroundTintList(getColorStateList(R.color.white));
                    tag = "戀愛";
                }
            }
        });

        btn_paper_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_foodClick){
                    paper_foodClick = false;
                    btn_paper_food.setBackgroundTintList(getColorStateList(R.color.white));
                    tag = "";
                }else if(!paper_foodClick){
                    paper_tripClick = false;
                    paper_shoppingClick = false;
                    paper_loveClick = false;
                    paper_foodClick = true;
                    paper_casualClick = false;
                    btn_paper_trip.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_shopping.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_love.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_food.setBackgroundTintList(getColorStateList(R.color.orange));
                    btn_paper_casual.setBackgroundTintList(getColorStateList(R.color.white));
                    tag = "美食";
                }
            }
        });

        btn_paper_casual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_casualClick){
                    paper_casualClick = false;
                    btn_paper_casual.setBackgroundTintList(getColorStateList(R.color.white));
                    tag = "";
                }else if(!paper_casualClick){
                    paper_tripClick = false;
                    paper_shoppingClick = false;
                    paper_loveClick = false;
                    paper_foodClick = false;
                    paper_casualClick = true;
                    btn_paper_trip.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_shopping.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_love.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_food.setBackgroundTintList(getColorStateList(R.color.white));
                    btn_paper_casual.setBackgroundTintList(getColorStateList(R.color.orange));
                    tag = "休閒娛樂";
                }
            }
        });


        final Button btn_paper_check = findViewById(R.id.btn_paper_check);
        btn_paper_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count();
                countMood = 0;
                countTag = 0;
            }
        });

    }


    private void uploadImagesToServer() {
        if (InternetConnection.checkConnection(TakePhotoActivity.this)) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            showProgress();
            List<MultipartBody.Part> parts = new ArrayList<>();

            ApiService service = retrofit.create(ApiService.class);

            try {
                if (arrayList != null) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        parts.add(prepareFilePart("image" + i, arrayList.get(i)));
                    }
                }
            }catch (Exception e){

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
                        Toast.makeText(TakePhotoActivity.this,"Images successfully uploaded!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TakePhotoActivity.this,MainActivity.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(TakePhotoActivity.this);
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


    public void count(){

        if(mood.equals("") && tag.equals("")){
            new AlertDialog.Builder(TakePhotoActivity.this)
                    .setTitle("提醒您")
                    .setMessage("請點選心情和主題!!")
                    .setPositiveButton("好", null)
                    .show();
        }else if(mood.equals("")){
            new AlertDialog.Builder(TakePhotoActivity.this)
                    .setTitle("提醒您")
                    .setMessage("請點選心情!!")
                    .setPositiveButton("好", null)
                    .show();
        }else if(tag.equals("")){
            new AlertDialog.Builder(TakePhotoActivity.this)
                    .setTitle("提醒您")
                    .setMessage("請點選主題!!")
                    .setPositiveButton("好", null)
                    .show();
        }else{
            new AlertDialog.Builder(TakePhotoActivity.this)
                    .setCancelable(false)
                    .setTitle("提醒您")
                    .setMessage("確定完成?")
                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progressBar.setVisibility(View.VISIBLE);
                            uploadImagesToServer();
//                            DiaryInsert();
                        }
                    }).setNegativeButton("我想再改改",null).create()
                    .show();
        }

    }

    public void DiaryInsert(){

        String currentDate;
        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Map<String,String> map = new HashMap<>();
        map.put("command", "newDiary");
        map.put("uid", sqlReturn.GetUserID);
        map.put("diaryContent",(currentDate+"\n"+tag+"拍照日記"));
        map.put("diaryTag",tag);
        map.put("diaryDate",currentDate);
        map.put("diaryMood", mood);
        map.put("diaryOptionClass", "拍照無what");
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
                //Toast.makeText(activity, "日記新增成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TakePhotoActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(TakePhotoActivity.this);
                startActivity(intent,options.toBundle());
            }else {
                new AlertDialog.Builder(activity)
                        .setTitle("伺服器擁擠中")
                        .setMessage("請重複點選結束按鈕!!")
                        .setPositiveButton("OK", null)
                        .show();
            }
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


    private void doData(){
        data1 = new LinkedList<>();
        for(int i = 0; i < 1; i++){
            HashMap<String,String> row = new HashMap<>();
            data1.add(row);
        }
    }

    private class  MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

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
                    .inflate(R.layout.photo_item,parent,false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.imgPhoto.setImageBitmap(mbmp);
        }

        @Override
        public int getItemCount() {
            return data1.size();
        }
    }


    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent intent = new Intent(TakePhotoActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("id",1);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
