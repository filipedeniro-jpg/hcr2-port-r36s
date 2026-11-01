package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class s {
    private static s b = new s(new Handler(Looper.getMainLooper()));
    public final Handler a;

    public s(Handler handler) {
        this.a = handler;
    }

    public static s a() {
        return b;
    }

    public File b() {
        return Environment.getExternalStorageDirectory();
    }

    public String c() {
        return Environment.getExternalStorageState();
    }

    public boolean a(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    public String d() {
        return Build.VERSION.RELEASE;
    }

    public AdvertisingIdClient.Info a(Context context) throws GooglePlayServicesRepairableException, IllegalStateException, GooglePlayServicesNotAvailableException, IOException {
        return AdvertisingIdClient.getAdvertisingIdInfo(context);
    }

    public boolean e() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public boolean a(CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence);
    }

    public Bitmap a(byte[] bArr) {
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, null);
    }
}
