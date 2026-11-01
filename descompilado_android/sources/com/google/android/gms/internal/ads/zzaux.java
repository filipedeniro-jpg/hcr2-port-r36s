package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.File;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaux extends zzak {
    private final Context zzlk;

    public static zzu zzbf(Context context) {
        zzu zzuVar = new zzu(new zzal(new File(context.getCacheDir(), "admob_volley"), 20971520), new zzaux(context, new zzar()));
        zzuVar.start();
        return zzuVar;
    }

    private zzaux(Context context, zzah zzahVar) {
        super(zzahVar);
        this.zzlk = context;
    }

    @Override // com.google.android.gms.internal.ads.zzak, com.google.android.gms.internal.ads.zzn
    public final zzo zzc(zzq<?> zzqVar) throws zzae {
        if (zzqVar.zzg() && zzqVar.getMethod() == 0) {
            if (Pattern.matches((String) zzuo.zzoj().zzd(zzyt.zzcpo), zzqVar.getUrl())) {
                zzuo.zzof();
                if (zzawe.zzc(this.zzlk, 13400000)) {
                    zzo zzoVarZzc = new zzaer(this.zzlk).zzc(zzqVar);
                    if (zzoVarZzc != null) {
                        String strValueOf = String.valueOf(zzqVar.getUrl());
                        zzatm.zzdy(strValueOf.length() != 0 ? "Got gmscore asset response: ".concat(strValueOf) : new String("Got gmscore asset response: "));
                        return zzoVarZzc;
                    }
                    String strValueOf2 = String.valueOf(zzqVar.getUrl());
                    zzatm.zzdy(strValueOf2.length() != 0 ? "Failed to get gmscore asset response: ".concat(strValueOf2) : new String("Failed to get gmscore asset response: "));
                }
            }
        }
        return super.zzc(zzqVar);
    }
}
