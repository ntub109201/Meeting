package com.example.myapplication2.ui.dashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.myapplication2.HttpURLConnection_AsyncTask;
import com.example.myapplication2.User.PersonalActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.sqlReturn;
import com.example.myapplication2.ui.home.HomeFragment;

import static com.example.myapplication2.R.layout.fragment_dashboard;

import androidx.annotation.AttrRes;
import androidx.core.util.Pair;
import androidx.navigation.Navigation;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


public class DashboardFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    private ImageButton imBtnPersonal;
    private MaterialDatePicker.Builder<Pair<Long,Long>> builder;
    private CalendarConstraints.Builder constraintsBuilder;
    private MaterialDatePicker<Pair<Long,Long>> materialDatePicker;
    private MaterialDatePicker<?> picker;
    private static ProgressBar progressbar;
    private long today;
    //心情
    private int moodResult01;
    private int moodResult02;
    private int moodResult03;
    private int moodResult04;
    private int moodResult05;
    //主題
    private int tagResult01;
    private int tagResult02;
    private int tagResult03;
    private int tagResult04;
    private int tagResult05;
    String d1;
    String d2;
    private PieChart pie_Chart;
    View root;
    final String TAG = "nice";
    private Button btnRecommend;
    private TextView suggestion,statistics__no_text_1,statistics__no_text_2;

    private Button recommend;
    private ImageView statistics_no;

    private TextView selectedTextDate_start,selectedTextDate_end,selectedTextSelected_date;
    private TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(fragment_dashboard, container, false);

        if(HomeFragment.changeBtn == true){
            HomeFragment.changeBtn = false;
        }

        btnRecommend = root.findViewById(R.id.recommend);
        btnRecommend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigation_maybelike);
            }
        });
        imBtnPersonal = root.findViewById(R.id.imBtnPersonal);
        imBtnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardFragment.super.getActivity(), PersonalActivity.class);
                intent.putExtra("pageId",3);
                startActivity(intent);
            }
        });
        tabLayout = root.findViewById(R.id.tabLayout2);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "pieChart: "+tabLayout.getSelectedTabPosition());
                if(tabLayout.getSelectedTabPosition()==0){
                    mood_statistics(d1,d2);
                }else{
                    tag_statistics(d1,d2);
                }
