package com.example.myapplication2.ui.maybelike;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.myapplication2.MainActivity;
import com.example.myapplication2.sqlReturn;

import androidx.annotation.Nullable;

public class ServiceGetLocation extends Service {
    public static final String TAG = ServiceGetLocation.class.getSimpleName();
    LocationManager mLocationManager;
    private ServiceBinder mBinder = new ServiceBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class ServiceBinder extends Binder {
        public void getLocation() {
            // 執行任務
            if(sqlReturn.locationPermissionGranted){
                try{
                    Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(location == null)
                    {
                        location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                    //緯度
                    //Double latitude = location.getLatitude();
                    sqlReturn.lat = location.getLatitude();
                    //經度
                    sqlReturn.lng = location.getLongitude();
                    Log.d(TAG,"Latitude:"+sqlReturn.lat+"  Longitude:"+sqlReturn.lng);
                }catch (SecurityException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
