package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzaid {

    @VisibleForTesting
    private static final zzavb<zzagk> zzdap = new zzaig();

    @VisibleForTesting
    private static final zzavb<zzagk> zzdaq = new zzaif();
    private final zzagt zzdar;

    public zzaid(Context context, zzawv zzawvVar, String str) {
        this.zzdar = new zzagt(context, zzawvVar, str, zzdap, zzdaq);
    }

    public final <I, O> zzahv<I, O> zza(String str, zzaia<I> zzaiaVar, zzahx<O> zzahxVar) {
        return new zzaii(this.zzdar, str, zzaiaVar, zzahxVar);
    }

    public final zzaim zzrh() {
        return new zzaim(this.zzdar);
    }
}
