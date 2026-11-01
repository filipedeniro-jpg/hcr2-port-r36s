package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaat extends NativeAd.Image {
    private final int height;
    private final Uri uri;
    private final int width;
    private final double zzcvm;
    private final zzaas zzcvr;
    private final Drawable zzcvs;

    public zzaat(zzaas zzaasVar) {
        Drawable drawable;
        int width;
        this.zzcvr = zzaasVar;
        Uri uri = null;
        try {
            IObjectWrapper iObjectWrapperZzqf = this.zzcvr.zzqf();
            drawable = iObjectWrapperZzqf != null ? (Drawable) ObjectWrapper.unwrap(iObjectWrapperZzqf) : null;
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
        this.zzcvs = drawable;
        try {
            uri = this.zzcvr.getUri();
        } catch (RemoteException e2) {
            zzawo.zzc("", e2);
        }
        this.uri = uri;
        double scale = 1.0d;
        try {
            scale = this.zzcvr.getScale();
        } catch (RemoteException e3) {
            zzawo.zzc("", e3);
        }
        this.zzcvm = scale;
        int height = -1;
        try {
            width = this.zzcvr.getWidth();
        } catch (RemoteException e4) {
            zzawo.zzc("", e4);
            width = -1;
        }
        this.width = width;
        try {
            height = this.zzcvr.getHeight();
        } catch (RemoteException e5) {
            zzawo.zzc("", e5);
        }
        this.height = height;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.Image
    public final Drawable getDrawable() {
        return this.zzcvs;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.Image
    public final Uri getUri() {
        return this.uri;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.Image
    public final double getScale() {
        return this.zzcvm;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.Image
    public final int getWidth() {
        return this.width;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.Image
    public final int getHeight() {
        return this.height;
    }
}
