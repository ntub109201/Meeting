package com.example.myapplication2.ui.dashboard;

import android.util.Log;

import com.example.myapplication2.sqlReturn;
import com.example.myapplication2.ui.maybelike.GoogleAPIResponse;
import com.example.myapplication2.ui.maybelike.NotifyInterface;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Statistics implements NotifyInterface {
    private final String TAG = "NiCe";
    private final String apiURL = "https://guidary.000webhostapp.com/director.php";
    int finished_thread_count;

    @Override
    public void runEnd() {
        synchronized (this) {
            ++finished_thread_count;
            Log.d(TAG, "runEnd: "+ finished_thread_count);
            this.notify();
        }
    }
    public void getMoodAndTag(){
        finished_thread_count=0;
        Thread getMood = new Thread(new GetMoodStatisticsByWeek(this));
        Thread getTag = new Thread(new GetTagStatisticsByWeek(this));
        getMood.start();
        getTag.start();
        synchronized (this){
            while(true){
                if (finished_thread_count >= 2) break;
                try{
                    this.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public void getMood(){
        // may be useless
    }
    public void getTag(){
        finished_thread_count=0;
        Thread getTag = new Thread(new GetTagStatisticsByWeek(this));
        getTag.start();
        synchronized (this){
            while(true){
                if (finished_thread_count >= 1) break;
                try{
                    this.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    private String[] getIntervalDate(){
        String[] ret = new String[2];
        //取得這一個禮拜的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -7);
        ret[0]=sdf.format(c.getTime());
        c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 0);
        ret[1]= sdf.format(c.getTime());
        return ret;
    }
    private class GetMoodStatisticsByWeek implements Runnable{
        private NotifyInterface mInterface = null;
        private Map<String,String> map = new HashMap<>();
        GetMoodStatisticsByWeek(NotifyInterface iface){
            this.mInterface = iface;
            map.put("command", "moodCaculate");
            String[] date = getIntervalDate();
            map.put("startDate", date[0]);
            map.put("endDate", date[1]);
            map.put("uid", sqlReturn.GetUserID);
        }
        private void notifyEnd() {
            if (this.mInterface != null)
                this.mInterface.runEnd();
        }
        @Override
        public void run() {
            synchronized(Statistics.this) {
                HttpURLConnection connection;
                try{
                    URL url = new URL(apiURL);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setUseCaches(true);
                    connection.setAllowUserInteraction(false);
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.connect();
                    // output
                    Log.d(TAG, "run: mood output");
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    Iterator<String> iterator = map.keySet().iterator();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        stringBuilder.append(key).append("=").append(URLEncoder.encode(map.get(key), "UTF-8")).append("&");
                    }

                    stringBuilder.deleteCharAt(stringBuilder.length()-1);
                    outputStream.writeBytes((stringBuilder.toString()));
                    outputStream.flush();
                    outputStream.close();
                    Log.d(TAG, "run: mood input");
                    int status = connection.getResponseCode();
                    switch (status){
                        case 200:
                        case 201:
                            BufferedReader br =new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
                            StringBuilder sb = new StringBuilder();
                            String line;
                            while ((line = br.readLine()) != null) {
                                sb.append(line).append("\n");
                            }
                            br.close();
                            JSONObject jObject = new JSONObject(sb.toString());
                            sqlReturn.moodResult01 = jObject.getInt("心情1");
                            sqlReturn.moodResult02 = jObject.getInt("心情2");
                            sqlReturn.moodResult03 = jObject.getInt("心情3");
                            sqlReturn.moodResult04 = jObject.getInt("心情4");
                            sqlReturn.moodResult05 = jObject.getInt("心情5");
                            Log.d(TAG, sb.toString());
                            this.notifyEnd();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class GetTagStatisticsByWeek implements Runnable{
        private NotifyInterface mInterface = null;
        private Map<String,String> map = new HashMap<>();
        GetTagStatisticsByWeek(NotifyInterface iface){
            this.mInterface = iface;
            map.put("command", "tagCaculate");
            String[] date = getIntervalDate();
            map.put("startDate", date[0]);
            map.put("endDate", date[1]);
            map.put("uid", sqlReturn.GetUserID);
        }
        private void notifyEnd() {
            if (this.mInterface != null)
                this.mInterface.runEnd();
        }
        @Override
        public void run() {
            synchronized(Statistics.this) {
                HttpURLConnection connection;
                try{
                    URL url = new URL(apiURL);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setUseCaches(true);
                    connection.setAllowUserInteraction(false);
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.connect();
                    Log.d(TAG, "run: tag output");
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    Iterator<String> iterator = map.keySet().iterator();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        stringBuilder.append(key).append("=").append(URLEncoder.encode(map.get(key), "UTF-8")).append("&");
                    }

                    stringBuilder.deleteCharAt(stringBuilder.length()-1);
                    outputStream.writeBytes((stringBuilder.toString()));
                    outputStream.flush();
                    outputStream.close();

                    int status = connection.getResponseCode();
                    switch (status){
                        case 200:
                        case 201:
                            Log.d(TAG, "run: tag input");
                            BufferedReader br =new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
                            StringBuilder sb = new StringBuilder();
                            String line;
                            while ((line = br.readLine()) != null) {
                                sb.append(line).append("\n");
                            }
                            br.close();
                            JSONObject jObject = new JSONObject(sb.toString());
                            sqlReturn.tagResult01 = jObject.getInt("美食tag");
                            sqlReturn.tagResult02 = jObject.getInt("購物tag");
                            sqlReturn.tagResult03 = jObject.getInt("戀愛tag");
                            sqlReturn.tagResult04 = jObject.getInt("旅遊tag");
                            sqlReturn.tagResult05 = jObject.getInt("休閒娛樂tag");
                            Log.d(TAG, sb.toString());
                            this.notifyEnd();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
