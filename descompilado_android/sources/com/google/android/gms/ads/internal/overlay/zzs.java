package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzamx;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzs extends zzamx {
    private AdOverlayInfoParcel zzdhw;
    private Activity zzzr;
    private boolean zzdgu = false;
    private boolean zzdhx = false;

    public zzs(Activity activity, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzdhw = adOverlayInfoParcel;
        this.zzzr = activity;
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onActivityResult(int i, int i2, Intent intent) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onBackPressed() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onRestart() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onStart() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void zzag(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void zzcz() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final boolean zzsl() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onCreate(Bundle bundle) {
        boolean z = false;
        if (bundle != null && bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false)) {
            z = true;
        }
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzdhw;
        if (adOverlayInfoParcel == null) {
            this.zzzr.finish();
            return;
        }
        if (z) {
            this.zzzr.finish();
            return;
        }
        if (bundle == null) {
            if (adOverlayInfoParcel.zzcbl != null) {
                this.zzdhw.zzcbl.onAdClicked();
            }
            if (this.zzzr.getIntent() != null && this.zzzr.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true) && this.zzdhw.zzdhm != null) {
                this.zzdhw.zzdhm.zzsf();
            }
        }
        com.google.android.gms.ads.internal.zzp.zzjw();
        if (zza.zza(this.zzzr, this.zzdhw.zzdhl, this.zzdhw.zzdhq)) {
            return;
        }
        this.zzzr.finish();
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onResume() throws RemoteException {
        if (this.zzdgu) {
            this.zzzr.finish();
            return;
        }
        this.zzdgu = true;
        if (this.zzdhw.zzdhm != null) {
            this.zzdhw.zzdhm.onResume();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzdgu);
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onPause() throws RemoteException {
        if (this.zzdhw.zzdhm != null) {
            this.zzdhw.zzdhm.onPause();
        }
        if (this.zzzr.isFinishing()) {
            zzsu();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onStop() throws RemoteException {
        if (this.zzzr.isFinishing()) {
            zzsu();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onDestroy() throws RemoteException {
        if (this.zzzr.isFinishing()) {
            zzsu();
        }
    }

    private final synchronized void zzsu() {
        if (!this.zzdhx) {
            if (this.zzdhw.zzdhm != null) {
                this.zzdhw.zzdhm.zzse();
            }
            this.zzdhx = true;
        }
    }
}