//                pieChart();
//                suggest();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // 隱藏所有物件
        suggestion = root.findViewById(R.id.suggestion);
        //recommend = root.findViewById(R.id.recommend);
        pie_Chart = root.findViewById(R.id.pie_chart);
        statistics__no_text_1 = root.findViewById(R.id.statistics__no_text_1);
        statistics__no_text_2 = root.findViewById(R.id.statistics__no_text_2);
        statistics_no = root.findViewById(R.id.statistics_no);

        suggestion.setVisibility( View.INVISIBLE );
        btnRecommend.setVisibility( View.INVISIBLE );
        pie_Chart.setVisibility( View.INVISIBLE );
        statistics__no_text_1.setVisibility( View.INVISIBLE );
        statistics__no_text_2.setVisibility( View.INVISIBLE );
        statistics_no.setVisibility( View.INVISIBLE );

        progressbar = root.findViewById(R.id.progressBar);
        progressbar.setZ(10);

        //取得這一個禮拜的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -7);
        String startDate=sdf.format(c.getTime());
        d1=startDate;
        c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 0);
        String endDate = sdf.format(c.getTime());
        d2=endDate;

        selectedTextDate_start = root.findViewById(R.id.date_start);
        selectedTextDate_start.setText(startDate);
        selectedTextDate_end = root.findViewById(R.id.date_end);
        selectedTextDate_end.setText(endDate);
        mood_statistics(startDate,endDate);

        initialize();
        //date
        selectedTextSelected_date = root.findViewById(R.id.selected_date);
        root.findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picker.show(getParentFragmentManager(), picker.toString());
            }
        });
        root.findViewById(R.id.show_dialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.show(getParentFragmentManager(), picker.toString());

            }
        });


        return root;
    }

    private void showDatePickerDialog(){
        materialDatePicker.show(getParentFragmentManager(), "NiCe");
    }


    private  void suggest() {
        // 建議
        int[] suggestResult;
        ArrayList<String[]> data = new ArrayList<>();
        if(tabLayout.getSelectedTabPosition()==0){
            suggestResult = new int[]{sqlReturn.moodResult01, sqlReturn.moodResult02, sqlReturn.moodResult03, sqlReturn.moodResult04, sqlReturn.moodResult05};
            // 資料 - 心情
            String[] mood1 =
                    {"每天做一件令別人愉快的事，自己也會特別快樂。",
                            "好心情像冬天裡難得的好天氣，一瞬間照亮瞭心間；好心情像夏天的冰棍，一下子涼爽瞭心田。",
                            "有了積極的心態，便有了戰勝一切困難取得成功的信心。繼續保持！"};
            String[] mood2 =
                    {"心情普遍不錯唷！要記得，只要心情是晴朗的，人生就沒有雨天，繼續保持：）",
                            "微笑，是最美的陽光"};
            String[] mood3 =
                    {"健康良好的心理是取得成功的開端",
                            "保持一顆年輕的心，做個簡單的人，享受陽光和溫暖。",
                            "只要你還願意，世界一定會給你驚喜。"};
            String[] mood4 =
                    {"活在當下，別在懷念過去或者憧憬未來中浪費掉你現在的生活。",
                            "結局很美妙的事，但開頭並非如此，不必太灰心。",
                            "任何事情，總有答案。與其煩惱，不如順其自然",
                            "一切都會好起來的，即使不會是在今天，但總有一天會的",
                            "日出东海落西山，愁也一天，喜也一天；遇事不钻牛角尖，人也舒坦，心也舒坦。"};
            String[] mood5 =
                    {"如果我不堅強、誰能替我勇敢，如果我不獨立誰又能給與支持！找到問題點，一起面對它、解決他！",
                            "當你不能夠再擁有的時候，你唯一可以做的就是令自己不要忘記。",
                            "不要小看自己，因為人有無限的可能。",
                            "所有看似美好的，都經歷過或者正在經歷著不美好。"};
            data.add(mood1);data.add(mood2);data.add(mood3);data.add(mood4);data.add(mood5);
        }else{
            suggestResult = new int[]{sqlReturn.tagResult01, sqlReturn.tagResult02, sqlReturn.tagResult03, sqlReturn.tagResult04, sqlReturn.tagResult05};
            // 資料 - 主題
            String[] tag1 =
                    {"這段期間吃很多美食唷～別忘了要多運動，維持健康生活，繼續吃遍全世界。",
                            "最近常吃美食耶～享受美食的懷抱～"};
            // 購物
            String[] tag2 =
                    {"這段期間購物的頻率偏高耶！要謹慎理財，掌握金錢的支出～",
                            "購物率有點高呦！下手前可能需要再三思～",
                            "不要衝動消費啊，衝動是魔鬼"};
            // 戀愛
            String[] tag3 =
                    {"生活，是用來經營的，而不是用來計較的；感情，是用來維繫的，而不是用來考驗的",
                            "愛情，從來就沒有固定形態，拿別人的樣板是臨摹不出自己的幸福的",
                            "最好的愛情，會讓你不斷完善自我，而非失去自我"};
            // 旅遊
            String[] tag4 =
                    {"休息是為了走更長遠的路，在這段期間規劃的旅程，將帶給你無限的回憶。",
                            "旅行可以放鬆自己的心情，寬闊自己的心境，忘掉不順心，迎接新的開心"};
            // 休閒娛樂
            String[] tag5 =
                    {"適度的娱樂能放鬆人的情緒，陶冶人的情操",
                            "放鬆與娛樂，被認為是生活中不可缺少的要素"};
            data.add(tag1);data.add(tag2);data.add(tag3);data.add(tag4);data.add(tag5);
        }

        suggestion.setText("");
        if(suggestResult[0] >= suggestResult[1] && suggestResult[0] >= suggestResult[2] && suggestResult[0] >= suggestResult[3] && suggestResult[0] >= suggestResult[4]) {
            int num=data.get(0).length;
            int number_random = (int)(Math.random()*num);
            suggestion.append(data.get(0)[number_random]);
        }else if(suggestResult[1] >= suggestResult[0] && suggestResult[1] >= suggestResult[2] && suggestResult[1] >= suggestResult[3] && suggestResult[1] >= suggestResult[4]) {
            int num=data.get(1).length;
            int number_random = (int)(Math.random()*num);
            suggestion.append(data.get(1)[number_random]);
        }else if(suggestResult[2] >= suggestResult[0] && suggestResult[2] >= suggestResult[1] && suggestResult[2] >= suggestResult[3] && suggestResult[1] >= suggestResult[4]) {
            int num=data.get(2).length;
            int number_random = (int)(Math.random()*num);
            suggestion.append(data.get(2)[number_random]);
        }else if(suggestResult[3] >= suggestResult[0] && suggestResult[3] >= suggestResult[2] && suggestResult[3] >= suggestResult[1] && suggestResult[1] >= suggestResult[4]) {
            int num=data.get(3).length;
            int number_random = (int)(Math.random()*num);
            suggestion.append(data.get(3)[number_random]);
        }else if(suggestResult[4] >= suggestResult[0] && suggestResult[4] >= suggestResult[2] && suggestResult[4] >= suggestResult[3] && suggestResult[4] >= suggestResult[1]) {
            int num=data.get(4).length;
            int number_random = (int)(Math.random()*num);
            suggestion.append(data.get(4)[number_random]);
        }else{
            suggestion.append("資料不足");
        }
    }


    private  void pieChart() {

        suggestion.append(Integer.toString(moodResult01));

        pie_Chart.setEntryLabelTextSize(17f); //圖表裡文字大小
        pie_Chart.setEntryLabelColor(Color.parseColor("#ffffff")); //圖表裡文字顏色
        ArrayList<PieEntry> visitors = new ArrayList<>();
        if(tabLayout.getSelectedTabPosition()==0){
            //心情
            if(moodResult01 != 0){
                visitors.add(new PieEntry(moodResult01,"晴天"));
            }
            if(moodResult02 != 0){
                visitors.add(new PieEntry(moodResult02,"時晴"));
            }
            if(moodResult03 != 0){
                visitors.add(new PieEntry(moodResult03,"多雲"));
            }
            if(moodResult04 != 0){
                visitors.add(new PieEntry(moodResult04,"陣雨"));
            }
            if(moodResult05 != 0){
                visitors.add(new PieEntry(moodResult05,"雷雨"));
            }
        }else{
            //主題
            if(tagResult01 != 0){
                visitors.add(new PieEntry(tagResult01,"美食"));
            }
            if(tagResult02 != 0){
                visitors.add(new PieEntry(tagResult02,"購物"));
            }
            if(tagResult03 != 0){
                visitors.add(new PieEntry(tagResult03,"感情"));
            }
            if(tagResult04 != 0){
                visitors.add(new PieEntry(tagResult04,"旅遊"));
            }
            if(tagResult05 != 0){
                visitors.add(new PieEntry(tagResult05,"休閒娛樂"));
            }
        }
        PieDataSet pieDateSet = new PieDataSet(visitors,"");
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.rgb(245, 187, 207));
        colors.add(Color.rgb(248, 210, 189));
        colors.add(Color.rgb(236, 228, 76));
        colors.add(Color.rgb(119, 183, 246));
        colors.add(Color.rgb(142, 225, 149));
        pieDateSet.setColors(colors);
        pieDateSet.setValueTextColor(Color.DKGRAY);
        pieDateSet.setValueTextSize(16f);


        PieData pieData = new PieData(pieDateSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new DefaultValueFormatter(0)); //設定小數點
        pieData.setValueFormatter(new PercentFormatter(pie_Chart));  // ％ 顯示
        pie_Chart.setUsePercentValues(true);  // 轉換為百分比

        pie_Chart.setData(pieData);
        pie_Chart.getDescription().setEnabled(false);
        pie_Chart.animate();
        pie_Chart.setDrawHoleEnabled(false); //true = 空心圓



        pie_Chart.getLegend().setTextSize(14f); //圖例文字大小
        pie_Chart.getLegend().setFormSize(10);  //圖例大小
        pie_Chart.getLegend().setTextColor(Color.parseColor("#87C3C0"));//圖例顏色
        pie_Chart.getLegend().setFormToTextSpace(10f); //圖例與文字的間鉅
        pie_Chart.getLegend().setXEntrySpace(20);
