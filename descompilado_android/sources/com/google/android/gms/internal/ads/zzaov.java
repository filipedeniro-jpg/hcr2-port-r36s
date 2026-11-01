package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.WeakHashMap;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaov {
    private WeakHashMap<Context, zzaox> zzdna = new WeakHashMap<>();

    public final Future<zzaot> zzw(Context context) {
        return zzawx.zzdvx.submit(new zzaou(this, context));
    }
}
