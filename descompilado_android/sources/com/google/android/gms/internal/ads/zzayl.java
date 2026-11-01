package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzayl extends zzayd {
    @Override // com.google.android.gms.internal.ads.zzayd
    @Nullable
    public final zzaya zza(Context context, zzayt zzaytVar, int i, boolean z, zzzh zzzhVar, zzayq zzayqVar) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (!(PlatformVersion.isAtLeastIceCreamSandwich() && (applicationInfo == null || applicationInfo.targetSdkVersion >= 11))) {
            return null;
        }
        zzays zzaysVar = new zzays(context, zzaytVar.zzxp(), zzaytVar.zzxn(), zzzhVar, zzaytVar.zzxk());
        if ((Build.VERSION.SDK_INT >= 16 && i == 2) && Arrays.asList(zzayqVar.zzdza.split(",")).contains("3")) {
            return new zzayw(context, zzaysVar, zzaytVar, z, zzb(zzaytVar), zzayqVar);
        }
        return new zzaxr(context, z, zzb(zzaytVar), zzayqVar, new zzays(context, zzaytVar.zzxp(), zzaytVar.zzxn(), zzzhVar, zzaytVar.zzxk()));
    }
}
