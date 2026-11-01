package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbxc extends zzafo implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, zzaab {
    private zzwk zzfkv;
    private View zzfla;
    private zzbtp zzfma;
    private boolean zzefy = false;
    private boolean zzfow = false;

    public zzbxc(zzbtp zzbtpVar, zzbtx zzbtxVar) {
        this.zzfla = zzbtxVar.zzahp();
        this.zzfkv = zzbtxVar.getVideoController();
        this.zzfma = zzbtpVar;
        if (zzbtxVar.zzahq() != null) {
            zzbtxVar.zzahq().zza(this);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzafl
    public final void zza(IObjectWrapper iObjectWrapper, zzafn zzafnVar) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzefy) {
            zzatm.zzes("Instream ad is destroyed already.");
            zza(zzafnVar, 2);
            return;
        }
        if (this.zzfla == null || this.zzfkv == null) {
            String str = this.zzfla == null ? "can not get video view." : "can not get video controller.";
            zzatm.zzes(str.length() != 0 ? "Instream internal error: ".concat(str) : new String("Instream internal error: "));
            zza(zzafnVar, 0);
            return;
        }
        if (this.zzfow) {
            zzatm.zzes("Instream ad should not be used again.");
            zza(zzafnVar, 1);
            return;
        }
        this.zzfow = true;
        zzaiy();
        ((ViewGroup) ObjectWrapper.unwrap(iObjectWrapper)).addView(this.zzfla, new ViewGroup.LayoutParams(-1, -1));
        com.google.android.gms.ads.internal.zzp.zzkv();
        zzaxn.zza(this.zzfla, (ViewTreeObserver.OnGlobalLayoutListener) this);
        com.google.android.gms.ads.internal.zzp.zzkv();
        zzaxn.zza(this.zzfla, (ViewTreeObserver.OnScrollChangedListener) this);
        zzaiz();
        try {
            zzafnVar.zzqy();
        } catch (RemoteException e) {
            zzatm.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzafl
    public final zzwk getVideoController() throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzefy) {
            zzatm.zzes("getVideoController: Instream ad should not be used after destroyed");
            return null;
        }
        return this.zzfkv;
    }

    @Override // com.google.android.gms.internal.ads.zzafl
    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzaiy();
        zzbtp zzbtpVar = this.zzfma;
        if (zzbtpVar != null) {
            zzbtpVar.destroy();
        }
        this.zzfma = null;
        this.zzfla = null;
        this.zzfkv = null;
        this.zzefy = true;
    }

    private final void zzaiy() {
        View view = this.zzfla;
        if (view == null) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.zzfla);
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        zzaiz();
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        zzaiz();
    }

    @Override // com.google.android.gms.internal.ads.zzaab
    public final void zzqe() {
        zzatv.zzdsk.post(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzbxb
            private final zzbxc zzfov;

            {
                this.zzfov = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfov.zzaja();
            }
        });
    }

    private final void zzaiz() {
        View view;
        zzbtp zzbtpVar = this.zzfma;
        if (zzbtpVar == null || (view = this.zzfla) == null) {
            return;
        }
        zzbtpVar.zzb(view, Collections.emptyMap(), Collections.emptyMap(), zzbtp.zzx(this.zzfla));
    }

    private static void zza(zzafn zzafnVar, int i) {
        try {
            zzafnVar.zzck(i);
        } catch (RemoteException e) {
            zzatm.zze("#007 Could not call remote method.", e);
        }
    }

    final /* synthetic */ void zzaja() {
        try {
            destroy();
        } catch (RemoteException e) {
            zzatm.zze("#007 Could not call remote method.", e);
        }
    }
}
