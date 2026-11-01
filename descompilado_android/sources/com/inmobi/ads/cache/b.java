package com.inmobi.ads.cache;

import androidx.annotation.Nullable;
import com.inmobi.ads.bm;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: AdAssetBatch.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    Set<bm> b;
    int d;
    int e;
    public String f;

    @Nullable
    public String g;
    public String h;
    private String i;
    private final WeakReference<f> j;
    public List<a> a = new ArrayList();
    Set<String> c = new HashSet();

    public b(String str, String str2, Set<bm> set, f fVar) {
        this.i = str;
        this.f = str2;
        this.b = set;
        this.j = new WeakReference<>(fVar);
    }

    public b(String str, Set<bm> set, f fVar, String str2) {
        this.i = str;
        this.h = str2;
        this.b = set;
        this.j = new WeakReference<>(fVar);
    }

    @Nullable
    public final f a() {
        return this.j.get();
    }

    public final String toString() {
        return "AdAssetBatch{mRawAssets=" + this.b + ", mBatchDownloadSuccessCount=" + this.d + ", mBatchDownloadFailureCount=" + this.e + '}';
    }
}
