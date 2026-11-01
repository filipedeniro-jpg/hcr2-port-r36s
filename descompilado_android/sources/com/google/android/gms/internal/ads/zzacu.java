package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzacu implements UnifiedNativeAd.MediaContent {
    private final zzaan zzcwf;

    public zzacu(zzaan zzaanVar) {
        this.zzcwf = zzaanVar;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd.MediaContent
    public final float getAspectRatio() {
        try {
            return this.zzcwf.getAspectRatio();
        } catch (RemoteException unused) {
            return 0.0f;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd.MediaContent
    public final void setMainImage(Drawable drawable) {
        try {
            this.zzcwf.zzs(ObjectWrapper.wrap(drawable));
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd.MediaContent
    public final Drawable getMainImage() {
        try {
            IObjectWrapper iObjectWrapperZzqh = this.zzcwf.zzqh();
            if (iObjectWrapperZzqh != null) {
                return (Drawable) ObjectWrapper.unwrap(iObjectWrapperZzqh);
            }
            return null;
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    public final zzaan zzqu() {
        return this.zzcwf;
    }
}
