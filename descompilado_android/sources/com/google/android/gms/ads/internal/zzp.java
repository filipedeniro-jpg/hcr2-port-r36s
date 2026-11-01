package com.google.android.gms.ads.internal;

import android.os.Build;
import com.google.android.gms.ads.internal.overlay.zzu;
import com.google.android.gms.ads.internal.overlay.zzx;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.ads.zzafu;
import com.google.android.gms.internal.ads.zzagd;
import com.google.android.gms.internal.ads.zzahy;
import com.google.android.gms.internal.ads.zzajb;
import com.google.android.gms.internal.ads.zzamr;
import com.google.android.gms.internal.ads.zzank;
import com.google.android.gms.internal.ads.zzant;
import com.google.android.gms.internal.ads.zzaov;
import com.google.android.gms.internal.ads.zzarv;
import com.google.android.gms.internal.ads.zzatb;
import com.google.android.gms.internal.ads.zzatv;
import com.google.android.gms.internal.ads.zzaub;
import com.google.android.gms.internal.ads.zzauk;
import com.google.android.gms.internal.ads.zzaus;
import com.google.android.gms.internal.ads.zzavq;
import com.google.android.gms.internal.ads.zzavt;
import com.google.android.gms.internal.ads.zzawa;
import com.google.android.gms.internal.ads.zzaxe;
import com.google.android.gms.internal.ads.zzaxn;
import com.google.android.gms.internal.ads.zzbae;
import com.google.android.gms.internal.ads.zzbbl;
import com.google.android.gms.internal.ads.zzpx;
import com.google.android.gms.internal.ads.zzra;
import com.google.android.gms.internal.ads.zzrd;
import com.google.android.gms.internal.ads.zzrw;
import com.google.android.gms.internal.ads.zzyz;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzp {
    private static zzp zzblq = new zzp();
    private final com.google.android.gms.ads.internal.overlay.zza zzblr;
    private final zzant zzbls;
    private final com.google.android.gms.ads.internal.overlay.zzl zzblt;
    private final zzank zzblu;
    private final zzatv zzblv;
    private final zzbbl zzblw;
    private final zzaub zzblx;
    private final zzpx zzbly;
    private final zzatb zzblz;
    private final zzauk zzbma;
    private final zzra zzbmb;
    private final zzrd zzbmc;
    private final Clock zzbmd;
    private final zze zzbme;
    private final zzyz zzbmf;
    private final zzaus zzbmg;
    private final zzaov zzbmh;
    private final zzagd zzbmi;
    private final zzaxe zzbmj;
    private final zzafu zzbmk;
    private final zzahy zzbml;
    private final zzavq zzbmm;
    private final zzu zzbmn;
    private final zzx zzbmo;
    private final zzajb zzbmp;
    private final zzavt zzbmq;
    private final zzamr zzbmr;
    private final zzrw zzbms;
    private final zzarv zzbmt;
    private final zzawa zzbmu;
    private final zzbae zzbmv;
    private final zzaxn zzbmw;

    protected zzp() {
        this(new com.google.android.gms.ads.internal.overlay.zza(), new zzant(), new com.google.android.gms.ads.internal.overlay.zzl(), new zzank(), new zzatv(), new zzbbl(), zzaub.zzcp(Build.VERSION.SDK_INT), new zzpx(), new zzatb(), new zzauk(), new zzra(), new zzrd(), DefaultClock.getInstance(), new zze(), new zzyz(), new zzaus(), new zzaov(), new zzagd(), new zzaxe(), new zzahy(), new zzavq(), new zzu(), new zzx(), new zzajb(), new zzavt(), new zzamr(), new zzrw(), new zzarv(), new zzawa(), new zzbae(), new zzaxn());
    }

    private zzp(com.google.android.gms.ads.internal.overlay.zza zzaVar, zzant zzantVar, com.google.android.gms.ads.internal.overlay.zzl zzlVar, zzank zzankVar, zzatv zzatvVar, zzbbl zzbblVar, zzaub zzaubVar, zzpx zzpxVar, zzatb zzatbVar, zzauk zzaukVar, zzra zzraVar, zzrd zzrdVar, Clock clock, zze zzeVar, zzyz zzyzVar, zzaus zzausVar, zzaov zzaovVar, zzagd zzagdVar, zzaxe zzaxeVar, zzahy zzahyVar, zzavq zzavqVar, zzu zzuVar, zzx zzxVar, zzajb zzajbVar, zzavt zzavtVar, zzamr zzamrVar, zzrw zzrwVar, zzarv zzarvVar, zzawa zzawaVar, zzbae zzbaeVar, zzaxn zzaxnVar) {
        this.zzblr = zzaVar;
        this.zzbls = zzantVar;
        this.zzblt = zzlVar;
        this.zzblu = zzankVar;
        this.zzblv = zzatvVar;
        this.zzblw = zzbblVar;
        this.zzblx = zzaubVar;
        this.zzbly = zzpxVar;
        this.zzblz = zzatbVar;
        this.zzbma = zzaukVar;
        this.zzbmb = zzraVar;
        this.zzbmc = zzrdVar;
        this.zzbmd = clock;
        this.zzbme = zzeVar;
        this.zzbmf = zzyzVar;
        this.zzbmg = zzausVar;
        this.zzbmh = zzaovVar;
        this.zzbmi = zzagdVar;
        this.zzbmj = zzaxeVar;
        this.zzbmk = new zzafu();
        this.zzbml = zzahyVar;
        this.zzbmm = zzavqVar;
        this.zzbmn = zzuVar;
        this.zzbmo = zzxVar;
        this.zzbmp = zzajbVar;
        this.zzbmq = zzavtVar;
        this.zzbmr = zzamrVar;
        this.zzbms = zzrwVar;
        this.zzbmt = zzarvVar;
        this.zzbmu = zzawaVar;
        this.zzbmv = zzbaeVar;
        this.zzbmw = zzaxnVar;
    }

    public static com.google.android.gms.ads.internal.overlay.zza zzjw() {
        return zzblq.zzblr;
    }

    public static com.google.android.gms.ads.internal.overlay.zzl zzjx() {
        return zzblq.zzblt;
    }

    public static zzatv zzjy() {
        return zzblq.zzblv;
    }

    public static zzbbl zzjz() {
        return zzblq.zzblw;
    }

    public static zzaub zzka() {
        return zzblq.zzblx;
    }

    public static zzpx zzkb() {
        return zzblq.zzbly;
    }

    public static zzatb zzkc() {
        return zzblq.zzblz;
    }

    public static zzauk zzkd() {
        return zzblq.zzbma;
    }

    public static zzrd zzke() {
        return zzblq.zzbmc;
    }

    public static Clock zzkf() {
        return zzblq.zzbmd;
    }

    public static zze zzkg() {
        return zzblq.zzbme;
    }

    public static zzyz zzkh() {
        return zzblq.zzbmf;
    }

    public static zzaus zzki() {
        return zzblq.zzbmg;
    }

    public static zzaov zzkj() {
        return zzblq.zzbmh;
    }

    public static zzaxe zzkk() {
        return zzblq.zzbmj;
    }

    public static zzahy zzkl() {
        return zzblq.zzbml;
    }

    public static zzavq zzkm() {
        return zzblq.zzbmm;
    }

    public static zzamr zzkn() {
        return zzblq.zzbmr;
    }

    public static zzu zzko() {
        return zzblq.zzbmn;
    }

    public static zzx zzkp() {
        return zzblq.zzbmo;
    }

    public static zzajb zzkq() {
        return zzblq.zzbmp;
    }

    public static zzavt zzkr() {
        return zzblq.zzbmq;
    }

    public static zzrw zzks() {
        return zzblq.zzbms;
    }

    public static zzawa zzkt() {
        return zzblq.zzbmu;
    }

    public static zzbae zzku() {
        return zzblq.zzbmv;
    }

    public static zzaxn zzkv() {
        return zzblq.zzbmw;
    }

    public static zzarv zzkw() {
        return zzblq.zzbmt;
    }
}
