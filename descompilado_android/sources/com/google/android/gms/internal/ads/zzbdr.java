package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzbdr {
    private final zzawv zzbkw;
    private final WeakReference<Context> zzejg;
    private final Context zzlk;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static class zza {
        private zzawv zzbkw;
        private WeakReference<Context> zzejg;
        private Context zzzc;

        public final zza zza(zzawv zzawvVar) {
            this.zzbkw = zzawvVar;
            return this;
        }

        public final zza zzbr(Context context) {
            this.zzejg = new WeakReference<>(context);
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.zzzc = context;
            return this;
        }
    }

    private zzbdr(zza zzaVar) {
        this.zzbkw = zzaVar.zzbkw;
        this.zzlk = zzaVar.zzzc;
        this.zzejg = zzaVar.zzejg;
    }

    final Context zzaax() {
        return this.zzlk;
    }

    final WeakReference<Context> zzaay() {
        return this.zzejg;
    }

    final zzawv zzaaz() {
        return this.zzbkw;
    }

    final String zzaba() {
        return com.google.android.gms.ads.internal.zzp.zzjy().zzr(this.zzlk, this.zzbkw.zzbnh);
    }
}
