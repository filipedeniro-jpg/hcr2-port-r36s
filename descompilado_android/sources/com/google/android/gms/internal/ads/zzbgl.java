package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgl {
    private final Executor executor;
    private final String zzbng;
    private final zzaim zzfaw;
    private zzbgu zzfax;
    private final zzadx<Object> zzfay = new zzbgo(this);
    private final zzadx<Object> zzfaz = new zzbgq(this);

    public zzbgl(String str, zzaim zzaimVar, Executor executor) {
        this.zzbng = str;
        this.zzfaw = zzaimVar;
        this.executor = executor;
    }

    public final void zza(zzbgu zzbguVar) {
        this.zzfaw.zzc("/updateActiveView", this.zzfay);
        this.zzfaw.zzc("/untrackActiveViewUnit", this.zzfaz);
        this.zzfax = zzbguVar;
    }

    public final void zzd(zzbbc zzbbcVar) {
        zzbbcVar.zza("/updateActiveView", this.zzfay);
        zzbbcVar.zza("/untrackActiveViewUnit", this.zzfaz);
    }

    public final void zze(zzbbc zzbbcVar) {
        zzbbcVar.zzb("/updateActiveView", this.zzfay);
        zzbbcVar.zzb("/untrackActiveViewUnit", this.zzfaz);
    }

    public final void zzaed() {
        this.zzfaw.zzd("/updateActiveView", this.zzfay);
        this.zzfaw.zzd("/untrackActiveViewUnit", this.zzfaz);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzl(@Nullable Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(this.zzbng);
    }
}
