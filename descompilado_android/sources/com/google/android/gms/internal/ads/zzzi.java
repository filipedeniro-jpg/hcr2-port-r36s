package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzzi {
    private final Map<String, zzzf> zzcug = new HashMap();

    @Nullable
    private final zzzh zzcuh;

    public zzzi(@Nullable zzzh zzzhVar) {
        this.zzcuh = zzzhVar;
    }

    public final void zza(String str, zzzf zzzfVar) {
        this.zzcug.put(str, zzzfVar);
    }

    public final void zza(String str, String str2, long j) {
        zzzh zzzhVar = this.zzcuh;
        zzzf zzzfVar = this.zzcug.get(str2);
        String[] strArr = {str};
        if (zzzhVar != null && zzzfVar != null) {
            zzzhVar.zza(zzzfVar, j, strArr);
        }
        Map<String, zzzf> map = this.zzcug;
        zzzh zzzhVar2 = this.zzcuh;
        map.put(str, zzzhVar2 == null ? null : zzzhVar2.zzer(j));
    }

    @Nullable
    public final zzzh zzpy() {
        return this.zzcuh;
    }
}
