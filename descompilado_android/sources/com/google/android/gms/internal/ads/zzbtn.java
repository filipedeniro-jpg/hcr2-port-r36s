package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbtn extends zzaaq {
    private final zzbtx zzfjc;
    private IObjectWrapper zzfjz;

    public zzbtn(zzbtx zzbtxVar) {
        this.zzfjc = zzbtxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaan
    public final float getAspectRatio() throws RemoteException {
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcsx)).booleanValue()) {
            return 0.0f;
        }
        if (this.zzfjc.getMediaContentAspectRatio() != 0.0f) {
            return this.zzfjc.getMediaContentAspectRatio();
        }
        if (this.zzfjc.getVideoController() != null) {
            return zzahf();
        }
        IObjectWrapper iObjectWrapper = this.zzfjz;
        if (iObjectWrapper != null) {
            return zzar(iObjectWrapper);
        }
        zzaas zzaasVarZzahn = this.zzfjc.zzahn();
        if (zzaasVarZzahn == null) {
            return 0.0f;
        }
        float width = (zzaasVarZzahn == null || zzaasVarZzahn.getWidth() == -1 || zzaasVarZzahn.getHeight() == -1) ? 0.0f : zzaasVarZzahn.getWidth() / zzaasVarZzahn.getHeight();
        return width != 0.0f ? width : zzar(zzaasVarZzahn.zzqf());
    }

    @Override // com.google.android.gms.internal.ads.zzaan
    public final void zzs(IObjectWrapper iObjectWrapper) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcnz)).booleanValue()) {
            this.zzfjz = iObjectWrapper;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaan
    public final IObjectWrapper zzqh() throws RemoteException {
        IObjectWrapper iObjectWrapper = this.zzfjz;
        if (iObjectWrapper != null) {
            return iObjectWrapper;
        }
        zzaas zzaasVarZzahn = this.zzfjc.zzahn();
        if (zzaasVarZzahn == null) {
            return null;
        }
        return zzaasVarZzahn.zzqf();
    }

    private final float zzahf() {
        try {
            return this.zzfjc.getVideoController().getAspectRatio();
        } catch (RemoteException e) {
            zzatm.zzc("Remote exception getting video controller aspect ratio.", e);
            return 0.0f;
        }
    }

    private static float zzar(IObjectWrapper iObjectWrapper) {
        Drawable drawable;
        if (iObjectWrapper == null || (drawable = (Drawable) ObjectWrapper.unwrap(iObjectWrapper)) == null || drawable.getIntrinsicWidth() == -1 || drawable.getIntrinsicHeight() == -1) {
            return 0.0f;
        }
        return drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight();
    }
}
