package com.example.admin.movieplaydome.widget.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by admin on 17/2/3.
 */

public class NetworkStateReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (context == null) {
            return;
        }
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return;
        }
        NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean wifi = false;
        boolean mobile = false;
        if (null != wifiInfo && wifiInfo.isConnected()) {
            wifi = true;
        }
        if (null != mobileInfo && mobileInfo.isConnected()) {
            mobile = true;
        }
        EventBus.getDefault().post(new EventConnectStateChanged(wifi, mobile));
    }
}
