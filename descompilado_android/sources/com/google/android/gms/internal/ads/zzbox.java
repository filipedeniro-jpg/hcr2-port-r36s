package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.common.util.Clock;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzbox {
    private final Set<zzbqc<zzth>> zzfgx;
    private final Set<zzbqc<zzbmg>> zzfgy;
    private final Set<zzbqc<zzbmt>> zzfgz;
    private final Set<zzbqc<zzbnv>> zzfha;
    private final Set<zzbqc<zzbnm>> zzfhb;
    private final Set<zzbqc<zzbml>> zzfhc;
    private final Set<zzbqc<zzbmp>> zzfhd;
    private final Set<zzbqc<AdMetadataListener>> zzfhe;
    private final Set<zzbqc<AppEventListener>> zzfhf;
    private zzbmj zzfhg;
    private zzcil zzfhh;

    private zzbox(zza zzaVar) {
        this.zzfgx = zzaVar.zzfgx;
        this.zzfgz = zzaVar.zzfgz;
        this.zzfha = zzaVar.zzfha;
        this.zzfgy = zzaVar.zzfgy;
        this.zzfhb = zzaVar.zzfhb;
        this.zzfhc = zzaVar.zzfhc;
        this.zzfhd = zzaVar.zzfhd;
        this.zzfhe = zzaVar.zzfhe;
        this.zzfhf = zzaVar.zzfhf;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static class zza {
        private Set<zzbqc<zzth>> zzfgx = new HashSet();
        private Set<zzbqc<zzbmg>> zzfgy = new HashSet();
        private Set<zzbqc<zzbmt>> zzfgz = new HashSet();
        private Set<zzbqc<zzbnv>> zzfha = new HashSet();
        private Set<zzbqc<zzbnm>> zzfhb = new HashSet();
        private Set<zzbqc<zzbml>> zzfhc = new HashSet();
        private Set<zzbqc<AdMetadataListener>> zzfhe = new HashSet();
        private Set<zzbqc<AppEventListener>> zzfhf = new HashSet();
        private Set<zzbqc<zzbmp>> zzfhd = new HashSet();

        public final zza zza(zzbmg zzbmgVar, Executor executor) {
            this.zzfgy.add(new zzbqc<>(zzbmgVar, executor));
            return this;
        }

        public final zza zza(zzbnm zzbnmVar, Executor executor) {
            this.zzfhb.add(new zzbqc<>(zzbnmVar, executor));
            return this;
        }

        public final zza zza(zzbml zzbmlVar, Executor executor) {
            this.zzfhc.add(new zzbqc<>(zzbmlVar, executor));
            return this;
        }

        public final zza zza(zzbmp zzbmpVar, Executor executor) {
            this.zzfhd.add(new zzbqc<>(zzbmpVar, executor));
            return this;
        }

        public final zza zza(AdMetadataListener adMetadataListener, Executor executor) {
            this.zzfhe.add(new zzbqc<>(adMetadataListener, executor));
            return this;
        }

        public final zza zza(AppEventListener appEventListener, Executor executor) {
            this.zzfhf.add(new zzbqc<>(appEventListener, executor));
            return this;
        }

        public final zza zza(@Nullable zzvm zzvmVar, Executor executor) {
            if (this.zzfhf != null) {
                zzclr zzclrVar = new zzclr();
                zzclrVar.zzb(zzvmVar);
                this.zzfhf.add(new zzbqc<>(zzclrVar, executor));
            }
            return this;
        }

        public final zza zza(zzth zzthVar, Executor executor) {
            this.zzfgx.add(new zzbqc<>(zzthVar, executor));
            return this;
        }

        public final zza zza(zzbmt zzbmtVar, Executor executor) {
            this.zzfgz.add(new zzbqc<>(zzbmtVar, executor));
            return this;
        }

        public final zza zza(zzbnv zzbnvVar, Executor executor) {
            this.zzfha.add(new zzbqc<>(zzbnvVar, executor));
            return this;
        }

        public final zzbox zzagi() {
            return new zzbox(this);
        }
    }

    public final Set<zzbqc<zzbmg>> zzafz() {
        return this.zzfgy;
    }

    public final Set<zzbqc<zzbnm>> zzaga() {
        return this.zzfhb;
    }

    public final Set<zzbqc<zzbml>> zzagb() {
        return this.zzfhc;
    }

    public final Set<zzbqc<zzbmp>> zzagc() {
        return this.zzfhd;
    }

    public final Set<zzbqc<AdMetadataListener>> zzagd() {
        return this.zzfhe;
    }

    public final Set<zzbqc<AppEventListener>> zzage() {
        return this.zzfhf;
    }

    public final Set<zzbqc<zzth>> zzagf() {
        return this.zzfgx;
    }

    public final Set<zzbqc<zzbmt>> zzagg() {
        return this.zzfgz;
    }

    public final Set<zzbqc<zzbnv>> zzagh() {
        return this.zzfha;
    }

    public final zzbmj zzc(Set<zzbqc<zzbml>> set) {
        if (this.zzfhg == null) {
            this.zzfhg = new zzbmj(set);
        }
        return this.zzfhg;
    }

    public final zzcil zza(Clock clock) {
        if (this.zzfhh == null) {
            this.zzfhh = new zzcil(clock);
        }
        return this.zzfhh;
    }
}
