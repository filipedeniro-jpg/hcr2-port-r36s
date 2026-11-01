package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbyd implements zzaeh {
    private final zzbnb zzffi;

    @Nullable
    private final zzaqd zzfps;
    private final String zzfpt;
    private final String zzfpu;

    public zzbyd(zzbnb zzbnbVar, zzcvb zzcvbVar) {
        this.zzffi = zzbnbVar;
        this.zzfps = zzcvbVar.zzdks;
        this.zzfpt = zzcvbVar.zzdby;
        this.zzfpu = zzcvbVar.zzdbz;
    }

    @Override // com.google.android.gms.internal.ads.zzaeh
    public final void zzqv() {
        this.zzffi.onRewardedVideoStarted();
    }

    @Override // com.google.android.gms.internal.ads.zzaeh
    @ParametersAreNonnullByDefault
    public final void zza(zzaqd zzaqdVar) {
        String str;
        int i;
        zzaqd zzaqdVar2 = this.zzfps;
        if (zzaqdVar2 != null) {
            zzaqdVar = zzaqdVar2;
        }
        if (zzaqdVar != null) {
            str = zzaqdVar.type;
            i = zzaqdVar.zzdng;
        } else {
            str = "";
            i = 1;
        }
        this.zzffi.zzb(new zzapc(str, i), this.zzfpt, this.zzfpu);
    }

    @Override // com.google.android.gms.internal.ads.zzaeh
    public final void zzqw() {
        this.zzffi.onRewardedVideoCompleted();
    }
}
