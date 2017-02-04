package com.example.admin.movieplaydome.widget.media;

/**
 * Created by admin on 17/2/3.
 */

public class EventConnectStateChanged {

    private boolean wifi_enable;//wifi 是否连上
    private boolean mobile_enable; //移动网络 是否连上

    public EventConnectStateChanged(boolean wifi_enable, boolean mobile_enable) {
        this.wifi_enable = wifi_enable;
        this.mobile_enable = mobile_enable;
    }

    public boolean isWifi_enable() {
        return wifi_enable;
    }

    public boolean isMobile_enable() {
        return mobile_enable;
    }
}