//        pieChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//圖例水平居中

        pie_Chart.invalidate();
    }

    private void initialize(){
        today = MaterialDatePicker.todayInUtcMilliseconds();
        // CalenderConstraintBuilder
        constraintsBuilder = new CalendarConstraints.Builder();

        builder = MaterialDatePicker.Builder.dateRangePicker();

        Calendar upperBoundCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        upperBoundCalendar.add(Calendar.DAY_OF_YEAR, 0);
        long upperBound = upperBoundCalendar.getTimeInMillis();
        List<CalendarConstraints.DateValidator> validators = new ArrayList<>();
        validators.add(DateValidatorPointBackward.before(upperBound));
        //validators.add(new DateValidatorWeekdays());
        constraintsBuilder.setValidator(CompositeDateValidator.allOf(validators));
        // set opening month
        constraintsBuilder.setOpenAt(today);
        // set default selection
        builder.setSelection(new Pair<>(today, today));
        // set input mode
        builder.setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR);
        try {
            builder.setCalendarConstraints(constraintsBuilder.build());
            picker = builder.build();
            addSnackBarListeners(picker);
//            picker.show(getSupportFragmentManager(), picker.toString());
        } catch (IllegalArgumentException e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "日期："+ year + "/" + month + "/" + dayOfMonth;
//        selectedText.setText(date);
        Log.d("NiCe", "onDateSet: ");
    }
    private static int resolveOrThrow(Context context, @AttrRes int attributeResId) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(attributeResId, typedValue, true)) {
            return typedValue.data;
        }
        throw new IllegalArgumentException(context.getResources().getResourceName(attributeResId));
    }
    private void addSnackBarListeners(MaterialDatePicker<?> materialCalendarPicker) {
        materialCalendarPicker.addOnPositiveButtonClickListener(
                selection -> {
                    progressbar.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "positive", Toast.LENGTH_SHORT).show();
                    if (selection instanceof Pair){
                        long startDate=0, endDate=0;
                        if (Long.class.equals(((Pair) selection).first.getClass()))
                            startDate = (long) ((Pair) selection).first;
                        if (Long.class.equals(((Pair) selection).second.getClass()))
                            endDate = (long) ((Pair) selection).second;
                        if (startDate!=0 && endDate!=0){
                            try {
                                d1 = DateConvertTool.longToString(startDate, "yyyy/MM/dd");
                                d2 = DateConvertTool.longToString(endDate, "yyyy/MM/dd");
                                String s = "Start: "+d1+", \nEnd: "+d2;
                                selectedTextDate_start.setText(d1);
                                selectedTextDate_end.setText(d2);
                                mood_statistics(d1,d2);
                                tag_statistics(d1,d2);
                                pieChart();
                                suggest();

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        materialCalendarPicker.addOnNegativeButtonClickListener(
                dialog -> {
                    Toast.makeText(getActivity(), "negative", Toast.LENGTH_SHORT).show();
                });
        materialCalendarPicker.addOnCancelListener(
                dialog -> {
                    Toast.makeText(getActivity(), "cancel", Toast.LENGTH_SHORT).show();
                });
    }



    /* statistics */
    public void mood_statistics(String startDate,String endDate){
        Map<String,String> map = new HashMap<>();
        map.put("command", "moodCaculate");
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("uid", sqlReturn.GetUserID);
        new mood_statistics(this).execute((HashMap)map);
    }

    private class mood_statistics extends HttpURLConnection_AsyncTask {
        // 建立弱連結
        WeakReference<DashboardFragment> activityReference;
        mood_statistics(DashboardFragment context){
            activityReference = new WeakReference<>(context);
        }
        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonObject = null;
            JSONArray jsonArray = null;
            boolean status = false;
            // 取得弱連結的Context
            //PieChartActivity activity = (DashboardFragment) activityReference.get();
            DashboardFragment fragment = activityReference.get();
            if (fragment == null) return;

            try {
                Log.d("error", result);
                jsonObject = new JSONObject(result);

                status = jsonObject.getBoolean("status");
                if(status){
                    //心情
                    sqlReturn.moodResult01 = jsonObject.getInt("心情1");
                    sqlReturn.moodResult02 = jsonObject.getInt("心情2");
                    sqlReturn.moodResult03 = jsonObject.getInt("心情3");
                    sqlReturn.moodResult04 = jsonObject.getInt("心情4");
                    sqlReturn.moodResult05 = jsonObject.getInt("心情5");

                    fragment.moodResult01 = sqlReturn.moodResult01;
                    fragment.moodResult02 = sqlReturn.moodResult02;
                    fragment.moodResult03 = sqlReturn.moodResult03;
                    fragment.moodResult04 = sqlReturn.moodResult04;
                    fragment.moodResult05 = sqlReturn.moodResult05;
                    Log.d("mood1", String.valueOf(fragment.moodResult01));

                    if(fragment.moodResult01 == 0 && fragment.moodResult02 == 0 && fragment.moodResult03 == 0 && fragment.moodResult04 ==0 && fragment.moodResult05 == 0){
                        // 資料不足
                        suggestion.setVisibility( View.INVISIBLE );
                        btnRecommend.setVisibility( View.INVISIBLE );
                        pie_Chart.setVisibility( View.INVISIBLE );
                        statistics__no_text_1.setVisibility( View.VISIBLE );
                        statistics__no_text_2.setVisibility( View.VISIBLE );
                        statistics_no.setVisibility( View.VISIBLE );
                    }else {

                        suggestion.setVisibility( View.VISIBLE );
                        btnRecommend.setVisibility( View.VISIBLE );
                        pie_Chart.setVisibility( View.VISIBLE );
                        statistics__no_text_1.setVisibility( View.INVISIBLE );
                        statistics__no_text_2.setVisibility( View.INVISIBLE );
                        statistics_no.setVisibility( View.INVISIBLE );
                        fragment.pieChart();
                        fragment.suggest();
                    }
                    progressbar.setVisibility(View.INVISIBLE);
                }else {
                    Toast.makeText(fragment.getActivity(), "失敗", Toast.LENGTH_LONG).show();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
    public void tag_statistics(String startDate, String endDate){
        Map<String,String> map = new HashMap<>();
        map.put("command", "tagCaculate");
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("uid", sqlReturn.GetUserID);

        new tag_statistics(this).execute((HashMap)map);
    }
    private class tag_statistics extends HttpURLConnection_AsyncTask {
        // 建立弱連結
        WeakReference<Fragment> activityReference;
        tag_statistics(Fragment context){
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonObject = null;
            JSONArray jsonArray = null;
            boolean status = false;
            // 取得弱連結的Context
            DashboardFragment fragment = (DashboardFragment) activityReference.get();
            if (fragment == null) return;

            try {
                Log.d("error", result);
                jsonObject = new JSONObject(result);

                status = jsonObject.getBoolean("status");
                if(status){
                    //心情
                    sqlReturn.tagResult01 = jsonObject.getInt("美食tag");
                    sqlReturn.tagResult02 = jsonObject.getInt("購物tag");
                    sqlReturn.tagResult03 = jsonObject.getInt("戀愛tag");
                    sqlReturn.tagResult04 = jsonObject.getInt("旅遊tag");
                    sqlReturn.tagResult05 = jsonObject.getInt("休閒娛樂tag");

                    tagResult01 = sqlReturn.moodResult01;
                    tagResult02 = sqlReturn.moodResult02;
                    tagResult03 = sqlReturn.moodResult03;
                    tagResult04 = sqlReturn.moodResult04;
                    tagResult05 = sqlReturn.moodResult05;

                    //主題
                    if(tagResult01 == 0 && tagResult02 == 0 && tagResult03 == 0 && tagResult04 ==0 && tagResult05 == 0){
                        // 資料不足
                        suggestion.setVisibility( View.INVISIBLE );
                        btnRecommend.setVisibility( View.INVISIBLE );
                        pie_Chart.setVisibility( View.INVISIBLE );
                        statistics__no_text_1.setVisibility( View.VISIBLE );
                        statistics__no_text_2.setVisibility( View.VISIBLE );
                        statistics_no.setVisibility( View.VISIBLE );
                    }else {

                        suggestion.setVisibility( View.VISIBLE );
                        btnRecommend.setVisibility( View.VISIBLE );
                        pie_Chart.setVisibility( View.VISIBLE );
                        statistics__no_text_1.setVisibility( View.INVISIBLE );
                        statistics__no_text_2.setVisibility( View.INVISIBLE );
                        statistics_no.setVisibility( View.INVISIBLE );
                        fragment.pieChart();
                        fragment.suggest();
                    }
                    progressbar.setVisibility(View.INVISIBLE);
                }else {
                    Toast.makeText(fragment.getActivity(), "失敗", Toast.LENGTH_LONG).show();
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}
