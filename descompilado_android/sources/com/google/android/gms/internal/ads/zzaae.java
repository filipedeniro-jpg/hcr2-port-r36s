package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaae extends zzaar {
    private final int height;
    private final Uri uri;
    private final int width;
    private final Drawable zzcvl;
    private final double zzcvm;

    public zzaae(Drawable drawable, Uri uri, double d, int i, int i2) {
        this.zzcvl = drawable;
        this.uri = uri;
        this.zzcvm = d;
        this.width = i;
        this.height = i2;
    }

    @Override // com.google.android.gms.internal.ads.zzaas
    public final IObjectWrapper zzqf() throws RemoteException {
        return ObjectWrapper.wrap(this.zzcvl);
    }

    @Override // com.google.android.gms.internal.ads.zzaas
    public final Uri getUri() throws RemoteException {
        return this.uri;
    }

    @Override // com.google.android.gms.internal.ads.zzaas
    public final double getScale() {
        return this.zzcvm;
    }

    @Override // com.google.android.gms.internal.ads.zzaas
    public final int getWidth() {
        return this.width;
    }

    @Override // com.google.android.gms.internal.ads.zzaas
    public final int getHeight() {
        return this.height;
    }
}
