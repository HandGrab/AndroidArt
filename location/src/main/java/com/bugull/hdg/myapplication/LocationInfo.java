package com.bugull.hdg.myapplication;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.Locale;

/**
 * Created by Sandy Luo on 2017/4/27.
 */

public class LocationInfo {

    private LocationManager myLocationManager;

    public static LocationInfo instance;

    public static LocationInfo getInstance(LocationManager locationManager) {
        if (instance == null) {
            instance = new LocationInfo(locationManager);
        }
        return instance;

    }

    public LocationInfo(LocationManager locationManager) {
        this.myLocationManager = locationManager;
    }


    public List<Address> getAddress(Context context, Location location) {
        List<Address> result = null;
        try {
            if (location != null) {
                Geocoder gc = new Geocoder(context, Locale.getDefault());
                result = gc.getFromLocation(location.getLatitude(),
                        location.getLongitude(), 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("location","exception:" + e);
        }
        return result;
    }

    public Location getLocation(Activity activity) {
        PermissionUitls.checkLocationPermission(activity);
        //获取位置管理服务

        //查找服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); //定位精度: 最高
        criteria.setAltitudeRequired(false); //海拔信息：不需要
        criteria.setBearingRequired(false); //方位信息: 不需要
        criteria.setCostAllowed(true);  //是否允许付费
        criteria.setPowerRequirement(Criteria.POWER_LOW); //耗电量: 低功耗
//        String provider = myLocationManager.getBestProvider(criteria, true); //获取GPS信息
//        myLocationManager.requestLocationUpdates(provider,2000,5,locationListener);
//        Log.e("provider", provider);
//        List<String> list = myLocationManager.getAllProviders();
//        Log.e("provider", list.toString());
//
        Location gpsLocation = null;
        Location netLocation = null;

        List<String> providers = myLocationManager.getProviders(true);


        if (netWorkIsOpen()) {
            //2000代表每2000毫秒更新一次，5代表每5秒更新一次
            myLocationManager.requestLocationUpdates("network", 2000, 5, locationListener);
            netLocation = myLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Log.d("location", "net:" + netLocation);
            if(netLocation!=null){
                if(onLocationChangeListener!=null){
                    onLocationChangeListener.onLocationChange(netLocation);
                }
            }
        }

        if (gpsIsOpen()) {
            myLocationManager.requestLocationUpdates("gps", 2000, 5, locationListener);
            gpsLocation = myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Log.d("location", "gps:" + gpsLocation);
            if(gpsLocation!=null){
                if(onLocationChangeListener!=null){
                    onLocationChangeListener.onLocationChange(gpsLocation);
                }
            }
        }

        if (gpsLocation == null && netLocation == null) {
            return null;
        }
        if (gpsLocation != null && netLocation != null) {
            if (gpsLocation.getTime() < netLocation.getTime()) {
                gpsLocation = null;
                return netLocation;
            } else {
                netLocation = null;
                return gpsLocation;
            }
        }
        if (gpsLocation == null) {
            return netLocation;
        } else {
            return gpsLocation;
        }
    }

    private boolean gpsIsOpen() {
        boolean isOpen = true;
        if (!myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {//没有开启GPS
            isOpen = false;
        }

        Log.d("location", "gps is open:" + isOpen);
        return isOpen;
    }

    private boolean netWorkIsOpen() {
        boolean netIsOpen = true;
        if (!myLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {//没有开启网络定位
            netIsOpen = false;
        }
        Log.d("location", "net is open:" + netIsOpen);
        return netIsOpen;
    }

    //监听GPS位置改变后得到新的经纬度
    private LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
//            Log.e("location", location.toString() + "....");
            // TODO Auto-generated method stub
            if (location != null) {
                //获取国家，省份，城市的名称
                Log.d("location", location.toString());
                if (onLocationChangeListener != null) {
                    onLocationChangeListener.onLocationChange(location);
                }

            } else {

            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("location", "location is enable:" + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("location", "location is disable:" + provider);
        }

    };


    private OnLocationChangeListener onLocationChangeListener;

    public void setOnLocationChangeListener(OnLocationChangeListener onLocationChangeListener) {
        this.onLocationChangeListener = onLocationChangeListener;
    }

    public interface OnLocationChangeListener {
        void onLocationChange(Location location);
    }


}
