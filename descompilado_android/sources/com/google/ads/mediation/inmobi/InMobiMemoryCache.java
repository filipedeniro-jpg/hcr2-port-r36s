package com.google.ads.mediation.inmobi;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class InMobiMemoryCache {
    private static final String TAG = "MemoryCache";
    private final Map<String, Drawable> mCache = Collections.synchronizedMap(new LinkedHashMap(10, 1.5f, true));
    private long mSize = 0;
    private long mLimit = 1000000;

    InMobiMemoryCache() {
        setLimit(Runtime.getRuntime().maxMemory() / 4);
    }

    private void setLimit(long j) {
        this.mLimit = j;
        StringBuilder sb = new StringBuilder();
        sb.append("MemoryCache will use up to ");
        double d = this.mLimit;
        Double.isNaN(d);
        sb.append((d / 1024.0d) / 1024.0d);
        sb.append("MB");
        Log.i(TAG, sb.toString());
    }

    public Drawable get(String str) {
        try {
            if (this.mCache.containsKey(str)) {
                return this.mCache.get(str);
            }
            return null;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    void put(String str, Drawable drawable) {
        try {
            if (this.mCache.containsKey(str)) {
                this.mSize -= getSizeInBytes(((BitmapDrawable) this.mCache.get(str)).getBitmap());
            }
            this.mCache.put(str, drawable);
            this.mSize += getSizeInBytes(((BitmapDrawable) drawable).getBitmap());
            checkSize();
            Log.d(TAG, "Drawable used from cache");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void checkSize() {
        Log.i(TAG, "cache size=" + this.mSize + " length=" + this.mCache.size());
        if (this.mSize > this.mLimit) {
            Iterator<Map.Entry<String, Drawable>> it = this.mCache.entrySet().iterator();
            while (it.hasNext()) {
                this.mSize -= getSizeInBytes(((BitmapDrawable) it.next().getValue()).getBitmap());
                it.remove();
                if (this.mSize <= this.mLimit) {
                    break;
                }
            }
            Log.i(TAG, "Clean cache. New size " + this.mCache.size());
        }
    }

    public void clear() {
        try {
            this.mCache.clear();
            this.mSize = 0L;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private long getSizeInBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return 0L;
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
