package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaer implements zzn {
    private volatile zzaek zzcxy;
    private final Context zzlk;

    public zzaer(Context context) {
        this.zzlk = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzn
    public final zzo zzc(zzq<?> zzqVar) throws zzae {
        zzaej zzaejVarZzh = zzaej.zzh(zzqVar);
        long jElapsedRealtime = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime();
        try {
            zzaxf zzaxfVar = new zzaxf();
            this.zzcxy = new zzaek(this.zzlk, com.google.android.gms.ads.internal.zzp.zzkm().zzvz(), new zzaev(this, zzaxfVar), new zzaey(this, zzaxfVar));
            this.zzcxy.checkAvailabilityAndConnect();
            zzdcp zzdcpVarZza = zzdcf.zza(zzdcf.zzb(zzaxfVar, new zzaeu(this, zzaejVarZzh), zzawx.zzdvx), ((Integer) zzuo.zzoj().zzd(zzyt.zzcpp)).intValue(), TimeUnit.MILLISECONDS, zzawx.zzdvz);
            zzdcpVarZza.addListener(new zzaew(this), zzawx.zzdvx);
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) zzdcpVarZza.get();
            long jElapsedRealtime2 = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - jElapsedRealtime;
            StringBuilder sb = new StringBuilder(52);
            sb.append("Http assets remote cache took ");
            sb.append(jElapsedRealtime2);
            sb.append("ms");
            zzatm.zzdy(sb.toString());
            zzael zzaelVar = (zzael) new zzaoj(parcelFileDescriptor).zza(zzael.CREATOR);
            if (zzaelVar == null) {
                return null;
            }
            if (zzaelVar.zzcxw) {
                throw new zzae(zzaelVar.zzcxx);
            }
            if (zzaelVar.zzcxu.length != zzaelVar.zzcxv.length) {
                return null;
            }
            HashMap map = new HashMap();
            for (int i = 0; i < zzaelVar.zzcxu.length; i++) {
                map.put(zzaelVar.zzcxu[i], zzaelVar.zzcxv[i]);
            }
            return new zzo(zzaelVar.statusCode, zzaelVar.data, map, zzaelVar.zzac, zzaelVar.zzad);
        } catch (InterruptedException | ExecutionException unused) {
            long jElapsedRealtime3 = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - jElapsedRealtime;
            StringBuilder sb2 = new StringBuilder(52);
            sb2.append("Http assets remote cache took ");
            sb2.append(jElapsedRealtime3);
            sb2.append("ms");
            zzatm.zzdy(sb2.toString());
            return null;
        } catch (Throwable th) {
            long jElapsedRealtime4 = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - jElapsedRealtime;
            StringBuilder sb3 = new StringBuilder(52);
            sb3.append("Http assets remote cache took ");
            sb3.append(jElapsedRealtime4);
            sb3.append("ms");
            zzatm.zzdy(sb3.toString());
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disconnect() {
        if (this.zzcxy == null) {
            return;
        }
        this.zzcxy.disconnect();
        Binder.flushPendingCommands();
    }
}
