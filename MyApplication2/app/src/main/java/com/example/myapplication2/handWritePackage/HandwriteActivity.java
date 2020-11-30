package com.example.myapplication2.handWritePackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.net.Uri;
import android.os.Build;
import android.text.Layout;
import android.os.Bundle;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

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
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HandwriteActivity extends AppCompatActivity {

    private Button btn_handwritecheck,btn_sharehandwritediary;
    private ConstraintLayout textLayout;
    private RecyclerView recyclerview;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private HandwriteActivity.MyAdapter myAdapter;
    private LinkedList<HashMap<String,String>> data1;
    private int currentItem = 0;
    //多張圖片
    private static final String TAG = HandwriteActivity.class.getSimpleName();
    private Uri imageUri;
    private ArrayList<Uri> arrayList;
    private final int REQUEST_CODE_PERMISSIONS  = 1;
    private final int REQUEST_CODE_READ_STORAGE = 2;
    private Button btn_addphoto;

    private boolean paper_sunnyClick = false, paper_mcClick = false, paper_cloudyClick = false, paper_thunderClick = false, paper_rainClick = false;
    private boolean paper_tripClick = false, paper_shoppingClick = false, paper_loveClick = false, paper_foodClick = false, paper_casualClick = false;
    private String mood = "";
    private String tag = "";
    private Boolean btnChange;
    private Animation mOpen,mClose;
    private Button btn_sharefriend,btn_sharebestfriend;
    private boolean check_sharefriend = true,check_sharebestfriend = true;
    private String sharefriend = "n", sharebestfriend = "n";

    private ProgressBar progressBarHandWrite;
    private CardView noImageView;
    private EditText handWrite_context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handwrite);

        noImageView = findViewById(R.id.noImageView);

        btn_sharehandwritediary = findViewById(R.id.btn_sharehandwritediary);

        final ImageButton imbtnReturnMain = findViewById(R.id.imbtnReturnMain);
        imbtnReturnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HandwriteActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        handWrite_context = findViewById(R.id.handWrite_context);

        btn_handwritecheck = findViewById(R.id.btn_handwritecheck);

        textLayout = findViewById(R.id.textLayout);
        textLayout.setZ(100);
        btn_addphoto = findViewById(R.id.btn_addphoto);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(mLayoutManager);
        myAdapter = new MyAdapter();
        recyclerview.setAdapter(myAdapter);
        doData();

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
        progressBarHandWrite = findViewById(R.id.progressBarHandWrite);

        btn_paper_sunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_sunnyClick){
                    paper_sunnyClick = false;
                    btn_paper_sunny.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    mood = "";
                }else if(!paper_sunnyClick){
                    paper_sunnyClick = true;
                    paper_mcClick = false;
                    paper_cloudyClick = false;
                    paper_thunderClick = false;
                    paper_rainClick = false;
                    btn_paper_sunny.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.orange));
                    btn_paper_mc.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_cloudy.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_thunder.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_rain.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    mood = "心情1";
                }
            }
        });

        btn_paper_mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_mcClick){
                    paper_mcClick = false;
                    btn_paper_mc.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    mood = "";
                }else if(!paper_mcClick){
                    paper_sunnyClick = false;
                    paper_mcClick = true;
                    paper_cloudyClick = false;
                    paper_thunderClick = false;
                    paper_rainClick = false;
                    btn_paper_sunny.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_mc.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.orange));
                    btn_paper_cloudy.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_thunder.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_rain.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    mood = "心情2";
                }
            }
        });

        btn_paper_cloudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_cloudyClick){
                    paper_cloudyClick = false;
                    btn_paper_cloudy.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    mood = "";
                }else if(!paper_cloudyClick){
                    paper_sunnyClick = false;
                    paper_mcClick = false;
                    paper_cloudyClick = true;
                    paper_thunderClick = false;
                    paper_rainClick = false;
                    btn_paper_sunny.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_mc.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_cloudy.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.orange));
                    btn_paper_thunder.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_rain.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    mood = "心情3";
                }
            }
        });

        btn_paper_rain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_rainClick){
                    paper_rainClick = false;
                    btn_paper_rain.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    mood = "";
                }else if(!paper_rainClick){
                    paper_sunnyClick = false;
                    paper_mcClick = false;
                    paper_cloudyClick = false;
                    paper_thunderClick = false;
                    paper_rainClick = true;
                    btn_paper_sunny.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_mc.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_cloudy.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_thunder.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_rain.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.orange));
                    mood = "心情4";
                }
            }
        });

        btn_paper_thunder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_thunderClick){
                    paper_thunderClick = false;
                    btn_paper_thunder.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    mood = "";
                }else if(!paper_thunderClick){
                    paper_sunnyClick = false;
                    paper_mcClick = false;
                    paper_cloudyClick = false;
                    paper_thunderClick = true;
                    paper_rainClick = false;
                    btn_paper_sunny.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_mc.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_cloudy.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_thunder.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.orange));
                    btn_paper_rain.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    mood = "心情5";
                }
            }
        });


        btn_paper_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_tripClick){
                    paper_tripClick = false;
                    btn_paper_trip.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    tag = "";
                }else if(!paper_tripClick){
                    paper_tripClick = true;
                    paper_shoppingClick = false;
                    paper_loveClick = false;
                    paper_foodClick = false;
                    paper_casualClick = false;
                    btn_paper_trip.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.orange));
                    btn_paper_shopping.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_love.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_food.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_casual.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    tag = "旅遊";
                }
            }
        });

        btn_paper_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_shoppingClick){
                    paper_shoppingClick = false;
                    btn_paper_shopping.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    tag = "";
                }else if(!paper_shoppingClick){
                    paper_tripClick = false;
                    paper_shoppingClick = true;
                    paper_loveClick = false;
                    paper_foodClick = false;
                    paper_casualClick = false;
                    btn_paper_trip.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_shopping.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.orange));
                    btn_paper_love.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_food.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_casual.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    tag = "購物";
                }
            }
        });

        btn_paper_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_loveClick){
                    paper_loveClick = false;
                    btn_paper_love.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    tag = "";
                }else if(!paper_loveClick){
                    paper_tripClick = false;
                    paper_shoppingClick = false;
                    paper_loveClick = true;
                    paper_foodClick = false;
                    paper_casualClick = false;
                    btn_paper_trip.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_shopping.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_love.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.orange));
                    btn_paper_food.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_casual.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    tag = "戀愛";
                }
            }
        });

        btn_paper_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_foodClick){
                    paper_foodClick = false;
                    btn_paper_food.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    tag = "";
                }else if(!paper_foodClick){
                    paper_tripClick = false;
                    paper_shoppingClick = false;
                    paper_loveClick = false;
                    paper_foodClick = true;
                    paper_casualClick = false;
                    btn_paper_trip.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_shopping.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_love.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_food.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.orange));
                    btn_paper_casual.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    tag = "美食";
                }
            }
        });

        btn_paper_casual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_casualClick){
                    paper_casualClick = false;
                    btn_paper_casual.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    tag = "";
                }else if(!paper_casualClick){
                    paper_tripClick = false;
                    paper_shoppingClick = false;
                    paper_loveClick = false;
                    paper_foodClick = false;
                    paper_casualClick = true;
                    btn_paper_trip.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_shopping.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_love.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_food.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.white));
                    btn_paper_casual.setBackgroundTintList(HandwriteActivity.this.getColorStateList(R.color.orange));
                    tag = "休閒娛樂";
                }
            }
        });

        mOpen = AnimationUtils.loadAnimation(HandwriteActivity.this,R.anim.button_open);
        mClose = AnimationUtils.loadAnimation(HandwriteActivity.this,R.anim.button_close);
        btnChange = false;
        btn_sharefriend = findViewById(R.id.btn_sharefriend);
        btn_sharebestfriend = findViewById(R.id.btn_sharebestfriend);
        btn_sharehandwritediary.setOnClickListener(new View.OnClickListener() {
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
                if(check_sharefriend == true){
                    sharefriend = "y";
                    sharebestfriend = "y";
                    check_sharefriend = false;
                    check_sharebestfriend = true;
                    btn_sharefriend.setBackgroundResource(R.drawable.btn_sharediaryend2);
                    btn_sharebestfriend.setBackgroundResource(R.drawable.btn_sharediaryend);

                }else{
                    sharefriend = "n";
                    sharebestfriend = "n";
                    check_sharefriend = true;
                    check_sharebestfriend = true;
                    btn_sharefriend.setBackgroundResource(R.drawable.btn_sharediaryend);
                    btn_sharebestfriend.setBackgroundResource(R.drawable.btn_sharediaryend);
                }
                Log.d(TAG, " share "+sharefriend +" bff "+sharebestfriend);
            }
        });

        btn_sharebestfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_sharebestfriend == true){
                    sharebestfriend = "y";
                    sharefriend = "n";
                    check_sharefriend = true;
                    check_sharebestfriend = false;
                    btn_sharefriend.setBackgroundResource(R.drawable.btn_sharediaryend);
                    btn_sharebestfriend.setBackgroundResource(R.drawable.btn_sharediaryend2);
                }else{
                    sharebestfriend = "n";
                    sharefriend = "n";
                    check_sharefriend = true;
                    check_sharebestfriend = true;
                    btn_sharefriend.setBackgroundResource(R.drawable.btn_sharediaryend);
                    btn_sharebestfriend.setBackgroundResource(R.drawable.btn_sharediaryend);
                }
                Log.d(TAG, " share "+sharefriend +" bff "+sharebestfriend);
            }
        });

        btn_addphoto.setOnClickListener(new View.OnClickListener() {
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

        btn_handwritecheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count();
            }
        });

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
        if (InternetConnection.checkConnection(HandwriteActivity.this)) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
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
                Log.e(TAG, "File select error", e);
            }

            RequestBody description = createPartFromString("https://10836008.000webhostapp.com");
            RequestBody size = createPartFromString(""+parts.size());

            // finally, execute the request
            Call<ResponseBody> call = service.uploadMultiple(description, size, parts);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    hideProgress();
                    if(response.isSuccessful()) {
                        Intent intent = new Intent(HandwriteActivity.this,MainActivity.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HandwriteActivity.this);
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
        }
    }
    private void showProgress() {
        progressBarHandWrite.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBarHandWrite.setVisibility(View.INVISIBLE);

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
                //Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
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


    public void count(){

        if(mood.equals("") && tag.equals("")){
            new AlertDialog.Builder(HandwriteActivity.this)
                    .setTitle("提醒您")
                    .setMessage("請點選心情和主題!!")
                    .setPositiveButton("好", null)
                    .show();
        }else if(mood.equals("")){
            new AlertDialog.Builder(HandwriteActivity.this)
                    .setTitle("提醒您")
                    .setMessage("請點選心情!!")
                    .setPositiveButton("好", null)
                    .show();
        }else if(tag.equals("")){
            new AlertDialog.Builder(HandwriteActivity.this)
                    .setTitle("提醒您")
                    .setMessage("請點選主題!!")
                    .setPositiveButton("好", null)
                    .show();
        }else{
            new AlertDialog.Builder(HandwriteActivity.this)
                    .setCancelable(false)
                    .setTitle("提醒您")
                    .setMessage("確定完成日記?")
                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DiaryInsert();
                            //uploadImagesToServer();
                        }
                    }).setNegativeButton("取消",null).create()
                    .show();
        }

    }

    public void DiaryInsert(){

        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Map<String,String> map = new HashMap<>();
        map.put("command", "newDiaryHandWrite");
        map.put("uid", sqlReturn.GetUserID);
        map.put("diaryContent",handWrite_context.getText().toString());
        map.put("diaryTag",tag);
        map.put("diaryDate",currentDate);
        map.put("diaryMood",mood);
        map.put("diaryOptionClass","手寫無what");
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
                //Toast.makeText(activity, "日記新增成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HandwriteActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HandwriteActivity.this);
                startActivity(intent,options.toBundle());
                progressBarHandWrite.setVisibility(View.INVISIBLE);
            }else {
                new AlertDialog.Builder(activity)
                        .setTitle("伺服器擁擠中")
                        .setMessage("請重複點選結束按鈕!!")
                        .setPositiveButton("OK", null)
                        .show();
                progressBarHandWrite.setVisibility(View.INVISIBLE);
            }

        }
    }



    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent intent = new Intent(HandwriteActivity.this, MainActivity.class);
            intent.putExtra("id",1);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

}
