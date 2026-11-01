package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

/* JADX INFO: compiled from: NetworkInfoDataProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public class bp extends bm {
    private boolean ia;

    @Nullable
    private String ib;

    bp() {
    }

    public boolean isConnected() {
        return this.ia;
    }

    @Nullable
    public String aT() {
        return this.ib;
    }

    @Override // com.my.target.bm
    @SuppressLint({"MissingPermission"})
    @WorkerThread
    public synchronized void collectData(@NonNull Context context) {
        removeAll();
        this.ia = true;
        this.ib = "";
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            if (activeNetworkInfo != null) {
                this.ia = activeNetworkInfo.isConnected();
                this.ib = activeNetworkInfo.getTypeName();
                addParam(i.z, this.ib);
                a(activeNetworkInfo);
            }
        } catch (SecurityException unused) {
            g.a("No permissions for access to network state");
        }
    }

    private void a(@NonNull NetworkInfo networkInfo) {
        if (networkInfo.getType() == 0) {
            addParam(i.Y, networkInfo.getSubtypeName() != null ? networkInfo.getSubtypeName() : "");
        } else {
            addParam(i.Y, networkInfo.getTypeName() != null ? networkInfo.getTypeName() : "");
        }
    }
}
