package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzave {
    private static zzu zzdtt;
    private static final Object zzdtu = new Object();

    @Deprecated
    private static final zzavi<Void> zzdtv = new zzavh();

    public zzave(Context context) {
        zzbg(context.getApplicationContext() != null ? context.getApplicationContext() : context);
    }

    public static zzdcp<zzo> zzel(String str) {
        zzaxf zzaxfVar = new zzaxf();
        zzdtt.zze(new zzavk(str, zzaxfVar));
        return zzaxfVar;
    }

    public final zzdcp<String> zza(int i, String str, @Nullable Map<String, String> map, @Nullable byte[] bArr) {
        zzavl zzavlVar = new zzavl(null);
        zzavg zzavgVar = new zzavg(this, str, zzavlVar);
        zzawi zzawiVar = new zzawi(null);
        zzavj zzavjVar = new zzavj(this, i, str, zzavlVar, zzavgVar, bArr, map, zzawiVar);
        if (zzawi.isEnabled()) {
            try {
                zzawiVar.zza(str, HttpRequest.METHOD_GET, zzavjVar.getHeaders(), zzavjVar.zzf());
            } catch (zzb e) {
                zzatm.zzeu(e.getMessage());
            }
        }
        zzdtt.zze(zzavjVar);
        return zzavlVar;
    }

    public final zzdcp<String> zzc(String str, Map<String, String> map) {
        return zza(0, str, map, null);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0024  */
    @VisibleForTesting
    private static zzu zzbg(Context context) {
        zzu zzuVar;
        boolean z;
        zzu zzuVar2;
        synchronized (zzdtu) {
            if (zzdtt == null) {
                zzyt.initialize(context);
                if (ClientLibraryUtils.isPackageSide()) {
                    z = false;
                } else {
                    if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcpn)).booleanValue()) {
                        z = true;
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    zzuVar2 = zzaux.zzbf(context);
                } else {
                    zzuVar2 = new zzu(new zzal(new File(context.getCacheDir(), "volley")), new zzak(new zzar()));
                    zzuVar2.start();
                }
                zzdtt = zzuVar2;
            }
            zzuVar = zzdtt;
        }
        return zzuVar;
    }
}
