package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;
import java.util.WeakHashMap;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzpe {
    private final zzawv zzblh;
    private final zzagt zzbnd;
    private final Context zzzc;
    private final Object lock = new Object();
    private final WeakHashMap<Object, Object> zzbnb = new WeakHashMap<>();
    private final ArrayList<Object> zzbnc = new ArrayList<>();

    public zzpe(Context context, zzawv zzawvVar) {
        this.zzzc = context.getApplicationContext();
        this.zzblh = zzawvVar;
        this.zzbnd = new zzagt(context.getApplicationContext(), zzawvVar, (String) zzuo.zzoj().zzd(zzyt.zzcga));
    }
}
