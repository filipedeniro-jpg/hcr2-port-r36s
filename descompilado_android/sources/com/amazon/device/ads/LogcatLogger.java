package com.amazon.device.ads;

import android.util.Log;

/* JADX INFO: compiled from: Logger.java */
/* JADX INFO: loaded from: classes.dex */
class LogcatLogger implements Logger {
    private String logTag;

    LogcatLogger() {
    }

    @Override // com.amazon.device.ads.Logger
    public LogcatLogger withLogTag(String str) {
        this.logTag = str;
        return this;
    }

    @Override // com.amazon.device.ads.Logger
    public void i(String str) {
        Log.i(this.logTag, str);
    }

    @Override // com.amazon.device.ads.Logger
    public void v(String str) {
        Log.v(this.logTag, str);
    }

    @Override // com.amazon.device.ads.Logger
    public void d(String str) {
        Log.d(this.logTag, str);
    }

    @Override // com.amazon.device.ads.Logger
    public void w(String str) {
        Log.w(this.logTag, str);
    }

    @Override // com.amazon.device.ads.Logger
    public void e(String str) {
        Log.e(this.logTag, str);
    }
}
