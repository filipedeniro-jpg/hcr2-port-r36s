package com.google.android.gms.internal.ads;

import android.os.Bundle;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbxw implements com.google.android.gms.ads.internal.overlay.zzp, com.google.android.gms.ads.internal.overlay.zzv, zzadg, zzadi, zzth {
    private zzth zzcbl;
    private zzadg zzcwp;
    private zzadi zzcwr;
    private com.google.android.gms.ads.internal.overlay.zzp zzdhm;
    private com.google.android.gms.ads.internal.overlay.zzv zzdhq;

    private zzbxw() {
    }

    @Override // com.google.android.gms.internal.ads.zzth
    public final synchronized void onAdClicked() {
        if (this.zzcbl != null) {
            this.zzcbl.onAdClicked();
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final synchronized void zzse() {
        if (this.zzdhm != null) {
            this.zzdhm.zzse();
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final synchronized void onPause() {
        if (this.zzdhm != null) {
            this.zzdhm.onPause();
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final synchronized void onResume() {
        if (this.zzdhm != null) {
            this.zzdhm.onResume();
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final synchronized void zzsf() {
        if (this.zzdhm != null) {
            this.zzdhm.zzsf();
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzv
    public final synchronized void zzsv() {
        if (this.zzdhq != null) {
            this.zzdhq.zzsv();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzadg
    public final synchronized void zza(String str, Bundle bundle) {
        if (this.zzcwp != null) {
            this.zzcwp.zza(str, bundle);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzadi
    public final synchronized void onAppEvent(String str, @Nullable String str2) {
        if (this.zzcwr != null) {
            this.zzcwr.onAppEvent(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void zza(zzth zzthVar, zzadg zzadgVar, com.google.android.gms.ads.internal.overlay.zzp zzpVar, zzadi zzadiVar, com.google.android.gms.ads.internal.overlay.zzv zzvVar) {
        this.zzcbl = zzthVar;
        this.zzcwp = zzadgVar;
        this.zzdhm = zzpVar;
        this.zzcwr = zzadiVar;
        this.zzdhq = zzvVar;
    }

    /* synthetic */ zzbxw(zzbxs zzbxsVar) {
        this();
    }
}
