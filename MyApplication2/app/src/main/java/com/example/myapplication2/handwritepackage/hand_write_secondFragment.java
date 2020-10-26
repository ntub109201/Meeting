package com.example.myapplication2.handwritepackage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication2.R;


public class hand_write_secondFragment extends Fragment {

    private boolean paper_sunnyClick = false, paper_mcClick = false, paper_cloudyClick = false, paper_thunderClick = false, paper_rainClick = false;
    private boolean paper_tripClick = false, paper_shoppingClick = false, paper_loveClick = false, paper_foodClick = false, paper_casualClick = false;
    private String mood;
    private String tag;
    private int countMood = 0, countTag = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_hand_write_second, container, false);

        final Button btn_paper_sunny = root.findViewById(R.id.btn_paper_sunny);
        final Button btn_paper_mc = root.findViewById(R.id.btn_paper_mc);
        final Button btn_paper_cloudy = root.findViewById(R.id.btn_paper_cloudy);
        final Button btn_paper_thunder = root.findViewById(R.id.btn_paper_thunder);
        final Button btn_paper_rain = root.findViewById(R.id.btn_paper_rain);
        final Button btn_paper_trip = root.findViewById(R.id.btn_paper_trip);
        final Button btn_paper_shopping = root.findViewById(R.id.btn_paper_shopping);
        final Button btn_paper_love = root.findViewById(R.id.btn_paper_love);
        final Button btn_paper_food = root.findViewById(R.id.btn_paper_food);
        final Button btn_paper_casual = root.findViewById(R.id.btn_paper_casual);

        btn_paper_sunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_sunnyClick){
                    paper_sunnyClick = false;
                    btn_paper_sunny.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }else if(!paper_sunnyClick){
                    paper_sunnyClick = true;
                    paper_mcClick = false;
                    paper_cloudyClick = false;
                    paper_thunderClick = false;
                    paper_rainClick = false;
                    btn_paper_sunny.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.orange));
                    btn_paper_mc.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_cloudy.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_thunder.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_rain.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }
            }
        });

        btn_paper_mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_mcClick){
                    paper_mcClick = false;
                    btn_paper_mc.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }else if(!paper_mcClick){
                    paper_sunnyClick = false;
                    paper_mcClick = true;
                    paper_cloudyClick = false;
                    paper_thunderClick = false;
                    paper_rainClick = false;
                    btn_paper_sunny.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_mc.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.orange));
                    btn_paper_cloudy.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_thunder.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_rain.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }
            }
        });

        btn_paper_cloudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_cloudyClick){
                    paper_cloudyClick = false;
                    btn_paper_cloudy.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }else if(!paper_cloudyClick){
                    paper_sunnyClick = false;
                    paper_mcClick = false;
                    paper_cloudyClick = true;
                    paper_thunderClick = false;
                    paper_rainClick = false;
                    btn_paper_sunny.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_mc.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_cloudy.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.orange));
                    btn_paper_thunder.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_rain.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }
            }
        });

        btn_paper_thunder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_thunderClick){
                    paper_thunderClick = false;
                    btn_paper_thunder.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }else if(!paper_thunderClick){
                    paper_sunnyClick = false;
                    paper_mcClick = false;
                    paper_cloudyClick = false;
                    paper_thunderClick = true;
                    paper_rainClick = false;
                    btn_paper_sunny.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_mc.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_cloudy.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_thunder.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.orange));
                    btn_paper_rain.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }
            }
        });

        btn_paper_rain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_rainClick){
                    paper_rainClick = false;
                    btn_paper_rain.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }else if(!paper_rainClick){
                    paper_sunnyClick = false;
                    paper_mcClick = false;
                    paper_cloudyClick = false;
                    paper_thunderClick = false;
                    paper_rainClick = true;
                    btn_paper_sunny.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_mc.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_cloudy.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_thunder.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_rain.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.orange));
                }
            }
        });

        btn_paper_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_tripClick){
                    paper_tripClick = false;
                    btn_paper_trip.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }else if(!paper_tripClick){
                    paper_tripClick = true;
                    paper_shoppingClick = false;
                    paper_loveClick = false;
                    paper_foodClick = false;
                    paper_casualClick = false;
                    btn_paper_trip.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.orange));
                    btn_paper_shopping.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_love.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_food.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_casual.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }
            }
        });

        btn_paper_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_shoppingClick){
                    paper_shoppingClick = false;
                    btn_paper_shopping.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }else if(!paper_shoppingClick){
                    paper_tripClick = false;
                    paper_shoppingClick = true;
                    paper_loveClick = false;
                    paper_foodClick = false;
                    paper_casualClick = false;
                    btn_paper_trip.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_shopping.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.orange));
                    btn_paper_love.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_food.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_casual.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }
            }
        });

        btn_paper_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_loveClick){
                    paper_loveClick = false;
                    btn_paper_love.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }else if(!paper_loveClick){
                    paper_tripClick = false;
                    paper_shoppingClick = false;
                    paper_loveClick = true;
                    paper_foodClick = false;
                    paper_casualClick = false;
                    btn_paper_trip.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_shopping.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_love.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.orange));
                    btn_paper_food.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_casual.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }
            }
        });

        btn_paper_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_foodClick){
                    paper_foodClick = false;
                    btn_paper_food.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }else if(!paper_foodClick){
                    paper_tripClick = false;
                    paper_shoppingClick = false;
                    paper_loveClick = false;
                    paper_foodClick = true;
                    paper_casualClick = false;
                    btn_paper_trip.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_shopping.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_love.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_food.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.orange));
                    btn_paper_casual.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }
            }
        });

        btn_paper_casual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paper_casualClick){
                    paper_casualClick = false;
                    btn_paper_casual.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                }else if(!paper_casualClick){
                    paper_tripClick = false;
                    paper_shoppingClick = false;
                    paper_loveClick = false;
                    paper_foodClick = false;
                    paper_casualClick = true;
                    btn_paper_trip.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_shopping.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_love.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_food.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.white));
                    btn_paper_casual.setBackgroundTintList(hand_write_secondFragment.super.getActivity().getColorStateList(R.color.orange));
                }
            }
        });

        return root;
    }

    //    public void DiaryInsert(){
