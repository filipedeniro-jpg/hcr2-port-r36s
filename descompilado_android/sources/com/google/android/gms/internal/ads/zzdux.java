package com.google.android.gms.internal.ads;

import android.util.Log;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdux extends zzdva {
    private String name;

    public zzdux(String str) {
        this.name = str;
    }

    @Override // com.google.android.gms.internal.ads.zzdva
    public final void zzhr(String str) {
        String str2 = this.name;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str).length());
        sb.append(str2);
        sb.append(":");
        sb.append(str);
        Log.d("isoparser", sb.toString());
    }
}
