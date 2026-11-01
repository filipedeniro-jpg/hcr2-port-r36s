package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbyt {
    private final zzaro zzbkm;
    private final zzdf zzefm;
    private final Executor zzfbm;
    private final zzbnb zzffi;
    private final zzbnu zzfib;
    private final zzbmf zzfje;
    private final zzbgu zzfjf;
    private final zzbqb zzfjh;
    private final zzbnp zzfpk;
    private final zzbop zzfpl;
    private final com.google.android.gms.ads.internal.zza zzfqa;
    private final zzbmv zzfqb;
    private final zzbol zzfqc;

    public zzbyt(zzbmf zzbmfVar, zzbnb zzbnbVar, zzbnp zzbnpVar, zzbnu zzbnuVar, zzbop zzbopVar, Executor executor, zzbqb zzbqbVar, zzbgu zzbguVar, com.google.android.gms.ads.internal.zza zzaVar, zzbmv zzbmvVar, @Nullable zzaro zzaroVar, zzdf zzdfVar, zzbol zzbolVar) {
        this.zzfje = zzbmfVar;
        this.zzffi = zzbnbVar;
        this.zzfpk = zzbnpVar;
        this.zzfib = zzbnuVar;
        this.zzfpl = zzbopVar;
        this.zzfbm = executor;
        this.zzfjh = zzbqbVar;
        this.zzfjf = zzbguVar;
        this.zzfqa = zzaVar;
        this.zzfqb = zzbmvVar;
        this.zzbkm = zzaroVar;
        this.zzefm = zzdfVar;
        this.zzfqc = zzbolVar;
    }

    public final void zzb(final zzbbc zzbbcVar, boolean z) {
        zzdc zzdcVarZzcc;
        zzbbcVar.zzyy().zza(new zzth(this) { // from class: com.google.android.gms.internal.ads.zzbyw
            private final zzbyt zzfqe;

            {
                this.zzfqe = this;
            }

            @Override // com.google.android.gms.internal.ads.zzth
            public final void onAdClicked() {
                this.zzfqe.zzajh();
            }
        }, this.zzfpk, this.zzfib, new zzadi(this) { // from class: com.google.android.gms.internal.ads.zzbyv
            private final zzbyt zzfqe;

            {
                this.zzfqe = this;
            }

            @Override // com.google.android.gms.internal.ads.zzadi
            public final void onAppEvent(String str, String str2) {
                this.zzfqe.zzq(str, str2);
            }
        }, new com.google.android.gms.ads.internal.overlay.zzv(this) { // from class: com.google.android.gms.internal.ads.zzbyy
            private final zzbyt zzfqe;

            {
                this.zzfqe = this;
            }

            @Override // com.google.android.gms.ads.internal.overlay.zzv
            public final void zzsv() {
                this.zzfqe.zzajg();
            }
        }, z, null, this.zzfqa, new zzbzd(this), this.zzbkm);
        zzbbcVar.setOnTouchListener(new View.OnTouchListener(this) { // from class: com.google.android.gms.internal.ads.zzbyx
            private final zzbyt zzfqe;

            {
                this.zzfqe = this;
            }

            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.zzfqe.zza(view, motionEvent);
            }
        });
        zzbbcVar.setOnClickListener(new View.OnClickListener(this) { // from class: com.google.android.gms.internal.ads.zzbza
            private final zzbyt zzfqe;

            {
                this.zzfqe = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.zzfqe.zzac(view);
            }
        });
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcmu)).booleanValue() && (zzdcVarZzcc = this.zzefm.zzcc()) != null) {
            zzdcVarZzcc.zzb(zzbbcVar.getView());
        }
        this.zzfjh.zza(zzbbcVar, this.zzfbm);
        this.zzfjh.zza(new zzpl(zzbbcVar) { // from class: com.google.android.gms.internal.ads.zzbyz
            private final zzbbc zzehf;

            {
                this.zzehf = zzbbcVar;
            }

            @Override // com.google.android.gms.internal.ads.zzpl
            public final void zza(zzpi zzpiVar) {
                this.zzehf.zzyy().zza(zzpiVar.zzbnx.left, zzpiVar.zzbnx.top, false);
            }
        }, this.zzfbm);
        this.zzfjh.zzq(zzbbcVar.getView());
        zzbbcVar.zza("/trackActiveViewUnit", new zzadx(this, zzbbcVar) { // from class: com.google.android.gms.internal.ads.zzbzc
            private final zzbbc zzfoq;
            private final zzbyt zzfqe;

            {
                this.zzfqe = this;
                this.zzfoq = zzbbcVar;
            }

            @Override // com.google.android.gms.internal.ads.zzadx
            public final void zza(Object obj, Map map) {
                this.zzfqe.zza(this.zzfoq, (zzbbc) obj, map);
            }
        });
        this.zzfjf.zzo(zzbbcVar);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcka)).booleanValue()) {
            return;
        }
        zzbmv zzbmvVar = this.zzfqb;
        zzbbcVar.getClass();
        zzbmvVar.zza(zzbzb.zzn(zzbbcVar), this.zzfbm);
    }

    public static zzdcp<?> zza(zzbbc zzbbcVar, String str, String str2) {
        final zzaxf zzaxfVar = new zzaxf();
        zzbbcVar.zzyy().zza(new zzbcp(zzaxfVar) { // from class: com.google.android.gms.internal.ads.zzbze
            private final zzaxf zzbrp;

            {
                this.zzbrp = zzaxfVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbcp
            public final void zzab(boolean z) {
                zzaxf zzaxfVar2 = this.zzbrp;
                if (z) {
                    zzaxfVar2.set(null);
                } else {
                    zzaxfVar2.setException(new Exception("Ad Web View failed to load."));
                }
            }
        });
        zzbbcVar.zzb(str, str2, null);
        return zzaxfVar;
    }

    final /* synthetic */ void zza(zzbbc zzbbcVar, zzbbc zzbbcVar2, Map map) {
        this.zzfjf.zzf(zzbbcVar);
    }

    final /* synthetic */ void zzac(View view) {
        this.zzfqa.recordClick();
        zzaro zzaroVar = this.zzbkm;
        if (zzaroVar != null) {
            zzaroVar.zztk();
        }
    }

    final /* synthetic */ boolean zza(View view, MotionEvent motionEvent) {
        this.zzfqa.recordClick();
        zzaro zzaroVar = this.zzbkm;
        if (zzaroVar == null) {
            return false;
        }
        zzaroVar.zztk();
        return false;
    }

    final /* synthetic */ void zzajg() {
        this.zzffi.onAdLeftApplication();
    }

    final /* synthetic */ void zzq(String str, String str2) {
        this.zzfpl.onAppEvent(str, str2);
    }

    final /* synthetic */ void zzajh() {
        this.zzfje.onAdClicked();
    }
}
