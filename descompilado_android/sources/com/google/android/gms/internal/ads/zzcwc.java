package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcwc {
    private final zzawv zzbkw;
    private final zzatb zzblz;
    private final Map<String, zzcwe> zzgkk = new HashMap();
    private final zzdf zzgkl;
    private final Context zzzc;

    public zzcwc(Context context, zzawv zzawvVar, zzatb zzatbVar) {
        this.zzzc = context;
        this.zzbkw = zzawvVar;
        this.zzblz = zzatbVar;
        this.zzgkl = new zzdf(new com.google.android.gms.ads.internal.zzf(context, zzawvVar));
    }

    public final zzcwe zzgg(@Nullable String str) {
        if (str == null) {
            return zzand();
        }
        if (this.zzgkk.containsKey(str)) {
            return this.zzgkk.get(str);
        }
        zzcwe zzcweVarZzgh = zzgh(str);
        this.zzgkk.put(str, zzcweVarZzgh);
        return zzcweVarZzgh;
    }

    private final zzcwe zzand() {
        return new zzcwe(this.zzzc, this.zzblz.zzuj(), this.zzblz.zzul(), this.zzgkl);
    }

    private final zzcwe zzgh(String str) {
        zzapf zzapfVarZzy = zzapf.zzy(this.zzzc);
        try {
            zzapfVarZzy.setAppPackageName(str);
            zzatr zzatrVar = new zzatr();
            zzatrVar.zza(this.zzzc, str, false);
            zzats zzatsVar = new zzats(this.zzblz.zzuj(), zzatrVar);
            return new zzcwe(zzapfVarZzy, zzatsVar, new zzatj(zzawe.zzwd(), zzatsVar), new zzdf(new com.google.android.gms.ads.internal.zzf(this.zzzc, this.zzbkw)));
        } catch (PackageManager.NameNotFoundException unused) {
            return zzand();
        }
    }
}
