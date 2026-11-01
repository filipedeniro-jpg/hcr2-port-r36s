package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.ShowFirstParty;

/* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@ShowFirstParty
public final class zzcym {
    private final Looper zzgmz;
    private final Context zzlk;

    public zzcym(@NonNull Context context, @NonNull Looper looper) {
        this.zzlk = context;
        this.zzgmz = looper;
    }

    public final void zzgl(@NonNull String str) {
        new zzcyl(this.zzlk, this.zzgmz, (zzcys) ((zzdqd) zzcys.zzanu().zzgo(this.zzlk.getPackageName()).zzb(zzcys.zzb.BLOCKED_IMPRESSION).zzb(zzcyo.zzans().zzgn(str).zzb(zzcyo.zza.BLOCKED_REASON_BACKGROUND)).zzazm())).zzanr();
    }
}
