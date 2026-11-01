package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzbds implements zzbga {

    @GuardedBy("AppComponent.class")
    private static zzbds zzejh;

    protected abstract zzcrs zza(zzcsz zzcszVar);

    public abstract Executor zzabb();

    public abstract ScheduledExecutorService zzabc();

    public abstract Executor zzabd();

    public abstract zzdcs zzabe();

    public abstract zzboa zzabf();

    public abstract zzcgw zzabg();

    public abstract zzbfk zzabh();

    public abstract zzbiw zzabi();

    public abstract zzbhn zzabj();

    public abstract zzbrx zzabk();

    public abstract zzbsu zzabl();

    public abstract zzbyl zzabm();

    public abstract zzcus zzabn();

    public abstract zzcmi zzabo();

    public static zzbds zza(Context context, zzajd zzajdVar, int i) {
        zzbds zzbdsVarZzd = zzd(context, i);
        zzbdsVarZzd.zzabg().zzb(zzajdVar);
        return zzbdsVarZzd;
    }

    @Deprecated
    public static zzbds zzd(Context context, int i) {
        synchronized (zzbds.class) {
            if (zzejh != null) {
                return zzejh;
            }
            return zza(new zzawv(15301000, i, true, false), context, new zzbeh());
        }
    }

    @Deprecated
    private static zzbds zza(zzawv zzawvVar, Context context, zzbfg.zza zzaVar) {
        zzbds zzbdsVar;
        synchronized (zzbds.class) {
            if (zzejh == null) {
                zzejh = new zzbeu().zzc(new zzbdr(new zzbdr.zza().zza(zzawvVar).zzbr(context))).zza(new zzbfg(zzaVar)).zzada();
                zzyt.initialize(context);
                com.google.android.gms.ads.internal.zzp.zzkc().zzd(context, zzawvVar);
                com.google.android.gms.ads.internal.zzp.zzke().initialize(context);
                com.google.android.gms.ads.internal.zzp.zzjy().zzan(context);
                com.google.android.gms.ads.internal.zzp.zzjy().zzao(context);
                zzatk.zzam(context);
                com.google.android.gms.ads.internal.zzp.zzkb().initialize(context);
                com.google.android.gms.ads.internal.zzp.zzkt().initialize(context);
                if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcst)).booleanValue()) {
                    new zzcfg(context, zzawvVar, new zzrz(new zzse(context)), new zzcep(new zzcen(context), zzejh.zzabe())).zzakn();
                }
            }
            zzbdsVar = zzejh;
        }
        return zzbdsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbga
    public final zzcrs zza(zzaok zzaokVar) {
        return zza(new zzcsz(zzaokVar));
    }
}
