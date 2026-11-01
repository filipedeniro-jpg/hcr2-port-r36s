package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzblu {
    private final zzcvk zzffn;
    private Bundle zzfgf;

    @Nullable
    private final String zzfgg;

    @Nullable
    private final zzcvi zzfgh;
    private final Context zzlk;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static class zza {
        private zzcvk zzffn;
        private Bundle zzfgf;

        @Nullable
        private String zzfgg;

        @Nullable
        private zzcvi zzfgh;
        private Context zzlk;

        public final zza zzbx(Context context) {
            this.zzlk = context;
            return this;
        }

        public final zza zza(zzcvk zzcvkVar) {
            this.zzffn = zzcvkVar;
            return this;
        }

        public final zza zze(Bundle bundle) {
            this.zzfgf = bundle;
            return this;
        }

        public final zza zzfn(String str) {
            this.zzfgg = str;
            return this;
        }

        public final zzblu zzafu() {
            return new zzblu(this);
        }

        public final zza zza(zzcvi zzcviVar) {
            this.zzfgh = zzcviVar;
            return this;
        }
    }

    private zzblu(zza zzaVar) {
        this.zzlk = zzaVar.zzlk;
        this.zzffn = zzaVar.zzffn;
        this.zzfgf = zzaVar.zzfgf;
        this.zzfgg = zzaVar.zzfgg;
        this.zzfgh = zzaVar.zzfgh;
    }

    final zza zzafp() {
        return new zza().zzbx(this.zzlk).zza(this.zzffn).zzfn(this.zzfgg).zze(this.zzfgf);
    }

    final zzcvk zzafq() {
        return this.zzffn;
    }

    @Nullable
    final zzcvi zzafr() {
        return this.zzfgh;
    }

    @Nullable
    final Bundle zzafs() {
        return this.zzfgf;
    }

    @Nullable
    final String zzaft() {
        return this.zzfgg;
    }

    final Context zzbw(Context context) {
        return this.zzfgg != null ? context : this.zzlk;
    }
}