//        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//        handWriteContext = txtHandWrite.getText().toString();
//        Map<String,String> map = new HashMap<>();
//        map.put("command", "newDiaryHandWrite");
//        map.put("uid", sqlReturn.GetUserID);
//        map.put("diaryContent",handWriteContext);
//        map.put("diaryTag","手寫日記");
//        map.put("diaryDate",currentDate);
//        map.put("diaryMood","手寫日記心情");
//        map.put("diaryOptionClass","手寫日記選項");
//        new DiaryInsert(this).execute((HashMap)map);
//    }
//
//    private class DiaryInsert extends HttpURLConnection_AsyncTask {
//
//        // 建立弱連結
//        WeakReference<Activity> activityReference;
//        DiaryInsert(Activity context){
//            activityReference = new WeakReference<>(context);
//        }
//        @Override
//        protected void onPostExecute(String result) {
//            JSONObject jsonObject = null;
//            boolean status = false;
//            // 取得弱連結的Context
//            Activity activity = activityReference.get();
//            if (activity == null || activity.isFinishing()) return;
//
//            try {
//                jsonObject = new JSONObject(result);
//                status = jsonObject.getBoolean("status");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            if (status){
//                Toast.makeText(activity, "日記新增成功", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(HandwriteActivity.this, MainActivity.class);
//                intent.putExtra("id",1);
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HandwriteActivity.this);
//                startActivity(intent,options.toBundle());
//                txtHandWrite.setText("");
//                progressBarHandWrite.setVisibility(View.INVISIBLE);
//            }else {
//                new AlertDialog.Builder(activity)
//                        .setTitle("伺服器擁擠中")
//                        .setMessage("請重複點選結束按鈕!!")
//                        .setPositiveButton("OK", null)
//                        .show();
//                progressBarHandWrite.setVisibility(View.INVISIBLE);
//            }
//
//        }
//    }

}
