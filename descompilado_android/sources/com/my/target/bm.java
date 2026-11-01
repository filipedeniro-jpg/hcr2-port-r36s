package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: FPDataProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class bm {

    @NonNull
    private final Map<String, String> map = new HashMap();

    @WorkerThread
    public abstract void collectData(@NonNull Context context);

    @NonNull
    protected synchronized Map<String, String> getMap() {
        return this.map;
    }

    protected synchronized boolean addParam(@NonNull String str, @Nullable String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str2 == null) {
            return removeParam(str);
        }
        this.map.put(str, str2);
        return true;
    }

    protected synchronized boolean removeParam(@NonNull String str) {
        if (!this.map.containsKey(str)) {
            return false;
        }
        this.map.remove(str);
        return true;
    }

    protected synchronized void removeAll() {
        this.map.clear();
    }

    @Nullable
    protected synchronized String getParam(@NonNull String str) {
        return this.map.get(str);
    }

    @NonNull
    public synchronized Map<String, String> getData() {
        HashMap map;
        map = new HashMap();
        map.putAll(this.map);
        return map;
    }

    public synchronized void putDataTo(@NonNull Map<String, String> map) {
        map.putAll(this.map);
    }
}
